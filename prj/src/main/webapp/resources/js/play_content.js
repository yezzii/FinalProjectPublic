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