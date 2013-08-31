<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8" />
<title>管理员登录</title>
<style type="text/css">
<!--
body {
	background-color: #EF9735;
	font-size: 12px;
}

h2 {
	font-family: "Times New Roman", Times, serif;
	color: #666;
}

.register {
	background-image: url(${ctx}/image/menu/login-bt4.jpg);
	background-repeat: no-repeat;
	background-position: center left;
	padding-left: 6px;
}

.register:hover {
	background-image: url(${ctx}/image/menu/login-bt4-over.jpg);
	background-repeat: no-repeat;
	background-position: center left;
	padding-left: 6px;
}
-->
</style>
<script src="${ctx}/script/jquery.js"></script>
<script>
$(document).ready(function () {
	function login(){
		$.post("/admin/adminLogin", $("#loginForm").serialize(), function(data) {
			window.location.href = "/admin/showAdminProductInfo";
		});
	}
	
	$("#loginLink").click(function(){
		login();
	});
});
</script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><img src="${ctx}/image/admin/admin-logo.jpg"
				width="181" height="70" /></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="background-image: url(${ctx}/image/admin/line01.jpg); background-repeat: repeat-x; background-position: center">
		<tr>
			<td><img src="image/admin/line01.jpg" width="8" height="31" /></td>
		</tr>
	</table>
	<form id="loginForm" action="/admin/adminLogin">
	<table width="1024" height="497" border="0" align="center"
		cellpadding="0" cellspacing="0"
		style="background-image: url(${ctx}/image/admin/admin-bg.jpg); background-position: bottom right; background-repeat: no-repeat;">
		<tr>
			<td>&nbsp;</td>
			<td width="623" valign="top" style="background-image: url(${ctx}/image/admin/admin-box-bg.jpg); background-position: center top; background-repeat: no-repeat">
				<table width="100%" border="0" cellpadding="3" cellspacing="0">
					<tr>
						<td width="43%" rowspan="2" align="center" valign="bottom"><h2>信息化处理平台</h2></td>
						<td width="27%" height="40" valign="top">&nbsp;</td>
						<td width="30%" height="40" valign="top">&nbsp;</td>
					</tr>
					<tr>
						<td><input name="loginName" type="text" value="" /></td>
						<td rowspan="2" class="register"><a id="loginLink" href="#"
							style="color: #E7E7D8; text-decoration:none;">登 录</a></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input name="password" type="password" value="" /></td>
					</tr>
				</table></td>
			<td width="214">&nbsp;</td>
		</tr>
	</table>
	</form>
</body>
</html>
