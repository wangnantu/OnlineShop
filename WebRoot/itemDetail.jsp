<%-- 
    Document   : itemDetail
    Created on : 2013-5-9, 13:41:58
    Author     : wnt
--%>


<%@page import="se.uu.it.bean.ItemBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Detail</title>
        
    </head>
    <body>
          <%
            ItemBean item = (ItemBean)  request.getAttribute("item");
            %>
        <form method="POST" action="item.html?action=update&id=${item.getId()}">
            <table border="0" cellspacing="5" align="center">
    <tr>
       
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Item Detail</td> </h2>
    </tr>
    <tr>
      <th align="right">Name:</th>
      <td align="left"><input type="text" name="name" value="${item.getName()}"></td>
    </tr>
    <tr>
      <th align="right">Price:</th>
      <td align="left"><input type="text" name="price" value="${item.getPrice()}"></td>
    </tr>
    <tr>
      <th align="right">Stock:</th>
      <td align="left"><input type="text" name="stock" value="${item.getStock()}"></td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Update"></td>
    </tr>
        </form>
    </body>
</html>
