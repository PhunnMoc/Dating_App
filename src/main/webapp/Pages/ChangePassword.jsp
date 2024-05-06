<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@page import="Models.Account"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-Content-Type-Options" content="nosniff">
	<title>Login</title>
	</head>
	<link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
    />
    
    <style>
      .red{
        color: red;
      }
      .contain{
        left:0;
         right:0;
      }
      .container{
        width: 568px;
      }
    </style>
    <link rel="stylesheet" href="../Access/Style/css/Login.css" />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />

    <meta http-equiv="Content-Security-Policy" content="default-src 'self' ; 
        script-src  'self' 'nonce-ABC123' https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js https://use.fontawesome.com/releases/v5.15.4/js/all.js https://code.jquery.com/jquery-3.6.4.min.js; 
        style-src 'self'  https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
        font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
        connect-src 'self';
        img-src 'self' data: https://i.postimg.cc https://play-lh.googleusercontent.com;">
    
	<body>
	<%
		String errChangePass = (String) request.getAttribute("errorChangePass");
	%>
	<%
		Account account = (Account) session.getAttribute("account");
	%>
	<%if (account == null) {%>
		<%response.sendRedirect(request.getContextPath() + "/Pages/Login.jsp"); %>
	<%} %>
	<!-- partial:index.partial.html -->
    <h2>Hãy điền thông tin vào đây để đổi mật khẩu</h2>
    <div class="container" id="container">
      <div class="form-container contain " >
        <form action="<%=request.getContextPath()%>/pro/changePassword" method="post" accept-charset="UTF-8">
          <h1>Đổi mật khẩu</h1>
          <input type="password" placeholder="Mật khẩu cũ" id="oldpass" name="oldpass" required="required"/>
          <input type="password" placeholder="Mật khẩu mới" id="newpass" name="newpass" required="required"/>
          <input type="password" placeholder="Nhập lại mật khẩu mới" id="re_password" name="re_password" required="required" onkeyup="checkPassword()"/>
          
        <%
          	if (errChangePass != null) {
          		%> <span id="msg" class="red"> <%=errChangePass %></span> <%
          	}
          	else {
          		%> <span id="msg" class="red"></span> <%
          	}
          %>
          <button type="submit">Đổi mật khẩu</button>
        </form>
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
    <script nonce= "ABC123" src="../Access/Style/js/login.js"></script>
    <script nonce= "ABC123">
    function checkPassword(){
    	password = document.getElementById("newpass").value;
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
	</body>
</html>