let pwdPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/; // 영문 대문자와 소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자가 되는지 검사
let namePattern = /^[가-힣]{2,6}$/; // 2~6글자의 한글만 입력하였는지 검사
let nickNamePattern = /^[가-힣a-zA-Z0-9_\-]{2,10}$/; // 한글과 영어, 숫자, 특수문자(_,-)만 사용하였는지 검사
let emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; // 이메일 형식에 맞게 썻는지 검사 ex)aa01@aa.aa
let phonePattern = /^010[0-9]{8}$/; // 010 으로 시작하여 그 다음은 0~9로 구성된 8자리인지 검사

let passwordChecked = false;
let nameChecked = false;
let nickNameChecked = false;
let emailChecked = false;
let emailCodeChecked = false;
let phoneChecked = false;

$(function() {
	// 비밀번호 일치 여부 확인

	$('#password').keyup(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkPwd.do",
			data: {"password": $("#password").val(), "password2": $("#passwordCheck").val()},
			datatype: "json",
			success: function(data) {
				if(!pwdPattern.test($("#password").val()) || $("#password").val() === "") {
					pwdChecked = false;
					let warningTxt = '<font color = "red">8~16자리의 영문 대, 소문자, 숫자, 특수문자를 하나 이상 포함하여 사용 가능합니다.</span>';
					$("#checkPwd").text(""); // span 태그 영역 초기화
					$("#checkPwd").show();
					$("#checkPwd").append(warningTxt);
				} else {
					if (data === '{"resultChk":"사용불가능"}') {
						// 일치하지 않는 경우
						passwordChecked = false;
						let warningTxt = '<font color = "red">비밀번호가 일치하지 않습니다.</span>';
						$("#checkPwd").text(""); // span 태그 영역 초기화
						$("#checkPwd").show();
						$("#checkPwd").append(warningTxt);
					} else {
						// 일치하는 경우
						passwordChecked = true;
						let warningTxt = '<font color = "green">비밀번호가 일치합니다.</span>';
						$("#checkPwd").text(""); // span 태그 영역 초기화
						$("#checkPwd").show();
						$("#checkPwd").append(warningTxt);
					}
				}
			},
			error : function(data, xhr, status, error) {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
	
	$('#passwordCheck').keyup(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkPwd.do",
			data: {"password": $("#password").val(), "password2": $("#passwordCheck").val()},
			datatype: "json",
			success: function(data) {
				if(!pwdPattern.test($("#passwordCheck").val()) || $("#passwordCheck").val() === "") {
					pwdChecked = false;
					let warningTxt = '<font color = "red">비밀번호를 다시 확인해 주세요.</span>';
					$("#checkPwd").text(""); // span 태그 영역 초기화
					$("#checkPwd").show();
					$("#checkPwd").append(warningTxt);
				} else {
					if (data === '{"resultChk":"사용불가능"}') {
						// 일치하지 않는 경우
						passwordChecked = false;
						let warningTxt = '<font color = "red">비밀번호가 일치하지 않습니다.</span>';
						$("#checkPwd").text(""); // span 태그 영역 초기화
						$("#checkPwd").show();
						$("#checkPwd").append(warningTxt);
					} else {
						// 일치하는 경우
						passwordChecked = true;
						let warningTxt = '<font color = "green">비밀번호가 일치합니다.</span>';
						$("#checkPwd").text(""); // span 태그 영역 초기화
						$("#checkPwd").show();
						$("#checkPwd").append(warningTxt);
					}
				}
			},
			error : function(data, xhr, status, error) {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
})

$(function() {
	// 이름 정규표현식 확인
	$('#name').focusout(function() {
		if(!namePattern.test($("#name").val()) || $("#name").val() === "") {
			nameChecked = false;
			let warningTxt = '<font color = "red">2~6자리의 한글만 사용 가능합니다.</span>';
			$("#checkName").text(""); // span 태그 영역 초기화
			$("#checkName").show();
			$("#checkName").append(warningTxt);
		} else {
			nameChecked = true;
			let warningTxt = '<font color = "green">사용 가능한 이름입니다.</span>';
			$("#checkName").text(""); // span 태그 영역 초기화
			$("#checkName").show();
			$("#checkName").append(warningTxt);
		}
	});
})

$(function() {
	// 닉네임 중복 여부 확인
	$('#nick_name').focusout(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkInsertNickName.do",
			data: {"nickName": $("#nick_name").val()},
			datatype: "json",
			success: function(data) {
				if(!nickNamePattern.test($("#nick_name").val()) || $("#nick_name").val() === "") {
					nickNameChecked = false;
					let warningTxt = '<font color = "red">2~10자리의 한글과 영어, 숫자, 특수문자(_,-)만 사용 가능합니다.</span>';
					$("#checkNick_name").text(""); // span 태그 영역 초기화
					$("#checkNick_name").show();
					$("#checkNick_name").append(warningTxt);
				} else {
					if (data === '{"resultChk":"중복"}') {
						// 중복인 경우
						nickNameChecked = false;
						let warningTxt = '<font color = "red">중복 닉네임입니다.</span>';
						$("#checkNick_name").text(""); // span 태그 영역 초기화
						$("#checkNick_name").show();
						$("#checkNick_name").append(warningTxt);
					} else {        
						nickNameChecked = true;
						let warningTxt = '<font color = "green">사용 가능한 닉네임입니다.</span>';
						$("#checkNick_name").text(""); // span 태그 영역 초기화
						$("#checkNick_name").show();
						$("#checkNick_name").append(warningTxt);
					}
				}
			},
			error : function(data, xhr, status, error) {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
})

$(function() {
	// 이메일 중복 여부 확인
	$('#email').focusout(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkInsertEmail.do",
			data: {"email": $("#email").val()},
			datatype: "json",
			success: function(data) {
				if(!emailPattern.test($("#email").val()) || $("#email").val() === "") {
					emailChecked = false;
					let warningTxt = '<font color = "red">이메일 형식에 맞게 썻는지 검사해 주세요. ex)aa01@aa.aa</span>';
					$("#checkEmail").text(""); // span 태그 영역 초기화
					$("#checkEmail").show();
					$("#checkEmail").append(warningTxt);
				} else {
					if (data === '{"resultChk":"중복"}') {
						// 중복인 경우
						emailChecked = false;
						let warningTxt = '<font color = "red">중복 이메일입니다.</span>';
						$("#checkEmail").text(""); // span 태그 영역 초기화
						$("#checkEmail").show();
						$("#checkEmail").append(warningTxt);
					} else {        
						emailChecked = true;
						let warningTxt = '<font color = "green">사용 가능한 이메일입니다.</span>';
						$("#checkEmail").text(""); // span 태그 영역 초기화
						$("#checkEmail").show();
						$("#checkEmail").append(warningTxt);
					}
				}
			},
			error : function(data, xhr, status, error) {
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
		success : function (data) {
			if(email === "" || email === null) {
				alert('이메일을 확인해 주세요.');
			} else {
				checkInput.attr('disabled',false);
				code =data;
				alert('인증번호가 전송되었습니다.');
			}	
		}
	}); // end ajax
}); // end send eamil

// 인증번호 비교 
// blur -> focus가 벗어나는 경우 발생
$('.mail-check-input').blur(function () {
	const inputCode = $(this).val();
	const $resultMsg = $('#mail-check-warn');
	
	if(inputCode === code){
		emailCodeChecked = true;
		$resultMsg.html('인증번호가 일치합니다.');
		$resultMsg.css('color','green');
		$('#mail-Check-Btn').attr('disabled',true);
		$('#email').attr('readonly',true);
	}else{
		emailCodeChecked = false;
		$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요.');
		$resultMsg.css('color','red');
	}
});

$(function() {
	// 연락처 중복 여부 확인
	$('#phone').focusout(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkInsertPhone.do",
			data: {"phone": $("#phone").val()},
			datatype: "json",
			success: function(data) {
				if(!phonePattern.test($("#phone").val()) || $("#phone").val() === "") {
					phoneChecked = false;
					let warningTxt = '<font color = "red"> - 를 제외하며 010으로 시작하여 0~9로 구성된 8자리인지 검사해 주세요</span>';
					$("#checkPhone").text(""); // span 태그 영역 초기화
					$("#checkPhone").show();
					$("#checkPhone").append(warningTxt);
				} else {
					if (data === '{"resultChk":"중복"}') {
						// 중복인 경우
						phoneChecked = false;
						let warningTxt = '<font color = "red">중복된 연락처입니다.</span>';
						$("#checkPhone").text(""); // span 태그 영역 초기화
						$("#checkPhone").show();
						$("#checkPhone").append(warningTxt);
					} else {  
						phoneChecked = true;      
						let warningTxt = '<font color = "green">사용 가능한 연락처입니다.</span>';
						$("#checkPhone").text(""); // span 태그 영역 초기화
						$("#checkPhone").show();
						$("#checkPhone").append(warningTxt);
					}
				}
			},
			error : function(data, xhr, status, error) {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
})


$("#signup-btn").click(function(event) {
	if (!passwordChecked) {
		alert("비밀번호를 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	} else if (!nameChecked) {
		alert("이름을 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	} else if (!nickNameChecked) {
		alert("닉네임을 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	} else if (!emailChecked) {
		alert("이메일을 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	} else if (!phoneChecked) {
		alert("연락처를 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	} else if (!emailCodeChecked) {
		alert("이메일 인증을 진행해주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	}
});
