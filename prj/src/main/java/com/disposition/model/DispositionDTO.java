package com.disposition.model;

import lombok.Data;

@Data
public class DispositionDTO {
	
	private int disposition_num;
	private String disposition_code;
	private String disposition_content;
	private String disposition_answer1;
	private String disposition_answer2;
	private String disposition_answer3;
}
