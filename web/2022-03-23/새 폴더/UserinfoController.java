package xyz.itwill10.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	/*
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
	*/
	
	//Interceptor 클래스를 이용하여 비로그인 사용자 또는 관리자가 아닌 사용자가 요청한 경우 
	//요청 처리 메소드의 명령이 실행되지 않도록 설정
	// => Interceptor 클래스를 작성하고 Bean Configuration File(servlet-context.xml)에서 Spring
	//Bean으로 등록한 후 인터셉터가 동작될 수 있도록 설정
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() throws Exception {
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
	public String join(@ModelAttribute Userinfo userinfo) throws UserinfoExistsException {
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
	
	//로그아웃 처리하는 요청 처리 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/userinfo/login";
	}
	
	//회원 목록을 검색하여 응답하는 요청 처리 메소드
	// => 로그인 사용자만 요청 가능하도록 인터셉터 사용
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		return "userinfo/user_list";
	}
	
	//아이디를 전달받아 해당 아이디의 회원정보을 검색하여 응답하는 요청 처리 메소드
	// => 로그인 사용자만 요청 가능하도록 인터셉터 사용
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_view";
	}
	
	//아이디를 전달받아 해당 아이디의 회원정보를 검색하여 입력태그로 응답하는 요청 처리 메소드
	// => 관리자만 요청 가능하도록 인터셉터 사용
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_modify";
	}
	
	//회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하는 요청 처리 메소드
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Userinfo userinfo, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.modifyUserinfo(userinfo);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//변경 처리된 회원(관리자)이 현재 로그인 사용자인 경우 세션에 저장된 권한 관련 정보 변경
		if(loginUserinfo.getUserid().equals(userinfo.getUserid())) {
			session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		}
		
		return "redirect:/userinfo/view?userid="+userinfo.getUserid();
	}
	
	//아이디를 전달받아 해당 아이디의 회원정보를 삭제하는 요청 처리 메소드
	// => 관리자만 요청 가능하도록 인터셉터 사용
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.removeUserinfo(userid);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//삭제 처리된 회원(관리자)이 현재 로그인 사용자인 경우 로그아웃 처리
		if(loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}
		
		return "redirect:/userinfo/list";
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
