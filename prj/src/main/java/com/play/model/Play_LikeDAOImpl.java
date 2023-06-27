package com.play.model;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Play_LikeDAOImpl implements Play_LikeDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void play_like(Play_LikeDTO dto) {
	}
	
	public int play_check(Play_LikeDTO dto) {
		return this.sqlSession.selectOne("check",dto);
	}
	
	public int play_likeinsert(Play_LikeDTO dto) {
		return this.sqlSession.insert("likeinsert",dto);
	}
	@Override
	public void play_likedelete(Play_LikeDTO dto) {
		this.sqlSession.delete("likedelete",dto);
	}
	
	public void play_sequpdate(Play_LikeDTO dto) {
		this.sqlSession.update("index_seq",dto);
	}

	@Override
	public String play_likecheck(Play_LikeDTO dto) {
		return this.sqlSession.selectOne("check",dto);
	}


}
