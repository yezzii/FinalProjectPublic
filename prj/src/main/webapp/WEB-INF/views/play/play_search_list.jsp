<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>Play</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./resources/css/play/playList.css" />
<link rel="stylesheet" href="./resources/css/play/modal.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap"
	rel="stylesheet">
</head>
<body>

</body>
</html>

</head>
<body>
	<%@include file="../include/header01.jsp"%>
	<c:set var="UserId" value="${sessionScope.UserId}" />
	<c:set var="list" value="${SearchList}" />
	<c:set var="total" value="${TotalRecord}" />
	<form method="post"
		action="<%=request.getContextPath()%>/SearchResultOk.do"
		id="location-form" class="search-form">
		<%-- action="<%=request.getContextPath() %>/play_check.go" --%>

		<div id="plz-login1">
			<div id="plz-login" class="button-container">
				<div class="modal-text" align="center">
					<p>좋아요는 로그인이 필요한 서비스 입니다.</p>
					<p>먼저 로그인을 하시고 이용해주세요.</p>
				</div>
				<br> <br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
				<button type="button" id="modal-close1">Close</button>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a
					href="<%=request.getContextPath()%>/member_main.do">LogIn</a>
			</div>
		</div>



		<div class="location bottom-margin">
			<span class="head">지 역</span> <input type="checkbox" name="loc_chk"
				id="loc1" class="check_wrap1" value="강북구" /> <label for="loc1">강북구</label>
			<input type="checkbox" name="loc_chk" id="loc2" class="check_wrap1"
				value="강남구" /> <label for="loc2">강남구</label> <input type="checkbox"
				name="loc_chk" id="loc3" class="check_wrap1" value="강동구" /> <label
				for="loc3">강동구</label> <input type="checkbox" name="loc_chk"
				id="loc4" class="check_wrap1" value="강서구" /> <label for="loc4">강서구</label>
			<hr class="hr">
		</div>

		<br>
		<div class="outside">
			<span class="head">야 외</span> <input type="checkbox" name="side_chk"
				id="out1" class="check_wrap1" value="운동" /> <label for="out1">운동</label>
			<input type="checkbox" name="side_chk" id="out2" class="check_wrap1"
				value="여행" /> <label for="out2">여행</label> <input type="checkbox"
				name="side_chk" id="out4" class="check_wrap1" value="전시회" /> <label
				for="out4">전시회</label> <input type="checkbox" name="side_chk"
				id="out5" class="check_wrap1" value="뮤지컬" /> <label for="out5">뮤지컬</label>
			<input type="checkbox" name="side_chk" id="out6" class="check_wrap1"
				value="축제" /> <label for="out6">축제</label>
		</div>

		<hr class="hr">

		<br>

		<div class="inside">
			<span class="head">실 내</span> <input type="checkbox" name="side_chk"
				id="in1" class="check_wrap1" value="홈트" /> <label for="in1">홈트</label>
			<input type="checkbox" name="side_chk" id="in2" class="check_wrap1"
				value="요리" /> <label for="in2">요리</label> <input type="checkbox"
				name="side_chk" id="in3" class="check_wrap1" value="OTT" /> <label
				for="in3">OTT</label> <input type="checkbox" name="side_chk"
				id="in4" class="check_wrap1" value="게임" /> <label for="in4">게임</label>
			<hr class="hr">
		</div>


		<br>

		<div class="price">
			<span class="head">가 격</span> <input type="checkbox" name="price_chk"
				id="pri1" class="check_wrap1" value="무료" /> <label for="pri1">무료</label>

			<input type="checkbox" name="price_chk" id="pri2" class="check_wrap1"
				value="유료" /> <label for="pri2">유료</label>

			<div class="wrapper">
				<div class="slider">
					<div class="progress"></div>
				</div>
				<div class="range-input">
					<input type="range" class="range-min" min="0" max="800000"
						value="10000" step="1000"> <input type="range"
						class="range-max" min="0" max="800000" value="500000" step="1000">
				</div>
				<div class="price-input">
					<div class="field input-min-wrap">
						<input type="number" name="price_range" class="input-min"
							value="1000"> <span>원</span>
					</div>
					<!-- <div class="separator">-</div> -->
					<div class="field input-max-wrap">
						<input type="number" name="price_range" class="input-max"
							value="500000"> <span>원</span>
					</div>

					<input class="play_button" type="submit" value="검색">
					<!-- <button class="play_button" onclick="submit">검색</button> -->
					<span class="list_container" id="lens-icon"> <i
						class="fa fa-search" id="search_list"></i>
					</span>
				</div>
			</div>
		</div>
	</form>


	<form method="post" id="distance-form"
		action="<%=request.getContextPath()%>/DistanceSearchResultOk.do">
		<div class="distance-input" id="distance-input">
			<hr class="hr" style="margin-top: 17px">
			<span class="head">거 리 </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="wrapper">
				<div class="slider1">
					<div class="progress1"></div>
				</div>
				<div class="range-input1">
					<input type="range" class="range-min1" min="0" max="60000"
						value="0" step="10"> <input type="range"
						class="range-max1" min="0" max="60000" value="500" step="10">
				</div>
				<div class="price-input1">
					<div class="field1 input-min-wrap1">
						<input type="number" class="input-min1" name="distance_input"
							value="0"> <span>KM</span>
					</div>
					<!-- <div class="separator">-</div> -->
					<div class="field1 input-max-wrap1">
						<input type="number" class="input-max1" name="distance_input"
							value="500"> <span>KM</span>
					</div>
					<input class="play_button" type="submit" value="검색">
				</div>
			</div>
		</div>
	</form>

	<br>
	<br>
	<br>
	<div class="search_total">Player가 검색한 PlayList는 ${total}개 입니다.</div>

	<!-- 정보 보여주기  -->
	<!-- <img class="play_list" alt="" src="./resources/play_pic/podo.jpg"> -->

	<c:if test="${!empty list }">
		<div id="playlist-container" class="list_wrap">
			<c:forEach items="${list }" var="dto" varStatus="status">
				<div class="play_list" data-address="${dto.play_address }">
					<a href="#" class="play-link" data-play-index="${dto.play_index}">
						<img class="play_img" src="./resources/assets/${dto.play_picture}">
					</a> <br> <span class="play_name"> ${dto.play_name }</span>
					<c:set value="${0 }" var="like_check" />
					<c:forEach items="${like_list }" var="likes">
						<c:if test="${dto.play_index == likes}">
							<c:set value="${1 }" var="like_check" />
						</c:if>
					</c:forEach>
					<!--userId가 있을때 좋아요 활성화  -->
					<c:if test="${!empty UserId}">
						<c:if test="${like_check == 1 }">
							<img onclick="like_insert(${dto.play_index }, this)"
								class="like_heart" id="like_heart_fill"
								src="./resources/assets/play_pic/like_heart_fill.png"
								data-play-index="${dto.play_index }" data-liked="1">
						</c:if>
						<c:if test="${like_check == 0 }">
							<img onclick="like_insert(${dto.play_index }, this)"
								class="like_heart" id="like_heart"
								src="./resources/assets/play_pic/like_heart.png"
								data-play-index="${dto.play_index }" data-liked="0">
						</c:if>
						<span class="play_likefont" data-num-index="${dto.play_index }">&nbsp;${dto.play_like }개</span>
					</c:if>

					<!--userId가 없을때 좋아요 비활성화  -->
					<c:if test="${empty UserId}">

						<input type="image" class="like_heart modal-open1"
							id="modal-open1" src="./resources/assets/play_pic/like_heart.png"
							onmouseover="changeImage(this)" onmouseout="restoreImage(this)">
						<span class="play_likefont" data-num-index="${dto.play_index}">&nbsp;${dto.play_like}개</span>


					</c:if>
					<span class="play_star">⭐</span><span class="play_starfont">&nbsp;<fmt:formatNumber
							value="${dto.play_rating}" pattern="0.0" /></span> &nbsp;<img
						class="play_view" src="./resources/assets/play_pic/play_view.png">
					<span class="play_viewfont">${dto.play_view }</span> <input
						type="hidden" name="play_group" value="${dto.play_group }">

					<input type="hidden" name="play_address"
						value="${dto.play_address }"> <input type="hidden"
						name="play_price" value="${dto.play_price }">
				</div>

				<!-- 3개까지 한 페이지로 넘어가게끔 해주는 코드  -->
				<c:if test="${status.count %3 == 0}">
					<br>
					<br>
					<br>
					<br>
				</c:if>
			</c:forEach>
		</div>
	</c:if>


	<!-- 페이징 처리 출력 부분 -->
	<%-- <div class="Pagination">
		<c:if test="${paging.page > paging.block}">
			<a href="play_list.go?page=1">처음으로</a>
			<a href="play_list.go?page=${paging.startBlock - 1}"> ◀</a>
		</c:if>

		<c:forEach begin="${paging.startBlock}" end="${paging.endBlock}"
			var="i">
			<c:if test="${i == paging.page}">
				<b><a href="play_list.go?page=${i}">[${i}]</a></b>
			</c:if>
			<c:if test="${i != paging.page}">
				<a href="play_list.go?page=${i}">[${i}]</a>
			</c:if>
		</c:forEach>

		<c:if test="${paging.endBlock < paging.allPage}">
			<a href="play_list.go?page=${paging.endBlock + 1}">▶</a>
			<a href="play_list.go?page=${paging.allPage}">마지막으로</a>
		</c:if>
	</div> --%>

	<%@include file="../include/footer.jsp"%>

	<script type="text/javascript">

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
                $('.play_viewfont[data-play-index="' + playIndex + '"]').text(response);
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
        window.location.href = 'play_content.do?play_idx=' + playIndex;
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

});

</script>
	<script src="./resources/js/play.js"></script>
	<script src="./resources/js/modal_play.js"></script>
	<script src="./resources/js/play_list.js"></script>
</body>
</html>