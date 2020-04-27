package polymorphism;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");
		//IOC 컨테이너 가동
		
		//String str = new String("111.222.111.11");
		//String str = (String) factory.getBean("ip_addr");
		
		
		/*
		BeanFactory factory = new BeanFactory();

		Scanner scn = new Scanner(System.in);
		System.out.println("어떤 TV?>");
		String command = scn.nextLine();
		*/
		
		//TV tv = new SamsungTv(); //결합도를 낮춤
		//TV tv = new LgTv();
		//TV tv = (TV) factory.getBean(command); //결합도를 더 낮춤
		//--> Object 로 받은걸 TV로 Type Casting
		
		TV tv = (TV) factory.getBean("tv"); //결합도를 더 더 낮춤 (applicationContext.xml 을 통해)
		//--> Object 로 받은걸 TV로 Type Casting
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}

}
