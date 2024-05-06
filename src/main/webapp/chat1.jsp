<!-- chat.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat Page</title>
</head>
<body>

<h1>List of Other Users</h1>
<c:forEach var="profile" items="${list_other_user}">
    <p>User ID: ${profile.userID}, Name: ${profile.name}, Image URL: ${profile.urlImage}</p>
</c:forEach>

<h1>Last Message</h1>
<c:if test="${last_Message != null}">
    <p>Time: ${last_Message.time}, Content: ${last_Message.content}</p>
</c:if>
<c:if test="${last_Message == null}">
    <p>No last message available.</p>
</c:if>

</body>
</html>
