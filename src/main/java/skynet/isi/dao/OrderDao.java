package skynet.isi.dao;

import org.springframework.stereotype.Repository;

import skynet.isi.model.Order;

@Repository
public class OrderDao extends BaseHibernateDao<Order, java.lang.Integer> {

}