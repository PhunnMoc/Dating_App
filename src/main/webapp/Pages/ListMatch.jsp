<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Models.Profile"%>
<%@ page import="Models.Account"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TeenTher</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- phan card -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
<!-- phan card -->
<link rel="stylesheet" href="../Access/Style/css/Base.css" />
<link rel="stylesheet" href="../Access/Style/css/root/root.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
<link rel="stylesheet" href="../Access/Style/css/ListFriend.css">
<!-- <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	data-auto-replace-svg="nest"></script> -->
</head>
<body>
	<%
	Account account = (Account) session.getAttribute("account");
	Profile profile = (Profile) request.getAttribute("MyOwnProfile");
	String imageData = (String) request.getAttribute("image");
	%>
	<%
	if (account == null) {
	%>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
	}

	if (profile == null) {
	%>
	<jsp:forward page="/pro/showCard"></jsp:forward>
	<%
	}
	%>

	<nav>
		<div class="nav-left">
			<a href="<%=request.getContextPath()%>/pro/showCard"> <img
				src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
			</a>
			<ul class="nav-icon">
				<a href="<%=request.getContextPath()%>/pro/listFavorite"><li><i
						class="fa-solid fa-heart-circle-check"></i></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/message"><li><i
						class="fa-solid fa-message"></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/listMatch"><li><i
						class="fa-solid fa-user-group fa-beat " style="color: red;"></i></i></li></a>


			</ul>
		</div>
		<div class="nav-right">
			<div class="nav-user-icon online" onclick="settingsMenuToggle()">
				<img src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
			</div>
		</div>
		<!----------------Settings Menu"----------------------->
		<div class="settings-menu">
			<div class="settings-menu-inner">
				<div class="user-profile">
					<div class="nav-user-icon">
						<img src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
					</div>
					<div>
						<p><%=profile.getName()%></p>
						<a href="<%=request.getContextPath()%>/pro/list">Thông tin cá
							nhân</a>
					</div>
				</div>
				<hr />
				<img src="https://media.istockphoto.com/id/936681148/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-kh%C3%B3a.jpg?s=612x612&w=0&k=20&c=U6Hw5e1K70NUaQz2MjOeal_FjERS9swHClnFI6MMVEY="
					class="settings-icon logout_icon " /> <a
					href="<%=request.getContextPath()%>/pro/changePassword">Đổi mật khẩu
					<img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
				</a>
				<hr />
				<img src="https://i.postimg.cc/PJC9GrMb/logout.png"
					class="settings-icon logout_icon " /> <a
					href="<%=request.getContextPath()%>/pro/Logout">Đăng xuất
					<img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
				</a>
			</div>
		</div>
		</div>
	</nav>
	
<section class="hero-section">
  <div class="card-grid">
    <c:forEach var="user" items="${ListProfileMatch}">
				 <a class="card bd" href="#">
      <div class="card__background" >
      <img style="width: 100%;
    height: 100%;
    object-fit: cover;" src="data:image/jpeg;base64,${user.getImageURL()}">
      </div>

      <div class="card__content"> 
    
        <h3 class="card__heading">${user.name}</h3>
        <h4 class="card__category">${user.introduce}</h4>
		<form action="<%=request.getContextPath()%>/pro/sayHello" method="POST">
        <input value = "Hello!!" name = "content" style="display: none">
        <button class="custom-btn btn-15 hello" name="sayHello" value="${user.userID}">GỬI LỜI CHÀO</button>
        </form>
        <form action="<%=request.getContextPath()%>/pro/deleteMatch" method="POST">
        <button class="custom-btn btn-15 nono" name="deleteMatch" value="${user.userID}">HỦY TƯƠNG TÁC</button>
        </form>
       
      </div>
    </a>
</c:forEach>
  </div>
  
</section>
	
	<script src="../Access/Style/js/Base.js"></script>
	
</body>
</html>