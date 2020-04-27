package polymorphism;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


//@Component
@Controller
//@Service
//@Repository //4개 다 기능은 똑같다. (티어는 다르다)
public class SonySpeaker implements Speaker{
	
	//private SonySpeaker speaker;
	
	public SonySpeaker() {
		System.out.println("소니 스피커 --- 생성");
	}
	
	public void volumeUp() {
		System.out.println("소니 스피커 -- 소리 올림");
	}
	
	public void volumeDown() {
		System.out.println("소니 스피커 -- 소리 내림");
	}
	
}
