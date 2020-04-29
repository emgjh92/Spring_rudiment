package com.ja.reference.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.reference.vo.MemberVo;

@Controller
@RequestMapping("/board/*")
public class MappingTest2 {
	
	@RequestMapping("/test1")
	public String test1(HttpServletRequest re) {
		//http://localhost:8181/reference/board/test3?aa=88888&bb=99999
		String aa = re.getParameter("aa");
		String bb = re.getParameter("bb");
		System.out.println("hello1"+aa+bb);
		return "test1";
	}
	
	@RequestMapping("/test2")
	public String test2(int aa, int bb) { //String aa, String bb 도 가능
		//http://localhost:8181/reference/board/test3?aa=88888&bb=99999

		System.out.println("안녕2"+ aa + bb);

		return "test1";
	}
	
	@RequestMapping("/test3")
	public String test3(
			@RequestParam("aa") int v1,
			@RequestParam("bb") int v2) 
	{
		//http://localhost:8181/reference/board/test3?aa=88888&bb=99999
		System.out.println("안녕3 : " + v1 + v2);
		return "test1";
	}
	
	@RequestMapping("/test4")
	public String test4(MemberVo vo) { //get 방식
		//http://localhost:8181/reference/board/test4?m_id=s001&m_pw=222&m_nick=3333
		System.out.println(vo.getM_id());
		System.out.println(vo.getM_pw());
		System.out.println(vo.getM_nick());
		return "test1";
	}
	
	@RequestMapping("/test5/{pid}/{xxx}")
	public String test5( 
			@PathVariable("pid") int pid, 
			@PathVariable("xxx") int xxx) 
	{//http://localhost:8181/reference/board/test5/111/222
		System.out.println(pid);
		System.out.println(xxx);
		
		return "test1";
	}
	
}
