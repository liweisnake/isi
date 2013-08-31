package skynet.isi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.common.Enums.EMAIL_TEMPLATE;
import skynet.isi.dao.RoleDao;
import skynet.isi.dao.UserDao;
import skynet.isi.exception.MailFailException;
import skynet.isi.model.Permission;
import skynet.isi.model.Role;
import skynet.isi.model.RolePermission;
import skynet.isi.model.User;
import skynet.isi.model.UserRole;
import skynet.isi.service.AccountService;
import skynet.isi.service.CommonService;
import skynet.isi.service.EmailTemplateService;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	protected static final Logger log = LogManager
			.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private EmailTemplateService service;

	public String register(User user) {
		if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getPassword())
				|| StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getMobile())) {
		}
		// check login name and email not duplicate
		if (existLoginName(user.getLoginName())) {// not
																	// success
			return "已经存在的用户名";
		}
		if(existEmail(user.getEmail())){
			return "已经存在的email";
		}
		String password = user.getPassword();
		user.setPassword(new Md5Hash(user.getPassword()).toHex());
		userDao.save(user);
		
		login(user.getLoginName(), password, false);
		return "注册成功";
	}
	
	public void sendRegisterMail(User user) throws MailFailException{
		Map<String, String> params = new HashMap<String, String>();
		List<String> emailList = new ArrayList<String>();
		emailList.add(user.getEmail());
		params.put("loginName", user.getLoginName());
		params.put("url", "http://www.iic.net.cn");
		service.send(EMAIL_TEMPLATE.REGISTER, emailList, params);
	}

	public boolean login(String user, String password, boolean rememberMe) {
		if(StringUtils.isEmpty(user) || StringUtils.isEmpty(password)){
			return false;
		}
		Subject currentUser = SecurityUtils.getSubject();
//		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token;
			String pwd = new Md5Hash(password).toHex();
			token = new UsernamePasswordToken(user,
					pwd, rememberMe);
			try {
				currentUser.login(token);
				return true;
			} catch (AuthenticationException ae) {
//				ae.printStackTrace();
//				System.out.println("login fail. pwd is :" + password + " enpwd is:" + pwd);
				return false;
			}
//		}
//		return true;
	}
	
	@Override
	public boolean weibologin(String code) throws WeiboException {
		Oauth oauth = new Oauth();
		AccessToken acc = oauth.getAccessTokenByCode(code);
		Users um = new Users();
        um.client.setToken(acc.getAccessToken());
        weibo4j.model.User u = um.showUserById(acc.getUid());
		UsernamePasswordToken token = new UsernamePasswordToken(u.getName(), "ced1237ea65864db3f5f623ec5e619f7", false);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			User user = getUserByLoginName(u.getName());
			if(user == null){//register
				user = new User();
				user.setLoginName(u.getName());
				userDao.save(user);
			}
			currentUser.login(token);
			return true;
		} catch (AuthenticationException ae) {
			return false;
		}
	}
	
	public void logout(){
		SecurityUtils.getSubject().logout();
	}
	
	public List<Role> getAllRoles(String loginName){
		User user = getUserByLoginName(loginName);
		Set<UserRole> urs = user.getUserRoles();
		List<Role> roles = new ArrayList<Role>();
		for(UserRole ur : urs){
			roles.add(ur.getRole());
		}
		return roles;
	}
	
	public List<Permission> getAllPermissions(int roleId){
		Role r = roleDao.get(roleId);
		List<Permission> list = new ArrayList<Permission>();
		Set<RolePermission> rps = r.getRolePermissions();
		for(RolePermission rp : rps){
			list.add(rp.getPermission());
		}
		return list;
	}
	
	public boolean activeAccount(String loginName) {
		if (StringUtils.isEmpty(loginName)) {
			return false;
		}
		User user = getUserByLoginName(loginName);
		if (user != null) {
			// set isActive = true
			userDao.save(user);
			return true;
		}
		return false;
	}

	public User getUserByLoginName(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
//		List<User> users = userDao.getAll();
//		for(User u : users){
//			if(u.getLoginName().equals(loginName)){
//				return u;
//			}
//		}
//		return null;
//		List<User> list = userDao.findBy("loginName", loginName);
//		if(list.size() > 0){
//			return list.get(0);
//		} 
//		return null;
	}
	
	public User getUserByEmail(String email) {
		return userDao.findBy("email", email).get(0);
	}

	public boolean existLoginName(String loginName) {
		return userDao.findBy("loginName", loginName).size() > 0;
	}

	public boolean existEmail(String email) {
		return userDao.findBy("email", email).size() > 0;
	}

	@Override
	public void testSendEmail() throws MailFailException {
		Map<String, String> params = new HashMap<String, String>();
		List<String> emailList = new ArrayList<String>();
		emailList.add("liwei_snake@yahoo.com.cn");
		params.put("loginName", "levi");
		params.put("url", "www.iic.net.cn");
		service.send(EMAIL_TEMPLATE.REGISTER, emailList, params);
	}

	

}
