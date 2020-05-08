package com.ja.freeboard.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.freeboard.board.service.BoardServiceImpl;
import com.ja.freeboard.vo.BoardVo;
import com.ja.freeboard.vo.MemberVo;

import java.util.*;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("/main_page.do")
	public String mainPage(Model model) {
		List<Map<String,Object>> list = boardService.getBoardList();
		
		model.addAttribute("dataList",list);
		
		return "board/main_page";
	}
	
	@RequestMapping("/write_content_page.do")
	public String writeContentPage() {
		
		return "board/write_content_page";
	}
	
	@RequestMapping("/write_content_process.do")
	public String writeContentProcess(BoardVo boardVo,HttpSession session) {
		
		MemberVo memberVo = (MemberVo)session.getAttribute("sessionUser");
		
		boardVo.setMember_no(memberVo.getMember_no());
		
		boardService.writeContent(boardVo);
		
		return "redirect:./main_page.do";
	}
	
	@RequestMapping("/read_content_page.do")
	public String readContentPage(int board_no, Model model) {
		
		
		Map<String,Object> map = boardService.getBoard(board_no);
		
		model.addAttribute("aaaa",map);
		
		return "board/read_content_page";
	}
	
	@RequestMapping("/delete_content_process.do")
	public String deleteContent(int board_no) {
		
		boardService.deleteContent(board_no);
		
		return "redirect:/board/main_page.do";
	}//페이지 삭제
	
	@RequestMapping("/update_content_page.do")
	public String updateContentPage(int board_no, Model model) {
		
		
		model.addAttribute("data",boardService.getBoard(board_no));
		
		return "/board/update_content_page";
	} //페이지 업데이트(수정)
	
	
	@RequestMapping("/update_content_process.do")
	public String updateContentProcess(BoardVo vo) {
		
		boardService.updateContent(vo);
		
		return "redirect:/board/main_page.do";
	}
	

}
