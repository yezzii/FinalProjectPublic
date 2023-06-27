<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${completionUserList}" />
<c:set var="paging" value="${Paging }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 완료된 유저 목록</title>
<link href="<%=request.getContextPath()%>/resources/css/manager/manager_code.css" rel="stylesheet" type="text/css" /> 
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="../include/manager_header.jsp"%>
	<div id="main_container">
		<div class="header1"><h2>신고 완료된 유저 목록</h2></div>
	    	<div class="communityListWrap">
	        	<table class="table">
					<tr>
					   <th>신고한 유저</th>
					   <th>신고 글</th> 
					   <th>신고 접수된 유저</th>
					   <th>처리 상태</th>
					</tr>
	        	<c:if test="${!empty list}">
	        		<c:forEach items="${list}" var="dto">
		        		<tr>
							<td>${dto.member_id}</td>
							<td><a id="community_title" href="<%=request.getContextPath() %>/qna_content.do?no=${dto.qna_num}&page=${paging.page }">${dto.qna_title}</a></td>
							<td><a href="<%=request.getContextPath() %>/manager_look_user_info_view.do?user_nick_name=${dto.reported_user}">${dto.reported_user}</a></td>
							<c:if test="${dto.completion_report == 0}">
								<td><a href="<%=request.getContextPath() %>/manager_completion_report.do?user_nick_name=${dto.reported_user}">처리중</a></td>
							</c:if>
							<c:if test="${dto.completion_report == 1}">
								<td><a href="<%=request.getContextPath() %>/manager_completion_reset.do?user_nick_name=${dto.reported_user}">처리완료</a></td>
							</c:if>
							<c:if test="${dto.completion_report == 2}">
								<td>계정 복구 완료</td>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
		        <c:if test="${empty list }">
		        	<tr>
		            	<td colspan="2" align="center">
		                	<h3>신고 완료된 내역이 존재하지 않습니다.</h3>
		            	</td>
		       		</tr>
		        </c:if>
      		</table>
      		<div class="pageSearchWrap">
				<div class="pageContainer">
					<div class="page_nation">
						<c:if test="${paging.page > paging.block }">
							<a class="arrow pprev" href="manager_reset_user_list.do?page=1"></a>
							<a class="arrow prev" href="manager_reset_user_list.do?page=${paging.startBlock - 1 }"></a>
						</c:if>
						<c:forEach begin="${paging.startBlock }" end="${paging.endBlock }" var="i">
							<c:if test="${i == paging.page }">
								<a class="active" href="manager_reset_user_list.do?page=${i }">${i }</a>
							</c:if>
							<c:if test="${i != paging.page }">
								<a href="manager_reset_user_list.do?page=${i }">${i }</a>
							</c:if>
						</c:forEach>
						<c:if test="${paging.endBlock < paging.allPage }">
							<a class="arrow next" href="manager_reset_user_list.do?page=${paging.endBlock + 1 }"></a>
							<a class="arrow nnext" href="manager_reset_user_list.do?page=${paging.allPage }"></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../include/manager_footer.jsp"%>
</body>
</html>