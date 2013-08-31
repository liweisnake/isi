package skynet.isi.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import skynet.isi.resultmodel.PromotionInfo;

public interface UploadFileService {

	public String uploadExcel(MultipartFile file, HttpServletRequest request,
			Model model);

	public String updatePromotion(MultipartFile titleimgFile,
			MultipartFile imgFile, PromotionInfo promotionInfo,
			HttpServletRequest request, Model model);

	public String validateFile(String filePath);
}
