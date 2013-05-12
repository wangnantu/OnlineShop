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
        Welcome, <%= session.getAttribute("username") %>
        <br>
        <a href="item.html" >Item</a>
    </body>
</html>
