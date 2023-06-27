<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO</title>
<link rel="stylesheet" href="./resources/css/mainslide.css">
<link rel="stylesheet" href="./resources/css/mainmodal.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="./resources/js/main_ad.js"></script>
</head>
<body>
   <%@include file="./include/headerMain.jsp"%>
   <c:set var="UserId" value="${sessionScope.UserId}" />
   <div class="main_wrap">
      <div class="container1">
         <form method="post" action="<%=request.getContextPath()%>/disposition1.do">
               <div class="left-form">
               <span class="title">TODO</span><br><br>
               <span class="text1"> 
                              일상에 지친 당신, 오늘 할 일이 고민이 될 때는 TODO 성향 검사를 해보세요.<br>
                              여러분의 성향에 맞는 검사 결과에 따라 오늘 할 일을 추천받을 수 있습니다.<br>
                              특별한 당신을 위해 지금 시작해보세요!
               </span><br><br>
               <div class="startbutton"></div>
                  <%
                  String userID = (String) session.getAttribute("UserId");
                  session.setAttribute("IDID", userID);
                  if (session.getAttribute("UserId") == null) {
                  %>
               <button type="button" id="modal-open">START</button>
               <div id="plz-login" class="button-container">
                  <div class="modal-text" align="center">
                     <p>성향 검사는 로그인이 필요한 서비스 입니다. </p>
                     <p>먼저 로그인을 하시고 이용해주세요. </p>
                  </div><br><br>
                  <button type="button" id="modal-close">Close</button>
                  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                  <a href="<%=request.getContextPath()%>/member_main.do">LogIn</a>
               </div>
                  <%
                     } else {
                  %>
                  <%-- <input type="hidden" value="${IDID }">  --%>
                  <input type="button" class="matching2" value="START" onclick="location.href='disposition0.do?id=<%=userId%>&no=1'">
                  <%
                     }
                  %>
               </div>
         </form>
      </div>
      <div class="container2">
         <div id="slideShow">
            <ul class="slides">
                  <li><img class="slideimg" alt="" src="./resources/assets/NetFlix.gif"></li>
                  <li><img class="slideimg" alt="" src="./resources/assets/game.gif"></li>
                  <li><img class="slideimg" alt="" src="./resources/assets/sports.gif"></li>
                  <li><img class="slideimg" alt="" src="./resources/assets/festival.gif"></li>
                  <li><img class="slideimg" alt="" src="./resources/assets/travel2.gif"></li>
                  <li><img class="slideimg" alt="" src="./resources/assets/travel1.gif"></li>
               </ul>
               <p class="controller">
                  <!-- &lang: 왼쪽 방향 화살표 &rang: 오른쪽 방향 화살표 -->
                  <span class="prev">&lang;</span>
                  <span class="next">&rang;</span>
               </p>
         </div>
      </div>
   </div>
   
   <div class="best_content">
      <div class="mainAdWrap">
         <img id="randomImage" name="randomImage" alt="" src="" onclick="">
      </div>
      <span class="best_font">
         <i class="fa-solid fa-fire"></i>
         활동적인것을 좋아하는 당신을 위한 BEST
         <i class="fa-solid fa-volleyball fa-bounce"></i>
         <a class="more_font" href="<%=request.getContextPath()%>/play_list.go">더보기</a>
      </span><br><br>
      
      <!-- 정보 보여주기  -->
      <c:set var="list" value="${OutSide}" />
      <c:if test="${!empty list }">
         <div id="playlist-container" class="list_wrap">
            <c:forEach items="${list }" var="dto" varStatus="status">
                  <div class="play_list" data-address="${dto.play_address }">
                  <div class="play_pic">
                           <a href="#" class="play-link" data-play-index="${dto.play_index}">
                        <img class="play_img" src="./resources/assets/${dto.play_picture}">
                     </a>
                  </div>
                        <span class="play_name"> ${dto.play_name }</span>
                  <c:set value="${0 }" var="like_check" />
                  <c:forEach items="${like_list }" var="likes">
                     <c:if test="${dto.play_index == likes}">
                        <c:set value="${1 }" var="like_check" />
                     </c:if>
                  </c:forEach>
                  
                  <!--userId가 있을때 좋아요 활성화  -->
                  <c:if test="${!empty UserId}">
                     <c:if test="${like_check == 1 }">
                        <img onclick="like_insert(${dto.play_index }, this)" class="like_heart" id="like_heart_fill"
                           src="./resources/assets/play_pic/like_heart_fill.png" data-play-index="${dto.play_index }" data-liked="1">
                     </c:if>
                     <c:if test="${like_check == 0 }">
                        <img onclick="like_insert(${dto.play_index }, this)" class="like_heart" id="like_heart"
                           src="./resources/assets/play_pic/like_heart.png" data-play-index="${dto.play_index }" data-liked="0">
                     </c:if>
                     <span class="play_likefont" data-num-index="${dto.play_index }">&nbsp;${dto.play_like }개</span>  
                  </c:if>
   
                  <!--userId가 없을때 좋아요 비활성화  -->
                  <c:if test="${empty UserId}">
                     <input type="image" class="like_heart modal-open2" id="modal-open2" src="./resources/assets/play_pic/like_heart.png"
                        onmouseover="changeImage(this)" onmouseout="restoreImage(this)">
                     <span class="play_likefont" data-num-index="${dto.play_index}">&nbsp;${dto.play_like}개</span> 
                  </c:if>
                  <span class="play_star">⭐</span>
                  <span class="play_starfont">&nbsp;
                     <fmt:formatNumber value="${dto.play_rating}" pattern="0.0" />
                  </span>&nbsp;
                  <img class="play_view" src="./resources/assets/play_pic/play_view.png">
                  <span class="play_viewfont">${dto.play_view }</span> 
                  <input type="hidden" name="play_group" value="${dto.play_group }">
                  <input type="hidden" name="play_address" value="${dto.play_address }">
                  <input type="hidden" name="play_price" value="${dto.play_price }">
               </div>
            </c:forEach>
         </div>
      </c:if><br><br><br><br><br>
      
      <span class="best_font">
         <i class="fa-solid fa-house"></i>
         집에서 쉬는것을 좋아하는 당신을 위한 BEST
         <i class="fa-solid fa-gamepad fa-bounce"></i>
      <!-- <i class="fa-solid fa-headset fa-bounce"></i> -->
         <a class="more_font" href="<%=request.getContextPath()%>/play_list.go">더보기</a>
      </span><br><br>
      
      <!-- 정보 보여주기  -->
      <c:set var="list1" value="${InSide}" />
      <c:if test="${!empty list1 }">
      <div id="playlist-container" class="list_wrap">
         <c:forEach items="${list1 }" var="in" varStatus="status">
            <div class="play_list" data-address="${in.play_address }">
               <div class="play_pic">
                  <a href="#" class="play-link" data-play-index="${in.play_index}">
                     <img class="play_img" src="./resources/assets/${in.play_picture}">
                  </a>
               </div>
               <span class="play_name"> ${in.play_name }</span>
               <c:set value="${0 }" var="like_check" />
               <c:forEach items="${like_list }" var="likes">
                  <c:if test="${in.play_index == likes}">
                     <c:set value="${1 }" var="like_check" />
                  </c:if>
               </c:forEach>
               
                     <!--userId가 있을때 좋아요 활성화  -->
                     <c:if test="${!empty UserId}">
                        <c:if test="${like_check == 1 }">
                     <img onclick="like_insert(${in.play_index }, this)" class="like_heart" id="like_heart_fill"
                              src="./resources/assets/play_pic/like_heart_fill.png" data-play-index="${in.play_index }" data-liked="1">
                        </c:if>
                        <c:if test="${like_check == 0 }">
                           <img onclick="like_insert(${in.play_index }, this)" class="like_heart" id="like_heart"
                              src="./resources/assets/play_pic/like_heart.png" data-play-index="${in.play_index }" data-liked="0">
                        </c:if>
                        <span class="play_likefont" data-num-index="${in.play_index }">&nbsp;${in.play_like }개</span>  
                     </c:if>

                    <!--userId가 없을때 좋아요 비활성화  -->
                     <c:if test="${empty UserId}">
                        <input type="image" class="like_heart modal-open1" id="modal-open1" src="./resources/assets/play_pic/like_heart.png"
                           onmouseover="changeImage(this)" onmouseout="restoreImage(this)">
                        <span class="play_likefont" data-num-index="${in.play_index}">&nbsp;${in.play_like}개</span> 
                     </c:if>
                     <span class="play_star">⭐</span>
                     <span class="play_starfont">&nbsp;
                        <fmt:formatNumber value="${in.play_rating}" pattern="0.0" />
                     </span>&nbsp;
                     <img class="play_view" src="./resources/assets/play_pic/play_view.png">
                     <span class="play_viewfont">${in.play_view }</span> 
                     <input type="hidden" name="play_group" value="${in.play_group }">
                     <input type="hidden" name="play_address" value="${in.play_address }">
                     <input type="hidden" name="play_price" value="${in.play_price }">
               </div>
            </c:forEach>
         </div>
   </c:if>
   </div>
   <br><br><br><br><br><br>
   <%@include file="./include/footer.jsp"%>
   <script type="text/javascript" src="./resources/js/mainslide.js"></script>
   <script type="text/javascript" src="./resources/js/modal.js"></script>
   <script type="text/javascript" src="./resources/js/main.js"></script>
   <script type="text/javascript">
   random_img();
   </script>
</body>
</html>