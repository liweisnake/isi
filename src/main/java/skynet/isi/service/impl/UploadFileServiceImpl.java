package skynet.isi.service.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.service.AdminService;
import skynet.isi.service.ModifyDataService;
import skynet.isi.service.UploadFileService;

@Transactional
@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	ModifyDataService modifyDataService;

	@Autowired
	AdminService adminService;

	public String uploadFile(MultipartFile file, HttpServletRequest request,
			String desPath, boolean coverExists) throws Exception {
		String path = request.getSession().getServletContext()
				.getRealPath(desPath);
		String fileName = file.getOriginalFilename();

		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		} else {
			// 覆盖同名文件
			if (coverExists)
				targetFile.delete();
			else {
				return "文件名重复，请重新命名需要上传的文件！";
			}
		}

		// 保存
		file.transferTo(targetFile);
		// 返回文件的服务器上的绝对路径

		return path + "/" + fileName;
	}

	private boolean uploadSuccess(String result) {
		return (result.indexOf("/") > 0);
	}

	public String validateFile(String filePath) {
		// TODO::
		System.out.println(filePath);
		return "";
	}

	public String uploadExcel(MultipartFile file, HttpServletRequest request,
			Model model) {
		String msg = validateFile(file.getOriginalFilename());
		String uploadResult = "";
		if (!StringUtils.isEmpty(msg)) {
			return msg;
		}
		msg = "文件上传、导入失败！";
		try {
			uploadResult = uploadFile(file, request, "upload/excel", true);
			if (uploadSuccess(uploadResult)) {
				List<String> failed = modifyDataService.process(uploadResult,
						true);
				if (failed.size() == 0)
					msg = "文件上传、导入成功！";
				else {
					msg = "部分数据导入失败：<br/>";
					for (String message : failed)
						msg += "<br/>" + message;
				}
			} else {
				msg = uploadResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public String updatePromotion(MultipartFile titleimgFile,
			MultipartFile imgFile, PromotionInfo promotionInfo,
			HttpServletRequest request, Model model) {
		String msg = validateFile(titleimgFile.getOriginalFilename());
		String uploadTitileIMGResult = "";
		String uploadIMGResult = "";
		if (!StringUtils.isEmpty(msg)) {
			return msg;
		}
		msg = validateFile(imgFile.getOriginalFilename());
		if (!StringUtils.isEmpty(msg)) {
			return msg;
		}
		msg = "更新失败！";
		try {
			uploadTitileIMGResult = uploadFile(titleimgFile, request,
					"upload/img", false);
			if (uploadSuccess(uploadTitileIMGResult)) {
				uploadIMGResult = uploadFile(imgFile, request, "upload/img",
						false);
				if (uploadSuccess(uploadIMGResult)) {
					promotionInfo.setTitlePicUrl(titleimgFile.getOriginalFilename());
					promotionInfo.setPicUrl(imgFile.getOriginalFilename());
					adminService.updatePromotion(promotionInfo);
					msg = "更新成功！";
				} else {
					(new File(uploadTitileIMGResult)).delete();
					msg = "标题图片" + uploadIMGResult;
				}
			} else {
				msg = "图片" + uploadTitileIMGResult;
			}
		} catch (Exception e) {
			(new File(uploadTitileIMGResult)).delete();
			(new File(uploadIMGResult)).delete();
			e.printStackTrace();
		}
		return msg;
	}

}
