<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play</title>
<link rel="stylesheet" href="./resources/css/play/playcont.css" />
</head>
<body>
	<%@include file="../include/header01.jsp"%>
	<c:set var="dto" value="${playCont}" />
	<c:set var="UserId" value="${sessionScope.UserId }"/>
	<c:set var="MemDTO" value="${memDTO}"/>
	<c:set var="ReplayList" value="${replayList }"/>
	<c:set var ="InfoDTO" value="${InfoDto}"/>
	<c:set var = "StarCount" value="${star}"/>
	<c:set var="totalcomment" value="${total}"/>
	<c:set var="rating" value="${StarCount/totalcomment}"/>
	
	<div class="all-wrap">
		<div class="left-wrap">
			<div class="left-cont">
				<img class="cont-img" src="./resources/assets/${dto.play_picture}"></img>
				<div class="cont-text">
					<span class="cont-title"><h2>${dto.play_name }</h2></span> <span
						class="cont-cont">${dto.play_explanation1 } <br>
						${dto.play_explanation2 }<br>${dto.play_explanation3 }<br>예상
						소요 시간 : ${dto.play_time }분
					</span>
				</div>

			</div>
			
			<div class="play_reply">
				<form method="post" name="myform" id="myform">
				<fieldset>
			        <span>
			       <c:choose>
				    <c:when test="${rating == 'Infinity'}">
				     0점
				    </c:when>
				    <c:when test="${rating ne rating or rating eq 'NaN'}">
				     0점&nbsp;&nbsp;&nbsp;
				    </c:when>
				    <c:otherwise>
				      <fmt:formatNumber value="${rating}" pattern="#.#"/>점&nbsp;&nbsp;&nbsp;
				    </c:otherwise>
				  </c:choose>
			        </span>
			        <span id="totalCount">&nbsp;&nbsp;&nbsp;0</span>
			        <input type="hidden" name="star_count" value="${InfoDTO.play_rating }">
			        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
			        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
			        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
			        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
			        <input type="radio" name="rating" value="1" id="rate5" checked><label for="rate5">⭐</label>
			    </fieldset>
				
				<c:if test="${!empty UserId}">
					<span class="textForm">
						<input type="text" name="play_reply" id="reviewContents" value="${play_comment}" placeholder="소중한 한줄 후기를 남겨주세요!">
						<input type="hidden" name="nick_name" value="${MemDTO.nick_name }">
						<input type="hidden" name="play_index" value="${dto.play_index }">
						<input type="hidden" name="user_id" value="${sessionScope.UserId }">
						
					</span> 
					<input type="submit" value="댓글쓰기" class="play_button">
				</c:if>	
				</form>	
				
				<c:if test="${empty UserId}">
					<span class="textForm">
						<input type="text" name="play_reply" id="reviewContents" placeholder="로그인 후 후기를 남겨주세요!">
					</span> 
					<input type="button" value="로그인" class="play_button" onclick="location.href='member_main.do'" >
				</c:if>	
				<br><br>
				
				<!-- 댓글 폼 -->
				<c:if test="${!empty ReplayList}">
				  <c:forEach items="${ReplayList}" var="replydto">
				   <div id="nickname_${replydto.play_comment_num}" class="nickname">${replydto.member_nickname}</div>
				    <div class="starcount">
				      <ul class="star-list">
				        <c:forEach begin="1" end="5" var="i">
				          <li class="star-item ${i <= replydto.play_star ? 'active' : ''}">
				            <c:if test="${i <= replydto.play_star}">
				            		  ⭐
				            </c:if>
				            <c:if test="${i > replydto.play_star}">
				             		 <span class="star_white">⭐</span>
				            </c:if>
				          </li>
				        </c:forEach>
				      </ul>
				    </div>
				    
				    <div id="reply_form_${replydto.play_comment_num}">
				    <span id="reply_update1_form_${replydto.play_comment_num}"class="commentText">${replydto.play_comment}</span>
				     <c:if test="${replydto.member_id eq UserId}">
				    	
				    	<span class="replyDelete" onclick="deleteComment(${replydto.play_comment_num})">삭제</span>
				    	<span class="replyModify" onclick="Popup(${replydto.play_comment_num})">수정</span>
				    	
				    </c:if>
				   
				    <div class="date" id="comment_date_${replydto.play_comment_num}">
   						 ${replydto.play_update != null ? replydto.play_update : replydto.play_comment_regdate}
					</div>
				     </div>
				    <!--수정 댓글 폼 -->
				    <div id="reply_modify_form_${replydto.play_comment_num}" hidden>
					  <input type="text" class ="reviewContents" id="modify_reply_${replydto.play_comment_num}" value="${replydto.play_comment}">
					  <input type="hidden" id="modify_nickname_${replydto.play_comment_num}"value="${replydto.member_nickname}">
					  <input type="button" class="modify_button" value="저장" onclick="saveModifyComment(${replydto.play_comment_num})">
					</div>
				    
				    <hr>
				  </c:forEach>
				</c:if>
			</div>
		</div>
		<div class="right-cont"></div>
	</div>




	<%@include file="../include/footerPlayContent.jsp"%>
	<script>
	 
	/*댓글 폼 */
	function Popup(commentNum) {
		var commentTextElement = document.getElementById("reply_form_"+commentNum);
		  var modifyFormElement = document.getElementById("reply_modify_form_"+commentNum);

		  if (commentTextElement.style.display != "none") {
		    commentTextElement.style.display = "none";
		    modifyFormElement.hidden = false;
		  } else {
		    commentTextElement.style.display = "inline";
		    modifyFormElement.hidden = true;
		  }
    	}
	
	 /*댓글 수정  */
    function saveModifyComment(commentNum) {
    	
        var modifyReply = document.getElementById("modify_reply_" + commentNum).value;
        var modifyNickname = document.getElementById("modify_nickname_" + commentNum).value;
        // Ajax 호출
        $.ajax({
          type: 'post',
          url: 'comment_modifyok.do',
          data: {
            commentNum: commentNum,
            modifyReply: modifyReply,
            modifyNickname: modifyNickname
            
          },
          dataType: 'json',
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8', // 인코딩 설정 추가
          success: function(response) {
            
        	// 수정된 댓글을 화면에 업데이트하는 코드 작성
        	  var commentTextElement = document.getElementById("reply_update1_form_" + commentNum);
        	  var nicknameElement = document.getElementById("nickname_" + commentNum);
        	  var commentDateElement = document.getElementById("comment_date_" + commentNum);
        	  var replyFormElement = document.getElementById("reply_form_" + commentNum);
        	  var modifyFormElement = document.getElementById("reply_modify_form_" + commentNum);

        	  if (response.success) {
        	    // 수정된 댓글 내용, 닉네임, 날짜 업데이트
        	    commentTextElement.textContent = response.comment;
        	    nicknameElement.textContent = response.nickname;
        	    commentDateElement.textContent = response.update;

        	    // 수정 댓글 폼 숨기기
        	    modifyFormElement.hidden = true;

        	    // 댓글 폼 보여주기
        	    replyFormElement.style.display = "block";
        	  } else {
        	    // 수정 실패 시 처리할 코드 작성
          }
          },
          error: function(xhr, status, error) {
            console.log("상태 코드:", xhr.status, "에러 메시지:", error);
          }
        });
      }
    /*댓글 삭제  */
    function deleteComment(commentNum) {
    
    	
        var deleteform = confirm('댓글을 삭제하시겠습니까?');
        
        if (deleteform) {
            $.ajax({
                url: 'comment_delete.do',
                data: {
                    commentNum: commentNum,
                },
                datatype: 'json',
                type: 'post',
                success: function(data) {
                    if (data.success) {
                        // 삭제 성공
                        alert(data.message);
                        location.reload();
                    } else {
                        // 삭제 실패
                        alert(data.message);
                    }
                },
                error: function(xhr, status, error) {
                    console.log("상태 코드:", xhr.status, "에러 메시지:", error);
                }
            });
        }
    }
	
  $(document).ready(function() {
	  /*크롤링 */
	  $.ajax({
          url: "play_craw.do",            
          data: {
             "cont_tit": $(".cont-title").text(),
          },
          dataType:"json",
          type: "post",
          //contentType: "application/json; charset=utf-8;",
          success: function(data) {
              for (let i = 0; i < 3; i++) {
             $(".right-cont").append("<div class='cont-container'>" + "<img class='sum-img' src="+data.ModifiedImageUrl[i]+"/>"
             +"<div class ='text-con'>"+"<span class ='contit'>" +"<a href="+data.Href[i]+">"+ data.Tit[i] + "</span></a><br><span class='concon'>" + data.ContCont[i] + "</span></div></div>");
              }
          },
          error: function() {
                alert("error");
            }
       })

	  /*댓글 등록  */
	  function submitComment() {
      var star = document.querySelector('input[name="rating"]:checked').value;
      var reply = document.getElementById('reviewContents').value;
      var nickname = document.getElementsByName('nick_name')[0].value;
      var id = document.getElementsByName('user_id')[0].value;
      var play_index = document.getElementsByName('play_index')[0].value;
      
      
      $.ajax({
        type: 'post',
        url: 'play_comment_insert.do',
        data: {
          star: star,
          reply: reply,
          nickname: nickname,
          id: id,
          play_index: play_index
        },
        dataType: 'text',
        success: function(response) {
        	if (response === 'success') {
        	      var newHTML =
        	        '<div class="nickname">' +
        	        nickname +
        	        '</div>' +
        	        '<div class="starcount">' +
        	        '<ul class="star-list">';
        	      for (var i = 1; i <= 5; i++) {
        	        if (i <= star) {
        	          newHTML += '<li class="star-item active">⭐</li>';
        	        } else {
        	          newHTML += '<li class="star-item">🤍</li>';
        	        }
        	      }
        	      const currentDate = new Date();
        	      var year = currentDate.getFullYear();
        	      var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
        	      var day = ('0' + currentDate.getDate()).slice(-2);
        	      var dateString = year + '-' + month + '-' + day;
        	      newHTML +=
        	        '</ul></div>' +
        	        '<div>' +
        	        reply +
        	        '<span class="replyDelete" onclick="deleteComment(${replydto.play_comment_num})">삭제</span><span class="replyModify" onclick="Popup(${replydto.play_comment_num})">수정</span>' +
        	        '</div>' +
        	        '<div>' +
        	        dateString +
        	        '</div>' +
        	        '<hr>';

        	      // 새로운 댓글을 리스트에 추가
        	      $('.play_reply').append(newHTML);

        	      // 입력 필드 초기화
        	      $('input[name="rating"]').prop('checked', false);
        	      $('#reviewContents').val('');
        	      location.reload();
          } else {
            // 댓글 추가에 실패한 경우 처리할 코드 작성
          }
        },
        error: function(xhr, status, error) {
          console.log("상태 코드:", xhr.status, "에러 메시지:", error);
        }
      });
    }

    // submitComment() 함수를 클릭 이벤트로 연결
    $('#myform').submit(function(e) {
      e.preventDefault(); // 기본 동작 중단
      submitComment(); // 댓글 추가 함수 호출
    });
    
    
    /* 댓글 총 수 */
    var playIndex = ${dto.play_index};
    console.log("play_index>>> " + playIndex);
    
    $.ajax({
        url: 'comment_count.do',
        type: 'post',
        data: {playIndex: playIndex},
        dataType: 'text',
        success: function(response) {
            var totalCount = parseInt(response);
            $('#totalCount').text(totalCount+"개의 후기");
        },
        error: function(xhr, status, error) {
            console.log("상태 코드:", xhr.status, "에러 메시지:", error);
        }
    });
  });
  
</script>
</body>
</html>
