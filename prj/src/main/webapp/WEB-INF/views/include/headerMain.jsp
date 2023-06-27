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

<link rel="stylesheet" href="./resources/css/nav.css" />
<link rel="stylesheet" href="./resources/css/nav/navMain.css" />
<link rel="stylesheet" href="./resources/css/footer.css" />
<%-- <link rel="stylesheet"
   href="${pageContext.request.contextPath}/css/nav.css" /> --%>
<!-- <link rel="stylesheet" href="./css/main.css" /> -->
</head>
<body>
	<header>
		<div class="header-con">
		</div>
			<img class="logo box0" src="./resources/assets/logo.png" />
		
		<div class="box-wrap">

			<%
				if (managerId != null) {
			%>
			<div class="box1 box-box">
				<span>LogOut</span>
			</div>
			<div class="box6"></div>
			<%
				} else if (userId == null) {
			%>
			<div class="box1 box-box">
				<span>LogIn</span>
			</div>
			<div class="box6"></div>
			<%
				} else if (kakaoId == null) {
			%>
			<div class="box6 box-box MyPage-box">
				<span>MyPage</span>
			</div>
			<div class="box1"></div>
			<%
				} else {
			%>
			<div class="box6 box-box MyPage-box">
				<span>MyPage</span>
			</div>
			<div class="box1"></div>
			<%
				}
			%>

			<div class="box2 box-box">
				<span>Q&A</span>
			</div>
			<div class="box3 box-box">
				<span>Community</span>
			</div>
			<div class="box4 box-box">
				<span>Random</span>
			</div>
			<div class="box5 box-box">
				<span>Play!</span>
			</div>
		</div>
		<nav>

			<ul>

				<li>
					<%
						if (managerId != null) {
					%> <a
					href="<%=request.getContextPath()%>/manager_page.do">ManagerPage</a>
					<a href="<%=request.getContextPath()%>/manager_logOut.do">LogOut</a>
					<%
						} else if (userId == null) {
					%> <a class = "login-a-href"  href="<%=request.getContextPath()%>/member_main.do">LogIn</a>
					<%
						} else if (kakaoId == null) {
					%> <a
					href="<%=request.getContextPath()%>/member_logOut.do">LogOut</a> <%
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
	<script src="./resources/js/nav/navMain.js"></script>
</body>
</html>