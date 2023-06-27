<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/disposition/dispositionRank.css">
</head>
<body>

	<!-- play_information 테이블에서 play_choice 값 순서에 따라서 순서대로 보여주는 테이블 작성 필요 -->
	<%@include file="../include/dispositionheader.jsp"%>
	<c:set var="descList" value="${DescList }" />
	<c:set var="sum_choice" value="${SUM_choice }" />

	<div class="text-gallery" align="center">
		<div class="title">DISPOSITION RANK</div>
	</div>


	<div align="center">

		<table border="1" class="RankList">
			<tr>
				<th>순위</th>
				<th>이미지</th>
				<th>이름</th>
				<th>선택 비율</th>
			</tr>

			<c:forEach items="${descList }" var="descdto" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><a href="play_content.do?play_idx=${descdto.play_index }"><img class="play_img"
						src="./resources/assets/${descdto.play_picture }"></a></td>
					<td>${descdto.play_name }</td>
					<td><fmt:formatNumber
							value="${((descdto.play_choice / sum_choice) * 100)}"
							pattern="#,##0.00" />%</td>
				</tr>
			</c:forEach>

		</table>

	</div>

</body>
</html>
