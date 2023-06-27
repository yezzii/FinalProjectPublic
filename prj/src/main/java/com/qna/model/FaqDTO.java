package com.qna.model;

import lombok.Data;

@Data
public class FaqDTO {
	private int faq_num;
	private int faq_category;
	private String faq_title;
	private String faq_content;
 }
