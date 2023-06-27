package com.member.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.community.model.CommunityDTO;
import com.community.model.Community_replyDTO;
import com.play.model.Play_InfoDTO;

public interface MemberDAO {
	
	// 회원을 추가할 추상 메서드.
	int memberInsert(MemberDTO dto);
	
	// 회원을 추가할 추상 메서드.
	int memberKakaoInsert(MemberDTO dto);
	
	// 회원가입 시 아이디 중복 체크를 할 추상 메서드.
	int memberInsertCheckId(String id);
	
	// 회원가입 시 닉네임 중복 체크를 할 추상 메서드.
	int memberInsertCheckNickName(String nickName);
	
	// 회원가입 시 이메일 중복 체크를 할 추상 메서드.
	int memberInsertCheckEmail(String email);
	
	// 회원가입 시 연락처 중복 체크를 할 추상 메서드.
	int memberInsertCheckPhone(String phone);

	// 회원 로그인을 위해 아이디를 체크할 추상 메서드.
	int memberCheckId(String id);
	
	// 회원 로그인을 위해 해당 아이디에 대한 비밀번호를 체크할 추상 메서드.
	int memberCheckPwd(MemberDTO dto);
	
	// 카카오 로그인을 위해 DB에 카카오 유저 토큰이 있는지를 체크할 추상 메서드.
	MemberDTO memberCheckAccessToken(String kakao_id);
	
	// 로그인한 회원의 정보를 호출할 추상 메서드.
	MemberDTO getMember(String id);
	
	// 회원이 작성한 글 개수를 호출할 추상 메서드.
	int MemberCommunityCount(String nickName);
	
	// 회원이 작성한 글을 호출할 추상 메서드.
	List<CommunityDTO> MemberCommunityList(Map<String, Object> pagingAndNickName);
	
	// 회원이 작성한 댓글 개수를 호출할 추상 메서드.
	int MemberReplyCount(String nick_name);
	
	// 회원이 작성한 댓글 정보를 호출할 추상 메서드.
	List<Community_replyDTO> MemberReplyList(Map<String, Object> pagingAndNickName);
	
	int MemberReplyContentCount(String nick_name);
		
	List<CommunityDTO> MemberReplyContentList(Map<String, Object> pagingAndNickName);
	
	// 회원이 추천한 글 개수를 호출할 추상 메서드.
	int MemberLikeCount(String nick_name);
	
	// 회원이 추천한 정보를 호출할 추상 메서드.
	List<CommunityDTO> MemberLikeList(Map<String, Object> pagingAndNickName);

	// 회원이 찜한 Play 수를 호출할 추상 메서드.
	int MemberPlayLikeCount(String id);
	
	// 회원이 찜한 Play 리스트를 호출할 추상 메서드.
	List<Play_InfoDTO> MemberPlayList(String id);
	
	// 회원 수정 시 닉네임 중복 체크를 할 추상 메서드.
	int memberModifyCheckNickName(Map<String, String> newNickNameCheck);
	
	// 회원 수정 시 이메일 중복 체크를 할 추상 메서드.
	int memberModifyCheckEmail(Map<String, String> newEmailCheck);
	
	// 회원 수정 시 연락처 중복 체크를 할 추상 메서드.
	int memberModifyCheckPhone(Map<String, String> newPhoneCheck);
	
	// 회원 정보 수정 추상 메서드.
	int memberModify(MemberDTO dto);
	
	// 회원 닉네임 변경 추상 메서드.
	int memberModifyNickName(Map<String, String> nickNameAndId);
	
	// 회원 이메일 변경 추상 메서드.
	int memberModifyEmail(Map<String, String> emailAndId);
		
	// 회원 탈퇴 추상 메서드.
	int memberDelete(String id);
	
	// 회원 아이디 찾기 추상 메서드.
	MemberDTO memberFindId(Map<String, String> nameEmail);
	
	// 회원 비밀번호 찾기 추상 메서드.
	MemberDTO memberFindPwd(Map<String, String> idNameEmail);
	
	// 타 회원 커뮤니티 조회 개수구하는 뷰테이블 추상 메서드.
	int getUserCommunityInfoCount(String userNickName);
	
	// 타 회원 커뮤니티 조회 뷰테이블 추상 메서드.
	List<UserCommunityInfoViewDTO> getUserCommunityInfo(Map<String, Object> pagingAndNickName);
	
	// 타 회원 커뮤니티 조회 개수구하는 뷰테이블 추상 메서드.
	int getUserCommunityReplyInfoCount(String userNickName);
	
	// 타 회원 댓글 조회 뷰테이블 추상 메서드.
	List<UserCommunityReplyInfoViewDTO> getUserCommunityReplyInfo(Map<String, Object> pagingAndNickName);
}
