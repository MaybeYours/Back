package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.itwill.exception.PasswordMissMatchException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.service.UserinfoService;

//클라이언트가 [/login.do]를 요청한 경우 동작되는 모델 클래스
// => 로그인정보를 전달받아 USERINFO 테이블에 저장된 회원정보와 비교하여 인증 처리하는 기능 제공
// => 인증 성공 : 세션에 권한 관련 정보를 저장하고 [loginForm.do]로 리다이렉트 이동하기 위한 정보 반환
// => 인증 실패 : [user_login.jsp]로 포워드 이동하기 위한 정보 반환 - JSP 문서에 에러메세지와 아이디 제공
public class LoginModel implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		//요청 처리시 발생되는 모든 예외에 대한 예외 처리 기능 구현
		try {
			if(request.getMethod().equals("GET")) {//비정상적인 요청
				throw new Exception();//인위적 예외 발생
			}
			
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			
			//Model 클래스의 요청 처리 메소드는 Service 클래스의 메소드를 호출하여 요청 처리
			//UserinfoService 인스턴스로 인증 처리 메소드(auth) 호출
			// => 인증 처리 메소드에서 예외가 발생된 경우 인증 실패 
			UserinfoService.getService().auth(userid, password);
			
			//인증 처리 메소드에서 예외가 발생되지 않은 경우 인증 성공 
			HttpSession session=request.getSession();//세션을 바인딩하여 저장
			session.setAttribute("loginUserinfo", UserinfoService.getService().getUserinfo(userid));
			
			actionForward.setForward(false);
			actionForward.setPath("loginForm.do");
		} catch (UserinfoNotFoundException | PasswordMissMatchException e) {//JDK1.7 이상에서만 다수의 예외 처리 가능
			//Request Scope : 스레드가 이동된 JSP 문서에서만 속성값으로 저장된 인스턴스를 반환받아 사용
			request.setAttribute("message", e.getMessage());
			request.setAttribute("userid", request.getParameter("userid"));
			actionForward.setForward(true);
			actionForward.setPath("model_two/user_login.jsp");
		}  catch (Exception e) {
			System.out.println("[에러]메세지 = "+e.getMessage());
			actionForward.setForward(false);
			actionForward.setPath("error.do");
		}
		return actionForward;
	}
}
