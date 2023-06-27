package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.model.CommunityDTO;
import com.community.model.Community_likeDTO;
import com.community.model.Community_replyDTO;
import com.community.model.PageDTO;
import com.google.gson.Gson;
import com.manager.model.ManagerDAO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.member.model.MemberService;
import com.member.model.UserCommunityInfoViewDTO;
import com.member.model.UserCommunityReplyInfoViewDTO;
import com.play.model.Play_InfoDTO;
import com.point.model.PointDAO;
import com.point.model.PointDTO;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PointDAO pDao;
	
	@Autowired
	private ManagerDAO mDao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberService ms;
	
	// 한 페이지당 보여질 게시물의 수
	private final int rowsize = 10;

	// DB 상의 전체 게시물의 수
	private int totalRecord = 0;

	@RequestMapping("member_main.do")
	public String memberMain() {
		// 로그인 화면 이동
		return "member/member_main";
	}
	
	@RequestMapping(value="/member_kakaoLogin.do", method=RequestMethod.GET)
	public String memberKakaoLogin(@RequestParam(value = "code", required = false) String code, HttpServletResponse response,
			HttpServletRequest request, Model model) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String link = null;
		String access_Token = ms.getAccessToken(code);
		HashMap<String, Object> userInfo = ms.getUserInfo(access_Token);
		System.out.println("###access_Token#### : " + access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));
		String kakao_id = (String)userInfo.get("id");
		System.out.println(kakao_id); // 2790830631
		MemberDTO dto = this.dao.memberCheckAccessToken(kakao_id);
		System.out.println(dto);
		
		int check = 0;
		
		if(dto != null) {
			check = 1;
		}
		
		System.out.println("check >>> " + check);
		
		if(check == 1) {
			if(dto.getMember_login() == 0) {
				HttpSession session = request.getSession();
				session.setAttribute("UserId", dto.getId());
				session.setAttribute("KakaoId", dto.getKakao_id());
				out.println("<script>");
				out.println("alert('로그인 성공')");
				out.println("</script>");
				link = "main";
			} else if(dto.getMember_login() == 1) {
				// 정지된 아이디인 경우
				out.println("<script>");
				out.println("alert('정지된 아이디입니다. 고객센터를 이용해 주세요.')");
				out.println("</script>");
				link = "qna_main"; 
			}
		} else {
			model.addAttribute("kakao_id", userInfo.get("id")).addAttribute("nick_name", userInfo.get("nickname")).addAttribute("email", userInfo.get("email"));
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.')");
			out.println("</script>");
			link = "member/member_insert_kakao";
		}
		return link;
    }

	@RequestMapping("member_login.do")
	public void memberLogIn(@RequestParam("id") String id, MemberDTO dto, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		int checkId = this.dao.memberCheckId(id);
		int checkPwd = this.dao.memberCheckPwd(dto);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (checkId == 1) {
			// 아이디 존재
			if (checkPwd == 1) {
				// 비밀번호 존재 => 로그인 성공
				dto = this.dao.getMember(id);
				if(dto.getMember_login() == 1) {
					// 정지된 아이디인 경우
					out.println("<script>");
					out.println("alert('정지된 아이디입니다. 고객센터를 이용해 주세요.')");
					out.println("history.back()");
					out.println("</script>");
				} else if(dto.getMember_login() == 0) {
					HttpSession session = request.getSession();
					session.setAttribute("UserId", id);
					session.setAttribute("KakaoId", dto.getKakao_id());
					out.println("<script>");
					out.println("alert('로그인 성공')");
					out.println("location.href='main.do'");
					out.println("</script>");
				}
			} else {
				// 비밀번호가 틀린 경우
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			// 아이디가 존재하지 않는 경우
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping("member_logOut.do")
	public String memberLogOut(HttpServletRequest request) {

		// 로그아웃 세션 종료 기능
		HttpSession session = request.getSession();
		session.invalidate();

		return "main";
	}

	@RequestMapping("member_insert.do")
	public String memberInsert() {
		// 회원가입 화면 이동
		return "member/member_insert";
	}

	@RequestMapping(value = "/member_checkInsertId.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberInsertIdCheck(HttpServletResponse response, @RequestParam("inputId") String userId) throws IOException {
		Gson gson = new Gson();
		int check = dao.memberInsertCheckId(userId);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}
	
	@RequestMapping(value = "/member_checkPwd.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberInsertPwdCheck(HttpServletResponse response, @RequestParam("password") String password, @RequestParam("password2") String password2) throws IOException {
		Gson gson = new Gson();
		
		String result = "";
		
		Map<String, Object> resultChkPwd = new HashMap<String, Object>();
		
		if(password.equals(password2)) {
			result = "사용가능";
		} else {
			result = "사용불가능";
		}
		
		resultChkPwd.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkPwd));
	}
	
	@RequestMapping(value = "/member_checkInsertNickName.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberInsertNickNameCheck(HttpServletResponse response, @RequestParam("nickName") String nickName) throws IOException {
		Gson gson = new Gson();
		
		int check = dao.memberInsertCheckNickName(nickName);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}
	
	@RequestMapping(value = "/member_checkInsertEmail.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberInsertEmailCheck(HttpServletResponse response, @RequestParam("email") String email) throws IOException {
		Gson gson = new Gson();
		
		int check = dao.memberInsertCheckEmail(email);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}
	
	@RequestMapping(value = "/member_mailCheckCode.do", method = RequestMethod.GET)
	@ResponseBody
	public String memberEmailInsertCheckCode(@RequestParam("email") String email) throws Exception {
		
		/* 인증번호(난수) 생성 */
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;
        
        /* 이메일 보내기 */
        String setFrom = "gamemanchoo@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
        String toMail = email;
        String title = "TODO 인증 이메일 입니다."; // 이메일 제목 
        String content = 
                "홈페이지를 방문해주셔서 감사합니다." +  	//html 형식으로 작성
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
      
        MimeMessage message = mailSender.createMimeMessage();
        
        try {
        	// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true); // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }

        return Integer.toString(checkNum);
	}
	
	@RequestMapping(value = "/member_checkInsertPhone.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberInsertPhoneCheck(HttpServletResponse response, @RequestParam("phone") String phone) throws IOException {
		Gson gson = new Gson();
		
		int check = dao.memberInsertCheckPhone(phone);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}


	@RequestMapping("member_insert_ok.do")
	public void memberInsertOk(@RequestParam("password_check") String password_check, MemberDTO dto, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!password_check.equals(dto.getPassword())) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			int check = this.dao.memberInsert(dto);
			if (check > 0) {
				dto = this.dao.getMember(dto.getId());
				Map<String, Object> pointInsert = new HashMap<String, Object>();
				pointInsert.put("member_id", dto.getId());
				pointInsert.put("point_content", "가입 축하 포인트");
				pointInsert.put("point_sign", "+");
				pointInsert.put("point_score", 100);
				pointInsert.put("point_remanet", dto.getPoint());
				this.pDao.pointInsert(pointInsert);
				out.println("<script>");
				out.println("alert('회원가입 성공.')");
				out.println("location.href ='member_main.do'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('회원가입 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}	
	}
	
	@RequestMapping("member_kakaoInsert_ok.do")
   public void memberKakaoInsertOk(@RequestParam("password_check") String password_check, @RequestParam("id") String id, MemberDTO dto, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(!password_check.equals(dto.getPassword())) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다. 다시 확인해주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			int check = this.dao.memberKakaoInsert(dto);
			if (check > 0) {
				dto = this.dao.getMember(dto.getId());
				Map<String, Object> pointInsert = new HashMap<String, Object>();
				pointInsert.put("member_id", dto.getId());
				pointInsert.put("point_content", "가입 축하 포인트");
				pointInsert.put("point_sign", "+");
				pointInsert.put("point_score", 100);
				pointInsert.put("point_remanet", dto.getPoint());
				this.pDao.pointInsert(pointInsert);
				out.println("<script>");
				out.println("alert('회원가입 성공.')");
				out.println("location.href ='member_main.do'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('회원가입 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}	
	}

	@RequestMapping("member_findId_page.do")
	public String memberFindIdPage() {

		return "member/member_findId";
	}
	
	@RequestMapping("member_findPwd_page.do")
	public String memberFindPwdPage() {

		return "member/member_findPwd";
	}

	@RequestMapping("member_findId.do")
	public void memberFindId(@RequestParam("name") String name, @RequestParam("email") String email, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<String, String> nameEmail = new HashMap<String, String>();
		nameEmail.put("name", name);
		nameEmail.put("email", email);
		MemberDTO dto = this.dao.memberFindId(nameEmail);
		int check = 0;

		if (dto != null) {
			check = 1;
		}

		if (check == 1) {
			// 이름, 이메일이 있는경우
			out.println("<script>");
			out.println("alert('회원의 아이디는 " + dto.getId() + " 입니다.')");
			out.println("location.href ='member_main.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('등록된 회원의 정보가 없습니다.')");
			out.println("location.href ='member_main.do'");
			out.println("</script>");
		}
	}

	@RequestMapping("member_findPwd.do")
	public void memberFindPwd(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("email") String email, HttpServletResponse response) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<String, String> idNameEmail = new HashMap<String, String>();
		idNameEmail.put("id", id);
		idNameEmail.put("name", name);
		idNameEmail.put("email", email);
		MemberDTO dto = this.dao.memberFindPwd(idNameEmail);
		int check = 0;

		if (dto != null) {
			check = 1;
		}

		if (check == 1) {
			// 아이디, 이름, 폰번호가 있는경우
			out.println("<script>");
			out.println("alert('회원의 비밀번호는 " + dto.getPassword() + " 입니다.')");
			out.println("location.href ='member_main.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('등록된 회원의 정보가 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping("member_myPage.do")
	public String memberMyPage(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.MemberCommunityCount(dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", dto.getNick_name());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());
		
		
		// 해당하는 게시물을 가져오는 메서드 호출
		List<CommunityDTO> memberCommunityList = this.dao.MemberCommunityList(pagingAndNickName);
		
		model.addAttribute("memberCommunityList", memberCommunityList).addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);

		return "member/member_community_list";
	}
	
	@RequestMapping("member_reply_list.do")
	public String memberReplyList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.MemberReplyCount(dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", dto.getNick_name());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());
		
		// 해당하는 게시물을 가져오는 메서드 호출
		List<Community_replyDTO> memberReplyList = this.dao.MemberReplyList(pagingAndNickName);
//		List<String> memberReplyTitle = this.dao.MemberReplyTitle(dto.getNick_name());
		model.addAttribute("memberReplyList", memberReplyList)
		//.addAttribute("memberReplyTitle", memberReplyTitle)
		.addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);
		System.out.println(memberReplyList);

		return "member/member_reply_list";
	}
		
		@RequestMapping("member_reply_content_list.do")
	public String memberReplyContentList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.MemberReplyCount(dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", dto.getNick_name());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());
		
		// 해당하는 게시물을 가져오는 메서드 호출
		List<CommunityDTO> memberReplyContentList = this.dao.MemberReplyContentList(pagingAndNickName);
//		List<String> memberReplyTitle = this.dao.MemberReplyTitle(dto.getNick_name());
		model.addAttribute("memberReplyContentList", memberReplyContentList)
		//.addAttribute("memberReplyTitle", memberReplyTitle)
		.addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);

		return "member/member_reply_content_list";
	}
	
	@RequestMapping("member_like_list.do")
	public String memberLikeList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.MemberLikeCount(dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", dto.getNick_name());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());

		// 해당하는 게시물을 가져오는 메서드 호출
		List<CommunityDTO> memberLikeList = this.dao.MemberLikeList(pagingAndNickName);
		model.addAttribute("memberLikeList", memberLikeList).addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);

		return "member/member_like_list";
	}
	
	@RequestMapping("member_play_list.do")
	public String memberPlayList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());
		
		// 해당하는 게시물을 가져오는 메서드 호출
		List<Play_InfoDTO> memberPlayList = this.dao.MemberPlayList(id);
		model.addAttribute("memberPlayList", memberPlayList).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);

		return "member/member_play_list";
	}

	@RequestMapping("member_community_list.do")
	public String memberCommunityList(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.MemberCommunityCount(dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", dto.getNick_name());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());
		
		
		// 해당하는 게시물을 가져오는 메서드 호출
		List<CommunityDTO> memberCommunityList = this.dao.MemberCommunityList(pagingAndNickName);
		model.addAttribute("memberCommunityList", memberCommunityList).addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);

		return "member/member_community_list";
	}
	
	@RequestMapping("member_point_list.do")
	public String memberPointList(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.pDao.memberPointCount(dto.getId());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndId = new HashMap<String, Object>();
		pagingAndId.put("pDto", pDto);
		pagingAndId.put("id", dto.getId());
		
		int memberCommunityCount = this.dao.MemberCommunityCount(dto.getNick_name());
		int memberPlayLikeCount = this.dao.MemberPlayLikeCount(dto.getId());
		int memberReplyCount = this.dao.MemberReplyCount(dto.getNick_name());
		int memberReplyContentCount = this.dao.MemberReplyContentCount(dto.getNick_name());
		int memberLikeCount = this.dao.MemberLikeCount(dto.getNick_name());

		// 해당하는 게시물을 가져오는 메서드 호출
		List<PointDTO> memberPointList = this.pDao.pointList(pagingAndId);
		model.addAttribute("memberPointList", memberPointList).addAttribute("Paging", pDto).addAttribute("nick_name", dto.getNick_name()).addAttribute("dto", dto).addAttribute("memberCommunityCount", memberCommunityCount).addAttribute("memberPlayLikeCount", memberPlayLikeCount).addAttribute("memberReplyCount", memberReplyCount).addAttribute("memberReplyContentCount", memberReplyContentCount).addAttribute("memberLikeCount", memberLikeCount);
		
		return "member/member_point_list";
	}

	@RequestMapping("member_befordModify.do")
	public String memberBeforeModify(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		model.addAttribute("dto", dto);

		return "member/member_beforeModify";
	}
	
	@RequestMapping("member_beforeModifyPwdCheck.do")
	public void memberBeforeModifyPwdCheck(MemberDTO dto, @RequestParam("db_pwd") String db_pwd, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (db_pwd.equals(dto.getPassword())) {
				out.println("<script>");
				out.println("alert('회원 인증 성공, 수정페이지로 이동합니다.')");
				out.println("location.href='member_modify.do'");
				out.println("</script>");
		} else { // 비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요.')");
			out.println("location.href='member_befordModify.do'");
			out.println("</script>");
		}
	}
	
	@RequestMapping("member_modify.do")
	public String memberModify(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		model.addAttribute("dto", dto);

		return "member/member_modify";
	}
	
	@RequestMapping(value = "/member_checkModifyNickName.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberModifyNickNameCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("nickName") String newNickName) throws IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		
		Gson gson = new Gson();
		
		Map<String, String> newNickNameCheck = new HashMap<String, String>();
		newNickNameCheck.put("nickName", dto.getNick_name());
		newNickNameCheck.put("newNickName", newNickName);
		
		int check = dao.memberModifyCheckNickName(newNickNameCheck);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}
	
	@RequestMapping(value = "/member_checkModifyEmail.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberModifyEmailCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("email") String newEmail) throws IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		
		Gson gson = new Gson();
		
		Map<String, String> newEmailCheck = new HashMap<String, String>();
		newEmailCheck.put("email", dto.getEmail());
		newEmailCheck.put("newEmail", newEmail);
		
		int check = dao.memberModifyCheckEmail(newEmailCheck);

		Map<String, Object> resultChkId = new HashMap<String, Object>();

		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}

	@RequestMapping(value = "/member_checkModifyPhone.do", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public void memberModifyPhoneCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("phone") String newPhone) throws IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		
		Gson gson = new Gson();
		
		Map<String, String> newPhoneCheck = new HashMap<String, String>();
		newPhoneCheck.put("phone", dto.getPhone());
		newPhoneCheck.put("newPhone", newPhone);
		
		int check = dao.memberModifyCheckEmail(newPhoneCheck);

		Map<String, Object> resultChkId = new HashMap<String, Object>();
		String result = "사용가능";

		if (check > 0) {
			result = "중복";
		}
		resultChkId.put("resultChk", result);

		response.getWriter().print(gson.toJson(resultChkId));
	}

	@RequestMapping("member_modify_ok.do")
	public void memberModifyOk(MemberDTO dto, HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int check = this.dao.memberModify(dto);
		
		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원 수정 성공')");
			out.println("location.href='member_myPage.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("member_modify_nickName.do")
	public String memberModifyNickName(@RequestParam("nick_name") String nick_name, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");

		model.addAttribute("nick_name", nick_name);

		return "member/member_modify_nickName";
	}
	
	@RequestMapping("member_modify_nickName_ok.do")
	public void memberModifyNickName(@RequestParam("nick_name") String nick_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Map<String, String> nickNameAndId = new HashMap<String, String>();
		nickNameAndId.put("nick_name", nick_name);
		nickNameAndId.put("id", id);

		if (dto.getPoint() < 20) {
			out.println("<script>");
			out.println("alert('포인트가 부족합니다.')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			int check = this.dao.memberModifyNickName(nickNameAndId);
			if (check > 0) {
				dto = this.dao.getMember(id);
				Map<String, Object> pointInsert = new HashMap<String, Object>();
				pointInsert.put("member_id", id);
				pointInsert.put("point_content", "닉네임 변경");
				pointInsert.put("point_sign", "-");
				pointInsert.put("point_score", 20);
				pointInsert.put("point_remanet", dto.getPoint());
				this.pDao.pointInsert(pointInsert);
				out.println("<script>");
				out.println("alert('닉네임 변경 성공')");
				out.println("location.href='member_modify.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('닉네임 변경 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
	}
	
	@RequestMapping("member_modify_email.do")
	public String memberModifyEmail(@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");

		model.addAttribute("email", email);

		return "member/member_modify_email";
	}
	
	@RequestMapping("member_modify_email_ok.do")
	public void memberModifyEmail(@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Map<String, String> emailAndId = new HashMap<String, String>();
		emailAndId.put("email", email);
		emailAndId.put("id", id);
		
		int check = this.dao.memberModifyEmail(emailAndId);
		
		if (check > 0) {
			out.println("<script>");
			out.println("alert('이메일 변경 성공')");
			out.println("location.href='member_modify.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('이메일 변경 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	@RequestMapping("member_delete.do")
	public String memberDelete(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO dto = this.dao.getMember(id);
		model.addAttribute("dto", dto);

		return "member/member_delete";
	}

	@RequestMapping("member_delete_ok.do")
	public void memberDeleteOk(MemberDTO dto, @RequestParam("db_pwd") String db_pwd, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (db_pwd.equals(dto.getPassword())) {
			int check = this.dao.memberDelete(id);
			if (check > 0) {
				session.invalidate();
				out.println("<script>");
				out.println("alert('회원 탈퇴 성공')");
				out.println("location.href='main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 탈퇴 실패')");
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
	
	@RequestMapping("member_profile_view.do")
	public String memberProfileView(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		model.addAttribute("user_nick_name", userNickName);
		
		return "member/member_profile_view";
	}
	
	@RequestMapping("member_community_profile_view.do")
	public String memberCommunityProfileView(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO user_dto = this.mDao.getMemberInfo(userNickName);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.getUserCommunityInfoCount(user_dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", user_dto.getNick_name());
		
		List<UserCommunityInfoViewDTO> userCommunityInfo = this.dao.getUserCommunityInfo(pagingAndNickName);
//		List<UserCommunityReplyInfoViewDTO> getUserCommunityReplyInfo = this.dao.getUserCommunityReplyInfo(userNickName);
		model.addAttribute("userCommunityInfo", userCommunityInfo).
		// addAttribute("UserCommunityReplyInfo", getUserCommunityReplyInfo).
		addAttribute("userNickName", userNickName).addAttribute("Paging", pDto);
		
		return "member/member_community_profile_view";
	}
	
	@RequestMapping("member_community_reply_profile_view.do")
	public String memberCommunityReplyProfileView(@RequestParam("user_nick_name") String userNickName, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("UserId");
		MemberDTO user_dto = this.mDao.getMemberInfo(userNickName);

		// 검색 페이징 처리 작업
		int page; // 현재 페이지 변수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			// 처음으로 "게시글 수" 를 클릭한 경우
			page = 1;
		}

		totalRecord = this.dao.getUserCommunityReplyInfoCount(user_dto.getNick_name());
		PageDTO pDto = new PageDTO(page, rowsize, totalRecord);

		Map<String, Object> pagingAndNickName = new HashMap<String, Object>();
		pagingAndNickName.put("pDto", pDto);
		pagingAndNickName.put("nickName", user_dto.getNick_name());
		
		List<UserCommunityReplyInfoViewDTO> userCommunityReplyInfo = this.dao.getUserCommunityReplyInfo(pagingAndNickName);
//		List<UserCommunityReplyInfoViewDTO> getUserCommunityReplyInfo = this.dao.getUserCommunityReplyInfo(userNickName);
		model.addAttribute("userCommunityReplyInfo", userCommunityReplyInfo).
		// addAttribute("UserCommunityReplyInfo", getUserCommunityReplyInfo).
		addAttribute("userNickName", userNickName).addAttribute("Paging", pDto);
		
		return "member/member_community_reply_profile_view";
	}
}
