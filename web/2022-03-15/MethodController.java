package xyz.itwill10.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {
	//입력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => 요청 방식에 상관없이 요청해도 동작되는 요청 처리 메소드 
	@RequestMapping("/method_input")
	public String inputOne() {
		return "method_input1";
	}

	//전달값을 반환받아 출력페이지에게 제공하고 출력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => 요청 방식에 상관없이 요청해도 동작되는 요청 처리 메소드 
	@RequestMapping("/method_output")
	public String outputOne(HttpServletRequest request) throws UnsupportedEncodingException {
		if(request.getMethod().equals("GET")) {//GET 방식으로 요청한 경우 - 비정상적인 요청
			return "method_input1";//입력페이지의 ViewName 반환
		}
		
		//HttpServletRequest.setCharacterEncoding(String encoding) : POST 방식으로 요청하여
		//전달된 값에 대한 캐릭터셋을 변경하는 메소드 - UnsupportedEncodingException 발생 : 예외 처리 
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output1";
	}

	//입력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => GET 방식으로만 요청하여 동작되는 요청 처리 메소드
	//method 속성 : 요청 처리 메소드를 호출하기 위한 클라이언트 요청방식을 속성값으로 설정
	// => RequestMethod 자료형(Enum)의 상수를 속성값으로 설정
	//요청방식에 따라 @RequestMapping 어노테이션 대신 @GetMapping, @PostMapping 등의 어노테이션을
	//사용하여 요청 처리 메소드 설정 가능
	@RequestMapping(value = "/method", method = RequestMethod.GET)
	public String inputTwo() {
		return "method_input2";
	}
	
	//전달값을 반환받아 출력페이지에게 제공하고 출력페이지에 대한 ViewName를 반환하는 요청 처리 메소드
	// => POST 방식으로만 요청하여 동작되는 요청 처리 메소드
	//@RequestMapping 어노테이션의 요청 URL 주소가 같은 경우 클라이언트의 요청방식으로 구분하여
	//요청 처리 메소드 호출 가능
	@RequestMapping(value = "/method", method = RequestMethod.POST)
	public String outputTwo(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output2";
	}	
}




