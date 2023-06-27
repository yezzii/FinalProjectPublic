const clock = document.querySelector(".clock");

function sayHello() {
  console.log("hello");
}

// setInterval(sayHello, 5000);

setTimeout(sayHello, 5000);

// const date = new Date(); // date 오브젝트 생성
// //new Date 출력 하면 오늘날짜 출력
// date.getDate(); //오늘 날짜의 일을 가져옴
// date.getDay(); //현재 요일을 가져옴
// date.getFullYear();
// date.getHours();
// date.getMinutes();
// date.getSeconds();

function getClock() {
  const date = new Date(); // 코드를 실행한 시점의 시간 정보(현재 날짜, 시간)들을 가져와준다.
  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");
  const seconds = String(date.getSeconds()).padStart(2, "0");

  clock.innerText = `${hours}:${minutes}:${seconds}`;
}

getClock(); // 웹페이지가 로드되자마자 실행되게 하려고!
setInterval(getClock, 1000);
