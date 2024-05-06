<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Models.UserHobby"%>
<%@ page import = "Models.Hobby"%>
<%@ page import = "Models.Account"%>
<%@ page import = "java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-Content-Type-Options" content="nosniff">
    <title>CodePen - Checkbox group styled as tiles</title>

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
    />
	<link rel="stylesheet" type="text/css" href="../Access/Style/css/SoThich.css" />
   <script
      src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
      data-auto-replace-svg="nest"
    ></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
    />
    <meta http-equiv="Content-Security-Policy" content="default-src 'self' ; 
        script-src  'self' 'nonce-ABC123' https://cdnjs.cloudflare.com/ajax/libs/hammer.js/2.0.8/hammer.min.js https://use.fontawesome.com/releases/v5.15.4/js/all.js https://code.jquery.com/jquery-3.6.4.min.js; 
        style-src 'self'  https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
        font-src 'self' https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css;
        connect-src 'self';
        img-src 'self' data: https://i.postimg.cc https://play-lh.googleusercontent.com;">

  </head>
  <body>
<%
    //Account account = (Account) session.getAttribute("acc");
    	    ////List < Image > listImage =  (List<Image>) request.getAttribute("listImage");
    %>
 	 <% /* if (account != null) {
	  response.sendRedirect("Login.html"); */
 // }
  %>
    <!-- partial:index.partial.html -->
    <fieldset class="checkbox-group">
      <div class="row">
        <div class="col-1">
          <div class="Back">
            <i class="fa fa-arrow-left" id="Back"></i>
          </div>
        </div>
        <fieldset>
		    <legend class="checkbox-group-legend">Chọn sở thích của bạn</legend>
		    <!-- Place your checkboxes or other form elements here -->
		</fieldset>
      </div>
      <form action="<%=request.getContextPath() %>/pro/updateHobby" method="post" accept-charset="UTF-8" class="checkbox-group" >
      <c:forEach var="hobby" items="${listAllHobby}">
     
      <div class="checkbox">
        <label class="checkbox-wrapper">
          <input type="checkbox" class="checkbox-input" name = "listHobby" 
          value = <c:out value= "${hobby.iDhobby}" />
			<c:forEach var="userHobby" items="${listHobby}">
            <c:if test="${userHobby.hobbyName eq hobby.hobbyName}">
            checked
        	</c:if>
           </c:forEach> 
			/>       
          <span class="checkbox-tile">
            <span class="checkbox-icon">
			<i class="fa-solid fa-x"></i>         
            </span>
            <span class="checkbox-label"><c:out value= "${hobby.hobbyName}" /></span>
          </span>
        </label>
      </div>
		</c:forEach>
		
      <button type ="submit">Done Editing</button>
      </form>
    </fieldset>
    <!-- partial -->
    <script nonce= "ABC123" >
    document.getElementById("Back").addEventListener("click", function() {
		Back();
	});
      var checkboxgroup = document.getElementsByClassName("checkbox-input");

    var limit = 3;
	  for (var i = 0; i < checkboxgroup.length; i++) {
		checkboxgroup[i].onclick = function() {
			var checkedcount = 0;
				for (var i = 0; i < checkboxgroup.length; i++) {
				checkedcount += (checkboxgroup[i].checked) ? 1 : 0;

			}
			if (checkedcount > limit) {
				console.log("You can select maximum of " + limit + " checkbox.");
				alert("You can select maximum of " + limit + " checkbox.");
				this.checked = false;
			}
		}
	}
    </script>
    <script nonce= "ABC123"  src="../Access/Style/js/SoThich.js"></script>
  </body>
</html>
