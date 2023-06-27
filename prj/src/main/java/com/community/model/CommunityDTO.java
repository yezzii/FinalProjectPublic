package com.community.model;

import lombok.Data;

@Data
public class CommunityDTO {
	private int community_num;
	private String community_head;
	private String community_title;
	private String community_writer;
	private String community_content;
	private String community_file;
	private String community_regdate;
	private String community_update;
	private int community_hit;
	private int community_like;
	private int community_reply;
	private int community_notice;
	private String community_manager;
}
