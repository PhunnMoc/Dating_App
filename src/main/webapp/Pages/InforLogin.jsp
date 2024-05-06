<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.Account"%>
<%@ page import="Models.UserHobby"%>
<%@ page import="Models.Profile"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Base64"%>
<!DOCTYPE html>
<html lang="en" style="height: 120%; overflow: hidden">
<head>
<meta charset="UTF-8" />
<title>Profile Hobby</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" />
<link rel="stylesheet" href="../Access/Style/css/root/root.css" />
<style>
	.mgl-100{
		margin-left: -100px;
	}
	.text{
		text-decoration: none;
	}
</style>
<link rel="stylesheet" href="../Access/Style/css/InforLogin.css" />
<meta http-equiv="Content-Security-Policy" content="default-src 'self' ; 
        script-src  'self' 'nonce-ABC123' https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js; 
        style-src 'self'  https://use.fontawesome.com/releases/v5.0.10/css/all.css ;
        font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
        connect-src 'self'; img-src 'self' data: https://i.postimg.cc;  form-action 'self' ;">
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
	else
	{
		//response.sendRedirect(request.getContextPath() + "/pro/list");
		//response.sendRedirect("/pro/list");
	}
	%>
	<!-- partial:index.partial.html -->
	<div class="mar-top-7">
		<form action="<%=request.getContextPath()%>/pro/update" method="post"
			accept-charset="UTF-8" enctype="multipart/form-data">
			<div class="container">
				<div id="logo">
					<h1 class="logo">TeenTher</h1>

				</div>
				<div class="leftbox">
					<nav>
						<a id="profile" class="active"><i class="fa fa-user"></i></a> <a
							id="payment"><i class="fa fa-tasks"></i></a> <a id="subscription"><i
							class="fas fa-images"></i></a> <a id="privacy"><i
							class="fas fa-address-book"></i></a> <a id="settings"><i
							class="fa fa-cog"></i></a>
					</nav>
				</div>
				<div class="rightbox">
					<div class="profile">
						<h1>Thông tin cá nhân</h1>
						<h2>Họ và tên</h2>
						<p>
							<input type="text" name="name" value="<%=profile.getName()%>" />
						</p>
						<h2>Ngày sinh</h2>
						<p>
							<input type="date" name="birthday"
								value="<%=profile.getBirthDay()%>" />
						</p>
						<h2>Giới tính</h2>
						<p>
							<select name="gender" id="gioi-tinh">
								<option value="Nam">Nam</option>
								<option value="Nữ">Nữ</option>
								<option value="Khác">Khác</option>
							</select>
						</p>
						<h2>Tình trạng mối quan hệ</h2>
						<p>
							<select name="relationship" id="relationship">
								<option value="Độc thân">Độc thân</option>
								<option value="Hẹn hò">Hẹn hò</option>
								<option value="Đã kết hôn">Đã kết hôn</option>
								<option value="Góa">Góa</option>
							</select>
						</p>
					</div>
					<div class="payment noshow">
						<h1>Thông tin chi tiết</h1>

						<h2>Chiều cao</h2>
						<p>
							<input type="text" name="height" value="<%=profile.getHeight()%>" />
							<label  class="font-25 mgl-100" for="">(cm)</label>
						</p>
						<h2>Cung hoàng đạo</h2>
						<p>
							<select name="cunghoangdao" id="cunghoangdao">
								<option value="Bạch Dương">Bạch Dương</option>
								<option value="Kim Ngưu">Kim Ngưu</option>
								<option value="Song Tử">Song Tử</option>
								<option value="Cự Giải">Cự Giải</option>
								<option value="Sư Tử">Sư Tử</option>
								<option value="Xử Nữ">Xử Nữ</option>
								<option value="Nhân Mã">Nhân Mã</option>
								<option value="Thiên Bình">Thiên Bình</option>
								<option value="Bảo Bình">Bảo Bình</option>
								<option value="Ma Kết">Ma Kết</option>
								<option value="Song Ngư">Song Ngư</option>
								<option value="Thiên Yết">Thiên Yết</option>
							</select>
						</p>
						<h2>Giới thiệu</h2>
						<textarea class="font-25" placeholder="Giới thiệu"
							name="introduce" id="" cols="50" rows="4" locked="true"><%=profile.getIntroduce()%></textarea>
					</div>
					<div class="subscription noshow">
						<h1>Ảnh của bạn</h1>
						<div class="app-wrap">
							<div class="upload-form">
								<div class="upload-form__wrap">
									<div class="upload-form__field">
										<h2>Picture:</h2>
										<input type="file" accept="image/gif, image/jpeg, image/png"
											name="image">
									</div>
								</div>
							</div>
							<div class="grid-wrap">
								<ul class="picture-list">
									<li class="picture-list__item"><div
											class="picture-list__item__wrap">
											<%
											if (imageData != null) {
											%>
											<img class="picture-list__item__image"
												src="data:image/jpeg;base64, <%=imageData%>" alt="Image">
											<%
											} else {
											%>
											<p>No image available</p>
											<%
											}
											%>

										</div></li>
								</ul>
							</div>
						</div>
					</div>

					<div class="privacy noshow">
						<h1>Thông tin thêm</h1>
						<h2>Ngôn ngữ bạn biết</h2>
						<p>
							<select name="ngon-ngu" id="ngon-ngu">
								<option value="Tiếng Việt">Tiếng Việt</option>
								<option value="Tiếng Anh">Tiếng Anh</option>
								<option value="Khác">Khác</option>
							</select>
						</p>
						<h2>Địa chỉ</h2>
						<textarea class="font-25" placeholder="Địa chỉ của bạn"
							name="address" id="" cols="50" rows="4" locked="true"><%=profile.getAddress()%></textarea>
					</div>
					<div class="settings noshow">
						<h1>Cài đặt tài khoản</h1>
						<h2>Chế độ</h2>
						<p>
							<select name="che-do" id="che-do">
								<option value="Sáng">Sáng</option>
								<option value="Tối">Tối</option>
							</select>
						</p>
					</div>
				</div>
				<div>
					<button type="submit">
						<a class="CTA text" ><h1>Xong</h1></a>
					</button>

				</div>
			</div>
	</div>
	</form>
	<!-- partial -->


	<script nonce= "ABC123"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script nonce= "ABC123" src="../Access/Style/js/InforLogin.js"></script>
	<script nonce= "ABC123">
	    document.getElementById("cunghoangdao").value = "<%=profile.getZodiac() %>";
	    document.getElementById("relationship").value = "<%=profile.getRelationship() %>";
	</script>

</body>
</html>
