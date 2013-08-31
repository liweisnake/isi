package skynet.isi.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

@SuppressWarnings("unchecked")
public class BaseHibernateDao<T, PK extends Serializable> {

	protected Logger logger = LogManager.getLogger(getClass().getName());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * 用于Dao层子类使用的构�?函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * BaseHibernateDao<User, Long>
	 */

	public BaseHibernateDao() {
		this.entityClass = getSuperClassGenricType(getClass(), 0);
	}

	/**
	 * 用于用于省略Dao�? 在Service层直接使用�?用BaseHibernateDao的构造函�? 在构造函数中定义对象类型Class. eg.
	 * BaseHibernateDao<User, Long> userDao = new BaseHibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 */
	@Autowired
	public BaseHibernateDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函�?
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity) {
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属�?的transient对象.
	 */
	public void delete(final T entity) {
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(),
				id);
	}

	/***
	 * 获得某列的最大值
	 * 
	 * @param propertyName
	 * @return
	 */
	public int getMax(String propertyName) {
		Object max = getSession()
				.createCriteria(entityClass)
				.setProjection(
						Projections.projectionList().add(
								Projections.max(propertyName))).uniqueResult();
		if (max == null)
			return 0;
		else
			return (Integer) max;
	}

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 按id列表获取对象列表.
	 */
	public List<T> get(final Collection<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 获取全部对象.
	 */
	public List<T> getAll() {
		return find();
	}

	/**
	 * 获取全部对象, 支持按属性行�?
	 */
	public List<T> getAll(String orderByProperty, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderByProperty));
		} else {
			c.addOrder(Order.desc(orderByProperty));
		}
		return c.list();
	}

	/**
	 * 按属性查找对象列�? 匹配方式为相�?
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/**
	 * 按属性查找唯�?���? 匹配方式为相�?
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	public T findUniqueBy(final String propertyName, final Object value,
			Order order) {
		if (StringUtils.isEmpty(propertyName)) {
			return (T) createCriteria().addOrder(order).uniqueResult();
		} else {
			Criterion criterion = Restrictions.eq(propertyName, value);
			return (T) createCriteria(criterion).addOrder(order).uniqueResult();
		}
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参�?按顺序绑�?
	 */
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑�?
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            数量可变的参�?按顺序绑�?
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑�?
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            数量可变的参�?按顺序绑�?
	 * @return 更新记录�?
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑�?
	 * @return 更新记录�?
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行SQL进行批量修改/删除操作
	 * 
	 * @param natvieSQL
	 *            SQL语句
	 * @param paramlist
	 *            参数列表
	 * @return 更新记录�?
	 */
	public int batchExecteNative(final String natvieSQL,
			final Object... paramlist) {
		Query query = getSession().createSQLQuery(natvieSQL);
		setParameters(query, paramlist);
		Object result = query.executeUpdate();
		return result == null ? 0 : ((Integer) result).intValue();
	}

	private final int BATCH_SIZE = 50;

	private int count = 0;

	public int batchExecute(T t) {
		if (count > BATCH_SIZE) {
			flushSession();
		}
		getSession().save(t);
		count++;
		return BATCH_SIZE;
	}

	public void closeBatch() {
		flushSession();
	}

	private void flushSession() {
		getSession().flush();
		getSession().clear();
		count = 0;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            数量可变的参�?按顺序绑�?
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param values
	 *            命名参数,按名称绑�?
	 */
	public Query createQuery(final String queryString,
			final Map<String, ?> values) {
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	public List<T> find(final Order order, final Criterion... criterions) {

		Criteria c = createCriteria(criterions);

		c.addOrder(order);
		return c.list();
	}

	public List<T> find(final Map<String, String> alias,
			final Criterion... criterions) {

		Criteria c = createCriteria(criterions);

		for (String alia : alias.keySet()) {
			c.createAlias(alia, alias.get(alia));
		}

		return c.list();
	}

	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */

	public T findUnique(final Order order, final Criterion... criterions) {
		return (T) createCriteria(criterions).addOrder(order).uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria. 与find()函数可进行更加灵活的操作.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 初始化对�? 使用load()方法得到的仅是对象Proxy, 在传到View层前�?��进行初始�? 如果传入entity,
	 * 则只初始化entity的直接属�?但不会初始化延迟加载的关联集合和属�?. 如需初始化关联属�?�?���?
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize
	 * (user.getDescription())，初始化User的直接属性和延迟加载的Description属�?.
	 */
	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}

	/**
	 * Flush当前Session.
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * 为Query添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, �?��进行distinct处理.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 为Criteria添加distinct transformer. 预加载关联对象的HQL会引起主对象重复, �?��进行distinct处理.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 判断对象的属性�?在数据库内是否唯�?
	 * 
	 * 在修改对象的情景�?如果属�?新修改的�?value)等于属�?原来的�?(orgValue)则不作比�?
	 */
	public boolean isPropertyUnique(final String propertyName,
			final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}

	// -- 分页查询函数 --//
	/**
	 * 分页获取全部对象.
	 */
	public Page<T> getAll(final Page<T> page) {
		return findPage(page);
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参�?按顺序绑�?
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参�?
	 */
	public Page<T> findPage(final Page<T> page, final String hql,
			final Object... values) {

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑�?
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参�?
	 */
	public Page<T> findPage(final Page<T> page, final String hql,
			final Map<String, ?> values) {

		Query q = createQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询输入参�?
	 */
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		return findPage(page, new ArrayList<Order>(), criterions);
	}

	public Page<T> findPage(final Page<T> page, final List<Order> orderList,
			final Criterion... criterions) {

		Criteria c = createCriteria(criterions);

		for (Order or : orderList) {
			c.addOrder(or);
		}

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(final Page<T> page, final Order order,
			final Criterion... criterions) {

		Criteria c = createCriteria(criterions);

		c.addOrder(order);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(final Page<T> page,
			final Map<String, String> alias, final Criterion... criterions) {
		return findPage(page, alias, null, criterions);
	}

	public Page<T> findPage(final Page<T> page,
			final Map<String, String> alias, final Order order,
			final Criterion... criterions) {

		Criteria c = createCriteria(criterions);
		if (order != null) {
			c.addOrder(order);
		}
		for (String alia : alias.keySet()) {
			c.createAlias(alia, alias.get(alia));
		}
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameterToQuery(final Query q, final Page<T> page) {

		// hibernate的firstResult的序号从0�?��
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameterToCriteria(final Criteria c,
			final Page<T> page) {

		// hibernate的firstResult的序号从0�?��
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Hql查询�?��获得的对象�?�?
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询�?��获得的对象�?�?
	 * 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String countHql = prepareCountHql(hql);

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;
		// select子句与order by子句会影响count查询,进行�?��的排�?
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}

	/**
	 * 执行count查询获得本次Criteria查询�?��获得的对象�?�?
	 */
	public long countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出�?清空三�?后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		Field f = ReflectionUtils.findField(CriteriaImpl.class, "orderEntries");
		try {
			f.setAccessible(true);
			orderEntries = (List) ReflectionUtils.getField(f, impl);
			ReflectionUtils.setField(f, impl, new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		Long totalCountObject = (Long) c.setProjection(Projections.rowCount())
				.uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回�?
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setField(f, impl, orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	@SuppressWarnings("hiding")
	public <T> T unique(Criteria criteria) {
		return (T) criteria.uniqueResult();
	}

	protected void setParameters(Query query, Object[] paramlist) {
		if (paramlist != null) {
			for (int i = 0; i < paramlist.length; i++) {
				if (paramlist[i] instanceof Date) {
					// TODO 难道这是bug 使用setParameter不行？？
					query.setTimestamp(i, (Date) paramlist[i]);
				} else {
					query.setParameter(i, paramlist[i]);
				}
			}
		}
	}

	public SQLQuery findBySql(String sql) {
		return getSession().createSQLQuery(sql);
	}
}