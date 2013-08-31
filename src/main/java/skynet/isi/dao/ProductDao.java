package skynet.isi.dao;

import org.springframework.stereotype.Repository;

import skynet.isi.model.Product;

@Repository
public class ProductDao extends BaseHibernateDao<Product, java.lang.Integer> {

}