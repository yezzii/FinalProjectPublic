<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="list" value="${actionList}" />
<c:set var="dto" value="${dto}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.manager_id}님의 관리자 활동 내역</title>
<link href="<%=request.getContextPath()%>/resources/css/manager/manager_code.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="../include/manager_header.jsp"%>
		
	<div id="main_container">
		<div class="header1"><h2>${dto.manager_id}님의 관리자 활동 내역</h2></div>
			<div class="communityListWrap">
				<table class="table">
					<tr>
					   <th>No.</th><th>CONTENT</th><th>DATE</th>
					</tr>
			        <c:if test="${!empty list}">
			        	<c:forEach items="${list}" var="actionDto">
			        		<tr>
								<td>${actionDto.manager_action_index}</td>
								<td>${actionDto.manager_action_content}</td>
								<td>${actionDto.manager_action_date}</td>
							</tr>
						</c:forEach>
					</c:if>
			        <c:if test="${empty list }">
			        	<tr>
			            	<td colspan="2" align="center">
			                	<h3>활동 내역이 존재하지 않습니다.</h3>
			            	</td>
			       		</tr>
			        </c:if>
      			</table>
      		</div>
      	</div>
	<%@include file="../include/manager_footer.jsp"%>
</body>
</html>