<%-- 
    Document   : admin
    Created on : 2013-5-12, 16:21:52
    Author     : wnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <div align="center">
            <br/>
            <br/>
            <br/>
        Welcome, <%= session.getAttribute("username") %>
        <br>
        <a href="item.html" >Item Manage</a>
        <br>
        <a href="product.html">Product Manage</a>
        <br>
        <a href="order.html?action=list">Order Processing</a>
        </div>
    </body>
</html>
