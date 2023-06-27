package com.play.model;

import java.util.List;

public interface Play_CommentDAO {
	//댓글 리스트
	List<Play_CommentDTO> replayList(int idx);
	//댓글 추가
	int commentInsert(Play_CommentDTO commentdto);
	//댓글 총갯수
	int commentCount(int playIndex);
	//댓글 수정
	int commentModify(Play_CommentDTO commentdto);
	//댓글 삭제
	int commetDelete(int index);
	//댓글 시퀀스작업
	int updateSeq(int index);
	// 별점 총합 
	int startcount(int idx);
	// 별점 가져오기 
	int commentstarnum(int index);
	// 인덱스 가져오기
	int commentindexnum(int index);

}
