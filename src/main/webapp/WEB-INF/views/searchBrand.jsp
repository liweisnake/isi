<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>


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
          <jsp:include page="leftMenu.jsp" flush="true"/>
         
        </ul>
      <!--------------------------菜单---------------->
    </td>
    <td width="1%" rowspan="2" valign="top">&nbsp;</td>
    <td width="77%" rowspan="2" valign="top">
    
    <jsp:include page="searchTop.jsp" flush="true"/>
      
      <br />
      <br />
      <table width="100%" border="0">
        <tr>
          <td width="3%">&nbsp;</td>
          <td colspan="2"><!---table width="100%" border="0" cellpadding="0" cellspacing="0" style="background-image:url(image/search/data-02.jpg); background-position:center; background-repeat:repeat-x">
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
          </table-->
              <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="search-bg">
                <tr align="center" >
                  <td width="5%" height="80"><img src="${ctx}/image/search/search-01.jpg" width="64" height="80" align="left" /></td>
                  <td width="3%"><p><a href="#0" name="0" id="0">#</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#A" name="A" id="A">A</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#B" name="B" id="B">B</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#C" name="C" id="C">C</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#D" name="D" id="D">D</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#E" name="E" id="E">E</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#F" name="F" id="F">F</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#G" name="G" id="G">G</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#H" name="H" id="H">H</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#I" name="I" id="I">I</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#J" name="J" id="J">J</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#K" name="K" id="K">K</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#L" name="L" id="L">L</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#M" name="M" id="M">M</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#N" name="N" id="N">N</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#O" name="O" id="O">O</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#P" name="P" id="P">P</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#Q" name="Q" id="Q">Q</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#R" name="R" id="R">R</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#S" name="S" id="S">S</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#T" name="T" id="T">T</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#U" name="U" id="U">U</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#V" name="V" id="V">V</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#W" name="W" id="W">W</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#X" name="X" id="X">X</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#Y" name="Y" id="Y">Y</a></p>
                      <p>&nbsp;</p></td>
                  <td width="3%"><p><a href="#Z" name="Z" id="Z">Z</a></p>
                      <p>&nbsp;</p></td>
                  <td width="14%" align="right"><a name="Top" id="Top"></a><img src="${ctx}/image/search/search-03.jpg" width="11" height="80" /></td>
                </tr>
            </table></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="167">&nbsp;</td>
          <td colspan="2" valign="top" >
          <table width="100%" border="0" cellspacing="0" cellpadding="5">
          	<c:forEach items="${brandCategory}" var="entry">  
              <tr>
                <td bgcolor="#FFE8DD"><a name="A" id="A"></a>${entry.key}</td>
                <td width="7%" bgcolor="#FFE8DD"><a href="#Top">Top &and;</a></td>
              </tr>
              <tr>
                <td colspan="2">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <c:forEach items="${entry.value}" var="item" varStatus="status">
                	<c:if test="${status.index % 3 == 0}">
                    <tr>
                    </c:if>
                      <td><a href="/frontend/getCategoryByManufacturer?manufacturerId=${item.id}">${item.mfrName }</a></td>
                    <c:if test="${status.index % 3 == 0} && ${status.index != 0}">
                    </tr>
                    </c:if>
                </c:forEach>
                </table>
                </td>
              </tr>
              </c:forEach>
            </table>
              <br /></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td height="33">&nbsp;</td>
          <td width="26%">&nbsp;</td>
          <td width="71%">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <p></td>
  </tr>
  <tr>
    <td bgcolor="f0f0f2">&nbsp;</td>
  </tr>
</table>


<jsp:include page="buttom.jsp" />
</body>
</html>
