<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<LINK rel=stylesheet type=text/css href="${ctx}/login_files/e14eap3colv2_t.css">
<script src="${ctx}/script/easy_validator.pack.js"></script>
<script>
	$(document).ready(function() {
		$(".registerImg").click(function(){
			var loginVar = $("#loginName").val();
			if(loginVar == ""){
				alert("请输入用户名");
				return;
			}
			if(loginVar.length < 6 || loginVar.length > 20){
				alert("用户名长度必须在6到20之间");
				return;
			}
			var passwordVar = $("#password").val();
			if(passwordVar == ""){
				alert("请输入密码");
				return;
			}
			if(passwordVar.length < 6 || passwordVar.length > 15){
				alert("密码长度必须在6到15之间");
				return;
			}
			if(passwordVar != $("#confirmPassword").val()){
				alert("两次输入密码不匹配");
				return;
			}
			
			if($("#mobile").val() == ""){
				alert("请输入手机");
				return;
			}
			var emailStr = $("#email").val();
			if(emailStr == ""){
				alert("请输入email");
				return;
			}
			var reg=/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	        if(!reg.test(emailStr)){
	        	alert("请输入正确的email");
	        	return;
	        }
			$.post("/frontend/register", $("#registerForm").serialize(), function(data){
				alert(data);
				if(data.indexOf("成功") > 0){
					window.location.href="/frontend/showIndex";
				}
			});
		});
	});
	
</script>
<form id="registerForm">
<TABLE id="registerTb" border=0 cellSpacing=0 cellPadding=0>
  <TBODY>
    <TR>
      <TD></TD>
      <TD class=registerExplain colSpan=2 
                          align=left></TD>
    </TR>
	<tr>
		<td></td>
		<td>&nbsp;</td>
		<td colSpan=2></td>
	</tr>
	<TR>
      <TD>* </TD>
      <TD><LABEL 
                              for=username>用户名</LABEL>
          <LABEL 
                              class="chkbox noCase">(6-20个字符)</LABEL></TD>
      <TD colSpan=2><INPUT id=loginName class=textField 
                              name=loginName maxLength=20 size=40>
      </TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD><LABEL for=password>密码</LABEL>
          <LABEL 
                              class="chkbox noCase">6-15个字符,注意区分大小写</LABEL></TD>
      <TD colSpan=2><INPUT id=password class=textField 
                              name=password maxLength=15 size=40 
                              type=password>
      </TD>
    </TR>
    <TR>
      <TD>* </TD>
      <TD><LABEL for=confirmpassword>密码确认</LABEL></TD>
      <TD colSpan=2><INPUT id=confirmPassword 
                              class=textField name=confirmPassword maxLength=15 
                              size=40 type=password>
         </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD>
	  <LABEL for=personalTitle>区域</LABEL></TD>
      <TD colSpan=2>
        <SELECT id=title name=regionId>
            <OPTION selected value="">﹍请选择﹍
          <OPTION value="EAST">华东
          <OPTION value="SOUTH">华南
          <OPTION value="NORTH">华北
          <OPTION value="WEST">中西部</OPTION>
          </SELECT>		  </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD>
	  <LABEL for=personalTitle>客户类型</LABEL></TD>
      <TD colSpan=2>
          <SELECT id=title name=customType>
            <OPTION selected value="">﹍请选择﹍
            <OPTION value="BUSINESS_USER">贸易商
            <OPTION value="FINAL_USER">最终用户</OPTION>
          </SELECT>      </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>公司名称</LABEL></TD>
      <TD colSpan=2><INPUT id=company class=textField 
                              name=company maxLength=50 size=40>
        </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=personalTitle>职能</LABEL></TD>
      <TD colSpan=2>
          <SELECT id=title name=function>
            <OPTION selected value="">﹍请选择﹍
            <OPTION value="BUY">采购
            <OPTION value="RESEARCH">研发
			<OPTION value="MAINTAINCE">维护维修
			<OPTION value="MANAGE">项目管理
			<OPTION value="PRODUCE">生产
			<OPTION value="OTHER">其他			</OPTION>
          </SELECT>      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>职务</LABEL></TD>
      <TD colSpan=2><INPUT id=position class=textField 
                              name=position maxLength=50 size=40>
         </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD><LABEL 
                            for=phonenumber>电话/手机</LABEL></TD>
      <TD colSpan=2><INPUT id=mobile 
                              class=textField name=mobile maxLength=20 
                              size=40  tip="请输入你的手机">
      </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD><LABEL 
                            for=phonenumber>Email</LABEL></TD>
      <TD colSpan=2><INPUT id=email 
                              class=textField name=email  tip="请输入你的邮箱">
      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL 
                            for=phonenumber>QQ</LABEL></TD>
      <TD colSpan=2><INPUT id=phonenumber 
                              class=textField name=qq maxLength=20 
                              size=40>
      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>地址</LABEL></TD>
      <TD colSpan=2><INPUT id=address class=textField 
                              name=address maxLength=50 size=40>
      </TD>
    </TR>
    
    <TR>
      <TD height="16"></TD>
      <TD><INPUT class="registerImg"
                              type=button></TD>
      <TD colSpan=2>
      </TD>
    </TR>
    <TR>
      <TD class="footerBlock"  style="font-size:14px; color:#FF6600">&nbsp;</TD>
      <TD style="font-size:14px; color:#FF6600; padding-top:-100px">热线: <SPAN class=contact>(86) 21-5437 0611 <br>
          <A href="">联系我们</A></SPAN> </TD>
    </TR>
  </TBODY>
</TABLE>
</form>