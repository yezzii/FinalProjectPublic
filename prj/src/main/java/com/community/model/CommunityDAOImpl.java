package com.community.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityDAOImpl implements CommunityDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int getListCount() {
		return this.sqlSession.selectOne("community_cnt");
	}

	@Override
	public List<CommunityDTO> getCommunityList(PageDTO dto) {
		return this.sqlSession.selectList("community_list", dto);
	}

	@Override
	public int communityMemberInsert(CommunityDTO dto) {
		return this.sqlSession.insert("community_memberAdd", dto);
	}
	
	@Override
	public int communityManagerInsert(CommunityDTO dto) {
		return this.sqlSession.insert("community_managerAdd", dto);
	}

	@Override
	public void readCount(int no) {
		this.sqlSession.update("community_read", no);
	}

	@Override
	public CommunityDTO communityCont(int no) {
		return this.sqlSession.selectOne("community_cont", no);
	}

	@Override
	public int updateCommunity(CommunityDTO dto) {
		return sqlSession.update("community_modify", dto);
	}

	@Override
	public int deleteCommunity(int no) {
		return sqlSession.update("community_del", no);
	}

	@Override
	public void updateSequence(int no) {
		sqlSession.update("community_seq", no);
	}
	
	@Override
	public void updateLikeSequence(int no) {
		sqlSession.update("community_likeSeq", no);
	}

	@Override
	public int searchCount(Map<String, String> map) {
		return this.sqlSession.selectOne("community_searchCnt", map);
	}

	@Override
	public List<CommunityDTO> searchList(PageDTO dto) {
		return this.sqlSession.selectList("community_searchList", dto);
	}
	
	@Override
	public int likeCount(int no) {
		return sqlSession.selectOne("community_likeCount", no);
	}

	@Override
	public String getUploadFolder(String writer) {
		return sqlSession.selectOne("community_getId", writer);
	}

	@Override
	public int likeCheck(Map<String, String> map) {
		return sqlSession.selectOne("community_likeCheck", map);
	}

	@Override
	public int likeDelete(int no) {
		return sqlSession.update("community_likeDelete", no);
	}
	
	@Override
	public int likeDeleteRow(Map<String, String> map) {
		return sqlSession.update("community_likeDeleteRow", map);
	}

	@Override
	public int likeInsert(Map<String, String> map) {
		return sqlSession.update("community_likeInsert", map);
	}
	
	@Override
	public int likeInsertRow(Map<String, String> map) {
		return sqlSession.update("community_likeInsertRow", map);
	}

	@Override
	public void pointInsert(String id) {
		sqlSession.update("community_pointInsert", id);
	}
	
	@Override
	public void pointDelete(String id) {
		sqlSession.update("community_pointDelete", id);
	}

	@Override
	public List<Community_replyDTO> replyList(int no) {
		return sqlSession.selectList("community_replyList", no);
	}

	@Override
	public int replyMemberInsert(Community_replyDTO dto) {
		return sqlSession.insert("community_replyMemberInsert", dto);
	}
	
	@Override
	public int replyManagerInsert(Community_replyDTO dto) {
		return sqlSession.insert("community_replyManagerInsert", dto);
	}

	@Override
	public int replyMaxNum() {
		return sqlSession.selectOne("community_maxNum");
	}

	@Override
	public List<Community_likeDTO> likedMember(int no) {
		return sqlSession.selectList("community_likedMember", no);
	}

	@Override
	public void replyCountUpdate(int no) {
		sqlSession.update("community_replyCountUpdate", no);
	}

	@Override
	public int getWriterListCount(String writer_nick) {
		return sqlSession.selectOne("community_writerListCount", writer_nick);
	}

	@Override
	public List<CommunityDTO> getCommunityWriterList(String writer_nick) {
		return sqlSession.selectList("community_writerList", writer_nick);
	}

	@Override
	public int replyModify(Community_replyDTO dto) {
		return sqlSession.update("community_replyModify", dto);
	}
	
	@Override
	public int replyGroupCount(int no) {
		return sqlSession.selectOne("community_replyGroupCount", no);
	}

	@Override
	public int replyDeleteMent(int no) {
		return sqlSession.update("community_replyDeleteMent", no);
	}
	
	@Override
	public int replyDeleteAll(int no) {
		return sqlSession.delete("community_replyDeleteAll", no);
	}

	@Override
	public void updateReplySequence(int no) {
		sqlSession.update("community_replySeq", no);
	}

	@Override
	public int replyGroupCheck(int no) {
		return sqlSession.selectOne("community_replyGroupCheck", no);
	}

	@Override
	public Community_replyDTO replyCont(int no) {
		return sqlSession.selectOne("community_replyCont", no);
	}

	@Override
	public List<CommunityDTO> communityBestList() {
		return sqlSession.selectList("community_bestList");
	}

}