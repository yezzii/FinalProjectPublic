<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${Cont }" />
<c:set var="page" value="${Page }" />
<c:set var="ManagerId" value="${sessionScope.ManagerId }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${dto.qna_head }] ${dto.qna_title } : TODO QnA 게시판</title>
<link href="<%=request.getContextPath()%>/resources/css/qna.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js"></script>
</head>
<body>
	<%@include file="../include/header04.jsp"%>
	<div id="container">
		<div id="article_container">
			<div id="articlelHeader">
				<div id="article_title">
					<span id="article_head_tag">${dto.qna_head }</span>
					<h3>${dto.qna_title }</h3>
				</div>
				<div id="profile_info">
					<span>${dto.qna_writer }</span>
				</div>
				<div id="article_info_wrap">
					<c:if test="${dto.qna_update == '1000-01-01' }">
						<span>${dto.qna_regdate }</span>
					</c:if>
					<c:if test="${dto.qna_update != '1000-01-01'  }">
						<span>${dto.qna_update }</span>
					</c:if>
				</div>
			</div>
			<div id="articleContent">
				<div id="article_content">${dto.qna_content }</div>
				<c:if test="${dto.qna_writer != '관리자' }">
					<c:if test="${!empty dto.qna_file }">
						<img id="article_image" src="<%=request.getContextPath()%>/resources/upload/qna/${dto.qna_file }">
					</c:if>
				</c:if>
			</div>
			<div id="article_btn">
				<!-- 원글인 경우 -->
				<c:if test="${dto.qna_depthNo == 0 }">
					<!-- 관리자 계정으로 로그인한 경우: 답글달기 가능  -->
					<c:if test="${!empty ManagerId }">
						<input type="button" class="content_button" value="답글 작성" onclick="location.href='qna_reply.do?no=${dto.qna_num}&group_no=${dto.qna_groupNo}&group_ord=${dto.qna_groupOrd }&depth_no=${dto.qna_depthNo }'">
					</c:if>
					<input type="button" class="content_button" value="수정" onclick="location.href='qna_modify.do?no=${dto.qna_num}&page=${page }'">
					<input type="button" class="content_button" value="삭제" onclick="qnaDelete(${dto.qna_num})">
					<input type="button" class="content_button" value="전체 목록" onclick="location.href='qna_board.do'">
				</c:if>
				<!-- 답글인 경우 -->
				<c:if test="${dto.qna_depthNo == 1 }">
					<c:if test="${!empty ManagerId }">
						<input type="button" class="content_button" value="수정" onclick="location.href='qna_modify.do?no=${dto.qna_num}&page=${page }'">
						<input type="button" class="content_button" value="삭제" onclick="qnaDelete(${dto.qna_num})">
					</c:if>
					<input type="button" class="content_button" value="전체 목록" onclick="location.href='qna_board.do'">
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>