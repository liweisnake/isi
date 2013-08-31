<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分类</title>


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
			<td width="10%" align="right" valign="top" bgcolor="f0f0f2"><img
				src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
			<td width="226" rowspan="2" align="right" valign="top"><img
				src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
				<ul>
					<br />
					<jsp:include page="../leftMenu.jsp" flush="true" />
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
				</ul> <!--------------------------菜单----------------></td>
			<td width="78%" rowspan="2" valign="top">
			<jsp:include page="../searchTop.jsp" flush="true" /> 
				<table width="100%" border="0">
					<tr>
						<td width="3%">&nbsp;</td>
						<td><table width="100%" border="0" cellpadding="0"
								cellspacing="0" class="search-bg">
								<tr>
									<td width="7%"><img src="${ctx}/image/search/search-01.jpg"
										width="64" height="80" align="left" /></td>
									<td width="44%"><p>
											<b>查询列表</b>
										</p>
										<p>
											查询<span class="search-over"> ${resultNum } </span>结果
										</p></td>
									<td width="48%" align="right" valign="top"
										style="padding-top: 5px"><table width="500" border="0"
											cellpadding="0" cellspacing="0">
											<tr align="center">
												<td width="316" height="29" align="right"><img
													src="${ctx}/image/menu/p2.jpg" width="17" height="17" /></td>
												<td width="184" height="29" colspan="2" align="right"
													class="search-phon"><strong>(86) 21-5437 0611</strong></td>
											</tr>
										</table></td>
									<td width="1%" align="right"><img
										src="${ctx}/image/search/search-03.jpg" width="11" height="80" /></td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="167">&nbsp;</td>
						<td valign="top"><br />
							<table width="90%" border="0" cellpadding="5" cellspacing="1"
								bgcolor="#999999">
								<tr>
									<td height="42" colspan="3" align="left" bgcolor="#EBE9ED"><h1>发现
											${categoryTitle }: ${resultNum } 产品</h1></td>
								</tr>
								<tr>
									<td colspan="3" align="left" bgcolor="#EBE9ED"><span
										style="color: #333; padding-left: 5px"><b>&gt; 所有产品</b></span></td>
								</tr>
								<c:forEach items="${result}" var="middleCategory" varStatus="rowCounter">
								<c:if test="${rowCounter.count % 2 == 1 }">
								<tr bgcolor="#FFFFFF">
								</c:if>
									<td width="48%" style="padding: 6px 6px 6px 6px" valign="top"><p>
											<b>&or; ${middleCategory.categoryName }</b>
										</p>
										<dl>
											<c:forEach items="${middleCategory.child}" var="bottomCategory">
											<c:if test="${bottomCategory.childCount != 0 }">
											<dd>
												<a href="/frontend/getProductByCategory?categoryId=${bottomCategory.id }&mfrId=${manufacturerId}&pageNo=1">
												${bottomCategory.categoryName } </a>
												(${bottomCategory.childCount })
											</dd>
											</c:if>
											</c:forEach>
										</dl></td>
										<c:if test="${rowCounter.count % 2 == 1 }">
										<td width="2%" style="padding:6px 6px 6px 6px">&nbsp;</td>
										</c:if>
								<c:if test="${rowCounter.count % 2 == 0 }">
								</tr>
								</c:if>
								</c:forEach>
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
