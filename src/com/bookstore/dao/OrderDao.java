package com.bookstore.dao;

import java.util.List;

import com.bookstore.pojo.Order;

public interface OrderDao {
	
	boolean placeOrder(String emailId);
	boolean updateOrder(Order order);
	boolean deleteOrder(int orderId);
	boolean clearOrder(String emailId);
	List<Order>showMyOrder(String emailId);
    List<Order>showAllOrder();
	

}
