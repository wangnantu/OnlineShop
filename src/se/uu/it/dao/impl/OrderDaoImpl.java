/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import se.uu.it.bean.OrderBean;
import se.uu.it.bean.ProductBean;
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

    public void add(OrderBean order, List<String>  products,  List<Integer> quaList) {
        List<Integer> productsOld = this.getProductsByOrderId(order.getId());
        List<Integer> productsNew = new ArrayList<Integer>();
        for(int i=0;i<products.size();i++ ){
                int num = Integer.parseInt(products.get(i));
                productsNew.add(num);
            }            
        for(int j=0; j<productsNew.size();j++){
            if(productsOld.contains(productsNew.get(j))){
                int quantityOld = this.getQuantity(order.getId(), productsNew.get(j));
                int quantityNew = quaList.get(j)+quantityOld;
                 String sql = " update order_product set quantity = ? where order_id = ? and product_id = ? ";
                 DBUtil util = new DBUtil();
                 Connection conn = util.getConnection();
                  try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
                 try {
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1,quantityNew);
	pstmt.setInt(2,order.getId());
                          pstmt.setInt(3,productsNew.get(j));
	pstmt.executeUpdate();
	conn.commit();  
                      }catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    } 
                                                                  
                }
        }else{
                String sql1 = " insert into order_product (order_id,product_id,quantity) values (?,?,?)";
                DBUtil util = new DBUtil();
                Connection conn = util.getConnection();
                try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
                 try{
                 PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                 pstmt1.setInt(1, order.getId());
                 pstmt1.setInt(2, productsNew.get(j));
                 pstmt1.setInt(3, quaList.get(j));
                 pstmt1.executeUpdate();
                conn.commit();
            }catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    } 
                                                                  
                }
        
          }
        }
    }

    public void update(int order_id, int product_id, int quantity){
        String sql = " update order_product set quantity = ? where order_id=? and product_id=? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
          try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
          try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, quantity);
                pstmt.setInt(2,order_id);
                pstmt.setInt(3,product_id);
                 pstmt.executeUpdate();
                conn.commit();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }                                            
                }finally{
         util.closeConnection(conn);
          }
    }
    public void deleteProduct(int order_id,int product_id) {
        String sql = " delete from order_product where order_id = ? and product_id = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
          try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,order_id);
                pstmt.setInt(2,product_id);
                pstmt.executeUpdate();
                conn.commit();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }                                            
                }finally{
         util.closeConnection(conn);
     }
     
    }

    //return products list by order id
    public List<ProductBean> getProductListByOrderId(int order_id) {
        String sql = " select  product_id, name , price from order_product, product where order_id = ? and product_id = product.id order by product_id";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,order_id);
                ResultSet rs = pstmt.executeQuery();
                List<ProductBean>  products = new ArrayList<ProductBean>();
                while(rs.next()){
                    ProductBean product = new ProductBean();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getFloat(3));
                    products.add(product);
                }
                return products;
        }catch (SQLException e) {
                   e.printStackTrace();
                    }
            return null;
    }
    
    //return quantites of  products by order id
    public List<Integer> getUnPaidQuaList(int order_id){
        String sql = " select quantity from order_product where order_id = ? order by product_id";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,order_id);
                ResultSet rs = pstmt.executeQuery();
                List<Integer> quaList = new ArrayList<Integer>();
                while(rs.next()){
                    int quantity = rs.getInt(1);
                    quaList.add(quantity);
                }
                return quaList;
        }catch (SQLException e) {
                   e.printStackTrace();
                    }
        return null;
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
    
    public Boolean haveUnpaidOrder(int user_id){
        String sql = " select id from `order` where user_id = ? and paid = 0 ";
        DBUtil util = new DBUtil();
       Connection conn = util.getConnection();
       try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,user_id);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    return true;
                }else{
                    return false;
                }
              }catch (SQLException e) {
                   e.printStackTrace();
                    }
            return null;
    }

    public OrderBean getUnPaidOrderByUserId(int user_id) {
        String sql = " select id,orderdate from `order` where user_id = ? and paid = 0 ";
        DBUtil util = new DBUtil();
       Connection conn = util.getConnection();
       try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,user_id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    int id = rs.getInt(1);
                    String orderdate = rs.getString(2);
                    OrderBean order = new OrderBean();
                    order.setId(id);
                    order.setOrderdate(orderdate);
                     return order;
                }
       }catch (SQLException e) {
                   e.printStackTrace();
                    }
            return null;
    }
    
    public List<Integer> getProductsByOrderId(int id){
        String sql = " select product_id from order_product where order_id = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                          PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1,id);
	ResultSet rs = pstmt.executeQuery();
                          ArrayList<Integer> productList = new ArrayList<Integer>();
                          while(rs.next()){
                              int product_id = rs.getInt(1);
                              productList.add(product_id);
                          }
                          return productList;
                  }catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public Integer getQuantity(int order_id, int product_id){
        String sql = " select quantity from order_product where order_id = ? and product_id = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                          PreparedStatement pstmt = conn.prepareStatement(sql);
                          pstmt.setInt(1, order_id);
                          pstmt.setInt(2,product_id);
                          ResultSet rs = pstmt.executeQuery();
                          while(rs.next()){
                              int quantity = rs.getInt(1);
                               return quantity;
                          }
        }catch (SQLException e) {
                   e.printStackTrace();
                    }
            return null;
    }
    
    public void payOrder(int order_id){
        String sql = " update `order` set paid = true where id = ?";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
          try {
	conn.setAutoCommit(false);
                    } catch (SQLException e2) {
	e2.printStackTrace();
                   }
          try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,order_id);
                 pstmt.executeUpdate();
                conn.commit();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    }                                            
                }finally{
         util.closeConnection(conn);
          }
    }
    
    public List<OrderBean> list(){
        String sql = "select * from `order` where paid = true and delivered = false";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        ArrayList<OrderBean> orderList = new ArrayList<OrderBean>();
        try {
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
                                int id = rs.getInt(1);
                                int user_id = rs.getInt(2);
                                String orderdate = rs.getString(3);
                                OrderBean order =new OrderBean();
                                order.setId(id);
                                order.setUser_id(user_id);
                                order.setOrderdate(orderdate);
                                order.setPaid(Boolean.TRUE);
                                order.setDelivered(Boolean.FALSE);
                                orderList.add(order);
                          }
                          return orderList;
        }catch (SQLException e) {
	e.printStackTrace();
	}
	return null;
    }
}
