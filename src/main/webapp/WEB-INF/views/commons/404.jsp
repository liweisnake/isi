<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<script language="javascript"> 
// JavaScript Document 

startList = function() { 
if (document.all&&document.getElementById) { 
navRoot = document.getElementById("nav"); 
for (i=0; i<navRoot.childNodes.length; i++) { 
node = navRoot.childNodes[i]; 
if (node.nodeName=="LI") { 
node.onmouseover=function() { 
this.className+=" over"; 
 } 
 node.onmouseout=function() { 
 this.className=this.className.replace(" over", ""); 
 } 
 } 
 } 
 } 
} 
window.onload=startList; 
</script> 
 
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />

</head>

<body>
<jsp:include page="../top.jsp" flush="true" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td  width="10%" align="right" valign="top" bgcolor="f0f0f2"><img src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
    <td width="226" rowspan="2" align="right" valign="top"><img src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
        <ul>
          <br />
          <jsp:include page="../leftMenu.jsp" flush="true" />
          <br />
          <script>attachXMenu(xmenu0); //在上面这个table结束的地方执行事件动作的绑定, 这里的这个xmenu0就是那个table的id</script>
          <script language="JavaScript" type="text/javascript">
function over()
{
 document.getElementById("mouse").style.display=""
}

function out()
{
 document.getElementById("mouse").style.display="none"
}

function over2()
{
 document.getElementById("mouse2").style.display="block"
}
function out2()
{
 document.getElementById("mouse2").style.display="none"
}
      </script>
        </ul>
      <!--------------------------菜单---------------->
    </td>
    <td width="78%" rowspan="2" valign="top">
    	
      <br />
      <br />
      <table width="100%" border="0">
        <tr>
          <td width="3%">&nbsp;</td>
          <td><br />
            <table width="95%" cellpadding="15">
              <tr>
                <td width="19%" align="left"><img src="${ctx}/image/menu/error.jpg" width="156" height="156" /></td>
                <td width="81%" height="36" align="left">
				
				对不起，没有搜索到您所查找的页面。您可以尝试通过其他的方式联系我们：

<br />
				<br />
				联系电话：<span class="search-phon">(8621) 5437-0611</span><br />
				<br />
				发送邮件至<span class="search-over">sales@iic.net.cn	</span>				<br />
                <br />
                <p>新浪微博：<span class="search-over">@艾矽电子</span> </p>
                </td>
              </tr>
          </table></td>
        </tr>
        
        <tr>
          <td height="33">&nbsp;</td>
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
