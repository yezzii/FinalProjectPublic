<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userNickName" value="${userNickName}"></c:set>
<c:set var="list" value="${userCommunityReplyInfo}" />
<c:set var="paging" value="${Paging }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userNickName}님의 Reply</title>
<link href="<%=request.getContextPath()%>/resources/css/community.css" rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/618ef9578a.js" crossorigin="anonymous"></script>
<style>
.communityListWrap {
	margin-left: 250px;
}

.pageContainer1 {
	text-align: center;
	font-size: 0;
	padding: 20px 0;
	border-bottom: 1px solid #B3B3B3;
	width: 840px;
}
</style>
</head>
<body>
	<%@include file="../include/header05.jsp"%>
	<div id="main_container">
	<div class="header1"><h2 align="center">${userNickName}님의 Reply</h2></div>
		<div class="communityListWrap">
			<table class="table">
				<tr>
					<th colspan="3">TITLE</th><th>DATE</th><th>HIT</th>
				</tr>
       	 		<c:if test="${!empty list }">
        			<c:forEach items="${list }" var="dto">
		        		<tr>
							<td>${dto.written_num }</td>
							<td>${dto.written_head }</td>
							<td class="community_title">
								<a id="community_title" href="<%=request.getContextPath() %>/community_content.do?no=${dto.written_num}&page=${paging.page }">
									${dto.written_title}
									<c:if test="${dto.written_reply > 0 }">
										<span>
											[${dto.written_reply }]
										</span>
									</c:if>
									<c:if test="${dto.written_hit > 100 }">
										<span class="best_tag">BEST</span>
									</c:if>
									<c:if test="${dto.written_regdate eq nowDate }">
										<span class="new_tag">NEW</span>
									</c:if>
									<c:if test="${dto.written_update eq nowDate }">
										<span class="new_tag">NEW</span>
									</c:if>
								</a>
							</td>
							<td>
								<c:if test="${dto.written_update == '1000-01-01' }">
									<span>${dto.written_regdate }</span>
								</c:if>
								<c:if test="${dto.written_update != '1000-01-01'  }">
									<span>${dto.written_update }</span>
								</c:if>
							</td>
							<td>${dto.written_hit }</td>
						</tr>
				</c:forEach>
			</c:if>
	        <c:if test="${empty list }">
	        	<tr>
	            	<td colspan="7" align="center">
	                	<h3>댓글 내역이 존재하지 않습니다.</h3>
	            	</td>
	       		</tr>
        	</c:if>
     	</table>
    <div class="pageSearchWrap">
		<div class="pageContainer1">
			<div class="page_nation">
				<c:if test="${paging.page > paging.block }">
							<a class="arrow pprev" href="member_community_reply_profile_view.do?page=1"></a>
							<a class="arrow prev" href="member_community_reply_profile_view.do?page=${paging.startBlock - 1 }"></a>
						</c:if>
						<c:forEach begin="${paging.startBlock }" end="${paging.endBlock }" var="i">
							<c:if test="${i == paging.page }">
								<a class="active" href="member_community_reply_profile_view.do?page=${i }">${i }</a>
							</c:if>
							<c:if test="${i != paging.page }">
								<a href="member_community_reply_profile_view.do?page=${i }">${i }</a>
							</c:if>
						</c:forEach>
						<c:if test="${paging.endBlock < paging.allPage }">
							<a class="arrow next" href="cmember_community_reply_profile_view.do?page=${paging.endBlock + 1 }"></a>
							<a class="arrow nnext" href="member_community_reply_profile_view.do?page=${paging.allPage }"></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>