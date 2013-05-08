package se.uu.it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import se.uu.it.bean.ItemBean;
import se.uu.it.dao.ItemDao;
import se.uu.it.util.DBUtil;

public class ItemDaoImpl implements ItemDao {

	public void save(ItemBean item) {
		String sql = " insert into item (name,price,stock) values (?,?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,item.getName());
			pstmt.setFloat(2,item.getPrice());
			pstmt.setInt(3,item.getStock());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			util.closeConnection(conn);
		}
		
	}

	public void update(ItemBean item) {
		String sql = " update item set name = ? , price = ? , stock = ? where id = ? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,item.getName());
			pstmt.setFloat(2,item.getPrice());
			pstmt.setInt(3,item.getStock());
			pstmt.setInt(4, item.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			util.closeConnection(conn);
		}
		
	}

	public void delete(ItemBean item) {
		String sql = "delete from item where id = ?";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,item.getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			util.closeConnection(conn);
		}
	}

	public List<ItemBean> list() {
		String sql = "select * from item";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		ArrayList<ItemBean> itemList = new ArrayList<ItemBean>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				int stock = rs.getInt(4);
				ItemBean item = new ItemBean();
				item.setId(id);
				item.setName(name);
				item.setPrice(price);
				item.setStock(stock);
				itemList.add(item);
			}
				return itemList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
