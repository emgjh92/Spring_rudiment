package com.ja.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAdvice {
	
	public void testttt() {
		System.out.println("tttttttttttttttt");
	}
	
	public void printLog(JoinPoint jp) {
	//	System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
		String methodName=jp.getSignature().getName();
		System.out.println("["+methodName+"] 메소드 실행 전");
	}
	
	public void printAfterLog(JoinPoint jp) {
		//	System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
			String methodName=jp.getSignature().getName();
			System.out.println("["+methodName+"] 메소드 실행 후");
		}
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		String name=pjp.getSignature().getName();
		
		System.out.println(name+"실행전");
		
		Object obj = pjp.proceed();
		//pjp.proceed() 를 실행하지 않을 경우 대상 method가 실행되지 않는 독특한 구조이다.
		
		System.out.println(name+"실행후");
		
		return obj;
	}
}
