package xyz.itwill.exception;

//비밀번호가 틀린 경우 발생되는 예외 클래스
public class PasswordMissMatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordMissMatchException() {
		// TODO Auto-generated constructor stub
	}
	
	public PasswordMissMatchException(String message) {
		super(message);
	}
}
