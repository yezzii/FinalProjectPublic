<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="nick_name" value="${nick_name}" />
<c:set var="list" value="${memberPointList}" />
<c:set var="dto" value="${dto}" />
<c:set var="communityCount" value="${memberCommunityCount}" />
<c:set var="playCount" value="${memberPlayLikeCount}" />
<c:set var="memberReplyCount" value="${memberReplyCount}" />
<c:set var="memberReplyContentCount" value="${memberReplyContentCount}" />
<c:set var="memberLikeCount" value="${memberLikeCount}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
<link rel="stylesheet" href="./resources/css/member/member_myPage.css" />
<link href="<%=request.getContextPath()%>/resources/css/community.css"
	rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/618ef9578a.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<%@include file="../include/header_myPage.jsp"%>
	<div align="center" class="container">
		<h3 align="center">My Page</h3>

		<div class="my-page-all-con">
			<div class="my-page-left-con">
				<div>${dto.nick_name}
					<div class="insert-wrap">
						<div class="member-insert-date">가입</div>
						<div class="member-insert-date">${dto.date}</div>
					</div>

				</div>

				<div class="member-community-count-wrap">
					<div>
						<a href="<%=request.getContextPath()%>/member_community_list.do">내가
							쓴 글 보기</a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/member_community_list.do">${communityCount}</a>개
					</div>
				</div>
				<div class="member-reply-count">
					<div>
						<a href="<%=request.getContextPath()%>/member_reply_list.do">내가
							쓴 댓글 보기</a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/member_reply_list.do">${memberReplyCount}</a>개
					</div>
				</div>
				<div class="member-reply-count">
					<div>
						<a href="<%=request.getContextPath()%>/member_like_list.do">내가
							추천한 글 보기</a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/member_like_list.do">${memberLikeCount}</a>개
					</div>
				</div>
				<div class="member-play-list-wrap">
					<div>
						<a href="<%=request.getContextPath()%>/member_play_list.do">내
							찜 내역 보기</a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/member_play_list.do">${playCount}</a>개
					</div>
				</div>
				<div class="member_point_list">
					<div>
						<a style="color: salmon;"
							href="<%=request.getContextPath()%>/member_point_list.do">내
							포인트내역 보기 </a>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/member_point_list.do">${dto.point}</a>포인트
					</div>
				</div>
				<a href="<%=request.getContextPath()%>/member_befordModify.do">개인정보수정</a>
			</div>


			<div class="my-page-right-con">
				<div class="my-page-sub-right-con">
					<div class="my-page-right-wrap">
						<div class="my-page-sub-right-wrap">
							<div id="activityLog"></div>
							<p>
								<a href="<%=request.getContextPath()%>/member_community_list.do">작성글</a>
							</p>
							<p>
								<a href="<%=request.getContextPath()%>/member_reply_list.do">작성
									댓글 </a>
							</p>
							<p>
								<a
									href="<%=request.getContextPath()%>/member_reply_content_list.do">댓글단
									글</a>
							</p>
							<p>
								<a href="<%=request.getContextPath()%>/member_like_list.do">좋아요한
									글</a>
							</p>
						</div>
						<div class="my-content-con">
							<br> <a href="<%=request.getContextPath()%>/qna_main.do">포인트
								적립/사용 안내 바로가기</a>
							<div class="communityListWrap">
								<table class="table">
									<tr>
										<th>CONTENT</th>
										<th colspan="2">POINT</th>
										<th>DATE</th>
									</tr>
									<c:if test="${!empty list }">
										<c:forEach items="${list }" var="dto">
											<tr>
												<td>${dto.point_content}</td>
												<td>${dto.point_sign}&nbsp;${dto.point_score}</td>
												<td>${dto.point_remanet}</td>
												<td>${dto.point_date}</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty list }">
										<tr>
											<td colspan="7" align="center">
												<h3>포인트 내역이 존재하지 않습니다.</h3>
											</td>
										</tr>
									</c:if>
								</table>
							</div>
						</div>
					</div>
					<div class="insert-btn-con">
						<a href="<%=request.getContextPath()%>/member_delete.do">회원탈퇴</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../include/footer_my_page.jsp"%>

</body>
</html>