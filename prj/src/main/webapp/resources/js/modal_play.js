$(document).ready(function() {
  // 'modal-open1' 클래스를 가진 모든 요소 찾기
  $(".modal-open1").click(function(event) {
    // 'plz-login1' ID를 가진 요소의 표시 전환
    console.log("클릭클릭");
    var content = $("#plz-login1");
    if (content.css("display") === "none") {
      content
        .css({
          display: "block",
          position: "absolute",
          left: event.pageX,
          top: event.pageY,
        })
        .fadeIn();
    } else {
      content.css("display", "none");
    }
  });

  // 'modal-close1' ID를 가진 요소에 클릭 이벤트 리스너 추가
  $("#modal-close1").click(function() {
    $("#plz-login1").css("display", "none");
  });
});
