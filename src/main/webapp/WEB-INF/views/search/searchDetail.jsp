<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品信息</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script src="${ctx}/script/jquery.js"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1261596743" type="text/javascript" charset="utf-8"></script>
<script>
	var isAuth = $("#isAuth").val();
	$(document).ready(function() {
		
		$("#submitComment").click(function() {
			
			if(isAuth == "false"){
				alert("请先登录");
			}else{
				var myComment = $("textarea#myComment").val();
				if(myComment == ""){
					alert("请添加评论");
					return;
				}
				$.post("/frontend/addComment", $("#commentForm").serialize(), function(data) {
					$("#result").html(data);
					addComment();
				});
			}
			
		});
		$("#addToCart").click(function() {
			$("#addResult").html("");
			if(isAuth == "false"){
				alert("请先登录");
			}else{
				if($("#amount").val() == "")
				{
					alert("请填入商品数量");
					return ;
				}
				$.post("/frontend/addToCartForOneProduct", $("#form").serialize(), function(data) {
					$("#addResult").html(data);
				});
			}
		});
		$("#buyNow").click(function() {
			$("#addResult").html("");
			if(isAuth == "false"){
				alert("请先登录");
			}else{
				$.post("/frontend/addToFavorite", $("#form").serialize(), function(data) {
					$("#addResult").html(data);
				});
			}
		});
	});
	
	function addComment(){
		var loginName = document.getElementById("loginName").value;
		var table = document.getElementById("commentsTable");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "用户【<span class='u-name'>" + loginName + "</span>】：" + $("textarea#myComment").val();

		$("textarea#myComment").val("");
	}
	
	WB2.anyWhere(function(W){
	    W.widget.publish({
	        'id' : 'publish',
	        'default_text' : '我分享了${product.manufacturer.mfrName }-${product.mfrSku }，详情点击http://www.iic.net.cn/frontend/getProductInfo?productId=${product.id}&shoppingCartId=0(分享自@艾矽电子)'
	    });
	});
</script>
</head>

<body>
	<jsp:include page="../top.jsp" flush="true" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<input id="isAuth" type="hidden" value="<%=SecurityUtils.getSubject().isAuthenticated()%>"/>
			<td width="10%" align="right" valign="top" bgcolor="f0f0f2">
				<img src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
			<td width="226" rowspan="2" align="right" valign="top">
				<img src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
				<ul>
					<br />
					<jsp:include page="../leftMenu.jsp" flush="true" />
				</ul> <!--------------------------菜单----------------></td>
			<td width="78%" rowspan="2" valign="top">
				<jsp:include page="../searchTop.jsp" flush="true" /> <br /> <br />
				<table width="100%" border="0">
					<tr>
						<td height="167">&nbsp;</td>
						<td colspan="2" valign="top"><br />
							<table width="95%" cellpadding="15">
								<tr>
									<td height="36" colspan="3" align="left">
										<h1>${product.manufacturer.mfrName } - ${product.mfrSku } - ${product.description }</h1></td>
								</tr>
								<tr valign="top">
									<td width="31%" height="580" rowspan="2" align="left"><div>
											<img style="WIDTH: auto; DISPLAY: block; HEIGHT: auto"
												title="${product.manufacturer.mfrName } - ${product.mfrSku } - ${product.description }"
												alt="${product.manufacturer.mfrName } - ${product.mfrSku }"
												src="${product.imageUrl }" /></IMG>
											<div class="disclaimer">
												图片只做观看之用<br /> 请参考产品描述
											</div>
											<p>&nbsp;</p>
											<div class="disclaimer">
												<p>
													分享至 <a href="javascript:;" id="publish"><img src="${ctx}/image/menu/w2.jpg" width="28" height="30" /></a><br /> <br /> 
													产品反馈：<br />
													<input id="loginName" type="hidden" value="<%=org.apache.shiro.SecurityUtils.getSubject().getPrincipal()%>"/>
													<table id="commentsTable">
													<c:forEach items="${comments }" var="comment">
													 	<tr><td>用户【<span class="u-name">${comment.user.loginName }</span>】：${comment.comment }</td></tr>
													</c:forEach>
													</table>
												</p>
												<p> <form id="commentForm">
													<input name="productId" type="hidden" value="${product.id }"/>
													<img src="${ctx}/image/menu/comment-2.jpg" width="120"
														height="24" /><br />
													<textarea id="myComment" name="myComment" cols="50" rows="10"></textarea>
													<br /> <input id="submitComment" class="button" value="提交" type="button" />
														<div id="result"></div>
												</form></p>
											</div>
										</div></td>
									<td height="165" align="left">
										<!-- //#sub-header --> <!-- //#sidebar --> <!-- //#main-content -->
										<!--打开页面---->
										<dl>
											<dt>制造商:</dt>
											<dd style="TEXT-ALIGN: left">${product.manufacturer.mfrName }</dd>
											<dt>IIC库存编号:</dt>
											<dd>${product.iicSku }</dd>
											<dt>分销库存编号:</dt>
											<dd>${product.distySku }</dd>
											<dt>制造商编号:</dt>
											<dd>${product.mfrSku }</dd>
										</dl>
										<p>
											<img title="Technical Data Sheet (65.04KB) EN"
												alt="Technical Data Sheet (65.04KB) EN"
												src="${ctx}/image/search/icon_pdf.gif" /> <a
												onclick="trackProductDetailViews(this, 'Data Sheet', 'd')"
												href="${product.dataSheet }" rel="nofollow" target="info">Technical Data
												Sheet EN </a>
										</p>
									</td>
									<td width="49%" rowspan="2" align="left" bgcolor="#E7E7D8">
										<div id="orderDetailsContainer">
											<div class="order-details">
												<div class="availability">
													<table>
														<tbody>
															<tr>
																<td width="135" bgcolor="#FFD9B3"
																	class="prodDetailAvailability"><b>库存状态</b>:${product.store }</td>
															</tr>
															<!-- 
															<tr>
																<td class="prodDetailAvailability"><a
																	onclick="window.open('/jsp/commonfragments/productWarehouseInventory.jsp?sku=157454','price','height=300, width=500,scrollbars=no'); return false;"
																	href="http://cn.element14.com/jsp/commonfragments/productWarehouseInventory.jsp">上海
																		<b>0 </b>
																</a>,&nbsp; <a
																	onclick="window.open('/jsp/commonfragments/productWarehouseInventory.jsp?sku=157454','price','height=300, width=500,scrollbars=no'); return false;"
																	href="http://cn.element14.com/jsp/commonfragments/productWarehouseInventory.jsp">美国
																		<b>0 </b>
																</a>,&nbsp; <a
																	onclick="window.open('/jsp/commonfragments/productWarehouseInventory.jsp?sku=157454','price','height=300, width=500,scrollbars=no'); return false;"
																	href="http://cn.element14.com/jsp/commonfragments/productWarehouseInventory.jsp">英国
																		<b>0 </b>
																</a>,&nbsp; <a
																	onclick="window.open('/jsp/commonfragments/productWarehouseInventory.jsp?sku=157454','price','height=300, width=500,scrollbars=no'); return false;"
																	href="http://cn.element14.com/jsp/commonfragments/productWarehouseInventory.jsp">新加坡
																		<b>0 </b>
																</a>,&nbsp; <a
																	onclick="window.open('/jsp/commonfragments/productWarehouseInventory.jsp?sku=157454','price','height=300, width=500,scrollbars=no'); return false;"
																	href="http://cn.element14.com/jsp/commonfragments/productWarehouseInventory.jsp">英国
																		<b>10</b>
																</a></td>
															</tr> -->
														</tbody>
													</table>
													<p>
														<b>包装单位:</b> ${product.packSize }
													</p>
													<p>
														<b>包装规格:</b> ${product.packSize }
													</p>
													<p>
														<b>最小订单量:</b> 1
													</p>
												</div>
												<div class="price">

													<table width="51%" id="otherquantites">
														<tbody>
															<tr>
																<th colspan="2" bgcolor="#FFD9B3">价格</th>
															</tr>
															<tr>
																<th width="44%">数量</th>
																<th>单位价格(不含税)</th>
															</tr>
															<tr>
																<td>${product.oneAmount }</td>
																<td>CNY ${product.onePrice }</td>
															</tr>
														</tbody>
													</table>
													<p>
														<br />
													</p>
												</div>
											</div>

											<form id="form" method="post">
											<p>
												数量 <input id="amount" name="amount" type="text" width="55px" />
												<input id="productId" name="productId" type="hidden" value="${product.id }"/>
											</p>
											<p>
												
												<a id="buyNow"><img src="${ctx}/image/search/btn_add_cart.gif" /></a>
												<br /> <br /> 
												<a id="addToCart"><img src="${ctx}/image/search/btn_add.jpg" width="120" height="22" /></a>
												<div id="addResult" style="color:red"></div>
											</p>
											</form>
										</div> 更多批量价格，请咨询客服人员。<br /> <br /> 联系电话：<span class="search-phon">(8621)
											5437-0611</span><br /> <br /> 或发送邮件至<span class="search-over">sales@iic.net.cn
									</span>
									</td>
								</tr>
								<tr valign="top">
									<td height="372" align="left"><ul>
											<li>${product.longDesc }</li>
										</ul></td>
								</tr>
							</table></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="33">&nbsp;</td>
						<td width="26%">&nbsp;</td>
						<td width="71%">&nbsp;</td>
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
