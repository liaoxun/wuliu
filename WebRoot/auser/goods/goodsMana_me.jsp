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
		<script type="text/javascript" src="<%=path %>/js/popup.js"></script>
        <script language="javascript">
           function goodsAdd()
           {
              var url="<%=path %>/auser/goods/goodsAdd.jsp";
              window.location.href=url;
           }
            function goodsDel(id)
		    {
		        var url="<%=path %>/goods?type=goodsDel&id="+id;
		        var pop=new Popup({ contentType:1,isReloadOnClose:false,width:200,height:100});
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
					<td height="14" colspan="44" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">货物名称</td>
					<td width="10%">起始地</td>
					<td width="10%">目的地</td>
					<td width="10%">备注信息</td>
					
					<td width="10%">估算费用</td>
					<td width="10%">发布时间</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.goodsList}" var="goods">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.shifadi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.mudidi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.yusuanfei}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						 ${goods.shijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a class="pn-loperator" href="#" onclick="goodsDel(${goods.id})">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="发布" style="width: 80px;" onclick="goodsAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
