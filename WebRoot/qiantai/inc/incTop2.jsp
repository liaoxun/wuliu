<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <script type="text/javascript">
         function admin()
         {
            var url="<%=path %>/login.jsp";
            window.open(url,"_blank");
         } 
         
         function reg()
         {
                var url="<%=path %>/qiantai/userinfo/regPre.jsp";
                var n="";
                var w="480px";
                var h="500px";
                var s="resizable:no;help:no;status:no;scroll:yes";
			    openWin(url,n,w,h,s);
         }
         
         function myXinxi()
         {
            <c:if test="${sessionScope.userType==null}">
                  alert("请先登录");
            </c:if>
            
            <c:if test="${sessionScope.userType==0}">
                  alert("请先登录");
            </c:if>
            
            <c:if test="${sessionScope.userType !=null && sessionScope.userType==1}">
                var url="<%=path %>/auser/index.jsp";
				var targetWinName="newWin";
				var features="width="+screen.width+" ,height="+screen.height+" ,toolbar=no, top=0, left=0, menubar=no, scrollbars=no, resizable=no,location=no, status=no"
				var new_win=window.open(url,targetWinName,features);
            </c:if>
            <c:if test="${sessionScope.userType !=null && sessionScope.userType==2}">
                var url="<%=path %>/agongsi/index.jsp";
				var targetWinName="newWin";
				var features="width="+screen.width+" ,height="+screen.height+" ,toolbar=no, top=0, left=0, menubar=no, scrollbars=no, resizable=no,location=no, status=no"
				var new_win=window.open(url,targetWinName,features);
            </c:if>
         } 
         
         function liuyanAll()
         {
            <c:if test="${sessionScope.user==null}">
                  alert("请先登录");
            </c:if>
            
            <c:if test="${sessionScope.user!=null}">
                var url="<%=path %>/liuyan?type=liuyanAll";
				var targetWinName="newWin";
				var features="width="+screen.width-200+" ,height="+screen.height-150+" ,toolbar=no, top=0, left=0, menubar=no, scrollbars=no, resizable=no,location=no, status=no"
				var new_win=window.open(url,targetWinName,features);
            </c:if>
         } 
         
         function goodsAll()
         {
             var url="<%=path %>/goods?type=goodsAll";
             window.location.href=url;
         }
         function gongsiAll()
         {
             var url="<%=path %>/gongsi?type=gongsiAll";
             window.location.href=url;
         }
      </script>
  </head>
  
  <body>
       &nbsp;&nbsp;
       <A href="<%=path %>/qiantai/default.jsp">系统首页</A> &nbsp;&nbsp;
       <a href="#" onclick="reg()">用户注册</A> &nbsp;&nbsp;
       <a href="#" onclick="myXinxi()">会员中心</A> &nbsp;&nbsp;
       <a href="#" onclick="goodsAll()">货物信息</A> &nbsp;&nbsp;
       <a href="#" onclick="gongsiAll()">运输公司</A> &nbsp;&nbsp;
       <a href="#" onclick="liuyanAll()">留言板</A> &nbsp;&nbsp;
	   <a href="#" onclick="admin()">后台管理</a> &nbsp;&nbsp;
  </body>
</html>
