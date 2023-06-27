let box0 = document.querySelector(".box0");
let box1 = document.querySelector(".box1");
let box6 = document.querySelector(".box6");
let box2 = document.querySelector(".box2");
let box3 = document.querySelector(".box3");
let box4 = document.querySelector(".box4");
let box5 = document.querySelector(".box5");

let whiteScreenClass = "white-screen";

box5.addEventListener("click", function () {
 document.body.classList.add(whiteScreenClass);
  box3.style.right = "120px";
  box4.style.right = "160px";
  box5.style.right = "1690px";
  setTimeout(function () {
    window.location.href = "./play_list.go";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});

box4.addEventListener("click", function () {
 document.body.classList.add(whiteScreenClass);
  box3.style.right = "160px";
  box4.style.right = "1650px";
  box5.style.right = "1690px";
  setTimeout(function () {
    window.location.href = "./play_random.do";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});
box2.addEventListener("click", function () {
 document.body.classList.add(whiteScreenClass);
  box2.style.right = "1570px";
  box3.style.right = "1610px";
  box4.style.right = "1650px";
  box5.style.right = "1690px";
  setTimeout(function () {
    window.location.href = "./qna_main.do";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});
box1.addEventListener("click", function () {
 document.body.classList.add(whiteScreenClass);
  box1.style.right = "1530px";
  box6.style.right = "1530px";
  box2.style.right = "1570px";
  box3.style.right = "1610px";
  box4.style.right = "1650px";
  box5.style.right = "1690px";
  setTimeout(function () {
    window.location.href = "./member_main.do";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});
box6.addEventListener("click", function () {
 document.body.classList.add(whiteScreenClass);
  box1.style.right = "1530px";
  box6.style.right = "1530px";
  box2.style.right = "1570px";
  box3.style.right = "1610px";
  box4.style.right = "1650px";
  box5.style.right = "1690px";
  setTimeout(function () {
    window.location.href = "./member_myPage.do";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});
box0.addEventListener("click", function () {
  box1.style.right = "0px";
  box6.style.right = "0px";
  box2.style.right = "40px";
  box3.style.right = "80px";
  box4.style.right = "120px";
  box5.style.right = "160px";
  
          box0.style.width ="225px";
        box0.style.height ="118px";
        document.body.classList.add(whiteScreenClass);
  
  setTimeout(function () {
    window.location.href = "./main.do";
  }, 1000); // 1초 후에 'a.html'로 페이지 이동
});
