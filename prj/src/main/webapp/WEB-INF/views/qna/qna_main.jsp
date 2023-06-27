<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="faq_list" value="${faq_list }" />
<c:set var="faq_clist" value="${faq_clist }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 페이지</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/qna.css"/> 
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js" ></script>
</head>
<body>
	<%@include file="../include/header04.jsp"%>
	<div id="container">
		<div id="qna_header2">
			<div id="qna_title2">자주 묻는 질문</div>
		</div>
		<div id="category">
			<span class="category_title" onclick="location.href='qna_main.do'">전체</span>
			<span class="category_title" onclick="qnaCategory(1)">회원 가입 및 계정 관리</span>
			<span class="category_title" onclick="qnaCategory(2)">사용자 신고</span>
			<span class="category_title" onclick="qnaCategory(3)">포인트 관련</span>
			<span class="category_title" onclick="qnaCategory(4)">사이트 사용법</span>
			<span class="category_title" onclick="qnaCategory(5)">커뮤니티</span>
			<span class="category_title" onclick="qnaCategory(6)">기타</span>
		</div>
		<div id="qna_list">
			<c:forEach var="i" begin="1" end="6">
				<c:if test="${!empty faq_clist }">
					<c:forEach items="${faq_clist }" var="faqCDto">
						<c:if test="${faqCDto.faq_category_num eq i }">
							<c:if test="${!empty faq_list }">
								<c:forEach items="${faq_list }" var="faqDto">
									<c:if test="${faqDto.faq_category eq faqCDto.faq_category_num }">
										<div class="faq_wrap">
											<div class="faq_title" onclick="faqShow(this)">
												<div>
													<span class="faq_category_title">${faqCDto.faq_category_name }</span>
													<span class="faq_content_title">${faqDto.faq_title }</span>
												</div>
												<i class="faq_icon fa-solid fa-chevron-down"></i>
											</div>
											<div class="faq_content">
												<div class="faq_content_detail">${faqDto.faq_content }</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
		<div id="qna_button_wrap">
			<input type="button" class="qna_button" value="1:1 문의하기" onclick="location.href='qna_write.do'">
			<input type="button" class="qna_button" value="1:1 문의 게시판 보러가기" onclick="location.href='qna_board.do'">
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>