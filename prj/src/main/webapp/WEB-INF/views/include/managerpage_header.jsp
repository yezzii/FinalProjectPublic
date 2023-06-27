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
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script> -->
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>

<link rel="stylesheet" href="./resources/css/manager/managerpage_header.css" />
<link rel="stylesheet" href="./resources/css/manager/manager_footer.css" />
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav.css" /> --%>
<!-- <link rel="stylesheet" href="./css/main.css" /> -->
</head>
<body>
	<header>
		<div class="header-con"></div>
		<a href="<%=request.getContextPath()%>/main.do"> <img class="logo"
			src="./resources/assets/logo.png" /></a>
		<nav>
			<ul>
				<li>
					<%
						if (managerId != null) {
					%> <a href="<%=request.getContextPath()%>/manager_page.do">ManagerPage</a>
					<a href="<%=request.getContextPath()%>/manager_logOut.do">LogOut</a>
					<%
						} else if (userId == null) {
					%> <a href="<%=request.getContextPath()%>/member_main.do">LogIn</a>
					<%
						} else if (kakaoId == null) {
					%> <a href="<%=request.getContextPath()%>/member_logOut.do">LogOut</a>
					<%
						} else {
					%> <a
					href="https://kauth.kakao.com/oauth/logout?client_id=d2c2065b3a9570a58fb10819a2d0eb5c&logout_redirect_uri=http://localhost:8585/controller/member_logOut.do">LogOut</a>
					<%
						}
					%>
				</li>
			</ul>
		</nav>

		<div class="search-home-container" id="search_home_container">
			<form method="post" id="search-form"
				action="<%=request.getContextPath()%>/Search_list.do">
				<input type="text" placeholder="Search..." name="search">
				<button type="submit">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
	</header>
</body>
</html>