var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function checkFileSize(file) {
	var limit = 5242880;
	var fileSize = 0;

	if (isIE && !file.files) {
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		var newfile = fileSystem.GetFile(getFilePathInIE(file));
		fileSize = newfile.Size;
	} else {
		fileSize = file.files[0].size;
	}
	if (fileSize > limit) {
		alert("文件大小必须在4M以内！请重新选择文件！");
		return false;
	}
	return true;
}

function getFilePathInIE(file) {
	file.select();
	return document.selection.createRange().text;
}

// function checkFileExists(file) {
// if (isIE && !file.files) {
// var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
// return fileSystem.FileExists(getFilePathInIE(file));
// }
// return true;
// }

function checkExcelType(file) {
	var filename = file.value;

	if ((filename == null) || !(/(.*)+\.(xls|XLS)$/.test(filename))) {
		alert("文件必须为.xls或.XLS类型！");
		return false;
	} else {
		return true;
	}
}

function checkIMGType(file) {
	var filename = file.value;

	if ((filename == null)
			|| !(/(.*)+\.(jpg|bmp|gif|png|JPG|BMP|GIF|PNG)$/.test(filename))) {
		alert("文件必须为.jpg;.JPG;.bmp;.BMP;.gif;.GIF;.png;.PNG类型！");
		return false;
	} else {
		return true;
	}
}

function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}