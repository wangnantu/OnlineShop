<%-- 
    Document   : orderDetail
    Created on : 2013-5-23, 16:33:13
    Author     : wnt
--%>

<%@page import="se.uu.it.bean.ProductBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
    </head>
    <body>
        <br>
        <br>
        <br>
        <div align="center">
            <h2>Order Detail</h2>
        <table border="0" cellspacing="5" align="center">
                <tr>
                    <th> &nbsp&nbsp;Name&nbsp&nbsp;  </th>
                    <th>  &nbsp&nbsp;Price&nbsp&nbsp;</th>
                     <th> &nbsp&nbsp;Quantity&nbsp&nbsp;</th>
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
                    <td>&nbsp&nbsp;<%=product.getName()%>&nbsp&nbsp;</td>
                    <td>&nbsp&nbsp;<%=product.getPrice()%>&nbsp&nbsp;</td>
                    <td align="center">&nbsp&nbsp;<%=quantity%>&nbsp&nbsp;</td>
                </tr>
                <%}%>
         </table>
         <hr/>
     </div>
    </body>
</html>
