<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${List }" />
<c:set var="paging" value="${Paging }" />
<c:set var="UserId" value="${sessionScope.UserId }" />
<c:set var="ManagerId" value="${sessionScope.ManagerId }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO QnA 게시판</title>
<link href="<%=request.getContextPath()%>/resources/css/qna.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
	<%@include file="../include/header04.jsp"%>
	<div id="container">
		<div class="header1"><h2>Question & Answer</h2></div>
		<div class="header2">사이트 문의사항 혹은 그 외에도 궁금한 점이 있으시다면 질문해주세요! 관리자가 정성껏 답변해드립니다!</div>
		<table class="table">
			<tr>
				<th>No.</th> <th>CATEGORY</th> <th>TITLE</th> <th>WRITER</th> <th>DATE</th> <th>STATE</th> 
			</tr>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.qna_num }</td>
						<td>${dto.qna_head }</td>
						<td class="qna_title">
						<!-- 원글인 경우 -->
						<c:if test="${dto.qna_depthNo == 0}">
							<!-- 관리자 계정 -->
							<c:if test="${!empty ManagerId }">
								<a href="<%=request.getContextPath() %>/qna_content.do?no=${dto.qna_num }&page=${Paging.page }">${dto.qna_title }</a>
							</c:if>
							<!-- 일반 계정 OR 비회원 -->
							<c:if test="${empty ManagerId }">
								<img alt="" src="<%=request.getContextPath()%>/resources/qna/lock_icon.png" width="15px">
								<a href="<%=request.getContextPath() %>/qna_pwdCheck.do?no=${dto.qna_num }&page=${Paging.page }">${dto.qna_title }</a>
							</c:if>
						</c:if>
						<!-- 답글인 경우 -->
						<c:if test="${dto.qna_depthNo > 0}">
							<span style="color:red; padding-left:${dto.qna_depthNo*20}px;">└ Re</span>
							<!-- 관리자 계정 -->
							<c:if test="${!empty ManagerId }">
								<a href="<%=request.getContextPath() %>/qna_content.do?no=${dto.qna_num }&page=${Paging.page }">${dto.qna_title }</a>
							</c:if>
							<!-- 일반 계정 OR 비회원 -->
							<c:if test="${empty ManagerId }">
								<img alt="" src="<%=request.getContextPath()%>/resources/qna/lock_icon.png" width="15px">
								<a href="<%=request.getContextPath() %>/qna_pwdCheck.do?no=${dto.qna_num }&page=${Paging.page }">${dto.qna_title }</a>
							</c:if>
						</c:if>
						</td>
						<td>${dto.qna_writer }</td>
						<td>${dto.qna_regdate }</td>
						<td>
							<c:if test="${dto.qna_writer == '관리자' }">
								<span class="tag qna_reply">답변글</span>
							</c:if>
							<c:if test="${dto.qna_writer != '관리자' }">
								<c:if test="${dto.qna_state == 0 }">
									<span class="tag qna_reply_wait">답변대기</span>
								</c:if>
								<c:if test="${dto.qna_state == 1 }">
									<span class="tag qna_reply_ok">답변완료</span>
								</c:if>
							</c:if>
						</td>
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
		<div id="buttonContainer">
			<button id="writing_button" onclick="location.href='qna_write.do'">
				<span><i id="writing_button_icon" class="fa-regular fa-pen-to-square"></i>글쓰기</span>
			</button>
		</div>
		<!-- 페이징 처리 -->
		<div class="pageSearchWrap">
			<div class="pageContainer">
				<div class="page_nation">
					<c:if test="${paging.page > paging.block }">
						<a class="arrow pprev" href="qna_board.do?page=1"></a>
						<a class="arrow prev" href="qna_board.do?page=${paging.startBlock - 1 }">  </a>
					</c:if>
					<c:forEach begin="${paging.startBlock }" end="${paging.endBlock }" var="i">
						<c:if test="${i == paging.page }">
							<a class="active" href="qna_board.do?page=${i }">${i }</a>
						</c:if>
						<c:if test="${i != paging.page }">
							<a href="qna_board.do?page=${i }">${i }</a>
						</c:if>
					</c:forEach>
					<c:if test="${paging.endBlock < paging.allPage }">
						<a class="arrow next" href="qna_board.do?page=${paging.endBlock + 1 }"></a>
						<a class="arrow nnext" href="qna_board.do?page=${paging.allPage }"></a>
					</c:if>
				</div>
			</div>
		</div>
		<!-- 검색 -->
		<div class="searchContainer">
			<form method="post" action="<%=request.getContextPath() %>/qna_search.do">
				<select class="search head" name="head">
					<option value="선택안함">선택안함</option>
					<option value="회원 가입 및 계정 관리">회원 가입 및 계정 관리</option>
					<option value="사용자 신고">사용자 신고</option>
					<option value="포인트 관련">포인트 관련</option>
					<option value="사이트 사용법">사이트 사용법</option>
					<option value="커뮤니티">커뮤니티</option>
					<option value="기타">기타</option>
				</select>
				<select class="search field" name="field">
					<option value="all">전체</option>
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="writer">작성자</option>
				</select>
				<input class="search keyword" name="keyword">
				<input class="searchButton" type="submit" value="검색">
			</form>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>