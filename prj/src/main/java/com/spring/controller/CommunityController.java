package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.community.model.CommunityDAO;
import com.community.model.CommunityDTO;
import com.community.model.Community_likeDTO;
import com.community.model.Community_replyDTO;
import com.community.model.PageDTO;
import com.google.gson.Gson;
import com.manager.model.ManagerDAO;
import com.manager.model.ManagerDTO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.point.model.PointDAO;

@Controller
public class CommunityController {
	
	@Autowired
	private CommunityDAO dao;
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private ManagerDAO manager_dao;
	
	@Autowired
	private PointDAO pdao;
	
	private final int rowsize = 10;
	
	private int totalRecord = 0;

	@RequestMapping("community_main.do")
	public String communityList(HttpServletRequest request, 
								Model model, 
								Locale locale) {
		int page;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		
		totalRecord = dao.getListCount();
		PageDTO dto = new PageDTO(page, rowsize, totalRecord);
		List<CommunityDTO> list = dao.getCommunityList(dto);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = format.format(date);
		
		List<CommunityDTO> bestList = dao.communityBestList();
		
		model.addAttribute("nowDate", formattedDate ).addAttribute("List", list).addAttribute("Paging", dto).addAttribute("bestList", bestList);
		return "community/community_main";
	}
	
	@RequestMapping("community_write.do")
	public String communityWrite(Locale locale, 
								 HttpServletRequest request, 
								 HttpServletResponse response, 
								 Model model) throws IOException {
		String userId = (String)request.getSession().getAttribute("UserId");
		String managerId = (String)request.getSession().getAttribute("ManagerId");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		if(userId != null) {
			MemberDTO mdto = mdao.getMember(userId);
			model.addAttribute("serverTime", formattedDate ).addAttribute("mdto", mdto);
			return "community/community_write";
		} else if(managerId != null) {
			ManagerDTO manager_dto = manager_dao.getManager(managerId);
			model.addAttribute("serverTime", formattedDate ).addAttribute("mdto", manager_dto);
			return "community/community_write";
		} else {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.')");
			out.println("</script>");
			return "member/member_main";
		}
	}
	
	@RequestMapping("community_write_ok.do")
	public void communityWriteOk(CommunityDTO dto, 
								 HttpServletResponse response, 
								 MultipartHttpServletRequest mRequest) throws IOException {
//		Maven 또는 STS에서 getServletContext()를 사용하려고 할 경우, pom.xml에서 자바 api의 버전을 변경해줘야 합니다!
//		참고 링크: https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=javaking75&logNo=220072102739
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(mRequest.getServletContext().getRealPath("/WEB-INF/mapping.properties"));
//		mapping.properties 경로 "C:\NCS\workspace\final\prj\src\main\webapp\WEB-INF\mapping.properties"
		prop.load(new InputStreamReader(fis));
		fis.close();
		
		String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
//		시스템 경로 가져오기
		
//		getFileNames(): 업로드된 파일의 이름 목록 제공
		Iterator<String> iterator = mRequest.getFileNames();
		
		while(iterator.hasNext()) {
			String uploadFileName = iterator.next();
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String homedir = uploadPath +"/community/"+ mRequest.getParameter("writer_id");
//			로그인 세션에 올라와있는 id 가져오기
			File path1 = new File(homedir);                          
			
			if(!path1.exists()) {
				path1.mkdirs();
			}
			
			String saveFileName = mFile.getOriginalFilename();
			dto.setCommunity_file(saveFileName);
//			community_file 컬럼의 내용을 실제 첨부파일 이름으로 넣기
			
			if(!saveFileName.equals("")) {
				try {
					File origin = new File(homedir + "/" + saveFileName);
//					transferTo(): 파일데이터를 지정한 폴더로 실제 저장시키는 메서드
					mFile.transferTo(origin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int check;
		
		if(dto.getCommunity_head().equals("공지")) {
			dto.setCommunity_notice(1);
			check = dao.communityManagerInsert(dto);
			System.out.println("관리자 글 등록");
			if(check > 0) {
				ManagerDTO mDto = this.manager_dao.getManager(dto.getCommunity_manager());
				Map<String, Object> managerActionInsert = new HashMap<String, Object>();
				managerActionInsert.put("manager_id", mDto.getManager_id());
				managerActionInsert.put("manager_action_content", "공지글 작성");
				this.manager_dao.managerActionInsert(managerActionInsert);
				out.println("<script>");
				out.println("alert('게시글 등록 성공')");
				out.println("location.href='community_main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			dto.setCommunity_notice(0);
			check = dao.communityMemberInsert(dto);
			if(check > 0) {
				out.println("<script>");
				out.println("alert('게시글 등록 성공')");
				out.println("location.href='community_main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}
	}
	
	@RequestMapping("community_content.do")
	public String communityCont(@RequestParam("no") int no, 
								@RequestParam("page") int nowPage, 
								HttpServletRequest request, 
								Model model) {
		dao.readCount(no); // 조회수 증가
		int likeCount = dao.likeCount(no); // 좋아요 수 세기
		CommunityDTO dto = dao.communityCont(no); // 게시글 dto 구하기
		String uploadFolder = dao.getUploadFolder(dto.getCommunity_writer()); // 작성자 닉네임으로 id 구하기
		String id = (String)request.getSession().getAttribute("UserId"); // 현재 로그인된 사용자의 id
		MemberDTO mdto = mdao.getMember(id); // 현재 로그인된 사용자의 dto
		
		String nick_name = "";
		
		if(id != null) {
			nick_name = mdto.getNick_name();
		}
		
		List<Community_replyDTO> rList = dao.replyList(no);
		Map<String, String> map = new HashMap<String, String>();
		map.put("community_num", Integer.toString(no));
		map.put("member_nick_name", nick_name);
		
		// 로그인된 사용자가 해당 게시물에 좋아요를 눌렀나요?
		int likeCheck = dao.likeCheck(map);
		
		model.addAttribute("Cont", dto).
			  addAttribute("mdto", mdto).
			  addAttribute("Page", nowPage).
			  addAttribute("uploadFolder", uploadFolder).
			  addAttribute("likeCount", likeCount).
			  addAttribute("likeCheck", likeCheck).
			  addAttribute("rList", rList);
			  
		return "community/community_content";
	}
	
	// 좋아요 클릭시 실행 메서드
	@ResponseBody
	@RequestMapping("community_likeInsert.do")
	public void communityLikeInsert(@RequestParam("community_num") int community_num, 
									HttpServletRequest request, 
									HttpServletResponse response, 
									CommunityDTO dto, 
									Model model) throws IOException {
		
		dto = dao.communityCont(community_num);
		String id = (String)request.getSession().getAttribute("UserId");
		MemberDTO mdto = mdao.getMember(id);
		
		String member_nick_name = "";
		
		if(id != null) {
			member_nick_name = mdto.getNick_name();
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		if(id != null) {
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("community_like", Integer.toString(dto.getCommunity_like()));
			map1.put("community_num", Integer.toString(dto.getCommunity_num()));
			dao.likeInsert(map1);
			
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("community_num", Integer.toString(dto.getCommunity_num()));
			map2.put("member_nick_name", member_nick_name);
			dao.likeInsertRow(map2);
			
//			글 작성자에게 2포인트 더해줘
			String community_writer_id = dao.getUploadFolder(dto.getCommunity_writer());
			dao.pointInsert(community_writer_id);
//			포인트 테이블에 데이터 더해줘
			Map<String, Object> pointInsert = new HashMap<String, Object>();
			pointInsert.put("member_id", mdto.getId());
			pointInsert.put("point_content", "글 추천 포인트");
			pointInsert.put("point_sign", "+");
			pointInsert.put("point_score", 2);
			pointInsert.put("point_remanet", mdto.getPoint());
			pdao.pointInsert(pointInsert);
			
			int likeCountUpdate = dao.likeCount(community_num);
			out.println(likeCountUpdate);
		} else {
			out.println(-1);
		}
	}
	
	@ResponseBody
	@RequestMapping("community_likeDelete.do")
	public void communityLikeDelete(@RequestParam("community_num") int community_num, 
									HttpServletResponse response, 
									HttpServletRequest request, 
									CommunityDTO dto) throws IOException {
		String id = (String)request.getSession().getAttribute("UserId");
		MemberDTO mdto = mdao.getMember(id);
		String member_nick_name = mdto.getNick_name();
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		dto = dao.communityCont(community_num);
		
		if(id != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("community_num", Integer.toString(community_num));
			map.put("member_nick_name", member_nick_name);
			dao.likeDelete(community_num);
			dao.likeDeleteRow(map);
			dao.updateLikeSequence(community_num);
			
			int likeCountUpdate = dao.likeCount(community_num);
			out.println(likeCountUpdate);
		} else {
			out.println(-1);
		}
	}
	
	@RequestMapping("community_modify.do")
	public String communityUpdate(@RequestParam("no") int no, 
								  @RequestParam("page") int page, 
								  Model model) {
		CommunityDTO dto = dao.communityCont(no);
		String uploadFolder = dao.getUploadFolder(dto.getCommunity_writer()); // 작성자 닉네임으로 id 구하기
		model.addAttribute("Modify", dto).addAttribute("Page",  page).addAttribute("uploadFolder", uploadFolder);
		return "community/community_modify";
	}
	
	@RequestMapping("community_modify_ok.do")
	public void communityUpdateOk(CommunityDTO dto, 
								  HttpServletResponse response, 
								  MultipartHttpServletRequest mRequest, 
								  @RequestParam("page") int nowPage) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(mRequest.getServletContext().getRealPath("/WEB-INF/mapping.properties"));
		prop.load(new InputStreamReader(fis));
		fis.close();
		String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
		Iterator<String> iterator = mRequest.getFileNames();
		
		while(iterator.hasNext()) {
			String uploadFileName = iterator.next();
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String homedir = uploadPath +"/community/"+ mRequest.getParameter("writer_id");
			File path1 = new File(homedir);                          
			
			if(!path1.exists()) {
				path1.mkdirs();
			}
			
			String saveFileName = mFile.getOriginalFilename();
			dto.setCommunity_file(saveFileName);
			
			if(!saveFileName.equals("")) {
				try {
					File origin = new File(homedir + "/" + saveFileName);
					System.out.println(origin);
					if(!origin.exists()) {
						mFile.transferTo(origin);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int check = dao.updateCommunity(dto);
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시글 수정 성공')");
			out.println("location.href='community_content.do?no=" + dto.getCommunity_num() + "&page=" + nowPage +"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("community_delete.do")
	public String communityDelete(@RequestParam("no") int no, 
						 @RequestParam("page") int nowPage, 
						 HttpServletResponse response, 
						 HttpServletRequest request,
						 Model model) throws IOException {
		CommunityDTO dto = dao.communityCont(no);
		String id = (String)request.getSession().getAttribute("UserId");
		MemberDTO mdto = mdao.getMember(id);
		model.addAttribute("Delete", dto).
			  addAttribute("Page", nowPage).
			  addAttribute("mdto", mdto);
		return "community/community_delete";
	}
	
	@RequestMapping("community_delete_ok.do")
	public void communityDeleteOk(@RequestParam("community_num") int no, 
						 @RequestParam("page") int nowPage,
						 @RequestParam("db_pwd") String db_pwd,
						 HttpServletResponse response,
						 HttpServletRequest request,
						 Model model) throws IOException {
		String id = (String)request.getSession().getAttribute("UserId");
		MemberDTO mdto = mdao.getMember(id);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(db_pwd.equals(mdto.getPassword())) {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(request.getServletContext()
					.getRealPath("/WEB-INF/mapping.properties"));
			prop.load(new InputStreamReader(fis));
			fis.close();
			
			String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
			final String uploadDirectory = uploadPath +"/community/"+ id;
			CommunityDTO dto = dao.communityCont(no);
			String fileName = dto.getCommunity_file();
			File file = new File(uploadDirectory +"/"+ fileName);
			file.delete();
			
			int check = dao.deleteCommunity(no);
			
			if(check > 0) {
				this.dao.updateSequence(no);
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='community_main.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시글 삭제에 실패헸습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("community_delete_ok_manager.do")
	public void communityDeleteOkManager(@RequestParam("no") int no, 
						 @RequestParam("page") int nowPage,
						 HttpServletResponse response,
						 HttpServletRequest request,
						 Model model) throws IOException {
		
		CommunityDTO dto = dao.communityCont(no);
		String community_writer_nick_name = dto.getCommunity_writer();
		String community_writer_id = dao.getUploadFolder(community_writer_nick_name);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(request.getServletContext()
				.getRealPath("/WEB-INF/mapping.properties"));
		prop.load(new InputStreamReader(fis));
		fis.close();
		
		String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
		final String uploadDirectory = uploadPath +"/community/"+ community_writer_id;
		
		String fileName = dto.getCommunity_file();
		File file = new File(uploadDirectory +"/"+ fileName);
		file.delete();
		
		int check = dao.deleteCommunity(no);
		
		if(check > 0) {
			this.dao.updateSequence(no);
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다.')");
			out.println("location.href='community_main.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패헸습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

	
	@RequestMapping("community_search.do")
	public String communitySearch(@RequestParam("head") String head,
								  @RequestParam("field") String field,
								  @RequestParam("keyword") String keyword,
								  HttpServletRequest request,
								  Model model) {
//		현재 페이지 변수
		int page;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
//			게시판 처음 화면: 1페이지
			page = 1;
		}
		
		List<CommunityDTO> bestList = dao.communityBestList();
		
//		검색분류와 검색어에 해당하는 게시글의 수를 DB에서 확인하는 작업
		Map<String, String> map = new HashMap<String, String>();
		map.put("head", head);
		map.put("field", field);
		map.put("keyword", keyword);
		
		totalRecord = dao.searchCount(map);
		PageDTO pdto = new PageDTO(page, rowsize, totalRecord, head, field, keyword);
		System.out.println(totalRecord);
		
//		검색시 한 페이지당 보여질 게시물의 수만큼 검색한 게시물을 List로 가져오는 메서드 호출
		List<CommunityDTO> searchList = dao.searchList(pdto);
		model.addAttribute("searchPageList", searchList).
			  addAttribute("bestList", bestList).
			  addAttribute("Paging", pdto);
		return "community/community_search_list";
	}
	
	@RequestMapping("community_search_cont.do")
	public String communitySearchCont(Model model,
									  @RequestParam("no") int no,
									  @RequestParam("page") int nowPage,
									  @RequestParam("head") String head,
								 	  @RequestParam("field") String field,
									  @RequestParam("keyword") String keyword) {
		CommunityDTO dto = dao.communityCont(no);
		this.dao.readCount(no);
		model.addAttribute("sCont", dto).
			  addAttribute("Page", nowPage).
			  addAttribute("Head", head).
			  addAttribute("Field", field).
			  addAttribute("Keyword", keyword);
		
		return "community/community_search_cont";
	}
	
	@ResponseBody
	@RequestMapping(value = "/community_replyInsert.do", method = RequestMethod.GET, produces = "application/json")
	public void communityReplyInsert(@RequestParam("community_num") int community_num,
									 @RequestParam("reply_num") int reply_num,
									 @RequestParam("reply_content") String reply_content,
									 @RequestParam("re_reply_content") String re_reply_content,
									 @RequestParam("reply_writer") String reply_writer,
									 @RequestParam("re_reply_writer") String re_reply_writer,
									 @RequestParam("reply_manager") String reply_manager,
									 HttpServletRequest request,
									 HttpServletResponse response, 
									 CommunityDTO cdto,
									 Community_replyDTO rdto,
									 Model model) throws IOException {
// 		댓글 Insert
		rdto.setReply_community_num(community_num);
		
		if(reply_writer.equals("관리자")) {
			rdto.setReply_manager(reply_manager);
			if(reply_num == 0) {
//				댓글인 경우
				rdto.setReply_content(reply_content);
				rdto.setReply_depth(0);
				rdto.setReply_group(dao.replyMaxNum()+1);
			} else {
//				대댓글인 경우
				rdto.setReply_content(re_reply_content);
				rdto.setReply_depth(1);
				
//				대대댓글 이상인 경우 group을 대댓글의 group과 같게 설정
				Community_replyDTO rrdto = dao.replyCont(reply_num);
				if(rrdto.getReply_depth() == 1) {
					rdto.setReply_group(rrdto.getReply_group());
				} else {
					rdto.setReply_group(reply_num);
				}
			}
			dao.replyManagerInsert(rdto);
		} else {
			if(reply_num == 0) {
//				댓글인 경우
				rdto.setReply_writer(reply_writer);
				rdto.setReply_content(reply_content);
				rdto.setReply_depth(0);
				rdto.setReply_group(dao.replyMaxNum()+1);
			} else {
//				대댓글인 경우
				rdto.setReply_writer(re_reply_writer);
				rdto.setReply_content(re_reply_content);
				rdto.setReply_depth(1);
				
//				대대댓글 이상인 경우 group을 대댓글의 group과 같게 설정
				Community_replyDTO rrdto = dao.replyCont(reply_num);
				if(rrdto.getReply_depth() == 1) {
					rdto.setReply_group(rrdto.getReply_group());
				} else {
					rdto.setReply_group(reply_num);
				}
			}
			dao.replyMemberInsert(rdto);
		}
		dao.replyCountUpdate(community_num);
		Gson gson = new Gson();
		List<Community_replyDTO> list = dao.replyList(community_num);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(gson.toJson(list));
	}
	
	@ResponseBody
	@RequestMapping(value = "/community_likedMember.do", method = RequestMethod.GET, produces = "application/json")
	public void communityLikedMembers(@RequestParam("community_num") int community_num,
									  HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Gson gson = new Gson();
		// 해당 게시물에 좋아요 누른 사람들 조회
		List<Community_likeDTO> list = dao.likedMember(community_num);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(gson.toJson(list));
	}
	
	@RequestMapping("community_writerList.do")
	public String communityWriterList(@RequestParam("writer_nick") String writer_nick,
									  HttpServletRequest request, Model model) {
		int page;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		int writerListCount = dao.getWriterListCount(writer_nick);
		totalRecord = writerListCount;
		PageDTO dto = new PageDTO(page, rowsize, totalRecord);
		List<CommunityDTO> list = dao.getCommunityWriterList(writer_nick);
		model.addAttribute("writerListCount", writerListCount).addAttribute("list", list).addAttribute("Paging", dto);;
		return "community/community_writer_list";
	}
	
	@RequestMapping("community_replyModify.do")
	public void communityReplyModify(@RequestParam("reply_num") int reply_num, 
									 @RequestParam("community_num") int community_num, 
									 @RequestParam("reply_content") String reply_content, 
									 HttpServletResponse response, Community_replyDTO dto, Model model) throws IOException {
		dto.setReply_content(reply_content);
		dao.replyModify(dto);
		Gson gson = new Gson();
		List<Community_replyDTO> list = dao.replyList(community_num);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(gson.toJson(list));
	}
	
	@RequestMapping("community_replyDelete.do")
	public void communityReplyDelete(@RequestParam("rno") int rno, 
									 @RequestParam("cno") int cno, 
									 @RequestParam("rdepth") int rdepth, 
									 @RequestParam("rgroup") int rgroup, 
									 Model model, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		int reply_groupCount = dao.replyGroupCount(rgroup);
		
		if(rdepth == 0) { // 댓글이고 대댓글이 하나도 없는 경우
			if(reply_groupCount == 1) {
				dao.replyDeleteAll(rno);
				dao.updateReplySequence(rno);
				dao.replyCountUpdate(cno);
			} else { // 댓글이고 대댓글이 달려있는 경우
				dao.replyDeleteMent(rno);
			}
		} else { // 삭제할 객체가 대댓글인 경우
			dao.replyDeleteAll(rno);
			dao.updateReplySequence(rno);
			dao.replyCountUpdate(cno);
		} 
	}
}
