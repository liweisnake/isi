package skynet.isi.service;

import java.util.List;

import skynet.isi.exception.MailFailException;
import skynet.isi.model.Permission;
import skynet.isi.model.Role;
import skynet.isi.model.User;
import weibo4j.model.WeiboException;

public interface AccountService {

	public User getUserByLoginName(String loginName);
	
	public List<Role> getAllRoles(String loginName);
	
	public List<Permission> getAllPermissions(int roleId);

	public String register(User user);

	public boolean existLoginName(String loginName);

	public boolean existEmail(String email);
	
	public boolean weibologin(String code) throws WeiboException;

	public boolean login(String user, String password, boolean rememberMe);
	
	public void logout();
	
	public User getUserByEmail(String email);
	
	public void testSendEmail() throws MailFailException;

	public void sendRegisterMail(User user) throws MailFailException;
}
