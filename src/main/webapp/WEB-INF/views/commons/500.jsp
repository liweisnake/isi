<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>


 
<link rel="stylesheet" href="${ctx}css/style.css" type="text/css" />

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
         
        </ul>
      <!--------------------------菜单---------------->
    </td>
    <td width="78%" rowspan="2" valign="top">
    <c:choose>
	        	<c:when test="${loginName == null || loginName == ''}">
	            	<c:set var="jspPage" scope="page" value="../searchTop.jsp"/>
	          	</c:when>
	          	<c:otherwise>
	            	<c:set var="jspPage" scope="page" value="../welcome.jsp"/>
	          	</c:otherwise>
	        </c:choose>
			<jsp:include page="${jspPage }" flush="true" />
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
				
				对不起，您当前所请求的服务出现错误，请稍后再试。您也可以尝试通过其他的方式联系我们：
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
