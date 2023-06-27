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
 
 // 게시글 작성, 수정 유효성 검사
 function writeFormCheck() {
	if($('#community_title').val() == "") {
	   alert("제목을 입력해주세요.");
	   return false;
	} 
	if($('#community_content').val() == "") {
	   alert("내용을 입력해주세요.");
	   return false;
	} 
	if($.trim($('#community_content').val()).length < 20) {
	   alert("내용을 20자 이상 입력해주세요.");
	   return false;
	}
 }
 
 // 게시글 삭제 유효성 검사
 function deleteFormCheck() {
	if($('#member_pwd').val() == "") {
	   alert("비밀번호를 입력해주세요.");
	   return false;
	} 
 }
 
 /* 관리자 게시글 삭제 여부 확인 */
 function communityDelete(no, page) {
	 if(confirm('[관리자]해당 게시글을 삭제하시겠습니까?')) {
		 location.href='community_delete_ok_manager.do?no='+no+'&page='+page;
		 alert('[관리자]게시글이 삭제되었습니다.');
		 location.replace("community_main.do");
	 } else {
		 alert('[관리자]게시글 삭제가 취소되었습니다.');
	 }
 }
 
 // 더블클릭 방지
 var isRun = false;
 
 // 좋아요
 function likeInsert(self, no){
	if(isRun == true) return;
	isRun = true;
	 $.ajax({
		 type: "post",
		 url : "community_likeInsert.do",
		 data : {community_num: no},
		 success : function(data){
			if(data>=0) {
				$(self).attr('src', './resources/assets/play_pic/like_heart_fill.png');
				$(self).attr('onclick', 'likeDelete(this, '+no+')');
				$("#likeCount").text(data);
				isRun = false;
			 } else {
				alert("로그인이 필요합니다.");
				location.replace("member_main.do");
			 }
		 },
		 error : function(){
			 alert("시스템 오류");
		 }
	 });
 }
 
 // 안좋아요
 function likeDelete(self, no){
	if(isRun == true) return;
	isRun = true;
	 $.ajax({
		 type: "post",
		 url : "community_likeDelete.do",
		 data : {community_num: no},
		 success : function(data){
			if(data>=0) {
				$(self).attr('src', './resources/assets/play_pic/like_heart.png');
				$(self).attr('onclick', 'likeInsert(this, '+no+')');
				$("#likeCount").text(data);
				isRun = false;
			} else {
				alert("로그인이 필요합니다.");
				location.replace("member_main.do");
			 }
		 },
		 error : function(){
			 alert("시스템 오류");   
		 }
	 });
 }
 
 // 좋아요 누른 회원 조회
 function likeMember(no) {

	if(isRun == true) return;
	isRun = true;

	if(loginNick == "") {
		loginNick = "관리자";
	}

	$.ajax({
		type: "get",
		url : "community_likedMember.do",
		data : {
				 community_num: no,
				},
		 dataType: "json",
		success : function(list){
			 var text = "";
			 
			 $("#likeMember").attr("onclick","likeMemberRemove()");
			 if(list.length>0){
			 text += "<div class='likeMembersWrap'>";
			 for(var i=0; i<list.length; i++) {
				text += "<div class='likeMembersContent'>";
				
				if(loginNick == "관리자") {
					text += "<a href='manager_look_user_info_view.do?user_nick_name="+ list[i].community_like_id +"'>" + list[i].community_like_id + "</a>";
				} else {
					text += "<a href='member_profile_view.do?user_nick_name="+ list[i].community_like_id +"'>" + list[i].community_like_id + "</a>";
				}

				text += "<div class='likeMembersDate'>" + list[i].community_like_date + "</div></div>";
			 }
		  } else {
			 text += "<div>아직 좋아요를 누른 회원이 없습니다. 가장 먼저 좋아요를 눌러보세요!</div>";
		  }
		  text += "</div>";
		  $("#likeMemberList").html(text);
		  isRun = false;
		},
		error : function(){
		   alert("시스템 오류");
		}
	});
 }
 
 function likeMemberRemove() {
	$("#likeMember").attr("onclick","likeMember(community_num)");
	$(".likeMembersWrap").remove();
 }
 
 function nullCheck(str) {
	var newStr = str;
	if(newStr == null || newStr == "" || newStr == undefined || newStr == "undefined") {
	   newStr = "";
	}
	return newStr;
 }
 
// 댓글 작성
function replyInsert(self, cno, rno){
 	
	var replyCnt = 0; // 댓글 개수 실시간 반영을 위한 변수 선언
 
	if(isRun == true) return;
	isRun = true;
	
	var str_reply_content = nullCheck($(".replyWrite .reply_content").val()); // 댓글 작성 내용 가져오기
	var str_re_reply_content = nullCheck($(".re_replyWrite .reply_content").val()); // 대댓글 작성 내용 가져오기
	var reply_writer = nullCheck($(".replyWrite .reply_writer").text()); // 댓글 작성자 닉네임 가져오기
	var re_reply_writer = nullCheck($(".re_replyWrite .reply_writer").val()); // 대댓글 작성자 닉네임 가져오기
	var reply_manager = nullCheck($("#reply_manager").val()); // 관리자 id 가져오기
	var community_writer = $("#community_writer").val();
	
	$.ajax({
		type: "get",
		url : "community_replyInsert.do",
		data : {
				 community_num: cno,
				 reply_num: rno,
				 reply_content: str_reply_content,
				 re_reply_content: str_re_reply_content,
				 reply_writer: reply_writer,
				 re_reply_writer: re_reply_writer,
				 reply_manager: reply_manager
				},
		 dataType: "json",
		success : function(list){
			 $(".reply_content").val("");
			 console.log("댓글 등록 성공");
			 var text = "";
			
			 // 댓글 리스트 가져오기
			 for(var i=0; i<list.length; i++) {
 
				 if(list[i].reply_depth == 0) {
					 text += "<div class='replyDepth_0'>"; // 댓글인 경우
				 } else {
					 text += "<div class='replyDepth_1'>"; // 대댓글인 경우
				 }

				 var reply_writer = nullCheck(list[i].reply_writer); // 댓글 작성자 가져오기 -> null이면 ""로 넣기

				 if(reply_writer == "") { // reply_writer가 ""이면 관리자가 로그인했다는 뜻
					reply_writer = "관리자"; // jsp 내에 관리자 id가 아닌 "관리자" 로 통합하여 보여주기 위한 설정
				 }
 
				 text += "<div id='replyNum_"+list[i].reply_num+"'>";
				 text += "<div class='reply_writer_wrap'><div class='reply_writer'>" + reply_writer + "</div>";
				
				 if(community_writer == reply_writer) {
				 	if(reply_writer == "관리자") {
				 		text += "<span class=\"manager_tag\">관리자</span>";
				 	} else {
				 		text += "<span class=\"writer_tag\">작성자</span>";
				 	}
				}
				
				 text += "</div>";
				 text += "<div class='reply_content'>" + list[i].reply_content + "</div>";
				
				 if(list[i].reply_update == '1000-01-01') { // 수정 여부 알아보기: reply_update == '1000-01-01'이면 수정한 적 없는 댓글
					 var date = "<span class='reply_regdate'>"+list[i].reply_regdate+"</span>";
				 } else {
					 var date = "<span class='reply_update'>"+list[i].reply_update+"</span>";
				 }

				 text += "<div class='reply_date_button'>" + date; // date와 button을 묶은 div
				 text += "<input type='button' value='수정' onclick='replyModifyHTML(" + list[i].reply_num + ", " + list[i].reply_community_num + ", \"" + reply_writer + "\", \"" + list[i].reply_content + "\", \"" + list[i].reply_regdate + "\", \"" + list[i].reply_update + "\")'>";
				 text += "<input type='button' value='삭제' onclick='replyDelete("+list[i].reply_num+", "+list[i].reply_community_num+")'>";
				 text += "<input type='button' value='답글쓰기' onclick='reReplyShow("+list[i].reply_num+", "+loginNick+", "+list[i].reply_community_num+")'>";
				 text += "</div></div></div>";
 
			 if(list[i].reply_community_num == cno){ // 해당 댓글의 reply_community_num이 해당 게시글 번호와 같다면 댓글 개수 + 1
				replyCnt += 1;
			 }
		  }
		  $("#replyList").val("");
		  $("#replyList").html(text);
		  $("#replyCountNum").text(replyCnt); // 상단 댓글 개수에 넣기
		  isRun = false;
		},
		error : function(){
		   alert("시스템 오류");
		}
	});
 }
 
 // 댓글 수정 폼 변환
 function replyModifyHTML(rno, cno, writer, content, regdate, update){
 
	if(update == '1000-01-01') {
	   var date = regdate;
	} else {
	   var date = update;
	}
 
	var text = "";
	text += "<div class='reply_modifyWrap'><div class='reply_writer'>" + writer + "</div>";
	text += "<textarea class='reply_content'>" + content + "</textarea><br>";
	text += "<span class='reply_regdate replyModifyDate'>" + date + "</span>"; 
	text += "<input type='button' class='replyModifyButton' value='저장' onclick='replyModify("+rno+","+cno+")'>";
	text += "<input type='reset' class='replyModifyButton' value='취소' onclick='reload()'></div>";
	$('#replyNum_' + rno).html(text);
	$('#replyNum_' + rno + ' .reply_content').focus();
 }
 
 function reload() {
 location.reload();
 }

  // 댓글 수정
  function replyModify(rno, cno){
	if(isRun == true) return;
	isRun = true;
	$.ajax({
		type: "post",
		url : "community_replyModify.do",
		data : {
			 	 community_num: cno,
				 reply_num: rno,
				 reply_content: $('#replyNum_' + rno + ' .reply_content').val(),
				},
		 dataType: "json",
		success : function(list){
			 var text = "";
			 for(var i=0; i<list.length; i++) {
			 
				 if(list[i].reply_depth == 0) {
					 text += "<div class='replyDepth_0'>";
				 } else {
					 text += "<div class='replyDepth_1'>";
				 }
				 if(list[i].reply_update == '1000-01-01') {
					 var date = "<span class='reply_regdate'>"+list[i].reply_regdate+"</span>";
				 } else {
					 var date = "<span class='reply_update'>"+list[i].reply_update+"</span>";
				 }

				 var reply_writer = nullCheck(list[i].reply_writer);

				 if(reply_writer == "") {
					reply_writer = "관리자";
				 }

				 text += "<div id='replyNum_"+list[i].reply_num+"'>";
				 text += "<div class='reply_writer_wrap'><div class='reply_writer'>" + reply_writer + "</div></div>";
				 text += "<div class='reply_content'>" + list[i].reply_content + "</div>";
				 text += "<div class='reply_date_button'>" + date;
				 text += "<input type='button' value='수정' onclick='replyModifyHTML(" + list[i].reply_num + ", " + list[i].reply_community_num + ", \"" + reply_writer + "\", \"" + list[i].reply_content + "\", \"" + list[i].reply_regdate + "\", \"" + list[i].reply_update + "\")'>";
				 text += "<input type='button' value='삭제' onclick='replyDelete("+list[i].reply_num+", "+list[i].reply_community_num+")'>";
				 text += "<input type='button' value='답글쓰기' onclick='reReplyShow("+list[i].reply_num+","+loginNick+","+list[i].reply_community_num+")'>";
				 text += "</div></div></div>";
		  }
		  $("#replyList").val("");
		  $("#replyList").html(text);
		  console.log("댓글 수정 성공");
		  isRun = false;
		},
		error : function(){
		   alert("시스템 오류");
		}
	});
 }
 
 /* 댓글 삭제 여부 확인 */
 function replyDelete(rno, cno, rdepth, rgroup) {
	if(confirm('댓글을 삭제하시겠습니까?')) {
	   location.href='community_replyDelete.do?rno='+rno+'&cno='+cno+'&rdepth='+rdepth+'&rgroup='+rgroup;
	   alert('댓글이 삭제되었습니다.');
	   location.reload();
	} else {
	   alert('댓글 삭제가 취소되었습니다.');
	}
 }
 
 /* 답글쓰기 칸 보여주기  */
 function reReplyShow(rno, loginNick, cno) {
 
	if(loginNick == "") {
	   loginNick = "관리자";
	}
 
	var text = '';
	text += '<div class="re_replyWrite"><div class="replyWrite" style="display: none;">';
	text += '<div class="replyWriteWriter"><input name="reply_writer" class="reply_writer" value='+loginNick+' readonly></div>';
	text += '<div class="replyWriteContent"><textarea name="reply_content" class="reply_content" placeholder="댓글을 입력해주세요."></textarea></div>';
	text += '<div class="replyWriteButton"><input type="button" value="등록" onclick="replyInsert(this, '+cno+','+rno+')">';
	
	if($("#replyNum_"+rno+" .replyWrite").length == 0){
	   $("#replyNum_"+rno).append(text);
	}
 
	if($("#replyNum_"+rno+" .replyWrite").css("display")=="none"){
	   $("#replyNum_"+rno+" .replyWrite").show();
	   $("#replyNum_"+rno+" .reReplyShow").attr('value','답글쓰기 취소');
	} else {
	   $("#replyNum_"+rno+" .replyWrite").hide();
	   $("#replyNum_"+rno+" .reReplyShow").attr('value','답글쓰기');
	}
 }
 
 function replyReset(rno) {
	$("#replyNum_"+rno+" .reply_content").val("");
 }
 
function clip(){
	var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
}

function showLikeUserList() {
	if($(".likeMembersWrap").css("display") == "none"){
		$(".likeMembersWrap").show();
	}else{
		$(".likeMembersWrap").hide();
	}
}