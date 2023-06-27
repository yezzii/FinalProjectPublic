package com.qna.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.model.PageDTO;
import com.member.model.MemberDTO;

@Repository
public class QnaDAOImpl implements QnaDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int qnaInsert(QnaDTO dto) {
		return this.sqlSession.insert("qna_insert", dto);
	}
	
	@Override
	public int qnaInsertReply(QnaDTO dto) {
		return this.sqlSession.insert("qna_insertReply", dto);
	}
	
	@Override
	public int qnaCount() {
		return sqlSession.selectOne("qna_count");
	}

	@Override
	public List<QnaDTO> qnaList(PageDTO dto) {
		return sqlSession.selectList("qna_list", dto);
	}

	@Override
	public QnaDTO qnaContent(int no) {
		return sqlSession.selectOne("qna_content", no);
	}

	@Override
	public int qnaUpdate(QnaDTO dto) {
		return sqlSession.update("qna_update", dto);
	}

	@Override
	public int qnaDelete(int no) {
		return sqlSession.update("qna_delete", no);
	}

	@Override
	public void qnaUpdateSequence(int no) {
		sqlSession.update("qna_updateSeq", no);
	}

	@Override
	public int qnaSearchCount(Map<String, String> map) {
		return sqlSession.selectOne("qna_searchCount", map);
	}

	@Override
	public List<QnaDTO> qnaSearchList(PageDTO dto) {
		return sqlSession.selectList("qna_searchList", dto);
	}
	
	@Override
	public int qnaMaxNum() {
		return sqlSession.selectOne("qna_maxNum");
	}
	
	@Override
	public List<MemberDTO> qnaSearchUserList(String user_nick_name) {
		return sqlSession.selectList("qna_searchUserList", user_nick_name);
	}
	
	@Override
	public int qnaUpdateState(int no) {
		return sqlSession.update("qna_updateState", no);
	}
	
	@Override
	public List<faqJoinDTO> qnaCategoryList(int no) {
		return sqlSession.selectList("qna_categoryList", no);
	}
	
}
