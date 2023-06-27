package com.member.model;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String id;
	private String password;
	private String name;
	private String nick_name;
	private String email;
	private String phone;
	private String post;
	private String road_address;
	private String jibun_address;
	private String detail_address;
	private String dong_address;
	private String date;
	private int point;
	private String kakao_id;
	private int member_login;

}