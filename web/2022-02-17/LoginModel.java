package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.itwill.exception.PasswordMissMatchException;
import xyz.itwill.exception.UserinfoNotFoundException;
import xyz.itwill.service.UserinfoService;

//Ŭ���̾�Ʈ�� [/login.do]�� ��û�� ��� ���۵Ǵ� �� Ŭ����
// => �α��������� ���޹޾� USERINFO ���̺� ����� ȸ�������� ���Ͽ� ���� ó���ϴ� ��� ����
// => ���� ���� : ���ǿ� ���� ���� ������ �����ϰ� [loginForm.do]�� �����̷�Ʈ �̵��ϱ� ���� ���� ��ȯ
// => ���� ���� : [user_login.jsp]�� ������ �̵��ϱ� ���� ���� ��ȯ - JSP ������ �����޼����� ���̵� ����
public class LoginModel implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward actionForward=new ActionForward();
		//��û ó���� �߻��Ǵ� ��� ���ܿ� ���� ���� ó�� ��� ����
		try {
			if(request.getMethod().equals("GET")) {//���������� ��û
				throw new Exception();//������ ���� �߻�
			}
			
			String userid=request.getParameter("userid");
			String password=request.getParameter("password");
			
			//Model Ŭ������ ��û ó�� �޼ҵ�� Service Ŭ������ �޼ҵ带 ȣ���Ͽ� ��û ó��
			//UserinfoService �ν��Ͻ��� ���� ó�� �޼ҵ�(auth) ȣ��
			// => ���� ó�� �޼ҵ忡�� ���ܰ� �߻��� ��� ���� ���� 
			UserinfoService.getService().auth(userid, password);
			
			//���� ó�� �޼ҵ忡�� ���ܰ� �߻����� ���� ��� ���� ���� 
			HttpSession session=request.getSession();//������ ���ε��Ͽ� ����
			session.setAttribute("loginUserinfo", UserinfoService.getService().getUserinfo(userid));
			
			actionForward.setForward(false);
			actionForward.setPath("loginForm.do");
		} catch (UserinfoNotFoundException | PasswordMissMatchException e) {//JDK1.7 �̻󿡼��� �ټ��� ���� ó�� ����
			//Request Scope : �����尡 �̵��� JSP ���������� �Ӽ������� ����� �ν��Ͻ��� ��ȯ�޾� ���
			request.setAttribute("message", e.getMessage());
			request.setAttribute("userid", request.getParameter("userid"));
			actionForward.setForward(true);
			actionForward.setPath("model_two/user_login.jsp");
		}  catch (Exception e) {
			System.out.println("[����]�޼��� = "+e.getMessage());
			actionForward.setForward(false);
			actionForward.setPath("error.do");
		}
		return actionForward;
	}
}
