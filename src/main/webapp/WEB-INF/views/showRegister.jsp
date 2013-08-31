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
<script src="${ctx}/script/jquery.js"></script>
<script src="${ctx}/script/jquery.validate.js"></script>
<SCRIPT type=text/javascript>
$(document).ready(function () {
	$("#submitRegister").click(function(){
		$("#registerForm").validate({ 
			rules: {
				loginName: "required",				
			},
			messages: { 
				comment: "Please enter a comment." 
			} 
		});
		$.post("/register", $("#loginForm").serialize(), function(data) {
			window.location.href = "/frontend/showIndex";
		});
	});
	
});
</SCRIPT>

<SCRIPT type=text/javascript 
src="login_files/protoaculous.1.8.1.min_PF_106763.js"></SCRIPT>

<SCRIPT type=text/javascript src="login_files/application_240108.js"></SCRIPT>

<SCRIPT type=text/javascript src="login_files/globalv3.js"></SCRIPT>
<LINK rel=stylesheet type=text/css href="login_files/e14eap3colv2_t.css"><!-- No canonical URL set -->
<META name=GENERATOR content=""></HEAD>
<BODY>
<form id="registerForm" action="/register">
<TABLE border=0 cellSpacing=0 cellPadding=0>
  <TBODY>
    <TR>
      <TD width="11"></TD>
      <TD width="400" height=35 colSpan=2><H1 class=loginGrayTitle>注册</H1></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD class=registerExplain colSpan=2 
                          align=left></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><!-- PTS: PRJ50473-EC [19/06/08] START
Import Bean
-->
          <!-- PTS: PRJ50473-EC [19/06/08] END --></TD>
    </TR>
    <TR>
      <TD colSpan=3>&nbsp;</TD>
    </TR>
    
	
	<TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL 
                              for=username>用户名</LABEL>
          <LABEL 
                              class="chkbox noCase">(6-20个字符)</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=username class=textField 
                              name="LoginName" maxLength=20 size=40>
          <INPUT 
                              name=_D:username value=" " type=hidden></TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=password>密码</LABEL>
          <LABEL 
                              class="chkbox noCase">6-15个字符,注意区分大小写</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=password class=textField 
                              name=password maxLength=15 size=40 
                              type=password>
          <INPUT name=_D:password value=" " 
                              type=hidden></TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=confirmpassword>密码确认</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=confirmpassword 
                              class=textField name=confirmpassword maxLength=15 
                              size=40 type=password>
          <INPUT 
                              name=_D:confirmpassword value=" " 
                          type=hidden></TD>
    </TR>
    <!---TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=firstname>名</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=firstname class=textField 
                              name=firstname maxLength=40 size=40>
          <INPUT 
                              name=_D:firstname value=" " type=hidden></TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=lastname>姓</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=lastname class=textField 
                              name=lastname maxLength=40 size=40>
          <INPUT 
                              name=_D:lastname value=" " type=hidden></TD>
    </TR-->
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2>
	  <LABEL for=personalTitle>区域</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2>
        <SELECT id=region name=region>
            <OPTION selected value="0">﹍请选择﹍
          <OPTION value=1>华东
          <OPTION value=2>华南
          <OPTION value=3>华北
          <OPTION value=4>中西部</OPTION>
          </SELECT>		  </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2>
	  <LABEL for=personalTitle>客户类型</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2>
          <SELECT id=customType name=title>
            <OPTION selected value="0">﹍请选择﹍
            <OPTION value=1>贸易商
            <OPTION value=2>最终用户</OPTION>
          </SELECT>      </TD>
    </TR>
    
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2><LABEL for=company>公司名称</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=company class=textField 
                              name="company" maxLength=100 size=50>
          </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2><LABEL for=personalTitle>职能</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT name=_D:title value=" " type=hidden>
          </INPUT>
          <SELECT id=title name=title>
            <OPTION selected value="">﹍请选择﹍
            <OPTION value=DR>采购
            <OPTION value=PROF>研发
			<OPTION value=PROF>维护维修
			<OPTION value=PROF>项目管理
			<OPTION value=PROF>生产
			<OPTION value=PROF>其他			</OPTION>
          </SELECT>      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2><LABEL for=email>职务</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=position class=textField 
                              name="position" maxLength=100 size=60>
          </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL 
                            for=phonenumber>电话/手机</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=phonenumber 
                              class=textField name="phone" maxLength=20 
                              size=40>
      </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL 
                            for=phonenumber>Email</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id="email" 
                              class=textField name="email" maxLength=20 
                              size=40>
          </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2><LABEL 
                            for=qq>QQ</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id="qq" 
                              class=textField name="qq" maxLength=20 
                              size=40>
          </TD>
    </TR>
    
    <!--TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=email>邮箱地址</LABEL></TD>
    </TR>
    
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=email class=textField 
                              name=email maxLength=50 size=40>
          <INPUT 
                              name=_D:email value=" " type=hidden></TD>
    </TR-->
    
    <TR>
      <TD>&nbsp;</TD>
      <TD colSpan=2><LABEL for=address>地址</LABEL></TD>
    </TR>
    
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=address class=textField 
                              name="address" maxLength=100 size=50>
          </TD>
    </TR>
    
    <!----TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=jobFunction>您的主要工作职能是什么？</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT name=_D:jobFunction value=" " 
                              type=hidden>
          </INPUT>
        <SELECT id=jobFunction 
                              onchange="document.getElementById('secondaryFunc').style.display = this.value == 'OTHER' ? 'block':'none';" 
                              name=jobFunction>
            <OPTION selected 
                                value="">------请选择------
          <OPTION 
                                value=ADMIN_CLERK_EXPEDITOR>行政/文员
          <OPTION 
                                value=BUYER_PURCHASING>采购
          <OPTION 
                                value=ELECTRICIAN>电工，电器维护维修
          <OPTION 
                                value=ENGINEER>工程师
          <OPTION 
                                value=HOME_PERSONAL_HOBBYIST>家用/个人/爱好
          <OPTION 
                                value=OWNER_MANAGER>业主/经理
          <OPTION 
                                value=PROFESSOR_RESEARCHER_STUDENT>教授/研究员/学生
          <OPTION 
                                value=TECHNICIAN>技师
          <OPTION 
                                value=OTHER>其他</OPTION>
        </SELECT>
          <DIV style="DISPLAY: none" id=secondaryFunc>
            <INPUT 
                              class=input name="Job Function, 其他">
            <INPUT 
                              name="_D:Job Function, 其他" value=" " type=hidden>
        </DIV></TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD colSpan=2><LABEL for=purchaseRole>您所购买的产品主要用于哪方面？</LABEL></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT name=_D:purchaseRole 
                              value=" " type=hidden>
          </INPUT>
        <SELECT 
                              id=purchaseRole 
                              onchange="document.getElementById('secondaryDisc').style.display = this.value == 'OTHER' ? 'block':'none';" 
                              name=purchaseRole>
            <OPTION selected 
                                value="">------请选择------
          <OPTION 
                                value=FACILITY_MAINTENANCE_REPAIR_&amp;_FIELD_SERVICE>设备维护、维修及现场维修
          <OPTION 
                                value=PRODUCTION_&amp;_PLANT_OPERATIONS>生产及工厂运营
          <OPTION 
                                value=PRODUCT_DESIGN__RESEARCH_R&amp;D>产品设计/调查/研发
          <OPTION 
                                value=RESALE>转售
          <OPTION 
                                value=TEST_QUALITY_CONTROL>测试/质量控制
          <OPTION 
                                value=OTHER>其他</OPTION>
        </SELECT>
          <DIV id=secondaryDisc class=ch_disp_none>
            <INPUT 
                              class=input name="Job Discipline, 其他">
            <INPUT 
                              name="_D:Job Discipline, 其他" value=" " 
                              type=hidden>
        </DIV></TD>
    </TR>
    <TR>
      <TD><P></P></TD>
    </TR>
    <TR>
      <TD class=ch_a_right></TD>
      <TD><DIV style="DISPLAY: inline; FLOAT: left">
        <LABEL 
                              for=accNum>账号</LABEL>
      </DIV>
          <DIV id=helpContain2 class=helpContain>
            <LABEL 
                              class="chkbox noCase">&nbsp;&nbsp;<A 
                              href="javascript:void(0);">这是什么？<SPAN 
                              class=pop><STRONG>这不是在线注册必填项。</STRONG> 信用帐户需要申请。填入账号字段将帮助我们更有效地处理您的订单。</SPAN></A></LABEL>
          </DIV></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><INPUT id=accountno 
                              class=textFieldSmall 
                              name=/atg/userprofiling/RegistrationFormHandler.value.suggestedAccountNumber 
                              maxLength=11 size=11>
          <INPUT 
                              name=_D:/atg/userprofiling/RegistrationFormHandler.value.suggestedAccountNumber 
                              value=" " type=hidden></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2 align=left><TABLE width="100%">
        <TBODY>
          <TR>
            <TD vAlign=top width=10 align=left><SPAN 
                                class=registrationContentChkbox>
              <INPUT 
                                id=optOutEmail class=chkbox onclick=showeMail() 
                                name=eMailCheck value=true CHECKED 
                                type=checkbox>
              <INPUT name=_D:eMailCheck 
                                value=" " type=hidden>
            </SPAN></TD>
            <TD vAlign=top><LABEL 
                                class="chkbox noCase registrationContentChkbox" 
                                for=optOutEmail>通过邮件获取最新信息和产品更新</LABEL>            </TD>
          </TR>
        </TBODY>
      </TABLE></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2 align=left><DIV id=emaildiv>
        <TABLE width="100%">
          <TBODY>
            <TR>
              <TD vAlign=top width=10 align=left><SPAN 
                                class=registrationContentChkbox>
                <INPUT 
                                id=offerPriceDeals class=chkbox 
                                name=offerPriceDeals value=true CHECKED 
                                type=checkbox>
                <INPUT name=_D:offerPriceDeals 
                                value=" " type=hidden>
              </SPAN></TD>
              <TD><LABEL 
                                class="chkbox registrationContentChkbox" 
                                for=offerPriceDeals>报价及交易价格</LABEL>              </TD>
            </TR>
            <TR>
              <TD vAlign=top width=10 align=left><SPAN 
                                class=registrationContentChkbox>
                <INPUT 
                                id=marketIndustryInfo class=chkbox 
                                name=marketIndustryInfo value=true CHECKED 
                                type=checkbox>
                <INPUT name=_D:marketIndustryInfo 
                                value=" " type=hidden>
              </SPAN></TD>
              <TD><LABEL 
                                class="chkbox registrationContentChkbox" 
                                for=marketIndustryInfo>市场及产业信息</LABEL></TD>
            </TR>
            <TR>
              <TD vAlign=top width=10 align=left><SPAN 
                                class=registrationContentChkbox>
                <INPUT 
                                id=productStockingInfo class=chkbox 
                                name=productStockingInfo value=true CHECKED 
                                type=checkbox>
                <INPUT name=_D:productStockingInfo 
                                value=" " type=hidden>
              </SPAN></TD>
              <TD><LABEL 
                                class="chkbox registrationContentChkbox" 
                                for=productStockingInfo>产品及库存信息</LABEL>              </TD>
            </TR>
          </TBODY>
        </TABLE>
      </DIV></TD>
    </TR>
    <TR>
      <TD></TD>
      <TD colSpan=2><DIV style="FLOAT: left">
        <INPUT id=remember 
                              class=chkbox 
                              onclick="checkForCookies(this.value,'如需使用“记住我”，您必须使用Cookies','register');" 
                              name=rememberMeCheck value=true 
                              type=checkbox>
        <INPUT name=_D:rememberMeCheck 
                              value=" " type=hidden>
      </DIV>
          <DIV style="PADDING-LEFT: 18px" id=helpContain2 class=helpContain>
            <LABEL class="chkbox noCase">
            保存我的信息 <A  href="javascript:void(0);">(共享计算机?) </A><SPAN class=pop><B>保存我的信息 :</B></span>
            <UL>
              <LI>可保存您的用户名，使您不必每次登录时都需要重新输入
              <LI>可在您的交易期截止的情况下，保存您的购物篮中的内容
              <LI>我们建议如果您使用的是共享计算机，请勿使用“保存我的信息”选项，否则他人可能篡改您购物篮中的内容</LI>
            </UL>
            </LABEL>
          </DIV></TD>
    </TR----->
    <TR>
      <TD height="16"></TD>
      <TD colSpan=2 align=right>&nbsp;</TD>
    </TR>
    <TR>
      <TD rowspan="3"></TD>
      <TD colSpan=2 align=right><INPUT class=noBorder 
                              id="submitRegister"
                              border=0 
                              name=/atg/userprofiling/RegistrationFormHandler.create 
                              alt=提交您的细节 align=absMiddle value=register 
                              src="login_files/btn_register.gif" 
                              type=image>
          </TD>
    </TR>
    <TR>
      <TD class="footerBlock"  style="font-size:14px; color:#FF6600">&nbsp;</TD>
    </TR>
    <TR>
      <TD style="font-size:14px; color:#FF6600; padding-top:-100px">热线: <SPAN class=contact>(86) 21-5437 0611 <br>
          <A href="">联系我们</A></SPAN> </TD>
    </TR>
  </TBODY>
</TABLE>
</form>
<!-- Live Person proactive chat disabled in this environment --><!-- End Live Person proactive chat --><!-- TL: 131 ms --><!-- TL: 1 * 131.0 ms = 131 ms --><!-- *** google tracking --><!-- GWO - AB tests tracking --><!-- *** google control: empty --><!-- alternate tracking for bespoke page --><!-- *** google bespoke: empty --><!-- *** google conversion --><!-- Iperceptions code was added for BAU 44813 --><!-- Google conversion tracking is not enabled for locale zh_CN -->
</BODY>
</HTML>
