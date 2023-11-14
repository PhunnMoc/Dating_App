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
<link rel="stylesheet" href="../Access/Style/css/Card.css">
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
				<a href="<%=request.getContextPath()%>/chat/list_other_user_message""><li><i class="fa-solid fa-message"></i></li></a>
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
	<div class="container-app">
		<!----------------Left Sidebar----------------------->
		<div class="left-sidebar"></div>
		<!----------------Main Sidebar----------------------->
		<div class="tinder">
			<div class="tinder--status">
				<i class="fa fa-remove"></i> <i class="fa fa-heart"></i>
			</div>

			<div class="tinder--cards">

				<!--        Phương -->
				<c:forEach var="count" begin="1" end="10">
					<c:forEach var="user" items="${ListProfile}">
						<div class="tinder--card " id="myCard">
							<img src="../Access/Media/Picture/dislike.png">
							<div class="infor-hide">
								<div class="main-infor">
									<h3>${user.name}</h3>
									Độ tuổi: <h4>${user.age}</h4>
									<div> Cung hoàng đạo: <h4>${user.zodiac}</h4>  Sinh nhật:<h4>${user.birthDay}</h4></div>
									Giới tính: <h4>${user.gender}</h4>
									Tình trạng mối quan hệ: <h4>${user.relationship}</h4>
									Chiều cao: <h4>${user.height}</h4>
									 <h5>${user.introduce}</h5>
									
								</div>
							</div>
							<div class="tinder--card--text">
								<div style="display: flex; align-items: center; justify-content: start;">
									<h3>${user.name}</h3>
									<i class="fa-solid fa-circle-info fa-2xl showInf"
										style="color: #ffffff;"></i>
								</div>
								<h4>${user.age}</h4>
								<p>${user.introduce}</p>
							</div>
						</div>
						<%-- 								<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;</td>  --%>
					</c:forEach>
				</c:forEach>
				<!--        Phương -->
			</div>

			<div class="tinder--buttons">
				<button id="nope">
					<i class="fa-solid fa-x fa-beat " style="color: #160303;"></i>
				</button>
				<button id="love">
					<i class="fa-solid fa-heart fa-beat " style="color: #d21e1e;"></i>
				</button>
			</div>
		</div>
		<!----------------Right Sidebar----------------------->
          
        </div> -->
	<script src="../Access/Style/js/Base.js"></script>
	<script>
	 document.addEventListener("DOMContentLoaded", function () {
	      var showInfButtons = document.querySelectorAll('.showInf');

	      showInfButtons.forEach(function(button) {
	        button.addEventListener('click', function () {
	          var cardText = button.closest('.tinder--card').querySelector('.tinder--card--text');
	          cardText.style.display = 'none';
	          button.closest('.tinder--card').style.overflow = 'scroll';
	        });
	      });
	    });
	</script>
</body>
<script src="../Access/Style/js/Card.js"></script>
</html>