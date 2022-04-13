package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import xyz.itwill10.dto.Hewon;

@Controller
//@SessionAttributes : Model 객체를 이용하여 저장되는 속성값을 요청 처리 메소드의 뷰페이지에게만  
//제공하지 않고 Controller 클래스의 모든 요청 처리 메소드와 뷰페이지에게 제공하는 어노테이션
// => Model 객체를 이용하여 저장된 속성값이 Request Scope가 아닌 제한적인 Session Scope로 처리
// => 변경 처리시 다른 요청 처리 메소드에서 필요한 정보를 전달하거나 검색하는 작업을 생략하기 위해 사용
//value 속성 : 제한적인 Session Scope로 처리하기 위한 속성명을 속성값으로 설정
// => 다른 속성이 없는 경우 속성값만 설정 가능
@SessionAttributes("hewon")
public class SessionController {
	//아이디를 전달받아 회원정보를 검색하여 반환하는 메소드 - Service 클래스의 메소드
	private Hewon getHewon(String id) {
		Hewon hewon=new Hewon();
		hewon.setId("abc123");
		hewon.setPasswd("123456");
		hewon.setName("홍길동");
		hewon.setEmail("abc@itwill.xyz");
		hewon.setGender("남자");
		return hewon;
	}
	
	//아이디를 전달받아 회원정보를 검색하여 뷰페이지에 제공하는 요청 처리 메소드
	// => 제공받은 회원정보를 클라이언트에게 응답되도록 뷰페이지에서 처리
	@RequestMapping("/hewon_view")
	public String hewonView(@RequestParam(defaultValue = "abc123") String id, Model model) {
		//회원정보를 검색하여 반환받아 저장 - Service 클래스의 메소드 호출
		Hewon hewon=getHewon(id);
		
		//Model 객체를 이용하여 뷰페이지에게 검색된 회원정보 제공 - Request Scope
		//model.addAttribute("hewon", hewon);
		
		//Model.addAttribute(Object attributeValue) : 뷰페이지에게 속성값을 제공하는 메소드
		// => 속성명이 생략된 경우 속성값(객체)의 클래스명을 속성명으로 자동 설정 - 첫문자는 소문자로 변환하여 제공
		// => 속성값의 자료형이 기본형(Wrapper)이거나 String 클래스인 경우 호출 불가능
		//@SessionAttributes 어노테이션에 의해 제한적인 Session Scope로 처리
		// => Controller 클래스의 모든 요청 처리 메소드와 뷰페이지에서 속성값 사용 가능
		model.addAttribute(hewon);
		
		return "session/hewon_view";
	}
	
	/*
	//아이디를 전달받아 회원정보를 검색하여 뷰페이지에 제공하는 요청 처리 메소드
	// => 제공받은 회원정보를 입력태그의 초기값으로 설정되도록 뷰페이지에서 처리
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String hewonUpdate(@RequestParam String id, Model model) {
		//회원정보를 검색하여 반환받아 저장 - Service 클래스의 메소드 호출
		Hewon hewon=getHewon(id);

		//Model 객체를 이용하여 뷰페이지에게 검색된 회원정보 제공 - Request Scope
		model.addAttribute(hewon);

		return "session/hewon_update";
	}
	*/
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String hewonUpdate() {
		return "session/hewon_update";
	}
	
	//회원정보를 전달받아 변경 처리하는 요청 처리 메소드
	//@SessionAttributes 어노테이션을 사용한 경우 Command 객체에 속성값이 자동 저장되어 
	//제공되며 전달값이 있는 경우 Command 객체의 필드값 변경 처리 
	//SessionStatus : @SessionAttributes 어노테이션에 의해 제공된 속성값(객체)의 상태정보를 저장한 객체 
	@RequestMapping(value = "/hewon_update", method = RequestMethod.POST)
	public String hewonUpdate(@ModelAttribute Hewon hewon, SessionStatus status) {
		System.out.println("변경 아이디 = "+hewon.getId());

		//SessionStatus.setComplete() : @SessionAttributes 어노테이션에 의해 제한적인 
		//Session Scope로 제공된 객체를 Request Scope로 제공된 객체로 처리되도록 변경하는 메소드 
		status.setComplete();
		
		return "session/hewon_result";
	}
}






