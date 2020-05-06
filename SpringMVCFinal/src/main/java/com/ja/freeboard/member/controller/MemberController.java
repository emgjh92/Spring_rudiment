package com.ja.freeboard.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.freeboard.member.service.MemberServiceImpl;
import com.ja.freeboard.vo.MemberVo;

@Controller
@RequestMapping("/member/*")
public class MemberController {
/*
	@RequestMapping("/test.do")
	public String test() {
		System.out.println("안녕하세요");
		return "test";
	}
//테스트 페이지
*/
	@Autowired
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
	public String joinMemebrProcess(MemberVo memberVo, int [] member_hobby) {
		
		memberService.joinMember(memberVo, member_hobby);
		
		return "redirect:./login_page.do";
	}
	
}
