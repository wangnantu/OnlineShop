<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Sign in </title>
</head>
<body>
	<form method="POST" action="/OnlineShop/servlet/RegisterServlet" >
  <table border="0" cellspacing="5" align="center">
    <tr>
      <td colspan="2" bgcolor="#FFDC75"align="center">
          <h2>Sign in</td> </h2>
    </tr>
    <tr><td colspan="2"></td></tr>
    <tr>
      <th align="right">Username:</th>
      <td align="left"><input type="text" name="username"></td>
    </tr>
    <tr>
      <th align="right">Password:</th>
      <td align="left"><input type="password" name="password"></td>
    </tr>
    <tr>
      <td align="center"><input type="submit" value="OK"></td>
      <td align="center"><input type="reset" value="Cancel"></td>
    </tr>
  </table>

</form>
</body>
</html>