<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Random</title>
<link rel="stylesheet" href="./resources/css/play/playRandom.css" />
<link rel="stylesheet" href="./resources/css/weather_popup.css" />
</head>
<body>
	<%@include file="../include/header02.jsp"%>
	<div class="container">
		<input type="button" value="out"  class ="outSide-btn" onclick="location.href='play_out_random.do'">
		<input type="button" value="in"class ="inSide-btn" onclick="location.href='play_in_random.do'">
	</div>
	<%@include file="../include/footer02.jsp"%>
	<script src="./resources/js/random_weather.js"></script>
</body>
</html>