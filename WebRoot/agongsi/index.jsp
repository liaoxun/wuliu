<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <frameset rows="90,*,30" cols="*" framespacing="0" frameborder="no" border="0">
	  <frame src="<%=path %>/agongsi/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
	  <frame src="<%=path %>/agongsi/center.jsp" name="mainFrame" id="mainFrame" />
	  <frame src="<%=path %>/agongsi/down.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
  </frameset>
</html>
