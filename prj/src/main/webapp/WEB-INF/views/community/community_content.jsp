<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="dto" value="${Cont }" />
<c:set var="mdto" value="${mdto }" />
<c:set var="UserId" value="${sessionScope.UserId }" />
<c:set var="ManagerId" value="${sessionScope.ManagerId }" />
<c:set var="rList" value="${rList }" />
<c:set var="page" value="${Page }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${dto.community_head }] ${dto.community_title } : TODO 커뮤니티</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/community.css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/community.js"></script>
<script type="text/javascript">
	var community_num = "${dto.community_num}";
</script>
</head>
<body>
	<%@include file="../include/header03.jsp"%>
	<div id="container">
		<div id="article_container">
			<div id="articlelHeader">
				<div id="article_title">
					<span id="article_head_tag">${dto.community_head }</span>
					<h3>${dto.community_title }</h3>
				</div>
				<div id="profile_info">
					<c:if test="${!empty dto.community_writer }">
						<%
							if (managerId != null) {
						%> <a href="<%=request.getContextPath() %>/manager_look_user_info_view.do?user_nick_name=${dto.community_writer}">${dto.community_writer }</a>
						<%
							} else {
						%> <a href="<%=request.getContextPath() %>/member_profile_view.do?user_nick_name=${dto.community_writer}">${dto.community_writer }</a>
						<%
							}
						%>
						<input type="hidden" id="community_writer" value="${dto.community_writer }">
					</c:if>
					<c:if test="${!empty dto.community_manager }">
						관리자
						<input type="hidden" id="community_writer" value="관리자">
					</c:if>
				</div>
				<div id="article_info_wrap">
					<div id="article_info1">
						<c:if test="${dto.community_update == '1000-01-01' }">
							<span>${dto.community_regdate }</span>
						</c:if>
						<c:if test="${dto.community_update != '1000-01-01'  }">
							<span>${dto.community_update }</span>
						</c:if>
						<span>조회 ${dto.community_hit }</span>
					</div>
					<div id="article_info2">
						<span class="article_info2_text"><i class="fa-solid fa-comment" style="color: #000000;"></i> 댓글 <span id="replyCountNum">${dto.community_reply }</span></span>
						<span class="article_info2_text"><a onclick="clip(); return false;"><i class="fa-regular fa-paste" style="color: #000000;"></i> URL 복사</a></span>
					</div>
				</div>
			</div>
			<div id="articleContent">
				<div id="article_content">${dto.community_content }</div>
				<c:if test="${!empty dto.community_file }">
					<img id="article_image" src="<%=request.getContextPath()%>/resources/upload/community/${uploadFolder }/${dto.community_file }">
				</c:if>
			</div>
			<div id="articleFooter">
				<div id="article_like">
					<c:if test="${likeCheck > 0 }">
						<span class="articlelikeCheck"> 
							<img class="like_img" id="like" onclick="likeDelete(this, ${dto.community_num})" src="<%=request.getContextPath()%>/resources/assets/play_pic/like_heart_fill.png">
						</span>
					</c:if>
					<c:if test="${likeCheck == 0}">
						<span class="articlelikeCheck"> 
							<img class="like_img" id="unlike" onclick="likeInsert(this, ${dto.community_num})" src="<%=request.getContextPath()%>/resources/assets/play_pic/like_heart.png">
						</span>
					</c:if>
					<span id="likeMember" onclick="likeMember(${dto.community_num}); showLikeUserList();">추천 <span id="likeCount">${likeCount}</span></span>
					<span id="likeMemberList"></span>
				</div>
			</div>
			<div id="replyContainer">
				<div class="replyWrite">
					<c:if test="${!empty UserId || !empty ManagerId }">
						<div class="replyWriteWriter">
							<c:if test="${!empty UserId }">
								<c:if test="${dto.community_writer == mdto.nick_name}">
									<span class="reply_writer">${mdto.nick_name }</span>
									<span class="writer_tag">작성자</span>
								</c:if>
								<c:if test="${dto.community_writer != mdto.nick_name}">
									<span class="reply_writer">${mdto.nick_name }</span>
								</c:if>
							</c:if>
							<c:if test="${!empty ManagerId }">
								<span class="reply_writer">작성자</span>
								<span class="manager_tag">관리자</span>
								<input type="hidden" name="reply_writer" id="reply_manager" value="${ManagerId }" readonly>
							</c:if>
						</div>
						<div class="replyWriteContent">
							<textarea name="reply_content" class="reply_content" placeholder="댓글을 입력해주세요."></textarea>
						</div>
						<div class="replyWriteButton">
							<input type="button" value="등록" onclick="replyInsert(this, ${dto.community_num}, 0)">
						</div>
					</c:if>
					<c:if test="${empty UserId && empty ManagerId}">
						<div><h5><a href="member_main.do">댓글 작성은 로그인이 필요합니다.</a></h5></div>
					</c:if>
				</div>
				<div id="replyList">
					<c:if test="${!empty rList }">
						<c:forEach items="${rList }" var="rdto">
							<script type="text/javascript">
								var loginNick = nullCheck('"${mdto.getNick_name()}"');
							</script>	
							<c:if test="${rdto.reply_depth == 0}">
								<div class="replyDepth_0">
									<div id='replyNum_${rdto.reply_num }'>
										<div class="reply_writer_wrap">
											<!-- 댓글 작성자가 회원 -->
											<c:if test="${rdto.reply_writer != null }">
												<!-- 댓글 작성자와 글 작성자가 같은 경우 -->
												<c:if test="${dto.community_writer == rdto.reply_writer }">
													<span class="reply_writer">${rdto.reply_writer }</span>
													<span class="writer_tag">작성자</span>
												</c:if>
												<!-- 댓글 작성자와 글 작성자가 같지 않은 경우 -->
												<c:if test="${dto.community_writer != rdto.reply_writer }">
													<span class="reply_writer">${rdto.reply_writer }</span>
												</c:if>
											</c:if>
											<!-- 댓글 작성자가 관리자 -->
											<c:if test="${rdto.reply_writer == null }">
												<!-- 댓글 작성자와 글 작성자가 관리자인 경우 -->
												<c:if test="${dto.community_writer == null }">
													<div class='reply_writer'>관리자</div>
													<span class="manager_tag">관리자</span>
												</c:if>
												<!-- 댓글 작성자만 관리자인 경우 -->
												<c:if test="${dto.community_writer != null }">
													<div class='reply_writer'>관리자</div>
													<span class="manager_tag">관리자</span>
												</c:if>
											</c:if>
										</div>
										<div class='reply_content'>${rdto.reply_content }</div>
										<div class='reply_date_button'>
											<c:if test="${rdto.reply_update == '1000-01-01' }">
												<span class='reply_regdate'>${rdto.reply_regdate }</span>
											</c:if>
											<c:if test="${rdto.reply_update != '1000-01-01' }">
												<span class='reply_update'>${rdto.reply_update }</span>
											</c:if>
											<c:if test="${!empty UserId }"><!-- 회원 로그인 상태 -->
												<c:if test="${rdto.reply_writer == mdto.nick_name }"><!-- 로그인한 회원이 댓글 작성자일 경우: 수정, 삭제, 답글쓰기 버튼 보여주기 -->
													<c:if test="${!empty rdto.reply_writer }">
														<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '${rdto.reply_writer }', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
													</c:if>
													<c:if test="${empty rdto.reply_writer }">
														<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '관리자', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
													</c:if>
													<input type="button" value="삭제" onclick="replyDelete(${rdto.reply_num }, ${dto.community_num}, ${rdto.reply_depth }, ${rdto.reply_group })">
													<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '${mdto.nick_name }', ${dto.community_num})">
												</c:if>
												<c:if test="${rdto.reply_writer != mdto.nick_name}"><!-- 로그인한 회원이 댓글 작성자가 아닐 경우: 답글쓰기만 가능 -->
													<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '${mdto.nick_name }', ${dto.community_num})">
												</c:if>
											</c:if>
											<c:if test="${!empty ManagerId }"><!-- 관리자 로그인 상태 -->
												<c:if test="${!empty rdto.reply_writer }">
													<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '${rdto.reply_writer }', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
												</c:if>
												<c:if test="${empty rdto.reply_writer }">
													<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '관리자', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
												</c:if>												
												<input type="button" value="삭제" onclick="replyDelete(${rdto.reply_num }, ${dto.community_num}, ${rdto.reply_depth }, ${rdto.reply_group })">
												<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '관리자', ${dto.community_num})">
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${rdto.reply_depth == 1}">
								<div class="replyDepth_1">
									<div id='replyNum_${rdto.reply_num }'>
										<div class="reply_writer_wrap">
											<!-- 댓글 작성자가 회원 -->
											<c:if test="${rdto.reply_writer != null }">
												<!-- 댓글 작성자와 글 작성자가 같은 경우 -->
												<c:if test="${dto.community_writer == rdto.reply_writer }">
													<span class="reply_writer">${rdto.reply_writer }</span>
													<span class="writer_tag">작성자</span>
												</c:if>
												<!-- 댓글 작성자와 글 작성자가 같지 않은 경우 -->
												<c:if test="${dto.community_writer != rdto.reply_writer }">
													<span class="reply_writer">${rdto.reply_writer }</span>
												</c:if>
											</c:if>
											<!-- 댓글 작성자가 관리자 -->
											<c:if test="${rdto.reply_writer == null }">
												<!-- 댓글 작성자와 글 작성자가 관리자인 경우 -->
												<c:if test="${dto.community_writer == null }">
													<div class='reply_writer'>관리자</div>
													<span class="writer_tag">작성자</span>
												</c:if>
												<!-- 댓글 작성자만 관리자인 경우 -->
												<c:if test="${dto.community_writer != null }">
													<div class='reply_writer'>관리자</div>
												</c:if>
											</c:if>
										</div>
										<div class='reply_content'>${rdto.reply_content }</div>
										<div class='reply_date_button'>
											<c:if test="${rdto.reply_update == '1000-01-01' }">
												<span class='reply_regdate'>${rdto.reply_regdate }</span>
											</c:if>
											<c:if test="${rdto.reply_update != '1000-01-01' }">
												<span class='reply_update'>${rdto.reply_update }</span>
											</c:if>
											<c:if test="${!empty UserId }">
												<c:if test="${rdto.reply_writer == mdto.nick_name}">
													<c:if test="${!empty rdto.reply_writer }">
														<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '${rdto.reply_writer }', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
													</c:if>
													<c:if test="${empty rdto.reply_writer }">
														<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '관리자', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
													</c:if>														<input type="button" value="삭제" onclick="replyDelete(${rdto.reply_num }, ${dto.community_num}, ${rdto.reply_depth }, ${rdto.reply_group })">
													<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '${mdto.nick_name }', ${dto.community_num})">
												</c:if>
												<c:if test="${rdto.reply_writer != mdto.nick_name}">
													<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '${mdto.nick_name }', ${dto.community_num})">
												</c:if>
											</c:if>
											<c:if test="${!empty ManagerId }">
												<c:if test="${!empty rdto.reply_writer }">
													<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '${rdto.reply_writer }', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
												</c:if>
												<c:if test="${empty rdto.reply_writer }">
													<input type="button" value="수정" onclick="replyModifyHTML(${rdto.reply_num }, ${dto.community_num}, '관리자', '${rdto.reply_content }', '${rdto.reply_regdate }', '${rdto.reply_update }')">
												</c:if>													<input type="button" value="삭제" onclick="replyDelete(${rdto.reply_num }, ${dto.community_num}, ${rdto.reply_depth }, ${rdto.reply_group })">
												<input type="button" class="reReplyShow" value="답글쓰기" onclick="reReplyShow(${rdto.reply_num }, '관리자', ${dto.community_num})">
											</c:if>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty rList }">
						<script type="text/javascript">
							var loginNick = nullCheck('"${mdto.getNick_name()}"');
						</script>	
						<div>
							<div>아직 작성된 댓글이 없습니다.</div>
						</div>
					</c:if>
				</div>
			</div>
			<div id="article_btn">
				<c:if test="${!empty UserId }">
					<input type="button" class="content_button" value="수정" onclick="location.href='community_modify.do?no=${dto.community_num}&page=${Page }'">
					<input type="button" class="content_button" value="삭제" onclick="location.href='community_delete.do?no=${dto.community_num}&page=${Page }'">
					<input type="button" class="content_button" value="전체 목록" onclick="location.href='community_main.do?page=${Page }'">
				</c:if>
				<c:if test="${empty UserId && empty ManagerId }">
					<input type="button" class="content_button" value="전체 목록" onclick="location.href='community_main.do?page=${Page }'">
				</c:if>
				<c:if test="${!empty ManagerId }">
					<input type="button" class="content_button" value="수정" onclick="location.href='community_modify.do?no=${dto.community_num}&page=${Page }'">
					<input type="button" class="content_button" value="삭제" onclick="communityDelete(${dto.community_num}, ${Page })">
					<input type="button" class="content_button" value="전체 목록" onclick="location.href='community_main.do?page=${Page }'">
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
</html>