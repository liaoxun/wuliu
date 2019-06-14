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
            function goodsDel(id)
		    {
		        var url="<%=path %>/goods?type=goodsDel&id="+id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:200,height:100});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
	            //另一红上传方式可以参照进销存
		    }
		    function userDetail(user_id)
		    {
		        var url="<%=path %>/user?type=userDetail&id="+user_id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:400,height:400});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
	            //另一红上传方式可以参照进销存
		    }
		    
		    function orderDel_me(id)
		    {
		        var url="<%=path %>/order?type=orderDel_me&id="+id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:200,height:100});
	            pop.setContent("contentUrl",url);
	            pop.setContent("title","");
	            pop.build();
	            pop.show();
	            //另一红上传方式可以参照进销存
		    }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">货物名称</td>
					<td width="10%">起始地</td>
					<td width="10%">目的地</td>
					<td width="10%">备注信息</td>
					<td width="10%">下单时间</td>
					<td width="10%">用户信息</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.orderList}" var="order">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${order.huowuname}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${order.shifadi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${order.mudidi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${order.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${order.xiadanshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 <a class="pn-loperator" style="color: red" href="#" onclick="userDetail(${order.user_id})">查看用户信息</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 <a href="#" onclick="orderDel_me(${order.id})">删除恶意订单</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
