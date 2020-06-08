package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bookstore.pojo.Order;
import com.bookstore.utility.DBUtility;

public class OrderDaoImpl implements OrderDao {

	ResultSet rs=null;
	PreparedStatement ps=null;
	String query;
	int ans;
	Connection con=DBUtility.establishConnection();
	@Override
	public boolean placeOrder(String emailId) {

		query="select sum(totalPrice) from cart22377 where emailId=?";
		try
		{
			float fp=0.0f;
			 ps=con.prepareStatement(query);
			ps.setString(1,emailId);
			ResultSet r=ps.executeQuery();
			if(r.next())
			{
				fp=r.getFloat("finalPrice");
				
			}
			String date=new Date().toString();
			
			query="insert into order22377(emailId,totalBill,orderDate) values(?,?,?)";
			ps=con.prepareStatement(query);
			ps.setString(1, emailId);
			ps.setFloat(2, fp);
			ps.setString(3, date);
			ans=ps.executeUpdate();
			if(ans>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
			
		}
		catch(Exception e)
		{
			
	    e.printStackTrace();
		}
		return false;
	}

	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		query="update order22377 set status=? where orderId=?";
		try {
			PreparedStatement prp=con.prepareStatement(query);
			prp.setString(1, order.getStatus());
			prp.setInt(2, order.getOrderId());
			ans=prp.executeUpdate();
			if(ans>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteOrder(int orderId) {

		query="delete from order22377 where orderId=?";
		
		try {
			ps=con.prepareStatement(query);
			
			ps.setInt(1, orderId);
			
			ans=ps.executeUpdate();
			if(ans>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean clearOrder(String emailId) {

		query="delete from order22377 where emailId=?";
		
		try {
			ps=con.prepareStatement(query);
			
			
			ps.setString(1, emailId);
			
			ans=ps.executeUpdate();
			if(ans>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Order> showMyOrder(String emailId) {
  List<Order> l=new ArrayList<Order>();
		query=" select orderId,emailId,totalBill,date,status where emailId=? ";
		
		try {
			ps=con.prepareStatement(query);
			
			ps.setString(1, emailId);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				Order o=null;
				o.setOrderId(rs.getInt(1));
				o.setEmailId(rs.getString(2));
				o.setTotalBill(rs.getLong(3));
				o.setDate(rs.getString(4));
				o.setStatus(rs.getString(5));
				
				l.add(o);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return l;
	}

	@Override
	public List<Order> showAllOrder() {
		List<Order> l=new ArrayList<Order>();
		query=" select orderId,emailId,totalBill,date,status  ";
		
		try {
			ps=con.prepareStatement(query);
			
			
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				Order o=null;
				o.setOrderId(rs.getInt(1));
				o.setEmailId(rs.getString(2));
				o.setTotalBill(rs.getLong(3));
				o.setDate(rs.getString(4));
				o.setStatus(rs.getString(5));
				
				l.add(o);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return l;
	}
}
