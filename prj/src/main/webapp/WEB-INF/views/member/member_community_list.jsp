<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="id" value="${sessionScope.UserId}" />
<c:set var="nick_name" value="${nick_name}" />
<c:set var="list" value="${memberCommunityList}" />
<c:set var="dto" value="${dto}" />
<c:set var="paging" value="${Paging }" />
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
<link href="<%=request.getContextPath()%>/resources/css/community.css"
	rel="stylesheet" type="text/css" />
<script src="https://kit.fontawesome.com/618ef9578a.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./resources/css/member/footer_member.css" />
<link rel="stylesheet" href="./resources/css/member/member_myPage.css" />
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
						<a href="<%=request.getContextPath()%>/member_point_list.do">내
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
								<a style="color: salmon;"
									href="<%=request.getContextPath()%>/member_community_list.do">작성글</a>
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
							<table class="table">
								<tr>
									<th colspan="3">TITLE</th>
									<th>DATE</th>
									<th>HIT</th>
								</tr>
								<c:if test="${!empty list }">
									<c:forEach items="${list }" var="dto">
										<tr>
											<td>${dto.community_num }</td>
											<td>${dto.community_head }</td>
											<td class="community_title"><a id="community_title"
												href="<%=request.getContextPath() %>/community_content.do?no=${dto.community_num }&page=${paging.page }">
													${dto.community_title } <c:if
														test="${dto.community_reply > 0 }">
														<span> [${dto.community_reply }] </span>
													</c:if> <c:if test="${dto.community_hit > 100 }">
														<span class="best_tag">BEST</span>
													</c:if> <c:if test="${dto.community_regdate eq nowDate }">
														<span class="new_tag">NEW</span>
													</c:if> <c:if test="${dto.community_update eq nowDate }">
														<span class="new_tag">NEW</span>
													</c:if>
											</a></td>
											<td><c:if
													test="${dto.community_update == '1000-01-01' }">
													<span>${dto.community_regdate }</span>
												</c:if> <c:if test="${dto.community_update != '1000-01-01'  }">
													<span>${dto.community_update }</span>
												</c:if></td>
											<td>${dto.community_hit }</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty list }">
									<tr>
										<td colspan="7" align="center">
											<h3>작성한 게시물이 존재하지 않습니다.</h3>
										</td>
									</tr>
								</c:if>
							</table>
						</div>
						<div class="insert-btn-con">
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