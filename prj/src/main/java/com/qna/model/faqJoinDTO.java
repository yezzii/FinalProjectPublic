package com.qna.model;

import lombok.Data;

@Data
public class faqJoinDTO {
	private int faq_category_num;
	private String faq_category_name;
	private String faq_title;
	private String faq_content;
}
