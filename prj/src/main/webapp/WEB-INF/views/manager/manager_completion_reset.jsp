<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mDto" value="${mDto}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ${mDto.id}회원의 신고 철회</title>
<link rel="stylesheet"
	href="./resources/css/manager/managerCheckPassword.css" />
<style>
.report-pwd-con {
	margin-top: 350px;
}

.footer {
	margin-top: 700px;
}
</style>
</head>
<body>
	<%@include file="../include/header05.jsp"%>
	<div class="report-pwd-con" align="center">
		<form method="post"
			action="<%=request.getContextPath()%>/manager_user_reset_ok.do">
			<input type="hidden" name="user_id" value="${mDto.id}"> <input
				type="hidden" name="db_pwd" value="${dto.manager_password}">
			<h3>
				신고가 승인되어 계정 정지된 ${mDto.id}회원의 신고 철회를 진행합니다.<br>계정을 복구시키려면 관리자
				비밀번호를 입력해 주세요.
			</h3>
			<br>비밀번호&nbsp;&nbsp; <input type="password"
				name="manager_password"> <input type="submit" value="확인">
		</form>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>