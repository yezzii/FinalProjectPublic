package com.manager.model;

import lombok.Data;

@Data
public class ManagerActionDTO {
	
	private int manager_action_index;
	private String manager_id;
	private String manager_action_content;
	private String manager_action_date;
}
