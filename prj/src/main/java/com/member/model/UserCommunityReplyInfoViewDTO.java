package com.member.model;

import lombok.Data;

@Data
public class UserCommunityReplyInfoViewDTO {
	
	private String user_nickname;
	private int written_num;
	private String written_head;
	private String written_title;
	private int written_reply;
	private String written_update;
	private String written_regdate;
	private int written_hit;
}
