package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String form() {
		return "param_form";
	}
	
	/*
	//요청 처리 메소드에 HttpServletRequest 자료형의 매개변수를 선언하면 클라이언트의 리퀘스트
	//메세지(HttpServletRequest 객체)를 Front Controller에게 제공받아 저장
	// => HttpServletRequest 객체를 이용하여 전달값을 반환받아 사용
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//전달값의 이름과 같은 이름의 String 자료형의 매개변수를 선언하면 Front Controller에 의해 
	//전달값이 자동으로 매개변수에 저장되어 제공
	// => 전달값의 이름과 매개변수의 이름이 다른 경우 매개변수에는 NULL 저장
	//전달값이 대한 캐릭터셋를 미리 변경하기 위해 web.xml 파일에서 CharacterEncodingFilter 클래스를
	//필터로 등록해야만 정상적인 전달값이 매개변수에 저장
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//@RequestParam : 전달값을 매개변수에 저장하기 위한 어노테이션
	// => 매개변수의 이름과 같은 이름의 전달값이 없는 경우 400 에러 발생
	// => 매개변수에 반드시 전달값이 저장되도록 설정하기 위한 사용하는 어노테이션
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//전달값의 이름과 매개변수의 이름이 다른 경우 @RequestParam 어노테이션의 value 속성을 
	//사용하만 전달값을 제공받아 매개변수에 저장 가능
	//value 속성 : 전달값의 이름을 속성값으로 설정
	// => 다른 속성이 없는 경우 속성값만 설정 가능
	//required 속성 : false 또는 true 중 하나를 속성값으로 설정
	// => false : 전달값 저장 미필수, true : 전달값 저장 필수 - 기본
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam("username") String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	//defaultValue 속성 : 전달값의 이름이 다르거나 전달값이 없는 경우 매개변수에 저장되는 기본값을 
	//속성값으로 설정
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam(value = "username", defaultValue = "임꺽정") String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
}





