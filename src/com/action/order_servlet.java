package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tgonggao;
import com.orm.Tgongsi;
import com.orm.Tgoods;
import com.orm.Torder;
import com.orm.Tuser;

public class order_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("orderAdd"))
		{
			orderAdd(req, res);
		}
		
		if(type.endsWith("orderMana_me"))
		{
			orderMana_me(req, res);
		}
		if(type.endsWith("orderDel_me"))
		{
			orderDel_me(req, res);
		}
	}
	
	
	public void orderAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String gongsi_id=req.getParameter("gongsi_id");
		String user_id=req.getParameter("user_id");
		String huowuname=req.getParameter("huowuname");
		
		String shifadi=req.getParameter("shifadi");
		String mudidi=req.getParameter("mudidi");
		String beizhu=req.getParameter("beizhu");
		String xiadanshijian=new Date().toLocaleString();
		
		String del="no";
		
		String sql="insert into t_order values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,gongsi_id,user_id,huowuname,shifadi,mudidi,beizhu,xiadanshijian,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        req.setAttribute("message", "下单成功");
		
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	public void orderDel_me(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_order set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        req.setAttribute("message", "删除成功");
		
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
        
	}
	

	
	public void orderMana_me(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
			
		List orderList=new ArrayList();
		String sql="select * from t_order where del='no' and gongsi_id=?";
		Object[] params={((Tgongsi)(req.getSession().getAttribute("gongsi"))).getId()};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Torder order=new Torder();
				
				order.setId(rs.getString("id"));
				order.setGongsi_id(rs.getString("gongsi_id"));
				order.setUser_id(rs.getString("user_id"));
				order.setHuowuname(rs.getString("huowuname"));
				
				order.setShifadi(rs.getString("shifadi"));
				order.setMudidi(rs.getString("mudidi"));
				order.setBeizhu(rs.getString("beizhu"));
				order.setXiadanshijian(rs.getString("xiadanshijian"));
				
				order.setDel(rs.getString("del"));
				
				orderList.add(order);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("agongsi/order/orderMana_me.jsp").forward(req, res);
	}
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
