package com.ja.aoptest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@Autowired //private 로 선언했는데도 되는 이유 : Spring이 Autowired사용 시 setter 까지 자동으로 만들어서 실행해 주기 때문
	private BoardServiceImpl boardService;
	
	@RequestMapping("/main_page.do")
	public String mainPage(HttpServletRequest request) {
			
		ArrayList<String> list = boardService.getBoardList();
		
		request.setAttribute("xxxx", list);
		
		return "main_page";
	}
	
	@RequestMapping("/read_content_page.do")
	public String readContentPage() {
		
		return "read_content_page";
	}
	
	
	//컨트롤러는 서비스를 이용하고 서비스는 DAO를 이용하는 구조
	
	
	@RequestMapping("/test1")
	public String test1() {
		boardService.adminFunction();
		
		boardService.getContent();
		
		return "aaaa";
	}
	
}
