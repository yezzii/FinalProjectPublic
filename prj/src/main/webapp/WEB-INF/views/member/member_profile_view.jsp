<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user_nick_name" value="${user_nick_name}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${user_nick_name} 회원 정보 페이지</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f3f3f3;
}

.member-profile-con{
margin-top: 250px;
padding-top: 50px;
margin-left:650px;
background-color: #f8f8f8;

width: 500px;
height: 275px;



}
.footer{
margin-top: 500px !important;
}
.container {
	max-width: 500px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h3 {
	margin-top: 20px;
	font-weight: bold;
}

p {
	margin-top: 10px;
}

a {
	color: #226456;
	text-decoration: none;
}

a:hover {
	color: #1a473e;
}

hr {
	border: none;
	height: 2px;
	background-color: #b19cd9;
	margin-top: 20px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<%@include file="../include/header05.jsp"%>
	<div class ="member-profile-con" align = "center">
			<h3>${user_nick_name}회원 정보 페이지</h3>
		<br>
		<p><a href="<%=request.getContextPath() %>/member_community_profile_view.do?user_nick_name=${user_nick_name}">작성 글</a></p>
		<p><a href="<%=request.getContextPath() %>/member_community_reply_profile_view.do?user_nick_name=${user_nick_name}">작성 댓글</a></p>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>