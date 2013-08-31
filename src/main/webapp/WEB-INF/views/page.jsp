<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ page import="skynet.isi.dao.Page"%>
<%
	//String action = request.getServletPath() + "/?" + request.getQueryString();
	String action = (String)request.getAttribute("action");
	Page paging = (Page)request.getAttribute("searchResultPage");
%>
<table width="25%" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/search/search-10.jpg); background-repeat:no-repeat; background-position:center">
   <tbody><tr>
    
     <td width="8%"><a href="<%=paging.getFirstPageLink(action) %>"><img src="${ctx}/image/search/search-10-l.jpg" width="21" height="20"></a></td>
     <td width="84%" style="font-size:12px"><%=paging.getPageToolBar3(action) %></td>
     <td width="8%" align="right"><a href="<%=paging.getLastPageLink(action) %>"><img src="${ctx}/image/search/search-10-r.jpg" width="21" height="20"></a></td>
     
     <!-- 
     <%=paging.getPageToolBar3(action) %>
     -->
   </tr>
 </tbody>
 </table>