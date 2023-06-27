<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String managerId = (String) session.getAttribute("ManagerId");
String userId = (String) session.getAttribute("UserId");
String kakaoId = (String) session.getAttribute("KakaoId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>

<link rel="stylesheet" href="./resources/css/disposition/dispositionnav.css" />

</head>
<body>
	<header>

			<a href="<%=request.getContextPath()%>/main.do"> <img
				class="logo" src="./resources/assets/logo.png" /></a>
		
	</header>
</body>
</html>