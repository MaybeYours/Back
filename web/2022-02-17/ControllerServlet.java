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

//컨트롤러(Controller) : 클라이언트의 모든 요청을 받아 요청에 대한 처리는 모델(Model : 클래스)을
//이용하여 구현하고 처리결과를 뷰(View : JSP)에게 전달하여 응답되도록 이동하는 웹프로그램(서블릿)

//1.클라이언트의 모든 요청을 받을 수 있도록 서블릿 설정 - 단일 진입점의 기능 구현 : Front Controller Pattern
//@WebServlet("URL") : 서블릿 클래스를 웹프로그램(서블릿)으로 등록하고 URL 주소를 매핑하는 어노테이션
// => URL 주소에 패턴문자(* 또는 ?)를 사용하여 설정 가능
// => @WebServlet("*.do") : 클라이언트가 XXX.do 형식의 URL 주소로 요청한 경우 서블릿 실행
// => @WebServlet 어노테이션 대신 web.xml 파일에서 서블릿 클래스를 웹프로그램으로 등록하여 URL 주소 매핑 
//@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//요청정보(Key - String)와 모델 인스턴스(Value - Action)를 저장하기 위한 콜렉션 필드
	private Map<String, Action> actionMap;
	
	//클라이언트 요청에 의해 서블릿 클래스가 인스턴스로 생성된 후 가장 먼저 자동 호출되는 메소드
	// => 인스턴스 생성 후 한번만 호출 - 초기화 작업 
	@Override
	public void init(ServletConfig config) throws ServletException {
		//System.out.println("ControllerServlet 클래스의 init() 메소드 호출");
		
		actionMap=new HashMap<String, Action>();

		/*
		//Map 인스턴스에 엔트리(Entry - 요청정보와 모델 인스턴스)를 추가하여 저장
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
		
		//Properties 파일에 요청정보와 모델 클래스를 저장하고 파일의 내용을 읽어 Map 인스턴스의
		//엔트리로 추가하여 저장 - 유지보수의 효율성 증가
		//Properties 파일(XXX.properties) : 프로그램 실행에 필요한 값을 제공하는 텍스트 파일
		
		//Properties 파일의 내용을 읽기 저장하기 위한 Properties 인스턴스 생성
		Properties properties=new Properties();
		
		//ServletConfig.getInitParameter(String name) : web.xml 파일의 servlet 엘리먼트로 제공되는
		//값을 얻어와 반환하는 메소드 - init-param 엘리먼트로 이름과 값을 설정하여 제공
		String configFile=config.getInitParameter("configFile");
		
		//Properties 파일의 시스템 경로를 반환받아 저장
		//String configFilePath=config.getServletContext().getRealPath("/WEB-INF/model.properties");
		String configFilePath=config.getServletContext().getRealPath(configFile);
		
		try {
			//Properties 파일에 대한 입력스트림을 생성하여 저장
			FileInputStream in=new FileInputStream(configFilePath);
			
			//Properties 파일의 저장된 모든 값들을 이용하여 Properties 인스턴스의 엔트리로 추가하여 저장
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Properties 인스턴스에 저장된 엔트리(Key : 요청정보, Value : 모델 클래스)를 Map 인스턴스에 
		//엔트리(Key : 요청정보, Value : 모델 인스턴스)로 추가하여 저장 - 반복 처리
		//properties.keySet() : Properties 인스턴스에 저장된 모든 엔트리의 키(Key)가 저장된 Set 인스턴스로 반환하는 메소드
		for(Object key:properties.keySet()) {
			String actionCommand=(String)key;
			
			//Properties.get(String key) : Properties 인스턴스에서 키를 전달받아 값을 반환하는 메소드
			String actionClass=(String)properties.get(key);
			
			try {
				//모델 클래스를 이용하여 모델 인스턴스 생성 - 리플렉션 기능 이용
				//리플렉션(Reflection) : 프로그램 실행시 클래스(Clazz)를 읽어 인스턴스 생성하고  
				//인스턴스의 요소(필드 또는 메소드)에 접근 가능하도록 제공하는 기능
				//Class.forName(String className) : 전달받은 문자열의 클래스를 읽어 메모리에
				//저장하는 메소드 - Class 인스턴스(Clazz) 반환
				//Class.getDeclaredConstructor().newInstance() :  메모리에 저장된 클래스(Clazz)의
				//생성자를 제공받아 인스턴스를 생성하여 반환하는 메소드 
				Action actionObject=(Action)Class.forName(actionClass).getDeclaredConstructor().newInstance();
				
				//Map 인스턴스를 엔트리(Key : 요청정보, Value : 모델 인스턴스)를 추가하여 저장
				actionMap.put(actionCommand, actionObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//클라이언트의 요청을 처리하기 위해 자동 호출되는 메소드
	// => 클라이언트가 웹프로그램을 요청할 때마다 반복적으로 호출
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("ControllerServlet 클래스의 service() 메소드 호출");
		
		//2.클라이언트의 요청 분석 : 요청 URL 주소 이용 - http://localhost:8000/mvc/XXX.do
		//HttpServletRequest.getRequestURI() : 요청 URL 주소의 URI를 반환하는 메소드
		String requestURI=request.getRequestURI();
		//System.out.println("requestURI = "+requestURI);//requestURI = /mvc/XXX.do
		
		//HttpServletRequest.getContextPath() : 요청 URL 주소의 컨텍스트 경로를 반환하는 메소드
		String contextPath=request.getContextPath();
		//System.out.println("contextPath = "+contextPath);//contextPath = /mvc
		
		String command=requestURI.substring(contextPath.length());
		//System.out.println("command = "+command);//command = /XXX.do
		
		//3.클라이언트 요청에 대해 모델(Model)을 이용하여 처리하고 뷰(View) 관련 정보를 반환받아 저장
		// => 모델 역활의 클래스로 인스턴스를 생성하여 요청 처리 메소드 호출
		// => 하나의 요청에 대해 하나의 모델 클래스의 요청 처리 메소드가 호출되어 클라이언트의  
		//요청을 처리하고 뷰 관련 정보를 반환 - Command Controller Pattern
		/*
		//클라이언트 요청에 대한 모델 클래스 매핑 설계
		// => 모든 모델 클래스는 같은 인터페이스를 상속 받아 동일한 구조로 작성 - 사용성 및 유지보수의 효율성 증가
		/loginForm.do : LoginFormModel 클래스 - 로그인정보 입력페이지 또는 환영메세지 출력페이지
		/login.do : LoginModel 클래스 - 로그인 처리페이지
		/logout.do : LogoutModel 클래스 - 로그아웃 처리페이지
		/writeForm.do : WriteFormModel 클래스 - 회원정보 입력페이지 
		/write.do : WriteModel 클래스 - 회원정보 저장페이지 
		/list.do : ListModel 클래스 - 회원목록 출력페이지 
		/view.do : ViewModel 클래스 - 회원정보 출력페이지 
		/modifyForm.do : ModifyFormModel 클래스 - 회원정보 변경 입력페이지 
		/modify.do : ModifyModel 클래스 - 회원정보 변경페이지 
		/remove.do : RemoveModel 클래스 - 회원정보 삭제페이지 
		/error.do : ErrorModel 클래스 - 에러메세지 출력페이지 
		*/
		//모델 클래스가 상속받은 인터페이스를 이용하여 참조변수 생성
		// => 참조변수에는 인터페이스를 상속받은 모든 자식클래스의 인스턴스 저장 가능
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
		
		//Map 인스턴스에 저장된 엔트리에서 요청정보(Key)를 전달하여 모델 인스턴스(Value)를 반환받아 저장 - 가독성 증가
		Action action=actionMap.get(command);
		if(action==null) {//클라이언트 요청에 대한 모델 인스턴스가 없는 경우
			action=new ErrorModel();
		}
		
		//부모 인터페이스의 추상메소드를 호출하면 참조변수에 저장된 자식 인스턴스(모델)의
		//오버라이드 메소드 호출 - 메소드 오버라이드에 의한 다형성
		// => 요청 처리 메소드를 호출하여 뷰 관련 정보를 반환받아 저장
		ActionForward actionForward=action.execute(request, response);
		
		//4.반환받은 뷰 관련 정보(ActionForward 인스턴스)를 이용하여 클라이언트에게 응답 처리
		if(actionForward.isForward()) {//forward 필드값이 [true]인 경우 - 포워드 이동
			//컨트롤러(Controller - Servlet)에서 뷰(View - JSP)로 스레드를 이동하여 JSP 문서로 
			//클라이언트에게 처리결과(HTML)를 전달하여 응답
			request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
		} else {//forward 필드값이 [false]인 경우 - 리다이렉트 이동
			//클라이언트에게 URL 주소(XXX.do)를 전달하여 재요청하도록 응답
			response.sendRedirect(actionForward.getPath());
		}
		
	}

}










