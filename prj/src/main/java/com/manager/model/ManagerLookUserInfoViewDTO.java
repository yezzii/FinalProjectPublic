package com.manager.model;

import lombok.Data;

@Data
public class ManagerLookUserInfoViewDTO {
	
	private String user_id;
	private String user_name;
	private String user_nickname;
	private int user_account_state;
}
