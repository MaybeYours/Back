package xyz.itwill10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill10.dto.Member;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "session/login_form";
	}
	
	/*
	//요청 처리 메소드의 매개변수를 HttpSession 자료형으로 선언하면 Front Controller에
	//의해 바인딩 처리된 세션을 매개변수에 전달하여 제공  
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, @RequestParam String passwd
			, Model model, HttpSession session) {
		if(!id.equals("abc123") || !passwd.equals("123456")) {//인증 실패
			//Request Scope : 요청 처리 메소드의 뷰페이지에서만 속성값 사용 가능
			model.addAttribute("message", "아이디 또는 비밀번호를 확인해 주세요.");
			model.addAttribute("id", id);
			return "session/login_form";
		}
		
		//Session Scope : 동일한 세션을 사용하는 모든 요청 처리 메소드와 뷰페이지에서 속상값 사용 가능
		session.setAttribute("loginId", id);
		
		return "session/login_result";
	}
	*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Member member, Model model, HttpSession session) {
		if(!member.getId().equals("abc123") || !member.getPasswd().equals("123456")) {//인증 실패
			model.addAttribute("message", "아이디 또는 비밀번호를 확인해 주세요.");
			return "session/login_form";
		}
		
		session.setAttribute("loginId", member.getId());
		
		return "session/login_result";
	}
}
