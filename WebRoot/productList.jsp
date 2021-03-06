<%-- 
    Document   : ProductList
    Created on : 2013-5-12, 23:02:37
    Author     : wnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Manage</title>
    </head>
    <body>
        <form method="POST" action="product.html?action=add">
            <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Add a new Product</td> </h2>
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
      <th align="right">Components:</th>
      <td align="left">
              <c:forEach var="item" items="${itemList}" varStatus="status">
                      ${item.name}
                  <input type="checkbox" name="components" value="${item.id}"/>
                  <c:if test="${status.count%2==0}"><br/></c:if>
              </c:forEach>
      </td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Add"></td>
    </tr>
        </form>
        
        <hr/>
        <table border="0" cellspacing="5" align="center">
            <tr>
                <th>Name</td>
                <th>Price</td>
            </tr>
            
                <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td><a href="product.html?action=get&id=${product.getId()}">Edit</a></td>
                    <td><a href="product.html?action=delete&id=${product.getId()}">Delete</a></td>
                </tr>
                </c:forEach>
           
    </body>
</html>
