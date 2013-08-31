package skynet.isi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.common.Enums.EMAIL_TEMPLATE;
import skynet.isi.common.Enums.RESULT;
import skynet.isi.common.SystemConfigConstant;
import skynet.isi.dao.Page;
import skynet.isi.dao.UserDao;
import skynet.isi.exception.MailFailException;
import skynet.isi.model.User;
import skynet.isi.service.AccountManager;
import skynet.isi.service.AccountService;
import skynet.isi.service.CommonService;
import skynet.isi.service.EmailTemplateService;
import skynet.isi.service.ModifyPasswordInfo;
import skynet.isi.service.PagingModel;

@Transactional
@Service
public class AccountManagerImpl implements AccountManager {

	protected static final Logger log = LogManager
			.getLogger(AccountManagerImpl.class.getName());

	@Autowired
	private UserDao userDao;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private EmailTemplateService emailService;

	public User getUserBaseInfo(String loginName) {
		User user = accountService.getUserByLoginName(loginName);
		return user;
	}

	public RESULT modifyUserBaseInfo(String loginName, User user) {
		User dbUser = accountService.getUserByLoginName(loginName);
		String password = dbUser.getPassword();
		int id = dbUser.getId();
		BeanUtils.copyProperties(user, dbUser);
		dbUser.setLoginName(loginName);
		dbUser.setPassword(password);
		dbUser.setId(id);
		userDao.save(user);
		log.info("User " + user.getLoginName() + " changed base information.");
		return RESULT.SUCESS;
	}

	public RESULT modifyUserPassword(String loginName, String oldPassword, String newPassword) {
		User user = accountService.getUserByLoginName(loginName);
		String encryptOldPwd = new Md5Hash(oldPassword).toHex();
		if (encryptOldPwd.equals(user.getPassword())) {
			user.setPassword(new Md5Hash(newPassword).toHex());
			userDao.save(user);
			log.info("User " + user.getLoginName() + " changed password.");
			return RESULT.SUCESS;
		} else {
			return RESULT.PASSWORD_ERROR;
		}
	}

	public Integer getUserCredit(String loginName) {
		User user = accountService.getUserByLoginName(loginName);
		return user.getCredit();
	}

	public Page<User> findUser(PagingModel pagingModel, String userName) {
		Criterion cri = Restrictions.like("loginName", userName);
		Page<User> pageParam = Page.getInstance(pagingModel.getStart(),
				pagingModel.getLimit());
		return userDao.findPage(pageParam, cri);
	}

	public boolean forgetPassword(String email) {
		if(StringUtils.isEmpty(email)){
			return false;
		}
		String defaultPassword = commonService.getSystemConfigStringProperty(SystemConfigConstant.DEFAULT_PASSWORD, "iic123");
		User user = accountService.getUserByEmail(email);
		if(user == null){
			return false;
		}
		List<String> emailList = new ArrayList<String>();
		emailList.add(email);
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginName", user.getLoginName());
		params.put("password", defaultPassword);
		try {
			emailService.send(EMAIL_TEMPLATE.FIND_PASSWORD, emailList, params);
		} catch (MailFailException e) {
			return false;
		}
		user.setPassword(new Md5Hash(defaultPassword).toHex());
		userDao.save(user);
		return true;
	}
	
	
}
