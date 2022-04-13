package xyz.itwill.el;

//EL 함수로 호출되기 위한 메소드를 선언한 클래스
public class HelloEL {
	//EL 함수로 호출되는 메소드는 반드시 정적메소드로 선언
	public static String hello(String name) {
		return name+"님, 안녕하세요.";
	}
}
