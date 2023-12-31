<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Models.Message"%>
<%@ page import="Models.Account"%>
<%@ page import="Models.Profile"%>
<%@ page import="java.util.List"%>
<%@ page buffer="8192kb" autoFlush="true"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>TeenTher</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="../Access/Style/css/root/root.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open+Sans" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-icons/3.0.1/iconfont/material-icons.min.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../Access/Style/css/chat.css" />
<link rel="stylesheet" href="../Access/Style/css/Base.css" />
<link rel="stylesheet" href="../Access/Style/css/root/root.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	data-auto-replace-svg="nest"></script>
</head>
<body>

	<%
	Account account = (Account) session.getAttribute("account");
	%>
	<%
	if (account == null) {
	%>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
	}

	%>
		<%
	String listMessageJSON = (String) request.getAttribute("listMessJSON");
	String listProfileJSON = (String) request.getAttribute("listProfileJSON");
	Profile profile = (Profile) request.getAttribute("profile");
	String imageData = (String) request.getAttribute("image");
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
						class="fa-solid fa-message fa-beat" style="color: red;"></i></li></a>
				<a href="<%=request.getContextPath()%>/pro/listMatch"><li><i
						class="fa-solid fa-user-group  " ></i></i></li></a>


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
	<div class="container-app">
		<!----------------Left Sidebar----------------------->
		<div class="left-sidebar border-right">
			<div class="settings-tray">
				<img class="profile-image"
					src="data:image/jpeg;base64, <%=imageData%>" /> <span
					class="settings-tray--right"> </span>
			</div>
			<c:if test="${not empty list_other_user}">
				<!-- Lấy giá trị đầu tiên từ danh sách -->
				<c:set var="firstPerson" value="${list_other_user[0]}" />

				<!-- Gán giá trị cho thẻ input -->
				<input name="currentPeople" id="currentPeople"
					value="${firstPerson.getUserID()}" style="display: none">
			</c:if>
			<!-- Ô tìm kiếm người nhắn  -->
			<div class="search-box">
				<div class="input-wrapper">
					<i class="material-icons">search</i> <input
						placeholder="Search here" type="text" />
				</div>
			</div>
			<!-- Kết thúc tìm kiếm  -->

			<!-- Những người đã nhắn  -->
			<div class="frame-messages">
				<c:if test="${not empty list_other_user}">
					<c:forEach var="i" begin="0" end="${list_other_user.size() - 1}"
						step="1">

						<div class="friend-drawer friend-drawer--onhover"
							onclick="handleFriendClick(this)">
							<img class="profile-image"
								src="data:image/jpeg;base64, <c:out value="${list_other_user[i].getImageURL()}" />"
								alt="Image" />
							<div class="text">
								<h6>
									<c:out value="${list_other_user[i].name}" />
								</h6>
								<input name="userID"
									value=<c:out value="${list_other_user[i].getUserID()}" />
									style="display: none">
								<p class="text-muted">
									<c:out value="${last_Message[i].content}" />
								</p>
							</div>
							<span class="time text-muted small"><c:out
									value="${last_Message[i].time}" /> </span>
						</div>


					</c:forEach>
				</c:if>
			</div>
		</div>
		<!--  --------------Main Sidebar--------------------- Mục nhắn tin  -->
		<div class="mess">
			<!--         Thông tin của người đang nhắn -->
			<div class="settings-tray" type="hidden">
				<div class="friend-drawer no-gutters friend-drawer--grey">
					<img class="profile-image" id="imgReciever"
						src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
					<div class="text">
						<h6 id="RecieverName"></h6>

					</div>
					<span class="settings-tray--right"> <i
						class="material-icons" style="position: absolute; right: 10;" onclick="handleMenuClick()">menu</i>
					</span>
				</div>
			</div>
			<!-- End thông tin của người đang nhắn  -->

			<!-- Bảng Tin nhắn  -->
			<div class="messages" type="hidden">
				<div class="messages-content"></div>
			</div>
			<!-- End Bảng Tin nhắn  -->

			<!-- Mục nhập tin nhắn và gửi  -->
			<div class="message-box" id="message-box">
				<i class="material-icons">sentiment_very_satisfied</i> <input
					type="text" class="message-input"
					placeholder="Type your message here..." /> <i
					class="material-icons">mic</i>
				<button type="submit" class="material-icons message-submit"
					oncclick="themMessage()">send</button>
			</div>
			<!-- End mục nhập tin nhắn và gửi  -->
		</div>
		<div class="infor-hide" id="infor-hide">
								<div class="main-infor">
								<div class="image-contaiter">
								<img class="hide-image" id="img-hide" src="data:image/jpeg;base64, <%=imageData%>" alt="Image" />
								<h3 id="name-hide">thiện</h3>
								</div>								
								<div class="infor-user-hide">
									<h5 id="age-hide">20</h5>									
									<h5 id="zodiac-hide">Song Tử</h5>										
								<!-- 	//<h5 id="birthday-hide">16/12/2003</h5> -->
									<h5 id="gender-hide">Nam</h5>
									<h5 id="relationship-hide">Độc thân</h5>
									<h5 id="height-hide">165cm</h5>
									<h5 id="introduce-hide">Đẹp trai, nhà giàu, nổi tiếng </h5>
									<h5 id="address-hide">Quảng Lợi </h5>
								</div>	
									

								</div>
							</div>
		<!----------------Right Sidebar----------------------->
	</div>
	<!-- <div class="footer">
          
        </div> -->

	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js"></script>

	<script>
	<%
	String escapedListProfileJSON = StringEscapeUtils.escapeEcmaScript(listProfileJSON);
	String escapedListMessageJSON =  StringEscapeUtils.escapeEcmaScript(listMessageJSON);%>
	
	var listMessage = JSON.parse('<%=escapedListMessageJSON%>');
	var listProfile = JSON.parse('<%=escapedListProfileJSON%>');
		var $messages = $('.messages-content'), d, h, m, i = 0;
		var inputElement = document.getElementById("currentPeople");
		var sender = '<%=account.getUserID()%>';
		var reciever = inputElement.value;

		/*  	var listMessage = JSON.parse(listMessageJSON);
			var listProfile = JSON.parse(listProfileJSON);  */
		$(window).on('load', function() {
			socket.onopen();
			$messages.mCustomScrollbar();
			loadData(reciever)
		});
		$('.friend-drawer--onhover').on('click', function() {
			$('.chat-bubble').hide('slow').show('slow');
		});

		var wsUrl;
		if (window.location.protocol === 'http:') {
			wsUrl = 'ws://';
		} else {
			wsUrl = 'wss://';
		}
		function updateScrollbar() {
			$messages.mCustomScrollbar("update").mCustomScrollbar('scrollTo',
					'bottom', {
						scrollInertia : 10,
						timeout : 0
					});
		}
		// Thay đổi 'window.location.host' thành tên miền của bạn nếu cần thiết

		function setDate() {
			d = new Date()
			if (m != d.getMinutes()) {
				m = d.getMinutes();
				$('<div class="timestamp">' + d.getHours() + ':' + m + '</div>')
						.appendTo($('.message:last'));
			}
		}
		var socket = new WebSocket(wsUrl + window.location.host
				+ "/Dating_App/chat/" + sender + "/" + reciever);

		// Xử lý khi mở kết nối WebSocket
		socket.onopen = function(event) {
			console.log("WebSocket opened:", event);
		};

		// Xử lý khi nhận tin nhắn từ server WebSocket
		socket.onmessage = function(event) {
			console.log("Message received:", event.data);
			receiveMessage(event.data); // Hàm xử lý tin nhắn nhận được
		};

		// Xử lý khi đóng kết nối WebSocket
		socket.onclose = function(event) {
			console.log("WebSocket closed:", event);
		};

		// Hàm gửi tin nhắn
		function sendMessage(message) {
			socket.send(message);

		}

		// Hàm xử lý tin nhắn nhận được
		function receiveMessage(message) {
			$(
					'<div class="message new"><figure class="avatar"><img src="https://www.clarity-enhanced.net/wp-content/uploads/2020/06/robocop.jpg" /></figure>'
							+ message + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			setDate();
			updateScrollbar();
		}
		function receiveMessageimage(message, imageURL) {
			$(
					'<div class="message new"><figure class="avatar"><img src=' +imageURL+ ' /></figure>'
							+ message + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			setDate();
			updateScrollbar();
		}

		// Xử lý khi nhấn nút Gửi tin nhắn
		$('.message-submit').click(function() {
			var messageInput = $('.message-input').val();
			if ($.trim(messageInput) !== '') {
				sendMessage(messageInput);
				insertMessage(messageInput);
			}
		});

		// Xử lý khi nhấn phím Enter
		$(window).on('keydown', function(e) {
			if (e.which === 13) {
				var messageInput = $('.message-input').val();
				if ($.trim(messageInput) !== '') {
					sendMessage(messageInput);
					insertMessage(messageInput);
				}
				return false;
			}
		});

		// Hàm thêm tin nhắn vào giao diện
		function insertMessage(message) {
			console.log("message:", message);
			$('<div class="message message-personal">' + message + '</div>')
					.appendTo($('.mCSB_container')).addClass('new');
			setDate();
			$('.message-input').val(null);
			updateScrollbar();
		}

		// Bắt sự kiện khi click vào một người bạn
		$('.friend-drawer--onhover').on('click', function() {
			$('.chat-bubble').hide('slow').show('slow');
		});

		// Mở kết nối WebSocket khi trang được load

		function handleFriendClick(element) {
			// Lấy giá trị userID từ thẻ input bên trong
			var userID = element.querySelector('input[name="userID"]').value;
			inputElement.value = userID;
			loadData(userID)
		}
		
		function handleMenuClick () {
			var inforHide = document.getElementById('infor-hide');
			inforHide.classList.toggle('show-info-hide');
		}
		
		function loadData(userID) {
			reciever = userID;
			socket = new WebSocket(wsUrl + window.location.host
					+ "/Dating_App/chat/" + sender + "/" + reciever);
			//socket.open();
			var messages = document.querySelectorAll('.message');
			messages.forEach(function(message) {
				message.remove();
			});
			var imageURL = "";
			var userName = "";
			var name = "";
			var age = "";
			var zodiac = "";
			var birthday = "";
			var gender = "";
			var relationship = "";
			var heght = "";
			var introduce = "";
			var address = "";
			
			listProfile.forEach(function(profile) {
				if (profile.userID == reciever) {
					imageURL = profile.imageURL;
					userName = profile.name;
					name = profile.name;
					age = profile.age;
					gender = profile.gender;
					birthday = profile.birthDay;
					relationship = profile.relationship;
					height = profile.height;
					zodiac = profile.zodiac;
					introduce = profile.introduce;
					address = profile.address;
					//break;
				}
			});
			var imgReciever = document.getElementById("imgReciever");
			var recieverName = document.getElementById("RecieverName");
			recieverName.innerText = userName;
			imageURL = "data:image/jpeg;base64," + imageURL;
			imgReciever.src = imageURL;
			listMessage.forEach(function(message) {
				var idReceiver = message.id_Reciver;
				var idSender = message.id_Sender;
				var content = message.content;

				if (sender == idSender && reciever == idReceiver)
					insertMessage(content);
				else if (sender == idReceiver && reciever == idSender) {

					receiveMessageimage(content, imageURL);
				}
			});
			document.getElementById('img-hide').src = imageURL;
			document.getElementById('name-hide').textContent = name;
			document.getElementById('age-hide').textContent = "Tuổi: " + age;
			document.getElementById('gender-hide').textContent = "Giới tính: " + gender;
			//document.getElementById('birthday-hide').textContent ="Ngày sinh: " + birthday;
			document.getElementById('relationship-hide').textContent ="Tình trạng mối quan hệ: " + relationship;
			document.getElementById('height-hide').textContent ="Chiều cao: " + height;
			document.getElementById('zodiac-hide').textContent ="Cung hoàng đạo: " + zodiac;
			document.getElementById('introduce-hide').textContent ="Giới thiệu: " + introduce;
			document.getElementById('address-hide').textContent ="Địa chỉ: " + address;
			
		}
		// ... Các xử lý khác của bạn ...
	</script>
	<script src="../Access/Style/js/Base.js"></script>
</body>
</html>