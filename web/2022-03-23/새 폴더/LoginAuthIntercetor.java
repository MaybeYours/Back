package xyz.itwill10.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

//로그인 관련 권한 처리를 위한 Interceptor 클래스
// => 요청 처리 메소드의 명령이 실행되기 전에 비로그인 사용자가 웹프로그램을 요청한 경우 에러페이지로 응답 처리
public class LoginAuthIntercetor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		if(session.getAttribute("loginUserinfo")==null) {
			throw new Exception();
		}
		return true;
	}
}
