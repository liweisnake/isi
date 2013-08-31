<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script>
	function getChildCategory(obj){
		var inputs = obj.parentNode.getElementsByTagName("input");
		var categoryId = inputs[0].value;
		window.location.href = "/frontend/getChildCategory?categoryId=" + categoryId;
		//$('#content').load('/frontend/getChildCategory?categoryId=' + categoryId);
	}
</script>

<table class="xmenu" id="xmenu0" cellpadding="0" cellspacing="1" border="0" bgcolor="#" align="center">
	<c:forEach items="${topCategory }" var="item">
	<tr>
		<td>
			<li>
				<a onclick="getChildCategory(this)">${item.categoryName }</a>
				<input type="hidden" value="${item.id }"/>
			</li> <!----------------------------></td>
	</tr>
	</c:forEach>
</table>
