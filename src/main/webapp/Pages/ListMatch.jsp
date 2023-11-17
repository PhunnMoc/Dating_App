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
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	data-auto-replace-svg="nest"></script>
</head>
<body>
	<%
	Account username = (Account) session.getAttribute("user");
	%>
	<nav>
		<div class="nav-left">
			<a href="./Match.html"> <img
				src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
			</a>
			<ul class="nav-icon">
				<a href="./favourite.html"><li><i
						class="fa-solid fa-heart-circle-check"></i></i></li></a>
				<a href="<%=request.getContextPath()%>/chat/list_chat"><li><i class="fa-solid fa-message"></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/showCard"><li><i
						class="fa-solid fa-user-group fa-beat " style="color: red;"></i></i></li></a>
			</ul>
		</div>
		<div class="nav-right">
			<div class="nav-user-icon online" onclick="settingsMenuToggle()">
				<img src="https://i.postimg.cc/44VbNwBf/avatar.png" />
			</div>
		</div>
		<!----------------Settings Menu"----------------------->
		<div class="settings-menu">
			<div id="dark-btn">
				<span></span>
			</div>
			<div class="settings-menu-inner">
				<div class="user-profile">
					<img src="https://i.postimg.cc/cHg22LhR/profile-pic.png" />
					<div>
						<p>Huynh Hong Khanh</p>
						<a href="./InforLogin">See your profile</a>
					</div>
				</div>
				<hr />
				<div class="user-profile">
					<img src="https://i.postimg.cc/hv3nx52s/feedback.png" />
					<div>
						<p>Give Feedback</p>
						<a href="#">Help us to improve the new design</a>
					</div>
				</div>
				<hr />
				<div class="settings-links">
					<img src="https://i.postimg.cc/QCcPNYRV/setting.png"
						class="settings-icon" /> <a href="#">Settings & Privacy <img
						src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
					</a>
				</div>
				<div class="settings-links">
					<img src="https://i.postimg.cc/C5tydfK6/help.png"
						class="settings-icon" /> <a href="#">Help & Support<img
						src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
					</a>
				</div>
				<div class="settings-links">
					<img src="https://i.postimg.cc/5yt1XVSj/display.png"
						class="settings-icon" /> <a href="#">Display & Accessibility
						<img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
					</a>
				</div>
				<div class="settings-links">
					<img src="https://i.postimg.cc/PJC9GrMb/logout.png"
						class="settings-icon" /> <a href="#">Logout <img
						src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
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
      <p id="getID" style="display: none;">${user.name}</p>   
        <h3 class="card__heading">${user.name}</h3>
        <h4 class="card__category">${user.introduce}</h4>
        <button class="custom-btn btn-15 hello" value="${user.userID}">GỬI LỜI CHÀO</button>
        <button class="custom-btn btn-15 nono" value="${user.userID}">HỦY TƯƠNG TÁC</button>
      </div>
    </a>
					</c:forEach>
  <div>
  
</section>
	
	<script src="../Access/Style/js/Base.js"></script>
	
</body>
</html>