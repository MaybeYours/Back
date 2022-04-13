package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xyz.itwill.dto.UserinfoDTO;
import xyz.itwill.service.UserinfoService;

//클라이언트가 [/modify.do]를 요청한 경우 동작되는 모델 클래스
// => 회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하도록 처리
// => [view.do]로 리다이렉트 이동하기 위한 정보 반환
public class ModifyModel implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		try {
			if(request.getMethod().equals("GET")) {
				throw new Exception();
			}
			
			request.setCharacterEncoding("utf-8");
			
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			int status=Integer.parseInt(request.getParameter("status"));
			
			UserinfoDTO userinfo=new UserinfoDTO();
			userinfo.setUserid(userid);
			if(password==null || password.equals("")) {//비밀번호가 전달되지 않은 경우 - 기존 비밀번호 사용
				userinfo.setPassword(UserinfoService.getService().getUserinfo(userid).getPassword());
			} else {//비밀번호가 전달된 경우 - 새로운 비밀번호 사용
				userinfo.setPassword(password);
			}
			userinfo.setName(name);
			userinfo.setEmail(email);
			userinfo.setStatus(status);
			
			//회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하는 Service 클래스의 메소드 호출
			UserinfoService.getService().modifyUserinfo(userinfo);
			
			actionForward.setForward(false);
			actionForward.setPath("view.do?userid="+userid);
		} catch (Exception e) {
			System.out.println("[에러]메세지 = "+e.getMessage());
			actionForward.setForward(false);
			actionForward.setPath("error.do");
		}
		return actionForward;
	}

}
