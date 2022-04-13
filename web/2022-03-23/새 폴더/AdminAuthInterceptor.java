package xyz.itwill10.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import xyz.itwill10.dto.Userinfo;

//Interceptor 클래스 : 요청 처리 메소드의 명령 실행 전후에 삽입되어 동작될 기능을 제공하는 클래스
// => Interceptor 클래스는 HandlerInterceptor 인터페이스를 상속받아 작성
// => Bean Configuration File(servlet-context.xml)에서 Spring Bean으로 등록

//관리자 관련 권한 처리를 위한 Interceptor 클래스
// => 요청 처리 메소드의 명령이 실행되기 전에 비로그인 사용자이거나 관리자가 아닌 사용자가 
//웹프로그램을 요청한 경우 에러페이지로 응답 처리
public class AdminAuthInterceptor implements HandlerInterceptor {
	//preHandle : 요청 처리 메소드의 명령이 실행되기 전에 실행될 명령을 작성하는 메소드
	// => 권한 관련 명령을 작성하여 실행하기 위해 사용하는 메소드
	// => false 반환 : 요청 처리 메소드의 명령 미실행, true 반환  : 요청 처리 메소드의 명령 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		
		if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
			//request.getRequestDispatcher("userinfo/user_error.jsp").forward(request, response);
			//return false;
			
			throw new Exception();
		}
		
		return true;
	}
	
	//postHandle : 요청 처리 메소드의 명령이 정상적으로 실행된 후에 실행될 명령을 작성하는 메소드
	// => 요청 처리 메소드의 명령 실행시 예외가 발생된 경우 postHandle 메소드의 명령 미실행
	// => 요청 처리 메소드의 반환값(ModelAndView 객체)을 조작할 경우 사용하는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	//afterCompletion : 요청 처리 메소드의 명령 실행시 예외가 발생된 후에 실행될 명령을 작성하는 메소드
	// => 예외 처리를 목적을 사용하는 메소드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}




