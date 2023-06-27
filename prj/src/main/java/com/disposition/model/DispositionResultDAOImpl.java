package com.disposition.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.play.model.Play_CodeDTO;
import com.play.model.Play_InfoDTO;

@Repository
public class DispositionResultDAOImpl implements DispositionResultDAO   {

   @Autowired
   private SqlSessionTemplate sqlSession;

   @Override
   public int deleteDisposition_result(String id) {
      
      return this.sqlSession.delete("DisposotionResult_delete", id);
   }

   @Override
   public int insertDisposition_result(DispositionResultDTO dto) {
      
      return this.sqlSession.insert("DisposotionResult_insert", dto);
   }
   
   // 질문 답변 조회
   @Override
   public DispositionDTO selectDisposition(int no) {
      
      return this.sqlSession.selectOne("Disposition_select", no);
   }
   
   
   @Override
   public int updateDisposition_resultA_1(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateA_1", id);
      
   }
   
   @Override
   public int updateDisposition_resultA_2(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateA_2", id);
   }

   
   @Override
   public int updateDisposition_resultA_3(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateA_3", id);
   }

   @Override
   public int updateDisposition_resultB_1(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateB_1" ,id);
   }

   @Override
   public int updateDisposition_resultB_2(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateB_2" ,id);
   }

   @Override
   public int updateDisposition_resultB_3(String id) {
      
      return this.sqlSession.update("DisposotionResult_updateB_3" ,id);
   }

   @Override
   public int updateDisposition_resultC_1(String id) {
      return this.sqlSession.update("DisposotionResult_updateC_1" ,id);
   }

   @Override
   public int updateDisposition_resultC_2(String id) {
      return this.sqlSession.update("DisposotionResult_updateC_2" ,id);
   }

   @Override
   public int updateDisposition_resultC_3(String id) {
      return this.sqlSession.update("DisposotionResult_updateC_3" ,id);
   }

   @Override
   public int updateDisposition_resultAscore(String id) {
      return this.sqlSession.update("DispositionResultAscore_update", id);
   }

   @Override
   public int updateDisposition_resultBscore(String id) {
      return this.sqlSession.update("DispositionResultBscore_update", id);
   }

   @Override
   public int updateDisposition_resultCscore(String id) {
      return this.sqlSession.update("DispositionResultCscore_update", id);
   }

   @Override
   public String selectDisposition_resultABCscore_sum(String id) {
      return this.sqlSession.selectOne("DispositionResultABCscore_select", id);
   }

   @Override
   public Play_CodeDTO selectPlay_text(String ABCscore_sum) {
      return this.sqlSession.selectOne("selectPlay_text", ABCscore_sum);
   }

   @Override
   public List<Play_InfoDTO> searchResultList(String play_result) {
      return this.sqlSession.selectList("PlayInformationRandom_list", play_result);
   }

   @Override
   public int updatePlay_choice(String name) {
      return this.sqlSession.update("PlayInformation_choice", name);
   }

   @Override
   public List<Play_InfoDTO> seatchDESCList(String play_result) {
      return this.sqlSession.selectList("Play_choice_DESC_List", play_result);
   }

   @Override
   public int selectSUMchoice(String play_result) {
      return this.sqlSession.selectOne("Play_choiceSUM", play_result);
   }


   

}