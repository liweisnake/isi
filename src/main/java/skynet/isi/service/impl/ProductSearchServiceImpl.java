package skynet.isi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.dao.CategoryDao;
import skynet.isi.dao.CommentDao;
import skynet.isi.dao.ManufacturerDao;
import skynet.isi.dao.Page;
import skynet.isi.dao.ProductDao;
import skynet.isi.dao.ShoppingCartDao;
import skynet.isi.model.Category;
import skynet.isi.model.Comment;
import skynet.isi.model.Manufacturer;
import skynet.isi.model.Product;
import skynet.isi.model.User;
import skynet.isi.resultmodel.CategoryModel;
import skynet.isi.service.AccountService;
import skynet.isi.service.CommonService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ProductSearchService;

@Transactional
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private ManufacturerDao manufacturerDao;

	@Autowired
	private ShoppingCartDao shoppingCartDao;

	public String getMfrNameById(int id) {
		Manufacturer mfr = manufacturerDao.get(id);
		return mfr.getMfrName();
	}

	public Page<Product> fuzzySearch(PagingModel pagingModel, String searchStr) {

		commonService.checkPagingModel(pagingModel);

		Page<Product> pageParam = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());

		pageParam.setPageNo(pagingModel.getPageNo());
		StringTokenizer stringTokenizer = new StringTokenizer(searchStr);

		// "select product from Product product, Manufacturer mfr where product.mfr_id = mfr.id";
		List<Criterion> list = new ArrayList<Criterion>();

		while (stringTokenizer.hasMoreTokens()) {

			String searchToken = stringTokenizer.nextToken();

			list.add(Restrictions.like("iicSku", searchToken,
					MatchMode.ANYWHERE).ignoreCase());
			list.add(Restrictions.like("distySku", searchToken,
					MatchMode.ANYWHERE).ignoreCase());
			list.add(Restrictions.like("mfrSku", searchToken,
					MatchMode.ANYWHERE).ignoreCase());
			list.add(Restrictions.like("m.mfrName", searchToken,
					MatchMode.ANYWHERE).ignoreCase());
			list.add(Restrictions.like("longDesc", searchToken,
					MatchMode.ANYWHERE).ignoreCase());
		}

		Map<String, String> map = new HashMap<String, String>();
		if (!list.isEmpty()) {
			map.put("manufacturer", "m");
		}

		Page<Product> pageResult = productDao.findPage(pageParam, map,
				Order.asc("id"),
				Restrictions.or(list.toArray(new Criterion[list.size()])));

		return pageResult;
	}

	public Product getProductInfoByProductId(int productId) {
		return productDao.get(productId);
	}

	public int getProductIdByShoppingCartId(int shoppingCartId) {
		return shoppingCartDao.get(shoppingCartId).getProduct().getId();
	}

	public List<Category> getTopLevelCategory() {
		List<Category> list = categoryDao.find(Order.asc("id"),
				Restrictions.isNull("category"));
		int catIndex = -1;
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getCategoryName().equals("其他")){
				catIndex = i;
			}
		}
		if(catIndex > 0){
			Category cat = list.get(list.size() - 1);
			list.set(list.size() - 1, list.get(catIndex));
			list.set(catIndex, cat);
		}
		return list;
	}

	public List<CategoryModel> getChildCategory(int categoryId) {
		List<Category> middleCats = categoryDao.find(Order.asc("id"),
				Restrictions.eq("category.id", categoryId));
		List<CategoryModel> result = new ArrayList<CategoryModel>();
		
		for (Category middleCat : middleCats) {
			List<Category> childCats = categoryDao.find(
					Order.asc("id"),
					Restrictions.or(Restrictions.eq("category.id",
							middleCat.getId())),
					Restrictions.not(Restrictions.eq("childCount", 0)));
			if (childCats.size() != 0) {
				CategoryModel cat = new CategoryModel();
				cat.setCategoryName(middleCat.getCategoryName());
				cat.setChild(childCats);

				result.add(cat);
			}
		}
		return result;
	}

	public int getResultNum(List<CategoryModel> categories) {
		int num = 0;
		for (CategoryModel category : categories) {
			for (Category cat : category.getChild()) {
				num += cat.getChildCount();
			}
		}
		return num;
	}

	public String getCategoryName(int categoryId) {
		Category cat = categoryDao.get(categoryId);
		return cat.getCategoryName();
	}

	public boolean hasChildCategory(int categoryId) {
		Category cat = categoryDao.get(categoryId);
		return (cat.getCategories().size() > 0);
	}

	// public List<Category> getAllLevelCategory() {
	// List<Category> categories = getTopLevelCategory();
	// for (Category category : categories) {
	// category.setCategories(new HashSet<Category>(
	// getChildCategory(category.getId())));
	// }
	// return categories;
	// }

	public Page<Product> getProductByCategory(PagingModel pagingModel,
			int categoryId) {

		commonService.checkPagingModel(pagingModel);

		Page<Product> pageParam = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());
		pageParam.setPageNo(pagingModel.getPageNo());
		Page<Product> pageResult = productDao.findPage(pageParam,
				Restrictions.eq("category.id", categoryId));

		return pageResult;
	}

	// public List<Category> searchCategoryByString(String searchStr) {
	//
	// StringTokenizer stringTokenizer = new StringTokenizer(searchStr);
	//
	// List<Criterion> list = new ArrayList<Criterion>();
	//
	// while (stringTokenizer.hasMoreTokens()) {
	//
	// String searchToken = stringTokenizer.nextToken();
	//
	// list.add(Restrictions.like("categoryName", searchToken,
	// MatchMode.ANYWHERE).ignoreCase());
	// }
	//
	// List<Category> categories = categoryDao.find(Restrictions.or(list
	// .toArray(new Criterion[list.size()])));
	//
	// return categories;
	// }

	public Map<String, List<Manufacturer>> getAllManufacturers() {
		List<Manufacturer> result = manufacturerDao.getAll();
		TreeMap<String, List<Manufacturer>> map = new TreeMap<String, List<Manufacturer>>();
		for (Manufacturer m : result) {
			String str = m.getMfrName();
			if (StringUtils.isNotEmpty(str)) {
				String key = (str.charAt(0) + "").toUpperCase();
				if (map.containsKey(key)) {
					map.get(key).add(m);
				} else {
					List<Manufacturer> list = new ArrayList<Manufacturer>();
					list.add(m);
					map.put(key, list);
				}
			}
		}
		return map;
	}

	public List<CategoryModel> getCategoryByManufacturer(int manufacturerId) {

		// 获得符合条件的分类
		Criteria mfr_cri = productDao.createCriteria(Restrictions.eq(
				"manufacturer.id", manufacturerId));
		// results are pairs of category and count,
		// ArrayList<Object[]>
		// Object[0] is Long type, row count
		// Object[2] is Category
		List<?> results = mfr_cri.setProjection(
				Projections.projectionList().add(Projections.rowCount())
						.add(Projections.groupProperty("category"))).list();

		// 分类放入list
		List<Category> levelTwoCategories = new ArrayList<Category>();
		for (int i = 0; i < results.size(); i++) {
			Object[] object = (Object[]) results.get(i);
			Category category = (Category) object[1];
			category.setChildCount(((Long) object[0]).intValue());
			levelTwoCategories.add(category);
		}

		// 找出分类的父类，并放入到父类中去
		// fatherName, childCategory
		Map<String, List<Category>> categories = new HashMap<String, List<Category>>();
		for (Category levelTwoCategory : levelTwoCategories) {
			Category father = levelTwoCategory.getCategory();
			String fatherName = father.getCategoryName();
			if (!categories.containsKey(fatherName)) {
				List<Category> levelTwoList = new ArrayList<Category>();
				levelTwoList.add(levelTwoCategory);
				categories.put(fatherName, levelTwoList);
			} else {
				categories.get(fatherName).add(levelTwoCategory);
			}
		}

		List<CategoryModel> result = new ArrayList<CategoryModel>();
		Set<String> fatherNames = categories.keySet();
		for (String fatherName : fatherNames) {
			CategoryModel catModel = new CategoryModel();
			catModel.setCategoryName(fatherName);
			catModel.setChild(categories.get(fatherName));

			result.add(catModel);
		}

		return result;
	}

	public Page<Product> getProductByManufacturerAndCategory(
			PagingModel pagingModel, int manufacurerId, int categoryId) {

		commonService.checkPagingModel(pagingModel);

		Page<Product> pageParam = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());
		pageParam.setPageNo(pagingModel.getPageNo());

		Criterion mfr_cri = Restrictions.eq("manufacturer.id", manufacurerId);
		Criterion category_cri = Restrictions.eq("category.id", categoryId);

		Page<Product> pageResult = productDao.findPage(pageParam,
				Restrictions.and(mfr_cri, category_cri));

		return pageResult;
	}

	public void addComment(int productId, String commentStr) {
		String loginName = (String) SecurityUtils.getSubject().getPrincipal();
		User user = accountService.getUserByLoginName(loginName);
		Product product = productDao.get(productId);

		Comment comment = new Comment();
		comment.setUser(user);
		comment.setComment(commentStr);
		comment.setProduct(product);

		commentDao.save(comment);
	}

	public List<Comment> getComment(int productId) {
		return commentDao.find(Order.asc("id"),
				Restrictions.eq("product.id", productId));
	}
}
