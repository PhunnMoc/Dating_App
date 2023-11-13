<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Account"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<div align="center">
		<%
		String errMsg = (String) request.getAttribute("errMsg");
		Account account = (Account) session.getAttribute("user");
		%>

		<%
		if (account != null) {
		%>
		response.sendRedirect("loginsuccess.jsp");
		<%
		}
		%>

		<%
		if (errMsg != null) {
		%>
		<p style="color: red; font-weight: bold"><%=errMsg%></p>
		<%
		}
		%>
		<h1>Login Form</h1>

		<form action="<%=request.getContextPath()%>/login" method="post">
			<table style="with: 100%">
				<tr>
					<td>UserName</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>

			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>