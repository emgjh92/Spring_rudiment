package com.ja.aoptest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardServiceImpl {
	
	private LogAdvice logAdvice = new LogAdvice();
	
	
	@Autowired
	private BoardDao boardDao;
	/*
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	*/ 
	//==> @Autowired 사용 시 자동으로 setter를 만들어 주므로, 어노테이션을 쓰게 된다면 굳이 setter를 쓸 필요가 없다.
	
	public ArrayList<String> getBoardList(){
		
		//logAdvice.printLog();
		
		ArrayList<String> list = boardDao.selectAll();
		
		return list;
	}

	//-------------------비지니스 로직 (="코어 기능"을 의미한다)----------------------
	public void login() {
		//로그인과 관련된 매우 중요한 기능들 수행
		//logAdvice.printLog();//로그 기능의 경우엔 비즈니스 로직은 아니다. (코드가 없다고 프로그램이 안돌아가진 않으므로)
		System.out.println("로그인 기능 수행....");
	}
	
	public void getContent() {
		//읽기와 관련된 중요한 로직....
		//logAdvice.printLog();
		System.out.println("컨텐트 가져오기 기능 수행....");
	}
	
	public void adminFunction() {
		//관리자와 관련된 중요한 로직...
		//logAdvice.printLog();
		System.out.println("관리자 기능 수행....");
	}
	//--------------------------------------------------------------------

	/*
	 AOP :  Aspect Oriented Programming - 관점 지향 프로그래밍
	 코드의 관심을 횡단(가로로) 관심으로 본단 의미 (첨부해둔 AOP개념.png를 참조해 보자)
	 */
	
	
	
}