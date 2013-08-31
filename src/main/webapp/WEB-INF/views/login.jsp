<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0063)https://cn.element14.com/jsp/profile/register.jsp -->
<HTML lang=zh class="zh_CN tableLayout" xmlns:pftest = 
"urn:x-prefix:pftest"><HEAD><TITLE class=mfPageTitle>注册 | 登录</TITLE><!-- alternate tracking for bespoke page --><!-- conversion tracking --><!-- *** google conversion: login-registration -->
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<META name=google-site-verification 
content=7cKM3EFlTszqLnDxOf2M8r5rrtr4WEqGeX4oe99ztPk>
<META content=IE=EmulateIE7 http-equiv=X-UA-Compatible>
<META name=description 
content=eluomeng-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx。>
<META name=keywords content="">
<META name=author content="">
<META name=robots content="index, follow">
<META name=revisit-after content="">
<META name=version content=R30.78.107>
<LINK rel=" " href=""><!-- -->
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<script src="${ctx}/script/jquery.js"></script>
<script src="${ctx}/script/block.js"></script>
<SCRIPT type=text/javascript>

$(document)
.ready(
		function() {
		$("#registerStr").click(
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
		
});
</SCRIPT>


<LINK rel=stylesheet type=text/css href="${ctx}/login_files/e14eap3colv2_t.css"><!-- No canonical URL set -->
<META name=GENERATOR content=""></HEAD>
<BODY>
<form action="login">
<TABLE border=0 cellSpacing=0 cellPadding=0 width="310">
  <TBODY>
  <TD width="14">&nbsp;</TD>
      <TD height=35 colspan="2"><H1 class=loginGrayTitle>登陆</H1></TD>
  </TR>
  <TR>
    <TD class=heading colSpan=3></TD>
  </TR>
  <!-- PTS: PRJ50473-EC [19/06/08] START
Import Bean
-->
  <!-- PTS: PRJ50473-EC [19/06/08] END -->
  <TR>
    <TD>*</TD>
    <TD colspan="2"><LABEL for=login>用户名</LABEL></TD>
  </TR>
  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="2"><INPUT id=userName class=textField name="userName">
        <INPUT name=_D:login 
                              value=" " type=hidden>    </TD>
  </TR>
  <TR>
    <TD>*</TD>
    <TD colspan="2"><LABEL for=password>密码</LABEL></TD>
  </TR>
  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="2"><INPUT id=password class=textField 
                              name="password" 
                              type=password>
        </TD>
  </TR>
  <TR>
    <TD>&nbsp;</TD>
    <TD colspan="2"><DIV id=helpContain2 class=helpContain>
      <LABEL 
                              class="chkbox noCase">
      <INPUT id=rememberMe 
                              class=chkbox 
                              onclick="checkForCookies(this.value,'激活记住我，必须开启Cookies。如需了解如何开启Cookies，请参考帮助页面。','loginfragment');" 
                              name="rememberMe"
                              value=true type=checkbox>
      保存登陆信息
 <!---A href="javascript:void(0);">(共享计算机?) 
	  SPAN class=pop <B>保存我的信息 :</B>
      <UL>
        <LI>可保存您的用户名，使您不必每次登录时都需要重新输入
        <LI>可在您的交易期截止的情况下，保存您的购物篮中的内容
        <LI>我们建议如果您使用的是共享计算机，请勿使用“保存我的信息”选项，否则他人可能篡改您购物篮中的内容</LI>
      </UL-->
      </LABEL>
    </DIV></TD>
  </TR>
  
  <TR>
    <TD height="46">&nbsp;</TD>
    <TD width="202"  align=right><a href="#" id="registerStr">注 册</a><a class="changePwd findPassword"><b>忘记密码</b></a><input class=noBorder onClick="setRememberMeCookie('loginfragment', 90)" border=0 name=/atg/userprofiling/ProfileFormHandler.login alt=输入您的用户名和密码 align=absMiddle value=login src="${ctx}/login_files/btn_login.gif" type=image >
        <INPUT name=_D:/atg/userprofiling/ProfileFormHandler.login value=" " type=hidden>    </TD>
    <TD width="94" align=LEFT ><!--A class=btn_link_content href="#">忘记密码？</A---> </TD>
  </TR>
  <TR  id="pageFooter" class="footerBlock">
    <TD height="12" colSpan=3>&nbsp;</TD>
  </TR>
  <TR  id="pageFooter" class="footerBlock">
    <TD  style="font-size:14px; color:#FF6600">&nbsp;</TD>
    <TD  style="font-size:14px; color:#FF6600">热线: <SPAN class=contact>(86) 21-5437 0611 <br>
        <A href="">联系我们</A></SPAN> </TD>
    <TD  style="font-size:14px; color:#FF6600">&nbsp;</TD>
  </TR>
</TABLE>
<div id="dialog" style="display:none">
	<h4 class="top_h4">登录</h4>
	<a href="#" class="close" title="关闭">close</a>
	<div id="dialogContent"></div>
</div>
</form>
<!-- TL: 1 * 131.0 ms = 131 ms --><!-- *** google tracking --><!-- GWO - AB tests tracking --><!-- *** google control: empty --><!-- alternate tracking for bespoke page --><!-- *** google bespoke: empty --><!-- *** google conversion --><!-- Iperceptions code was added for BAU 44813 --><!-- Google conversion tracking is not enabled for locale zh_CN -->
</BODY>
</HTML>
