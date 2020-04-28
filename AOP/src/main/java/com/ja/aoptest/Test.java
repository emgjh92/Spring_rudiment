package com.ja.aoptest;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class Test {
	public void ssss() {
		
		InternalResourceViewResolver aa = new InternalResourceViewResolver();
		aa.setPrefix("/WEB-INF/views/");
		aa.setSuffix(".jsp");
	}
}
