<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="./resources/css/member/memberAccount.css" />
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="find-id-con" align="center">
		<form method="post"
			action="<%=request.getContextPath()%>/member_findPwd.do">
			<div>
				등록한 이메일로 비밀번호 찾기<br>본인확인 이메일 주소와 입력한 이메일 주소가 같아야, 인증번호를 받을 수
				있습니다.
			</div>

			<div class="id-con">
				<span>아이디&nbsp;&nbsp;</span> <input name="id" id="id"
					placeholder="아이디를 입력해주세요" required>
			</div>
			<div class="name-con">
				<span>&nbsp;&nbsp;이름&nbsp;&nbsp;&nbsp;</span> <input name="name"
					id="name" placeholder="이름을 입력해주세요" required>
			</div>

			<div class="email-con">
				<div>
					<span>이메일&nbsp;&nbsp;</span><input type="email" name="email"
						id="email" placeholder="이메일을 입력해주세요" required><span
						id="checkEmail"></span>
				</div>

			</div>
			<div class="mail-check-box-con">
				<div class="mail-check-box">
					<input class="mail-check-input" placeholder="인증번호 6자리를 입력해주세요!"
						disabled="disabled" maxlength="6">
					<div class="input-group-addon">
						<button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호
							받기</button>
					</div>
				</div>
				<br> <span id="mail-check-warn"></span> <input type="submit"
					id="signup-btn" value="비밀번호 찾기">
			</div>
		</form>
	</div>
	<%@include file="../include/footer_light.jsp"%>
	<script type="text/javascript">
		let emailCodeChecked = false;

		$('#mail-Check-Btn').click(function() {
			if ($("#id").val() === "") {
				alert("아이디를 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if ($("#name").val() === "") {
				alert("이름을 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if ($("#email").val() === "") {
				alert("이메일을 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else {
				const email = $('#email').val(); // 이메일 주소값 얻어오기
				const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 

				$.ajax({
					type : 'get',
					url : "member_mailCheckCode.do?email=" + email, // GET방식이라 Url 뒤에 email 가져오기 가능
					success : function(data) {
						checkInput.attr('disabled', false);
						code = data;
						alert('인증번호가 전송되었습니다.')
					}
				}); // end ajax
			}
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
			if ($("#id").val() === "") {
				alert("아이디를 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if ($("#name").val() === "") {
				alert("이름을 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if ($("#email").val() === "") {
				alert("이메일을 입력해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			} else if (!emailCodeChecked) {
				alert("이메일 인증을 진행해주세요.");
				event.preventDefault(); // 다음 페이지로 이동을 막음
			}
		});
	</script>

</body>
</html>