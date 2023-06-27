<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${sCont }" />
<c:set var="UserId" value="${sessionScope.UserId }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 검색 결과 상세 페이지</title>
</head>
<body>
	<%@include file="../include/header03.jsp"%>
	<div id="container">
		<div id="articlelHeader">
			<div id="article_title">
				<h3>[${dto.community_head }] ${dto.community_title }</h3>
			</div>
			<div id="profile_info">
				<button id="article_writer">${dto.community_writer }</button>
			</div>
			<div id="article_info">
				<c:if test="${dto.community_update == '1000-01-01' }">
					<span>${dto.community_regdate }</span>
				</c:if>
				<c:if test="${dto.community_update != '1000-01-01'  }">
					<span>${dto.community_update }</span>
				</c:if>
				<span>조회 ${dto.community_hit }</span>
			</div>
		</div>
		<div id="articleContent">
			<div id="article_content">${dto.community_content }</div>
			<div>
				<img id="article_image" src="<%=request.getContextPath()%>/resources/upload/${uploadFolder }/${dto.community_file }">
			</div>
		</div>
		<div id="articleFooter">
			<div id="writer_community">
				<a>${dto.community_writer }님의 게시글 더보기</a>
			</div>
			<div id="article_like">
				<c:if test="${likeCheck > 0 }">
					<span class="articlelikeCheck"> 
						<img class="like_img" id="like" onclick="likeDelete(this, ${dto.getCommunity_num() })" src="<%=request.getContextPath()%>/resources/assets/play_pic/like_heart_fill.png">
					</span>
				</c:if>
				<c:if test="${likeCheck == 0}">
					<span class="articlelikeCheck"> 
						<img class="like_img" id="unlike" onclick="likeInsert(this, ${dto.getCommunity_num() })" src="<%=request.getContextPath()%>/resources/assets/play_pic/like_heart.png">
					</span>
				</c:if>
				좋아요 <span id="likeCount">${likeCount}</span>
			</div>
		</div>
		<c:if test="${!empty UserId }">
			<div id="article_btn">
				<input type="button" value="수정" onclick="location.href='community_modify.do?no=${dto.community_num}&page=${Page }'">
				<input type="button" value="삭제" onclick="location.href='community_delete.do?no=${dto.community_num}&page=${Page }'">
				<input type="button" value="전체 목록" onclick="location.href='community_main.do?page=${Page }'">
			</div>
		</c:if>
		<c:if test="${empty UserId }">
			<div id="article_btn">
				<input type="button" value="검색 목록" onclick="location.href='community_search.do?page=${Page }&head=${head}&field=${Field}&keyword=${Keyword}'">
			</div>
		</c:if>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>