package se.uu.it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.uu.it.bean.UserBean;
import se.uu.it.dao.UserDao;
import se.uu.it.util.DBUtil;

public class UserDaoImpl implements UserDao {

	
	public void save(UserBean user) {
		String sql = " insert into user (username,password) values (?,?) ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getPassword());
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

	public void update(UserBean user) {
		
	}

	public void delete(UserBean user) {
	
	}
	
	public String getPasswordFromUser(String username){
		String sql = " select password from user where username = ? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String password = rs.getString(1);
				return password;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				util.closeConnection(conn);
			}
		
		return null;
		
	}

    public Integer getIdFromUser(String username) {
        String sql = " select id from user where username = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,username);
                ResultSet rs = pstmt.executeQuery();
                 while(rs.next()){
	int id = rs.getInt(1);
	return id;
                    }
                } catch (SQLException e) {
	e.printStackTrace();
                }finally{
	util.closeConnection(conn);
                }
	return null;
		
    }

}
