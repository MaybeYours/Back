package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill10.dto.Member;

@Controller
public class JoinController {
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join_form";
	}
	
	/*
	//전달값의 이름과 같은 이름의 매개변수를 작성하여 전달값 저장
	//@RequestParam 어노테이션을 사용하여 매개변수의 이름과 같은 이름의 전달값이 없는 경우 400 에러 발생
	// => value 속성을 이용하여 매개변수의 이름과 다른 이름의 전달값을 제공받아 매개변수에 저장 가능
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam String id, @RequestParam String passwd
			, @RequestParam String name, @RequestParam String email, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("passwd", passwd);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "join_display";
	}
	*/
	
	/*
	//@ModelAttribute : 뷰페이지(JSP)에서 사용하기 위한 속성값(객체)을 제공하는 어노테이션
	// => @ModelAttribute 어노테이션은 메소드와 매개변수에 선언 가능
	//@ModelAttribute 어노테이션을 메소드에 선언하면 메소드 반환값을 Controller 클래스의
	//모든 요청 처리 메소드의 뷰페이지에게 속성값으로 제공
	//@ModelAttribute 어노테이션을 매개변수에 선언하면 매개변수에 저장된 값을 요청 처리 메소드의 
	//뷰페이지에게 속성값으로 제공 - 매개변수에 저장된 전달값을 뷰페이지에게 제공
	// => 매개변수의 이름과 전달값의 이름이 같지 않아도 400 에러 미발생
	// => 매개변수의 자료형이 기본형(Wrapper 클래스)이나 String 클래스인 경우 반드시 value 속성을 설정
	//value 속성 : 뷰페이지에 제공하기 위한 속성명을 속성값으로 설정
	// => 다른 속성이 없는 경우 속성값만 설정 가능
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute(value="id") String id, @ModelAttribute("passwd") String passwd
			, @ModelAttribute("name") String name, @ModelAttribute("email") String email) {
		return "join_display";
	}
	*/
	
	/*
	//요청 처리 메소드의 매개변수 자료형을 JavaBean 클래스(DTO 클래스)로 선언하면 Front Controller가
	//JavaBean 객체를 생성하여 전달값을 필드에 저장하여 제공 - Command 객체
	//Command 객체 : 전달값이 필드에 저장된 객체로 요청 처리 메소드의 뷰페이지에서 사용되도록 제공되는 객체
	// => 전달값은 같은 이름의 필드에 자동 저장
	//뷰페이지에서 Command 객체가 사용되도록 @ModelAttribute 어노테이션을 사용하여 선언하는 것을 권장
	// => @ModelAttribute 어노테이션 선언하지 않아도 자동 처리
	//value 속성으로 뷰페이지에 제공되는 속성명을 설정할 수 있지만 value 속성이 생략된 경우 
	//클래스명을 속성명으로 자동 설정 - 첫문자는 소문자로 변환되어 제공
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Member member) {
		return "join_display";
	}
	*/
	
	//@ModelAttribute 어노테이션의 value 속성을 사용하여 뷰페이지에 제공되는 속성명 변경 가능
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("mem") Member member, Model model) {
		if(member.getId().equals("abc123")) {//아이디가 중복된 경우
			model.addAttribute("message", "이미 사용중인 아이디입니다.");
			return "join_form";
		}
		return "join_display";
	}
}








