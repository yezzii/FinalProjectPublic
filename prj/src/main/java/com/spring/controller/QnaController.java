package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
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

import com.community.model.PageDTO;
import com.google.gson.Gson;
import com.manager.model.ManagerDAO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.qna.model.FaqDAO;
import com.qna.model.FaqDTO;
import com.qna.model.Faq_CategoryDTO;
import com.qna.model.QnaDAO;
import com.qna.model.QnaDTO;
import com.qna.model.faqJoinDTO;

@Controller
public class QnaController {
	
	@Autowired
	private FaqDAO fdao;
	
	@Autowired
	private QnaDAO qdao;
	
	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private ManagerDAO manager_dao;
	
	private final int rowsize = 10;
	
	private int totalRecord = 0;
	
	@RequestMapping("qna_main.do")
	public String qnaMain(Model model) {
		List<Faq_CategoryDTO> faq_clist = fdao.faq_categoryName();
		List<FaqDTO> faq_list = fdao.faqList();
		model.addAttribute("faq_clist", faq_clist).
			  addAttribute("faq_list", faq_list);
		return "qna/qna_main";
	}                                                                                                                                                          
	
	@RequestMapping("qna_board.do")
	public String qnaBoard(HttpServletRequest request, Model model, Locale locale) {
		int page;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		totalRecord = qdao.qnaCount();
		PageDTO dto = new PageDTO(page, rowsize, totalRecord);
		List<QnaDTO> list = qdao.qnaList(dto);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate ).addAttribute("List", list).addAttribute("Paging", dto);
		return "qna/qna_board";
	}
	
	@RequestMapping("qna_write.do")
	public String qnaWrite(Model model, HttpServletRequest request, HttpServletResponse response) {
		
		String id = (String)request.getSession().getAttribute("UserId");
		response.setContentType("text/html; charset=UTF-8");
		MemberDTO mdto = mdao.getMember(id);
		model.addAttribute("mdto", mdto);
		return "qna/qna_write";
	}
	
	@RequestMapping("qna_write_ok.do")
	public void qnaWriteOk(Model model, MultipartHttpServletRequest mRequest, HttpServletResponse response, QnaDTO dto) throws IOException {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(mRequest.getServletContext().getRealPath("/WEB-INF/mapping.properties"));
			prop.load(new InputStreamReader(fis));
			fis.close();
			String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
			
			Iterator<String> iterator = mRequest.getFileNames();
			while(iterator.hasNext()) {
				String uploadFileName = iterator.next();
				MultipartFile mFile = mRequest.getFile(uploadFileName);
				String homedir = uploadPath +"/qna";

				File path1 = new File(homedir);                          
				if(!path1.exists()) {
					path1.mkdirs();
				}
				String saveFileName = mFile.getOriginalFilename();
				dto.setQna_file(saveFileName);
				
				if(!saveFileName.equals("")) {
					try {
						File origin = new File(homedir + "/" + saveFileName);
						mFile.transferTo(origin);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			int groupNo = qdao.qnaMaxNum()+1;
			dto.setQna_groupNo(groupNo);
			
			int check = qdao.qnaInsert(dto);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(check > 0) {
				// 사용자 신고 있을 경우 받기
				if(dto.getQna_head().equals("사용자 신고")) {
					String qna_reportedUser = mRequest.getParameter("qna_reportedUser");
					int qna_num = this.qdao.qnaMaxNum();
					Map<String, Object> reportInfo = new HashMap<String, Object>();
					reportInfo.put("member_id", dto.getQna_writer());
					reportInfo.put("qna_num", qna_num);
					reportInfo.put("qna_reportedUser", qna_reportedUser);
					this.manager_dao.reportedUser(reportInfo);
				}
				out.println("<script>");
				out.println("alert('1:1 문의 등록 성공')");
				out.println("location.href='qna_board.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('1:1 문의 등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
	}
	
	@ResponseBody
	@RequestMapping(value = "/qna_userSearch.do", method = RequestMethod.GET, produces = "application/json")
	public void qnaUserSearch(@RequestParam("keyword") String keyword, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		List<MemberDTO> list = qdao.qnaSearchUserList(keyword);
		System.out.println(list);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(gson.toJson(list));
	}
	
	@RequestMapping("qna_reply.do")
	public String qnaReply(Model model, HttpServletRequest request, HttpServletResponse response) {
		int original_qna_num = Integer.parseInt(request.getParameter("no"));
		int groupNo = Integer.parseInt(request.getParameter("group_no"));
		int groupOrd = Integer.parseInt(request.getParameter("group_ord"));
		int depthNo = Integer.parseInt(request.getParameter("depth_no"));
		QnaDTO original_qna_dto = qdao.qnaContent(original_qna_num);
		response.setContentType("text/html; charset=UTF-8");
		model.addAttribute("original_qna_dto", original_qna_dto).
			  addAttribute("groupNo", groupNo).
			  addAttribute("groupOrd", groupOrd).
			  addAttribute("depthNo", depthNo);
		return "qna/qna_reply";
	}
	
	@RequestMapping("qna_reply_ok.do")
	public void qnaReplyOk(Model model, MultipartHttpServletRequest mRequest, HttpServletResponse response, QnaDTO dto) throws IOException {
		int groupNo = Integer.parseInt(mRequest.getParameter("groupNo"));
		int groupOrd = Integer.parseInt(mRequest.getParameter("groupOrd"));
		response.setContentType("text/html; charset=UTF-8");
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(mRequest.getServletContext().getRealPath("/WEB-INF/mapping.properties"));
		prop.load(new InputStreamReader(fis));
		fis.close();
		String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
		
		Iterator<String> iterator = mRequest.getFileNames();
		while(iterator.hasNext()) {
			String uploadFileName = iterator.next();
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String homedir = uploadPath +"/qna";
//				로그인 세션에 올라와있는 id 가져오기
			File path1 = new File(homedir);                          
			if(!path1.exists()) {
				path1.mkdirs();
			}
			String saveFileName = mFile.getOriginalFilename();
			dto.setQna_file(saveFileName);
//				community_file 컬럼의 내용을 실제 첨부파일 이름으로 넣기
			if(!saveFileName.equals("")) {
				try {
					File origin = new File(homedir + "/" + saveFileName);
					System.out.println(origin);
//						transferTo(): 파일데이터를 지정한 폴더로 실제 저장시키는 메서드
					mFile.transferTo(origin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		dto.setQna_groupNo(groupNo);
		dto.setQna_depthNo(1);
		dto.setQna_groupOrd(groupOrd + 1);
		
		int check = qdao.qnaInsertReply(dto);
		qdao.qnaUpdateState(dto.getQna_groupNo());
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('1:1 문의 답글 등록 성공')");
			out.println("location.href='qna_board.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('1:1 문의 답글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("qna_pwdCheck.do")
	public String qnaPwdCheck(@RequestParam("no") int no, @RequestParam("page") int nowPage, HttpServletRequest request, HttpServletResponse response, Model model) {
		QnaDTO dto = qdao.qnaContent(no);
		response.setContentType("text/html; charset=UTF-8");
		model.addAttribute("qnaDto", dto).
			  addAttribute("Page", nowPage);
		return "qna/qna_pwdCheck";
	}
	
	@RequestMapping("qna_pwdCheckOk.do")
	public void qnaPwdCheckOk(QnaDTO dto, @RequestParam("db_pwd") String db_pwd, @RequestParam("page") int nowPage, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(db_pwd.equals(dto.getQna_password())) {
			out.println("<script>");
			out.println("location.href='qna_content.do?no="+dto.getQna_num()+"&page="+nowPage+"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("qna_content.do")
	public String qnaContent(@RequestParam("no") int no, @RequestParam("page") int nowPage, HttpServletResponse response, Model model) throws IOException {
		QnaDTO dto = qdao.qnaContent(no);
		response.setContentType("text/html; charset=UTF-8");
		model.addAttribute("Cont", dto).
			  addAttribute("Page", nowPage);
		return "qna/qna_content";
	}
	
	@RequestMapping("qna_modify.do")
	public String qnaModify(@RequestParam("no") int no, @RequestParam("page") int page, Model model) {
		QnaDTO dto = qdao.qnaContent(no);
		model.addAttribute("Modify", dto).
			  addAttribute("Page",  page);
		return "qna/qna_modify";
	}
	
	@RequestMapping("qna_modify_ok.do")
	public void qnaModifyOk(QnaDTO dto, HttpServletResponse response, @RequestParam("page") int nowPage) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int check = qdao.qnaUpdate(dto);
		if(check > 0) {
			out.println("<script>");
			out.println("alert('게시글 수정 성공')");
			out.println("location.href='qna_content.do?no=" + dto.getQna_num() + "&page=" + nowPage +"'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
	
	@RequestMapping("qna_delete.do")
	public void qnaDelete(@RequestParam("no") int no, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(request.getServletContext().getRealPath("/WEB-INF/mapping.properties"));
			prop.load(new InputStreamReader(fis));
			fis.close();
			
			String uploadPath = prop.getProperty(System.getenv("USERPROFILE").substring(3));
			final String uploadDirectory = uploadPath +"/qna";
			QnaDTO dto = qdao.qnaContent(no);
			String fileName = dto.getQna_file();
			File file = new File(uploadDirectory +"/"+ fileName);
			file.delete();
			
			int check = qdao.qnaDelete(no);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(check > 0) {
				qdao.qnaUpdateSequence(no);
				out.println("<script>");
				out.println("alert('문의글이 삭제되었습니다.')");
				out.println("location.href='qna_board.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('문의글이 삭제에 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
	}
	
	@RequestMapping("qna_search.do")
	public String qnaSearch(@RequestParam("head") String head, @RequestParam("field") String field, @RequestParam("keyword") String keyword, HttpServletRequest request, Model model) {
		int page;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("head", head);
		map.put("field", field);
		map.put("keyword", keyword);
		
		totalRecord = qdao.qnaSearchCount(map);
		PageDTO pdto = new PageDTO(page, rowsize, totalRecord, head, field, keyword);
		System.out.println(totalRecord);
		
		List<QnaDTO> searchList = qdao.qnaSearchList(pdto);
		model.addAttribute("searchPageList", searchList).
			  addAttribute("Paging", pdto);
		return "qna/qna_search_list";
	}
	
	@RequestMapping("qna_search_cont.do")
	public String qnaSearchCont(Model model, @RequestParam("no") int no, @RequestParam("page") int nowPage, @RequestParam("head") String head, @RequestParam("field") String field, @RequestParam("keyword") String keyword) {
		QnaDTO dto = qdao.qnaContent(no);
		model.addAttribute("sCont", dto).
			  addAttribute("Page", nowPage).
			  addAttribute("Head", head).
			  addAttribute("Field", field).
			  addAttribute("Keyword", keyword);
		return "qna/qna_search_cont";
	}
	
	@ResponseBody
	@RequestMapping(value = "/qna_categoryList.do", method = RequestMethod.GET, produces = "application/json")
	public void communityLikedMembers(@RequestParam("faq_category") int faq_category,
									  HttpServletRequest request,
									  HttpServletResponse response, 
									  Model model) throws IOException {
		Gson gson = new Gson();
		// 해당 카테고리에 해당하는 faq 조회
		List<faqJoinDTO> list = qdao.qnaCategoryList(faq_category);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(gson.toJson(list));
	}
}
