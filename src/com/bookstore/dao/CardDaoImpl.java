package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.pojo.Cart;
import com.bookstore.utility.DBUtility;

public class CardDaoImpl implements CardDao {

	Connection con = DBUtility.establishConnection();

	PreparedStatement ps = null;
	int ans;
	String query;
	ResultSet rs;

	@Override
	public boolean addToCart(Cart cart) {

		query = "select bookName,price from book22377  where bookId=? ";

		try {
			ps = con.prepareStatement(query);

			ps.setInt(1, cart.getBookId());
			rs = ps.executeQuery();

			String name = null;
			float price1 = 0f;

			if (rs.next()) {

				name = rs.getString(1);
				price1 = rs.getFloat(2);
			}

			float tp = price1 * cart.getQuantity();
			query = "insert into cart22377(bookId,bookName,price,quantity,totalPrice,emailId) values(?,?,?,?,?,?))";
			ps = con.prepareStatement(query);
			ps.setInt(1, cart.getBookId());
			ps.setString(2, cart.getBookName());
			ps.setFloat(3, cart.getPrice());
			ps.setInt(4, cart.getQuantity());
			ps.setLong(5, cart.getTotalPrice());
			ps.setString(6, cart.getEmailId());

			ans = ps.executeUpdate();
			if (ans > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateCart(Cart cart) {

		float tp = cart.getPrice() * cart.getQuantity();

		query = "update cart22377 set quantity=?,totalPrice=?where cartId=?";

		try {
			ps = con.prepareStatement(query);

			ps.setInt(1, cart.getQuantity());
			ps.setFloat(2, tp);
			ps.setInt(3, cart.getCartId());
			ans = ps.executeUpdate();
			if (ans > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteCart(int cartId) {

		query = "delete from cart22377 where cartId=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, cartId);

			ans = ps.executeUpdate();
			if (ans > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean clearCart(String emailId) {

		query = "delete from cart22377 where emailId=?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, emailId);

			ans = ps.executeUpdate();
			if (ans > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Cart> showMyCart(String emailId) {
		List<Cart> l = new ArrayList<Cart>();

		query = "select cartId,BookId,bookName,price,quantity,totalPrice,emailId  from cart22377 where emailId=? ";

		try {
			ps = con.prepareStatement(query);

			ps.setString(1, emailId);
			rs = ps.executeQuery();
			while (rs.next()) {

				Cart c = null;
				c.setCartId(rs.getInt(1));
				c.setBookId(rs.getInt(2));
				c.setBookName(rs.getString(3));
				c.setPrice(rs.getLong(4));
				c.setQuantity(rs.getInt(5));
				c.setTotalPrice(rs.getLong(6));
				c.setEmailId(rs.getString(7));

				l.add(c);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

}
