<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单提交成功</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />

</head>

<body>
	<jsp:include page="../top.jsp" flush="true" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="10%" align="right" valign="top" bgcolor="f0f0f2"><img src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
			<td width="226" rowspan="2" align="right" valign="top"><img src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
				<ul>
					<br />
					<jsp:include page="../leftMenu.jsp" flush="true" />

				</ul> <!--------------------------菜单----------------></td>
			<td width="78%" rowspan="2" valign="top">
				<jsp:include page="../searchTop.jsp" flush="true" />
				<br /> <br />
				<table width="880" border="0">
					<tr>
						<td width="3%" rowspan="2">&nbsp;</td>
						<td width="92%" rowspan="2">
							<!--table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image:url(${ctx}/image/search/search-02.jpg); background-position:center; background-repeat:repeat-x">
            <tr>
              <td width="7%"><img src="${ctx}/image/search/search-01.jpg" width="64" height="80" align="left" /></td>
              <td width="69%" style="font-family:Arial, Helvetica, sans-serif; color:#333; font-size:12px"><p><b>查询列表</b></p>
                <p style="font-family:Arial, Helvetica, sans-serif; color:#333; font-size:12px">查询<span style=" font-size:18px; color:#FF6600"> 13131313 </span>结果</p></td>
              <td width="23%" align="right" valign="top" style="padding-top:5px"><table width="73%" border="0" cellpadding="0" cellspacing="0">
                  <tr align="center">
                    <td width="17%" height="29" align="center"><img src="${ctx}/image/menu/p2.jpg" width="17" height="17" align="left" /></td>
                    <td width="83%" height="29" colspan="2" align="left" style="font-size:20px; color:#FF6600"><strong>(86) 21-5437 0611</strong></td>
                  </tr>
              </table></td>
              <td width="1%" align="right"><img src="${ctx}/image/search/search-03.jpg" width="11" height="80" /></td>
            </tr>
          </table--> <br />
							<table width="90%" border="0" cellspacing="0" cellpadding="0">
								<tr align="center" style="">
									<td height="47"
										style="background-image: url(${ctx}/image/buy/1-over.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px; color: #666"><b>&nbsp;我的购物车</b></td>
									<td
										style="background-image: url(${ctx}/image/buy/2-over.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px; color: #666"><b>&nbsp;填写核对订单</b></td>
									<td
										style="background-image: url(${ctx}/image/buy/3-off.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px; color: #000"><b>&nbsp;订单提交成功</b></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="9%" align="center" valign="top"
										style="BORDER-top: #FF6600 1px solid; border-left: #FF6600 1px solid;">&nbsp;</td>
									<td colspan="2" align="center"
										style="BORDER-top: #FF6600 1px solid; border-right: #FF6600 1px solid;">${msg}<br />
										如果您有任何的问题，请致电<span style="font-size: 16px; color: #FF9900">021-54370611</span></td>
								</tr>

								<tr>
									<td colspan="3" align="center" valign="top" bgcolor="#FFE6D9"
										style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid; border-right: #FF6600 1px solid;">&nbsp;</td>
								</tr>
							</table> <br />
						</td>
						<td width="5%">&nbsp;</td>
					</tr>
					<tr>
						<td height="167">&nbsp;</td>
					</tr>
					<tr>
						<td height="20">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td bgcolor="f0f0f2">&nbsp;</td>
		</tr>
	</table>
	<jsp:include page="../buttom.jsp" flush="true" />
</body>
</html>
