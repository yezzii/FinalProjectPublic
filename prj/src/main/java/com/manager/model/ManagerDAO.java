package com.manager.model;

import java.util.List;
import java.util.Map;

import com.community.model.PageDTO;
import com.member.model.MemberDTO;

public interface ManagerDAO {
	
	// 관리자 로그인을 위해 아이디를 체크할 추상 메서드.
	int managerCheckId(String id);
	
	// 관리자 로그인을 위해 해당 아이디에 대한 비밀번호를 체크할 추상 메서드.
	int managerCheckPwd(ManagerDTO dto);
	
	// 관리자 코드에 따른 정보를 호출할 추상 메서드.
	List<ManagerCodeDTO> getManagerCode(String manager_code);
	
	// 관리자 코드 리스트 정보를 호출할 추상 메서드.
	List<ManagerCodeDTO> managerCodeList();
	
	// 관리자 코드에 맞는 관리자 목록을 호출할 추상 메서드.
	List<ManagerDTO> managerList(String manager_code);
	
	// 로그인한 회원의 정보를 호출할 추상 메서드.
	ManagerDTO getManager(String id);
	
	// 회원이 작성한 글을 호출할 추상 메서드.
	List<ManagerActionDTO> managerActionList(String id);
	
	// 커뮤니티 공지글 작성 시 활동 내역 추가할 추상 메서드.
	int managerActionInsert(Map<String, Object> managerActionInsert);
	
	// 신고들어온 유저 기록 저장할 추상 메서드.
	int reportedUser(Map<String, Object> reportInfo);
	
	// 신고들어온 유저의 수를 조회할 추상 메서드.
	int reportedUserCount();
	
	// 신고들어온 유저 리스트를 조회할 추상 메서드.
	List<ReportedUserDTO> reportedUserList(PageDTO pDto);
	
	// 신고 관련 유저 정보 불러오는 추상 메서드.
	MemberDTO getMemberInfo (String userNickName);
	
	// 유저 계정 정지시킬 추상 메서드.
	int memberReportUpdate(String user_id);
	
	// 유저 계정 복구시킬 추상 메서드.
	int memberResetUpdate(String user_id);
	
	// 신고 현 상태를 구분할 수 있도록 업데이트할 추상 메서드.
	int managerReportUpdate(Map<String, Object> managerReportUpdate);
	
	// 신고 진행이 완료된 유저의 수를 조회할 추상 메서드.
	int completionUserCount();
	
	// 신고 완료된 유저 리스트를 조회할 추상 메서드.
	List<ReportedUserDTO> completionUserList(PageDTO pDto);
	
	// 유저의 개인정보를 조회할 추상 메서드.
	ManagerLookUserInfoViewDTO managerLookUserInfoView(String user_id);
}
