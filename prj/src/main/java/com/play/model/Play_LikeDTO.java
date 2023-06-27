package com.play.model;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class Play_LikeDTO {
private int play_like_index;
private int play_it;
private String member_id;
private String play_like_date;
}
