<%-- 
    Document   : orderList
    Created on : 2013-5-23, 15:12:40
    Author     : wnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Processing</title>
    </head>
    <body>
        <div align="center">
            <h2>Received Orders</h2>
        </div>
        <table border="0" cellspacing="5" align="center">
            <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>OrderDate</th>
                <th></th>
            </tr>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.id}</td>
                    <td align="center">${order.user_id}</td>
                    <td>${order.orderdate}</td>
                    <td><a href="order.html?action=detail&id=${order.id}">Detail</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
