package com.point.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public int Disposition_Start(String id) {
		return this.sqlSession.update("Point_DispositionStart", id);
	}
	
	@Override
	public int Disposition_Reset(String id) {
		return this.sqlSession.update("Point_DispositionReset", id);
	}

	
	@Override
	public List<PointDTO> pointList(Map<String, Object> pagingAndId) {
		
		return this.sqlSession.selectList("point_list", pagingAndId);
	}
	
	@Override
	public int pointInsert(Map<String, Object> pointInsert) {
		
		return this.sqlSession.insert("point_insert", pointInsert);
	}

	@Override
	public int memberPointCount(String id) {
		
		return this.sqlSession.selectOne("point_count", id);
	}

	
}