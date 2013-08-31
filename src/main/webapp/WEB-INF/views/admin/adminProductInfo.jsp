<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品管理</title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css" />
<script type="text/javascript" src="${ctx}/script/jquery.js"></script>
<script>
	$(document).ready(function() {
		$("#updateLink").click(function() {
			$("#updateForm").submit();
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
			<td><img src="${ctx}/image/admin/line01.jpg" width="8" height="31" /></td>
		</tr>
	</table>
	<table width="1050" height="497" border="0" align="center" cellpadding="0" cellspacing="0"
		style="background-image: url(${ctx}/image/admin/admin-bg.jpg); background-position: bottom right; background-repeat: no-repeat;">
		<tr><td valign="top" style="background-image: url(${ctx}/image/admin/bg-body.gif); background-repeat: repeat-y;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>
			<td>
				<jsp:include page="adminLeftMenu.jsp" flush="true"/>
			</td>
			<td width="1%" rowspan="10" valign="top" bgcolor="#F5F5EF">&nbsp;</td>
			<td width="77%" rowspan="10" valign="top" bgcolor="#F5F5EF">
				<table width="100%" border="0" cellpadding="6" cellspacing="0">
					<tr>
						<td bgcolor="#F5F5EF"><h1>产品管理-产品信息管理</h1></td>
					</tr>
					<tr>
						<td width="78%" bgcolor="#F5F5EF">
							<form action="search">
								<input name="iicSku" type="text" value="输入iic库存号" size="60" /> 
								<input type="submit" name="Submit4" value="搜索" />
							</form>
						</td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>

					<tr>
						<td bgcolor="#F5F5EF">
						<form id="updateForm" action="update" method="post">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" 
								style="background-image: url(${ctx}/image/admin/box1.jpg); background-position: center top; TABLE-LAYOUT: fixed;">
								<tr align="center" valign="bottom" style="color: #FF8000">
									<td width="7%" height="62" bgcolor="">iIC <br /> 库存号</td>
									<td width="7%" style="BORDER-left: #FF6600 1px solid;">E14<br />库存号</td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;">制造商<br />产品编号&#13;</td>
									<td width="9%" style="BORDER-left: #FF6600 1px solid;">制造商<br />名称</td>
									<td width="4%" style="BORDER-left: #FF6600 1px solid;">描述</td>
									<td width="8%" style="BORDER-left: #FF6600 1px solid;">详细描述</td>
									<td width="5%" style="BORDER-left: #FF6600 1px solid;">UOM</td>
									<td width="5%" style="BORDER-left: #FF6600 1px solid;">包装</td>
									<td width="4%" style="BORDER-left: #FF6600 1px solid;">Rohs<br />状态</td>
									<td width="7%" style="BORDER-left: #FF6600 1px solid;">产品<br />数据库</td>
									<td width="9%" style="BORDER-left: #FF6600 1px solid;">图片<br />数据库</td>
									<td width="8%" style="BORDER-left: #FF6600 1px solid;">原产地</td>
									<td width="3%" style="BORDER-left: #FF6600 1px solid;">库存</td>
									<td width="7%" style="BORDER-left: #FF6600 1px solid;">第一档<br />数量</td>
									<td width="7%" style="BORDER-left: #FF6600 1px solid;">第一档<br />价格</td>
								</tr>
								<tr>
									<td height="189" bgcolor="#FFEEDD"><textarea name="iccSku" rows="10" cols="4">${productInfo.iccSku }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="distySku" rows="10" cols="4">${productInfo.distySku }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="mfrSku" rows="10" cols="7">${productInfo.mfrSku }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="manufacturer" rows="10" cols="6">${productInfo.manufacturer }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="desc" rows="10" cols="1">${productInfo.desc }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="longDesc" rows="10" cols="6">${productInfo.longDesc }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="uom" rows="10" cols="2">${productInfo.uom }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="packSize" rows="10" cols="2">${productInfo.packSize }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="rohsStatus" rows="10" cols="1">${productInfo.rohsStatus }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="dataSheet" rows="10" cols="4">${productInfo.dataSheet }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="imageURL" rows="10" cols="6">${productInfo.imageURL }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="countryOrigin" rows="10" cols="5">${productInfo.countryOrigin }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="store" rows="10" cols="1">${productInfo.store }</textarea></td>
									<td bgcolor="#FFFFFF"><textarea name="oneAmount" rows="10" cols="4">${productInfo.oneAmount }</textarea></td>
									<td height="189" bgcolor="#FFEEDD"><textarea name="onePrice" rows="10" cols="4">${productInfo.onePrice }</textarea></td>
								</tr>
							</table></form></td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
					<c:if test="${updateResult != null}">
						<tr align="center" height="60">
							<td>${updateResult}</td>
						</tr>
					</c:if>
					<tr>
						<td align="right" bgcolor="#F5F5EF">
							<table border="0" cellspacing="0" cellpadding="0" class="confirmation">
								<tr>
									<td align="center"><a id="updateLink">更新至网站</a></td>
								</tr>
							</table></td>
					</tr>
					
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
			</table></td></tr></table></td>
		</tr>
	</table>
	<jsp:include page="../buttom.jsp" flush="true" />
</body>
</html>
