package skynet.isi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import skynet.isi.dao.Page;
import skynet.isi.resultmodel.CreditHistoryViewModel;
import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.resultmodel.UserCreditViewModel;
import skynet.isi.service.AdminService;
import skynet.isi.service.ModifyDataService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.UploadFileService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	ModifyDataService modifyDataService;

	@Autowired
	UploadFileService uploadFileService;

	@RequestMapping(value = "/admin/adminCreditManagement")
	public String showCreditManagement(Model model, String loginName) {
		UserCreditViewModel ucvm = adminService
				.getUserCreditViewModel(loginName);
		model.addAttribute("loginName", loginName);
		model.addAttribute("userCreditInfo", ucvm);
		return "admin/adminCreditManagement";
	}

	@RequestMapping(value = "/admin/adminCreditUpdate")
	public String updateCredit(Model model, String loginName,
			int totalLeftCredit) {
		adminService.updateCredit(loginName, totalLeftCredit);
		UserCreditViewModel ucvm = adminService
				.getUserCreditViewModel(loginName);
		model.addAttribute("loginName", loginName);
		model.addAttribute("userCreditInfo", ucvm);
		return "admin/adminCreditManagement";
	}

	@RequestMapping(value = "/admin/adminCreditHistory")
	public String showCreditHistory(Model model, PagingModel pagingModel,
			String loginName) {
		Page<CreditHistoryViewModel> creditHistorys = adminService
				.getCreditHistoryViewModel(pagingModel, loginName);
		model.addAttribute("loginName", loginName);
		model.addAttribute("creditHistorys", creditHistorys);
		return "admin/adminCreditHistory";
	}

	@RequestMapping(value = "/admin/adminPromotion")
	public String adminPromotion(Model model) {
		List<PromotionInfo> promotionInfos = adminService.getPromotionList();
		model.addAttribute("promotions", promotionInfos);
		return "admin/adminPromotion";
	}

	@RequestMapping(value = "/admin/updatePromotion")
	public String updatePromotion(MultipartFile titleimgFile,
			MultipartFile imgFile, PromotionInfo promotionInfo,
			HttpServletRequest request, Model model) {
		model.addAttribute("result", uploadFileService.updatePromotion(
				titleimgFile, imgFile, promotionInfo, request, model));
		model.addAttribute("promotions", adminService.getPromotionList());
		model.addAttribute("banner", promotionInfo.getBanner());
		return "admin/adminPromotion";
	}
	
	@RequestMapping(value = "/admin/showAdminCreditHistory")
	public String showAdminCreditHistory() {
		Subject sub = SecurityUtils.getSubject();
		if (!sub.hasRole("admin")) {
			throw new RuntimeException();
		}

		return "admin/adminCreditHistory";
	}

	@RequestMapping(value = "/admin/showAdminCreditManagement")
	public String showAdminCreditManagement() {
		return "admin/adminCreditManagement";
	}

	@RequestMapping(value = "/admin/showAdminLogin")
	public String showAdminLogin() {
		return "admin/adminLogin";
	}

//	@RequiresRoles("admin")
//	@RequestMapping(value = "/admin/showAdminBatch")
//	public String showAdminBatch() {
//		return "admin/adminBatch";
//	}

	@RequestMapping(value = "/admin/showAdminProductInfo")
	public String showAdminProductInfo() {
		return "admin/adminProductInfo";
	}
	
	
}
