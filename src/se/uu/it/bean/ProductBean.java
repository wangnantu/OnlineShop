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
public class ProductBean {


    private int id;
    private String name;
    private float price;
    private ArrayList<String> componentsList;
    
//    public ProductBean() throws Exception{
//        String url = "jdbc:mysql://localhost:3306/webshop?user=root&password=hjkl;'";
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        componentsList = new ArrayList<String>();
//        try{
//             // get a database connection and load the JDBC-driver
//
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection(url);
//            
//            // create SQL statements to load the items into the list
//            // each item is a object
//            stmt = conn.createStatement();
//            String sql = "select item_id from product_item where product_id = "+id;
//            rs = stmt.executeQuery(sql);
//            while(rs.next()){
//                int item_id =rs.getInt("item_id");
//                String sql2 = "select name from item where id = "+item_id;
//                ResultSet rs2 = stmt.executeQuery(sql2);
//                String item_name = rs2.getString("name");
//                componentsList.add(item_name);
//                
//            }
//            
//        }catch(SQLException sqle){
//            throw new Exception(sqle);
//        }
//          finally{
// 	    try{
//              rs.close();
//            }
//            catch(Exception e) {}
//            try{
//              stmt.close();
//            }
//	    catch(Exception e) {}
//            try {
//              conn.close();
//            }
//            catch(Exception e){}
//        }
//    }
    
    
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public ArrayList<String> getComponents() {
        
        return componentsList;
    }
    
//    public String getXml(){
//        StringBuffer xmlOut = new StringBuffer();
//         xmlOut.append("<product>");
//         xmlOut.append("<id>");
//         xmlOut.append(id);
//         xmlOut.append("</id>");
//         xmlOut.append("<name>");               
//         xmlOut.append(name);
//         xmlOut.append("</name>");
//         xmlOut.append("<price>");
//         xmlOut.append(price);
//         xmlOut.append("</price>");
//         
//         Iterator it = componentsList.iterator();
//          while(it.hasNext()){
//         xmlOut.append("<component>");
//         xmlOut.append((String)it.next());
//         xmlOut.append("</component>");
//          }
//          xmlOut.append("<product>");
//        return xmlOut.toString();
//        
//    }
    
}
