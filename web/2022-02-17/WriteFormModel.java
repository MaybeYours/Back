package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.itwill.dto.UserinfoDTO;

//클라이언트가 [/writeForm.do]를 요청한 경우 동작되는 모델 클래스
// => 관리자만 요청 가능하도록 권한 설정
// => [user_write.jsp]로 포워드 이동하기 위한 정보 반환
public class WriteFormModel implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		try {
			HttpSession session=request.getSession();
			UserinfoDTO loginUserinfo=(UserinfoDTO)session.getAttribute("loginUserinfo");
			//비로그인 사용자이거나 관리자가 아닌 경우 - 비정상적인 요청
			if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
				throw new Exception();//인위적 예외 발생
			}
			
			actionForward.setForward(true);
			actionForward.setPath("model_two/user_write.jsp");
		} catch (Exception e) {
			System.out.println("[에러]메세지 = "+e.getMessage());
			actionForward.setForward(false);
			actionForward.setPath("error.do");
		}
		return actionForward;
	}

}
