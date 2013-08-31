<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分历史</title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css" />
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
			<td><img src="${ctx}/image/admin/line01.jpg" width="8" height="31" /></td>
		</tr>
	</table>
	<table width="1050" height="324" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr><td height="324" valign="top" style="background-image: url(${ctx}/image/admin/bg-body.gif); background-repeat: repeat-y;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
			<td>
				<jsp:include page="adminLeftMenu.jsp" flush="true"/>
			</td>
			<td width="1%" rowspan="10" valign="top" bgcolor="#F5F5EF">&nbsp;</td>
			<td width="77%" rowspan="10" valign="top" bgcolor="#F5F5EF">
				<table width="100%" border="0" cellpadding="6" cellspacing="0">
					<tr>
						<td bgcolor="#F5F5EF"><h1>积分管理-使用历史</h1></td>
					</tr>
					<tr>
						<td width="78%" bgcolor="#F5F5EF">
						<form action="/admin/adminCreditHistory">
							<input id="loginName" name="loginName" type="text" value="${loginName }" size="60" /> 
							<input type="submit" name="Submit4" value="搜索" /></td>
						</form>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF"><table width="100%" border="0"
								cellpadding="0" cellspacing="1" bgcolor="#CCCCCC"
								style="background-image: url(${ctx}/image/admin/box1.jpg); background-position: center top; background-repeat: no-repeat">
								<tr align="center" valign="bottom" style="color: #FF8000">
									<td width="7%" height="62"><p>序列</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>用户名</p></td>
									<td width="23%" style="BORDER-left: #FF6600 1px solid;"><p>Email</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>最近使用日期</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>使用积分</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>剩余积分</p></td>
								</tr>
								<c:if test="${creditHistorys.result != null}">
									<c:forEach items="${creditHistorys.result}" var="item" varStatus="rowCounter">
										<tr align="center" height="60">
											<td bgcolor="#FFFFFF"><p>${rowCounter.count}.</p></td>
											<td bgcolor="#FFEEDD"><p>${item.loginName}</p></td>
											<td bgcolor="#FFFFFF"><p>${item.email}</p></td>
											<td bgcolor="#FFEEDD"><p>${item.useDate}</p></td>
											<td bgcolor="#FFFFFF"><p>${item.usedCredit}</p></td>
											<td bgcolor="#FFEEDD"><p>${item.leftCredit}</p></td>
										</tr>
									</c:forEach>
								</c:if>
							</table></td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
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
