package skynet.isi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import skynet.isi.service.CommonService;
import skynet.isi.service.ProductSearchService;

@Controller
public class JSPController {

	@Autowired
	ProductSearchService productSearchService;

	@Autowired
	CommonService commonService;

	@RequestMapping(value = "/frontend/showIndex")
	public String showIndex(Model model, HttpServletRequest request) {
		request.getSession().setAttribute("topCategory",
				productSearchService.getTopLevelCategory());
		model.addAttribute("promotionList", commonService.getPromotionList());
		return "index";
	}

	@RequestMapping(value = "/frontend/showRegister")
	public String showRegister() {
		return "register";
	}

	@RequestMapping(value = "/frontend/500")
	public String errorPage500(Model model) {
		return "commons/500";
	}

	@RequestMapping(value = "/frontend/404")
	public String errorPage404(Model model) {
		return "commons/404";
	}

}
