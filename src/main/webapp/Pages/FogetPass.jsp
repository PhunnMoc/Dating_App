<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="../Access/Style/css/Login.css" />
</head>
<body>
    <form action="<%=request.getContextPath()%>/send-email" method = "post" accept-charset="UTF-8">
          <h1>Quên mật khẩu</h1>

          <input type="email" placeholder="Nhập email của bạn" id="email" name="recipient"/>
          
			<p><%=request.getAttribute("Message")%></p>
			<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Pages/Login.jsp">Đăng nhập</a>
          <button type="submit">Xác thực</button>
        </form>
      </div>
</html>