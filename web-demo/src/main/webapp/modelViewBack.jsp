<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>web app test</title>
</head>
<body>
hello!Welcome back!
<c:forEach items="${list}" var="user">
<ul> 
     <li> <c:out value="${user.id}"></c:out> </li>
     <li> <c:out value="${user.username}"></c:out> 1</li>
     <li> <c:out value="${user.password}"></c:out> 2</li>
</ul>
</c:forEach>
</body>
</html>