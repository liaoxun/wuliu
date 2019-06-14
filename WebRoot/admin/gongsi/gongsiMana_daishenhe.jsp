<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
		<script language="JavaScript" src="<%=path %>/js/public.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=path %>/js/popup_shuaxin.js"></script>
        <script language="javascript">
            function gongsiShenhe(id)
		    {
		        var url="<%=path %>/gongsi?type=gongsiShenhe&id="+id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:400,height:200});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
		    }
		    
		    function gongsiDel(id)
		    {
		        var url="<%=path %>/gongsi?type=gongsiDel&id="+id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:400,height:200});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
		    }
        </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="14" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">用户名</td>
					<td width="10%">密 码</td>
					<td width="10%">公司名称</td>
					<td width="10%">地址</td>
					<td width="10%">联系方式</td>
					<td width="10%">E-mail</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.gongsiList}" var="gongsi">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.loginname}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.loginpw}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.qiyenmae}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.address}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.tel}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${gongsi.email}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <a class="pn-loperator" href="#" onclick="gongsiShenhe(${gongsi.id})">审核</a>
						<a class="pn-loperator" href="#" onclick="gongsiDel(${gongsi.id})">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
