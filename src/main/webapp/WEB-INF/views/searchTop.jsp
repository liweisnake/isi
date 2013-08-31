<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script src="${ctx}/script/jquery.js"></script>
<script src="${ctx}/script/block.js"></script>
<script src=" http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=3168919025" type="text/javascript" charset="utf-8"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#dialog").hide();
						
						$("#login").click(
								function() {
									$.post("/frontend/login", $("#loginForm")
											.serialize(), function(data) {
										window.location.href = "/frontend/userInfo";
									});
								});
						
						$("#logout").click(
								function() {
									$.get("/frontend/logout", function(data){
										window.location.href = "/frontend/showIndex";
									});
								});
						$(".registerStr").click(
								function(){
									$(".top_h4").text("注册");
									var node = $("#dialog");
									$("#dialogContent").load("/frontend/showRegister");
									$.blockUI({ message: node, css: { top: '10%' } });
									$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
									$('.close').click($.unblockUI);
								}
							);
						$(".findPassword").click(function(){
							$(".top_h4").text("找回密码");
							var node = $("#dialog");
							$("#dialogContent").load("/frontend/findPassword");
							$.blockUI({ message: node, css: { top: '5%' } });
							$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
							$('.close').click($.unblockUI);
						});
						$(".changePassword").click(function(){
							$(".top_h4").text("修改密码(所有带*号的为必填项)");
							var node = $("#dialog");
							$("#dialogContent").load("/frontend/changePassword");
							$.blockUI({ message: node, css: { top: '5%' } });
							$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
							$('.close').click($.unblockUI);
						});
						$("#fuzzySearch")
								.click(
										function() {
											var searchStr = document
													.getElementById("searchStr").value;
											$(".top_h4").text("正在搜索...");
											var node = $("#dialog");
											$.blockUI({ message: node, css: { top: '25%' } });
											$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
											$('.close').click($.unblockUI);
											window.location.href='/frontend/fuzzySearch?searchStr=' + searchStr;
										});
						
						function msg(info){
							var p=document.createElement("DIV");
							if (!info) var info='<iframe height="903" name="neepage" width="808" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="${ctx}/login/login-o.html" alt="" />&nbsp;</iframe>';
							p.id="p";
							p.style.position="absolute";
							p.style.width=document.body.scrollWidth;
							p.style.height=(document.body.offsetHeight>document.body.scrollHeight)?'100%':document.body.scrollHeight;
							p.style.zIndex='998';
							p.style.top='0px';
							  p.style.left='0%';
							p.style.backgroundColor="gray";
							p.style.opacity='0.5';
							p.style.filter="alpha(opacity=80)";
							document.body.appendChild(p);
							var p1=document.createElement("DIV");
							var top=parseInt(parseInt(document.body.scrollHeight)*0.25)+document.body.scrollTop;
							p1.style.position="absolute";
							p1.style.width="300px";
							p1.id="p1";
							var left=Math.ceil(((document.body.scrollWidth)-parseInt(p1.style.width.replace('px','')))/2)+document.body.scrollLeft;
							p1.style.height="400px";
							p1.style.zIndex='999';
							p1.style.top=top+'px';
							  p1.style.left=left+'px';
							p1.style.border="0px solid red";
							var html="";
							   html+="<center>"
							  html+="<div class='p3' style='height:1px;overflow:hidden;background:red;width:400px;border-left:1px solid red;border-right:1px solid red;'></div>"
							  html+="<div class='p2' style='height:1px;overflow:hidden;background:red;width:400px;border-left:1px solid red;border-right:1px solid red;'></div>"

							  html+="<div class='p1' style='height:40px;overflow:hidden;background:red;width:400px;border-left:1px solid red;border-right:1px solid red;color:#fff;font-size:9pt;font-weight:bold;text-align:left;'> ⊙ 提示<span style=' padding-left:320px;'></span><a href='javascript:this.cancle()' style=' color:#000000;font-size:20pt; padding-left:13px;  background-image:url(login/login_files/c2.jpg)'>&nbsp;</a> </div>"
							  
							html+="<div id='c' style='height:960px;width:400px;background-color:#fff; overflow:hidden;border-left:1px solid red;border-right:1px solid red;padding-top:5px;font-size:9pt; color:#000000'>"+info+"<br><br><br> <a href='javascript:this.cancle()' style=' color:#000000'>[关闭]</a> </div>"

							  html+="<div class='p1' style='height:1px;overflow:hidden;background:#FEEACB;width:400px;border-left:1px solid red;border-right:1px solid red;'></div>"
							  html+="<div class='p2' style='height:1px;overflow:hidden;background:#FEEACB;width:400px;border-left:1px solid red;border-right:1px solid red;'></div>"
							  html+="<div class='p3' style='height:1px;overflow:hidden;background:red;width:400px;border-left:1px solid red;border-right:1px solid red'></div>"
							  html+="</center>"
							  
							document.body.appendChild(p1);
							p1.innerHTML=html;
							var arr=document.getElementsByTagName("select");
							var i=0;
							while(i<arr.length){
							  arr[i].style.visibility='hidden';
							  i++;
							}
							this.cancle=function(){
							document.body.removeChild(document.getElementById('p'));
							document.body.removeChild(document.getElementById('p1'));
							var arr=document.getElementsByTagName("select");
							  var i=0;
							  while(i<arr.length){
							  arr[i].style.visibility='visible';
							  i++;
							  }
							}
							}
					});
</script>
<table width="800" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="41" colspan="2" align="center" class="search-top"><b>&nbsp;零件编号/关键字（请输入英文）</b></td>
		<td width="119" rowspan="2">&nbsp;</td>
		<c:choose>
			<c:when
				test="<%=(org.apache.shiro.SecurityUtils.getSubject()
							.getPrincipal() == null)%>">
				<td width="346" rowspan="2">
					<form id="loginForm" action="/frontend/login">
						<table height="57" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="168" height="45" valign="bottom"><input
									name="userName" value="" /></td>
								<td width="97" rowspan="2" class="login-on"><a id="login"
									class="login"><b>登 录</b></a>
									</td>
								<td width="150" class="register"><a
									class="login registerStr"><b>注 册</b></a><a class="changePwd findPassword"><b>忘记密码</b></a></td>
							</tr>
							<tr>
								<td height="46" valign="top"><input type="password"
									name="password" value="" /></td>
								<td><a href="https://api.weibo.com/oauth2/authorize?client_id=1261596743&redirect_uri=http://www.iic.net.cn/frontend/weibologin&response_type=code" class="bottom" id="weiboLogin"> <!--img src="${ctx}/image/menu/w4.jpg" width="45" height="39" /-->
										微博登录
								</a></td>
							</tr>
						</table>
					</form>
			</c:when>
			<c:otherwise>
				<td width="346" rowspan="2" valign="top">
					<table width="100%" height="18" border="0" cellpadding="0"
						cellspacing="0" bgcolor="#F29725">
						<tr align="center">
							<td width="100" height="18"><%=org.apache.shiro.SecurityUtils.getSubject()
							.getPrincipal()%>&nbsp; 您好</td>
							<td width="48"><a id="logout" style="color: #E7E7D8">[退出]</a></td>
						</tr>
					</table>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td width="199" height="73" align="right" class="search-left"><input
			id="searchStr" name="searchStr" type="text" size="20"
			value="${searchStr }" /></td>
		<td width="136" valign="top"><a id="fuzzySearch"><img
				src="${ctx}/image/search/search-00-bt.jpg" width="136" height="73" /></a></td>
	</tr>
</table>
<p>&nbsp;</p>
<div id="searchResult" ></div>
<div id="dialog" style="display:none">
	<h4 class="top_h4">登录</h4>
	<a href="#" class="close" title="关闭">close</a>
	<div id="dialogContent"></div>
</div>
