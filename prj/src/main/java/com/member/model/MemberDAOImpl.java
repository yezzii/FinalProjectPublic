package com.member.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.model.CommunityDTO;
import com.community.model.Community_replyDTO;
import com.community.model.PageDTO;
import com.play.model.Play_InfoDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int memberInsertCheckId(String id) {
		
		return this.sqlSession.selectOne("member_insert_checkId", id);
	}
	
	@Override
	public int memberInsertCheckNickName(String nickName) {
		
		return this.sqlSession.selectOne("member_insert_checkNickName", nickName);
	}
	
	@Override
	public int memberInsertCheckEmail(String email) {
		
		return this.sqlSession.selectOne("member_insert_checkEmail", email);
	}
	
	@Override
	public int memberInsertCheckPhone(String phone) {
		
		return this.sqlSession.selectOne("member_insert_checkPhone", phone);
	}

	@Override
	public int memberInsert(MemberDTO dto) {
		
		return this.sqlSession.insert("member_insert", dto);
		
	}
	
	@Override
	public int memberKakaoInsert(MemberDTO dto) {
		
		return this.sqlSession.insert("member_kakaoInsert", dto);
	}

	@Override
	public int memberCheckId(String id) {
		
		return this.sqlSession.selectOne("member_checkId", id);
	}

	@Override
	public int memberCheckPwd(MemberDTO dto) {
		
		return this.sqlSession.selectOne("member_checkPwd", dto);
	}
	
	@Override
	public MemberDTO memberCheckAccessToken(String kakao_id) {
		
		return this.sqlSession.selectOne("member_checkAccessToken", kakao_id);
	}


	@Override
	public MemberDTO getMember(String id) {
		
		return this.sqlSession.selectOne("member_content", id);
	}
	

	@Override
	public int MemberCommunityCount(String nickName) {
		
		return this.sqlSession.selectOne("member_community_count", nickName);
	}

	@Override
	public List<CommunityDTO> MemberCommunityList(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("member_community_list", pagingAndNickName);
	}
	
	@Override
	public int MemberReplyCount(String nick_name) {
		
		return this.sqlSession.selectOne("member_reply_count", nick_name);
	}

	@Override
	public List<Community_replyDTO> MemberReplyList(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("member_reply_list", pagingAndNickName);
	}
	
	@Override
	public int MemberReplyContentCount(String nick_name) {
		
		return this.sqlSession.selectOne("member_reply_content_count", nick_name);
	}
	
	@Override
	public List<CommunityDTO> MemberReplyContentList(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("member_reply_content_list", pagingAndNickName);
	}
	
	@Override
	public int MemberLikeCount(String nick_name) {
		
		return this.sqlSession.selectOne("member_like_count", nick_name);
	}

	@Override
	public List<CommunityDTO> MemberLikeList(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("member_like_list", pagingAndNickName);
	}
	
	@Override
	public int MemberPlayLikeCount(String id) {
		
		return this.sqlSession.selectOne("member_play_like_count", id);
	}
	
	@Override
	public List<Play_InfoDTO> MemberPlayList(String id) {
		
		return this.sqlSession.selectList("member_play_like_list", id);
	}
	
	@Override
	public int memberModifyCheckNickName(Map<String, String> newNickNameCheck) {
		
		return this.sqlSession.selectOne("member_modify_checkNickName", newNickNameCheck);
	}
	
	@Override
	public int memberModifyCheckEmail(Map<String, String> newEmailCheck) {
		
		return this.sqlSession.selectOne("member_modify_checkEmail", newEmailCheck);
	}
	
	@Override
	public int memberModifyCheckPhone(Map<String, String> newPhoneCheck) {
		
		return this.sqlSession.selectOne("member_modify_checkPhone", newPhoneCheck);
	}


	@Override
	public int memberModify(MemberDTO dto) {
		
		return this.sqlSession.update("member_modify", dto);
	}
	
	@Override
	public int memberModifyNickName(Map<String, String> nickNameAndId) {
		
		return this.sqlSession.update("member_modify_nickName", nickNameAndId);
	}
	
	@Override
	public int memberModifyEmail(Map<String, String> emailAndId) {
		
		return this.sqlSession.update("member_modify_email", emailAndId);
	}
	
	@Override
	public int memberDelete(String id) {
		
		return this.sqlSession.delete("member_delete", id);
	}

	@Override
	public MemberDTO memberFindId(Map<String, String> nameEmail) {
		
		return this.sqlSession.selectOne("member_findId", nameEmail);
	}

	@Override
	public MemberDTO memberFindPwd(Map<String, String> idNameEmail) {
		
		return this.sqlSession.selectOne("member_findPwd", idNameEmail);
	}
		
	@Override
	public int getUserCommunityInfoCount(String userNickName) {
		
		return this.sqlSession.selectOne("member_community_info_count", userNickName);
	}
	
	@Override
	public List<UserCommunityInfoViewDTO> getUserCommunityInfo(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("user_community_info", pagingAndNickName);
	}
	
	@Override
	public int getUserCommunityReplyInfoCount(String userNickName) {
		
		return this.sqlSession.selectOne("member_community_reply_info_count", userNickName);
	}

	@Override
	public List<UserCommunityReplyInfoViewDTO> getUserCommunityReplyInfo(Map<String, Object> pagingAndNickName) {
		
		return this.sqlSession.selectList("user_community_reply_info", pagingAndNickName);
	}

}
