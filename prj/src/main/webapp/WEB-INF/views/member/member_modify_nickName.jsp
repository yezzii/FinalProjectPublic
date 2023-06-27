<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nick_name" value="${nick_name}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 변경</title>
<style type="text/css">
form {
	margin-top: 20px;
}

.modify-nickname-con {
	padding-top: 180px;
}

input[name="nick_name"], input[name="before_nick_name"] {
	margin-bottom: 10px;
	padding: 5px;
	width: 250px;
}

input[type="submit"], button {
	padding: 10px 20px;
	background-color: #226456;
	color: #fff;
	border: none;
	cursor: pointer;
	width: 263.64px;
}

input[type="submit"]:hover {
	background-color: #1a473e;
}

input:focus {
	outline: none;
}

.before-nickname-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 48.5%;
}

.nickname-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 50%;
	left: 48.5%;
}

#checkNick_name {
	width: 600px;
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 238px;
}

#signup-link {
	font-weight: bold;
	color: #226456;
}

#signup-btn {
	position: absolute;
	top: 100px;
	left: 50%;
	transform: translate(-50%, -50%);
}

.btn-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 52%;
}

.footer {
	margin-top: 500px !important;
}

.success-text {
	color: green;
	position: relative;
	left: 230px;
}

.warning-text {
	color: RED;
	position: relative;
	left: 350px;
}

.curr-temp {
	position: relative !important;
	left: 112px !important;
}
</style>
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class=modify-nickname-con align="center">
		<form method="post"
			action="<%=request.getContextPath()%>/member_modify_nickName_ok.do">
			<div>
				닉네임 변경<br>변경 시 회원 포인트 중 20포인트가 차감됩니다.
			</div>
			<div class="before-nickname-con">
				<span>&nbsp;&nbsp;변경전 닉네임&nbsp;&nbsp;&nbsp;</span> <input
					name="before_nick_name" id="before_nick_name" value="${nick_name}"
					maxlength="16" readonly>
			</div>
			<div class="nickname-con">
				<span>&nbsp;&nbsp;변경할 닉네임&nbsp;&nbsp;&nbsp;</span> <input
					name="nick_name" id="nick_name" maxlength="16" required> <span
					id="checkNick_name"></span>
			</div>
			<br>
			<div class="btn-con">
				<input type="submit" id="signup-btn" value="닉네임 변경">
			</div>
		</form>
	</div>
	<%@include file="../include/footer_light.jsp"%>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		let nickNamePattern = /^[가-힣a-zA-Z0-9_\-]{2,10}$/; // 한글과 영어, 숫자, 특수문자(_,-)만 사용하였는지 검사
		let nickNameChecked = false;

		$(function() {
			// 닉네임 중복 여부 확인
			$('#nick_name')
					.focusout(
							function() {
								$
										.ajax({
											ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
											type : "post",
											url : "member_checkModifyNickName.do",
											data : {
												"nickName" : $("#nick_name")
														.val()
											},
											datatype : "json",
											success : function(data) {
												if (!nickNamePattern.test($(
														"#nick_name").val())
														|| $("#nick_name")
																.val() === "") {
													nickNameChecked = false;
													let warningTxt = '<span class="warning-text">2~10자리의 한글과 영어, 숫자, 특수문자(_,-)만 사용 가능합니다.</span>';
													$("#checkNick_name").html(
															""); // span 태그 영역 초기화
													$("#checkNick_name").show();
													$("#checkNick_name")
															.append(warningTxt);
												} else if ($("#nick_name")
														.val() === $(
														"#before_nick_name")
														.val()) {
													nickNameChecked = false;
													let warningTxt = '<span class="warning-text">이전 닉네임과 동일합니다.</span>';
													$("#checkNick_name").html(
															""); // span 태그 영역 초기화
													$("#checkNick_name").show();
													$("#checkNick_name")
															.append(warningTxt);
												} else {
													if (data === '{"resultChk":"중복"}') {
														// 중복인 경우
														nickNameChecked = false;
														let warningTxt = '<span class="warning-text">중복 닉네임입니다.</span>';
														$("#checkNick_name")
																.html(""); // span 태그 영역 초기화
														$("#checkNick_name")
																.show();
														$("#checkNick_name")
																.append(
																		warningTxt);
													} else {
														nickNameChecked = true;
														let warningTxt = '<span class="success-text">사용 가능한 닉네임입니다.</span>';
														$("#checkNick_name")
																.html(""); // span 태그 영역 초기화
														$("#checkNick_name")
																.show();
														$("#checkNick_name")
																.append(
																		warningTxt);
													}
												}
											},
											error : function(data, xhr, status,
													error) {
												alert("데이터 통신 오류입니다.");
											}
										});
							});
		})

		$("#signup-btn").click(function(event) {
			if (!nickNameChecked) {
				alert("닉네임을 다시 확인해 주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			}
		});
	</script>
</body>
</html>