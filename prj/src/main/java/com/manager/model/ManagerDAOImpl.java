package com.manager.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.community.model.PageDTO;
import com.member.model.MemberDTO;

@Repository
public class ManagerDAOImpl implements ManagerDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int managerCheckId(String id) {
		
		return this.sqlSession.selectOne("manager_checkId", id);
	}

	@Override
	public int managerCheckPwd(ManagerDTO dto) {
		
		return this.sqlSession.selectOne("manager_checkPwd", dto);
	}
	
	@Override
	public ManagerDTO getManager(String id) {
		
		return this.sqlSession.selectOne("manager_content", id);
	}

	@Override
	public List<ManagerCodeDTO> getManagerCode(String manager_code) {
		
		return this.sqlSession.selectList("manager_code", manager_code);
	}
	
	@Override
	public List<ManagerCodeDTO> managerCodeList() {
		
		return this.sqlSession.selectList("manager_code_list");
	}

	@Override
	public List<ManagerDTO> managerList(String manager_code) {
		
		return this.sqlSession.selectList("manager_list", manager_code);
	}
	
	@Override
	public List<ManagerActionDTO> managerActionList(String id) {
		
		return this.sqlSession.selectList("manager_action_list", id);
	}

	@Override
	public int managerActionInsert(Map<String, Object> managerActionInsert) {
		
		return this.sqlSession.insert("manager_action_insert", managerActionInsert);
	}

	@Override
	public int reportedUser(Map<String, Object> reportInfo) {
		
		return this.sqlSession.insert("manager_reported_user", reportInfo);
	}
	
	@Override
	public int reportedUserCount() {
		
		return this.sqlSession.selectOne("reported_user_count");
	}
	
	@Override
	public List<ReportedUserDTO> reportedUserList(PageDTO pDto) {
		
		return this.sqlSession.selectList("reported_user_list", pDto);
	}

	@Override
	public MemberDTO getMemberInfo(String userNickName) {

		return this.sqlSession.selectOne("member_info", userNickName);
	}

	@Override
	public int memberReportUpdate(String user_id) {
		
		return this.sqlSession.update("user_report_update", user_id);
	}

	@Override
	public int memberResetUpdate(String user_id) {
		
		return this.sqlSession.update("user_reset_update", user_id);
	}

	@Override
	public int managerReportUpdate(Map<String, Object> managerReportUpdate) {
		
		return this.sqlSession.update("manager_report_update", managerReportUpdate);
	}

	@Override
	public int completionUserCount() {
		
		return this.sqlSession.selectOne("completion_user_count");
	}

	@Override
	public List<ReportedUserDTO> completionUserList(PageDTO pDto) {
		
		return this.sqlSession.selectList("completion_user_list", pDto);
	}

	@Override
	public ManagerLookUserInfoViewDTO managerLookUserInfoView(String user_id) {
		
		return this.sqlSession.selectOne("manager_look_user_info_view", user_id);
	}



}