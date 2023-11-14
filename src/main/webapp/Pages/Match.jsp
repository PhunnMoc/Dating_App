<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="Models.Account"%>
<%@ page import="Models.UserHobby"%>
<%@ page import="Models.Profile"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64"%>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>TeenTher</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <!-- phan card -->
  <link rel="stylesheet" href="../Access/Style/css/Card.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js"></script>
    <!-- phan card -->
    <link rel="stylesheet" href="../Access/Style/css/Base.css" />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script
      src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
      data-auto-replace-svg="nest"
    ></script>
  </head>
  <body>
  	<%	
		Account account = (Account) session.getAttribute("account");
		Profile profile = (Profile) request.getAttribute("profile");
		String imageData = (String) request.getAttribute("image");
	%>
	<%
	if (account == null) {
	  response.sendRedirect(request.getContextPath() + "/Pages/Login.jsp");
	}
	%>
    <nav>
      <div class="nav-left">
        <a href="./Match.html">
          <img src="https://i.postimg.cc/Pq3ZM5hW/logo.png" class="logo" />
        </a>
        <ul class="nav-icon">
            <a href="./favourite.html"
            ><li><i class="fa-solid fa-heart-circle-check"></i></i></li
          ></a>
          <a href="./chat.html"
            ><li><i class="fa-solid fa-message"></i></li
          ></a>
          <a href="./Match.html"
            ><li><i class="fa-solid fa-user-group fa-beat " style="color:red;"></i></i></li
          ></a>
        </ul>
      </div>
      <div class="nav-right">
        <div class="nav-user-icon online" onclick="settingsMenuToggle()">
          <img src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
        </div>
      </div>
      <!----------------Settings Menu"----------------------->
      <div class="settings-menu">
        <div id="dark-btn">
          <span></span>
        </div>
        <div class="settings-menu-inner">
          <div class="user-profile">
            <div class="nav-user-icon online">
          <img src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
        </div>
            <div>
              <p><%=profile.getName()%></p>
              <a href="<%=request.getContextPath()%>/pro/list">Thông tin cá nhân</a>
            </div>
          </div>
          <hr />
            <img
              src="https://i.postimg.cc/PJC9GrMb/logout.png"
              class="settings-icon"
            />
            <a href="<%=request.getContextPath() %>/pro/Logout"
              >Logout
              <img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px"
            /></a>
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
            <i class="fa fa-remove"></i>
            <i class="fa fa-heart"></i>
        </div>

        <div class="tinder--cards">
            <div class="tinder--card">
                <img src="../Access/Media/Picture/dislike.png">
                <div class="tinder--card--text">
                    <h3>Demo card 1</h3>
                <p>This is a demo for Tinder like swipe cards</p>
                </div>
            </div>
            <div class="tinder--card">
                <img src="../Access/Media/Picture/test.jpg">
                <div class="tinder--card--text">
                    <h3>Demo card 1</h3>
                <p>This is a demo for Tinder like swipe cards</p>
                </div>
            </div>
            <div class="tinder--card">
                <img src="../Access/Media/Picture/Thanh.jpg">
                <div class="tinder--card--text">
                    <h3>Demo card 1</h3>
                <p>This is a demo for Tinder like swipe cards</p>
                </div>
            </div>
            <div class="tinder--card">
                <img src="../Access/Media/Picture/Banh.jpg">
                <div class="tinder--card--text">
                    <h3>Demo card 1</h3>
                <p>This is a demo for Tinder like swipe cards</p>
                </div>
            </div>
            <div class="tinder--card">
                <img src="../Access/Media/Picture/love.jpg">
                <div class="tinder--card--text">
                    <h3>Demo card 1</h3>
                <p>This is a demo for Tinder like swipe cards</p>
                </div>
            </div>
        </div>

        <div class="tinder--buttons">
            <button id="nope"><i class="fa-solid fa-x fa-beat " style="color: #160303;"></i></button>
            <button id="love"><i class="fa-solid fa-heart fa-beat " style="color: #d21e1e;"></i></button>
        </div>
    </div>
      <!----------------Right Sidebar----------------------->
    </div>
    <!-- <div class="footer">
          
        </div> -->
  <script src="../Access/Style/js/Base.js"></script>
  </body>
  <script src="../Access/Style/js/Card.js"></script>
</html>