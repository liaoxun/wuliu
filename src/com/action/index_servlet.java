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
import com.orm.Tgongsi;
import com.service.liuService;

public class index_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		
		List gonggaoList=new ArrayList();
		String sql="select * from t_gonggao order by id desc";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgonggao gonggao=new Tgonggao();
				
				gonggao.setId(rs.getString("id"));
				gonggao.setTitle(rs.getString("title"));
				gonggao.setContent(rs.getString("content"));
				gonggao.setShijian(rs.getString("shijian"));
				
				gonggaoList.add(gonggao);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		if(gonggaoList.size()>5)
		{
			gonggaoList=gonggaoList.subList(0, 5);
		}
		req.getSession().setAttribute("gonggaoList", gonggaoList);
		
		
		
		req.setAttribute("goodsList", liuService.get_goods_qian5());
		req.setAttribute("gongsiList", liuService.get_gongsi_qian5());
		
		req.getRequestDispatcher("qiantai/index.jsp").forward(req, res);
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
