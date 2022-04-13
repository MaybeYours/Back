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
	//��û ó�� �޼ҵ忡 HttpServletRequest �ڷ����� �Ű������� �����ϸ� Ŭ���̾�Ʈ�� ������Ʈ
	//�޼���(HttpServletRequest ��ü)�� Front Controller���� �����޾� ����
	// => HttpServletRequest ��ü�� �̿��Ͽ� ���ް��� ��ȯ�޾� ���
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//���ް��� �̸��� ���� �̸��� String �ڷ����� �Ű������� �����ϸ� Front Controller�� ���� 
	//���ް��� �ڵ����� �Ű������� ����Ǿ� ����
	// => ���ް��� �̸��� �Ű������� �̸��� �ٸ� ��� �Ű��������� NULL ����
	//���ް��� ���� ĳ���ͼ¸� �̸� �����ϱ� ���� web.xml ���Ͽ��� CharacterEncodingFilter Ŭ������
	//���ͷ� ����ؾ߸� �������� ���ް��� �Ű������� ����
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//@RequestParam : ���ް��� �Ű������� �����ϱ� ���� ������̼�
	// => �Ű������� �̸��� ���� �̸��� ���ް��� ���� ��� 400 ���� �߻�
	// => �Ű������� �ݵ�� ���ް��� ����ǵ��� �����ϱ� ���� ����ϴ� ������̼�
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	/*
	//���ް��� �̸��� �Ű������� �̸��� �ٸ� ��� @RequestParam ������̼��� value �Ӽ��� 
	//����ϸ� ���ް��� �����޾� �Ű������� ���� ����
	//value �Ӽ� : ���ް��� �̸��� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	//required �Ӽ� : false �Ǵ� true �� �ϳ��� �Ӽ������� ����
	// => false : ���ް� ���� ���ʼ�, true : ���ް� ���� �ʼ� - �⺻
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam("username") String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
	*/
	
	//defaultValue �Ӽ� : ���ް��� �̸��� �ٸ��ų� ���ް��� ���� ��� �Ű������� ����Ǵ� �⺻���� 
	//�Ӽ������� ����
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String form(@RequestParam(value = "username", defaultValue = "�Ӳ���") String name, Model model) {
		model.addAttribute("name", name);
		return "param_display";
	}
}





