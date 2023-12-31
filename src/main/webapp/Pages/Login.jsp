<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@page import="Models.Account"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Login</title>
	</head>
	<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    />
    <link rel="stylesheet" href="../Access/Style/css/Login.css" />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />
    
	<body>
	<%
		String err_register = (String) request.getAttribute("error_register");
		String err_login = (String) request.getAttribute("error_login");
	%>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	<%if (account != null) {%>
		<%response.sendRedirect(request.getContextPath() + "/Pages/Match.jsp"); %>
	<%} %>
	<!-- partial:index.partial.html -->
    <h2>Chào mừng đến với TeenTher</h2>
    <div class="container" id="container">
      <div class="form-container sign-up-container">
        <form action="<%=request.getContextPath()%>/pro/Register" method="post" onsubmit="return checkPassword()" accept-charset="UTF-8">
          <h1>Tạo tài khoản</h1>
   <!--        <div class="social-container">
            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
          </div> -->
<!--           <span>hoặc đăng ký bằng email</span> -->
          <input type="text" placeholder="Họ và tên" id="fullname" name="fullname" required="required"/>
          <input type="email" placeholder="Email" id="email" name="email" required="required"/>
          <% if (err_register != null) { %>
			  <div style="color: red"> <%=err_register %></div>
			<% } %>
          <input type="password" placeholder="Mật khẩu" id="password" name="password" required="required"/>
          <input type="password" placeholder="Nhập lại mật khẩu" id="re_password" name="re_password" required="required" onkeyup="checkPassword()"/>
          <span id="msg" style="color: red;"></span>
          <button type="submit">Đăng ký</button>
        </form>
      </div>
      <div class="form-container sign-in-container">
        <form action="<%=request.getContextPath()%>/pro/Login" method = "post" accept-charset="UTF-8">
          <h1>Đăng nhập</h1>

          <input type="email" placeholder="Email" id="email" name="email"/>
          <input type="password" placeholder="Mật khẩu" id="password" name="password" />
          <% if (err_login != null) { %>
			  <div style="color: red"> <%=err_login %></div>
			<% } %>
			<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Pages/FogetPass.jsp"">Quên mật khẩu?</a>
          <button type="submit">Đăng nhập</button>
        </form>
      </div>
      <div class="overlay-container">
        <div class="overlay">
          <div class="overlay-panel overlay-left">
            <h1>Chào mừng trở lại!</h1>
            <p>Để tiếp tục kết nối vui lòng đăng nhập!</p>
            <button class="ghost" id="signIn">Đăng nhập</button>
          </div>
          <div class="overlay-panel overlay-right">
            <h1>Xin chào bạn!</h1>
            <p>
              Nhập thông tin của bạn để bắt đầu hành trình của chúng ta nào!
            </p>
            <button class="ghost" id="signUp">Đăng ký</button>
          </div>
        </div>
      </div>
    </div>


    <footer>
      <p>
        Created with <i class="fa fa-heart"></i> by
        <a target="_blank" href="https://www.facebook.com/hoang.congthien.1612">TTTPKL</a>
      </p>
    </footer>

    <!-- partial -->
    <script src="../Access/Style/js/login.js"></script>
    <script>
    function checkPassword(){
    	password = document.getElementById("password").value;
    	repassword = document.getElementById("re_password").value;
    	if (password != repassword){
    		document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
    		return false;
    	} else {
    		document.getElementById("msg").innerHTML = "";
    		return true;
    	}
    }
    </script>
	
</html>