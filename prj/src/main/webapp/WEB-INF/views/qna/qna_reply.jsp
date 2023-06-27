<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ManagerId" value="${sessionScope.ManagerId }" />
<c:set var="dto" value="${original_qna_dto }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의 작성 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/qna.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
	<%@include file="../include/header04.jsp"%>
	<div id="container">
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/qna_reply_ok.do">
			<input type="hidden" name="groupNo" value="${dto.qna_groupNo }">
			<input type="hidden" name="groupOrd" value="${dto.qna_groupOrd }">
			<div id="writingHeader">
				<h2>[관리자] 1:1 문의 답글 작성</h2>
			</div>
			<div id="writingContent">
				<div>
					<input name="qna_head" value="${dto.qna_head }">
				</div>
				<div>
					<input name="qna_title" value="${dto.qna_title }">
				</div>
				<div>
					<input name="qna_writer" value="관리자">
				</div>
				<div>
					<input name="qna_password" type="password" value="${dto.qna_password }">
				</div>
				<div>
					<div>문의 내용</div>
					<div>${dto.qna_content }</div>
					<img id="article_image" name="qna_file" src="<%=request.getContextPath()%>/resources/upload/qna/${dto.qna_file }">
				</div>
				<hr>
				<div>
					<div></div>
					<textarea name="qna_content" id="qna_content"></textarea>
				</div>
				<div>
					<input type="submit" value="답글 쓰기"> 
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../include/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/qna.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/summernote.js"></script>
	<script type="text/javascript">
		$('#qna_content').summernote('editor.insertText', ' ');
	</script>
</body>
</html>