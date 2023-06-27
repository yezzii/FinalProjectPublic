package com.disposition.model;

import lombok.Data;

@Data
public class DispositionResultDTO {
	
	private int disposition_result_index;
	private String result_member_id;
	private int A_score;
	private int B_score;
	private int C_score;
	private String ABCscore_sum;
	
}
