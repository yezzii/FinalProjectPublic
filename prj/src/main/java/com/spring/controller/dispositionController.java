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

import com.community.model.CommunityDAO;
import com.disposition.model.DispositionDTO;
import com.disposition.model.DispositionResultDAO;
import com.disposition.model.DispositionResultDTO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.mysql.cj.Session;
import com.play.model.Play_CodeDTO;
import com.play.model.Play_InfoDTO;
import com.point.model.PointDAO;

@Controller
public class dispositionController {

	@Autowired
	private DispositionResultDAO dao;

	@Autowired
	private PointDAO pDao;

	@Autowired
	private MemberDAO mDao;
	
	@RequestMapping("dispositionReset.do")
	public String dispositionReset(@RequestParam("id") String id, HttpServletResponse response) {
		
		int check = this.pDao.Disposition_Reset(id);
		
		return "main";
	}
	
	@RequestMapping("disposition0.do")
	public String disposition0(@RequestParam("id") String id, @RequestParam("no") int no, DispositionResultDTO Rdto,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		Rdto.setResult_member_id(id);

		DispositionDTO dto = this.dao.selectDisposition(no);


		model.addAttribute("dto", dto);

		// 포인트 내역
		MemberDTO mDto = this.mDao.getMember(id);

		if (mDto.getPoint() < 5) {
			out.println("<script>");
			out.println("alert('포인트가 부족합니다.')");
			out.println("location.href='main.do'");
			out.println("</script>");
			
			return null;
		}else {
			int check = this.dao.deleteDisposition_result(id);
			
			int check2 = this.dao.insertDisposition_result(Rdto);
			
			int check3 = this.pDao.Disposition_Start(id);
			
			Map<String, Object> pointInsert = new HashMap<String, Object>();
			pointInsert.put("member_id", id);
			pointInsert.put("point_content", "성향 검사 사용");
			pointInsert.put("point_sign", "-");
			pointInsert.put("point_score", 5);
			pointInsert.put("point_remanet", mDto.getPoint());
			this.pDao.pointInsert(pointInsert);
			return "./disposition/disposition1";
			
		}
		

	}

	// 1 => 2 ====================================================

	@RequestMapping("dispositionA_1_1.do")
	public String dispositionA1(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition2";

	}

	@RequestMapping("dispositionA_1_2.do")
	public String dispositionB1(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition2";

	}

	@RequestMapping("dispositionA_1_3.do")
	public String dispositionC1(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition2";

	}

	// 2 => 3 ====================================================

	@RequestMapping("dispositionA_2_1.do")
	public String dispositionA2(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition3";

	}

	@RequestMapping("dispositionA_2_2.do")
	public String dispositionB2(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition3";

	}

	@RequestMapping("dispositionA_2_3.do")
	public String dispositionC2(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition3";

	}

	// 3 => 4 ====================================================

	@RequestMapping("dispositionA_3_1.do")
	public String dispositionA3(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition4";

	}

	@RequestMapping("dispositionA_3_2.do")
	public String dispositionB3(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition4";

	}

	@RequestMapping("dispositionA_3_3.do")
	public String dispositionC3(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition4";

	}

	// 4 => 5 ====================================================

	@RequestMapping("dispositionA_4_1.do")
	public String dispositionA4(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition5";

	}

	@RequestMapping("dispositionA_4_2.do")
	public String dispositionB4(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition5";

	}

	@RequestMapping("dispositionA_4_3.do")
	public String dispositionC4(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition5";

	}

	// 5 => 6 ====================================================

	@RequestMapping("dispositionA_5_1.do")
	public String dispositionA5(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultAscore(id);

		return "./disposition/disposition6";

	}

	@RequestMapping("dispositionA_5_2.do")
	public String dispositionB5(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultAscore(id);

		return "./disposition/disposition6";

	}

	@RequestMapping("dispositionA_5_3.do")
	public String dispositionC5(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultA_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultAscore(id);

		return "./disposition/disposition6";

	}

	// 6 => 7 ====================================================

	@RequestMapping("dispositionB_1_1.do")
	public String dispositionA6(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition7";

	}

	@RequestMapping("dispositionB_1_2.do")
	public String dispositionB6(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition7";

	}

	@RequestMapping("dispositionB_1_3.do")
	public String dispositionC6(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition7";

	}

	// 7 => 8 ====================================================

	@RequestMapping("dispositionB_2_1.do")
	public String dispositionA7(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition8";

	}

	@RequestMapping("dispositionB_2_2.do")
	public String dispositionB7(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition8";

	}

	@RequestMapping("dispositionB_2_3.do")
	public String dispositionC7(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition8";

	}

	// 8 => 9 ====================================================

	@RequestMapping("dispositionB_3_1.do")
	public String dispositionA8(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition9";

	}

	@RequestMapping("dispositionB_3_2.do")
	public String dispositionB8(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition9";

	}

	@RequestMapping("dispositionB_3_3.do")
	public String dispositionC8(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition9";

	}

	// 9 => 10 ====================================================

	@RequestMapping("dispositionB_4_1.do")
	public String dispositionA9(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition10";

	}

	@RequestMapping("dispositionB_4_2.do")
	public String dispositionB9(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition10";

	}

	@RequestMapping("dispositionB_4_3.do")
	public String dispositionC9(@RequestParam("id") String id, @RequestParam("no") int no, HttpServletResponse response,
			Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition10";

	}

	// 10 => 11 ====================================================

	@RequestMapping("dispositionB_5_1.do")
	public String dispositionA10(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultBscore(id);

		return "./disposition/disposition11";

	}

	@RequestMapping("dispositionB_5_2.do")
	public String dispositionB10(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultBscore(id);

		return "./disposition/disposition11";

	}

	@RequestMapping("dispositionB_5_3.do")
	public String dispositionC10(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultB_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		int check2 = this.dao.updateDisposition_resultBscore(id);

		return "./disposition/disposition11";

	}

	// 11 => 12 ====================================================

	@RequestMapping("dispositionC_1_1.do")
	public String dispositionA11(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition12";

	}

	@RequestMapping("dispositionC_1_2.do")
	public String dispositionB11(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition12";

	}

	@RequestMapping("dispositionC_1_3.do")
	public String dispositionC11(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition12";

	}

	// 12 => 13 ====================================================

	@RequestMapping("dispositionC_2_1.do")
	public String dispositionA12(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition13";

	}

	@RequestMapping("dispositionC_2_2.do")
	public String dispositionB12(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition13";

	}

	@RequestMapping("dispositionC_2_3.do")
	public String dispositionC12(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition13";

	}

	// 13 => 14 ====================================================

	@RequestMapping("dispositionC_3_1.do")
	public String dispositionA13(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition14";

	}

	@RequestMapping("dispositionC_3_2.do")
	public String dispositionB13(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition14";

	}

	@RequestMapping("dispositionC_3_3.do")
	public String dispositionC13(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition14";

	}

	// 13 => 14 ====================================================

	@RequestMapping("dispositionC_4_1.do")
	public String dispositionA14(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_1(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition15";

	}

	@RequestMapping("dispositionC_4_2.do")
	public String dispositionB14(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_2(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition15";

	}

	@RequestMapping("dispositionC_4_3.do")
	public String dispositionC14(@RequestParam("id") String id, @RequestParam("no") int no,
			HttpServletResponse response, Model model) throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_3(id);

		DispositionDTO dto = this.dao.selectDisposition(no);

		model.addAttribute("dto", dto);

		return "./disposition/disposition15";

	}

	// 15 => 결과페이지 ====================================================

	@RequestMapping("dispositionC_5_1.do")
	public String dispositionA15(@RequestParam("id") String id, HttpServletResponse response, Model model)
			throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_1(id);
		System.out.println("chk" + check);

		int check2 = this.dao.updateDisposition_resultCscore(id);
		System.out.println("chk2" + check2);

		String ABCscore_sum = this.dao.selectDisposition_resultABCscore_sum(id);
		System.out.println("score" + ABCscore_sum);

		Play_CodeDTO dto = this.dao.selectPlay_text(ABCscore_sum);
		System.out.println("dto" + dto);

		List<Play_InfoDTO> result = this.dao.searchResultList(ABCscore_sum);
		System.out.println("Result" + result);

		model.addAttribute("Dto", dto).addAttribute("Result", result);
		return "./disposition/dispositionResult";
	}

	@RequestMapping("dispositionC_5_2.do")
	public String dispositionB15(@RequestParam("id") String id, HttpServletResponse response, Model model)
			throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_2(id);

		int check2 = this.dao.updateDisposition_resultCscore(id);

		String ABCscore_sum = this.dao.selectDisposition_resultABCscore_sum(id);

		Play_CodeDTO dto = this.dao.selectPlay_text(ABCscore_sum);

		List<Play_InfoDTO> result = this.dao.searchResultList(ABCscore_sum);

		model.addAttribute("Dto", dto).addAttribute("Result", result);

		return "./disposition/dispositionResult";
	}

	@RequestMapping("dispositionC_5_3.do")
	public String dispositionC15(@RequestParam("id") String id, HttpServletResponse response, Model model)
			throws IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int check = this.dao.updateDisposition_resultC_3(id);

		int check2 = this.dao.updateDisposition_resultCscore(id);

		//
		String ABCscore_sum = this.dao.selectDisposition_resultABCscore_sum(id);

		Play_CodeDTO dto = this.dao.selectPlay_text(ABCscore_sum);

		List<Play_InfoDTO> result = this.dao.searchResultList(ABCscore_sum);

		model.addAttribute("Dto", dto).addAttribute("Result", result);

		System.out.println("score" + ABCscore_sum);
		System.out.println("Result" + result);
		return "./disposition/dispositionResult";
	}

	@RequestMapping("dispositionCrawling.do")
	public String dispositionCrawling(@RequestParam("name") String name, HttpServletResponse response,
			HttpServletRequest request, Model model) {
		// 아이디 세션
		HttpSession session = request.getSession();

		// ABCscore_sum ==> ex) TTT,TFT
		String ABCscore_sum = this.dao.selectDisposition_resultABCscore_sum((String) session.getAttribute("UserId"));

		Play_CodeDTO dto = this.dao.selectPlay_text(ABCscore_sum);

		List<Play_InfoDTO> result = this.dao.searchResultList(ABCscore_sum);

		model.addAttribute("Dto", dto).addAttribute("Result", result);

		// 받은 문자열로 내림차순 Desclist 조회
		List<Play_InfoDTO> descList = this.dao.seatchDESCList(ABCscore_sum);
		System.out.println("descLisct >>> " + descList);

		int sum_choice = this.dao.selectSUMchoice(ABCscore_sum);

		model.addAttribute("DescList", descList).addAttribute("SUM_choice", sum_choice);

		System.out.println("SUm_choice >>> " + sum_choice);

		// play_choice 선택 수 1 증가
		int check = this.dao.updatePlay_choice(name);

		return "./disposition/dispositionRank";

	}

	@RequestMapping("dispositionRank.do")
	public String dispositionRank(@RequestParam("id") String id, HttpServletResponse response, Model model) {

		// 문자열 반환 받으려고 사용함
		String ABCscore_sum = this.dao.selectDisposition_resultABCscore_sum(id);

		// 받은 문자열로 내림차순 Desclist 조회
		List<Play_InfoDTO> descList = this.dao.seatchDESCList(ABCscore_sum);
		System.out.println("descLisct >>> " + descList);

		int sum_choice = this.dao.selectSUMchoice(ABCscore_sum);

		model.addAttribute("DescList", descList).addAttribute("SUM_choice", sum_choice);

		System.out.println("SUm_choice >>> " + sum_choice);

		return "./disposition/dispositionRank";
	}

}