<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Models.Profile"%>
<%@ page import="Models.Account"%>
<%@ page import="java.util.Base64"%>
<%@ page import="DAO.ProfileDAO"%>
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
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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
						class="fa-solid fa-user-group "></i></i></li></a>


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
	
	</nav>
	<div class="container-app">
		<!----------------Left Sidebar----------------------->
		<div class="left-sidebar">
		<h4 style="padding-left: 10px;" class="title-left">KHÁM PHÁ</h4>
		</div>
		<!----------------Main Sidebar----------------------->
			<div class="tinder">
			<div class="tinder--status">
				<i class="fa-solid fa-remove " style="color: #160303;"></i>
				<i class="fa-solid fa-heart fa-beat " style="color: #d21e1e;"></i>
			</div>
			
			<div class="tinder--cards">

				<!--        Phương -->
				
					<c:forEach var="user" items="${ListProfile}">
						<div class="tinder--card " id="myCard">
							<img src="data:image/jpeg;base64,${user.getImageURL()}">
							<div class="infor-hide">
								<div class="main-infor">
									<h3>${user.name}</h3>
									Độ tuổi:
									<h4>${user.age}</h4>
									<div>
										Cung hoàng đạo:
										<h4>${user.zodiac}</h4>
										Sinh nhật:
										<h4>${user.birthDay}</h4>
									</div>
									Giới tính:
									<h4>${user.gender}</h4>
									Tình trạng mối quan hệ:
									<h4>${user.relationship}</h4>
									Chiều cao:
									<h4>${user.height}</h4>
									<h5>${user.introduce}</h5>

								</div>
							</div>
							<div class="tinder--card--text">
								<div
									style="display: flex; align-items: center; justify-content: start;">
									<h3>${user.name}</h3>
									<i class="fa-solid fa-circle-info fa-2xl showInf"
										style="color: #ffffff;"></i>
								</div>
								<h4>${user.age}</h4>
								<p>${user.introduce}</p>
								<p style="display:none" id="userID2">${user.userID}</p>
							</div>
						</div>

					</c:forEach>
						<h4>Đã hết, vui lòng reload...</h4>
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

	</div>
	-->
	<script src="../Access/Style/js/Base.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var showInfButtons = document.querySelectorAll('.showInf');

			showInfButtons.forEach(function(button) {
				button.addEventListener('click', function() {
					var cardText = button.closest('.tinder--card')
							.querySelector('.tinder--card--text');
					cardText.style.display = 'none';
					button.closest('.tinder--card').style.overflow = 'scroll';
				});
			});
		});
	</script>
</body>
<script src="../Access/Style/js/Card.js"></script>
</html>