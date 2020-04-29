package com.ja.reference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*") //URL 구조에서 /member/test~ 로 되도록 RequestMapping
public class MappingTest {
//스프링의 대표 특징 중 하나 : POJO (Plain Old Java Object) - 경량화
	
	@RequestMapping("/test1")
	public void test1() {
		System.out.println("hello_test1");
	}
	
	@RequestMapping("/test2")
	public String test2() {
		System.out.println("hello_test2");
		return "test1"; //test2.jsp로 접속해서 test1.jsp로 포워딩하고 싶은 경우
	}
	
	@RequestMapping("/test3")
	public String test3() {
		System.out.println("hello_test3");
		return "redirect:./test2"; //test3 로 접속해서 test2로 redirect ==> test1 으로 접속됨
	}
	
	@RequestMapping("/test4")
	public String test4(){
		System.out.println("hello_test4");
		return "test1";
	}
}
