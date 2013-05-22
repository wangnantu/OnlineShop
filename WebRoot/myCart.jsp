<%-- 
    Document   : myOrder
    Created on : 2013-5-22, 12:56:23
    Author     : wnt
--%>

<%@page import="se.uu.it.bean.ProductBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order</title>
    </head>
    <body>
        <h2 align =" center">My Cart</h2>
        <form method="POST" action="order.html?action=checkout">
            <table border="0" cellspacing="5" align="center">
                <tr>
                    <th>&nbsp&nbsp;ID&nbsp&nbsp;</th>
                    <th> &nbsp&nbsp;Name&nbsp&nbsp;  </th>
                    <th>  &nbsp&nbsp;Price&nbsp&nbsp;</th>
                     <th> &nbsp&nbsp;Quantity&nbsp&nbsp;</th>
                     <th></th>
                </tr>
                  <%
                List products  = (List)request.getAttribute("products");
                ProductBean product = new ProductBean();
                List quaList = (List)request.getAttribute("quaList");
                for(int i=0;i<products.size();i++){
                    product = (ProductBean) products.get(i);
                    int quantity = (Integer)quaList.get(i);
                %>
                <tr>
                    <td>&nbsp&nbsp;<%=product.getId()%>&nbsp&nbsp;</td>
                    <td>&nbsp&nbsp;<%=product.getName()%>&nbsp&nbsp;</td>
                    <td>&nbsp&nbsp;<%=product.getPrice()%>&nbsp&nbsp;</td>
                    <td>&nbsp&nbsp;<input type="text" name="<%=product.getId()%>+quantity" size="2"  value="<%=quantity%>">&nbsp&nbsp;</td>
                    <td><a href="order.html?action=delete&id=<%=product.getId()%>">Delete</a></td>
                </tr>
                <%}%>
                <tr/>
                <tr/>
                <tr>
                    <td colspan="4" align="right"><input type="submit" value="Checkout"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
