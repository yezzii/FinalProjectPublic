package com.qna.model;

import lombok.Data;

@Data
public class QnaDTO {
	private int qna_num;
	private String qna_head;
	private String qna_title;
	private String qna_writer;
	private String qna_password;
	private String qna_content;
	private String qna_file;
	private String qna_regdate;
	private String qna_update;
	private int qna_groupOrd;
	private int qna_groupNo;
	private int qna_depthNo;
	private int qna_state;
}
