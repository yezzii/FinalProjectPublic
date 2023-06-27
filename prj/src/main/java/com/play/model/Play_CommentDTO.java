package com.play.model;

import java.sql.Date;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class Play_CommentDTO {
private int play_comment_num;
private int play_index;
private String member_nickname;
private String member_id;
private String play_comment;
private int play_star;
private String play_comment_regdate;
private int play_recommend;
private String play_update;
}
