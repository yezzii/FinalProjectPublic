package com.community.model;

import lombok.Data;

@Data
public class Community_replyDTO {
	private int reply_num;
	private String reply_content;
	private int reply_community_num;
	private String reply_writer;
	private String reply_regdate;
	private String reply_update;
	private int reply_depth;
	private int reply_group;
	private String community_reply_title;
	private String reply_manager;
}
