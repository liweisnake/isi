<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>批量处理</title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css" />
<script type="text/javascript" src="${ctx}/script/check.js"></script>
<script type="text/javascript" src="${ctx}/script/submit.js"></script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center"><img src="${ctx}/image/admin/admin-logo.jpg"
				width="181" height="70" /></td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="background-image: url(${ctx}/image/admin/line01.jpg); background-repeat: repeat-x; background-position: center;">
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
						<td bgcolor="#F5F5EF"><h1>产品管理-批量修改价格</h1></td>
					</tr>
					<tr>
						<td width="78%" bgcolor="#F5F5EF">
							<form id="uploadForm" action="upload" method="post" enctype="multipart/form-data">
								<input type="file" name="file" accept="*.xls" /><br/> <br/>  
								<font color="red">注意：上传文件大小必须在4M以内！</font><br/> <br/>  
							</form></td>
					</tr>
					<c:if test="${result != null}">
						<tr height="60">
							<td><p>${result}</p></td>
						</tr>
					</c:if>
					<tr>
              			<td bgcolor="#F5F5EF">
              			<table border="0" cellspacing="0" cellpadding="0" class="confirmation">
		                	<tr>
		                  		<td align="center"><a onclick="submitUpload()">上传至网站</a></td>
		                	</tr>
		              	</table></td>
		            </tr>
					<tr>
						<td bgcolor="#F5F5EF">&nbsp;</td>
					</tr>
					<tr>
						<td align="right" bgcolor="#F5F5EF">&nbsp;</td>
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
