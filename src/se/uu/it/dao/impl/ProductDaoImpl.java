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
		
                
                   int product_id = this.getProductIdByName(product.getName());
            for(int i = 0;i<list.size();i++ ){
                String sql3 = " insert into product_item (product_id,item_id) values (?,?)";
                int item_id = Integer.parseInt(list.get(i));
             try {
                PreparedStatement pstmt2 = conn.prepareStatement(sql3);
                pstmt2.setInt(1,product_id);
                pstmt2.setInt(2, item_id);
                pstmt2.executeUpdate();
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

    public void update(ProductBean product,List<String> list) {
           int id = product.getId();
           String sql = " update product set name=?, price=? where id = ?";
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
                                                    pstmt.setInt(3,id);
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
            List<Integer> componentsOld = this.getComponentsByProductId(id);
            List<Integer> componentsNew = new ArrayList<Integer>();
            for(int i=0;i<list.size();i++ ){
                int num = Integer.parseInt(list.get(i));
                componentsNew.add(num);
            }            
            List<Integer> componentsNew2 = new ArrayList<Integer>(componentsNew);
            
            componentsNew.removeAll(componentsOld);
            
            for(int i = 0;i<componentsNew.size();i++ ){
                String sql3 = " insert into product_item (product_id,item_id) values (?,?)";
                int item_id = componentsNew.get(i);
             try {
                PreparedStatement pstmt2 = conn.prepareStatement(sql3);
                pstmt2.setInt(1,id);
                pstmt2.setInt(2, item_id);
                pstmt2.executeUpdate();
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
            
            componentsOld.removeAll(componentsNew2);
            for(int i = 0;i<componentsOld.size();i++ ){
                String sql4 = " delete from product_item where product_id =? and  item_id = ? ";
                int item_id = componentsOld.get(i);
                try {
                PreparedStatement pstmt2 = conn.prepareStatement(sql4);
                pstmt2.setInt(1,id);
                pstmt2.setInt(2, item_id);
                pstmt2.executeUpdate();
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
            util.closeConnection(conn);
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
    
    public List<Integer> getComponentsByProductId(int id){
        String sql = " select item_id from product_item where product_id = ? ";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                          PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1,id);
	ResultSet rs = pstmt.executeQuery();
                          ArrayList<Integer> componentList = new ArrayList<Integer>();
                             while(rs.next()){
                                 int item_id = rs.getInt(1);
                                 componentList.add(item_id);
                             }
                             return componentList;
            }catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    public void delete(int id) {
               String sql = " delete from product_item where product_id =? ";
               String sql2 = " delete from product where id = ? ";
               DBUtil util = new DBUtil();
               Connection conn = util.getConnection();
     try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1,id);
                pstmt.executeUpdate();
                pstmt = conn.prepareStatement(sql2);
                pstmt.setInt(1,id);
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
    
    public Integer getProductIdByName(String name){
        String sql2 = " select id from product where name = ?";
        DBUtil util = new DBUtil();
        Connection conn = util.getConnection();
        try {
                PreparedStatement pstmt1 = conn.prepareStatement(sql2);
                pstmt1.setString(1,name);
                ResultSet rs = pstmt1.executeQuery();
                int product_id = 0;
                while(rs.next()){
                product_id = rs.getInt(1);
                return product_id;
                }
                 }catch (SQLException e) {
                   e.printStackTrace();
                    }
                    return null;
                }
      
}
