<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Models.Account"%>
<%@ page import = "Models.UserHobby"%>
<%@ page import = "Models.Image"%>
<%@ page import = "Models.Profile"%>
<%@ page import = "java.util.List"%>

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
    <link rel="stylesheet" href="../Access/Style/css/InforLogin.css" />

  </head>

  <body>
  
<style>
@import url("https://fonts.googleapis.com/css?family=Nunito:400,900|Montserrat|Roboto");
body {
  background: var(--color-theme-1)
}
:root{
    --color-theme: linear-gradient(to bottom right, lightyellow, lightpink);
    --side-bar-color:rgba(185, 185, 185, 0.493);
    --nav-bar-color: black;
    --box-ava: white;
    --color-theme-1: linear-gradient(to bottom right, lightyellow, lightpink);  
    --color-theme-2: linear-gradient(to right, #FF4B2B, #FF416C);
    --color-theme-3: linear-gradient(to right, #d6563f, #db4a6b);
    --color-theme-4: linear-gradient(to right, #ea300f, #f31045);
    --color-theme-5: linear-gradient(to bottom right, rgb(250, 250, 232), rgb(250, 223, 227));
    /* font, size, color ... */
/* font, size, color ... */
}

body{
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 0;
    margin: 0;
}
/* width */
::-webkit-scrollbar {
    width: 10px;
    height: 10px;
}
::-webkit-scrollbar {
    width: 5px;
  }
  
  /* Track */
  ::-webkit-scrollbar-track {
    background: #f1f1f1; 
    background: transparent 
  }
   
  /* Handle */
  ::-webkit-scrollbar-thumb {
    background: #888; 
  }
  
  /* Handle on hover */
  ::-webkit-scrollbar-thumb:hover {
    background: #555; 
    background: transparent; 
  }
  
  /* Handle on hover */
  .left-sidebar:hover::-webkit-scrollbar-thumb {
    background: #555; 
  }
  dl, ol, ul {
    margin: 0 !important;
}

.mar-lef-15 {
  margin-left: -15px;
}
.font-25 {
  font-size: 25px !important;
}
.mar-top-7 {
  margin-top: -7%;
}
.container {
  background: #FFFFFF;
  width: 940px;
  height: 620px;
  margin: 0 auto;
  position: relative;
  margin-top: 10%;
  box-shadow: 2px 5px 20px rgba(119, 119, 119, 0.5);
}

.logo {
  float: right;
  margin-right: 12px;
  margin-top: 12px;
  font-family: "Nunito Sans", sans-serif;
  color: rgb(238, 19, 81);
  font-weight: 900;
  font-size: 1.4em;
  letter-spacing: 1px;
}

.CTA {
  width: 119px;
  height: 62px;
  right: -45px;
  bottom: 0;
  margin-bottom: 90px;
  position: absolute;
  z-index: 1;
  background: var(--color-theme-2);
  font-size: 1em;
  transform: rotate(-90deg);
  transition: all 0.5s ease-in-out;
  cursor: pointer;
}
.CTA h1 {
  color: #FFFFFF;
  margin-top: 5px;
  margin-left: 9px;
}
.CTA:hover {
  background: green !important;
  transform: scale(1.1);
}

.leftbox {
  float: left;
  top: -5%;
  left: 5%;
  position: absolute;
  width: 15%;
  height: 110%;
  background: var(--color-theme-2);
  box-shadow: 3px 3px 10px rgba(216, 181, 181, 0.5);
}

nav a {
  list-style: none;
  padding: 45px;
  color: #FFFFFF;
  font-size: 2em;
  display: block;
  transition: all 0.3s ease-in-out;
  margin-left: 6px;
}
nav a:hover {
  color: var(--nav-bar-color);
  transform: scale(1.2);
  cursor: pointer;
}
nav a:first-child {
  margin-top: -19px;
}

.active {
  color: var(--nav-bar-color);
}


  

.rightbox {
  float: right;
  width: 60%;
  height: 100%;
}

.profile, .payment, .subscription, .privacy, .settings {
  transition: opacity 0.5s ease-in;
  position: absolute;
  width: 70%;
  /* display: none; */
}

h1 {
  font-family: "Montserrat", sans-serif;
  color: rgb(217, 55, 101);
  font-size: 2em;
  margin-top: 40px;
  margin-bottom: 35px;
}

h2 {
  color: #777777;
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  font-size: 15px;
  letter-spacing: 1px;
  margin-left: 2px;
  width: auto;
}

p, textarea {
  border-width: 1px;
  border-style: solid;
  border-image: linear-gradient(to right, #3FB6A8, rgba(126, 211, 134, 0.5)) 1 0%;
  border-top: 0;
  width: 80%;
  font-family: "Montserrat", sans-serif;
  font-size: 0.7em;
  padding: 7px 0;
  color: #070707;
  resize: none;
}

span {
  font-size: 0.5em;
  color: #777777;
}

.btn {
  float: right;
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  font-size: 18px;
  border: none;
  color: #3FB6A8;


}

.btn:hover {
  text-decoration: underline;
  font-weight: 900;
  cursor: pointer;
}

input, select {
  border: 0px solid #dddddd;
  width: 80%;
  font-family: "Montserrat", sans-serif;
  font-size: 25px;
  color: #070707;
}


.privacy h2 {
  margin-top: 25px;
}

.settings h2 {
  margin-top: 25px;
}

.noshow {
  display: none;
}

footer {
  position: absolute;
  width: 20%;
  bottom: 0;
  right: -20px;
  text-align: right;
  font-size: 0.8em;
  text-transform: uppercase;
  letter-spacing: 2px;
  font-family: "Roboto", sans-serif;
}
footer p {
  border: none;
  padding: 0;
}
footer a {
  color: #ffffff;
  text-decoration: none;
}
footer a:hover {
  color: #7d7d7d;
}
button {
  z-index: 100;
}
.btn-1 {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  font-size: 18px;
  border: none;
  color: #3FB6A8;
  margin-left: 430px;
}

/* *, *:before, *:after {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }
   */
/* 
  
  input, button, select {

    font-size: 14px;
  }
   */
   .app-wrap {
    background: var(--color-theme-5);
  }
  
  .upload-form {
    display: flex;
    justify-content: center;
    width: 100%;
    border-bottom: 1px solid #D6D5D1;
    background-color: white;
    padding: 5px 0;
  }
  .upload-form__field {
    padding:0px 10px 0;
    display: flex;
    align-items: center;
  }
  .upload-form__field input {
    font-size: 14px !important;
  }
  .upload-form__field label {
    width: 70px;
  }
  .upload-form__field input[type=text] {
    padding: 7px 10px;
    border-radius: 3px;
    border: 1px solid darkGray;
    flex: 1;
  }
  .upload-form__submit {
    text-align: center;

  }
  .upload-form__submit__action {
    border: none;
    padding: 10px 25px;
    background: var(--color-theme-2);
    border-radius: 3px;
    font-weight: 700;
    color: white;
    cursor: pointer;
    transition: all 0.3s ease;
  }
  .upload-form__submit__action:hover {
    background-color: DodgerBlue;
  }
  .upload-form .error {
    text-align: center;
    color: tomato;
    font-weight: 700;
    width: 100%;
    margin: 1px;
  }
  
  .grid-wrap {
    border: 2px solid pink;
    width: 100%;
    display: flex;
    justify-content: center;
  }
  
  .picture-list {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-gap: 20px;
    max-width: 1024px;
    list-style: none;
    margin: 0;
    overflow-y: scroll;
    max-height: 300px;    
    padding: 5px;
  }
  @media (min-width: 640px) {
    .picture-list {
      grid-template-columns: repeat(3, 1fr);
    }
  }
  @media (min-width: 1024px) {
    .picture-list {
      grid-template-columns: repeat(3, 1fr);
    }
  }
  .picture-list__item__wrap {
    box-shadow: 6px 6px 15px 1px rgba(204, 204, 204, 0.5);
    background: white;
    border-radius: 3px;
    display: flex;
    flex-direction: column;
  }
  .picture-list__item__image {
    width: 100%;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    max-height: 300px;
  }
  .picture-list__item__name {
    padding: 10px;
    font-weight: 700;
    display: inline-block;
    margin-top: auto;
  }
  
  .bold {
    font-weight: 700;
  }

  .frame-image {
    overflow-y: scroll;
    max-height: 200px;
  }
</style>    
    <%
    //Account account = (Account) session.getAttribute("acc");
    	    Profile profile = (Profile) request.getAttribute("profile");
    	    ////List < Image > listImage =  (List<Image>) request.getAttribute("listImage");
    	   // List < UserHobby > listHobby = (List<UserHobby>) request.getAttribute("listHobby");
    %>
 	 <% /* if (account != null) {
	  response.sendRedirect("Login.html"); */
 // }
  %>
    <!-- partial:index.partial.html -->
    <div class="mar-top-7">
    <form action="<%=request.getContextPath() %>/update" method="post" accept-charset="UTF-8">
      <div class="container">
        <div id="logo">
          <h1 class="logo">TeenTher</h1>
          
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
              <label style="margin-left: -100px" class="font-25" for=""
                >(cm)</label
              >
            </p>
            <h2>Cung hoàng đạo</h2>
            <p>
              <select name="cunghoangdao" id="cunghoangdao" >
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
                <option value="Song Ngư">Song Ngư </option>
                <option value="Thiên Yết">Thiên Yết</option>
              </select>
            </p>
            <h2>Giới thiệu</h2>
            <textarea
              class="font-25"
              placeholder="Giới thiệu"
              name="introduce"
              id=""
              cols="50"
              rows="4"
              locked="true"
            ><%=profile.getIntroduce()%></textarea>
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
            <h2>Địa chỉ</h2>
            <textarea
              class="font-25"
			  placeholder="Địa chỉ của bạn"
              name="address"
              id=""
              cols="50"
              rows="4"
              locked="true"
            ><%=profile.getAddress()%></textarea>
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
              <div >
              <button type="submit"><a class="CTA" href="<%=request.getContextPath()%>/update" style="text-decoration: none"
              ><h1>Xong</h1></a
            ></button>
            
          </div>
      </div>
    </div>
    </form>
    <!-- partial -->
 
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../Access/Style/js/InforLogin.js"></script>
    <script type="text/javascript">
    /*active button class onclick*/
    $('nav a').click(function(e) {
        e.preventDefault();
        $('nav a').removeClass('active');
        $(this).addClass('active');
        if(this.id === !'payment'){
          $('.payment').addClass('noshow');
        }
        else if(this.id === 'payment') {
          $('.payment').removeClass('noshow');
          $('.rightbox').children().not('.payment').addClass('noshow');
        }
        else if (this.id === 'profile') {
          $('.profile').removeClass('noshow');
           $('.rightbox').children().not('.profile').addClass('noshow');
        }
        else if(this.id === 'subscription') {
          $('.subscription').removeClass('noshow');
          $('.rightbox').children().not('.subscription').addClass('noshow');
        }
          else if(this.id === 'privacy') {
          $('.privacy').removeClass('noshow');
          $('.rightbox').children().not('.privacy').addClass('noshow');
        }
        else if(this.id === 'settings') {
          $('.settings').removeClass('noshow');
          $('.rightbox').children().not('.settings').addClass('noshow');
        }
      });


      
    function _defineProperty(obj, key, value) {
      if (key in obj) {
          Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true });
      } else {
          obj[key] = value;
      }
      return obj;
    }
    var config = {
      apiKey: "AIzaSyAH_ooTUeL-zbmqwiPWxeoymFRrcAyomYs",
      authDomain: "datingapp-8390d.firebaseapp.com",
      databaseURL: "https://datingapp-8390d-default-rtdb.firebaseio.com",
      projectId: "datingapp-8390d",
      storageBucket: "datingapp-8390d.appspot.com",
      messagingSenderId: "182475800709",
      appId: "1:182475800709:web:48ac3a2bba138f08f769ec",
      measurementId: "G-8CN7BQQRNC" };

    firebase.initializeApp(config);


    class UploadForm extends React.Component {

      constructor(props) {
        super(props);_defineProperty(this, "handleUpload",

        e => {
          const file = e.target.files[0];
          if (file.type === 'image/png' || file.type === 'image/jpeg') {
            this.setState({ picture: file });
          } else {
            this.setState({ error: "Invalid file format" });
          }
        });_defineProperty(this, "handleClick",

        () => {
          if (this.state.name !== '' && this.state.picture !== null) {
            this.props.onClick(this.state);
            this.setState({ error: null, name: '', picture: null });
          } else {
            this.setState({ error: "Hãy tải ảnh lên trước" });
          }
        });this.state = { picture: null, name: '', error: null };}

      handleChange(e) {
        const value = e.target.value;
        this.setState({ name: value });
      }

      render() {
        return /*#__PURE__*/(
          React.createElement("div", { className: "upload-form" }, /*#__PURE__*/
          React.createElement("div", { className: "upload-form__wrap" }, /*#__PURE__*/

          React.createElement("div", { className: "upload-form__field" }, /*#__PURE__*/
          React.createElement("h2", null, "Picture:"), /*#__PURE__*/
          React.createElement("input", { type: "file", onChange: e => this.handleUpload(e), accept: "image/gif, image/jpeg, image/png" })), /*#__PURE__*/

          
          React.createElement("div", { className: "upload-form__field" }, /*#__PURE__*/
          React.createElement("h2", null, "Name:"), /*#__PURE__*/
          React.createElement("input", { type: "text", value: this.state.name, onChange: e => this.handleChange(e) })), /*#__PURE__*/


          React.createElement("div", { className: "upload-form__submit" }, /*#__PURE__*/
          React.createElement("button", { className: "upload-form__submit__action", onClick: () => this.handleClick() }, "Upload Picture")),


          this.state.error && /*#__PURE__*/
          React.createElement("p", { className: "error" }, this.state.error))));




      }}


    class App extends React.Component {

      constructor(props) {
        super(props);_defineProperty(this, "handleUpload",


        data => {

          const file = data.picture;
          const storageRef = firebase.storage().ref(`images/${file.name}`);
          const task = storageRef.put(data.picture);

          // Listener que se ocupa del estado de la carga del fichero
          task.on('state_changed', snapshot => {
            // console.log('loading...');

          }, error => {
            console.error(error.message);

          }, () => {

            const record = {
              image: task.snapshot.downloadURL,
              displayName: data.name };


            const dbRef = firebase.database().ref('/pictures');
            const newPicture = dbRef.push();
            newPicture.set(record);

          });
        });this.state = { pictures: [] };}componentWillMount() {firebase.database().ref('pictures').on('child_added', snapshot => {
          const picture = snapshot.val();
          // if (picture.displayName == "k") 
          {
            console.log(picture.displayName)
            this.setState({ pictures: this.state.pictures.concat(picture) });
          }
        });}

      render() {

        return /*#__PURE__*/(
          React.createElement("div", { className: "app-wrap" }, /*#__PURE__*/
          React.createElement(UploadForm, { onClick: this.handleUpload }), /*#__PURE__*/
          React.createElement("div", { className: "grid-wrap" }, /*#__PURE__*/
          React.createElement("ul", { className: "picture-list" },

          this.state.pictures.map((picture, index) => /*#__PURE__*/
          React.createElement("li", { className: "picture-list__item", key: index }, /*#__PURE__*/
          React.createElement("div", { className: "picture-list__item__wrap" }, /*#__PURE__*/
          React.createElement("img", { className: "picture-list__item__image", src: picture.image, alt: picture.displayName }) /*#__PURE__*/
    ))).


          reverse()))));






      }}



    ReactDOM.render( /*#__PURE__*/React.createElement(App, null), document.getElementById('root'));
    
    
    document.getElementById("cunghoangdao").value = "<%=profile.getZodiac() %>";
    document.getElementById("relationship").value = "<%=profile.getRelationship() %>";
    </script>
  </body>
</html>
