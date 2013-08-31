<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<!---------左侧菜单-------------->
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




<link rel="stylesheet" href="css/style.css" type="text/css" />
<script src="${ctx}/script/jquery.js"></script>
<script src="${ctx}/script/block.js"></script>
<script>
	$(document)
			.ready(
					function() {
							$("#creditInfo").click(
								function(){
									$(".top_h4").text("积分管理");
									var node = $("#dialog");
									$("#dialogContent").load("/frontend/getCreditInfo");
									$.blockUI({ message: node, css: { top: '20%' } });
									$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
									$('.close').click($.unblockUI);
								}
							);
							
							$("#personalInfo").click(
									function(){
										$(".top_h4").text("个人信息管理");
										var node = $("#dialog");
										$("#dialogContent").load("/frontend/personalInfo");
										$.blockUI({ message: node, css: { top: '8%' } });
										$('.blockOverlay').attr('title','单击关闭').click($.unblockUI);
										$('.close').click($.unblockUI);
									}
								);
							
							
							
							
				});
</script>
</head>

<body>
<jsp:include page="top.jsp" flush="true"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td  width="10%" align="right" valign="top" bgcolor="f0f0f2"><img src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
    <td width="226" rowspan="2" align="right" valign="top"><img src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
        <ul>
          <br />
          <jsp:include page="leftMenu.jsp" flush="true"/>
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
    <jsp:include page="searchTop.jsp" />
      <table width="100%" border="0">
        <tr>
          <td width="3%" rowspan="2">&nbsp;</td>
          <td width="97%" rowspan="2">
		  <!---table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image:url(image/search/data-02.jpg); background-position:center; background-repeat:repeat-x">
            <tr>
              <td width="7%"><img src="image/search/data.jpg" width="73" height="83" align="left" /></td>
              <td width="69%" style="font-family:Arial, Helvetica, sans-serif; color:#333; font-size:12px"><p><b><br />
                查询列表</b></p>
                <p style="font-family:Arial, Helvetica, sans-serif; color:#333; font-size:12px">查询<span style=" font-size:18px; color:#FF6600"> 13131313 </span>结果</p></td>
              <td width="23%" align="right" valign="top" style="padding-top:25px"><table width="68%" border="0" cellpadding="0" cellspacing="0">
                <tr align="center">
                  <td width="17%" height="29" align="center"><img src="image/menu/p2.jpg" width="17" height="17" align="left" /></td>
                  <td width="83%" height="29" colspan="2" align="left" style="font-size:20px; color:#FF6600"><strong>(86) 21-5437 0611</strong></td>
                </tr>
              </table></td>
              <td width="1%" align="right"><img src="image/search/data-03.jpg" width="13" height="83" /></td>
            </tr>
          </table-->		  <br />
            <table width="95%" cellpadding="15">
              <tr>
                <td height="36" colspan="2" align="left"><h1 style="font-size:18px; color:#FF9900">欢迎&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的账户已建立</h1>
                <hr /></td>
              </tr>
              <tr valign="top">
                <td height="580" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="6">
                  <!--tr>
                    <td width="11%">联络信息</td>
                    <td width="89%"><input type="text" name="textfield" /></td>
                  </tr-->
                  <tr>
                    <td>名字</td>
                    <td><input type="text" name="textfield5" value="${user.loginName}"/></td>
                  </tr>
                  <tr>
                    <td>邮箱</td>
                    <td><input type="text" name="textfield6" value="${user.email}"/></td>
                  </tr>
                  <tr>
                    <td>电话</td>
                    <td><input type="text" name="textfield7" value="${user.mobile}"/></td>
                  </tr>
                </table>                  
                  <hr />
                  <p style="color:#FF6600" ><b>关于您的个人资料</b></p>
                  <p> 当艾矽电子需要核实您的身份时，会要求您提供个人资料。当您下订单、索取目录或订阅电子邮件时，我们会要求您提供这些资料。</p>
                  <p style="color:#FF6600" ><b>个人资料的用途如下：</b>
                  <ol>
                    <li> 协助您快速找到所需的服务或资讯。</li>
                    <li> 为您提供最具相关性的内容。</li>
                    <li> 向您通知优惠促销、最新服务或产品等内容。</li>
                    <li> 在出现错误时与您联络，协助解决问题。 </li>
                  </ol>                 
				   
				  
				  我们会妥善保存您的个人资料，防止资料遗失、遭到滥用、泄露、篡改、毁坏或未经授权存取。 </p></td>
                <td width="49%" align="left" bgcolor=""><table width="291" height="142" cellpadding="3" style="background-image:url(${ctx}/image/account/boxbg.jpg); ">
                  <tbody>
                    <tr>
                      <td width="11" class="prodDetailAvailability">&nbsp;</td>
                      <td width="174" class="prodDetailAvailability"><b>我的资料</b></td>
                      <td width="78" rowspan="4" align="center" valign="top" style="padding-top:10px;"><img src="${ctx}/image/account/avatar.jpg" width="58" height="58" /></td>
                    </tr>
                    <tr>
                      <td style="color:#666666">&nbsp;</td>
                      <td style="color:#666666">个人信息管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="personalInfo" href="#" style="color:#FF6600">&gt;&gt; 编辑</a> <br />
                        密码管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="changePassword" href="#" style="color:#FF6600">&gt;&gt; 编辑</a>
                        <hr />
						<!--//-资料-打开窗口--->
<script>
function msg(info){
var p=document.createElement("DIV");
if (!info) var info='<iframe height="903" name="neepage" width="808" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="info/info-o.html" alt="" />&nbsp;</iframe>';
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
</script>			

<!--------------------------------------------------2------------------------------------------------------------>

<script>
function msg2(info){
var p=document.createElement("DIV");
if (!info) var info='<iframe height="903" name="neepage" width="808" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="info/info-o2.html" alt="" />&nbsp;</iframe>';
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
</script>		

<!--------------------------------------------------3------------------------------------------------------------>

<script>
function msg3(info){
var p=document.createElement("DIV");
if (!info) var info='<iframe height="903" name="neepage" width="808" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="info/info-o3.html" alt="" />&nbsp;</iframe>';
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
</script>			</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td><b>积分管理</b></td>
                    </tr>
                    <tr>
                      <td style="color:#666666">&nbsp;</td>
                      <td style="color:#666666"> 积分查看&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="creditInfo" href="#" style="color:#FF6600">&gt;&gt; 点击查看</a></td>
                    </tr>
                  </tbody>
                </table>
                  <p>&nbsp;</p>
                  <table width="288" height="224" cellpadding="0" style="background-image:url(${ctx}/image/account/boxbg2.jpg); ">
                    <tbody>
                      <tr>
                        <td width="11" height="30" class="prodDetailAvailability">&nbsp;</td>
                        <td width="240" class="prodDetailAvailability">&nbsp;</td>
                        <td width="12" rowspan="4" align="center" valign="top" style="padding-top:10px;">&nbsp;</td>
                      </tr>
                      <tr>
                        <td style="color:#666666">&nbsp;</td>
                        <td style="color:#666666">
                        	<a href="/frontend/getFavorite?pageNo=1"><img src="${ctx}/image/account/favorite-2.jpg" width="203" height="73" /></a></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td><hr /></td>
                      </tr>
                      <tr>
                        <td style="color:#666666">&nbsp;</td>
                        <td style="color:#666666"><a href="/frontend/getShoppingCart?pageNo=1"><img src="${ctx}/image/account/b-2.jpg" width="203" height="74" /></a></td>
                      </tr>
                    </tbody>
                  </table>
                <p>&nbsp;</p></td>
              </tr>
          </table></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="167">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td width="10%">&nbsp;</td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/bottom/bottom-line.jpg); background-repeat:repeat-x;">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<jsp:include page="buttom.jsp" />
</body>
</html>
