<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/manager/managerpage_footer.css"/>
<script src="https://kit.fontawesome.com/e6bfca85af.js" crossorigin="anonymous"></script>
</head>
<body>

	
	<footer class="footer">
		<div class="footer_wrap">
			<div class="footer_left">
				<img alt="" src="./resources/images/logo_white.png" width="200">
				<div class="menu_div start">
					<ul class="menu_ul">
						<li class="menu_li" onclick="location.href='main.do'">MAIN</li>
						<li class="menu_li" onclick="location.href='play_list.go'">PLAY</li>
						<li class="menu_li" onclick="location.href='play_out_random.do'">RANDOM</li>
						<li class="menu_li" onclick="location.href='community_main.do'">COMMUNITY</li>
						<li class="menu_li" onclick="location.href='qna_main.do'">Q&A</li>
					</ul>
					<p class="copy_text end">
						Copyright © 2023 TODO KHACADEMY Inc. All Rights Reserved
					</p>
				</div>
			</div>
			<div class="footer_right">
				<div class="inner_div mid">
					<strong>Contact Us</strong>
					<div class="inner_content">
						<address>(우)03190 서울 종로구 청계천로 77-1</address>
						<ul class="inner_ul">
							<li class="inner_li">Tel 02-736-6888</li>
							<li class="inner_li">Email todo@todo.co.kr</li>
						</ul>
					</div>
				</div>
				<div class="inner_div mid">
					<strong>Our SNS</strong>
					<div class="inner_content">
						<ul class="inner_ul">
							<li class="inner_li sns_icon"><a href="https://www.instagram.com/">INSTAGRAM</a></li>
							<li class="inner_li sns_icon"><a href="https://www.youtube.com/">YOUTUBE</a></li>
							<li class="inner_li sns_icon"><a href="https://ko-kr.facebook.com/">FACEBOOK</a></li>
						</ul>
					</div>
				</div>
				<div class="inner_div end">
					<strong>Team TODO</strong>
					<div class="inner_content">
						<ul class="inner_ul">
							<li class="inner_li team_info">KO GANGCHAN&nbsp;&nbsp;|&nbsp;&nbsp;bushwick97@naver.com</li>
							<li class="inner_li team_info">KWON YEJI&nbsp;&nbsp;|&nbsp;&nbsp;yezzi771@gmail.com</li>
							<li class="inner_li team_info">KIM SEOYOON&nbsp;&nbsp;|&nbsp;&nbsp;kimseouni@gmail.com</li>
							<li class="inner_li team_info">CHOI JEONGMIN&nbsp;&nbsp;|&nbsp;&nbsp;jeongmin3510@gmail.com</li>
							<li class="inner_li team_info">HONG SEOHWA&nbsp;&nbsp;|&nbsp;&nbsp;tjghk0867@naver.com</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</footer>
	
	<script src="./resources/js/weather.js"></script>
	<script src="./resources/js/ment.js"></script>
	<script src="./resources/js/floatingbtn.js"></script>
	<script src="./resources/js/clock.js"></script>
	<script src="./resources/js/search_modal.js"></script>
	<script src="./resources/js/scroll.js"></script>
</body>
</html>