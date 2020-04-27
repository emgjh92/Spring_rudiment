package polymorphism;

public class SamsungTv implements TV{
	
	private Speaker speaker;
	private int price;
	
	public SamsungTv() {
		System.out.println("삼성 --- 기본 생성자 호출됨.");
	}
	
	public SamsungTv(Speaker speaker) {
		this.speaker = speaker;
		System.out.println("삼성 --- 주입 생성자1 호출됨.");
	}
	
	public SamsungTv(Speaker speaker, int price) {
		this.price = price;
		this.speaker = speaker;
		System.out.println("삼성 --- 주입 생성자2 호출됨.");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
		
	}
	
	
	public void powerOn() {
		System.out.println("삼성 --- 전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("삼성 --- 전원 끈다.");
	}
	
	public void volumeUp() {
		//System.out.println("삼성 --- 소리 올림.");
		//speaker = new SonySpeaker();
		speaker.volumeUp();
	}
	
	public void volumeDown() {
		//System.out.println("삼성 --- 소리 내림.");
		//speaker = new SonySpeaker();
		speaker.volumeDown();
	}
	
	public void initMethod() {
		System.out.println("삼성 --- 초기화 코드...");
	}
}
