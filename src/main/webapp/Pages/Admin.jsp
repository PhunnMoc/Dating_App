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
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
 integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
 crossorigin="anonymous">
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	data-auto-replace-svg="nest"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<%-- 	<%
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
	%> --%>

	<nav>
		<div class="nav-left">
			<a href="./Match.html"> <img
				src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
			</a>
			<ul class="nav-icon">
				<a href="./favourite.html"><li><i
						class="fa-solid fa-heart-circle-check"></i></i></li></a>
				<a href="<%=request.getContextPath()%>/chat/list_chat"><li><i
						class="fa-solid fa-message"></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/showCard"><li><i
						class="fa-solid fa-user-group fa-beat " style="color: red;"></i></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/listMatch"><li><i
						class="fa-solid fa-user-group fa-beat " style="color: red;"></i></i></li></a>


			</ul>
		</div>
<%-- 		<div class="nav-right">
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
				<img src="https://i.postimg.cc/PJC9GrMb/logout.png"
					class="settings-icon logout_icon " /> <a
					href="<%=request.getContextPath()%>/pro/Logout">Logout <img
					src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px" />
				</a>
			</div>
		</div> --%>
		
	</nav>
	<div class="container-app">
		<!----------------Left Sidebar----------------------->
		<div class="left-sidebar"></div>
		<!----------------Main Sidebar----------------------->

		<!----------------Right Sidebar----------------------->
<div class="row">
  <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

  <div class="container">
<h3>Sở thích</h3>
   <div class="container text-left">

    <a href="<%=request.getContextPath()%>/new"
     class="btn btn-success">Add</a>
   </div>
   <br>
   <table class="table table-bordered">
    <thead>
     <tr>
      <th>Tên sở thích</th>
      <th>Ảnh tượng trưng</th> 
      <th>Actions</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach var="hobby" items="${listHobby}">

      <tr>
       <td><c:out value="${hobby.title}" /></td>
       <td><c:out value="${hobby.formatDate}" /></td>   
       <td><a href="edit?id=<c:out value='${hobby.id}' />">Edit</a>
        &nbsp;&nbsp;&nbsp;&nbsp; <a
        href="delete?id=<c:out value='${hobby.id}' />">Delete</a></td>

       <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                 <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
      </tr>
     </c:forEach>
     <!-- } -->
    </tbody>

   </table>
  </div>
 </div>
	</div>

</body>
<script src="../Access/Style/js/Card.js"></script>
</html>