/*
 * ItemListBean.java
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
public class ItemListBean {
    
    private String url = null;
    private  ArrayList itemList;
    
      public ItemListBean() throws Exception{
      this("jdbc:mysql://localhost:3306/webshop?user=root&password=hjkl;'");
      }
    public ItemListBean(String _url) throws Exception{
        url = _url;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        itemList = new ArrayList();
        try{
             // get a database connection and load the JDBC-driver

            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url);
            
            // create SQL statements to load the items into the list
            // each item is a object
            stmt = conn.createStatement();
            String sql = "select *from item";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                ItemBean item = new ItemBean();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setStock(rs.getInt("stock"));
                itemList.add(item);
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
    //return the itemList
    public ArrayList<ItemBean>  getItemList(){
        return itemList;
    }
    
//    //create an XML file from the itemList
//    
//    public String getXml(){
//        //ItemBean item = null;
//        Iterator it = itemList.iterator();
//        StringBuffer buff = new StringBuffer();
//        buff.append("<itemlist>");
//        while(it.hasNext()){
//            ItemBean item;
//            item = (ItemBean)it.next();
//            buff.append(item.getXml());
//        }
//        buff.append("</itemlist>");
//        xml = buff.toString();
//        return xml;
//        
//    }
//    
//     public static void main(String[] args){
//        try{
//	    ItemListBean ilb = new ItemListBean();
//	    System.out.println(ilb.getXml());
//        }
//        catch(Exception e){
//	    System.out.println(e.getMessage());
//        }
//    }
//    
}
