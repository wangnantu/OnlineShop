<%-- 
    Document   : itemList
    Created on : 2013-5-8, 15:53:51
    Author     : wnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, se.uu.it.bean.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="/OnlineShop/item.html?action=add">
            <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Add a new Item</td> </h2>
    </tr>
    <tr>
      <th align="right">Name:</th>
      <td align="left"><input type="text" name="name"></td>
    </tr>
    <tr>
      <th align="right">Price:</th>
      <td align="left"><input type="text" name="price"></td>
    </tr>
    <tr>
      <th align="right">Stock:</th>
      <td align="left"><input type="text" name="stock"></td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Add"></td>
    </tr>
        </form>
        <hr/>
        <table border="0" cellspacing="5" align="center">
            <tr>
                <td>Name</td>
                <td>Price</td>
                <td>Stock</td>
            </tr>
            <%
                List list = (List)request.getAttribute("itemList");
                ItemBean item = new ItemBean();
                for(int i=0;i<list.size();i++){
                    item = (ItemBean) list.get(i);
                %>
                <tr>
                    <td><%=item.getName()%></td>
                    <td><%=item.getPrice()%></td>
                    <td><%=item.getStock()%></td>
                    <td><a href="item.html?action=get&id=<%=item.getId()%>">Edit</a></td>
                    <td><a href="item.html?action=delete&id=<%=item.getId()%>">Delete</a></td>
                </tr>
                <%}%>
        </table>
    </body>
</html>
