<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO 관리자 로그인</title>
<style>
 form {
	margin-top: 250px;
}

input[name="manager_id"], input[name="manager_password"], input[type="submit"]
	{
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
}

input[type="submit"]:hover, button:hover {
	background-color: #1a473e;
}


.form-wrap {
	display: flex;
	justify-content: center;
	align-items: center;
}

.manager_box {
	width: 270px;
	text-align: center;
	margin-top: -10px !important; 
}

.A {
	width: 100%;
	box-sizing: border-box;
}


</style>
</head>
<body>
	<%@include file="../include/manager_header.jsp"%>
	
	<form method="post"
		action="<%=request.getContextPath()%>/manager_login.do">
		
		<div class="form-wrap">
			<div class="manager_box">
				TODO 관리자 로그인 <br> <br> 
				<input class="A" name="manager_id" placeholder="아이디"> <br> 
				<input class="A" type="password" name="manager_password" placeholder="비밀번호"> <br> 
				<input class="A" type="submit" value="login">
			</div>
		</div>

	</form>
	<%@include file="../include/manager_footer.jsp"%>
</body>
</html>