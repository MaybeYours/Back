package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.itwill10.dto.Member;

//REST(RePresentation State Transfer) ����� �����ϴ� ��Ʈ�ѷ�
//REST : Ŭ���̾�Ʈ ��û�� ���� ��û ó�� �޼ҵ忡�� ��û ó�� �� ó�� ����� ��������(JSP)��
//�ƴ� ���� ���ҽ�(TEXT, XML, JSON ��)�� �����ϴ� ���
// => �پ��� ��⿡�� ���������� ó�� ������ �ؽ�Ʈ ����Ÿ�� �����ϴ� ���� �������� ���� �����α׷�
// => ����Ʈ ������ AJAX ������� REST �����α׷��� ��û�ϰ� ó������� JSON���� ����޾� DHTML�� �������� ����

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
	
	//@ResponseBody : ��û ó�� �޼ҵ��� ��ȯ��(���ڿ�)�� Ŭ���̾�Ʈ���� ���� �ؽ�Ʈ ����Ÿ�� ����ǵ��� �����ϴ� ������̼�
	// => ���ڿ�(String)�θ� ���� ���� - Java ��ü�� ���� �Ұ���
	//@RequestBody : POST, PUT, PATCH, DELETE  ���� ��û�� ���� ���޵� ��� ���� �ϳ��� ���ڿ��� �����Ͽ� �����ϱ� ���� ������̼�
	// => GET ������� ��û�Ͽ� ���޵� ���� �Ű������� ���� �Ұ���
	// => ���ް��� �ڹٽ�ũ��Ʈ�� Object ��ü �������� ����(�̸�=��&�̸�=��&... >> {"�̸�":��,"�̸�":��,...})
	// => AJAX ��û�� ���� ���޵� ���� �����ϱ� ���� ���
	@RequestMapping(value = "/rest", method = RequestMethod.POST)
	@ResponseBody
	public String rest(@RequestBody String inputData) {
		return inputData;
	}
	
	//ȸ�������� �ؽ�Ʈ ����Ÿ�� �����ϴ� ��û ó�� �޼ҵ�
	// => ���ڿ�(String)�� �ƴ� Java ��ü�� �ڷ������� ������ ��� 406 ���� �߻�
	//Java ��ü�� JSON ������ �ؽ�Ʈ ����Ÿ�� �ڵ� ��ȯ�Ǿ� ���ڿ��� ����ǵ��� ����
	// => jackson-databind ���̺귯���� ������Ʈ�� ���� ó�� - pom.xml
	@RequestMapping("/rest_member")
	@ResponseBody
	public Member restMember() {
		Member member=new Member();
		member.setId("abc123");
		member.setPasswd("123456");
		member.setName("ȫ�浿");
		member.setEmail("abc@itwill.xyz");
		
		return member;
	}
}







