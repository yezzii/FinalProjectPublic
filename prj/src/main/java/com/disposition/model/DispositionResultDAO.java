package com.disposition.model;

import java.util.List;

import com.play.model.Play_CodeDTO;
import com.play.model.Play_InfoDTO;

public interface DispositionResultDAO {

   // 처음에 DB 정보가 남아 있으면 즉시 삭제
   int deleteDisposition_result(String id);

   // result 테이블의 default 값으로 넣어준다
   int insertDisposition_result(DispositionResultDTO dto);

   // 해당 페이지에 해당하는 질문, 답변 정보 가져오기
   DispositionDTO selectDisposition(int no);
   

   
   // A 타입 질문에 대한 점수
   // 질문에 대한 1번 답변 선택시 2점 증가.
   int updateDisposition_resultA_1(String id);

   // 질문에 대한 1번 답변 선택시 1점 증가.
   int updateDisposition_resultA_2(String id);

   // 질문에 대한 1번 답변 선택시 0점 증가.
   int updateDisposition_resultA_3(String id);

   // Ascore 점수 조회해서 문자열 반환
   int updateDisposition_resultAscore(String id);

   
   
   // B 타입 질문에 대한 점수
   // 질문에 대한 1번 답변 선택시 2점 증가.
   int updateDisposition_resultB_1(String id);

   // 질문에 대한 1번 답변 선택시 1점 증가.
   int updateDisposition_resultB_2(String id);

   // 질문에 대한 1번 답변 선택시 0점 증가.
   int updateDisposition_resultB_3(String id);

   // Bscore 점수 조회해서 문자열 반환
   int updateDisposition_resultBscore(String id);

   
   
   // C 타입 질문에 대한 점수
   // 질문에 대한 1번 답변 선택시 2점 증가.
   int updateDisposition_resultC_1(String id);

   // 질문에 대한 1번 답변 선택시 1점 증가.
   int updateDisposition_resultC_2(String id);

   // 질문에 대한 1번 답변 선택시 0점 증가.
   int updateDisposition_resultC_3(String id);

   // Cscore 점수 조회해서 문자열 반환
   int updateDisposition_resultCscore(String id);
   
   
   // ABCscore_sum 에 있는 결과값을 아이디로 조회
   String selectDisposition_resultABCscore_sum(String id);
   
   Play_CodeDTO selectPlay_text(String play_result);
   
   List<Play_InfoDTO> searchResultList(String play_result);
   
   
   
   // dispositionResult 에서 resultdto.play_name 누르면 play_information 테이블 play_choice 값  1 증가
   int updatePlay_choice(String name);
   
   // play_information 에서 play_choice 값을 기준으로 내림차순 조회
   List<Play_InfoDTO> seatchDESCList(String play_result);
   
   // 성향 별 전체 조회수 조회
   int selectSUMchoice(String play_result);
   

}