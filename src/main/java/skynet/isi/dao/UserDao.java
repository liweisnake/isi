package skynet.isi.dao;

import org.springframework.stereotype.Repository;

import skynet.isi.model.User;

@Repository
public class UserDao extends BaseHibernateDao<User, java.lang.Integer> {

}