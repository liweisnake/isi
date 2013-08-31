<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<LINK rel=stylesheet type=text/css href="${ctx}/login_files/e14eap3colv2_t.css">
<script src="${ctx}/script/easy_validator.pack.js"></script>
<script>
	$(document).ready(function() {
		$(".personalInfoSubmitImg").click(function(){
			
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
			$.post("/frontend/modifyInfo", $("#personalInfoSubmitForm").serialize(), function(data){
				alert(data);
				$.unblockUI();
			});
		});
	});
	
</script>
<form id="personalInfoSubmitForm">
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
      <TD>&nbsp;</TD>
      <TD>
	  <LABEL for=personalTitle>区域</LABEL></TD>
      <TD colSpan=2>
        <SELECT id=title name=regionId>
            <OPTION selected value="">﹍请选择﹍
          <OPTION value="EAST" <c:if test='${user.regionId == "EAST"}'>selected</c:if>>华东
          <OPTION value="SOUTH" <c:if test='${user.regionId == "SOUTH"}'>selected</c:if>>华南
          <OPTION value="NORTH" <c:if test='${user.regionId == "NORTH"}'>selected</c:if>>华北
          <OPTION value="WEST" <c:if test='${user.regionId == "WEST"}'>selected</c:if>>中西部</OPTION>
          </SELECT>		  </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD>
	  <LABEL for=personalTitle>客户类型</LABEL></TD>
      <TD colSpan=2>
          <SELECT id=title name=customType>
            <OPTION value="">﹍请选择﹍
            <OPTION value="BUSINESS_USER" <c:if test='${user.customType == "BUSINESS_USER"}'>selected</c:if>>贸易商
            <OPTION value="FINAL_USER" <c:if test='${user.customType == "FINAL_USER"}'>selected</c:if>>最终用户</OPTION>
          </SELECT>      </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>公司名称</LABEL></TD>
      <TD colSpan=2><INPUT id=company class=textField 
                              name=company maxLength=50 size=40 value="${user.company}">
        </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=personalTitle>职能</LABEL></TD>
      <TD colSpan=2>
          <SELECT id=title name=function>
            <OPTION value="">﹍请选择﹍
            <OPTION value="BUY" <c:if test='${user.function == "BUY"}'>selected</c:if>>采购
            <OPTION value="RESEARCH" <c:if test='${user.function == "RESEARCH"}'>selected</c:if>>研发
			<OPTION value="MAINTAINCE" <c:if test='${user.function == "MAINTAINCE"}'>selected</c:if>>维护维修
			<OPTION value="MANAGE" <c:if test='${user.function == "MANAGE"}'>selected</c:if>>项目管理
			<OPTION value="PRODUCE" <c:if test='${user.function == "PRODUCE"}'>selected</c:if>>生产
			<OPTION value="OTHER" <c:if test='${user.function == "OTHER"}'>selected</c:if>>其他			</OPTION>
          </SELECT>      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>职务</LABEL></TD>
      <TD colSpan=2><INPUT id=position class=textField 
                              name=position maxLength=50 size=40 value="${user.position}">
         </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD><LABEL 
                            for=phonenumber>电话/手机</LABEL></TD>
      <TD colSpan=2><INPUT id=mobile 
                              class=textField name=mobile maxLength=20 
                              size=40  tip="请输入你的手机" value="${user.mobile}">
      </TD>
    </TR>
    
    <TR>
      <TD>* </TD>
      <TD><LABEL 
                            for=phonenumber>Email</LABEL></TD>
      <TD colSpan=2><INPUT id=email 
                              class=textField name=email  tip="请输入你的邮箱" value="${user.email}">
      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL 
                            for=phonenumber>QQ</LABEL></TD>
      <TD colSpan=2><INPUT id=phonenumber 
                              class=textField name=qq maxLength=20 
                              size=40 value="${user.qq}">
      </TD>
    </TR>
    
    <TR>
      <TD>&nbsp;</TD>
      <TD><LABEL for=email>地址</LABEL></TD>
      <TD colSpan=2><INPUT id=address class=textField 
                              name=address maxLength=50 size=40 value="${user.address}">
      </TD>
    </TR>
    
    <TR>
      <TD height="16"></TD>
      <TD><INPUT class="personalInfoSubmitImg"
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