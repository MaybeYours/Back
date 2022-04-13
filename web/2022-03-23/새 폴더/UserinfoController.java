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
	//ȸ�������� �Է¹ޱ� ���� ��û ó�� �޼ҵ� - �����ڸ� ��û ����
	// => ��α��� ������̰ų� �����ڰ� �ƴ� ����ڰ� ��û�ϸ� ���� �߻� 
	// => ����ó�� �޼ҵ带 �̿��Ͽ� ���� �������� ���� ó��
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(HttpSession session) throws Exception {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo==null || loginUserinfo.getStatus()!=9) {
			throw new Exception();
		}
		return "userinfo/user_write";
	}
	*/
	
	//Interceptor Ŭ������ �̿��Ͽ� ��α��� ����� �Ǵ� �����ڰ� �ƴ� ����ڰ� ��û�� ��� 
	//��û ó�� �޼ҵ��� ����� ������� �ʵ��� ����
	// => Interceptor Ŭ������ �ۼ��ϰ� Bean Configuration File(servlet-context.xml)���� Spring
	//Bean���� ����� �� ���ͼ��Ͱ� ���۵� �� �ֵ��� ����
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() throws Exception {
		return "userinfo/user_write";
	}
	
	/*
	//ȸ�������� ���޹޾� USERINFO ���̺� �����ϴ� ��û ó�� �޼ҵ�
	// => UserinfoService ��ü�� �޼ҵ� ȣ��� ���� �߻� ���� - try~catch ������ �̿��Ͽ� ����ó��
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
	//ȸ�������� ���޹޾� USERINFO ���̺� �����ϴ� ��û ó�� �޼ҵ�
	// => UserinfoService ��ü�� �޼ҵ� ȣ��� ���� �߻� ���� - ����ó�� �޼ҵ带 ����Ͽ� ����ó��
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Userinfo userinfo) throws UserinfoExistsException {
		userinfoService.addUserinfo(userinfo);
		return "redirect:/userinfo/login";
	}
	
	//�α��������� �Է¹ޱ� ���� ��û ó�� �޼ҵ�
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "userinfo/user_login";
	}
	
	//�α��������� ���޹޾� �α��� ���� ó���ϴ� ��û ó�� �޼ҵ�
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, HttpSession session) throws LoginAuthFailException, UserinfoNotFoundException {
		//�޼ҵ� ȣ��� ���ܰ� �߻��� ��� ���� ����
		userinfoService.loginAuth(userinfo);
		//���ܰ� �߻����� ���� ��� ���� ���� - ���ǿ� ���� ���� ������ �Ӽ������� ����
		session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		return "userinfo/user_login";
	}
	
	//�α׾ƿ� ó���ϴ� ��û ó�� �޼ҵ�
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/userinfo/login";
	}
	
	//ȸ�� ����� �˻��Ͽ� �����ϴ� ��û ó�� �޼ҵ�
	// => �α��� ����ڸ� ��û �����ϵ��� ���ͼ��� ���
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		return "userinfo/user_list";
	}
	
	//���̵� ���޹޾� �ش� ���̵��� ȸ�������� �˻��Ͽ� �����ϴ� ��û ó�� �޼ҵ�
	// => �α��� ����ڸ� ��û �����ϵ��� ���ͼ��� ���
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_view";
	}
	
	//���̵� ���޹޾� �ش� ���̵��� ȸ�������� �˻��Ͽ� �Է��±׷� �����ϴ� ��û ó�� �޼ҵ�
	// => �����ڸ� ��û �����ϵ��� ���ͼ��� ���
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model) throws UserinfoNotFoundException {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_modify";
	}
	
	//ȸ�������� ���޹޾� USERINFO ���̺� ����� ȸ�������� �����ϴ� ��û ó�� �޼ҵ�
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Userinfo userinfo, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.modifyUserinfo(userinfo);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//���� ó���� ȸ��(������)�� ���� �α��� ������� ��� ���ǿ� ����� ���� ���� ���� ����
		if(loginUserinfo.getUserid().equals(userinfo.getUserid())) {
			session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		}
		
		return "redirect:/userinfo/view?userid="+userinfo.getUserid();
	}
	
	//���̵� ���޹޾� �ش� ���̵��� ȸ�������� �����ϴ� ��û ó�� �޼ҵ�
	// => �����ڸ� ��û �����ϵ��� ���ͼ��� ���
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) throws UserinfoNotFoundException {
		userinfoService.removeUserinfo(userid);
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//���� ó���� ȸ��(������)�� ���� �α��� ������� ��� �α׾ƿ� ó��
		if(loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}
		
		return "redirect:/userinfo/list";
	}
	
	//@ExceptionHandler : Controller Ŭ������ ��û ó�� �޼ҵ忡�� �߻��� ���ܸ� ó���ϱ� ���� 
	//����ó�� �޼ҵ带 �����ϴ� ������̼�
	//value �Ӽ� : ���� ó���� ����Ŭ����(Clazz)�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	@ExceptionHandler(value = UserinfoExistsException.class)
	//����ó�� �޼ҵ��� �Ű��������� ����ó�� ���� ��(��ü)�� �����޾� ��� �����ϸ� ViewName�� 
	//��ȯ�Ͽ� JSP ������ ���� ó��
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
