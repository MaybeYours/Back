package xyz.itwill.exception;

//기존 회원정보와 중복될 경우 발생되는 예외 클래스
// => 예외 클래스는 반드시 Exception 클래스를 상속받아 작성 
public class ExistsUserinfoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExistsUserinfoException() {
		// TODO Auto-generated constructor stub
	}
	
	public ExistsUserinfoException(String message) {
		super(message);
	}
}
