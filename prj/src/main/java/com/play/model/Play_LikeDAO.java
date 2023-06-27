package com.play.model;

public interface Play_LikeDAO {

		//좋아요 
		
		int play_likeinsert(Play_LikeDTO dto);
		
		 void play_like(Play_LikeDTO dto);
		 
		 void play_likedelete(Play_LikeDTO dto);
		 
		 void play_sequpdate(Play_LikeDTO dto);

		 int play_check(Play_LikeDTO dto);
		 
		 String play_likecheck(Play_LikeDTO dto);
}
