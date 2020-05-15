package com.ja.freeboard.member.controller;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.freeboard.member.service.MemberServiceImpl;
import com.ja.freeboard.vo.*;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired	// 컨테이너 조사해서 해당 class 연결해줌
	private MemberServiceImpl memberService;
	
	@Autowired //메일 발송 용
	private JavaMailSender mailSender;
	
	
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
		
		//인증키 생성....
		AuthVo authVo = new AuthVo();
		String authKey = UUID.randomUUID().toString();
		authVo.setAuth_key(authKey);
				
		//메일 발송...		
		memberService.joinMember(memberVo, member_hobby,authVo);
				
		FBMailSenderThread thread 
		= new FBMailSenderThread(mailSender, memberVo.getMember_id(),authVo.getAuth_key());
		//ID, 키값 넘겨주고 
		thread.start(); //메일링 시작 
		
		
		System.out.println("호출됨 로그인 페이지...컨트롤러....");
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
	
	@RequestMapping("/certification_process.do") //메일링
	public String certificationProcess(String key) {
		
		memberService.certification(key);
		
		return "member/certification_complete";
	}
	
	@RequestMapping("/testAjax.do")
	public String testAjax() {
		System.out.println("testAjax 호출");
		return "member/testAjax";
	}
	
	
}


class FBMailSenderThread extends Thread{ //메일링을 위한 Thread 처리
	
	private String to;
	private String authKey;
	private JavaMailSender mailSender;
	
	public FBMailSenderThread(JavaMailSender mailSender, String to, String authKey) {
		this.mailSender = mailSender;
		this.to = to;
		this.authKey = authKey;
	}
	
	
	@Override
	public void run() {
		//메일발송....
		/*
		--발송용 계정이 따로 필요하다.

		지메일 설정에서 IMAP '사용'으로 설정
		https://myaccount.google.com/lesssecureapps?pli=1 가서 보안수준이 낮은 앱의 액세스 '허용' 
		*/
		try {
				MimeMessage message = null;
			    MimeMessageHelper messageHelper = null;
		        message = mailSender.createMimeMessage();
		        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		        messageHelper.setSubject("[WEB 발송] FB회원가입을 축하드립니다.");
		        
		        String link 
		        ="http://localhost:8181/freeboard/member/certification_process.do?key=" + authKey;
		        
		        String text ="";
		        text += "FB 회원 가입을 축하 드립니다.<br>";
		        text += "회원가입 완료를 위해 아래 링크를 클릭해 주세요<br>";
		        text += "<a href='"+link+"'>";
		        text += "링크 클릭";
		        text += "</a>";
		        messageHelper.setText(text, true);
		        messageHelper.setFrom("111", "FB관리자"); //발송자 이름
		        messageHelper.setTo(to);//보내는 메일을 받을 메일 주소
		        //messageHelper.addInline(contentId, dataSource);
		        mailSender.send(message);
		        
			}catch(Exception e) {
				e.printStackTrace();
			}
				
	}
}

