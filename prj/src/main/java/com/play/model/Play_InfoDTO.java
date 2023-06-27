package com.play.model;

import org.springframework.stereotype.Repository;

import lombok.Data;
@Repository
@Data
public class Play_InfoDTO {

	private int play_index;
	private String play_result;
	private String play_category;
	private String play_group;
	private String play_name;
	private String play_address;
	private String play_detail_address;
	private String play_price_standard;
	private String play_price_chk;
	private int play_price;
	private int play_time; 
	private String play_explanation1; 
	private String play_explanation2; 
	private String play_explanation3;
	private int travel_distance;
	private int play_like;
	private int play_choice;
	private String play_picture;
	private int play_view; 
	private int play_startotal;
	private int play_replytotal;
	private Double play_rating;
	
}
