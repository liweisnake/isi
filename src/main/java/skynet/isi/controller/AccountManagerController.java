package skynet.isi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import skynet.isi.common.Enums.RESULT;
import skynet.isi.exception.MailFailException;
import skynet.isi.model.User;
import skynet.isi.service.AccountManager;
import skynet.isi.service.AccountService;
import skynet.isi.service.ProductSearchService;
import skynet.isi.utils.Utils;

@Controller
public class AccountManagerController {

	@Autowired
	AccountManager accountManagementService;
	
	@Autowired
	AccountService accService;
	
	@Autowired
	ProductSearchService productSearchService;
	
//	@Autowired
//	ProductSearchService productSearchService;

	@RequestMapping(value = "/frontend/getUser")
	@ResponseBody
	public User getUser(String loginName) {
		User user = accountManagementService.getUserBaseInfo(loginName);
		return user;
	}

	@RequestMapping(value = "/frontend/modifyInfo")
	public void modifyInfo(User user, HttpServletResponse response) {
		String msg = "数据库发生错误，请稍后再试！";
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		RESULT result = accountManagementService.modifyUserBaseInfo(loginName, user);
		if (result == RESULT.SUCESS) {
			msg = "修改信息成功！";
		}
		Utils.writeMsg(response, msg);
	}

	@RequestMapping(value = "/frontend/getCredit")
	@ResponseBody
	public Integer getCredit(String loginName) {
		return accountManagementService.getUserCredit(loginName);
	}
	
	@RequestMapping(value = "/frontend/changePassword")
	public String changePassword() {
		return "/info/changePassword";
	}
	
	@RequestMapping(value = "/frontend/doChangePassword")
	public void doChangePassword(String oldPassword, String newPassword, HttpServletResponse response) {
		String msg = "数据库发生错误，请稍后再试！";
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		RESULT result = accountManagementService.modifyUserPassword(loginName, oldPassword, newPassword);
		if(result == RESULT.SUCESS){
			msg = "密码修改成功";
		}else{
			msg = "密码修改失败，旧密码输入错误";
		}
		Utils.writeMsg(response, msg);
	}
	
	@RequestMapping(value = "/frontend/findPassword")
	public String findPassword() {
		return "/info/findPassword";
	}
	
	@RequestMapping(value = "/frontend/forgetPassword")
	public String forgetPassword(String email, Model model) {
		boolean result = accountManagementService.forgetPassword(email);
		model.addAttribute("isSuccess", result);
		return "/info/forgotPassword";
	}
	
	@RequestMapping(value = "/frontend/userInfo")
	public String userInfo(Model model) {
		
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		User user = accountManagementService.getUserBaseInfo(loginName);
		model.addAttribute("user", user);
		return "userInfo";
	}
	
	@RequestMapping(value="/frontend/getCreditInfo")
	public String getCreditInfo(Model model){
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		User user = accountManagementService.getUserBaseInfo(loginName);
		model.addAttribute("user", user);
		return "/info/credit";
	}
	
	@RequestMapping(value="/frontend/personalInfo")
	public String personalInfo(Model model){
		String loginName = (String)SecurityUtils.getSubject().getPrincipal();
		User user = accountManagementService.getUserBaseInfo(loginName);
		model.addAttribute("user", user);
		return "/info/personalInfo";
	}
	
	@RequestMapping(value="/frontend/testSendEmail")
	public void testSendEmail(){
		try {
			accService.testSendEmail();
		} catch (MailFailException e) {
			e.printStackTrace();
		}
	}
}
