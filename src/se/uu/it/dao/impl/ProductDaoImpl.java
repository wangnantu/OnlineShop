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
import se.uu.it.bean.ProductBean;
import se.uu.it.dao.ProductDao;
import se.uu.it.util.DBUtil;

/**
 *
 * @author wnt
 */
public class ProductDaoImpl  implements ProductDao  {

    public void save(ProductBean product, List<String> list) {
        String sql = "  insert into product (name,price) values (?,?) ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
	conn.setAutoCommit(false);
    } catch (SQLException e2) {
	e2.printStackTrace();
    }
        try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,product.getName());
			pstmt.setFloat(2,product.getPrice());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
                }
            for (String item_name : list) {
                String sql2 = " insert into product_item (product_name,item_name) values (?,?)";
                try {
                PreparedStatement pstmt1 = conn.prepareStatement(sql2);
                pstmt1.setString(1,product.getName());
                pstmt1.setString(2, item_name);
                pstmt1.executeUpdate();
                conn.commit();
                
            } catch (SQLException ex) {
                   ex.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        ex1.printStackTrace();
                    } finally{
	util.closeConnection(conn);
		}
                                                                  
                }
            }
       
    }

    public void update(ProductBean product,List<String> list) {
           int id = product.getId();
           String sql = " update product set name=?, price=? where id = ?";
           
    }

    public ProductBean getProductById(int id) {
          String sql = " select name, price from product where id = ? ";
          DBUtil util = new DBUtil();
          Connection conn = util.getConnection();
                      try {
                          PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1,id);
	ResultSet rs = pstmt.executeQuery();
                            while(rs.next()){
		String name = rs.getString(1);
		float price = rs.getFloat(2);
                                                    ProductBean product = new ProductBean();
                                                    product.setId(id);
                                                    product.setName(name);
                                                    product.setPrice(price);
                                                    return product;
                                        }
                            conn.commit();
                      }catch (SQLException e) {
		e.printStackTrace();
		try {
		conn.rollback();
		} catch (SQLException e1) {
		         e1.printStackTrace();
                                                            }
		}finally{
			util.closeConnection(conn);
		}
                                                      return null;
    }
    
    public List<String> getComponentsByProductName(String name){
        String sql = " select item_name from product_item where product_name = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                          PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1,name);
	ResultSet rs = pstmt.executeQuery();
                          ArrayList<String> componentList = new ArrayList<String>();
                             while(rs.next()){
                                 String itemName = rs.getString(1);
                                 componentList.add(itemName);
                             }
                             return componentList;
            }catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    public void delete(int id) {
    }

    public List<ProductBean> list() {
                            String sql = "select * from product";
                            DBUtil util = new DBUtil();
                            Connection conn = util.getConnection();
                            ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
                            try {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
                                                                              int id = rs.getInt(1);
			String name = rs.getString(2);
			float price = rs.getFloat(3);
                                                                              ProductBean product = new ProductBean();
                                                                              product.setId(id);
                                                                              product.setName(name);
                                                                              product.setPrice(price);
                                                                              productList.add(product);
                                                     }
                                                     return productList;
                                                     
                            }catch (SQLException e) {
			e.printStackTrace();
		}                                                                              
                                                    
        return null;
    }
    
}
