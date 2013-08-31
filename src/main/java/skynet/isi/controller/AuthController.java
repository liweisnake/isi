package skynet.isi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import skynet.isi.model.User;
import skynet.isi.service.AccountService;
import skynet.isi.service.ProductSearchService;
import skynet.isi.service.impl.AccountServiceImpl;
import skynet.isi.utils.Utils;
import weibo4j.model.WeiboException;

@Controller
public class AuthController {
	
	protected static final Logger log = LogManager
			.getLogger(AuthController.class.getName());

	@Autowired
	AccountService accService;

	@Autowired
	ProductSearchService productSearchService;

	@RequestMapping(value = "/frontend/showLogin")
	public String showLogin() {
		return "/login";
	}

	@RequestMapping(value = "/frontend/register", method = RequestMethod.POST)
	public void register(User user, HttpServletResponse response) {
		String msg = "";
		msg = accService.register(user);
		try {
			if (msg.contains("成功")) {
				accService.sendRegisterMail(user);
			}
		} catch (Exception e) {
			log.info("邮件发送失败:" + user.getEmail());
		}

		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/weibologin")
	public String weblogin(String code) {
		try {
			boolean b = accService.weibologin(code);
		} catch (WeiboException e) {
			throw new RuntimeException(e);
		}
		return "/index";
	}

	@RequestMapping(value = "/frontend/login")
	public String login(String userName, String password, boolean rememberMe,
			Model model, HttpServletRequest request) {
		boolean b = accService.login(userName, password, rememberMe);
		if (b) {
			return "/index";
		}
		return "/login";
	}

	@RequestMapping(value = "/frontend/logout")
	public String logout(String userName, String password, boolean rememberMe,
			Model model, HttpServletRequest request) {
		accService.logout();
		return "/index";
	}

	@RequestMapping(value = "/admin/adminLogin")
	public String adminLogin(String loginName, String password) {
		boolean b = accService.login(loginName, password, false);
		if (b)
			return "/admin/adminProductInfo";
		return "/admin/adminLogin";
	}

	// @RequestMapping(value = "/showIndex")
	// public String showIndex() {
	// return "index";
	// }
}
