package polymorphism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTv implements TV{
	
	@Autowired
	@Qualifier("sonySpeaker")
	private Speaker speaker;
	
	public void powerOn() {
		System.out.println("엘지 --- 전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("엘지 --- 전원 끈다.");
	}
	
	public void volumeUp() {
		//System.out.println("엘지 --- 소리 올림.");
		speaker.volumeUp();
	}
	
	public void volumeDown() {
		//System.out.println("엘지 --- 소리 내림.");
		speaker.volumeDown();
	}
}
