package com.manager.model;

import lombok.Data;

@Data
public class ReportedUserDTO {
	
	private int report_user_index;
	private String member_id;
	private int qna_num;
	private String reported_user;
	private String qna_title;
	private int completion_report;
}
