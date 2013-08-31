<script>
	$(document).ready(function() {
		$(".changePwdImg").click(function(){
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
	        $.post("/frontend/forgetPassword", $("#findPwdForm").serialize(), function(data){
				$("#showInfo").html(data);
			});
		});
	});
</script>
<div id="showInfo">
<form id="findPwdForm">
<TABLE border=0 cellSpacing=0 cellPadding=0 width="263">
   <TBODY>
   <TR>
     <TD class=heading colSpan=2></TD>
   </TR>
   <TR>
     <TD colspan="2" style="text-align:left"><img src="${ctx}/info_files/t01.jpg" width="240" height="50"></TD>
   </TR>
   <TR>
     <TD>*</TD>
     <TD><INPUT id=email class=textField name=email></TD>
   </TR>
   <TR>
     <TD></TD>
     <TD><input class="changePwdImg" type=button ></TD>
   </TR>
 </TABLE>
 </form>
 </div>