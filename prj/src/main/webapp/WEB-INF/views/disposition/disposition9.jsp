<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO 성향 검사</title>
<link rel="stylesheet" href="./resources/css/disposition/disposition.css">
<link rel="stylesheet" href="./resources/css/disposition/progressbar.css">
<style type="text/css">
.progress_now {
	height: 20px; /* 전체 높이와 똑같이 */
	width: calc(100/ 15 * 9%);
	background-color: #FFD8D8; /* 프로그레스바가 채워질때 색 */
	margin-right: 500px;
	border-radius: 8px;
}
</style>
</head>
<body>

	<%@include file="../include/dispositionheader.jsp"%>
	<c:set var="dto" value="${dto}" />

	<div class="container1">

		<div align="center">
			<span class="title">TEST</span> <br> <br> <br>

			<div class="progress_container">
				<div class="progress_now"></div>
			</div>
		</div>

	</div>

	<div class="container2">

		<div align="center">

			<div>
				<h3 class="text">Q9.</h3>
			</div>

			<br>

			<div class="question">
				<h2>${dto.disposition_content }</h2>
			</div>

			<br> <br> <br>

			<div class="answer1">
				<input type="button" class="A" value="${dto.disposition_answer1 }"
					onclick="location.href='dispositionB_4_1.do?id=<%=userId%>&no=10'">
			</div>
			<br> <br>

			<div class="answer2">
				<input type="button" class="A" value="${dto.disposition_answer2 }"
					onclick="location.href='dispositionB_4_2.do?id=<%=userId%>&no=10'">
			</div>
			<br> <br>

			<div class="answer3">
				<input type="button" class="A" value="${dto.disposition_answer3 }"
					onclick="location.href='dispositionB_4_3.do?id=<%=userId%>&no=10'">
			</div>
			<br> <br>

		</div>

	</div>

	<script type="text/javascript" src="./resources/js/doubleclick.js"></script>

</body>
</html>