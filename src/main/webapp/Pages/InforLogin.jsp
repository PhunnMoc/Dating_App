<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Models.Account"%>
<%@ page import = "Models.UserHobby"%>
<%@ page import = "Models.Image"%>
<%@ page import = "Models.Profile"%>
<%@ page import = "java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" style="height: 120%; overflow: hidden">
  <head>
    <meta charset="UTF-8" />
    <title>Profile Hobby</title>
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.0.10/css/all.css"
    />
    <link rel="stylesheet" href="../Access/Style/css/root/root.css" />
    <link rel="stylesheet" href="./style.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <link rel="stylesheet" href="../Access/Style/css/InforLogin.css" />

  </head>
  <body>
    <%
    Account account = (Account) session.getAttribute("acc");
    	    Profile profile = (Profile) session.getAttribute("profile");
    	    List < Image > listImage =  (List<Image>) session.getAttribute("listImage");
    	    List < UserHobby > listHobby = (List<UserHobby>) session.getAttribute("listHobby");
    %>
 	 <% if (account == null) {
	  response.sendRedirect("Login.html");
  }
  %>
    <!-- partial:index.partial.html -->
    <div class="mar-top-7">
      <div class="container">
        <div id="logo">
          <h1 class="logo">TeenTher</h1>
          <div class="CTA">
            <a href="./Match.html" style="text-decoration: none"
              ><h1>Xong</h1></a
            >
          </div>
        </div>
        <div class="leftbox">
          <nav>
            <a id="profile" class="active"><i class="fa fa-user"></i></a>
            <a id="payment"><i class="fa fa-tasks"></i></a>
            <a id="subscription"><i class="fas fa-images"></i></a>
            <a id="privacy"><i class="fas fa-address-book"></i></a>
            <a id="settings"><i class="fa fa-cog"></i></a>
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
            <p><input type="date" name="birthday" value="<%=profile.getBirthDay()%>"/></p>
            <h2>Giới tính</h2>
            <p>
              <select name="gender" id="gioi-tinh" >
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
                <option value="Khác">Khác</option>
              </select>
            </p>
            <h2>Tình trạng mối quan hệ</h2>
            <p>
              <select name="relationship" id="xu-huong">
                <option value="Độc thân">Độc thân</option>
                <option value="Hẹn hò">Hẹn hò</option>
                <option value="Đã kết hôn">Đã kết hôn</option>
                <option value="Góa">Góa</option>
              </select>
            </p>
          </div>
          <div class="payment noshow">
            <h1>Thông tin chi tiết</h1>
            <h2>Sở thích</h2>
            <c:forEach var="hobby" items="${listHobby}">
            <p class="font-25">
              <c:out value="${hobby.getHobbyName} " />
              <button class="btn" onclick="location.href='./SoThich.html'">
                Cập nhật
              </button>
            </p>
            </c:forEach>
            <h2>Chiều cao</h2>
            <p>
              <input type="text" name="height" value="<%=profile.getHeight()%>" />
              <label style="margin-left: -100px" class="font-25" for=""
                >(cm)</label
              >
              <button class="btn input-button">Cập nhật</button>
            </p>
            <h2>Mục đích hẹn hò</h2>
            <p>
              <select name="muc-dich" id="muc-dich">
                <option value="Người yêu">Người yêu</option>
                <option value="Quan hệ không ràng buộc">
                  Quan hệ không ràng buộc
                </option>
                <option value="Bất kỳ điều gì có thể">
                  Bất kỳ điều gì có thể
                </option>
                <option value="Chưa xác định">Chưa xác định</option>
              </select>
            </p>
            <h2>Giới thiệu</h2>
            <textarea
              class="font-25"
              placeholder="<%=profile.getIntroduce()%>"
              name="introduce"
              id=""
              cols="50"
              rows="4"
              locked="true"
            ></textarea>
          </div>
          <div class="subscription noshow">
            <h1>Ảnh của bạn</h1>
            <!-- <h2>Tải ảnh lên</h2>
            <p><button class="btn-1 mar-lef-15">Tải ảnh</button></p>
  
            <h2>Xem ảnh</h2>
            <p><button class="btn-1 mar-lef-15">Xem ảnh</button></p> -->
            <div id="root"></div>
            <script src="https://unpkg.com/react@16/umd/react.development.js"></script>
            <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
            <script src="https://www.gstatic.com/firebasejs/live/3.0/firebase.js"></script>
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
            <h2>Cung hoàng đạo</h2>
            <p class="font-25">
              Nhân Mã
              <button class="btn" onclick="location.href='./CungHoangDao.html'">
                Cập nhật
              </button>
            </p>
            <h2>Địa chỉ</h2>
            <textarea
              class="font-25"
              placeholder="<%=profile.getAddress()%>"
              name="address"
              id=""
              cols="50"
              rows="4"
              locked="true"
            ></textarea>
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
      </div>
    </div>

    <!-- partial -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../Access/Style/js/InforLogin.js"></script>
  </body>
</html>