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
import com.orm.Tgoods;
import com.orm.Tuser;

public class goods_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("goodsAdd"))
		{
			goodsAdd(req, res);
		}
		if(type.endsWith("goodsMana_me"))
		{
			goodsMana_me(req, res);
		}
		if(type.endsWith("goodsDel"))
		{
			goodsDel(req, res);
		}
		if(type.endsWith("goodsDetailQian"))
		{
			goodsDetailQian(req, res);
		}
		
		
		if(type.endsWith("goodsMana"))
		{
			goodsMana(req, res);
		}
		if(type.endsWith("goodsAll"))
		{
			goodsAll(req, res);
		}
	}
	
	
	public void goodsAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String name=req.getParameter("name");
		String shifadi=req.getParameter("shifadi");
		String mudidi=req.getParameter("mudidi");
		
		String beizhu=req.getParameter("beizhu");
		String yusuanfei=req.getParameter("yusuanfei");
		String shijian=new Date().toLocaleString();
		String user_id=req.getParameter("user_id");
		String del="no";
		
		String sql="insert into t_goods values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,name,shifadi,mudidi,beizhu,yusuanfei,shijian,user_id,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        req.setAttribute("message", "操作成功");
		
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
        
	}
	
	
	public void goodsDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_goods set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "操作成功");
		
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
	}

	public void goodsMana_me(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String user_id=req.getParameter("user_id");
		
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no' and user_id=?";
		Object[] params={user_id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setShifadi(rs.getString("shifadi"));
				goods.setMudidi(rs.getString("mudidi"));
				
				goods.setBeizhu(rs.getString("beizhu"));
				goods.setYusuanfei(rs.getString("yusuanfei"));
				goods.setShijian(rs.getString("shijian"));
				goods.setUser_id(rs.getString("user_id"));
				
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("auser/goods/goodsMana_me.jsp").forward(req, res);
	}
	
	
	
	
	public void goodsDetailQian(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String id=req.getParameter("id");
		Tgoods goods=new Tgoods();
		
		String sql="select * from t_goods where id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			
			goods.setId(rs.getString("id"));
			goods.setName(rs.getString("name"));
			goods.setShifadi(rs.getString("shifadi"));
			goods.setMudidi(rs.getString("mudidi"));
			
			goods.setBeizhu(rs.getString("beizhu"));
			goods.setYusuanfei(rs.getString("yusuanfei"));
			goods.setShijian(rs.getString("shijian"));
			goods.setUser_id(rs.getString("user_id"));
			
			goods.setDel(rs.getString("del"));
			
			
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goods", goods);
		req.getRequestDispatcher("/qiantai/goods/goodsDetailQian.jsp").forward(req, res);
	}
	
	public void goodsMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setShifadi(rs.getString("shifadi"));
				goods.setMudidi(rs.getString("mudidi"));
				
				goods.setBeizhu(rs.getString("beizhu"));
				goods.setYusuanfei(rs.getString("yusuanfei"));
				goods.setShijian(rs.getString("shijian"));
				goods.setUser_id(rs.getString("user_id"));
				
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("admin/goods/goodsMana.jsp").forward(req, res);
	}
	
	
	public void goodsAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List goodsList=new ArrayList();
		String sql="select * from t_goods where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgoods goods=new Tgoods();
				
				goods.setId(rs.getString("id"));
				goods.setName(rs.getString("name"));
				goods.setShifadi(rs.getString("shifadi"));
				goods.setMudidi(rs.getString("mudidi"));
				
				goods.setBeizhu(rs.getString("beizhu"));
				goods.setYusuanfei(rs.getString("yusuanfei"));
				goods.setShijian(rs.getString("shijian"));
				goods.setUser_id(rs.getString("user_id"));
				
				goods.setDel(rs.getString("del"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("qiantai/goods/goodsAll.jsp").forward(req, res);
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
