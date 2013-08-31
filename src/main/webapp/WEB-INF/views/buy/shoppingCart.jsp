<%@page import="com.sun.mail.imap.protocol.Item"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的购物车</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script type="text/javascript" src="${ctx}/script/jquery.js"></script>
<script type="text/javascript" src="${ctx}/script/check.js"></script>
<script type="text/javascript" src="${ctx}/script/calculate.js"></script>
<script>
	$(document).ready(function() {
		$("#delete").click(function() {
			$.post("/frontend/deleteCart", $("#shoppingCartForm").serialize(), function(data) {
				$("#result").html(data);
			});
			var table = document.getElementById("shoppingCartTable");
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
			calculateOrderTotalPrice();
		});
		$("#topUpdateCart").click(function() {
			$.post("/frontend/updateCart", $("#shoppingCartForm").serialize(), function(data) {
				$("#result").html(data);
			});
		});
		$("#continueToOrder").click(function() {
			document.forms['shoppingCartForm'].action = "/frontend/checkout";
			if($("#preTaxPriceNum").text() == 0){
				alert("请选择您需要结账的产品");
				return ;
			}
			$("#shoppingCartForm").submit();
		});
		$("#continueShopping").click(function() {
			window.location.href = "/frontend/continueShopping";
		});
	});
	
	function calculateItemTotalPrice(rowNum){
		var row = document.getElementById(rowNum);
		var cells = row.getElementsByTagName("td");
		var inputs = cells[3].getElementsByTagName("input");
		var num = inputs[0].value;
		if (!isNumber(num)){
			alert("数量必须是整数！");
			return false;
		}
		
		var unitPrice = cells[8].innerHTML;
		cells[9].innerHTML = accMul(num, unitPrice);
		calculateOrderTotalPrice();
	}
	
	function calculateOrderTotalPrice(){
		var totalPrice = 0;
		var rowNum = 1;
		while (document.getElementById(rowNum)){
			var itemRow = document.getElementById(rowNum);
			var itemCells = itemRow.getElementsByTagName("td");
			var checkbox = itemCells[0].getElementsByTagName("input")[0];
			if (checkbox.checked){
				var itemTotalPrice = itemCells[9].innerHTML;
				totalPrice = accAdd(totalPrice, itemTotalPrice);
			}
			rowNum = rowNum + 1;
		}
		
		setTotalPrice(totalPrice);
	}
	
	function setTotalPrice(preTaxPrice){
		var valueAddedTax = accMul(preTaxPrice, 0.17);
		if (preTaxPrice < 500)
			var freight = 15.0;
		else freight = 0.0;
		var totalPrice = Number(preTaxPrice).add(Number(valueAddedTax)).add(Number(freight));
		
		setInnerHTML("preTaxPrice", 1, preTaxPrice);
		setInnerHTML("valueAddedTax", 1, valueAddedTax);
		setInnerHTML("freight", 1, freight);
		setInnerHTML("totalPrice", 1, totalPrice);
	}
	
	function getInnerText(trName, tdNo){
		var row = document.getElementById(trName);
		var cells = row.getElementsByTagName("td");
		return cells[tdNo].innerText;
	}
	
	function setInnerHTML(trName, tdNo, value){
		var row = document.getElementById(trName);
		var cells = row.getElementsByTagName("td");
		cells[tdNo].innerHTML = value;
	}
	
</script>
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

				<table width="100%" border="0">
					<tr>
						<td height="84" colspan="4">
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
							<table width="90%">
								<tr>
									<td width="77%"><h1>采购推车</h1></td>
									<td width="12%">&nbsp;</td>
									<td width="11%" align="left" valign="top">
										<!--table width="100%" border="0" align="right" cellpadding="5" cellspacing="0">
                    <tr>
                      <td width="2%" bgcolor="#FF9900">&nbsp;</td>
                      <td width="98%" bgcolor="#FF9900" style="color:#FFFFFF"><b>送货服务</b></td>
                    </tr>
                    <tr>
                      <td >&nbsp;</td>
                      <td >所有现货产品皆来自e络盟在上海、新加坡、英国和美国仓库的存货。<br />
                        上海 
                        - 即/翌日到<br />
                        新加坡 - 2-3个工作日交付<br />
                        英国/美国 - 
                        5-6个工作日交付<br />
                        仅当订单提交并收到回复后，该货品才会被保留</td>
                    </tr>
                    <tr>
                      <td >&nbsp;</td>
                      <td >&nbsp;</td>
                    </tr>
                </table-->
									</td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td height="33">&nbsp;</td>
						<td colspan="2">以下显示当前购物车中的产品。 目前提供 <a href="#"
							target="_blank">银行转账</a>、<a href="#" target="_blank">支付宝</a>等2种交易支付方式<br />
							<br /> 由于货物所在地以及海关等因素，不同的产品可能会有不同的货期。具体的交货时间，请咨询销售人员。
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="3%" height="33">&nbsp;</td>
						<td colspan="2">
							<form id="shoppingCartForm" method="post">
							<table width="98%">
								<tr valign="top">
									<td height="208" colspan="2" align="left">
										<table id="shoppingCartTable"
											width="100%" cellpadding="0" cellspacing="0"
											class="tableBorderSort">
											<tr bgcolor="#F0F0F0" height="">
												<td colspan="3">
													<!--a onclick="window.print();" href="javascript:void(0)">&nbsp;&nbsp;<b><img class="ex_vaM" src="search/btn_print.gif" /> 打印推车</b></a-->&nbsp;
												</td>
												<td class="colheads">&nbsp;</td>
												<td class="colheads">&nbsp;</td>
												<td class="colheads">&nbsp;</td>
												<td class="colheads">&nbsp;</td>
												<td class="colheads"><div id="result"></div></td>
												<td align="right" class="colheads">
													<input id="delete" type="button" class="button" name="delete" value="删除" /></td>
												<td class="colheads">&nbsp;</td>
												<td align="center" class="colheads printHide">
													<input id="topUpdateCart" type="button" class="button" name="topUpdateCart" value="更新推车" /></td>
											</tr>
											<tr>
												<td width="3%" align="center" bgcolor="#FFD9B3">选择</td>
												<td width="8%" align="center" bgcolor="#FFD9B3">项目<br />
													编号
												</td>
												<td width="24%" bgcolor="#FFD9B3" class="colheads">库存编号
												</td>
												<td width="3%" align="center" bgcolor="#FFD9B3"
													class="colheads">数量</td>
												<td width="13%" align="center" bgcolor="#FFD9B3"
													class="colheads">制造商编号</td>
												<td width="10%" align="center" bgcolor="#FFD9B3"
													class="colheads">产品描述</td>
												<td width="5%" align="center" bgcolor="#FFD9B3"
													class="colheads">RoHS</td>
												<td width="14%" align="center" bgcolor="#FFD9B3"
													class="colheads">库存状况</td>
												<td width="7%" align="center" bgcolor="#FFD9B3"
													class="colheads">单位价格<br /> (不含税)
												</td>
												<td width="7%" align="center" bgcolor="#FFD9B3"
													class="colheads">总价</td>
												<td width="6%" align="center" bgcolor="#FFD9B3"
													class="colheads printHide">删除 <input id="checkAll"
													class="brand_checkbox"
													onclick="if (this.checked) {
} else {
}" name="checkAll"
													type="checkbox" />
												</td>
											</tr>
											<!---tr class="printHide">
                    <td align="center" bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td align="center" bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td bgcolor="#333333" class="sortResults" id="cartSortRow"><div>
                        <div><a class="sortUp" 
                        href="#"><img 
                        title="排序 库存编号 / SKU 升序" alt="排序 库存编号 / SKU 升序" 
                        src="${ctx}/image/buy/arrow_sort_up.gif" /></IMG></a> <a 
                        class="sortDown" 
                        href="#"><img 
                        title="排序 库存编号 / SKU 降序" alt="排序 库存编号 / SKU 降序" 
                        src="${ctx}/image/buy/arrow_sort_down.gif" /></IMG></a> </div>
                    </div></td>
                    <td align="center" bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td align="center" bgcolor="#333333" class="sortResults" id="cartSortRow"><div>
                        <div><a class="sortUp" 
                        href="#"><img 
                        title="排序 制造商名称与产品编号 升序" alt="排序 制造商名称与产品编号 升序" 
                        src="${ctx}/image/buy/arrow_sort_up.gif" /></IMG></a> <a 
                        class="sortDown" 
                        href="#"><img 
                        title="排序 制造商名称与产品编号 降序" alt="排序 制造商名称与产品编号 降序" 
                        src="${ctx}/image/buy/arrow_sort_down.gif" /></IMG></a> </div>
                    </div></td>
                    <td bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td align="center" bgcolor="#333333" class="sortResults" id="cartSortRow"><div>
                        <div><a class="sortUp" 
                        href="#"><img 
                        title="排序 库存数 升序" alt="排序 库存数 升序" 
                        src="${ctx}/image/buy/arrow_sort_up.gif" /></IMG></a> <a 
                        class="sortDown" 
                        href="#"><img 
                        title="排序 库存数 降序" alt="排序 库存数 降序" 
                        src="${ctx}/image/buy/arrow_sort_down.gif" /></IMG></a> </div>
                    </div></td>
                    <td bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td bgcolor="#333333" class="sortResults" id="cartSortRow"></td>
                    <td bgcolor="#333333" 
                    class="sortResults printHide" id="cartSortRow"></td>
                  </tr--->
											<pftest:commerceitems>
												<tr class=" printHide">
													<td align="center">&nbsp;</td>
													<td align="center">&nbsp;</td>
													<td>&nbsp;</td>
													<td align="center">&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
													<td>&nbsp;</td>
												</tr>
												<c:forEach items="${searchResultPage}" var="item" varStatus="rowCounter">
													<c:choose>
											        	<c:when test="${rowCounter.count % 2 == 0}">
											            	<c:set var="rowStyle" scope="page" value=""/>
											          	</c:when>
											          	<c:otherwise>
											            	<c:set var="rowStyle" scope="page" value="#CCCCCC"/>
											          	</c:otherwise>
											        </c:choose>
												<tr id="${rowCounter.count}" height="30px" bgcolor="${rowStyle}">
													<td align="center">
														<input type="checkbox" name="checkbox${rowCounter.count }"
															checked="checked" onclick="calculateOrderTotalPrice()"/></td>
													<td align="center">${rowCounter.count}<input type="hidden" name="shoppingCartId${rowCounter.count }" value="${item.shoppingCartId }"/></td>
													<td><a href="/frontend/getProductInfo?shoppingCartId=${item.shoppingCartId }&productId=0">${item.iicSku }</a></td>
													<td align="center">
														<input name="intentNum${rowCounter.count }" maxlength="9" 
															value="${item.intentNum }" size="2" 
															onkeyup="calculateItemTotalPrice(${rowCounter.count })"/></td>
													<td>${item.mfrSku }</td>
													<td>${item.description }</td>
													<td align="center">${item.rohsStatus }</td>
													<td align="center">${item.store }</td>
													<td align="center">${item.onePrice }</td>
													<td align="center">${item.totalPrice }</td>
													<td align="center"></td>
												</tr>
											</c:forEach>
											</pftest:commerceitems>
											<tr height="30px" bgcolor="#CCCCCC">
												<td colspan="4" align="right">&nbsp;</td>
												<td align="center">&nbsp;</td>
												<td align="center">&nbsp;</td>
												<td class="printHide" escapexml="yes">&nbsp;</td>
												<td class="printHide" escapexml="yes">&nbsp;</td>
												<td class="printHide" escapexml="yes">&nbsp;</td>
												<td align="right" class="printHide" escapexml="yes">&nbsp;</td>
												<td class="printHide" escapexml="yes">&nbsp;</td>
												<td class="printHide" escapexml="yes">&nbsp;</td>
											</tr>



											<!---tr height="30px" bgcolor="#CCCCCC">
                    <td   colspan="3" align="right">增加行 </td>
                    <td align="center"><input name="emptyLinesA" maxlength="2" value="10" 
                        size="2" />
                        <input name="_D:emptyLinesA" value=" " type="hidden" />
                        <input name="emptyLinesB" value="10" type="hidden" />
                        <input 
                        name="_D:emptyLinesB" value=" " type="hidden" />                    </td>
                    <td align="center"><input class="button" border="0" name="addEmptyLines" value="添加" type="submit" istyle="ex_vaM" />
                        <input 
                        name="_D:addEmptyLines" value=" " type="hidden" />                    </td>
                    <td class="printHide" escapexml="yes"><input class="button" name="clearBlankLines" value="删除空白行" type="submit" />
                        <input 
                        name="_D:clearBlankLines" value=" " type="hidden" />                    </td>
                    <td class="printHide" escapexml="yes">&nbsp;</td>
                    <td class="printHide" escapexml="yes">&nbsp;</td>
                    <td align="right" class="printHide" escapexml="yes">&nbsp;
                      <input class="button" name="topUpdateCart" value="删除" type="submit" /></td>
                    <td class="printHide" escapexml="yes">&nbsp;</td>
                    <td class="printHide" escapexml="yes"><input class="button" name="topUpdateCart" value="更新推车" type="submit" /></td>
                  </tr--->
										</table></td>
								</tr>
								<tr valign="top" height="34">
									<td><table border="0" cellspacing="0" cellpadding="0">

											<!-- fragment to save the current cart -->
											<!--tr align="left">
                    <td height="60px">保存推车 </td>
                    <td><input name="/"  value="../profile/mySavedCarts.jsp" type="hidden" />
                        <input name="/" value=" " type="hidden" />
                        <input name="/" value="../shoppingCart/shoppingCart.jsp" type="hidden" />
                        <input name="/" value=" " type="hidden" />
                        <input name="/" value="false" type="hidden" />
                        <input name="/" value=" " type="hidden" />
                        <input name="saveOrderName" maxlength="30" maxsize="64" />
                        <input name="saveOrderName" value=" " type="hidden" />
                    </td>
                    <td>&nbsp;
                        <input name="saveOrder" value="保存推车" type="submit" />
                        <input name="saveOrder" value=" " type="hidden" />
                    </td>
                  </tr-->



											<tr>
												<td width="38">&nbsp;</td>
												<td width="308">* 输入库存编号和数量并点击&nbsp;<span
													style="color: #FF9900">更新推车</span></td>
											</tr>
										</table></td>
									<td rowspan="2"><table style="MARGIN-TOP: 15px" border="0"
											cellspacing="0" cellpadding="0" width="100%">
											<tr id="preTaxPrice">
												<td class="brandColor" align="right">税前价格合计：CNY &nbsp;</td>
												<td class="brandColor" align="right" id="preTaxPriceNum">
													${orderAmount.preTaxPrice }</td>
											</tr>
											<tr id="valueAddedTax">
												<td class="brandColor" width="85%" align="right">增值税：CNY &nbsp;</td>
												<td class="brandColor" width="15%" align="right">
													${orderAmount.valueAddedTax }</td>
											</tr>
											<tr>
												<style type="text/css">
.offer {
	BORDER-BOTTOM: #f24811 1px solid;
	BORDER-LEFT: #f24811 1px solid;
	PADDING-BOTTOM: 10px;
	BACKGROUND-COLOR: #f8ffca;
	PADDING-LEFT: 10px;
	WIDTH: 50%;
	PADDING-RIGHT: 10px;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;
	COLOR: #333333;
	FONT-SIZE: 11px;
	BORDER-TOP: #f24811 1px solid;
	BORDER-RIGHT: #f24811 1px solid;
	PADDING-TOP: 10px
}

.offer H3 {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	FONT-SIZE: 11px;
	PADDING-TOP: 0px
}

.offer P {
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	PADDING-TOP: 0px
}
</style>
											</tr>
											<tr id="freight">
												<td align="right">运费：CNY &nbsp;</td>
												<td nowrap="nowrap" align="right">
													<pftest:shippingcosts>${orderAmount.freight }</pftest:shippingcosts></td>
											</tr>
											<tr  id="totalPrice" class="subtotalRow">
												<td class="brandColor" align="right">总计：CNY &nbsp;</td>
												<td class="brandColor mfOrderTotal" nowrap="nowrap" align="right">
													<pftest:ordertotal>${orderAmount.totalPrice }</pftest:ordertotal></td>
											</tr>
											<tr id="getCloseOrder">
											</tr>
										</table></td>
								</tr>
								<tr valign="top">
									<td height="71" align="left"><table width="80%" border="0"
											cellspacing="0" cellpadding="0">
											<!--tr valign="top">
                    <td width="23%" height="60px"><a  href="#"><b>查看所有保存推车</b></a></td>
                    <td width="11%"><a  href="#"><b>快速粘贴</b></a> </td>
                    <td width="66%"><input class="button" name="topUpdateCart2" value="更新推车" type="submit" />
                        <input name="_D:topUpdateCart2" value=" " type="hidden" /></td>
                  </tr-->
											<tr>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
											<tr>
												<td width="23%"><a>
													<img src="${ctx}/image/buy/btn_checkout.gif" name="continueToOrder" id="continueToOrder"
														title="登入至退出" /> </a> <br /> <br /> 
													<input id="continueShopping" class="button" name="continueShopping" value="继续选购" type="button" /></td>
												<td width="11%">&nbsp;</td>
												<td>&nbsp;</td>
											</tr>
										</table></td>
								</tr>
							</table></form>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
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
