package xyz.itwill10.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice : Controller 클래스를 Spring Bean으로 등록하기 위한 어노테이션  
// => 예외처리 메소드만 선언된 Controller 클래스를 Spring Bean으로 등록할 때 사용
// => 모든 Controller 클래스의 요청 처리 메소드에서 발생되는 예외에 대한 처리 가능
@ControllerAdvice
public class ExceptionController {
	/*
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception exception) {
		exception.printStackTrace();
		return "userinfo/user_error";
	}
	*/
}
