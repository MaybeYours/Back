package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트가 [/loginForm.do]를 요청한 경우 동작되는 모델 클래스
// => [user_login.jsp]로 포워드 이동하기 위한 정보 반환
public class LoginFormModel implements Action {
	//클라이언트의 요청을 처리하는 메소드
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		actionForward.setForward(true);
		actionForward.setPath("model_two/user_login.jsp");
		return actionForward;
	}
}
