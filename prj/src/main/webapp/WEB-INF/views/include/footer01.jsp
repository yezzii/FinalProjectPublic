<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
</head>
<body>
	<div class="pop-up-wrap">
		<button id="popup-btn">
			<span class="clock">00:00:00</span>
			<p class="temp curr-temp"></p>
		</button>
	</div>

	<div id="popup-overlay"></div>
	<div id="popup">
		<button id="close-btn">x</button>
		<span class="popup-clock">00:00:00</span>&nbsp;<span>/</span>&nbsp;<span
			class="popup-temp"></span><br> <span class="precipitation"></span><br>
		<span class="air-quality"></span><span class="air-quality-ment">(안녕)</span>
		<p class="ment">지금은 집에서 즐겨봐요.</p>

	</div>

	<footer class="footer">
		<div class="footer_wrap">
			<div class="footer_box1">
				<p>제작 : 최정민</p>
				<address>E-Mail : vkfks1693@gmail.com</address>
				<p>Copyright &copy; All right reserved</p>
			</div>
			<div class="footer_box2">
				<p class="start">Play</p>
				<p class="mid1">Random</p>
				<p>Community</p>
				<p class="end">Q&A</p>
			</div>
			<div class="footer_box3">
				<p>이용약관</p>
				<p>개인정보처리방침</p>
			</div>
			<div class="footer_box4">
				<br> <i class="fa-brands fa-instagram fa-xl"></i>&nbsp; <i
					class="fa-brands fa-twitter fa-xl"></i> &nbsp;<i
					class="fa-brands fa-facebook-f fa-xl"></i>&nbsp; &nbsp;<i
					class="fa-solid fa-rss fa-xl"></i>
			</div>
		</div>
	</footer>
	<script src="./resources/js/weather.js"></script>
	<script src="./resources/js/ment.js"></script>
	<script src="./resources/js/floatingbtn.js"></script>
	<script src="./resources/js/clock.js"></script>
	<script src="./resources/js/search_modal.js"></script>
	<script src="./resources/js/scroll1.js"></script>
</body>
</html>