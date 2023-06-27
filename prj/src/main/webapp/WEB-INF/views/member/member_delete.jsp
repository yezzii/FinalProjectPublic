<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<style type="text/css">

form {
	margin-top: 20px;
}

input[type="password"] {
	margin-bottom: 10px;
	padding: 5px;
	width: 250px;
}

input[type="submit"], button {
	padding: 10px 20px;
	background-color: #226456;
	color: #fff;
	border: none;
	cursor: pointer;
	width: 263.64px;
}

input[type="submit"]:hover {
	background-color: #1a473e;
}

input:focus {
	outline: none;
}

.delete-account-con {
	padding-top: 180px;
}

.password-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 48.5%;
}

#signup-btn {
	position: absolute;
	top: 100px;
	left: 63%;
	transform: translate(-50%, -50%);
}

.btn-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 51%;
}

.footer {
	margin-top: 500px !important;
}

</style>
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="delete-account-con" align="center">
		<form method="post" action="<%=request.getContextPath() %>/member_delete_ok.do">
			<input type="hidden" name="db_pwd" value="${dto.password}">
			<div>회원 탈퇴를 진행합니다.<br>정말 삭제하시려면 비밀번호를 입력해 주세요.</div>
			<div class ="password-con">
			<span>&nbsp;&nbsp;비밀번호&nbsp;&nbsp;&nbsp;</span>
			<input type="password" name="password">
			</div>
			<br>
			<div class="btn-con">
			<input type="submit" id="signup-btn" value="확인">
			</div>
		</form>
	</div>	
	<%@include file="../include/footer_light.jsp"%>
</body>
</body>
</html>