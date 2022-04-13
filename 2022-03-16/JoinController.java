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
	//���ް��� �̸��� ���� �̸��� �Ű������� �ۼ��Ͽ� ���ް� ����
	//@RequestParam ������̼��� ����Ͽ� �Ű������� �̸��� ���� �̸��� ���ް��� ���� ��� 400 ���� �߻�
	// => value �Ӽ��� �̿��Ͽ� �Ű������� �̸��� �ٸ� �̸��� ���ް��� �����޾� �Ű������� ���� ����
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
	//@ModelAttribute : ��������(JSP)���� ����ϱ� ���� �Ӽ���(��ü)�� �����ϴ� ������̼�
	// => @ModelAttribute ������̼��� �޼ҵ�� �Ű������� ���� ����
	//@ModelAttribute ������̼��� �޼ҵ忡 �����ϸ� �޼ҵ� ��ȯ���� Controller Ŭ������
	//��� ��û ó�� �޼ҵ��� ������������ �Ӽ������� ����
	//@ModelAttribute ������̼��� �Ű������� �����ϸ� �Ű������� ����� ���� ��û ó�� �޼ҵ��� 
	//������������ �Ӽ������� ���� - �Ű������� ����� ���ް��� ������������ ����
	// => �Ű������� �̸��� ���ް��� �̸��� ���� �ʾƵ� 400 ���� �̹߻�
	// => �Ű������� �ڷ����� �⺻��(Wrapper Ŭ����)�̳� String Ŭ������ ��� �ݵ�� value �Ӽ��� ����
	//value �Ӽ� : ���������� �����ϱ� ���� �Ӽ����� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute(value="id") String id, @ModelAttribute("passwd") String passwd
			, @ModelAttribute("name") String name, @ModelAttribute("email") String email) {
		return "join_display";
	}
	*/
	
	/*
	//��û ó�� �޼ҵ��� �Ű����� �ڷ����� JavaBean Ŭ����(DTO Ŭ����)�� �����ϸ� Front Controller��
	//JavaBean ��ü�� �����Ͽ� ���ް��� �ʵ忡 �����Ͽ� ���� - Command ��ü
	//Command ��ü : ���ް��� �ʵ忡 ����� ��ü�� ��û ó�� �޼ҵ��� ������������ ���ǵ��� �����Ǵ� ��ü
	// => ���ް��� ���� �̸��� �ʵ忡 �ڵ� ����
	//������������ Command ��ü�� ���ǵ��� @ModelAttribute ������̼��� ����Ͽ� �����ϴ� ���� ����
	// => @ModelAttribute ������̼� �������� �ʾƵ� �ڵ� ó��
	//value �Ӽ����� ���������� �����Ǵ� �Ӽ����� ������ �� ������ value �Ӽ��� ������ ��� 
	//Ŭ�������� �Ӽ������� �ڵ� ���� - ù���ڴ� �ҹ��ڷ� ��ȯ�Ǿ� ����
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Member member) {
		return "join_display";
	}
	*/
	
	//@ModelAttribute ������̼��� value �Ӽ��� ����Ͽ� ���������� �����Ǵ� �Ӽ��� ���� ����
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("mem") Member member, Model model) {
		if(member.getId().equals("abc123")) {//���̵� �ߺ��� ���
			model.addAttribute("message", "�̹� ������� ���̵��Դϴ�.");
			return "join_form";
		}
		return "join_display";
	}
}








