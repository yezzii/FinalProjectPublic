package com.play.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Play_CommentDAOImpl implements Play_CommentDAO {
	@Inject
	private SqlSessionTemplate sqlSession;
	
	
	// 댓글 리스트 
	public List<Play_CommentDTO> replayList(int idx) {
		return this.sqlSession.selectList("replayall",idx);
	}

	
	//댓글 추가
	@Override
	public int commentInsert(Play_CommentDTO commentdto) {
		return this.sqlSession.insert("reply_insert",commentdto);
	}

   //총 댓글 수
	@Override
	public int commentCount(int playIndex) {
		return this.sqlSession.selectOne("reply_count",playIndex);
	}

	//댓글 수정
	@Override
	public int commentModify(Play_CommentDTO commentdto) {
		return this.sqlSession.update("reply_modify",commentdto);
	}


	//댓글 삭제
	public int commetDelete(int index) {
		return this.sqlSession.delete("reply_delete",index);
	}


	//시퀀스 작업
	public int updateSeq(int index) {
		return this.sqlSession.update("reply_seq",index);
	}


	//별점 총 합 
	public int startcount(int idx) {
		int hi = this.sqlSession.selectOne("star_count",idx);
		return hi;
	}
	
	//별점 가져오기
	public int commentstarnum(int index) {
		return this.sqlSession.selectOne("star_num",index);
	}


	@Override
	public int commentindexnum(int index) {
		return this.sqlSession.selectOne("play_index_num",index);
	}

}
