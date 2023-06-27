<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="email" value="${email}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 변경</title>
<style type="text/css">
form {
	margin-top: 20px;
}

.modify-email-con {
	padding-top: 180px;
}

input[type="email"], input[name="before_email"] {
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

.before-email-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 48.5%;
}

.email-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 50%;
	left: 48.5%;
}

.mail-check-box {
	margin-top: 5px;
}

.mail-check-input {
	width: 260.64px !important;
	height: 28.18px !important;
	position: absolute;
	transform: translate(-50%, -50%);
	top: 65%;
	left: 48.5%;
}

.mail-check-box-con {
	position: absolute;
	transform: translate(-50%, -50%);
	top: 55%;
	left: 52%;
}

.btn-primary {
	background-color: transparent;
	font-weight: bold;
	color: #226456;
	border: none;
	cursor: pointer;
}

#signup-link {
	font-weight: bold;
	color: #226456;
}

#checkEmail {
	width: 600px;
	position: absolute;
	transform: translate(-50%, -50%);
	top: 40%;
	left: 425px;
}

#mail-check-warn {
	width: 600px;
	position: absolute;
	transform: translate(-50%, -50%);
	top: 45%;
	left: 220px;
}

#mail-Check-Btn {
	position: absolute;
	top: 55px;
	left: 50%;
	transform: translate(-50%, -50%);
}

#signup-btn {
	position: absolute;
	top: 100px;
	left: 50%;
	transform: translate(-50%, -50%);
}

.footer {
	margin-top: 500px !important;
}

.success-text {
	color: green;
	position: relative;
	left: 40px;
}

.warning-text {
	color: RED;
	position: relative;
	left: 40px;
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
	<div class="modify-email-con" align="center">
		<form method="post"
			action="<%=request.getContextPath()%>/member_modify_email_ok.do">
			<div>이메일 변경</div>
			<div class="before-email-con">
				<span>&nbsp;&nbsp;변경전 이메일&nbsp;&nbsp;&nbsp;</span> <input
					name="before_email" id="before_email" value="${email}" readonly>
			</div>
			<div class="email-con">
				<div>
					<span>&nbsp;&nbsp;변경할 이메일&nbsp;&nbsp;&nbsp;</span> <input
						type="email" name="email" id="email" placeholder="이메일을 입력해주세요"
						required> <span id="checkEmail" class="error-message"></span>
				</div>
			</div>
			<div class="mail-check-box-con">
				<div class="mail-check-box">
					<input class="mail-check-input" placeholder="인증번호 6자리를 입력해주세요!"
						disabled="disabled" maxlength="6">
					<div class="input-group-addon">
						<button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호
							받기</button>
						<div id="checkEmail"></div>
					</div>
				</div>
				<br> <span id="mail-check-warn"></span> <input type="submit"
					id="signup-btn" value="이메일 변경">
			</div>
		</form>
	</div>
	<%@include file="../include/footer_light.jsp"%>
	<script type="text/javascript">
		let emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일 형식에 맞게 썻는지 검사 ex)aa01@aa.aa
		let emailChecked = false;
		let emailCodeChecked = false;

		$(function() {
			// 이메일 중복 여부 확인
			$('#email')
					.focusout(
							function() {
								$
										.ajax({
											ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
											type : "post",
											url : "member_checkModifyEmail.do",
											data : {
												"email" : $("#email").val()
											},
											datatype : "json",
											success : function(data) {
												if (!emailPattern.test($(
														"#email").val())
														|| $("#email").val() === "") {
													emailChecked = false;
													let warningTxt = '<font class = "warning-text" color = "red">사용 불가능한 이메일입니다.</span>';
													$("#checkEmail").text(""); // span 태그 영역 초기화
													$("#checkEmail").show();
													$("#checkEmail").append(
															warningTxt);
												} else if ($("#email").val() === $(
														"#before_email").val()) {
													nickNameChecked = false;
													let warningTxt = '<font class = "warning-text" color = "red">이전 이메일과 동일합니다.</span>';
													$("#checkEmail").text(""); // span 태그 영역 초기화
													$("#checkEmail").show();
													$("#checkEmail").append(
															warningTxt);
												} else {
													if (data === '{"resultChk":"중복"}') {
														// 중복인 경우
														emailChecked = false;
														let warningTxt = '<font class = "warning-text" color = "red">중복 이메일입니다.</span>';
														$("#checkEmail").text(
																""); // span 태그 영역 초기화
														$("#checkEmail").show();
														$("#checkEmail")
																.append(
																		warningTxt);
													} else {
														emailChecked = true;
														let warningTxt = '<font class = "success-text" color = "green">사용 가능한 이메일입니다.</span>';
														$("#checkEmail").text(
																""); // span 태그 영역 초기화
														$("#checkEmail").show();
														$("#checkEmail")
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

		$('#mail-Check-Btn').click(function() {
			const email = $('#email').val(); // 이메일 주소값 얻어오기
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			$.ajax({
				type : 'get',
				url : "member_mailCheckCode.do?email=" + email, // GET방식이라 Url 뒤에 email 가져오기 가능
				success : function(data) {
					if (email === "" || email === null) {
						alert('이메일을 확인해 주세요.');
					} else if ($("#email").val() === $("#before_email").val()) {
						alert('변경전 이메일과 동일합니다. 다시 확인해 주세요.');
					} else {
						checkInput.attr('disabled', false);
						code = data;
						alert('인증번호가 전송되었습니다.');
					}
				}
			}); // end ajax
		}); // end send eamil

		// 인증번호 비교 
		// blur -> focus가 벗어나는 경우 발생
		$('.mail-check-input').blur(function() {
			const inputCode = $(this).val();
			const $resultMsg = $('#mail-check-warn');

			if (inputCode === code) {
				emailCodeChecked = true;
				$resultMsg.html('인증번호가 일치합니다.');
				$resultMsg.css('color', 'green');
				$('#mail-Check-Btn').attr('disabled', true);
				$('#email').attr('readonly', true);
			} else {
				emailCodeChecked = false;
				$resultMsg.html('인증번호가 불일치 합니다.');
				$resultMsg.css('color', 'red');
			}
		});

		$("#signup-btn").click(function(event) {
			if (!emailChecked) {
				alert("이메일을 다시 확인해 주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if (!emailCodeChecked) {
				alert("이메일 인증을 진행해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			}
		});
	</script>
</body>
</html>