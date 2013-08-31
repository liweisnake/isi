<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel=stylesheet type=text/css href="${ctx}/css/admin.css">
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td valign="top" class="left-t">产品管理</td>
		</tr>
		<tr>
			<td width="22%" valign="top" class="left"><a href="showAdminProductInfo" style="text-decoration:none">产品信息管理</a></td>
		</tr>
		<!-- 
		<tr>
			<td valign="top" class="left"><a href="showAdminBatch" style="text-decoration:none">批量处理</a></td>
		</tr>
		 -->
		<tr>
			<td valign="top" class="left-t">限时抢购</td>
		</tr>
		<tr>
			<td height="262" valign="top" class="left"><a href="adminPromotion" style="text-decoration:none">限时抢购管理</a></td>
		</tr>
		<tr>
			<td valign="top" class="left-t">积分管理</td>
		</tr>
		<tr>
			<td height="262" valign="top" class="left"><a href="showAdminCreditManagement" style="text-decoration:none">积分更新</a></td>
		</tr>
		<tr>
			<td height="262" valign="top" class="left"><a href="showAdminCreditHistory" style="text-decoration:none">使用历史查看</a></td>
		</tr>
		<tr>
			<td align="right">&nbsp;</td>
		</tr>
	</table>
</body>
</html>