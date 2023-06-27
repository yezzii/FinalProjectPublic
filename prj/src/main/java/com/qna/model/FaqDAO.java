package com.qna.model;

import java.util.List;

public interface FaqDAO {

	// 카테고리 이름 구하기
	List<Faq_CategoryDTO> faq_categoryName();
	
	// 카테고리 번호에 따른 faq 내용 구하기
	List<FaqDTO> faqList();
	
}
