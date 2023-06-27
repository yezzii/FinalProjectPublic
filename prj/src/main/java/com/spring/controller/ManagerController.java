package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.model.PageDTO;
import com.manager.model.ManagerActionDTO;
import com.manager.model.ManagerCodeDTO;
import com.manager.model.ManagerDAO;
import com.manager.model.ManagerDTO;
import com.manager.model.ManagerLookUserInfoViewDTO;
import com.manager.model.ReportedUserDTO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@Controller
public class ManagerController {

	@Autowired
	private ManagerDAO dao;
	
	@Autowired
	private MemberDAO mDao;
	
	// 한 페이지당 보여질 게시물의 수
	private final int rowsize = 10;

	// DB 상의 전체 게시물의 수
	private int totalRecord = 0;
	
	@RequestMapping("manager_main.do")
	public String managerMain() {
		// 로그인 화면 이동
		return "manager/manager_main";
	}
	
	@RequestMapping("manager_login.do")
	public void memberLogIn(@RequestParam("manager_id") String id, ManagerDTO dto, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		int checkId = this.dao.managerCheckId(id);
		int checkPwd = this.dao.managerCheckPwd(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(checkId == 1) {
			// 아이디 존재
			if(checkPwd == 1) {
				// 비밀번호 존재 => 로그인 성공
				dto = this.dao.getManager(id);
				HttpSession session = request.getSession();
				session.setAttribute("ManagerId", id);
				out.println("<script>");
				out.println("alert('관리자 로그인 성공')");
				out.println("location.href='main.do'");
				out.println("</script>");
			}else {
				// 비밀번호가 틀린 경우
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			// 아이디가 존재하지 않는 경우
			out.println("<script>");
			out.println("alert('관리자 아이디가 존재하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("manager_logOut.do")
	public String managerLogOut(HttpServletRequest request) {
		
		 // 로그아웃 세션 종료 기능
	     HttpSession session = request.getSession();
	     session.invalidate();
	     
	     return "main";
	}
	
	@RequestMapping("manager_page.do")
	public String managerPage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		ManagerDTO dto = this.dao.getManager(managerId);
		int reportedUserCount = this.dao.reportedUserCount();
		int completionUserCount = this.dao.completionUserCount();
		model.addAttribute("dto", dto).addAttribute("reportedUserCount", reportedUserCount).addAttribute("completionUserCount", completionUserCount);
		
		return "manager/manager_page";
	}
	
	@RequestMapping("manager_code.do")
	public String managerCode(@RequestParam("manager_code") String manager_code, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		List<ManagerCodeDTO> cDto = this.dao.getManagerCode(manager_code);
		List<ManagerCodeDTO> cDtoList = this.dao.managerCodeList();
		List<ManagerDTO> mDto = this.dao.managerList(manager_code);
		model.addAttribute("cDto", cDto).addAttribute("cDtoList", cDtoList).addAttribute("mDto", mDto);
		
		return "manager/manager_code";
	}
	
	@RequestMapping("manager_action_list.do")
	public String managerActionList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		ManagerDTO dto = this.dao.getManager(managerId);
		List<ManagerActionDTO> actionList = this.dao.managerActionList(managerId);
		model.addAttribute("dto", dto).addAttribute("actionList", actionList);
		
		return "manager/manager_action_list";
	}
	
	@RequestMapping("manager_report_user_list.do")
	public String managerReportUserList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		
		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.reportedUserCount();
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		List<ReportedUserDTO> reportedList = this.dao.reportedUserList(pDto);
		model.addAttribute("reportedList", reportedList).addAttribute("Paging", pDto);
		
		return "manager/manager_report_user_list";
	}
	
	@RequestMapping("manager_reset_user_list.do")
	public String managerResetUserList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		
		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.completionUserCount();
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		List<ReportedUserDTO> completionUserList = this.dao.completionUserList(pDto);
		model.addAttribute("completionUserList", completionUserList).addAttribute("Paging", pDto);
		
		return "manager/manager_reset_user_list";
	}
	
	@RequestMapping("manager_user_stop_ok.do")
	public void managerUserStopOk(ManagerDTO dto, @RequestParam("db_pwd") String db_pwd, @RequestParam("user_id") String user_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDTO mDto = mDao.getMember(user_id);

		if (db_pwd.equals(dto.getManager_password())) {
			int check = this.dao.memberReportUpdate(mDto.getId());
			if (check > 0) {
				Map<String, Object> managerReportUpdate = new HashMap<String, Object>();
				managerReportUpdate.put("nickName", mDto.getNick_name());
				managerReportUpdate.put("state", 1);
				this.dao.managerReportUpdate(managerReportUpdate);
				Map<String, Object> managerActionInsert = new HashMap<String, Object>();
				managerActionInsert.put("manager_id", managerId);
				managerActionInsert.put("manager_action_content", mDto.getId() + " 회원 계정 정지");
				this.dao.managerActionInsert(managerActionInsert);
				out.println("<script>");
				out.println("alert('회원 계정 정지 성공')");
				out.println("location.href='main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 계정 정지 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else { // 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("manager_completion_report.do")
	public String managerCompletionReport(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		
		ManagerDTO dto = this.dao.getManager(managerId);
		MemberDTO mDto = this.dao.getMemberInfo(userNickName);
		model.addAttribute("dto", dto).addAttribute("mDto", mDto);
		
		return "manager/manager_completion_report";
	}
	
	@RequestMapping("manager_completion_reset.do")
	public String managerCompletionReset(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		
		ManagerDTO dto = this.dao.getManager(managerId);
		MemberDTO mDto = this.dao.getMemberInfo(userNickName);
		model.addAttribute("dto", dto).addAttribute("mDto", mDto);
		
		return "manager/manager_completion_reset";
	}
	
	@RequestMapping("manager_user_reset_ok.do")
	public void managerUserResetOk(ManagerDTO dto, @RequestParam("db_pwd") String db_pwd, @RequestParam("user_id") String user_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId");
		MemberDTO mDto = mDao.getMember(user_id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (db_pwd.equals(dto.getManager_password())) {
			int check = this.dao.memberResetUpdate(user_id);
			if (check > 0) {
				Map<String, Object> managerReportUpdate = new HashMap<String, Object>();
				managerReportUpdate.put("nickName", mDto.getNick_name());
				managerReportUpdate.put("state", 2);
				this.dao.managerReportUpdate(managerReportUpdate);
				Map<String, Object> managerActionInsert = new HashMap<String, Object>();
				managerActionInsert.put("manager_id", managerId);
				managerActionInsert.put("manager_action_content", mDto.getId() + " 회원 계정 복구");
				this.dao.managerActionInsert(managerActionInsert);
				out.println("<script>");
				out.println("alert('회원 계정 복구 성공')");
				out.println("location.href='main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 계정 복구 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else { // 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("manager_look_user_info_view.do")
	public String memberProfileView(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String managerId = (String) session.getAttribute("ManagerId"); 
		
		MemberDTO mDto = this.dao.getMemberInfo(userNickName);
		ManagerLookUserInfoViewDTO managerLookUserInfoView = this.dao.managerLookUserInfoView(mDto.getId());
		model.addAttribute("managerLookUserInfoView", managerLookUserInfoView).addAttribute("user_nick_name", mDto.getNick_name());
		
		return "manager/manager_look_user_info_view";
	}
}
