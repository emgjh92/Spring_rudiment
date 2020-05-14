package com.ja.freeboard.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.freeboard.member.service.MemberServiceImpl;
import com.ja.freeboard.vo.*;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired	// 컨테이너 조사해서 해당 class 연결해줌
	private MemberServiceImpl memberService;
	
	@RequestMapping("/login_page.do")
	public String loginPage() {
		
		return "member/login_page";
	}
	
	@RequestMapping("/join_member_page.do")
	public String joinMemberPage() {
		
		return "member/join_member_page";
	}
	
	@RequestMapping("/join_member_process.do")
	public String joinMemberProcess(MemberVo memberVo, int[] member_hobby) {
		
		memberService.joinMember(memberVo, member_hobby);
		
		return "redirect:./login_page.do";
	}
	
	@RequestMapping("/login_process.do")
	public String loginMemberProcess(MemberVo memberVo, HttpSession session) {	// 로그인 성공 시 세션 설정을 위해 매개변수에 HttpSession 넣어줌
		
		MemberVo userData = memberService.login(memberVo);
		
		if(userData == null) {	// 로그인 실패
			
			return "member/login_fail";
		} else {	// 로그인 성공
			
			session.setAttribute("sessionUser", userData);

			return "redirect:/board/main_page.do";	// 웹브라우저 기준의 /
		}
	}
	
	@RequestMapping("/logout_process.do")
	public String logoutProcess(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/board/main_page.do";
	}
}