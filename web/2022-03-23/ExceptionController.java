package xyz.itwill10.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;

//@ControllerAdvice : Controller Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�  
// => ����ó�� �޼ҵ常 ����� Controller Ŭ������ Spring Bean���� ����� �� ���
// => ��� Controller Ŭ������ ��û ó�� �޼ҵ忡�� �߻��Ǵ� ���ܿ� ���� ó�� ����
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
