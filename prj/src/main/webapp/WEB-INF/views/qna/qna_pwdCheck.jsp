<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${qnaDto }" />
<c:set var="page" value="${Page }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${dto.qna_head }] ${dto.qna_title } : TODO qna</title>
<link href="<%=request.getContextPath()%>/resources/css/qna.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js"></script>
</head>
<body>
<%@include file="../include/header04.jsp"%>
	<div id="container">
		<div id="pwdCheckContainer">
			<form method="post" action="<%=request.getContextPath() %>/qna_pwdCheckOk.do">
				<input type="hidden" name="qna_num" value="${dto.qna_num }">
				<input type="hidden" name="db_pwd" value="${dto.qna_password }">
				<input type="hidden" name="page" value="${page }">
				<div class="pwdCheckHeader">
					<h2>비밀번호 확인</h2>
					[${dto.qna_head }] ${dto.qna_title }
				</div>
				<div class="pwdCheckContent">
					<div class="pwdCheckMent">
						해당 문의글을 확인하기 위해 본인 인증이 필요합니다.<br>
						문의글 작성시 설정하셨던 ${dto.qna_writer }님의 문의글 비밀번호를 입력하세요.
					</div>
					<div class="pwdCheckInput">
						<input type="password" name="qna_password" placeholder="PASSWORD">
					</div>
				</div>
				<div class="pwdCheckButton">
					<input type="submit" value="비밀번호 확인">
					<input type="reset" value="다시 작성">
				</div>
			</form>
			<a id="goBackHref" href="qna_board.do"><i class="fa-solid fa-arrow-left" style="color: #000000;"></i> Q&A 게시판으로 돌아가기</a>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>