<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Account"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login success</title>
</head>
<body>


	<div align="center">
		<%
		Account account = (Account) session.getAttribute("user");
		%>
		<%
		if (account == null) {
		%>
		<%
		response.sendRedirect("login.jsp");
		%>
		<%
		} else {
		%>
		<p>
			Username:
			<%=account.getUsername()%></p>
		
		<%
		}
		%>
		<h1>You have logined successfully</h1>
	</div>
</body>
</html>