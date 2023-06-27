package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.play.model.Play_InfoDAO;
import com.play.model.Play_InfoDTO;
import com.spring.model.TestDAO;
import com.spring.model.TestDTO;

@Controller
public class TestController {
	@Autowired
	private TestDAO dao;
	
	@Autowired
	private Play_InfoDAO infodao;
	
	@RequestMapping("main.do")

	public String all(Model model, HttpSession session) {
		
		List<Play_InfoDTO> outside = this.infodao.mainOutSideList();
		List<Play_InfoDTO> inside = this.infodao.mainInSideList();
		
		model.addAttribute("OutSide", outside)
		     .addAttribute("InSide", inside);	

		return "main";
	}

	@RequestMapping("test_insert.do")

	public String insert() {

		return "test_insert";

	}

	@RequestMapping("test_insert_ok.do")

	public void insertOk(TestDTO dto, HttpServletResponse response) throws IOException {

		int check = this.dao.insertTest(dto);

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원 등록 성공!')");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원 등록 실패!')");
			out.println("</script>");
		}

	}

}
