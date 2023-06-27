/* faq 더보기, 접기 */
function faqShow(self) {
    var faqIcon = $(self).find('.faq_icon');
    if ($(self).next().css("display") == "none") {
        $(self).next().show();
        faqIcon.attr("class", "faq_icon fa-solid fa-chevron-up");
    } else {
        $(self).next().hide();
        faqIcon.attr("class", "faq_icon fa-solid fa-chevron-down");
    }
}
/* 댓글 삭제 여부 확인 */
function qnaDelete(no) {
	if(confirm('문의글을 삭제하시겠습니까?')) {
		location.href='qna_delete.do?no='+no;
	} else {
		alert('문의글 삭제가 취소되었습니다.');
	}
}

// 파일 선택
$("#file_input_hidden").change(function() {
	var filename = $("#fileName");
	if(this.files[0] == undefined){
	   filename.innerText = '선택된 파일없음';
	   return;
	}
	filename.innerText = this.files[0].name;
 });
 
// 파일 미리보기
function readURL(input) {
  const preview = document.getElementById("preview");
  const filename = document.getElementById("fileName");

  if (input.files && input.files[0]) {
    const reader = new FileReader();

    reader.onload = function(event) {
      preview.style.display = "block";
      preview.src = event.target.result;
      filename.textContent = input.files[0].name;
    };

    reader.readAsDataURL(input.files[0]);
  } else {
    preview.style.display = "none";
    preview.src = "";
    filename.textContent = "";
  }
}

document.addEventListener("DOMContentLoaded", function() {
  const fileInput = document.getElementById("file_input_hidden");
  fileInput.onchange = function() {
    readURL(this);
  };
});

// 문의글 및 답글 작성, 수정 유효성 검사
function qnaFormCheck() {
	if($('#qna_title').val() == "") {
		alert("제목을 입력해주세요.");
		return false;
	} 
	if($('#qna_writer').val() == "") {
		alert("닉네임을 입력해주세요.");
		return false;
	} 
	if($('#qna_password').val() == "") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} 
	if($.trim($('#qna_password').val()).length < 4) {
		alert("최소 4자리 이상의 비밀번호를 입력해주세요.");
		return false;
	} 
	if($.trim($('#qna_content').val()).length < 10) {
		alert("문의 내용을 10자 이상 입력해주세요.");
		return false;
	}
	if($('.qna_head').val() == "사용자 신고") {
		if($('#searchBox').val() == "") {
			alert("신고할 사용자의 닉네임을 입력해주세요.");
			return false;
		} 
	}
}

function checkNick(){
	
	$("#qna_writer_check").hide(); // span 태그 영역 숨기기
	let qna_writer = $("#qna_writer").val(); // id 값 가져오기
	
	/* 닉네임 입력 길이 체크  */
	if($.trim(qna_writer).length < 3 || $.trim(qna_writer).length > 10) {
		$("#qna_writer_check").text(""); // span 태그 영역 초기화
		$("#qna_writer_check").show();
		$("#qna_writer_check").append('<font color="red">※ 3 ~ 10 자리 이내의 닉네임을 입력해주세요.</font>');
		$("#qna_writer").val('').focus();
		return false;
	}
	
	$.ajax({
		ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
		type: "post",
		url: "member_checkInsertNickName.do",
		data: {"nickName": $("#qna_writer").val()},
		datatype: "json",
		success: function(data) {
			if (data === '{"resultChk":"중복"}') {
				// 중복인 경우
				nickNameChecked = false;
				let warningTxt = '<font color="red">중복 닉네임입니다.</span>';
				$("#qna_writer_check").text(""); // span 태그 영역 초기화
				$("#qna_writer_check").show();
				$("#qna_writer_check").append(warningTxt);
			} else {        
				nickNameChecked = true;
				let warningTxt = '<font color="green">사용 가능한 닉네임입니다.</span>';
				$("#qna_writer_check").text(""); // span 태그 영역 초기화
				$("#qna_writer_check").show();
				$("#qna_writer_check").append(warningTxt);
			}
		},
		error : function(data, xhr, status, error) {
			alert("데이터 통신 오류입니다.");
		}
	});
}

function showSearchUser(e) {
	if($(e).val()=='사용자 신고'){
		$(".searchUserWrap").show();
	}else{
		$(".searchUserWrap").hide();
	}
}

function searchUser() {
  $("#searchBox").autocomplete({
    source: function(request, response) {
      $.ajax({
        type: "get",
        url: "qna_userSearch.do",
        data: { keyword: request.term },
        dataType: "json",
        success: function(list) {
          response(list.map(function(item) {
            return item.nick_name;
          }));
        },
        error: function() {
          alert("시스템 오류");
        }
      });
    },
    minLength: 1
  });
}

 // qna 카테고리 리스트 조회
 function qnaCategory(no) {
	$.ajax({
		type: "get",
		url : "qna_categoryList.do",
		data : {
				 faq_category: no,
				},
		dataType: "json",
		success : function(list){
					var text = "";
					if(list.length>0){
						for(var i=0; i<list.length; i++) {
							text += "<div class='faq_wrap'><div class='faq_title' onclick='faqShow(this)'><div>";
							text += "<span class='faq_category_title'>" + list[i].faq_category_name + "</span>";
							text += "<span class='faq_content_title'>" + list[i].faq_title + "</span>";
							text += "</div><i class='faq_icon fa-solid fa-chevron-down'></i></div><div class='faq_content'><div class='faq_content_detail'>" + list[i].faq_content + "</div></div></div>";
						}
					}
					$("#qna_list").html(text);
		},
		error : function(){
		   alert("시스템 오류");
		}
	});
 }