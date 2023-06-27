<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO LOGIN</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
<style>
form {
	margin-top: 20px;
}

input[name="id"],
input[name="password"] {
	margin-bottom: 10px;
	padding: 5px;
	width: 250px;
}

input:focus {
	outline: none;
}

.submit-btn {
	margin-bottom: 10px;
	padding: 5px;
	width: 263.64px;
}

input[type="submit"],
button {
	padding: 10px 20px;
	background-color: #226456;
	color: #fff;
	border: none;
	cursor: pointer;
}

input[type="submit"]:hover,
button:hover {
	background-color: #1a473e;
}

a {
	text-decoration: none;
	color: #226456;
	margin-right: 10px;
}

.p-2 {
	padding: 2px;
}

.img-2 {
position:relative;
left:5px;
	width: 270px;
	
}

#qna-link {
	font-weight: bold;
	color: #226456;
}

#signup-link {
	font-weight: bold;
	color: #226456;
}

.member-wrap {
/* position: absolute;
top:250px;
left:50%;
transform: translate(-50%,-50%); */
padding-top: 100px;
}

.btn-width{
width: 250px;
}


</style>
</head>
<body>
	<%@include file="../include/header05.jsp"%>
	<div class ="member-wrap" align = "center">
		<form method="post" action="<%=request.getContextPath() %>/member_login.do">
			TODO LOGIN<br><br>
			<input name="id"  placeholder="아이디"><br>
			<input type="password" name="password" placeholder="비밀번호"><br>
			<input type = "submit" class= "submit-btn btn-width" value = "Login">
		</form>
			<p><a href="<%=request.getContextPath() %>/member_findId_page.do">아이디 찾기</a> | &nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/member_findPwd_page.do">비밀번호 찾기</a> |  &nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/member_insert.do">회원가입</a></p>
			<br>
			TODO KAKAO LOGIN<br><br>
			<a class="p-2" href="https://kauth.kakao.com/oauth/authorize?client_id=d2c2065b3a9570a58fb10819a2d0eb5c&redirect_uri=http://localhost:8585/controller/member_kakaoLogin.do&response_type=code"><img class = "img-2" src="./resources/images/kakao_login_medium_wide.png"></a>
			<!-- 해당 프로젝트는 redirect_uri을http://localhost:8585/controller/member_kakaoLogin.do 으로 했습니다. -->
			<!-- 이미지는 카카오 개발자센터에서 제공하는 login 이미지를 사용했습니다. -->
			<br><br>고객센터를 통해 TODO에 대하여 알아보세요!<br><br>
			<a href="<%=request.getContextPath() %>/qna_main.do">고객센터로 이동하기</a>
	</div>
	<%@include file="../include/footer_member_main.jsp"%>
</body>
</html>