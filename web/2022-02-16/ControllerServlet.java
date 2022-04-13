package xyz.itwill.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��Ʈ�ѷ�(Controller) : Ŭ���̾�Ʈ�� ��� ��û�� �޾� ��û�� ���� ó���� ��(Model : Ŭ����)��
//�̿��Ͽ� �����ϰ� ó������� ��(View : JSP)���� �����Ͽ� ����ǵ��� �̵��ϴ� �����α׷�(����)

//1.Ŭ���̾�Ʈ�� ��� ��û�� ������ �ֵ��� ���� ���� - ���� �������� ��� ���� : Front Controller Pattern
//@WebServlet("URL") : ���� Ŭ������ �����α׷�(����)���� ����ϰ� URL �ּҸ� �����ϴ� ������̼�
// => URL �ּҿ� ���Ϲ���(* �Ǵ� ?)�� ����Ͽ� ���� ����
// => @WebServlet("*.do") : Ŭ���̾�Ʈ�� XXX.do ������ URL �ּҷ� ��û�� ��� ���� ����
// => @WebServlet ������̼� ��� web.xml ���Ͽ��� ���� Ŭ������ �����α׷����� ����Ͽ� URL �ּ� ���� 
//@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� �ڵ� ȣ��Ǵ� �޼ҵ�
	// => Ŭ���̾�Ʈ�� �����α׷��� ��û�� ������ �ݺ������� ȣ��
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ControllerServlet Ŭ������ service() �޼ҵ� ȣ��");
		
		//2.Ŭ���̾�Ʈ�� ��û �м� : ��û URL �ּ� �̿� - http://localhost:8000/mvc/XXX.do
		//HttpServletRequest.getRequestURI() : ��û URL �ּ��� URI�� ��ȯ�ϴ� �޼ҵ�
		String requestURI=request.getRequestURI();
		//System.out.println("requestURI = "+requestURI);//requestURI = /mvc/XXX.do
		
		//HttpServletRequest.getContextPath() : ��û URL �ּ��� ���ؽ�Ʈ ��θ� ��ȯ�ϴ� �޼ҵ�
		String contextPath=request.getContextPath();
		//System.out.println("contextPath = "+contextPath);//contextPath = /mvc
		
		String command=requestURI.substring(contextPath.length());
		//System.out.println("command = "+command);//command = /XXX.do
		
		//3.Ŭ���̾�Ʈ ��û�� ���� ó�� �� �� ���� ������ ��ȯ�޾� ����
		// => ��(Model) ��Ȱ�� Ŭ������ �ν��Ͻ��� �����Ͽ� ��û ó�� �޼ҵ� ȣ��
		// => �ϳ��� ��û�� ���� �ϳ��� �� Ŭ������ ��û ó�� �޼ҵ尡 ȣ��Ǿ� Ŭ���̾�Ʈ��  
		//��û�� ó���ϰ� �� ���� ������ ��ȯ - Command Controller Pattern
		/*
		//Ŭ���̾�Ʈ ��û�� ���� �� Ŭ���� ���� ����
		/loginForm.do : LoginFormModel Ŭ���� - �α������� �Է������� �Ǵ� ȯ���޼��� ���������
		/login.do : LoginModel Ŭ���� - �α��� ó��������
		/logout.do : LogoutModel Ŭ���� - �α׾ƿ� ó��������
		/writeForm.do : WriteFormModel Ŭ���� - ȸ������ �Է������� 
		/write.do : WriteModel Ŭ���� - ȸ������ ���������� 
		/list.do : ListModel Ŭ���� - ȸ����� ��������� 
		/view.do : ViewModel Ŭ���� - ȸ������ ��������� 
		/modifyForm.do : ModifyFormModel Ŭ���� - ȸ������ ���� �Է������� 
		/modify.do : ModifyModel Ŭ���� - ȸ������ ���������� 
		/remove.do : RemoveModel Ŭ���� - ȸ������ ���������� 
		/error.do : ErrorModel Ŭ���� - �����޼��� ��������� 
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










