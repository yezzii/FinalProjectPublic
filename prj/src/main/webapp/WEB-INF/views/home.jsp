<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="fontawesome/css/all.min.css">
<link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
  />
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<input type="button" value="테스트등록" onclick="location.href='test_insert.do'">
	<input type="button" value="메인" onclick="location.href='main.do'">
	<input type="button" value="플레이Cont" onclick="location.href='play_content.do'">
	<input type="button" value="관리자로그인" onclick="location.href='manager_main.do'">
	
	<!-- 확인용 -->
	<i class="fa fa-arrow-right" aria-hidden="true"></i>
	
	
	<i class="fa-solid fa-arrow-up" style="color: #000000;"></i>
<script src="https://kit.fontawesome.com/f4b42488b3.js" crossorigin="anonymous"></script>
</body>
</html>
