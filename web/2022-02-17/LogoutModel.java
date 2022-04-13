package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//클라이언트가 [/logout.do]를 요청한 경우 동작되는 모델 클래스
// => 로그아웃 처리 후 [loginForm.do]로 리다이렉트 이동하기 위한 정보 반환
public class LogoutModel implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		//session.removeAttribute("loginUserinfo");
		session.invalidate();
				
		ActionForward actionForward=new ActionForward();
		actionForward.setForward(false);
		actionForward.setPath("loginForm.do");
		return actionForward;
	}

}
