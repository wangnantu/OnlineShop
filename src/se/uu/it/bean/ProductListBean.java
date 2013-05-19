/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.uu.it.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author wnt
 */
public class ProductListBean {
    
    private String url = null;
    private  ArrayList productList;
    
    public ProductListBean()throws Exception{
      this("jdbc:mysql://localhost:3306/webshop?user=root&password=hjkl;'");
      }
    public ProductListBean(String _url)throws Exception{
        url = _url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        productList = new ArrayList();
        try{
             // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            // create SQL statements to load the products into the list
            // each product is a object
            stmt = conn.createStatement();
            String sql = "select *from product";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                ProductBean product = new ProductBean();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                productList.add(product);
            }
            
        }catch(SQLException sqle){
            throw new Exception(sqle);
        }
          finally{
 	    try{
              rs.close();
            }
            catch(Exception e) {}
            try{
              stmt.close();
            }
	    catch(Exception e) {}
            try {
              conn.close();
            }
            catch(Exception e){}
        }
        
    }

    //return the productList
    public ArrayList<ProductBean> getProductList(){
        return productList;
    }
    
    //create an XML file from productList
//    public String getXml(){
//        ProductBean product = null;
//        Iterator it = productList.iterator();
//        StringBuffer buff = new StringBuffer();
//        buff.append("<productlist>");
//        while(it.hasNext()){
//            product = (ProductBean)it.next();
//              buff.append(product.getXml());
//        }
//          buff.append("</productlist>");
//          
//        return buff.toString();
//    }
    
//     public static void main(String[] args){
//        try{
//	   ProductListBean plb = new ProductListBean();
//	    System.out.println(plb.getXml());
//        }
//        catch(Exception e){
//	    System.out.println(e.getMessage());
//        }
//    }
    
}
