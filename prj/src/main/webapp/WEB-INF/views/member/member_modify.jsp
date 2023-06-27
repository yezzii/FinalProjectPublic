<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dto" value="${dto}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.nick_name} 회원 정보 수정</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src = "http://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="./resources/css/member/memberModify.css" />
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
</head>
<body>
	<%@include file="../include/headerModify.jsp"%>
	<div class="container">
		<form method="post" class="form-con" action = "<%=request.getContextPath() %>/member_modify_ok.do">
			<div class="form-wrap">
				<div class="left-right-id-con">
					<div class="id-left-con">
					<h3 align="left">${dto.nick_name}회원 정보 수정 페이지</h3>
					<div class="form-width">아이디</div>
					<input  class="input-width" name = "id" value = "${dto.id}" disabled>
					<div id="checkId"></div>
					
					<div>비밀번호</div>
					<input class="input-width" type = "password" name = "password" id = "password" minlength = "4" maxlength = "20" value = "${dto.password}" required>
					<div id="pwd"></div>
					
					<div>비밀번호 확인</div>
		            <input class="input-width" type="password" name="password_check" id="passwordCheck" minlength = "4" maxlength = "20" required>
		            <div id="checkPwd"></div>
		            
		       		<div>이름</div>
					<input class="input-width" name = "name" value = "${dto.name}" required>
					<div id = "checkName"></div>

					<div>닉네임</div>
					<input class="input-width" name = "nick_name" id = "nick_name" value = "${dto.nick_name}"  maxlength = "16" disabled>
					<a href="<%=request.getContextPath()%>/member_modify_nickName.do?nick_name=${dto.nick_name}"><input type = "button" id="nick_name_change" value = "변경"></a>
					</div>
				</div>
					
				<div class="id-right-con">
					<br> <br>
					<div>이메일</div>
					<input class="input-width" type = "email" name = "email" id = "email" value = "${dto.email}" disabled>
					<a href="<%=request.getContextPath()%>/member_modify_email.do?email=${dto.email}"><input type = "button" id="email_change" value = "수정"></a>
					
					
					<div class="text_phone">연락처(숫자만 입력해주세요.)</div>
					<input class="input-width" name = "phone" id = "phone" value = "${dto.phone}" maxlength = "11" required>
					<div id="checkPhone"></div>

					<div>주소(선택사항)</div>
						<input class="input-width input-addr" name="post" id="sample4_postcode"
							maxlength="5" placeholder="우편번호" value = "${dto.post}" > 
						<input type="button"
							class="btn0 btn-primary" onclick="sample4_execDaumPostcode()"
							value="우편번호 찾기"> <br> <input class="input-width input-addr"
							name="road_address" id="sample4_roadAddress" placeholder="도로명주소" value = "${dto.road_address}">
						<br> <input class="input-width" name="jibun_address"
							id="sample4_jibunAddress" placeholder="지번주소" value = "${dto.jibun_address}">
						<div id="guide" style="color: #999;"></div>
						<br> <input class="input-width input-addr" name="detail_address"
							id="sample4_detailAddress" placeholder="상세주소" value = "${dto.detail_address}"> <br>
						<input class="input-width input-addr" name="dong_address"
							id="sample4_extraAddress" placeholder="동주소" value = "${dto.dong_address}">
				</div>

				<br>
				<br>
			</div>
				<div class="button-container">
					<input class ="btn" type="submit"
						id="signup-btn" value="회원수정">
				</div>
				
				<br>
		</form>
	</div>
	<%@include file="../include/footer_light.jsp"%>
	<script src="./resources/js/member.js"></script>
	<script src="./resources/js/member_modify.js"></script>
</body>
</html>