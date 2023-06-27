package com.point.model;

import lombok.Data;

@Data
public class PointDTO {

	private int point_index;
	private String member_id;
	private String point_content;
	private String point_sign;
	private int point_score;
	private String point_date;
	private int point_remanet;
}
