/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import se.uu.it.bean.OrderBean;
import se.uu.it.dao.OrderDao;
import se.uu.it.util.DBUtil;

/**
 *
 * @author wnt
 */
public class OrderDaoImpl implements OrderDao{

        public void save(OrderBean order, List<String>  products, List<Integer> quaList) {
            String sql = "  insert into `order` (user_id,orderdate)  values  (?,?) ";
            DBUtil util = new DBUtil();
            Connection conn = util.getConnection();
              try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
        try {
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1,order.getUser_id());
	pstmt.setString(2,order.getOrderdate());
	pstmt.executeUpdate();
	conn.commit();  
        
        int order_id = this.getOrderId(order.getUser_id(), order.getOrderdate());
         for(int i=0;i<products.size();i++){
             String sql1 = " insert into order_product (order_id,product_id,quantity) values (?,?,?)";
             int product_id = Integer.parseInt(products.get(i));
             int quantity = quaList.get(i);
             try{
                 PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                 pstmt1.setInt(1, order_id);
                 pstmt1.setInt(2, product_id);
                 pstmt1.setInt(3, quantity);
                 pstmt1.executeUpdate();
                conn.commit();
                
            } catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    } 
                                                                  
                }
            }
      } catch (SQLException e) {
	e.printStackTrace();
	try {
	             conn.rollback();
	       } catch (SQLException e1) {
	            e1.printStackTrace();
	       }
                }
        util.closeConnection(conn);
             
            
    }

    public void update() {
    }

    public void delete() {
    }

    public void list() {
    }
    
    public Integer getOrderId(int user_id, String order_date){
        String sql = " select id from `order` where user_id = ? and orderdate = ?";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,user_id);
                pstmt.setString(2, order_date);
                ResultSet rs = pstmt.executeQuery();
                int order_id = 0;
                while(rs.next()){
                order_id = rs.getInt(1);
                return order_id;
                }
                 }catch (SQLException e) {
                   e.printStackTrace();
                    }
            return null;
    }
    
}
