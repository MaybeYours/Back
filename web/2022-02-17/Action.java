package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 모델 클래스가 반드시 상속 받아야 되는 인터페이스
// => 모델 클래스의 요청 처리 메소드 작성 규칙 제공
// => 요청 처리 메소드 호출 편의성과 유지보수의 효율성 증가
//요청 처리 메소드는 HttpServletRequest 인스턴스와 HttpServletResponse 인스턴스를 매개변수로 전달
//받아 요청에 대한 처리를 실행하고 뷰 관련 정보(이동 관련 정보)를 반환하도록 작성 
public interface Action {
	ActionForward execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException;
}
