//지역 // 문서가 로드되면
$(function () {
  //체인지가 발생했을 때 동작 정의
  $(".check_wrap1").change(function () {
    //form에 있는 값들을 한번에 전송 가능한 data로 만들수 있는 .serialize()
    $("#location-form").serialize();
    var checkedValues = [];
    //선택자 					//반복문
    $('input[type="checkbox"]:checked').each(function () {
      checkedValues.push($(this).val());
      //this = 현재 이벤트가 발생한 체크박스 요소를 나타냄
      // val() values값      / push()추가해주는 메서드
    });

    $.ajax({
      url: "play_locationcheck.do",

      type: "post",
      data: {
        // key: values
        values: JSON.stringify(checkedValues),
        //JSON.stringify() avaScript 객체나 배열을 JSON 문자열로 변환하는 함수
        //checkedValues 배열을 JSON 형식의 문자열로 변환
      },
      timeout: 10000,
      success: function (data) {
        var parsedData = JSON.parse(data); // JSON 문자열을 JavaScript 객체로 변환

        /* console.log("data: " + data);
                 console.log("111"+ checkedValues);
                 console.log("222"+ parsedData);
                 console.log("333"+ JSON.stringify(data));
                 console.log("parsedData: " + JSON.stringify(parsedData));
                 console.log("444"+ parsedData);
                 console.log("555"+ JSON.stringify(data)); */

        var playlistContainer = $("#playlist-container");
        playlistContainer.empty();

        var parsedObject = parsedData;

        parsedObject.SearchResult.forEach(function (item, index) {
          var playListElement = $('<div class="playlist-item"></div>');

          // 이미지 추가
          var playImage = $(
            '<a href="/controller/play_content.do?play_idx=' +
              item.play_index +
              '"><img class="play_img" src="./resources/assets/' +
              item.play_picture +
              '"></a>'
          );
          playListElement.append(playImage);

          // 재생 이름 추가
          var playName = $(
            '<br><span class="play_name">' + item.play_name + "</span> &nbsp;&nbsp;"
          );
          playListElement.append(playName);

          // 좋아요 개수 추가
          var playLike = $(
            '<img class="like_heart" src="./resources/assets/play_pic/like_heart.png">&nbsp;&nbsp;'
          );
          var playLikeCount = $(
            '<span class="play_likefont">&nbsp;' + item.play_like + "개 &nbsp;&nbsp;&nbsp;</span>"
          );
          playListElement.append(playLike);
          playListElement.append(playLikeCount);

          // 별 개수 추가
          var playStar = $(
            '<span class="play_star">⭐</span>'
          );
          
          var playStarCount = $(
            '<span class="play_starfont">'+item.play_rating.toFixed(1)+'&nbsp;&nbsp;&nbsp;</span>'
          );
          playListElement.append(playStar);
          playListElement.append(playStarCount);



          // 조회수 추가
          var playView = $(
            '<img class="play_view" src="./resources/assets/play_pic/play_view.png">'
          );
          var playViewCount = $(
            '<span class="play_viewfont">&nbsp;' + item.play_view + "</span>"
          );
          playListElement.append(playView);
          playListElement.append(playViewCount);

          // 숨겨진 필드 추가
          var playGroup = $(
            '<input type="hidden" name="play_group" value="' +
              item.play_group +
              '">'
          );
          var playAddress = $(
            '<input type="hidden" name="play_address" value="' +
              item.play_address +
              '">'
          );
          var playPrice = $(
            '<input type="hidden" name="play_price" value="' +
              item.play_price +
              '">'
          );

          playListElement.append(playGroup);
          playListElement.append(playAddress);
          playListElement.append(playPrice);

          playlistContainer.append(playListElement);

          if ((index + 1) % 3 === 0) {
            let brtag = $("<br>");
            playlistContainer.append(brtag);
          }
        });
      },
      error: function (request, status, error) {
        alert(
          "status: " +
            request.status +
            ", message: " +
            request.responseText +
            ", error: " +
            error
        );
      },
    });
  });
});
