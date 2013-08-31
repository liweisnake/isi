<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<script>
	$(document).ready(function() {
		$(".changePwdImg").click(function(){
			if($("#oldPassword").val() == ""){
				alert("请输入旧密码");
				return;
			}
			if($("#newPassword").val() == ""){
				alert("请输入新密码");
				return;
			}
			if($("#newPassword").val() != $("#confirmNewPassword").val()){
				alert("两次输入的新密码不匹配");
				return;
			}
	        $.post("/frontend/doChangePassword", $("#changePwdForm").serialize(), function(data){
				alert(data);
				$.unblockUI();
			});
		});
	});
</script>
<form id="changePwdForm">
<TABLE>
  <TBODY>
    <TR>
      <TD height="285" vAlign=top>
        <TABLE border=0 cellSpacing=0 cellPadding=0>
          <TBODY>
            <TR>
              <TD></TD>
              <TD height=19 colSpan=2>&nbsp;</TD>
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
              <TD colspan="3" style="text-align: left;"><img src="${ctx}/info_files/t02.jpg" width="240" height="50"></TD>
            </TR>
          	<TD>* </TD>
              <TD><LABEL for=lastname>旧密码</LABEL></TD>
            <TD><INPUT id=oldPassword class=textField type="password"
                              name=oldPassword maxLength=40 size=40></TD>
          </TR>
          <TR>
            <TD>* </TD>
            <TD><LABEL for=email>新密码</LABEL></TD>
            <TD><INPUT id=newPassword class=textField 
                              name=newPassword type="password" maxLength=50 size=40></TD>
          </TR>
          <TR>
            <TD>* </TD>
            <TD><LABEL for=phonenumber>再次输入新密码</LABEL></TD>
            <TD rowspan="2"><INPUT id=confirmNewPassword 
                              class=textField type="password" name=confirmNewPassword maxLength=20 
                              size=40></TD>
          </TR>
          <TR>
            <TD>&nbsp;</TD>
          </TR>
          <TR>
            <TD height="46">&nbsp;</TD>
            <TD width="87"><input class="noBorder changePwdImg" type=button ></TD>
          </TR>
          <TR  id="pageFooter" class="footerBlock">
            <TD height="53" colSpan=3  style="font-size:14px; color:#FF6600">热线: <SPAN class=contact>(86) 21-5437 0611 | <A 
            href="">联系我们</A></SPAN> </TD>
          </TR>
        </TABLE>
    </TR>
</TABLE>
</form>
