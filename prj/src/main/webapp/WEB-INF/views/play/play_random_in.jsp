<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Random</title>
<link rel="stylesheet" href="./resources/css/play/playRandom.css" />
<!-- <link rel="stylesheet" href="./resources/css/weather_popup.css" /> -->
</head>
<body>
	<%@include file="../include/header02.jsp"%>
	<c:set var="list" value="${RandomInList }" />
	<c:if test="${not empty IDID}">
		<div class="temp-temp-con">
			<div class="temp-con"
				style="padding: 40px; color: #000; height: 250px">

				<div class="temp-left-wrap">

					<div class="weather_icon"></div>
					<div>
						<div class="current_temp" style="font-size: 50pt"></div>
						<div class="weather_description" style="font-size: 20pt"></div>
						<div class="city" style="font-size: 13pt"></div>
					</div>

					<div class="temp-right-wrap">
						<div class="air-quality1"></div>
						<div class="temp_min"></div>
						<div class="temp_max"></div>
						<div class="humidity"></div>
						<div class="wind"></div>
						<div class="cloud"></div>
					</div>

				</div>
				<div class="temp-right-con">
					<p>날씨에 따른 Random 추천입니다.</p>
				</div>

			</div>


		</div>
		<button class="toggle-button next-rec-btn">RANDOM</button>
		<div class="hidden-content">
			<c:if test="${!empty list }">
				<div class="list_wrap">
					<c:forEach items="${list }" var="dto" varStatus="status">
						<div class="play_list" data-address="${dto.play_address }">
							<a href="play_content.do?play_idx=${dto.play_index }"> <img
								class="play_img" src="./resources/assets/${dto.play_picture}">
							</a> <br> <span class="play_name"> ${dto.play_name }</span> <img
								class="like_heart"
								src="./resources/assets/play_pic/like_heart.png"> <span
								class="play_likefont">&nbsp;${dto.play_like }개</span> &nbsp; <span
								class="play_star">⭐</span><span class="play_starfont">&nbsp;<fmt:formatNumber
									value="${dto.play_rating}" pattern="0.0" /></span> &nbsp; <img
								class="play_view"
								src="./resources/assets/play_pic/play_view.png"> <span
								class="play_viewfont">&nbsp;${dto.play_view }</span> <input
								type="hidden" name="play_group" value="${dto.play_group }">
							<input type="hidden" name="play_address"
								value="${dto.play_address }"> <input type="hidden"
								name="play_price" value="${dto.play_price }">
						</div>

						<c:if test="${status.count % 3 == 0}">
							<br>
							<br>
							<br>
							<br>
						</c:if>

					</c:forEach>
				</div>

			</c:if>
			<button class="next-road-btn" onClick="window.location.reload()">NEXT</button>
		</div>
	</c:if>

	<c:if test="${empty IDID}">
		<div class="temp-temp-con">
			<div class="temp-con"
				style="padding: 40px; color: #000; height: 250px">

				<div class="temp-left-wrap">

					<div class="weather_icon"></div>
					<div>
						<div class="current_temp" style="font-size: 50pt"></div>
						<div class="weather_description" style="font-size: 20pt"></div>
						<div class="city" style="font-size: 13pt"></div>
					</div>

					<div class="temp-right-wrap">
						<div class="air-quality1"></div>
						<div class="temp_min"></div>
						<div class="temp_max"></div>
						<div class="humidity"></div>
						<div class="wind"></div>
						<div class="cloud"></div>
					</div>

				</div>
				<div class="temp-right-con">
					<p>로그인후 이용가능한 서비스 입니다!</p>
				</div>

			</div>


		</div>
		<div class="login-con">
			<a href="./member_main.do">
				<button class="login-go">로그인 하러가기</button>
			</a>
			<div id = "search-btn" class = "toggle-button"></div>
		</div>
	</c:if>

	<%@include file="../include/footer02.jsp"%>
	<script src="./resources/js/weather_text.js"></script>
	<script src="./resources/js/clickView.js"></script>
</body>
</html>
</body>
</html>