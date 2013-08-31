<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收藏</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script src="${ctx}/script/jquery.js"></script>
<script>
	function addToCart(){
		$.post("/frontend/addToCart", $("#favoritListForm").serialize(), function(data) {
			$("#result").html(data);
		});
	}
	function deleteFromFavorite(){
		$.post("/frontend/deleteFavorite", $("#favoritListForm").serialize(), function(data) {
			$("#result").html(data);
		});
		var table = document.getElementById("favoritListTable");
		var rowCount = table.rows.length;
        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].getElementsByTagName("input")[0];
            if(null != chkbox && true == chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
	}
</script>
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

				</ul> <!--------------------------菜单----------------></td>
			<td width="78%" rowspan="2" valign="top">
				<jsp:include page="../searchTop.jsp" flush="true" /> <br /> <br />
				<table width="100%" border="0">
					<tr>
						<td>&nbsp;</td>
						<td><table width="100%" border="0" cellpadding="0"
								cellspacing="0" class="search-bg">
								<tr>
									<td width="7%"><img src="${ctx}/image/search/search-01.jpg"
										width="64" height="80" align="left" /></td>
									<td width="44%"><p>
											<b>查询列表</b>
										</p>
										<p>* 以下项目是您所收藏的产品，您可以进行购买或者删除一项或多项产品</p></td>
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
						<td>&nbsp;</td>
					</tr>
					<!--tr>
          <td height="167">&nbsp;</td>
          <td>
		  <table width="53%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td width="5%" rowspan="2" align="right"><img src="${ctx}/image/search/search-04.jpg" width="39" height="136" /></td>
              <td height="40" colspan="7" style="background-image:url(${ctx}/image/search/search-05.jpg); background-repeat:repeat-x; background-position:top">&nbsp;</td>
              <td width="14%" rowspan="2" valign="top"><img src="${ctx}/image/search/search-07.jpg" width="13" height="136" /></td>
            </tr>
            <tr style="background-image:url(${ctx}/image/search/search-06.jpg); background-repeat:repeat-x; background-position:bottom" height="60">
              <td width="18%"><select name="select" style="width:150px">
              </select></td>
              <td width="0%">&nbsp;</td>
              <td width="20%"><input type="text" name="textfield" /></td>
              <td width="0%" >&nbsp;</td>
              <td width="20%"><input type="text" name="textfield" /></td>
              <td width="1%">&nbsp;</td>
              <td width="22%"><input type="text" name="textfield" /></td>
            </tr>
          </table>
		  </td>
          <td>&nbsp;</td>
        </tr-->
					<tr>
						<td height="13">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><div id="result"></div></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="13">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<form id="favoritListForm" method="post">
							<table width="919" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td height="23"
										style="background-image: url(${ctx}/image/search/search-08-2.jpg); color: #333; font-size: 10px">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="16%" height="21" align="center" style="color: #333; font-size: 10px">
													<shiro:authenticated>
													<a onclick="addToCart()">加入购物车</a>
													</shiro:authenticated>
													</td>
												<td width="84%">
													<input class="button" name="topUpdateCart" value="删除" type="button" onclick="deleteFromFavorite()"/></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><table id="favoritListTable" width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#999999">
											<tr bgcolor="#FF6600" align="center">
												<td width="3%" height="50">选择</td>
												<td width="7%">图&nbsp;片</td>
												<td width="14%">制造商编号</td>
												<td width="17%">制造商</td>
												<td width="17%">产品描述</td>
												<td width="11%">Datasheet</td>
												<td width="7%">库&nbsp;存</td>
												<td>包装规格</td>
												<td width="8%">价&nbsp;格</td>
											</tr>
											<c:forEach items="${searchResultPage.result}" var="item" varStatus="rowCounter">
											<tr bgcolor="#FFFFFF" align="center">
												<td>
													<input type="checkbox" name="checkbox${rowCounter.count }" value="checkbox" />
													<input type="hidden" name="productId${rowCounter.count }" value="${item.id }"/></td>
												<td><a href="/frontend/getProductInfo?productId=${item.id }&shoppingCartId=0"><img src="${item.imageUrl}" width="75" height="64" /></a></td>
												<td style="color: #FF6600">${item.mfrSku}</td>
												<td>${item.manufacturer.mfrName}</td>
												<td>${item.description}</td>
												<td>${item.dataSheet}</td>
												<td>${item.store}</td>
												<td>${item.packSize}</td>
												<td>${item.onePrice}</td>
											</tr>
											</c:forEach>
										</table></td>
								</tr>
								<tr>
									<td height="38"
										style="background-image: url(${ctx}/image/search/search-09-2.jpg); padding-right: 30px"
										align="right">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="17%" align="center" style="color: #333; font-size: 10px">
													<a onclick="addToCart()">加入购物车</a>
													</td>
												<td width="5%" style="color: #333; font-size: 10px">
													<input class="button" name="topUpdateCart" value="删除" type="button" onclick="deleteFromFavorite()"/></td>
												<td width="84%" align="right">
													<jsp:include page="../page.jsp" flush="true" />
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table></form></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="3%">&nbsp;</td>
						<td width="96%">&nbsp;</td>
						<td width="1%">&nbsp;</td>
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
