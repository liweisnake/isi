<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>填写核对订单</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script type="text/javascript" src="${ctx}/script/jquery.js"></script>
<script>
	$(document).ready(function() {
		
		var radio = document.getElementsByName("invoiceType");
		radio[0].checked=true;
		
		$("#submitLink").click(function() {
			
			if($("#addressDetail").val() == ""){
				alert("请输入详细地址");
				return;
			}
			
			if($("#receiver").val() == ""){
				alert("请输入收货人姓名");
				return;
			}
			
			if(($("#mobile").val() == "") && (($("#regionNo").val() == "") || ($("#telephone").val() == ""))){
				alert("请输入手机号或固定电话");
				return;
			}
			
			$("#orderInfoForm").submit();
		});
	});
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
				<br />
				<form id="orderInfoForm" action="/frontend/submitOrder" method="post">
				<table width="880" border="0">
					<tr>
						<td width="3%" rowspan="2">&nbsp;</td>
						<td colspan="2" rowspan="2">
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
									<td height="47" style="background-image: url(${ctx}/image/buy/1-over.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px; color: #666666"><b>&nbsp;我的购物车</b></td>
									<td style="background-image: url(${ctx}/image/buy/2.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px"><b>&nbsp;填写核对订单</b></td>
									<td style="background-image: url(${ctx}/image/buy/3-off.jpg); background-position: center right; background-repeat: no-repeat; BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; font-size: 18px; color: #666666"><b>&nbsp;订单提交成功</b></td>
								</tr>
							</table>
							<p>&nbsp;</p>
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="9%" rowspan="7" align="center" valign="top" bgcolor="#FFE6D9"
										style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid;">收货信息</td>
									<td align="center" style="BORDER-top: #FF6600 1px solid;">地址标注：</td>
									<td style="BORDER-top: #FF6600 1px solid; border-right: #FF6600 1px solid; color: #999999;">
										<input type="text" name="addressLabel" /> 例如家里、公司，最多四个字。</td>
								</tr>
								<!-- 
								<tr>
									<td width="6%" align="center">*&nbsp;选择地区：</td>
									<td width="77%" style="border-right: #FF6600 1px solid">
										<select name="region1" style="width: 150px; color: #FFCD9B"><option>---All---</option></select>
										<select name="region2" style="color: #FFCD9B"><option>---All---</option></select>
										<select name="region3" style="color: #FFCD9B"><option>---All---</option></select>
										标**为支持货到付款</td>
								</tr>
								 -->
								<tr>
									<td align="center">*&nbsp;详细地址：</td>
									<td width="77%" style="border-right: #FF6600 1px solid">
										<input id="addressDetail" name="addressDetail" type="text" size="50" /></td>
								</tr>
								<tr>
									<td align="center">邮政编码：</td>
									<td width="77%" style="border-right: #FF6600 1px solid">
										<input name="zipCode" type="text" size="20" /></td>
								</tr>
								<tr>
									<td align="center">*&nbsp;收&nbsp;&nbsp;货&nbsp;&nbsp;人：</td>
									<td width="77%" style="border-right: #FF6600 1px solid">
										<input id="receiver" name="receiver" type="text" size="20" /></td>
								</tr>
								<tr>
									<td align="center" style="BORDER-bottom: #FF6600 1px solid; ">*&nbsp;手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
									<td width="77%" style="border-right: #FF6600 1px solid;BORDER-bottom: #FF6600 1px solid; ">
										<input id="mobile" name="mobile" type="text" value="" size="20" style="color: #FFCD9B" /> 或固定电话 
										<input id="regionNo" name="regionNo" type="text" value="" size="10" style="color: #FFCD9B" /> -
										<input id="telephone" name="telephone" type="text" value="" size="20" style="color: #FFCD9B" /> - 
										<input id="extensionNo" name="extensionNo" type="text" value="" size="20" style="color: #FFCD9B" />
										两者至少填写一项</td>
								</tr>
								<!-- 
								<tr>
									<td align="center" style="BORDER-bottom: #FF6600 1px solid;">
										<table border="0" cellspacing="0" cellpadding="0" class="confirmation">
											<tr>
												<td align="center"><a>确认收货信息</a></td>
											</tr>
										</table></td>
									<td width="77%" style="border-bottom: #FF6600 1px solid; border-right: #FF6600 1px solid">&nbsp;</td>
								</tr>
								 -->
							</table> <br />
						</td>
						<td width="5%">&nbsp;</td>
					</tr>
					<tr>
						<td height="167">&nbsp;</td>
					</tr>
					<tr>
						<td height="20">&nbsp;</td>
						<td colspan="2">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="33">&nbsp;</td>
						<td colspan="2">
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="10%" rowspan="4" align="center" valign="top" bgcolor="#FFE6D9" style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid;">配送方式</td>
									<td width="8%" align="center" style="BORDER-top: #FF6600 1px solid;BORDER-bottom: #FF6600 1px solid; ">
										<input type="checkbox" name="expressDelivery" value="快递" /> 快递：</td>
									<td width="6%" style="BORDER-top: #FF6600 1px solid;BORDER-bottom: #FF6600 1px solid; ">&nbsp;</td>
									<td width="75%" style="BORDER-top: #FF6600 1px solid; border-right: #FF6600 1px solid;BORDER-bottom: #FF6600 1px solid; ">&nbsp;</td>
								</tr>
								<!--tr>
              <td align="center" style="BORDER-top:#FF6600 1px solid;">易迅快递：</td>
              <td style="BORDER-top:#FF6600 1px solid;">免费</td>
              <td style="BORDER-top:#FF6600 1px solid; border-right:#FF6600 1px solid;"> 支持货到付款及POS机刷卡（<a href="" target="_blank">查看配送区域及说明</a>）送货时间：
                <select name="shipDate" shipid="1" subid="1">
                    <option value="">请选择收货日期</option>
                    <option value="20120916">2012-09-16 星期日</option>
                    <option value="20120917">2012-09-17 星期一</option>
                    <option value="20120918">2012-09-18 星期二</option>
                  </select>
                  <select name="shipTime" shipid="1" subid="1">
                    <option value="">请选择收货时间</option>
                  </select>
              </td>
              </tr-->
								<!--tr>
              <td colspan="3" style="border-right:#FF6600 1px solid;"><input type="checkbox" name="sign_by_other" value="1" checked="checked" />
                收货时若易迅网快递无法联系上我，我同意由朋友、同事或家人代收 </td>
              </tr-->
								<!-- 
								<tr>
									<td colspan="3" style="border-right: #FF6600 1px solid; border-bottom: #FF6600 1px solid;">

										<table border="0" cellspacing="0" cellpadding="0" class="confirmation">
											<tr>
												<td align="center"><a>确认配送方式</a></td>
											</tr>
										</table>
									</td>
								</tr>
								 -->
							</table> <br /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td width="26%">&nbsp;</td>
						<td width="66%">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2">
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="10%" rowspan="2" align="center" bgcolor="#FFE6D9" style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid;">支付方式</td>
									<td width="12%" style="BORDER-top: #FF6600 1px solid;">
										<span style="border-right: #FF6600 1px solid;"> 
											<input type="radio" name="payMethod" value="银行转帐" />
										</span>银行转帐：</td>
									<td width="78%"
										style="BORDER-top: #FF6600 1px solid; border-right: #FF6600 1px solid;">（<a
										href="/landing/payments.html">查看银行信息</a>）
									</td>
								</tr>
								<tr>
									<td style="border-bottom: #FF6600 1px solid;">
										<input type="radio" name="payMethod" value="支付宝" checked /> 支付宝</td>
									<td style="border-right: #FF6600 1px solid; border-bottom: #FF6600 1px solid;">
										（如您选择支付宝方式，我们将在和您确认订单信息后向您发送支付宝付款链接）</td>
								</tr>

							</table></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2">
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="10%" align="center" valign="top" bgcolor="#FFE6D9" style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid;">发票信息</td>
									<td colspan="2" style="BORDER-top: #FF6600 1px solid; BORDER-right: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid;">
										<table width="100%">
											<tr iid="41646395" t="">
												<td colspan="2">发票抬头： 
													<input name="invoiceTitle" type="text" size="50" /></td>
											</tr>
											<tr iid="41439066" t="">
												<td colspan="2"><p>纳税人识别号： 
													<input name="taxpayerNo" type="text" size="40" />
													</p></td>
											</tr>
											<tr iid="41439066" t="">
												<td colspan="2">地址： 
													<input name="invoiceAddress" type="text" size="54" /></td>
											</tr>
											<tr iid="41439066" t="">
												<td colspan="2">电话： 
													<input name="invoiceRegionNo" type="text" size="10" /> - 
													<input name="invoiceTelephone" type="text" size="10" /> - 
													<input name="invoiceExtensionNo" type="text" /></td>
											</tr>
											<tr iid="41439066" t="">
												<td colspan="2">开户银行： 
													<input name="bank" type="text" size="60" /></td>
											</tr>
											<tr t="showMore">
												<td colspan="2">账号： 
													<input name="account" type="text" size="60" /></td>
											</tr>
											<tr t="showMore">
												<td width="49%">*发票类型：
													<input type="radio" id="plainInvoice" name="invoiceType" value="增值税普通发票" /> 增值税普通发票 
													<input type="radio" id="professionalInvoice" name="invoiceType" value="增值税专业发票" /> 增值税专业发票
												</td>
												<td width="51%">
													<a href="#" onclick="return false" count="2"></a></td>
											</tr>
											<!--tr iid="-1">
                  <td rowspan="5" valign="top"><input type="radio" name="sel_invoice" value="-1" /></td>
                  <td>使用新发票</td>
                </tr>
                <tr iid="-1">
                  <td> 发票类型：
                    <input type="radio" name="radiobutton" value="radiobutton" />
                    商业零售发票 
                    <input type="radio" name="radiobutton" value="radiobutton" />
                    增值税专用发票</td>
                </tr>
                <tr iid="-1">
                  <td>*发票抬头：
                    <input type="radio" name="radiobutton" value="radiobutton" />
                    个人 
                    <input type="radio" name="radiobutton" value="radiobutton" />
                    单位</td>
                </tr>
                <tr iid="-1">
                  <td>*单位名称：
                    <input name="textfield3" type="text" size="50" /></td>
                </tr>
                <tr iid="-1">
                  <td bgcolor="#FF6600"  style="color:#E7E7D8">*发票相关信息将被系统自动打印到发票上，请勿填写与发票无关的信息。</td>
                </tr>
                <tfoot>
                  <tr>
                    <td colspan="2">发票内容：
                      <label>
                        <input type="radio" name="iv_content_check" value="0" checked="checked" />
                        商品明细</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="1" />
                          核心配件</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="2" />
                          办公用品</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="3" />
                          耗材</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="4" />
                          电脑外设</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="5" />
                          电脑整机</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="6" />
                          附件</label>
                        <label>
                        <input type="radio" name="iv_content_check" value="7" />
                          精品家电</label></td>
                  </tr--->
											</tfoot>
										</table>
										<!-- 
										<table border="0" cellspacing="0" cellpadding="0" class="confirmation">
											<tr>
												<td align="center"><a>确认发票信息</a></td>
											</tr>
										</table>
										 -->
										 </td>
								</tr>

							</table></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2">
							<table width="100%" cellpadding="5" cellspacing="0">
								<tr>
									<td width="10%" align="center" valign="top" bgcolor="#FFE6D9" style="BORDER-top: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid; border-left: #FF6600 1px solid;">购物清单</td>
									<td colspan="2" style="BORDER-top: #FF6600 1px solid; BORDER-right: #FF6600 1px solid; BORDER-bottom: #FF6600 1px solid;">
										<table width="100%">
											<tr valign="top">
												<td height="208" colspan="2" align="left">
													<table width="100%" cellpadding="0" cellspacing="0" class="tableBorderSort">
														<tr bgcolor="#F0F0F0" height="">
															<td colspan="2">
																<!--a onclick="window.print();" href="javascript:void(0)">&nbsp;&nbsp;<b><img class="ex_vaM" src="search/btn_print.gif" /> 打印推车</b></a-->
																&nbsp;
															</td>
															<td class="colheads">&nbsp;</td>
															<td class="colheads">&nbsp;</td>
															<td class="colheads">&nbsp;</td>
															<td class="colheads">&nbsp;</td>
															<td class="colheads">&nbsp;</td>
															<td align="right" class="colheads">&nbsp;</td>
															<td class="colheads">&nbsp;</td>
														</tr>
														<tr>
															<td width="4%" align="center" bgcolor="#FFD9B3">项目编号
															</td>
															<td width="24%" align="center" bgcolor="#FFD9B3" class="colheads">库存编号</td>
															<td width="5%" align="center" bgcolor="#FFD9B3" class="colheads">数量</td>
															<td width="10%" align="center" bgcolor="#FFD9B3" class="colheads">制造商编号</td>
															<td width="11%" align="center" bgcolor="#FFD9B3" class="colheads">产品描述</td>
															<td width="7%" align="center" bgcolor="#FFD9B3" class="colheads">RoHS</td>
															<td width="9%" align="center" bgcolor="#FFD9B3" class="colheads">库存状况</td>
															<td width="10%" align="center" bgcolor="#FFD9B3" class="colheads">单位价格<br /> (不含税)</td>
															<td width="8%" align="center" bgcolor="#FFD9B3" class="colheads">总价</td>
														</tr>
														<pftest:commerceitems>
															<tr class=" printHide">
																<td align="center">&nbsp;</td>
																<td>&nbsp;</td>
																<td align="center">&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<c:forEach items="${shoppingList}" var="item" varStatus="rowCounter">
																<c:choose>
														        	<c:when test="${rowCounter.count % 2 == 0}">
														            	<c:set var="rowStyle" scope="page" value=""/>
														          	</c:when>
														          	<c:otherwise>
														            	<c:set var="rowStyle" scope="page" value="#CCCCCC"/>
														          	</c:otherwise>
														        </c:choose>
																<tr height="30px" bgcolor="${rowStyle}">
																	<td align="center">${rowCounter.count}<input type="hidden" name="shoppingCartId${rowCounter.count }" value="${item.shoppingCartId }"/></td>
																	<td><a href="/frontend/getProductInfo?shoppingCartId=${item.shoppingCartId }">${item.iicSku }</a></td>
																	<td align="center">${item.intentNum }</td>
																	<td>${item.mfrSku }</td>
																	<td>${item.description }</td>
																	<td align="center">${item.rohsStatus }</td>
																	<td align="center">${item.store }</td>
																	<td align="center">${item.onePrice }</td>
																	<td align="center">${item.totalPrice }</td>
																</tr>
															</c:forEach>
														</pftest:commerceitems>
														<tr height="30px" bgcolor="#CCCCCC">
															<td colspan="2" align="right">&nbsp;</td>
															<td align="center">&nbsp;</td>
															<td align="center">&nbsp;</td>
															<td class="printHide" escapexml="yes">&nbsp;</td>
															<td class="printHide" escapexml="yes">&nbsp;</td>
															<td class="printHide" escapexml="yes">&nbsp;</td>
															<td align="right" class="printHide" escapexml="yes">&nbsp;</td>
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
												<td width="52%">&nbsp;</td>
												<td width="48%" rowspan="2" align="right">
													<table style="MARGIN-TOP: 15px" border="0" cellspacing="0" cellpadding="0" width="100%">
														<tr>
															<td class="brandColor" align="right">税前价格合计：CNY &nbsp;</td>
															<td class="brandColor" align="right"><pftest:listpricetotal>${orderAmount.preTaxPrice } </pftest:listpricetotal>
															</td>
														</tr>
														<tr>
															<td class="brandColor" width="81%" align="right">增值税：CNY &nbsp;
															</td>
															<td class="brandColor" width="19%" align="right"><pftest:listpricetotal>${orderAmount.valueAddedTax } </pftest:listpricetotal>
															</td>
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
														<tr>
															<td align="right">运费：CNY &nbsp;</td>
															<td nowrap="nowrap" align="right"><pftest:shippingcosts>${orderAmount.freight } </pftest:shippingcosts></td>
														</tr>
														<tr class="subtotalRow">
															<td class="brandColor" align="right">总计：CNY &nbsp;</td>
															<td class="brandColor mfOrderTotal" nowrap="nowrap"
																align="right"><pftest:ordertotal>${orderAmount.totalPrice } </pftest:ordertotal>
															</td>
														</tr>
														<tr id="getCloseOrder">
														</tr>
													</table>
													<table border="0" cellspacing="0" cellpadding="0"
														class="confirmation">
														<tr>
															<td align="center"><a id="submitLink"><h3>提交订单</h3></a></td>
														</tr>
													</table></td>
											</tr>
											<tr valign="top">
												<td height="71" align="left">&nbsp;</td>
											</tr>
										</table> <!--table width="100%">
                <tr bgcolor="#FFE6D9">
                  <th>购物车中的商品</th>
                  <th>易迅价</th>
                  <th>返现</th>
                  <th>送积分</th>
                  <th>购买数量</th>
                  <th>合计</th>
                </tr>
                <tr>
                  <td><a href="#" target="_blank">HDVISION 高清锐视 RT1185 高清播放器</a> <br />
                    商品编号：23-480-004&nbsp;&nbsp;&nbsp;&nbsp; </td>
                  <td align="center" style="color:#FF0000"><strong>￥299.00</strong></td>
                  <td align="center">￥0.00</td>
                  <td align="center">0</td>
                  <td align="center">1</td>
                  <td align="center"  style="color:#FF0000"><strong>￥1196.00</strong></td>
                </tr>
              </table--> <!--table border="0" cellspacing="0" cellpadding="0" class="confirmation">
                    <tr>
                      <td align="center"><a>确认发票信息</a></td>
                    </tr>
                </table--->
									</td>
								</tr>
							</table></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table></form>
			</td>
		</tr>
		<tr>
			<td bgcolor="f0f0f2">&nbsp;</td>
		</tr>
	</table>
	<jsp:include page="../buttom.jsp" flush="true" />
</body>
</html>
