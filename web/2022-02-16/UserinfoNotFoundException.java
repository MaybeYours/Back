package xyz.itwill.exception;

//검색된 회원정보가 없는 경우 발생되는 예외 클래스
public class UserinfoNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserinfoNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserinfoNotFoundException(String message) {
		super(message);
	}
}
