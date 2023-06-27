<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${dto}" />
<c:set var="reportedUserCount" value="${reportedUserCount}" />
<c:set var="completionUserCount" value="${completionUserCount}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Page</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f3f3f3;
}

.container {
	position: relative;
	top: 250px;
	max-width: 500px;
	margin-bottom: 378px !important;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	margin-left: 617px;
}

h2 {
	margin-top: 20px;
	font-weight: bold;
}

p {
	margin-top: 10px;
}

a {
	color: #226456;
	text-decoration: none;
	margin-right: 10px;
}

a:hover {
	color: #1a473e;
}
</style>
</head>
<body>
	<%@include file="../include/managerpage_header.jsp"%>
	
	<div align="center" class="container">
		<h3>Manager Page</h3>
		<p>
			관리자 번호 <a
				href="<%=request.getContextPath() %>/manager_code.do?manager_code=${dto.manager_code}">'${dto.manager_code}'</a>
			관리자
		</p>
		<p>
			<a href="<%=request.getContextPath()%>/manager_action_list.do">활동
				내역</a>
		</p>
		<p>
			접수 진행중인 신고 <a
				href="<%=request.getContextPath()%>/manager_report_user_list.do">${reportedUserCount}</a>건
			/ 완료된 신고 <a
				href="<%=request.getContextPath()%>/manager_reset_user_list.do">${completionUserCount}</a>건
		</p>
	</div>
	
	<%@include file="../include/managerpage_footer.jsp"%>
</body>
</html>