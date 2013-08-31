<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>限时抢购</title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css" />
<link type="text/css" rel="Stylesheet" href="${ctx}/css/StyleSheet.css"/> 

<script type="text/javascript" src="${ctx}/script/check.js"></script>
<script type="text/javascript" src="${ctx}/script/submit.js"></script>

<script type="text/javascript" src="${ctx}/Tools/js/script.js"></script>
<script type="text/javascript" src="${ctx}/Tools/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/Tools/js/JScript.js" ></script> 

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
	<table width="1050" height="324" border="0" align="center" cellpadding="0" cellspacing="0"
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
						<td bgcolor="#F5F5EF"><h1>限时抢购管理</h1></td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC"
								style="background-image: url(${ctx}/image/admin/box1.jpg); background-position: center top; background-repeat: no-repeat;TABLE-LAYOUT: fixed;">
								<tr align="center" valign="bottom" style="color: #FF8000">
									<td width="7%">Banner<br/>位置</td>
									<td width="15%" style="BORDER-left: #FF6600 1px solid;"><p>标题图片</p></td>
									<td width="15%" style="BORDER-left: #FF6600 1px solid;"><p>图片</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>描述</p></td>
									<td width="21%" style="BORDER-left: #FF6600 1px solid;"><p>链接</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>开始时间</p></td>
									<td width="10%" style="BORDER-left: #FF6600 1px solid;"><p>结束时间</p></td>
									<td width="7%" height="62" style="BORDER-left: #FF6600 1px solid;"><p>更新</p></td>
								</tr>
								<c:forEach items="${promotions}" var="item">
									<tr><td><form id="promotionForm${item.banner }" action="updatePromotion" method="post" enctype="multipart/form-data">
										<tr align="center" height="60">
											<td bgcolor="#FFEEDD"><p><textarea name="banner" rows="3" cols="1" readonly>${item.banner }</textarea></p></td>
											<td bgcolor="#FFFFFF"><p>
												<c:if test="${item.titlePicUrl != null }">
													<img src="${ctx}/upload/img/${item.titlePicUrl }" style="width:100%"/><br/>
													${item.titlePicUrl }<br/>
												</c:if>
												<input type="file" name="titleimgFile" style="width:100px"/></p></td>
											<td bgcolor="#FFEEDD"><p>
												<c:if test="${item.picUrl != null }">
													<img src="${ctx}/upload/img/${item.picUrl }" style="width:100%"/><br/>
													${item.picUrl }<br/>
												</c:if>
												<input type="file" name="imgFile" style="width:100px"/></p></td>
											<td bgcolor="#FFFFFF"><p><textarea name="description" rows="3" cols="9">${item.description }</textarea></p></td>
											<td bgcolor="#FFEEDD"><p><textarea name="link" rows="3" cols="20">${item.link }</textarea></p></td>
											<td bgcolor="#FFFFFF"><p>
												<input type="text" name="startTime" value="${item.startTime }" style="width: 80px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></p></td>
											<td bgcolor="#FFEEDD"><p>
												<input type="text" name="endTime" value="${item.endTime }" style="width: 80px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></p></td>
											<td bgcolor="#FFFFFF"><p>
												<c:if test="${(banner == item.banner) && (result != null) }">
													${result }<br/>
												</c:if><br />
												<input type="button" value="更新" onclick="uploadPromotion(${item.banner })"/></p></td>
									</tr></form></td></tr>
								</c:forEach>
							</table></td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
				</table></td>
		</tr>
	</table></td></tr></table>
	<jsp:include page="../buttom.jsp" flush="true" />
</body>
</html>
