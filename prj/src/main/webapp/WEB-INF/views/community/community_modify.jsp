<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${Modify }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${dto.community_head }] ${dto.community_title } : TODO 커뮤니티</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="../include/header03.jsp"%>
	<div id="container">
		<div class="header1"><h2>Community 게시글 수정하기</h2></div>
		<div class="header2">활동 후기 및 자유로운 이야기를 작성해주세요!</div>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/community_modify_ok.do">
			<input type="hidden" name="community_num" value="${dto.community_num }">
			<input type="hidden" name="page" value="${Page }">			
			<div id="writingContent">
				<table>
					<tr>
						<th>CATEGORY</th>
						<td>
							<c:if test="${dto.community_head != '공지'}">
								<select id="community_head" name="community_head">
									<option value="자유">[자유]</option>
									<option value="후기">[후기]</option>
								</select>
							</c:if>
							<c:if test="${dto.community_head == '공지'}">
								<select id="community_head" name="community_head">
									<option value="공지">[공지]</option>
								</select>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>WRITER</th>
						<c:if test="${!empty dto.community_writer}">
							<input type="hidden" name="writer_id" value="${mdto.id }">
							<td><input class="inputBox" id="community_writer" name="community_writer" value="${dto.community_writer }" readonly></td>
						</c:if>
						<c:if test="${empty dto.community_writer}">
							<input type="hidden" name="writer_id" value="${mdto.manager_id }">
							<input type="hidden" name="community_manager" value="${mdto.manager_id }">
							<td><input class="inputBox" id="community_writer" name="community_writer" value="관리자" readonly></td>
						</c:if>
					</tr>
					<tr>
						<th>DATE</th>
						<td>
							<c:if test="${dto.community_update == '1000-01-01'}">
								<input class="inputBox" id="community_regdate" name="community_writer" value="${dto.community_regdate }" readonly>
							</c:if>
							<c:if test="${dto.community_update != '1000-01-01'}">
								<input class="inputBox" id="community_update" name="community_writer" value="${dto.community_update }" readonly>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>TITLE</th>
						<td><input class="inputBox" id="community_title" name="community_title" value="${dto.community_title }" placeholder="제목을 입력해 주세요."></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="community_content" id="community_content" cols="" rows="" class="form-control">${dto.community_content }</textarea>
						</td>
					</tr>
					<tr>
						<th>IMAGE</th>
						<td>
							<div class="writingFile">
								<input type="file" name="file" id="file_input_hidden">
								<label class="file_label" for="file_input_hidden">파일선택</label>
								<span id="fileName">${dto.community_file }</span>
							</div>
							<img id="preview" style="display: none; height: 200px;" src="<%=request.getContextPath()%>/resources/upload/community/${uploadFolder }/${dto.community_file }" />
						</td>
					</tr>
				</table>
			</div>
			<div id="writingHeader">
				<input type="submit" id="writingSubmit" value="수정">
			</div>
		</form>
	</div>
	<%@include file="../include/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/community.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/summernote.js"></script>
	<script type="text/javascript">
		$('#community_content').summernote('editor.insertText', ' ');
	</script>
</body>
</html>