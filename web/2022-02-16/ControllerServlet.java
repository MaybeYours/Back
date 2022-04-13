package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러(Controller) : 클라이언트의 모든 요청을 받아 요청에 대한 처리는 모델(Model : 클래스)을
//이용하여 구현하고 처리결과를 뷰(View : JSP)에게 전달하여 응답되도록 이동하는 웹프로그램(서블릿)

//1.클라이언트의 모든 요청을 받을수 있도록 서블릿 설정 - 단일 진입점의 기능 구현 : Front Controller Pattern
//@WebServlet("URL") : 서블릿 클래스를 웹프로그램(서블릿)으로 등록하고 URL 주소를 매핑하는 어노테이션
// => URL 주소에 패턴문자(* 또는 ?)를 사용하여 설정 가능
// => @WebServlet("*.do") : 클라이언트가 XXX.do 형식의 URL 주소로 요청한 경우 서블릿 실행
// => @WebServlet 어노테이션 대신 web.xml 파일에서 서블릿 클래스를 웹프로그램으로 등록하여 URL 주소 매핑 
//@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//클라이언트의 요청을 처리하기 위해 자동 호출되는 메소드
	// => 클라이언트가 웹프로그램을 요청할 때마다 반복적으로 호출
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ControllerServlet 클래스의 service() 메소드 호출");
		
		//2.클라이언트의 요청 분석 : 요청 URL 주소 이용 - http://localhost:8000/mvc/XXX.do
		//HttpServletRequest.getRequestURI() : 요청 URL 주소의 URI를 반환하는 메소드
		String requestURI=request.getRequestURI();
		//System.out.println("requestURI = "+requestURI);//requestURI = /mvc/XXX.do
		
		//HttpServletRequest.getContextPath() : 요청 URL 주소의 컨텍스트 경로를 반환하는 메소드
		String contextPath=request.getContextPath();
		//System.out.println("contextPath = "+contextPath);//contextPath = /mvc
		
		String command=requestURI.substring(contextPath.length());
		//System.out.println("command = "+command);//command = /XXX.do
		
		//3.클라이언트 요청에 대한 처리 후 뷰 관련 정보를 반환받아 저장
		// => 모델(Model) 역활의 클래스로 인스턴스를 생성하여 요청 처리 메소드 호출
		// => 하나의 요청에 대해 하나의 모델 클래스의 요청 처리 메소드가 호출되어 클라이언트의  
		//요청을 처리하고 뷰 관련 정보를 반환 - Command Controller Pattern
		/*
		//클라이언트 요청에 대한 모델 클래스 매핑 설계
		/loginForm.do : LoginFormModel 클래스 - 로그인정보 입력페이지 또는 환영메세지 출력페이지
		/login.do : LoginModel 클래스 - 로그인 처리페이지
		/logout.do : LogoutModel 클래스 - 로그아웃 처리페이지
		/writeForm.do : WriteFormModel 클래스 - 회원정보 입력페이지 
		/write.do : WriteModel 클래스 - 회원정보 저장페이지 
		/list.do : ListModel 클래스 - 회원목록 출력페이지 
		/view.do : ViewModel 클래스 - 회원정보 출력페이지 
		/modifyForm.do : ModifyFormModel 클래스 - 회원정보 변경 입력페이지 
		/modify.do : ModifyModel 클래스 - 회원정보 변경페이지 
		/remove.do : RemoveModel 클래스 - 회원정보 삭제페이지 
		/error.do : ErrorModel 클래스 - 에러메세제 출력페이지 
		*/
		if(command.equals("/loginForm.do")) {
			
		} else if(command.equals("/login.do")) {
			
		} else if(command.equals("/logout.do")) {
			
		} else if(command.equals("/writeForm.do")) {
			
		} else if(command.equals("/write.do")) {
			
		} else if(command.equals("/list.do")) {
			
		} else if(command.equals("/view.do")) {
			
		} else if(command.equals("/modifyForm.do")) {
			
		} else if(command.equals("/modify.do")) {
			
		} else if(command.equals("/error.do")) {
			
		} else if(command.equals("/login.do")) {
			
		} else {
			
		}
	}

}










