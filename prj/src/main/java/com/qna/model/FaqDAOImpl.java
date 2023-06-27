package com.qna.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FaqDAOImpl implements FaqDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Faq_CategoryDTO> faq_categoryName() {
		return this.sqlSession.selectList("faq_category_name");
	}

	@Override
	public List<FaqDTO> faqList() {
		return this.sqlSession.selectList("faq_content");
	}

}
