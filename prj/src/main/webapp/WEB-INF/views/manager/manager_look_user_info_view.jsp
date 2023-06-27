<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user_nick_name" value="${user_nick_name}" />
<c:set var="dto" value="${managerLookUserInfoView}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.user_id} 회원 정보 페이지</title>
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" /> 
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f3f3f3;
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
.communityListWrap {
	margin-left: 250px;
	
}
.curr-temp{
    position: relative !important;
    bottom: 45px  !important;
}
</style>
</head>
<body>
	<%@include file="../include/header05.jsp"%>
	<div id="main_container">
		<div class="header1"><h2 align="center">${user_nick_name}회원 정보 페이지</h2></div>
			<div class="communityListWrap">
	        	<table class="table">
					<tr>
					   <th>유저 아이디</th>
					   <th>유저 이름</th> 
					   <th>유저 닉네임</th>
					   <th>유저 계정 상태</th>
					   <th>해당 유저 작성 글</th>
					   <th>해당 유저 작성 댓글</th>
					</tr>
        			<tr>
						<td>${dto.user_id}</td>
						<td>${dto.user_name}</td>
						<td>${dto.user_nickname}</td>
						<c:if test="${dto.user_account_state == 0}">
							<td>활성화</td>
						</c:if>
						<c:if test="${dto.user_account_state == 1}">
							<td>비활성화</td>
						</c:if>
						<td><a id="community_title" href="<%=request.getContextPath() %>/member_community_profile_view.do?user_nick_name=${user_nick_name}">해당 유저 작성 글</a></td>
						<td><a id="community_title" href="<%=request.getContextPath() %>/member_community_reply_profile_view.do?user_nick_name=${user_nick_name}">해당 유저 작성 댓글</a></td>
					</tr>
      			</table>
      		</div>
		</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>