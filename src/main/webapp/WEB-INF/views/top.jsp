<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />

</head>

<table width="100%" border="0" cellpadding="0" cellspacing="0" background="${ctx}/image/menu/m-bg.jpg">
  <tr>
    <td width="10%" height="110" align="right"><img src="${ctx}/image/menu/left-top.jpg" width="140" height="2" /></td>
    <td width="227" bgcolor="#FFFFFF"><img src="${ctx}/image/logo.jpg" width="227" height="62" /></td>
    <td width="1%">&nbsp;</td>
    <td width="77%" valign="top"><table border="0" align="right" cellpadding="0" cellspacing="0">
      <tr align="center" >
        <td height="3" colspan="3">&nbsp;</td>
      </tr>
      <tr >
        <td height="50" colspan="2" align="right">
		<table border="0" align="right" cellpadding="0" cellspacing="0">
          <tr align="center" class="top-line">
            <td width="24" height="50" align="center"><img src="${ctx}/image/menu/bg-t.jpg" width="24" height="24" align="left" /></td>
            <td width="37" height="50" align="center" valign="top"><img src="${ctx}/image/menu/p.jpg" width="37" height="37" align="left" /></td>
            <td height="50" colspan="2" align="center" class="top-phon"><b>(86) 21-5437 0611</b></td>
            <td width="21" align="center" valign="top">&nbsp;</td>
            <td width="33" height="50" align="center" valign="top"><img src="${ctx}/image/menu/e.jpg" width="33" height="37" /></td>
            <td height="50" colspan="2" align="left"  class="mail">sales@iic.net.cn</td>
            <td width="12" align="center" valign="top">&nbsp;</td>
            <td width="37" height="50" align="center" valign="top"><img src="${ctx}/image/menu/c.jpg" width="37" height="37" /></td>
            <td width="57">在线客服</td>
            <td width="37" height="50" align="left" valign="top"   class="mail"><img src="${ctx}/image/menu/w.jpg" width="37" height="37" /></td>
            <td width="37" height="50" align="center" valign="top"><img src="${ctx}/image/menu/h.jpg" width="37" height="37" /></td>
            <td width="56" height="50" align="center" valign="top"><a href="/frontend/getShoppingCart"><img src="${ctx}/image/menu/b.jpg" width="37" height="37" /></a></td>
          </tr>
        </table></td>
        <td height="50" align="center" style="background-image:url(${ctx}/image/menu/bg-tbg.jpg); background-repeat:repeat-x; background-position:center">&nbsp;</td>
      </tr>
      <tr align="center" class="menu">
        <td>&nbsp;</td>
        <td height="39" align="right"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr height="39" align="center" class="menu">
              <td width="75" class="menu-on"><a href="/frontend/fuzzySearch?searchStr=">产 品</a></td>
              <td width="75" class="menu-on"><a href="/frontend/getAllManufacturers">供应商</a></td>
              <td width="75" class="menu-on"><a>产品目录</a></td>
              <td width="75" class="menu-on"><a>服 务</a></td>
              <td width="75" class="menu-on"><a href="/frontend/userInfo">我的账户</a></td>
            </tr>
        </table></td>
        <td >&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
