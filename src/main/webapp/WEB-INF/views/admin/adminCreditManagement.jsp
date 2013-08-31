<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分管理</title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css" />
<script type="text/javascript" src="${ctx}/script/jquery.js"></script>
<script>
	function showCreditHistory(){
		var loginName = document.getElementById("loginName").value;
		window.location.href = "/admin/adminCreditHistory?loginName=" + loginName;
	}
	
	function updateCredit(){
		var loginName = document.getElementById("loginName").value;
		var totalLeftCredit = document.getElementById("totalLeftCredit").value;
		window.location.href = "/admin/adminCreditUpdate?loginName=" + loginName + "&totalLeftCredit=" + totalLeftCredit;
	}
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
			<td><img src="${ctx}/image/admin/line01.jpg" width="8"
				height="31" /></td>
		</tr>
	</table>

	<table width="1050" height="324" border="0" align="center"
		cellpadding="0" cellspacing="0"
		style="background-image: url(${ctx}/image/admin/admin-bg.jpg); background-position: bottom right; background-repeat: no-repeat;">
		<tr><td height="324" valign="top" style="background-image: url(${ctx}/image/admin/bg-body.gif); background-repeat: repeat-y;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
			<td>
				<jsp:include page="adminLeftMenu.jsp" flush="true"/>
			</td>
			<td width="1%" rowspan="10" valign="top" bgcolor="#F5F5EF">&nbsp;</td>
			<td width="77%" rowspan="10" valign="top" bgcolor="#F5F5EF">
				<table width="100%" border="0" cellpadding="6" cellspacing="0">
					<tr>
						<td bgcolor="#F5F5EF"><h1>积分管理-积分更新</h1></td>
					</tr>
					<tr>
						<td width="78%" bgcolor="#F5F5EF">
							<form action="/admin/adminCreditManagement">
								<input id="loginName" name="loginName" type="text" value="${loginName }" size="60" /> 
								<input type="submit" value="搜索" />
							</form>
						</td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC"
								style="background-image: url(${ctx}/image/admin/box1.jpg); background-position: center top; background-repeat: no-repeat">
								<tr align="center" valign="bottom" style="color: #FF8000">
									<td width="7%" height="62" bgcolor=""><p>选择</p></td>
									<td width="7%" style="BORDER-left: #FF6600 1px solid;"><p>序列</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>用户名</p></td>
									<td width="23%" style="BORDER-left: #FF6600 1px solid;"><p>Email</p></td>
									<td width="11%" style="BORDER-left: #FF6600 1px solid;"><p>当前积分</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>使用积分</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>剩余积分</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>最近使用日期</p></td>
									<td width="12%" style="BORDER-left: #FF6600 1px solid;"><p>操作</p></td>
								</tr>
								<c:if test="${userCreditInfo.loginName != null}">
									<tr align="center" height="60">
										<td bgcolor="#FFEEDD">
										<input type="checkbox" name="checkbox" value="checkbox" /></td>
										<td bgcolor="#FFFFFF"><p>1</p></td>
										<td bgcolor="#FFEEDD"><p>${userCreditInfo.loginName}</p></td>
										<td bgcolor="#FFFFFF"><p>${userCreditInfo.email}</p></td>
										<td bgcolor="#FFEEDD"><p>${userCreditInfo.lastLeftCredit}</p></td>
										<td bgcolor="#FFFFFF"><p>${userCreditInfo.totalUsedCredit}</p></td>
										<td bgcolor="#FFEEDD"><input id="totalLeftCredit" value="${userCreditInfo.totalLeftCredit}"/></td>
										<td bgcolor="#FFFFFF"><p>${userCreditInfo.lastUseDate}</p></td>
										<td bgcolor="#FFEEDD"><input type="submit" onclick="showCreditHistory()" value="查看历史" />
										</td>
									</tr>
								</c:if>
							</table></td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" bgcolor="#F5F5EF"><table border="0"
								cellspacing="0" cellpadding="0" class="confirmation">
								<tr>
									<td align="center"><a onclick="updateCredit()">更新至网站</a></td>
								</tr>
							</table></td>
					</tr>

					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table></td></tr></table>

	<jsp:include page="../buttom.jsp" flush="true" />
</body>
</html>
