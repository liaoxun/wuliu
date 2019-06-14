package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;


import com.dao.DB;
import com.orm.TAdmin;
import com.orm.Tuser;
import com.orm.Tgongsi;

public class loginService
{
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result="no";
		
		if(userType==0)//ÏµÍ³¹ÜÀíÔ±µÇÂ½
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		
		if(userType==1)
		{
			String sql="select * from t_user where del='no' and loginname=? and loginpw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					result="yes";
					Tuser user=new Tuser();
					user.setId(rs.getString("id"));
					user.setLoginname(rs.getString("loginname"));
					user.setLoginpw(rs.getString("loginpw"));
					user.setLoginpw(rs.getString("loginpw"));
					user.setName(rs.getString("name"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getString("age"));
					user.setAddress(rs.getString("address"));
					user.setTel(rs.getString("tel"));
					user.setEmail(rs.getString("email"));
					user.setQq(rs.getString("qq"));
					
					WebContext ctx = WebContextFactory.get(); 
					HttpSession session=ctx.getSession(); 
					session.setAttribute("userType", 1);
		            session.setAttribute("user", user);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
		}
		if(userType==2)
		{
			String sql="select * from t_gongsi where del ='no' and zhuangtai='b' and loginname=? and loginpw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					result="yes";
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
					
					WebContext ctx = WebContextFactory.get(); 
					HttpSession session=ctx.getSession(); 
					session.setAttribute("userType", 2);
		            session.setAttribute("gongsi", gongsi);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
		}
		return result;
	}

    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
    
    
    public List zhuanyeAll()
    {
    	try
		{
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List zhuanyeList=new ArrayList();
		String sql="select * from t_zhuanye where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return zhuanyeList;
    }
}
