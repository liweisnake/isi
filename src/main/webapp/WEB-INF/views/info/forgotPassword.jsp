<%@ page contentType="text/html;charset=UTF-8"%>
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
<SCRIPT type=text/javascript>
function checkLocale() {
var urlLocaleChanged = '';
var cookieValue = getCookie("userSelectedLocale");
if (cookieValue == 'English' && document.getElementById('changeDisplayLocaleSubmit') && urlLocaleChanged == '') {
document.getElementById('changeDisplayLocaleSubmit').click();
}
}
addOnloadEvent(checkLocale);
</SCRIPT>

<SCRIPT type=text/javascript 
src="login_files/protoaculous.1.8.1.min_PF_106763.js"></SCRIPT>

<SCRIPT type=text/javascript src="login_files/application_240108.js"></SCRIPT>

<SCRIPT type=text/javascript src="login_files/globalv3.js"></SCRIPT>
<LINK rel=stylesheet type=text/css href="login_files/e14eap3colv2_t.css"><!-- No canonical URL set -->
<META name=GENERATOR content=""></HEAD>
<BODY>
<NOSCRIPT></NOSCRIPT>
<SCRIPT type=text/javascript>
AjaxCancelPolicy.register(/SuggestionLookupServlet/);
AjaxCancelPolicy.register(/parametrics\.form/);
</SCRIPT>
<!-- SiteCatalyst code version: H.11.
Copyright 1997-2007 Omniture, Inc. More info available at
http://www.omniture.com -->
<SCRIPT type=text/javascript>
var s_account="PFCNFarnell";
</SCRIPT>

<SCRIPT type=text/javascript src="login_files/s_code_E14_H_13.js"></SCRIPT>

<SCRIPT type=text/javascript><!--
/* You may give each page an identifying name, server, and channel on
the next lines. */
s.pageName="zh_CN:Login:Registration"
s.server="cn.element14.com"
s.channel="Login"
s.pageType=""
s.prop1="" // Type of search
s.prop2="" // Search term
s.prop3="" // Number of results
s.prop4="" // Campaign ID
s.prop5="" // Search breadcrumbs
s.prop6="" // Stock availability
s.prop7="" //cross sell product viewed
s.prop8="" // home zone 3
s.prop11="" // number of datasheets returned
/* E-commerce Variables */
s.campaign="" // Campaign ID
s.state="" // County / state
s.zip="" // Postcode / ZIP code
s.events="event1" // Event type
//omniture code for new successful registrations
s.products="" // Products array
s.purchaseID=""
s.eVar1="" // Internal finding method
s.eVar2=""
s.eVar3=""
s.eVar4=""
s.eVar5=""
s.eVar6="" // Profile ID
s.eVar7=""
s.eVar8="" // Shipping Method
s.eVar9="Logged Out" // Login status
s.eVar10="" // Job Function
s.eVar11="" // Purchase Role
s.eVar12="" // Stock availability
s.eVar13="" // ATG Order Reference
s.eVar14="" // Express Checkout revenue event
s.eVar23=""
s.linkInternalFilters="javascript:,cn.element14.com"
s.visitorMigrationKey="4D813A65"
s.visitorMigrationServer="metrics.farnell.com"
s.visitorMigrationServerSecure="smetrics.farnell.com"
/************* DO NOT ALTER ANYTHING BELOW THIS LINE ! **************/
var s_code=s.t();if(s_code)document.write(s_code)//--></SCRIPT>

<SCRIPT language=JavaScript><!--
if(navigator.appVersion.indexOf('MSIE')>=0)document.write(unescape('%3C')+'\!-'+'-')
//--></SCRIPT>
<!-- End SiteCatalyst code version: H.11. --><!-- Leadex Enecto ProspectFinder code for Europe -->&nbsp; 
<!-- Enecto ProspectFinder disabled in this environment --><!-- End Enecto ProspectFinder --><!-- Start Demandbase Tracking Codes -->&nbsp; 
<!-- Demandbase Tracking Codes disabled in this environment --><!-- End Demandbase Tracking Codes --><!-- BAU 45021 for Live Person proactive chat -->&nbsp; 
<TABLE border=0 cellSpacing=0 cellPadding=0 width="310">
  <TBODY>
  <TD width="14">&nbsp;</TD>
      <TD width="296" height=35><H1 class=loginGrayTitle style="color:#FF6600">
      <c:choose>
                  <c:when test="${isSuccess == true}">
		密码修复确认
	  </c:when>
	  <c:otherwise>密码修复失败
	  </c:otherwise>
	</c:choose>
      
      </H1></TD>
  </TR>
  <TR>
    <TD class=heading colSpan=2></TD>
  </TR>
  <!-- PTS: PRJ50473-EC [19/06/08] START
Import Bean
-->
  <!-- PTS: PRJ50473-EC [19/06/08] END -->
  <TR>
    <TD>&nbsp;</TD>
    <TD><DIV id=helpContain2 class=helpContain>
      <LABEL 
                              class="chkbox noCase"><B><br>
                              <c:choose>
                              <c:when test="${isSuccess == true}">
        						感谢您提交信息，您将立即收到一封包含新密码的邮件。
        					  </c:when>
        					  <c:otherwise>邮箱地址验证错误
        					  </c:otherwise>
        					</c:choose>
        				</B> </LABEL>
    </DIV></TD>
  </TR>
  <TR  id="pageFooter" class="footerBlock">
    <TD height="53" colSpan=2>&nbsp;</TD>
  </TR>
  <TR  id="pageFooter" class="footerBlock">
    <TD colSpan=2><DIV class=hotLine>热线: <SPAN class=contact>(86) 21-21-5437 0611 | <A 
            href="https://cn.element14.com/jsp/bespoke/bespoke2.jsp?bespokepage=e14/zh_CN/custsrv/index.jsp">联系我们</A></SPAN> </DIV></TD>
  </TR>
</TABLE>
<!-- Live Person proactive chat disabled in this environment --><!-- End Live Person proactive chat --><!-- TL: 131 ms --><!-- TL: 1 * 131.0 ms = 131 ms --><!-- *** google tracking --><!-- GWO - AB tests tracking --><!-- *** google control: empty --><!-- alternate tracking for bespoke page --><!-- *** google bespoke: empty --><!-- *** google conversion --><!-- Iperceptions code was added for BAU 44813 --><!-- Google conversion tracking is not enabled for locale zh_CN --></BODY></HTML>
