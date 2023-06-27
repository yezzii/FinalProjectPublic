let pwdPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/; // 영문 대문자와 소문자, 숫자, 특수문자를 하나 이상 포함하여 8~16자가 되는지 검사
let namePattern = /^[가-힣]{2,6}$/; // 2~6글자의 한글만 입력하였는지 검사
let phonePattern = /^010[0-9]{8}$/; // 010 으로 시작하여 그 다음은 0~9로 구성된 8자리인지 검사

let passwordChecked = false;
let nameChecked = true;
let phoneChecked = true;

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
	$('#name').change(function() {
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
	// 연락처 중복 여부 확인
	$('#phone').change(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "member_checkModifyPhone.do",
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
	} else if (!phoneChecked) {
		alert("연락처를 다시 확인해 주세요.");
		event.preventDefault(); // 다음 페이지로 이동을 막음
	}
});
