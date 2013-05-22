<%-- 
    Document   : checkout
    Created on : 2013-5-22, 18:29:36
    Author     : wnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
    </head>
    <body>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <div align ="center">
            <form method="POST" action="order.html?action=pay">
                <h2 align="center">Checkout</h2>
                    The total price of your order is 
                    <%= request.getAttribute("totalPrice")%>
                    <br/>
                    <input type="submit" value="Pay" align="center">
                    
            </form>
        </div>
    </body>
</html>
