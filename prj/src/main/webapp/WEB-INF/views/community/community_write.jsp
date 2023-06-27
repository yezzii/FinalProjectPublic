<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="mdto" value="${mdto }" />
<c:set var="ManagerId" value="${sessionScope.ManagerId }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODO 커뮤니티 글쓰기</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
	<%@include file="../include/header03.jsp"%>
	<div id="container">
		<div class="header1"><h2>Community 게시글 작성하기</h2></div>
		<div class="header2">활동 후기 및 자유로운 이야기를 작성해주세요!</div>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/community_write_ok.do" onsubmit="return writeFormCheck()">
			<div id="writingContent">
				<table>
					<c:if test="${empty ManagerId }">
						<input type="hidden" name="writer_id" value="${mdto.id }">
						<tr>
							<th>CATEGORY</th>
							<td>
								<select id="community_head" name="community_head">
									<option value="자유">[자유]</option>
									<option value="후기">[후기]</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>WRITER</th>
							<td><input class="inputBox" id="community_writer" name="community_writer" value="${mdto.nick_name }" readonly></td>
						</tr>
						<tr>
							<th>DATE</th>
							<td><input class="inputBox" id="writingDate" value="${serverTime.substring(0,11) }" readonly></td>
						</tr>
					</c:if>
					<c:if test="${!empty ManagerId }">
						<input type="hidden" name="writer_id" value="${mdto.manager_id }">
						<input type="hidden" name="community_manager" value="${mdto.manager_id }">
						<tr>
							<th>CATEGORY</th>
							<td>
								<input class="inputBox" name="community_head" value="공지">
							</td>
						</tr>
						<tr>
							<th>WRITER</th>
							<td><input class="inputBox" id="community_writer" value="관리자" readonly></td>
						</tr>
						<tr>
							<th>DATE</th>
							<td><input class="inputBox" id="writingDate" value="${serverTime.substring(0,11) }" readonly></td>
						</tr>
					</c:if>
					<tr>
						<th>TITLE</th>
						<td><input class="inputBox" id="community_title" name="community_title" placeholder="제목을 입력해 주세요."></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="community_content" id="community_content" cols="5" rows="5" class="form-control"></textarea>
						</td>
					</tr>
					<tr>
						<th>IMAGE</th>
						<td>
							<div class="writingFile">
								<input type="file" name="file" id="file_input_hidden">
								<label class="file_label" for="file_input_hidden">파일선택</label>
								<span id="fileName"></span>
							</div>
							<img id="preview" style="display: none; height: 200px;" />
						</td>
					</tr>
				</table>
			</div>
			<div id="writingHeader">
				<input type="submit" id="writingSubmit" value="글쓰기">
			</div>
		</form>
	</div>
	<%@ include file="../include/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/community.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/summernote.js"></script>
</body>
</html>