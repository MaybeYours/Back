package xyz.itwill10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill10.dto.Userinfo;
import xyz.itwill10.exception.LoginAuthFailException;
import xyz.itwill10.exception.UserinfoExistsException;
import xyz.itwill10.exception.UserinfoNotFoundException;
import xyz.itwill10.service.UserinfoService;

@Controller
@RequestMapping("/userinfo")
public class UserinfoController {
	@Autowired
	private UserinfoService userinfoService;
	
	//회원정보를 입력받기 위한 요청 처리 메소드 - 관리자만 요청 가능
	// => 비로그인 사용자이거나 관리자가 아닌 사용자가 요청하면 예외 발생 
	// => 예외처리 메소드를 이용하여 에러 페이지로 응답 처리
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(HttpSession session) throws Exception {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
			throw new Exception();
		}
		return "userinfo/user_write";
	}
	
	/*
	//회원정보를 전달받아 USERINFO 테이블에 저장하는 요청 처리 메소드
	// => UserinfoService 객체의 메소드 호출시 예외 발생 가능 - try~catch 구문를 이용하여 예외처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Userinfo userinfo, Model model) {
		try {
			userinfoService.addUserinfo(userinfo);
		} catch(UserinfoExistsException e) {
			model.addAttribute("message", e.getMessage());
			return "userinfo/user_write";
		} catch (Exception e) {
			e.printStackTrace();
			return "userinfo/user_error";
		}
		return "redirect:/userinfo/login";
	}
	*/
	//회원정보를 전달받아 USERINFO 테이블에 저장하는 요청 처리 메소드
	// => UserinfoService 객체의 메소드 호출시 예외 발생 가능 - 예외처리 메소드를 사용하여 예외처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Userinfo userinfo, Model model) throws UserinfoExistsException {
		userinfoService.addUserinfo(userinfo);
		return "redirect:/userinfo/login";
	}
	
	//로그인정보를 입력받기 위한 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "userinfo/user_login";
	}
	
	//로그인정보를 전달받아 로그인 인증 처리하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, HttpSession session) throws LoginAuthFailException, UserinfoNotFoundException {
		//메소드 호출시 예외가 발생된 경우 인증 실패
		userinfoService.loginAuth(userinfo);
		//예외가 발생되지 않은 경우 인증 성공 - 세션에 권한 관련 정보를 속성값으로 저장
		session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		return "userinfo/user_login";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/userinfo/login";
	}
	
	//@ExceptionHandler : Controller 클래스의 요청 처리 메소드에서 발생된 예외를 처리하기 위해 
	//예외처리 메소드를 설정하는 어노테이션
	//value 속성 : 예외 처리할 예외클래스(Clazz)를 속성값으로 설정
	// => 다른 속성이 없는 경우 속성값만 설정 가능
	@ExceptionHandler(value = UserinfoExistsException.class)
	//예외처리 메소드의 매개변수에는 예외처리 관련 값(객체)를 제공받아 사용 가능하며 ViewName를 
	//반환하여 JSP 문서로 응답 처리
	public String exceptionHandler(UserinfoExistsException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userinfo", exception.getUserinfo());
		return "userinfo/user_write";
	}
	
	@ExceptionHandler(value = LoginAuthFailException.class)
	public String exceptionHandler(LoginAuthFailException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userid", exception.getUserid());
		return "userinfo/user_login";
	}

	/*
	@ExceptionHandler(value = UserinfoNotFoundException.class)
	public String exceptionHandler(UserinfoNotFoundException exception) {
		exception.printStackTrace();
		return "userinfo/user_error";
	}
	
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception exception) {
		exception.printStackTrace();
		return "userinfo/user_error";
	}
	*/
}
