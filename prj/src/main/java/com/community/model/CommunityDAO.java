package com.community.model;

import java.util.List;
import java.util.Map;

public interface CommunityDAO {
	
//  커뮤니티 게시글 총 개수 조회
	int getListCount();
	
//	전체 게시글 리스트 조회
	List<CommunityDTO> getCommunityList(PageDTO dto);
	
//	회원 글쓰기
	int communityMemberInsert(CommunityDTO dto);
	
//	관리자 글쓰기
	int communityManagerInsert(CommunityDTO dto);
	
//	조회수 증가
	void readCount(int no);
	
//	게시글 상세 정보 조회
	CommunityDTO communityCont(int no);
	
//	게시글 수정
	int updateCommunity(CommunityDTO dto);
	
//	게시글 삭제
	int deleteCommunity(int no);
	
//	게시글 삭제시 community_num 시퀀스 수정
	void updateSequence(int no);
	
//	좋아요 삭제시 community_like_index 시퀀스 수정
	void updateLikeSequence(int no);
	
//	head, field, keyword에 맞는 검색 결과 총 개수 조회
	int searchCount(Map<String, String> map);
	
//	field, keyword에 맞는 검색 결과 리스트 조회
	List<CommunityDTO> searchList(PageDTO dto);
	
//	해당 게시글의 좋아요 개수 세기
	int likeCount(int no);
	
//	작성자(닉네임)로 작성자 id 구해 upload folder 연결하기
	String getUploadFolder(String writer);
	
//	로그인된 사용자가 해당 게시물에 좋아요를 눌렀는지 아닌지 알아보기
	int likeCheck(Map<String, String> map);
	
//	게시글 좋아요 개수 - 1
	int likeDelete(int no);
	
//	community_like 테이블에서 해당 행 삭제
	int likeDeleteRow(Map<String, String> map);
	
//	게시글 좋아요 개수 + 1
	int likeInsert(Map<String, String> map);
	
//	community_like 테이블에서 해당 행 삽입
	int likeInsertRow(Map<String, String> map);
	
//	멤버 포인트 추가
	void pointInsert(String id);
	
//	멤버 포인트 차감
	void pointDelete(String id);
	
//	게시글 번호에 맞는 댓글 리스트 불러오기
	List<Community_replyDTO> replyList(int no);
	
//	게시글 하나 조회하기
	Community_replyDTO replyCont(int no);
	
//	게시글 댓글 달기
	int replyMemberInsert(Community_replyDTO dto);
	
//	게시글 댓글 달기
	int replyManagerInsert(Community_replyDTO dto);
	
//	게시글 댓글 수정
	int replyModify(Community_replyDTO dto);
	
//	댓글 및 대댓글 삭제 여부 구분하기
	int replyGroupCount(int no);
	
//	게시글 댓글 삭제 - 행 완전히 삭제 : 대댓글 없이 댓글만 있는 경우
	int replyDeleteAll(int no);
	
//	게시글 댓글 삭제 - 삭제된 댓글입니다로 멘트 변경 : 댓글에 대댓글이 달려있는 경우
	int replyDeleteMent(int no);
	
//	게시글 댓글 삭제시 reply_num 시퀀스 수정
	void updateReplySequence(int no);
	
//	groupNo의 최대값
	int replyMaxNum();
	
//	댓글 대댓글 구분 : 이미 GroupNo 존재?
	int replyGroupCheck(int no);
	
//	좋아요 누른 멤버 구하기
	List<Community_likeDTO> likedMember(int no);
	
//	댓글 입력 및 삭제시 개수 갱신
	void replyCountUpdate(int no);
	
//  특정 작성자의 커뮤니티 게시글 총 개수 조회
	int getWriterListCount(String writer_nick);
	
//	특정 작성자의 전체 게시글 리스트 조회
	List<CommunityDTO> getCommunityWriterList(String writer_nick);
	
//	좋아요(좋아요 같으면 최신글) 순으로 정렬한 게시글 리스트
	List<CommunityDTO> communityBestList();
}
