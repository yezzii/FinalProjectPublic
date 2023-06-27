// 스크롤된 거리를 추적하기 위한 변수 초기화
var scrolledDistance = 0;
var scrolledDistance1 = 0;
let box = $(".box-box");

// 스크롤 이벤트 리스너 추가
var scrollHandler = function () {
  // 사용자가 스크롤한 거리를 추적
  scrolledDistance = $(window).scrollTop();

  // 추적된 스크롤 거리 출력
  console.log("스크롤된 거리: " + scrolledDistance + "px");

  box.css({
    height: 900 + scrolledDistance,
    "margin-top": 0 + scrolledDistance,
  });
};

//스크롤 인식되면
$(window).scroll(scrollHandler);

// 특정 태그를 인식하는 함수
function observeElement() {
  // 태그를 인식할 요소 선택
  var targetElement = $(".footer"); // 대상 요소의 ID를 지정
  var targetElement1 = $(".start");
  var targetElement2 = $(".mid1");
  var targetElement3 = $(".end");
  // Intersection Observer 생성

  var observer = new IntersectionObserver(function (entries) {
    // 인터섹션 이벤트 발생 시 실행할 콜백 함수
    entries.forEach(function (entry) {
      scrolledDistance1 = $(window).scrollTop();

      if (entry.target === targetElement[0] && entry.isIntersecting) {
        // targetElement가 인식되었을 때 실행할 작업
        box.css({
          height: 560,
          "margin-top":0 + scrolledDistance1,
        });
        console.log("footer");
        $(window).off("scroll", scrollHandler);
      } else if (entry.target === targetElement1[0] && entry.isIntersecting) {
        // targetElement1이 인식되었을 때 실행할 작업
        box.css({
          height: 460,
          "margin-top": 0 + scrolledDistance1,
        });
        console.log("start");
      } else if (entry.target === targetElement2[0] && entry.isIntersecting) {
        box.css({
          height: 370,
          "margin-top": 0 + scrolledDistance1,
        });
        console.log("mid");
      } else if (entry.target === targetElement3[0] && entry.isIntersecting) {
        box.css({
          height: 290,
          "margin-top": 0 + scrolledDistance1,
        });
        console.log("end");
      } else {
        console.log("특정 태그가 인식안됌~!");
        $(window).on("scroll", scrollHandler);
      }
    });
  });

  // 타겟 요소 감시 시작
  observer.observe(targetElement[0]);
  observer.observe(targetElement1[0]); // 추가한 요소를 관찰합니다.
  observer.observe(targetElement2[0]); // 추가한 요소를 관찰합니다.
  observer.observe(targetElement3[0]); // 추가한 요소를 관찰합니다.
}

// 스크롤 이벤트 리스너 추가
$(window).scroll(function () {
  // 스크롤 중에 호출할 함수
  observeElement();
});
