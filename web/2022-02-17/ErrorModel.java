package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트가 [/error.do]를 요청하거나 요청에 대한 모델이 없는 경우 동작되는 모델 클래스
//=> [user_error.jsp]로 포워드 이동하기 위한 정보 반환
public class ErrorModel implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		actionForward.setForward(true);
		actionForward.setPath("model_two/user_error.jsp");
		return actionForward;
	}

}
