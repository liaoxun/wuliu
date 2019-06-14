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
import com.orm.Tuser;
import com.orm.Tgongsi;

public class gongsi_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("gongsiReg"))
		{
			gongsiReg(req, res);
		}
		if(type.endsWith("gongsiMana_daishenhe"))
		{
			gongsiMana_daishenhe(req, res);
		}
		if(type.endsWith("gongsiShenhe"))
		{
			gongsiShenhe(req, res);
		}
		if(type.endsWith("gongsiMana_yishenhe"))
		{
			gongsiMana_yishenhe(req, res);
		}
		if(type.endsWith("gongsiDel"))
		{
			gongsiDel(req, res);
		}
		
		if(type.endsWith("gongsiDetailQian"))
		{
			gongsiDetailQian(req, res);
		}
		
		if(type.endsWith("logout"))
		{
			logout(req, res);
		}
		
		if(type.endsWith("gongsiAll"))
		{
			gongsiAll(req, res);
		}
	}
	
	
	public void gongsiReg(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String qiyenmae=req.getParameter("qiyenmae");
		
		String address=req.getParameter("address");
		String tel=req.getParameter("tel");
		String email=req.getParameter("email");
		String zhuangtai="a";
		String del="no";
		String sql="insert into t_gongsi values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,loginname,loginpw,qiyenmae,address,tel,email,zhuangtai,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		req.setAttribute("message", "注册成功,等待管理员审核");
        String targetURL = "/common/add_success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void gongsiMana_daishenhe(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongsiList=new ArrayList();
		String sql="select * from t_gongsi where del ='no' and zhuangtai='a'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongsi gongsi=new Tgongsi();
				
				gongsi.setId(rs.getString("id"));
				gongsi.setLoginname(rs.getString("loginname"));
				gongsi.setLoginpw(rs.getString("loginpw"));
				gongsi.setQiyenmae(rs.getString("qiyenmae"));
				
				gongsi.setAddress(rs.getString("address"));
				gongsi.setTel(rs.getString("tel"));
				gongsi.setEmail(rs.getString("email"));
				gongsi.setZhuangtai(rs.getString("zhuangtai"));
				gongsi.setDel(rs.getString("del"));
				gongsiList.add(gongsi);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongsiList", gongsiList);
		req.getRequestDispatcher("admin/gongsi/gongsiMana_daishenhe.jsp").forward(req, res);
	}
	
	
	public void gongsiShenhe(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_gongsi set zhuangtai='b' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void gongsiMana_yishenhe(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongsiList=new ArrayList();
		String sql="select * from t_gongsi where del ='no' and zhuangtai='b'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongsi gongsi=new Tgongsi();
				
				gongsi.setId(rs.getString("id"));
				gongsi.setLoginname(rs.getString("loginname"));
				gongsi.setLoginpw(rs.getString("loginpw"));
				gongsi.setQiyenmae(rs.getString("qiyenmae"));
				
				gongsi.setAddress(rs.getString("address"));
				gongsi.setTel(rs.getString("tel"));
				gongsi.setEmail(rs.getString("email"));
				gongsi.setZhuangtai(rs.getString("zhuangtai"));
				gongsi.setDel(rs.getString("del"));
				gongsiList.add(gongsi);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongsiList", gongsiList);
		req.getRequestDispatcher("admin/gongsi/gongsiMana_yishenhe.jsp").forward(req, res);
	}
	
	
	public void gongsiDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="update t_gongsi set del='yes' where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "操作成功");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void gongsiDetailQian(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Tgongsi gongsi=new Tgongsi();
		
		String sql="select * from t_gongsi where id=?";
		Object[] params={req.getParameter("id")};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				gongsi.setId(rs.getString("id"));
				gongsi.setLoginname(rs.getString("loginname"));
				gongsi.setLoginpw(rs.getString("loginpw"));
				gongsi.setQiyenmae(rs.getString("qiyenmae"));
				
				gongsi.setAddress(rs.getString("address"));
				gongsi.setTel(rs.getString("tel"));
				gongsi.setEmail(rs.getString("email"));
				gongsi.setZhuangtai(rs.getString("zhuangtai"));
				gongsi.setDel(rs.getString("del"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongsi", gongsi);
		req.getRequestDispatcher("qiantai/gongsi/gongsiDetailQian.jsp").forward(req, res);
	}
	
	public void logout(HttpServletRequest req,HttpServletResponse res)
	{
		req.getSession().setAttribute("userType", null);
		String targetURL = "/qiantai/default.jsp";
		dispatch(targetURL, req, res);		
	}
	
	
	public void gongsiAll(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongsiList=new ArrayList();
		String sql="select * from t_gongsi where del ='no' and zhuangtai='b'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongsi gongsi=new Tgongsi();
				
				gongsi.setId(rs.getString("id"));
				gongsi.setLoginname(rs.getString("loginname"));
				gongsi.setLoginpw(rs.getString("loginpw"));
				gongsi.setQiyenmae(rs.getString("qiyenmae"));
				
				gongsi.setAddress(rs.getString("address"));
				gongsi.setTel(rs.getString("tel"));
				gongsi.setEmail(rs.getString("email"));
				gongsi.setZhuangtai(rs.getString("zhuangtai"));
				gongsi.setDel(rs.getString("del"));
				gongsiList.add(gongsi);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongsiList", gongsiList);
		req.getRequestDispatcher("qiantai/gongsi/gongsiAll.jsp").forward(req, res);
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
