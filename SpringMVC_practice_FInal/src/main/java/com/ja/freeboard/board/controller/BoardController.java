package com.ja.freeboard.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.freeboard.board.service.BoardServiceImpl;
import com.ja.freeboard.vo.BoardVo;
import com.ja.freeboard.vo.MemberVo;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("/main_page.do")
	public String mainPage
	(Model model, String search_word, 
			@RequestParam(value="currPage", required=false, defaultValue = "1") int currPage) { 
		//검색기능  추가, 페이징 기능 추가 (@RequestParam은 기본 페이지 즉, ?currPage=~ 이게 없을때 오류 나는걸 방지하기 위함)
		
		if(currPage <= 0) {
			currPage = 1;//사용자가 이상한 값을 강제로 집어 넣을 경우를 방지 
		}
		
		List<Map<String,Object>> list = boardService.getBoardList(search_word, currPage);
		
		
		//----------------------------------------------------------------------
		
		int totalCount= boardService.getBoardDataCount(search_word);
		model.addAttribute("totalCount",totalCount);
		
		int beginPage = ((currPage-1)/5)*5 +1;
		int endPage = ((currPage-1)/5+1)*(5);
		
		if(endPage > ((totalCount-1)/10) +1 ) {
			endPage = ((totalCount-1)/10) +1;
		}
		//페이징에서 이전, 다음 버튼 용 변수를 만들어 전달해 준다.
		model.addAttribute("beginPage",beginPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("currPage",currPage);//기본 페이지 셋팅

		//----------------------------------------------------------------------

		model.addAttribute("dataList",list);
		return "board/main_page";
	}
	
	@RequestMapping("/write_content_page.do")
	public String writeContentPage() {
		
		return "board/write_content_page";
	}
	
	@RequestMapping("/write_content_process.do")
	public String writeContentProcess(BoardVo boardVo, HttpSession session) {
		
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
