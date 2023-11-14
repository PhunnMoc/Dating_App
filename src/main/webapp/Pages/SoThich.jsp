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
    <title>CodePen - Checkbox group styled as tiles</title>

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous"
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
            <i class="fa fa-arrow-left" onclick="Back()"></i>
          </div>
        </div>
        <div class="col-11">
          <legend class="checkbox-group-legend">Chọn sở thích của bạn</legend>
        </div>
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
    <script>
      
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
    <script src="../Access/Style/js/SoThich.js"></script>
  </body>
</html>
