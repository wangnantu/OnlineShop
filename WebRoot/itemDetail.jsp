<%-- 
    Document   : itemDetail
    Created on : 2013-5-9, 13:41:58
    Author     : wnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Detail</title>
        
    </head>
    <body>
       <form method="POST" action="/OnlineShop/servlet/AddServlet">
            <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Item Detail</td> </h2>
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
    </body>
</html>
