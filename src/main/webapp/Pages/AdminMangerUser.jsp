<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Models.Profile" %>
<%@ page import="Models.Account" %>
	<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>TeenTher</title>
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<meta http-equiv="X-Content-Type-Options" content="nosniff">
		<style type="text/css">
			.dpflex{
				display: flex;
			}
			
			.wh100{
				width: 100%;
				height: 100%;
				object-fit: cover;
			}
			
			.scroll{
				overflow-y: scroll;
			}
			
			#cardcss{
				top:0 ; 
				color: #acacac;
			}
			.dpnone{
				display: none;
			}
		
		</style>
		<!-- phan card -->
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
		<!-- phan card -->
		<link rel="stylesheet" href="../Access/Style/css/Base.css" />
		<link rel="stylesheet" href="../Access/Style/css/root/root.css" />
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
			rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
		<link rel="stylesheet" href="../Access/Style/css/ListFriend.css">
		<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
			data-auto-replace-svg="nest"></script>
		<meta http-equiv="Content-Security-Policy" content="default-src 'self' ; 
			script-src  'self' 'nonce-ABC123' https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js https://use.fontawesome.com/releases/v5.15.4/js/all.js 
			https://code.jquery.com/jquery-3.6.4.min.js; 
			style-src 'self'  https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
			font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/ https://fonts.gstatic.com/s/montserrat/v26/ 
			https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css 
			https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
			connect-src 'self';
			img-src 'self' data: https://i.postimg.cc https://play-lh.googleusercontent.com favicon.ico ;">
	</head>

	<body class="scroll">
		<% Account admin=(Account) session.getAttribute("admin"); Account account=(Account)
			session.getAttribute("account"); //Profile profile=(Profile)
			request.getAttribute("MyOwnProfile"); //String imageData=(String) request.getAttribute("image");
			%>
			<% if (account !=null) { %>
				<jsp:forward page="Login.jsp"></jsp:forward>
				<% } %>

					<%-- if (profile==null) { <jsp:forward page="/pro/showCard"></jsp:forward>
						<% } %> --%>

							<nav class="dpflex">
								<div class="nav-left">
									<a href="<%=request.getContextPath()%>/pro/showCard"> <img
											src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
									</a>
									<ul class="nav-icon">
										<a href="<%=request.getContextPath()%>/AdminRole/list">
											<li><i class="fa-solid fa-heart-circle-check"></i> </li>
										</a>
										<a href="<%=request.getContextPath()%>/AdminRole/listUser">
											<li> <i class="fa-solid fa-user-group "></i> </li>
										</a>


									</ul>
								</div>
								<div class="nav-right">
									<div class="nav-user-icon online" id="settingsMenuToggle">
										<img src="https://play-lh.googleusercontent.com/p9Kte5C0SltIXXYvQMdo64XCLmrhnX_E6DijP2d4-aMOjrneUI7ctx1Acz612DPa0hE"
											alt="Image" />
									</div>
								</div>
								<!----------------Settings Menu"----------------------->
								<div class="settings-menu">
									<div class="settings-menu-inner">
										<hr />
										<img src="https://i.postimg.cc/PJC9GrMb/logout.png"
											class="settings-icon logout_icon " /> <a
											href="<%=request.getContextPath()%>/pro/Logout">Đăng xuất
											<img src="https://i.postimg.cc/RF1dBMWr/arrow.png"
												width="10px" />
										</a>
									</div>
								</div>

							</nav>
							<section class="hero-section">
								<div class="card-grid">
									<c:forEach var="user" items="${ListProfileMatch}">
										<a class="card bd" href="#">
											<div class="card__background">
												<img class="wh100" src="data:image/jpeg;base64,${user.getImageURL()}">
											</div>

											<div class="card__content " id="cardcss" >

												<h3 class="card__heading">${user.name}</h3>
												<h4 class="card__category">${user.introduce}</h4>
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
												<%-- <form
													action="<%=request.getContextPath()%>/pro/sayHello"
													method="POST">
													<input value="Hello!!" name="content"
														class="dpnone">
													<button class="custom-btn btn-15 hello" name="sayHello"
														value="${user.userID}">GỬI LỜI CHÀO</button>
													</form>
													<form
														action="<%=request.getContextPath()%>/pro/deleteMatch"
														method="POST">
														<button class="custom-btn btn-15 nono"
															name="deleteMatch" value="${user.userID}">HỦY
															TƯƠNG TÁC</button>
													</form> --%>

											</div>
										</a>
									</c:forEach>
								</div>

							</section>
							<script nonce= "ABC123">
								document.getElementById("settingsMenuToggle").addEventListener("click", function() {
									settingsMenuToggle();
								});
							</script >
							<script nonce= "ABC123" src="../Access/Style/js/Base.js"></script>

	</body>

	</html>