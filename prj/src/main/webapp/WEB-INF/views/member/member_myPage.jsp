<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<div><a href="<%=request.getContextPath()%>/member_community_list.do">내가 쓴 글 보기</a></div>
					<div><a href="<%=request.getContextPath()%>/member_community_list.do">${communityCount}</a>개</div>
				</div>
				<div class="member-reply-count">
					<div><a href="<%=request.getContextPath()%>/member_reply_list.do">내가 쓴 댓글 보기</a></div>
					<div><a href="<%=request.getContextPath()%>/member_reply_list.do">${memberReplyCount}</a>개</div>
				</div>
				<div class="member-reply-count">
					<div><a href="<%=request.getContextPath()%>/member_like_list.do">내가 추천한 글 보기</a></div>
					<div><a href="<%=request.getContextPath()%>/member_like_list.do">${memberLikeCount}</a>개</div>
				</div>
				<div class="member-play-list-wrap">
					<div><a href="<%=request.getContextPath()%>/member_play_list.do">내 찜 내역 보기</a></div>
					<div><a href="<%=request.getContextPath()%>/member_play_list.do">${playCount}</a>개</div>
				</div>
				<div class="member_point_list">
					<div><a href="<%=request.getContextPath()%>/member_point_list.do">내 포인트내역 보기 </a></div>
					<div><a href="<%=request.getContextPath()%>/member_point_list.do">${dto.point}</a>포인트</div>
				</div>
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
								<a href="<%=request.getContextPath()%>/member_reply_list.do">작성 댓글 </a>
							</p>
							<p>
								<a href="<%=request.getContextPath()%>/member_reply_content_list.do">댓글단 글</a>
							</p>
							<p>
								 <a href="<%=request.getContextPath()%>/member_like_list.do">좋아요한 글</a>
							</p>
						</div>
						<div class="my-content-con"></div>
						<div class="insert-btn-con">
							<a href="<%=request.getContextPath()%>/member_befordModify.do">개인정보수정</a>
							<a href="<%=request.getContextPath()%>/member_delete.do">회원탈퇴</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../include/footer_my_page.jsp"%>
</body>
</html>