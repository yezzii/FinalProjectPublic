package com.qna.model;

import java.util.List;
import java.util.Map;

import com.community.model.PageDTO;
import com.member.model.MemberDTO;

public interface QnaDAO {
	
//	글쓰기
	int qnaInsert(QnaDTO dto);
	
//	답글쓰기
	int qnaInsertReply(QnaDTO dto);
	
//	전체 게시글 수 조회
	int qnaCount();
	
//	전체 게시글 리스트 조회
	List<QnaDTO> qnaList(PageDTO dto);
	
//	게시글 상세보기
	QnaDTO qnaContent(int no);
	
//	게시글 수정
	int qnaUpdate(QnaDTO dto);
	
//	게시글 삭제
	int qnaDelete(int no);
	
//	게시글 시퀀스 수정
	void qnaUpdateSequence(int no);
	
//	검색 결과 수 조회
	int qnaSearchCount(Map<String, String> map);
	
//	검색 결과 리스트 조회
	List<QnaDTO> qnaSearchList(PageDTO dto);
	
//	최대 qna_num 구하기
	int qnaMaxNum();
	
//	사용자 리스트 가져오기
	List<MemberDTO> qnaSearchUserList(String user_nick_name);

//	답변 완료시 상태 업데이트
	int qnaUpdateState(int no);
	
//	카테고리별 리스트 조회
	List<faqJoinDTO> qnaCategoryList(int no);
}
