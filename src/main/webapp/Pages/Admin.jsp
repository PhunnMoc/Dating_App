<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Models.Profile"%>
<%@ page import="Models.Account"%>
<%@ page import="Models.Hobby"%>
<%@ page import="java.util.Base64"%>
<%@ page import="DAO.ProfileDAO"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-Content-Type-Options" content="nosniff">
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
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	data-auto-replace-svg="nest"></script>
<style type="text/css">
	#hide-box{
		display: none;
	}
	.contain{
		justify-content: center; 
		overflow: scroll;
	}
	.dpflex{
		display:flex;
	}
	.w200px{
		width:200px;
	}
	.w700px{
		width:700px
	}
	.w10px {
		width:10px;
	}
	.edit{
		text-decoration: underline; 
		cursor: pointer;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<meta http-equiv="Content-Security-Policy" content="default-src 'self' ; 
			script-src  'self' 'nonce-ABC123' https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js https://use.fontawesome.com/releases/v5.15.4/js/all.js https://code.jquery.com/jquery-3.6.4.min.js; 
			style-src 'self'  https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
			font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/ https://fonts.gstatic.com/s/montserrat/v26/ https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
			connect-src 'self';
			img-src 'self' data: https://i.postimg.cc https://play-lh.googleusercontent.com favicon.ico ;
			 form-action 'self' ;">
</head>
<body>
	<%
	Account admin = (Account) session.getAttribute("admin");
	Account account = (Account) session.getAttribute("account");
	//Profile profile = (Profile) request.getAttribute("MyOwnProfile");
	//String imageData = (String) request.getAttribute("image");
	%>
	<%
	if (account != null) {
	%>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
	}
	%>
	
	<%-- 
	if (profile == null) {
	
	<jsp:forward page="/pro/showCard"></jsp:forward>
	<%
	}
	%> --%>

		<nav class="dpflex">
		<div class="nav-left">
			<a href="<%=request.getContextPath()%>/pro/showCard"> <img
				src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
			</a>
			<ul class="nav-icon">
				<a href="<%=request.getContextPath()%>/AdminRole/list"> <li> <i class="fa-solid fa-heart-circle-check"></i></li> </a>
				<a href="<%=request.getContextPath()%>/AdminRole/listUser"> <li> <i class="fa-solid fa-user-group "></i></li> </a>


			</ul>
		</div>
		<div class="nav-right">
			<div class="nav-user-icon online" id="settingsMenuToggle">
				<img src="https://play-lh.googleusercontent.com/p9Kte5C0SltIXXYvQMdo64XCLmrhnX_E6DijP2d4-aMOjrneUI7ctx1Acz612DPa0hE" alt="Image" />
			</div>
		</div>
		<!----------------Settings Menu"----------------------->
		<div class="settings-menu">
			<div class="settings-menu-inner">
				<hr />
				<img src="https://i.postimg.cc/PJC9GrMb/logout.png"
					class="settings-icon logout_icon " /> <a
					href="<%=request.getContextPath()%>/pro/Logout">Đăng xuất
					<img src="https://i.postimg.cc/RF1dBMWr/arrow.png" class="w10px" />
				</a>
			</div>
		</div>
	
	</nav>
	<div class="container-app contain">
		<!----------------Left Sidebar----------------------->
		<!-- 		<div class="left-sidebar"></div> -->
		<!----------------Main Sidebar----------------------->

		<!----------------Right Sidebar----------------------->
		<div class="row">
			<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

			<div class="container">
				<h3 class="title-left">Quản lí các sở thích</h3>
				<div class="container text-left">
					<button class="btn btn-success" id="toggleHideBox">+</button>

					<div id="hide-box" >
						<form id="form" enctype="multipart/form-data"
							action="<%=request.getContextPath()%>/AdminRole/add"
							method="POST" accept-charset="UTF-8">
							<label>Tên Sở thích: </label> <input id="namehobby"
								name="namehobby" value="Nhap so thich moi"> <input id="idhobby"
								name="idhobby" value="" > 

							<div class="subscription noshow">

								<div class="app-wrap">
									<div class="upload-form">
										<div class="upload-form__wrap">
											<div class="upload-form__field">
												<label>Thêm hình ảnh: </label> <input type="file"
													accept="image/gif, image/jpeg, image/png" name="image">

												<button class="btn btn-success" type="submit">Done</button>
												
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Tên sở thích</th>
							<th class="w700px">Ảnh tượng trưng</th>
							<th>Actions</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="hobby" items="${list}">

							<tr>
								<td><c:out value="${hobby.hobbyName}" /></td>
								<td><img
									src="data:image/jpeg;base64,${hobby.getImageURL()}" class="w200px"></td>
								<td id="getRowData">
									<a class="edit" data-id='${hobby.iDhobby}' >Edit</a>
								</td>
								<td><a
									href="<%=request.getContextPath()%>/AdminRole/delete?id=<c:out value='${hobby.iDhobby}' />">Delete</a></td>

							</tr>
						</c:forEach>
						<!-- } -->
					</tbody>

				</table>
			</div>
		</div>
	</div>
	
	<script nonce ="ABC123">
		document.getElementById("settingsMenuToggle").addEventListener("click", function() {
			settingsMenuToggle();
		});
		document.getElementById("toggleHideBox").addEventListener("click", function() {
			toggleHideBox();add();
		});
		document.getElementById("getRowData").addEventListener("click", function() {
			getRowData(this);
			edit();
		});
		
		function toggleHideBox() {
			var box = document.getElementById("hide-box");
			if (box.style.display == "none") {
				box.style.display = "block";
			} else {
				box.style.display = "none";
			}
		}
		 function edit() {
		        // Get the form element
		        var myForm = document.getElementById("form");

		        // Change the action attribute
		        myForm.action = "<%=request.getContextPath()%>/AdminRole/edit"; // Set your new action URL here
		    }
		 function add() {
		        // Get the form element
		        var myForm = document.getElementById("form");

		        // Change the action attribute
		        myForm.action = "<%=request.getContextPath()%>/AdminRole/add"; // Set your new action URL here
		    }
		function getRowData(clickedElement) {
			// Get the clicked row
			var clickedRow = clickedElement.parentNode;

			// Get data from cells in the clicked row
			var name = clickedRow.cells[0].innerHTML;
			var id = clickedRow.cells[2].querySelector('a').getAttribute('data-id');
			document.getElementById("namehobby").value = name;
			document.getElementById("idhobby").value = id;
			console.log(id);
			toggleHideBox()
		}
	</script>
	<script nonce="ABC123" src="../Access/Style/js/Base.js"></script>
</body>

</html>