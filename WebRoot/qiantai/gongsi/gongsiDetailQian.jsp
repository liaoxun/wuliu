<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <LINK href="<%=path %>/css/css.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=path %>/js/popup.js"></script>
	    <script type="text/javascript">
	    function orderAdd(gongsi_id)
	    {
	        <c:if test="${sessionScope.userType==null}">
                  alert("请先登录");
            </c:if>
            <c:if test="${sessionScope.userType!=null && sessionScope.userType==2}">
                  alert("您注册的运输公司，不能下单");
            </c:if>
            <c:if test="${sessionScope.userType!=null && sessionScope.userType==1}">
                var url="<%=path %>/qiantai/order/orderAdd.jsp?gongsi_id="+gongsi_id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:600,height:400});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
            </c:if>
	    }
	    </script>
  </head>
  
  <BODY text=#000000  leftMargin=0 topMargin=0>
	<div class="wrap"> 
		<TABLE  cellSpacing=0 cellPadding=0 width="100%" align=center border=0  background="<%=path %>/img/reservation01.gif">
				<TR height="90">
					<TD align="center">
					    <jsp:include flush="true" page="/qiantai/inc/incTop1.jsp"></jsp:include> 
					</TD>
				</TR>
		</TABLE>
		
		
		<TABLE id=guide cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
				<TR>
					<TD align="left">
						<jsp:include flush="true" page="/qiantai/inc/incTop2.jsp"></jsp:include>
					</TD>
				</TR>
		</TABLE>

        <TABLE class=MainTable style="MARGIN-TOP: 0px" cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
				<TR>
				    <TD class=Side vAlign=top align=right width="25%">
						<jsp:include flush="true" page="/qiantai/inc/incLeft.jsp"></jsp:include>
					</TD>
					<td width="1">&nbsp;</td>
					<TD class=Side vAlign=top align=right width="75%">
						<TABLE class=dragTable cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TR>
									<TD class=head>
										<SPAN class=TAG>运输公司信息</SPAN>
									</TD>
								</TR>
								<TR align="left">
									<TD height="5"></TD>
								</TR>
								<TR align="left" height="500">
									<td>
										<table width="100%" border="0" cellpadding="9" cellspacing="9">
											<tr>
												<td align="left">公司名称：<c:out value="${requestScope.gongsi.qiyenmae}" escapeXml="false"></c:out></td>
											</tr>
											<tr>
												<td align="left">公司地址：<c:out value="${requestScope.gongsi.address}" escapeXml="false"></c:out></td>
											</tr>
											<tr>
												<td align="left">联系方式： <c:out value="${requestScope.gongsi.tel}" escapeXml="false"></c:out></td>
											</tr>
											<tr>
												<td align="left">E-mail：<c:out value="${requestScope.gongsi.email}" escapeXml="false"></c:out></td>
											</tr>
											<tr>
												<td align="left"><a href="#" style="color: red" onclick="orderAdd(${requestScope.gongsi.id})">在线下单</a></td>
											</tr>
										</table>
									</td>
								</TR>
								<TR align="left">
									<TD height="5"></TD>
								</TR>
						</TABLE>
					</TD>
				</TR>
		</TABLE>
		<jsp:include flush="true" page="/qiantai/inc/incFoot.jsp"></jsp:include>
    </div>
  </BODY>
</html>
