<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
	<script type='text/javascript' src='<%=path %>/dwr/interface/loginService.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
    <script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
	<script type="text/javascript">
	        function reg()
	        {
	                var url="<%=path %>/qiantai/userinfo/regPre.jsp";
	                var n="";
	                var w="480px";
	                var h="500px";
	                var s="resizable:no;help:no;status:no;scroll:yes";
				    openWin(url,n,w,h,s);
	        }
	        
	        function check()
			{                                                                   
			     if(document.ThisForm.loginname.value=="")
				 {
				 	alert("请输入用户名");
					return false;
				 }
				 if(document.ThisForm.loginpw.value=="")
				 {
				 	alert("请输入密码");
					return false;
				 }
				 document.getElementById("indicator").style.display="block";
				 loginService.login(document.ThisForm.loginname.value,document.ThisForm.loginpw.value,document.ThisForm.userType.value,callback);
			}
		
			function callback(data)
			{
			    document.getElementById("indicator").style.display="none";
			    if(data=="no")
			    {
			        alert("用户名或密码错误");
			    }
			    if(data=="yes")
			    {   alert("登陆成功");
			        window.location.reload();
			    }
			    
			}
	        
	</script>
  </head>
  
  <body>
       <c:if test="${sessionScope.userType==null || sessionScope.userType==0}">
			<form action="<%=path %>/user?type=userLogin" name="ThisForm" method="post">
			      <table cellspacing="0" cellpadding="0" width="98%" align="center" border="0">
			          <tr>
			            <td align="center" colspan="2" height="10"></td>
			          </tr>
			          <tr>
			            <td align="right" width="31%" height="30" style="font-size: 11px;">用户名：</td>
			            <td align="left" width="69%"><input name="loginname" type="text" style="width: 100px;"/></td>
			          </tr>
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">密　码：</td>
			            <td align="left"><input type="password" style="width: 100px;" name="loginpw"/></td>
			          </tr>
			          <tr>
			            <td align="right" height="30" style="font-size: 11px;">身　份：</td>
			            <td align="left">
                           <select name="userType" style="width: 100px;">
                               <option value="1">个人用户</option>
                               <option value="2">运输公司</option>
                           </select>
                        </td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="10"></td>
			          </tr>
			          <tr>
			            <td align="center" colspan="2" height="30">
			               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			               <input type="button" value="登  录" onclick="check()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
						   &nbsp;
						   <input type="button" value="注  册" onclick="reg()" style="border:#ccc 1px solid; background-color:#FFFFFF; font-size:12px; padding-top:3px;" />
			               &nbsp;
			               <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
			            </td>
			          </tr>
			      </table>
		    </form>
		    </c:if>
		    <c:if test="${sessionScope.userType==1}">
		        <br/>
			     欢迎您：${sessionScope.user.loginname } &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="<%=path %>/user?type=logout">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
			    <br/><br/><br/>
			</c:if>
			 <c:if test="${sessionScope.userType==2}">
		        <br/>
			     欢迎您：${sessionScope.gongsi.loginname } &nbsp;&nbsp;&nbsp;&nbsp;
			    <a href="<%=path %>/gongsi?type=logout">安全退出</a> &nbsp;&nbsp;&nbsp;&nbsp;
			    <br/><br/><br/>
			</c:if>
  </body>
</html>
