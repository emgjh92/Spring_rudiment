package com.ja.reference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.reference.service.BoardService;
import com.ja.reference.vo.BoardVo;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/read_content_page.do")
	public String xxxx(int b_no, Model model) {
		
		BoardVo boardVo = boardService.getContent(b_no);
		
		model.addAttribute("boardVo",boardVo);
		
		return "read_content_page";
	}
	
}
