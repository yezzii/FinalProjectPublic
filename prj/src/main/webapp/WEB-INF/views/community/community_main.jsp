<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${List }" />
<c:set var="bestList" value="${bestList }" />
<c:set var="paging" value="${Paging }" />
<c:set var="UserId" value="${sessionScope.UserId }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO Community</title>
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" /> 
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/community.js"></script>
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
</head>
<body>
   <%@include file="../include/header03.jsp"%>
   <div id="main_container">
      <div class="header1"><h2>Community</h2></div>
      <div class="header2">자유로운 이야기 또는 활동 후기를 올려주세요!</div>
         <div class="communityListWrap">
            <table class="table">
               <tr>
                  <th>No.</th> <th>CATEGORY</th> <th>TITLE</th> <th>WRITER</th> <th>DATE</th> <th>HIT</th> <th>LIKE</th> 
               </tr>
               <c:if test="${!empty list }">
                  <c:forEach items="${list }" var="dto">
                     <tr>
                        <td>${dto.community_num }</td>
                        <td>${dto.community_head }</td>
                        <td class="community_title">
							<a id="community_title" href="<%=request.getContextPath() %>/community_content.do?no=${dto.community_num }&page=${paging.page }">
								${dto.community_title }
								<c:if test="${dto.community_reply > 0 }">
									<span>[${dto.community_reply }]</span>
								</c:if>
								<c:forEach items="${bestList }" var="bdto" begin="0" end="4">
									<c:if test="${bdto.community_title eq dto.community_title}">
										<span class="best_tag">BEST</span>
									</c:if>
								</c:forEach>
								<c:if test="${dto.community_regdate eq nowDate }">
									<span class="new_tag">NEW</span>
								</c:if>
								<c:if test="${dto.community_update eq nowDate }">
									<span class="new_tag">NEW</span>
								</c:if>
							</a>
                        </td>
                        <c:if test="${!empty dto.community_writer }">
                        	<td>
                           <%
								if (managerId != null) {
							%> 
									<a href="<%=request.getContextPath() %>/manager_look_user_info_view.do?user_nick_name=${dto.community_writer}">${dto.community_writer }</a>
							<%
								} else {
							%> 
									<a href="<%=request.getContextPath() %>/member_profile_view.do?user_nick_name=${dto.community_writer}">${dto.community_writer }</a>
							<%
								}
							%>
							</td>
                        </c:if>
                        <c:if test="${!empty dto.community_manager }">
                           <td>관리자</td>
                        </c:if>
                        <td>
                           <c:if test="${dto.community_update == '1000-01-01' }">
                              <span>${dto.community_regdate }</span>
                           </c:if>
                           <c:if test="${dto.community_update != '1000-01-01'  }">
                              <span>${dto.community_update }</span>
                           </c:if>
                        </td>
                        <td>${dto.community_hit }</td>
                        <td>${dto.community_like }</td>
                     </tr>
                  </c:forEach>
               </c:if>
               <c:if test="${empty list }">
                  <tr>
                     <td colspan="7" align="center">
                        <h3>전체 게시물이 존재하지 않습니다.</h3>
                     </td>
                  </tr>
               </c:if>
            </table>
            <div class="bestListWrap">
               <h1>실시간 인기글</h1>
               <div class="bestListInner">
                  <c:if test="${!empty bestList }">
                     <c:set var="i" value="1" />
                     <c:forEach items="${bestList }" var="bdto" begin="0" end="2">
						<p class="rank upRank">
							<em>${i }</em>
							<a href="<%=request.getContextPath() %>/community_content.do?no=${bdto.community_num }&page=${paging.page }">
							${bdto.community_title }</a>
						</p>
					<c:set var="i" value="${i+1 }" />
					</c:forEach>
					<c:forEach items="${bestList }" var="bdto" begin="3" end="9">
						<p class="rank">
							<em>${i }</em>
							<a href="<%=request.getContextPath() %>/community_content.do?no=${bdto.community_num }&page=${paging.page }">
							${bdto.community_title }</a>
						</p>
					<c:set var="i" value="${i+1 }" />
					</c:forEach>
                  </c:if>
                  <c:if test="${empty bestList }">
                  </c:if>
               </div>
            </div>
            <div class="ad">
               <img src="<%=request.getContextPath()%>/resources/images/ad_image.png" onclick="location.href='play_out_random.do'">
            </div>
         </div>
         <div id="buttonContainer">
            <button id="writing_button" onclick="location.href='community_write.do'">
               <span><i id="writing_button_icon" class="fa-regular fa-pen-to-square"></i>글쓰기</span>
            </button>
         </div>
      <!-- 페이징 처리 -->
      <div class="pageSearchWrap">
         <div class="pageContainer">
            <div class="page_nation">
               <c:if test="${paging.page > paging.block }">
                  <a class="arrow pprev" href="community_main.do?page=1"></a>
                  <a class="arrow prev" href="community_main.do?page=${paging.startBlock - 1 }"></a>
               </c:if>
               <c:forEach begin="${paging.startBlock }" end="${paging.endBlock }" var="i">
                  <c:if test="${i == paging.page }">
                     <a class="active" href="community_main.do?page=${i }">${i }</a>
                  </c:if>
                  <c:if test="${i != paging.page }">
                     <a href="community_main.do?page=${i }">${i }</a>
                  </c:if>
               </c:forEach>
               <c:if test="${paging.endBlock < paging.allPage }">
                  <a class="arrow next" href="community_main.do?page=${paging.endBlock + 1 }"></a>
                  <a class="arrow nnext" href="community_main.do?page=${paging.allPage }"></a>
               </c:if>
            </div>
         </div>
         <!-- 검색 -->
         <div class="searchContainer">
            <form method="post" action="<%=request.getContextPath() %>/community_search.do">
               <select class="search head" name="head">
                  <option value="noSelect">선택안함</option>
                  <option value="free">자유</option>
                  <option value="review">후기</option>
                  <option value="notice">공지</option>
               </select>
               <select class="search field" name="field">
                  <option value="all">전체</option>
                  <option value="title">제목</option>
                  <option value="cont">내용</option>
                  <option value="writer">작성자</option>
               </select>
               <input class="search keyword" name="keyword">
               <input class="searchButton" type="submit" value="검색">
            </form>
         </div>
      </div>
   </div>
   <%@include file="../include/footer.jsp"%>
</body>
</html>