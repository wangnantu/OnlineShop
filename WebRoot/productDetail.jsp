<%-- 
    Document   : productDetail
    Created on : 2013-5-12, 22:06:46
    Author     : wnt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
    </head>
    <body>
        <form method="POST" action="product.html?action=update&id=${item.getId()}">
            <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Product Detail</td> </h2>
    </tr>
    <tr>
      <th align="right">Name:</th>
      <td align="left"><input type="text" name="name" value="${product.name}"></td>
    </tr>
    <tr>
      <th align="right">Price:</th>
      <td align="left"><input type="text" name="price" value="${product.price}"></td>
    </tr>
    
 <tr>
      <th align="right">Components:</th>
      <td align="left">
              <c:forEach var="item" items="${itemList}" varStatus="status">
                      ${item.name}
                      <c:choose>
                         <c:when test = "${components.contains(item.id)}">
                            <input type="checkbox" name="components" value="${item.id}"  checked>
                        </c:when>
                        <c:otherwise>
                          <input type="checkbox" name="components" value="${item.id}"  />
                        </c:otherwise>
                      </c:choose>
                  <c:if test="${status.count%2==0}"><br/></c:if>
              </c:forEach>
      </td>
    </tr>
    <tr>
      <td align="right"><input type="submit" value="Update"></td>
    </tr>
        </form>
    </body>
</html>
