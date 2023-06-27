<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cDto" value="${cDto}" />
<c:set var="mDto" value="${mDto}" />
<c:set var="cDtoList" value="${cDtoList}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 목록</title>
<link href="<%=request.getContextPath()%>/resources/css/manager/manager_code.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="../include/manager_header.jsp"%>
	
	<div id="main_container">
		<div class="header1"><h2>관리자 목록</h2></div>
			<div class="communityListWrap">
				<table class="table">
					<tr>
					   <th>ID</th><th>NAME</th><th>EMAIL</th>
					</tr>
			        <c:if test="${!empty mDto}">
			        	<c:forEach items="${mDto}" var="mDto">
			        		<tr>
								<td>${mDto.manager_id}</td>
								<td>${mDto.manager_name}</td>
								<td>${mDto.manager_email}</td>
							</tr>
						</c:forEach>
					</c:if>
					<tr>
						<th>THE OTHERS</th>
					</tr>
					<c:if test="${!empty cDtoList }">
						<c:forEach items="${cDtoList}" var="cDtoList">
			        		<tr>
								<td><a id="community_title" href="<%=request.getContextPath() %>/manager_code.do?manager_code=${cDtoList.manager_code}">${cDtoList.manager_code}</a></td>
								<td>${cDtoList.manager_code_name}</td>
							</tr>
						</c:forEach>
					</c:if>
			        <c:if test="${empty mDto }">
			        	<tr>
			            	<td colspan="2" align="center">
			                	<h3>관리자가 존재하지 않습니다.</h3>
			            	</td>
			       		</tr>
			        </c:if>
			         <c:if test="${empty cDto }">
			        	<tr>
			            	<td colspan="2" align="center">
			                	<h3>관리자 코드가 존재하지 않습니다.</h3>
			            	</td>
			       		</tr>
			        </c:if>
			         <c:if test="${empty cDtoList }">
			        	<tr>
			            	<td colspan="2" align="center">
			                	<h3>다른 관리자 코드가 존재하지 않습니다.</h3>
			            	</td>
			       		</tr>
			        </c:if>
      			</table>
      		</div>
      	</div>
	<%@include file="../include/manager_footer.jsp"%>
</body>
</html>