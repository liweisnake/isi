<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>iic首页</title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />

</head>

<body>
<jsp:include page="top.jsp" flush="true"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td  width="10%" align="right" valign="top" bgcolor="f0f0f2"><img src="${ctx}/image/menu/left.jpg" width="140" height="2" /></td>
    <td width="226" rowspan="2" align="right" valign="top"><img src="${ctx}/image/menu/t-m.jpg" width="227" height="40" />
	
	  <ul>
        <br />
        <jsp:include page="leftMenu.jsp" flush="true" />
      </ul>
    <!--------------------------菜单---------------->	</td>
    <td width="1%" rowspan="2" valign="top">&nbsp;</td>
    <td width="77%" rowspan="2" valign="top">
    <jsp:include page="searchTop.jsp" flush="true"/>
      
       <p>   <iframe height="440" name="neepage" width="750" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="${ctx}/adpic/flashb.html"></iframe>
       <table width="747" height="39" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/promotions/label-2.jpg); background-repeat:no-repeat; background-position:left center; ">
         <tr>
           <td><h1>限时抢购</h1></td>
         </tr>
       </table>
       <table width="747" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/promotions/promotions-table-m.jpg); background-repeat:repeat-x;">
         <tr>
           <td width="11"><img src="${ctx}/image/promotions/promotions-table-l.jpg" width="11" height="186" /></td>
           <td width="725">
		   
		   <div id="scrollBox2">
             <table border="0" align="center" cellpadding="2" cellspacing="0">
               <!-------------------------1------------------------>
               <c:forEach items="${promotionList}" var="stat" varStatus="status">
	               <c:if test="${status.index == 0}">
	               		<tr height="160" valign="top">
	               </c:if>
	               <c:if test="${status.index == 3}">
	               		</tr>
	               		<tr height="160">
	               </c:if>
              		<td width="237"><a href="${stat.link}"><img src="${ctx}/upload/img/${stat.picUrl}" /><br />
                 			${stat.description}<br />
               		<img src="${ctx}/image/promotions/button-buy.jpg" /></a></td>
                   <c:if test="${(status.index + 1) % 3 != 0}">
                   		<td width="3" valign="top"><img src="${ctx}/image/promotions/line.jpg" /></td>
	               </c:if>
	               <c:if test="${status.index == 5}">
	               		</tr>
	               </c:if>
               </c:forEach>
      		  </table>
             <br />
           </div>
             <script>
window.onload=function(){
  new Marquee(
    "scrollBox2",  //容器ID<br />
    0,  //滚动方向(0向上 1向下 2向左 3向右)<br />
    2,  //滚动步长<br />
    744,  //滚动区域宽度<br />
    160,  //滚动区域高度<br />
    50,  //定时器 数值越小，滚动的速度越快(1000=1秒,建议不小于20)<br />
    5000,  //停顿时间(0为不停顿,1000=1秒)<br />
    2000,  //开始等待时间(0为不等待,1000=1秒)<br />
    168  //间歇滚动间距(可选)<br />
    );
  };
function Marquee(){
  this.ID=document.getElementById(arguments[0]);
  this.Direction=arguments[1];
  this.Step=arguments[2];
  this.Width=arguments[3];
  this.Height=arguments[4];
  this.Timer=arguments[5];
  this.WaitTime=arguments[6];
  this.StopTime=arguments[7];
  if(arguments[8]){this.ScrollStep=arguments[8];}else{this.ScrollStep=this.Direction>1?this.Width:this.Height;}
  this.CTL=this.StartID=this.Stop=this.MouseOver=0;
  this.ID.style.overflowX=this.ID.style.overflowY="hidden";
  this.ID.noWrap=true;
  this.ID.style.width=this.Width;
  this.ID.style.height=this.Height;
  this.ClientScroll=this.Direction>1?this.ID.scrollWidth:this.ID.scrollHeight;
  this.ID.innerHTML+=this.ID.innerHTML;
  this.Start(this,this.Timer,this.WaitTime,this.StopTime);
  }
Marquee.prototype.Start=function(msobj,timer,waittime,stoptime){
  msobj.StartID=function(){msobj.Scroll();}
  msobj.Continue=function(){
    if(msobj.MouseOver==1){setTimeout(msobj.Continue,waittime);}
    else{clearInterval(msobj.TimerID); msobj.CTL=msobj.Stop=0; msobj.TimerID=setInterval(msobj.StartID,timer);}
    }
  msobj.Pause=function(){msobj.Stop=1; clearInterval(msobj.TimerID); setTimeout(msobj.Continue,waittime);}
  msobj.Begin=function(){
    msobj.TimerID=setInterval(msobj.StartID,timer);
    msobj.ID.onmouseover=function(){msobj.MouseOver=1; clearInterval(msobj.TimerID);}
    msobj.ID.onmouseout=function(){msobj.MouseOver=0; if(msobj.Stop==0){clearInterval(msobj.TimerID); msobj.TimerID=setInterval(msobj.StartID,timer);}}
    }
  setTimeout(msobj.Begin,stoptime);
  }
Marquee.prototype.Scroll=function(){
  switch(this.Direction){
    case 0:
      this.CTL+=this.Step;
      if(this.CTL>=this.ScrollStep&&this.WaitTime>0){this.ID.scrollTop+=this.ScrollStep+this.Step-this.CTL; this.Pause(); return;}
      else{if(this.ID.scrollTop>=this.ClientScroll) this.ID.scrollTop-=this.ClientScroll; this.ID.scrollTop+=this.Step;}
      break;
    case 1:
      this.CTL+=this.Step;
      if(this.CTL>=this.ScrollStep&&this.WaitTime>0){this.ID.scrollTop-=this.ScrollStep+this.Step-this.CTL; this.Pause(); return;}
      else{if(this.ID.scrollTop<=0) this.ID.scrollTop+=this.ClientScroll; this.ID.scrollTop-=this.Step;}
      break;
    case 2:
      this.CTL+=this.Step;
      if(this.CTL>=this.ScrollStep&&this.WaitTime>0){this.ID.scrollLeft+=this.ScrollStep+this.Step-this.CTL; this.Pause(); return;}
      else{if(this.ID.scrollLeft>=this.ClientScroll) this.ID.scrollLeft-=this.ClientScroll; this.ID.scrollLeft+=this.Step;}
      break;
    case 3:
      this.CTL+=this.Step;
      if(this.CTL>=this.ScrollStep&&this.WaitTime>0){this.ID.scrollLeft-=this.ScrollStep+this.Step-this.CTL; this.Pause(); return;}
      else{if(this.ID.scrollLeft<=0) this.ID.scrollLeft+=this.ClientScroll; this.ID.scrollLeft-=this.Step;}
      break;
    }
  }
</script>		   </td>
           <td width="11"><img src="${ctx}/image/promotions/promotions-table-r.jpg" width="11" height="186" align="right" /></td>
         </tr>
       </table>
	   
       <br />
       <table width="747" height="39" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/promotions/label.jpg); background-repeat:no-repeat; background-position:left center; ">
         <tr>
           <td><h1>精选供应商</h1></td>
         </tr>
       </table>
       <table width="746" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/contact/box-bg.jpg); background-repeat:no-repeat; background-position:left top;">
        <tr>
          <td height="33" align="right" style="padding-right:20px"><a > 查看所有供应商</a></td>
        </tr>
        <tr>
          <td height="239" align="center"><img src="${ctx}/image/contact/logos.jpg" width="694" height="210" /></td>
        </tr>
      </table>       </p></td>
  </tr>
  <tr>
    <td bgcolor="f0f0f2">&nbsp;</td>
  </tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background-image:url(${ctx}/image/bottom/bottom-line.jpg); background-repeat:repeat-x;">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<jsp:include page="buttom.jsp" flush="true" />
</body>
</html>
