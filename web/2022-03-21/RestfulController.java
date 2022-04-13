package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.itwill10.dto.Member;

//REST(RePresentation State Transfer) 기능을 제공하는 컨트롤러
//REST : 클라이언트 요청에 대해 요청 처리 메소드에서 요청 처리 후 처리 결과를 뷰페이지(JSP)가
//아닌 직접 리소스(TEXT, XML, JSON 등)로 응답하는 기능
// => 다양한 기기에서 공통적으로 처리 가능한 텍스트 데이타로 응답하는 것을 목적으로 만든 웹프로그램
// => 사이트 구현시 AJAX 기능으로 REST 웹프로그램을 요청하고 처리결과를 JSON으로 응답받아 DHTML로 페이지를 변경

@Controller
public class RestfulController {
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public String rest() {
		return "rest/input";
	}
	
	/*
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	public String rest(@RequestParam String id, @RequestParam String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "rest/output";
	}
	*/
	
	//@ResponseBody : 요청 처리 메소드의 반환값(문자열)을 클라이언트에게 직접 텍스트 데이타로 응답되도록 설정하는 어노테이션
	// => 문자열(String)로만 응답 가능 - Java 객체는 응답 불가능
	//@RequestBody : POST, PUT, PATCH, DELETE  등의 요청에 의해 전달된 모든 값을 하나의 문자열로 전달하여 저장하기 위한 어노테이션
	// => GET 방식으로 요청하여 전달된 값은 매개변수에 저장 불가능
	// => 전달값은 자바스크립트의 Object 객체 형식으로 전달(이름=값&이름=값&... >> {"이름":값,"이름":값,...})
	// => AJAX 요청에 의해 전달된 값을 저장하기 위해 사용
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	@ResponseBody
	public String rest(@RequestBody String inputData) {
		return inputData;
	}
	
	//회원정보를 텍스트 데이타로 응답하는 요청 처리 메소드
	// => 문자열(String)이 아닌 Java 객체의 자료형으로 응답할 경우 406 에러 발생
	//Java 객체를 JSON 형식의 텍스트 데이타로 자동 변환되어 문자열로 응답되도록 설정
	// => jackson-databind 라이브러리를 프로젝트에 빌드 처리 - pom.xml
	@RequestMapping("/rest_member")
	@ResponseBody
	public Member restMember() {
		Member member=new Member();
		member.setId("abc123");
		member.setPasswd("123456");
		member.setName("홍길동");
		member.setEmail("abc@itwill.xyz");
		
		return member;
	}
}







