<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="./resources/css/disposition/dispositionResult.css">
<style type="text/css">
.refund_point {
	font-size: 20px;
	background-color: #D9E5FF;
	width: 200px;
	height: 50px;
	border-radius: 30px;
	border-style: none;
	font-family: 'S-CoreDream-3Light';
	font-weight: 700;
}

.refund_point:hover {
	background-color: #fff;
	transition: background-color 0.7s ease;
}

.error_page {
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -65%);
}
</style>
</head>
<body>

	<%@include file="../include/dispositionheader.jsp"%>

	<c:set var="dto" value="${Dto}" />
	<c:set var="result" value="${Result}" />

	<c:if test="${not empty result}">

		<div class="text-gallery" align="center">
			<div class="title">DISPOSITION TEST</div>
			<div class="text1">당신의 결과는 ?</div>
			<div class="text2">${dto.play_text}</div>
		</div>

		<br>

		<div class="photo-gallery">
			<c:forEach items="${result }" var="resultdto">

				<div class="photo-item" align="center">

					<a href="play_content.do?play_idx=${resultdto.play_index }"> <img
						class="play_img"
						src="./resources/assets/${resultdto.play_picture}">
					</a>


					<p class="name_text">${resultdto.play_name}</p>


					<!-- 여기서 이거 클릭하면 play_information 테이블에서 play_choice + 1하는 메서드  -->
					<input type="button" class="play_text" value="투표하기"
						onclick="location.href='dispositionCrawling.do?name=${resultdto.play_name}'">
				</div>

			</c:forEach>
		</div>

		<br>
		<br>
		<br>

		<!-- 검사 결과 나온 성향 타입을 쏴주고, 그 타입과 일치하는 행들의 play_picture 와  play_name 을
	play_choice 순서대로 테이블에서 보여준다. -->
		<div class="container1" align="center">
			<input type="button" class="matching2" value="나와 같은 유형의 사람들의 선택은 ?"
				onclick="location.href='dispositionRank.do?id=<%=userId%>'">
		</div>

	</c:if>

	<c:if test="${empty result}">


		<div class="error_page">

			<div class="text-gallery" align="center">
				<div class="title">DISPOSITION TEST</div>
				<div class="text1">당신의 결과는 ?</div>
				<div class="text2">${dto.play_text}</div>
			</div>

			<br> <br>

			<div align="center">
				<h2>페이지 오류로 인해서 성향 검사를 다시 실시해야합니다. 죄송합니다.</h2>
				<h3>사용되신 포인트 환불해드리겠습니다.</h3>
				<br> <input type="button" class="refund_point" value="메인 페이지"
					onclick="location.href='dispositionReset.do?id=<%=userId%>'">
			</div>

		</div>
	</c:if>

<script type="text/javascript">
setTimeout(function() {
	  document.querySelector('.refund_point').click();
	}, 10000);

</script>

</body>
</html>