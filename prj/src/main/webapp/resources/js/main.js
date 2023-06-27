	function changeImage(img){
		img.src = "./resources/assets/play_pic/like_heart_fill.png";
	} // 호버시 이미지

	function restoreImage(img){
		img.src = "./resources/assets/play_pic/like_heart.png";
	} // 마우스 벗어났을떄 이미지 

	$(document).ready(function(){
	   
		$('.play-link').click(function(e) {
	        e.preventDefault();
	        var playIndex = $(this).data('play-index');
	        $.ajax({
	            url: 'playview_count.do',
	            type: 'POST',
	            data: { playIndex: playIndex },
	            success: function(response) {
	                $('.play_viewfont[data-play-index="'+playIndex+'"]').text(response);
	            },
	            error: function(xhr, status, error) {
	                console.log(error);
	            }
	        });
	        window.location.href = 'play_content.do?play_idx='+playIndex;
	    });
	});

	/*좋아요 구현 + 중복방지  */
	function like_insert(play_no, self) {
	    if ($(self).hasClass('disabled')) {
	        return; // 이미 실행 중인 경우 함수 종료
	    }

	    $(self).addClass('disabled'); // 실행 중인 상태로 설정

	    let check;
	    if ($(self).attr('id') == 'like_heart_fill') {
	        check = 1;
	    } else {
	        check = 0;
	    }

	    $.ajax({
	        url: 'play_heart.go',
	        type: 'post',
	        dataType: 'json',
	        data: {
	            play_it: play_no,
	            check: check
	        },
	        success: function(response) {
	            if (response.status === 'liked') {
	                $(self).attr('id', 'like_heart_fill');
	                $(self).attr('src', './resources/assets/play_pic/like_heart_fill.png');
	            } else if (response.status === 'unliked') {
	                $(self).attr('id', 'like_heart');
	                $(self).attr('src', './resources/assets/play_pic/like_heart.png');
	            }
	            var likeCountElement = $('.play_likefont[data-num-index="' + play_no + '"]');
	            likeCountElement.text(response.likeCount + "개");
	        },
	        error: function(xhr, status, error) {
	            console.log(error);
	        },
	        complete: function() {
	            $(self).removeClass('disabled'); // 실행 종료 상태로 설정
	        }
	    });
	}