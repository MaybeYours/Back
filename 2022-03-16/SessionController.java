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
//@SessionAttributes : Model ��ü�� �̿��Ͽ� ����Ǵ� �Ӽ����� ��û ó�� �޼ҵ��� �����������Ը�  
//�������� �ʰ� Controller Ŭ������ ��� ��û ó�� �޼ҵ�� ������������ �����ϴ� ������̼�
// => Model ��ü�� �̿��Ͽ� ����� �Ӽ����� Request Scope�� �ƴ� �������� Session Scope�� ó��
// => ���� ó���� �ٸ� ��û ó�� �޼ҵ忡�� �ʿ��� ������ �����ϰų� �˻��ϴ� �۾��� �����ϱ� ���� ���
//value �Ӽ� : �������� Session Scope�� ó���ϱ� ���� �Ӽ����� �Ӽ������� ����
// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
@SessionAttributes("hewon")
public class SessionController {
	//���̵� ���޹޾� ȸ�������� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ� - Service Ŭ������ �޼ҵ�
	private Hewon getHewon(String id) {
		Hewon hewon=new Hewon();
		hewon.setId("abc123");
		hewon.setPasswd("123456");
		hewon.setName("ȫ�浿");
		hewon.setEmail("abc@itwill.xyz");
		hewon.setGender("����");
		return hewon;
	}
	
	//���̵� ���޹޾� ȸ�������� �˻��Ͽ� ���������� �����ϴ� ��û ó�� �޼ҵ�
	// => �������� ȸ�������� Ŭ���̾�Ʈ���� ����ǵ��� ������������ ó��
	@RequestMapping("/hewon_view")
	public String hewonView(@RequestParam(defaultValue = "abc123") String id, Model model) {
		//ȸ�������� �˻��Ͽ� ��ȯ�޾� ���� - Service Ŭ������ �޼ҵ� ȣ��
		Hewon hewon=getHewon(id);
		
		//Model ��ü�� �̿��Ͽ� ������������ �˻��� ȸ������ ���� - Request Scope
		//model.addAttribute("hewon", hewon);
		
		//Model.addAttribute(Object attributeValue) : ������������ �Ӽ����� �����ϴ� �޼ҵ�
		// => �Ӽ����� ������ ��� �Ӽ���(��ü)�� Ŭ�������� �Ӽ������� �ڵ� ���� - ù���ڴ� �ҹ��ڷ� ��ȯ�Ͽ� ����
		// => �Ӽ����� �ڷ����� �⺻��(Wrapper)�̰ų� String Ŭ������ ��� ȣ�� �Ұ���
		//@SessionAttributes ������̼ǿ� ���� �������� Session Scope�� ó��
		// => Controller Ŭ������ ��� ��û ó�� �޼ҵ�� ������������ �Ӽ��� ��� ����
		model.addAttribute(hewon);
		
		return "session/hewon_view";
	}
	
	/*
	//���̵� ���޹޾� ȸ�������� �˻��Ͽ� ���������� �����ϴ� ��û ó�� �޼ҵ�
	// => �������� ȸ�������� �Է��±��� �ʱⰪ���� �����ǵ��� ������������ ó��
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String hewonUpdate(@RequestParam String id, Model model) {
		//ȸ�������� �˻��Ͽ� ��ȯ�޾� ���� - Service Ŭ������ �޼ҵ� ȣ��
		Hewon hewon=getHewon(id);

		//Model ��ü�� �̿��Ͽ� ������������ �˻��� ȸ������ ���� - Request Scope
		model.addAttribute(hewon);

		return "session/hewon_update";
	}
	*/
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String hewonUpdate() {
		return "session/hewon_update";
	}
	
	//ȸ�������� ���޹޾� ���� ó���ϴ� ��û ó�� �޼ҵ�
	//@SessionAttributes ������̼��� ����� ��� Command ��ü�� �Ӽ����� �ڵ� ����Ǿ� 
	//�����Ǹ� ���ް��� �ִ� ��� Command ��ü�� �ʵ尪 ���� ó�� 
	//SessionStatus : @SessionAttributes ������̼ǿ� ���� ������ �Ӽ���(��ü)�� ���������� ������ ��ü 
	@RequestMapping(value = "/hewon_update", method = RequestMethod.POST)
	public String hewonUpdate(@ModelAttribute Hewon hewon, SessionStatus status) {
		System.out.println("���� ���̵� = "+hewon.getId());

		//SessionStatus.setComplete() : @SessionAttributes ������̼ǿ� ���� �������� 
		//Session Scope�� ������ ��ü�� Request Scope�� ������ ��ü�� ó���ǵ��� �����ϴ� �޼ҵ� 
		status.setComplete();
		
		return "session/hewon_result";
	}
}






