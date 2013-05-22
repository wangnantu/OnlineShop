<%-- 
    Document   : main
    Created on : 2013-5-12, 16:36:03
    Author     : wnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shop</title>
    </head>
    <body>
        <div align="center">
        Welcome, 
        <%
               Boolean login = session.getAttribute("username") != null;
                %>
        <c:choose>    
        <c:when test="${login}">
            <%=session.getAttribute("username")%>
            <br/>
            <a href="order.html">My Cart</a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp">login</a>
        </c:otherwise>
            </c:choose>
            </div>
            <br/>
            <h2 align="center">Product</h2>
            <form method="POST" action="shopping.html?action=add">
            <table border="1" cellspacing="5" align="center">
                <tr>
                    <th> &nbsp&nbsp;Name&nbsp&nbsp;  </th>
                    <th>  &nbsp&nbsp;Price&nbsp&nbsp;</th>
                     <th> &nbsp&nbsp;Quantity&nbsp&nbsp;</th>
                     <th></th>
                </tr>
            <c:forEach var="product" items="${productList}">
                <tr>
                     <td>${product.name}</td>
                     <td>${product.price}</td>
                     <td align="center"><input type="text" name="${product.id}+quantity" size="2" ></td>
                     <td><input type="checkbox" name="products" value="${product.id}"></td>
                </tr>     
           </c:forEach>
                <tr >
                    <th colspan="4" align="right">
                    <input type="submit" value="Add to Cart" >
                    </th>
                </tr>
            </table>
          </form>
    </body>
</html>
