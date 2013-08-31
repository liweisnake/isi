package skynet.isi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import skynet.isi.resultmodel.ProductInfo;
import skynet.isi.service.ModifyDataService;
import skynet.isi.service.UploadFileService;

@Controller
public class ModifyDataController {

	@Autowired
	ModifyDataService modifyDataService;

	@Autowired
	UploadFileService uploadFileService;

	@RequestMapping(value = "/admin/search")
	public String search(String iicSku, Model model) {
		model.addAttribute("productInfo", modifyDataService.getInfo(iicSku));
		return "/admin/adminProductInfo";
	}

	@RequestMapping(value = "/admin/update")
	public String update(ProductInfo info, Model model) {
		modifyDataService.update(info);
		model.addAttribute("productInfo",
				modifyDataService.getInfo(info.getIccSku()));
		model.addAttribute("updateResult", "更新成功！");
		return "/admin/adminProductInfo";
	}

//	@RequestMapping(value = "/admin/upload")
//	public String uploadExcel(MultipartFile file, HttpServletRequest request,
//			Model model) {
//		String msg = uploadFileService.uploadExcel(file, request, model);
//		model.addAttribute("result", msg);
//		return "/admin/adminBatch";
//	}
}
