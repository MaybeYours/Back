package xyz.itwill.mvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//��Ʈ�ѷ�(Controller) : Ŭ���̾�Ʈ�� ��� ��û�� �޾� ��û�� ���� ó���� ��(Model : Ŭ����)��
//�̿��Ͽ� �����ϰ� ó������� ��(View : JSP)���� �����Ͽ� ����ǵ��� �̵��ϴ� �����α׷�(����)

//1.Ŭ���̾�Ʈ�� ��� ��û�� ���� �� �ֵ��� ���� ���� - ���� �������� ��� ���� : Front Controller Pattern
//@WebServlet("URL") : ���� Ŭ������ �����α׷�(����)���� ����ϰ� URL �ּҸ� �����ϴ� ������̼�
// => URL �ּҿ� ���Ϲ���(* �Ǵ� ?)�� ����Ͽ� ���� ����
// => @WebServlet("*.do") : Ŭ���̾�Ʈ�� XXX.do ������ URL �ּҷ� ��û�� ��� ���� ����
// => @WebServlet ������̼� ��� web.xml ���Ͽ��� ���� Ŭ������ �����α׷����� ����Ͽ� URL �ּ� ���� 
//@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//��û����(Key - String)�� �� �ν��Ͻ�(Value - Action)�� �����ϱ� ���� �ݷ��� �ʵ�
	private Map<String, Action> actionMap;
	
	//Ŭ���̾�Ʈ ��û�� ���� ���� Ŭ������ �ν��Ͻ��� ������ �� ���� ���� �ڵ� ȣ��Ǵ� �޼ҵ�
	// => �ν��Ͻ� ���� �� �ѹ��� ȣ�� - �ʱ�ȭ �۾� 
	@Override
	public void init(ServletConfig config) throws ServletException {
		//System.out.println("ControllerServlet Ŭ������ init() �޼ҵ� ȣ��");
		
		actionMap=new HashMap<String, Action>();

		/*
		//Map �ν��Ͻ��� ��Ʈ��(Entry - ��û������ �� �ν��Ͻ�)�� �߰��Ͽ� ����
		actionMap.put("/loginForm.do", new LoginFormModel());
		actionMap.put("/login.do", new LoginModel());
		actionMap.put("/logout.do", new LogoutModel());
		actionMap.put("/writeForm.do", new WriteFormModel());
		actionMap.put("/write.do", new WriteModel());
		actionMap.put("/list.do", new ListModel());
		actionMap.put("/view.do", new ViewModel());
		actionMap.put("/modifyForm.do", new ModifyFormModel());
		actionMap.put("/modify.do", new ModifyModel());
		actionMap.put("/remove.do", new RemoveModel());
		actionMap.put("/error.do", new ErrorModel());
		*/
		
		//Properties ���Ͽ� ��û������ �� Ŭ������ �����ϰ� ������ ������ �о� Map �ν��Ͻ���
		//��Ʈ���� �߰��Ͽ� ���� - ���������� ȿ���� ����
		//Properties ����(XXX.properties) : ���α׷� ���࿡ �ʿ��� ���� �����ϴ� �ؽ�Ʈ ����
		
		//Properties ������ ������ �б� �����ϱ� ���� Properties �ν��Ͻ� ����
		Properties properties=new Properties();
		
		//ServletConfig.getInitParameter(String name) : web.xml ������ servlet ������Ʈ�� �����Ǵ�
		//���� ���� ��ȯ�ϴ� �޼ҵ� - init-param ������Ʈ�� �̸��� ���� �����Ͽ� ����
		String configFile=config.getInitParameter("configFile");
		
		//Properties ������ �ý��� ��θ� ��ȯ�޾� ����
		//String configFilePath=config.getServletContext().getRealPath("/WEB-INF/model.properties");
		String configFilePath=config.getServletContext().getRealPath(configFile);
		
		try {
			//Properties ���Ͽ� ���� �Է½�Ʈ���� �����Ͽ� ����
			FileInputStream in=new FileInputStream(configFilePath);
			
			//Properties ������ ����� ��� ������ �̿��Ͽ� Properties �ν��Ͻ��� ��Ʈ���� �߰��Ͽ� ����
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Properties �ν��Ͻ��� ����� ��Ʈ��(Key : ��û����, Value : �� Ŭ����)�� Map �ν��Ͻ��� 
		//��Ʈ��(Key : ��û����, Value : �� �ν��Ͻ�)�� �߰��Ͽ� ���� - �ݺ� ó��
		//properties.keySet() : Properties �ν��Ͻ��� ����� ��� ��Ʈ���� Ű(Key)�� ����� Set �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
		for(Object key:properties.keySet()) {
			String actionCommand=(String)key;
			
			//Properties.get(String key) : Properties �ν��Ͻ����� Ű�� ���޹޾� ���� ��ȯ�ϴ� �޼ҵ�
			String actionClass=(String)properties.get(key);
			
			try {
				//�� Ŭ������ �̿��Ͽ� �� �ν��Ͻ� ���� - ���÷��� ��� �̿�
				//���÷���(Reflection) : ���α׷� ����� Ŭ����(Clazz)�� �о� �ν��Ͻ� �����ϰ�  
				//�ν��Ͻ��� ���(�ʵ� �Ǵ� �޼ҵ�)�� ���� �����ϵ��� �����ϴ� ���
				//Class.forName(String className) : ���޹��� ���ڿ��� Ŭ������ �о� �޸𸮿�
				//�����ϴ� �޼ҵ� - Class �ν��Ͻ�(Clazz) ��ȯ
				//Class.getDeclaredConstructor().newInstance() :  �޸𸮿� ����� Ŭ����(Clazz)��
				//�����ڸ� �����޾� �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ� 
				Action actionObject=(Action)Class.forName(actionClass).getDeclaredConstructor().newInstance();
				
				//Map �ν��Ͻ��� ��Ʈ��(Key : ��û����, Value : �� �ν��Ͻ�)�� �߰��Ͽ� ����
				actionMap.put(actionCommand, actionObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
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
		
		//3.Ŭ���̾�Ʈ ��û�� ���� ��(Model)�� �̿��Ͽ� ó���ϰ� ��(View) ���� ������ ��ȯ�޾� ����
		// => �� ��Ȱ�� Ŭ������ �ν��Ͻ��� �����Ͽ� ��û ó�� �޼ҵ� ȣ��
		// => �ϳ��� ��û�� ���� �ϳ��� �� Ŭ������ ��û ó�� �޼ҵ尡 ȣ��Ǿ� Ŭ���̾�Ʈ��  
		//��û�� ó���ϰ� �� ���� ������ ��ȯ - Command Controller Pattern
		/*
		//Ŭ���̾�Ʈ ��û�� ���� �� Ŭ���� ���� ����
		// => ��� �� Ŭ������ ���� �������̽��� ��� �޾� ������ ������ �ۼ� - ��뼺 �� ���������� ȿ���� ����
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
		//�� Ŭ������ ��ӹ��� �������̽��� �̿��Ͽ� �������� ����
		// => ������������ �������̽��� ��ӹ��� ��� �ڽ�Ŭ������ �ν��Ͻ� ���� ����
		/*
		Action action=null;
		if(command.equals("/loginForm.do")) {
			action=new LoginFormModel();
		} else if(command.equals("/login.do")) {
			action=new LoginModel();
		} else if(command.equals("/logout.do")) {
			action=new LogoutModel();
		} else if(command.equals("/writeForm.do")) {
			action=new WriteFormModel();
		} else if(command.equals("/write.do")) {
			action=new WriteModel();
		} else if(command.equals("/list.do")) {
			action=new ListModel();
		} else if(command.equals("/view.do")) {
			action=new ViewModel();
		} else if(command.equals("/modifyForm.do")) {
			action=new ModifyFormModel();
		} else if(command.equals("/modify.do")) {
			action=new ModifyModel();
		} else if(command.equals("/remove.do")) {
			action=new RemoveModel();
		} else if(command.equals("/error.do")) {
			action=new ErrorModel();
		} else {
			action=new ErrorModel();
		}
		*/
		
		//Map �ν��Ͻ��� ����� ��Ʈ������ ��û����(Key)�� �����Ͽ� �� �ν��Ͻ�(Value)�� ��ȯ�޾� ���� - ������ ����
		Action action=actionMap.get(command);
		if(action==null) {//Ŭ���̾�Ʈ ��û�� ���� �� �ν��Ͻ��� ���� ���
			action=new ErrorModel();
		}
		
		//�θ� �������̽��� �߻�޼ҵ带 ȣ���ϸ� ���������� ����� �ڽ� �ν��Ͻ�(��)��
		//�������̵� �޼ҵ� ȣ�� - �޼ҵ� �������̵忡 ���� ������
		// => ��û ó�� �޼ҵ带 ȣ���Ͽ� �� ���� ������ ��ȯ�޾� ����
		ActionForward actionForward=action.execute(request, response);
		
		//4.��ȯ���� �� ���� ����(ActionForward �ν��Ͻ�)�� �̿��Ͽ� Ŭ���̾�Ʈ���� ���� ó��
		if(actionForward.isForward()) {//forward �ʵ尪�� [true]�� ��� - ������ �̵�
			//��Ʈ�ѷ�(Controller - Servlet)���� ��(View - JSP)�� �����带 �̵��Ͽ� JSP ������ 
			//Ŭ���̾�Ʈ���� ó�����(HTML)�� �����Ͽ� ����
			request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
		} else {//forward �ʵ尪�� [false]�� ��� - �����̷�Ʈ �̵�
			//Ŭ���̾�Ʈ���� URL �ּ�(XXX.do)�� �����Ͽ� ���û�ϵ��� ����
			response.sendRedirect(actionForward.getPath());
		}
		
	}

}










