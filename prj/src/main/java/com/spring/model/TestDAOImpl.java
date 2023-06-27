package com.spring.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAOImpl implements TestDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertTest(TestDTO dto) {
		return this.sqlSession.insert("insert", dto);
	}

}
