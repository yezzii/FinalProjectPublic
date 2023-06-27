<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${Delete }" />
<c:set var="mdto" value="${mdto }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${dto.community_head }] ${dto.community_title } : TODO 커뮤니티</title>
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
	<%@include file="../include/header03.jsp"%>
	<div id="container">
		<div id="pwdCheckContainer">
			<form method="post" action="<%=request.getContextPath() %>/community_delete_ok.do" onsubmit="return deleteFormCheck()">
				<div id="pwdCheckHeader">
					<h2>비밀번호 확인&nbsp;&nbsp;|&nbsp;&nbsp;[${dto.community_head }] ${dto.community_title }</h2>
				</div>
				<input type="hidden" name="community_num" value="${dto.community_num }">
				<input type="hidden" name="db_pwd" value="${mdto.password }">
				<input type="hidden" name="page" value="${Page }">
				<div class="pwdCheckContent">
					<div class="pwdCheckMent">
						해당 게시글을 삭제하기 위해 본인 인증이 필요합니다.<br>
						${mdto.nick_name }님의 계정 비밀번호를 입력하세요.
					</div>
					<div class="pwdCheckInput">
						<input type="password" name="member_pwd" id="member_pwd" placeholder="PASSWORD">
					</div>
				</div>
				<div class="pwdCheckButton">
					<input type="submit" value="비밀번호 확인">
					<input type="reset" value="다시 작성">
				</div>
			</form>
			<a id="goBackHref" href="community_main.do"><i class="fa-solid fa-arrow-left" style="color: #000000;"></i> COMMUNITY 게시판으로 돌아가기</a>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>