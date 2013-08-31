function submitUpload() {
	var uploadForm = document.getElementById("uploadForm");
	var file = uploadForm.file;
	if (checkExcelType(file) && checkFileSize(file)) {
		uploadForm.submit();
	}
}

function uploadPromotion(banner) {
	var promotionForm = document.getElementById("promotionForm" + banner);
	var titleimgfile = promotionForm.titleimgFile;
	var imgfile = promotionForm.imgFile;
	if (checkIMGType(titleimgfile) && checkFileSize(titleimgfile)
			&& checkIMGType(imgfile) && checkFileSize(imgfile)) {
		promotionForm.submit();
	}
}
