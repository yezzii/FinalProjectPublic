<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "kakao_id" value = "${kakao_id}" />
<c:set var = "kakao_nick_name" value = "${nick_name}" />
<c:set var = "kakao_email" value = "${email}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KAKAO 회원가입</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src = "http://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="./resources/css/member/member_insert.css" />
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="container">
		<form method="post" class="form-con" action="<%=request.getContextPath() %>/member_kakaoInsert_ok.do">
		<div class="form-wrap">
		<input type="hidden" name="kakao_id" value="${kakao_id}">
		<div class="left-right-id-con">
			<div class="id-left-con">
    	<h3 align="left">회원가입</h3>
        <div class="form-width">아이디</div>
        <input class="input-width" name="id" id = "id" minlength = "5" maxlength = "20" placeholder="영문 대소문자, 숫자  5~20자" value = "${kakao_id}" readonly>
        <div id="checkId"></div>
		<div>비밀번호</div>
        <input class="input-width" type="password" name="password" id = "password" minlength = "8" maxlength = "16" placeholder="영문 대소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자" required>
        <div id="pwd"></div>

        <div>비밀번호 확인</div>
        <input class="input-width" type="password" name="password_check" id="passwordCheck" minlength = "8" maxlength = "16" required>
        <div id="checkPwd"></div>

        <div>이름</div>
        <input class="input-width" name="name" id = "name" minlength = "2" maxlength = "6"  placeholder="2~6글자의 한글" required>
        <div id="checkName"></div>

        <div>닉네임</div>
        <input class="input-width" name="nick_name" id = "nick_name" placeholder="한글과 영어, 숫자만 사용 2~10글자" minlength = "2" maxlength = "10" value = "${kakao_nick_name}" required>
        <div id="checkNick_name"></div>
        </div>
        <div class="id-right-con">
            <br><br>
			<div>이메일</div>
            <input class="input-width" type = "email" name="email" id = "email" placeholder="ex)aa01@aa.aa" value = "${kakao_email}" required>
            <button type="button" class="btn0 btn-primary" id="mail-Check-Btn">인증번호
							받기</button>
             <div id="checkEmail"></div>
			<div class="mail-check-box">
							<input class="mail-check-input input-width"
								placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled"
								maxlength="6">
						</div>
						<p id="mail-check-warn"></p>
						
            <div>연락처(숫자만 입력해주세요.)</div>
            <input class="input-width" name="phone" id = "phone" placeholder="ex.) 01012341234" maxlength = "11" required>
            <div id="checkPhone"></div>

           <div>주소(선택사항)</div>
            <input class="input-width input-addr" name="post" id="sample4_postcode"
					maxlength="5" placeholder="우편번호"> <input type="button"
					class="btn0 btn-primary" onclick="sample4_execDaumPostcode()"
					value="우편번호 찾기"> <br> <input class="input-width input-addr"
					name="road_address" id="sample4_roadAddress" placeholder="도로명주소">
				<br> <input class="input-width" name="jibun_address"
					id="sample4_jibunAddress" placeholder="지번주소">
				<div id="guide" style="color: #999;"></div>
				<br> <input class="input-width input-addr" name="detail_address"
					id="sample4_detailAddress" placeholder="상세주소"> <br>
				<input class="input-width input-addr" name="dong_address"
					id="sample4_extraAddress" placeholder="동주소">
			</div>
			</div>
          	<br>
				<div class="button-container">
					<input class ="btn" type="reset" value="다시작성"> <input class ="btn" type="submit"
						id="signup-btn" value="회원가입">
				</div>
				<br>
				</div>
        </form>
	</div>
	<%@include file="../include/footer_light.jsp"%>
	<script src="./resources/js/member.js"></script>
	<script src="./resources/js/member_kakaoLogin.js"></script>
</body>
</html>