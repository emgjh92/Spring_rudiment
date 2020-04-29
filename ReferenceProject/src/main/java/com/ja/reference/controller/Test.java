package com.ja.reference.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
class Test {
	
	//@RequestMapping("/test1")
	//@PostMapping("/test1") //무조건 POST 방식으로만 받는다.
	@GetMapping("/test1")//무조건 GET 방식으로만 받는다.
	public String test1() {
		System.out.println("hello1");
		return "test1";
	}
	
	@RequestMapping("/test2")
	public String test2(HttpSession session) { //세션 받아오기
		session.invalidate();
		session.setAttribute("xxx",1111);
		session.getAttribute("xxx");
		return "test1";
	}
	
	@RequestMapping("/test3")
	public String test3(Model model) {
		//test1.jsp 에서 ${title }<br> 추가
		String title="제목입니다....";
		model.addAttribute("title",title);
		return "test1";
	}
	
	
}
