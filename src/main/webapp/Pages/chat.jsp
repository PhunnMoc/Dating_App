<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>TeenTher</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Open+Sans"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/material-design-icons/3.0.1/iconfont/material-icons.min.css"
    />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../Access/Style/css/chat.css" />
    <link rel="stylesheet" href="../Access/Style/css/Base.css" />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />
    <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <script
      src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
      data-auto-replace-svg="nest"
    ></script>
  </head>
  <body>
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
          ><li><i class="fa-solid fa-message fa-beat" style="color: red;"></i></li
        ></a>
        <a href="./Match.html"
          ><li><i class="fa-solid fa-user-group"></i></i></li
        ></a>
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
              <a href="profile.html">See your profile</a>
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
            <img
              src="https://i.postimg.cc/QCcPNYRV/setting.png"
              class="settings-icon"
            />
            <a href="#"
              >Settings & Privacy
              <img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px"
            /></a>
          </div>
          <div class="settings-links">
            <img
              src="https://i.postimg.cc/C5tydfK6/help.png"
              class="settings-icon"
            />
            <a href="#"
              >Help & Support<img
                src="https://i.postimg.cc/RF1dBMWr/arrow.png"
                width="10px"
            /></a>
          </div>
          <div class="settings-links">
            <img
              src="https://i.postimg.cc/5yt1XVSj/display.png"
              class="settings-icon"
            />
            <a href="#"
              >Display & Accessibility
              <img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px"
            /></a>
          </div>
          <div class="settings-links">
            <img
              src="https://i.postimg.cc/PJC9GrMb/logout.png"
              class="settings-icon"
            />
            <a href="#"
              >Logout
              <img src="https://i.postimg.cc/RF1dBMWr/arrow.png" width="10px"
            /></a>
          </div>
        </div>
      </div>
    </nav>
    <div class="container-app">
      <!----------------Left Sidebar----------------------->
      <div class="left-sidebar border-right">
        <div class="settings-tray">
          <img
            class="profile-image"
            src="https://www.clarity-enhanced.net/wp-content/uploads/2020/06/filip.jpg"
            alt="Profile img"
          />
          <span class="settings-tray--right">
            <i class="material-icons">cached</i>
            <i class="material-icons">message</i>
            <i class="material-icons">menu</i>
          </span>
        </div>

        <!-- Ô tìm kiếm người nhắn  -->
        <div class="search-box">
          <div class="input-wrapper">
            <i class="material-icons">search</i>
            <input placeholder="Search here" type="text" />
          </div>
        </div>
        <!-- Kết thúc tìm kiếm  -->

        <!-- Những người đã nhắn  -->
        <div class="frame-messages">
	        <c:forEach var="profile" items="${list_other_user}">
	        
	        	<div class="friend-drawer friend-drawer--onhover">
	        		<input type="hidden" name="otherUserID" value="<c:out value='${profile.userID}' />" />
		            <img
		              class="profile-image"
		              src="https://www.clarity-enhanced.net/wp-content/uploads/2020/06/robocop.jpg"
		              alt=""
		            />
		            <div class="text">
		              <h6><c:out value="${profile.name}" /> </h6>
		              
		              <p class="text-muted">${last_Message.content}</p>
		            </div>
		            <span class="time text-muted small">${last_Message.time}</span>
		          </div>
		          
	        </c:forEach>
	             
        </div>
      </div> 
     <!--  --------------Main Sidebar---------------------
      Mục nhắn tin  -->
      <div class="mess">
<!--         Thông tin của người đang nhắn 
 -->        <div class="settings-tray">
          <div class="friend-drawer no-gutters friend-drawer--grey">
            <img
              class="profile-image"
              src="https://www.clarity-enhanced.net/wp-content/uploads/2020/06/robocop.jpg"
              alt=""
            />
            <div class="text">
              <h6>Robo Cop</h6>
              <p class="text-muted">
                Layin' down the law since like before Christ...
              </p>
            </div>
            <span class="settings-tray--right">
              <i class="material-icons">cached</i>
              <i class="material-icons">message</i>
              <i class="material-icons">menu</i>
            </span>
          </div> -->
        </div>
        <!-- End thông tin của người đang nhắn  -->

        <!-- Bảng Tin nhắn  -->
        <div class="messages">
          <div class="messages-content"></div>
        </div>
        <!-- End Bảng Tin nhắn  -->

        <!-- Mục nhập tin nhắn và gửi  -->
        <div class="message-box" id="message-box" >
          <i class="material-icons">sentiment_very_satisfied</i>
          <input
            type="text"
            class="message-input"
            placeholder="Type your message here..."
          />
          <i class="material-icons">mic</i>
          <button
            type="submit"
            class="material-icons message-submit"
            oncclick="themMessage()"
          >
            send
          </button>
        </div>
        <!-- End mục nhập tin nhắn và gửi  -->
      </div>
      <!----------------Right Sidebar----------------------->
    </div>
    <!-- <div class="footer">
          
        </div> -->
    <script src="/Access/Style/js/Base.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="../Access/Style/js/chat.js"></script>
  </body>
</html>