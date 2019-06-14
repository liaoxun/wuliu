package com.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.DB;
import com.orm.Tgongsi;
import com.orm.Tgoods;

public class liuService
{
	public static String getUserName(String id)
	{
		String name="";
		
		String sql="select * from t_user where id=?";
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("loginname");
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}
	
	public static List get_goods_qian5()
	{
			List goodsList=new ArrayList();
			String sql="select * from t_goods where del='no' order by id desc";
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
			if(goodsList.size()>8)
			{
				goodsList=goodsList.subList(0, 8);
			}
			
			return goodsList;
	}
	
	
	public static List get_gongsi_qian5()
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
		if(gongsiList.size()>8)
		{
			gongsiList=gongsiList.subList(0, 8);
		}
		
		return gongsiList;
	}
}
