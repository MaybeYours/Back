package xyz.itwill10.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MethodController {
	//�Է��������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => ��û ��Ŀ� ������� ��û�ص� ���۵Ǵ� ��û ó�� �޼ҵ� 
	@RequestMapping("/method_input")
	public String inputOne() {
		return "method_input1";
	}

	//���ް��� ��ȯ�޾� ������������� �����ϰ� ����������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => ��û ��Ŀ� ������� ��û�ص� ���۵Ǵ� ��û ó�� �޼ҵ� 
	@RequestMapping("/method_output")
	public String outputOne(HttpServletRequest request) throws UnsupportedEncodingException {
		if(request.getMethod().equals("GET")) {//GET ������� ��û�� ��� - ���������� ��û
			return "method_input1";//�Է��������� ViewName ��ȯ
		}
		
		//HttpServletRequest.setCharacterEncoding(String encoding) : POST ������� ��û�Ͽ�
		//���޵� ���� ���� ĳ���ͼ��� �����ϴ� �޼ҵ� - UnsupportedEncodingException �߻� : ���� ó�� 
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output1";
	}

	//�Է��������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => GET ������θ� ��û�Ͽ� ���۵Ǵ� ��û ó�� �޼ҵ�
	//method �Ӽ� : ��û ó�� �޼ҵ带 ȣ���ϱ� ���� Ŭ���̾�Ʈ ��û����� �Ӽ������� ����
	// => RequestMethod �ڷ���(Enum)�� ����� �Ӽ������� ����
	//��û��Ŀ� ���� @RequestMapping ������̼� ��� @GetMapping, @PostMapping ���� ������̼���
	//����Ͽ� ��û ó�� �޼ҵ� ���� ����
	@RequestMapping(value = "/method", method = RequestMethod.GET)
	public String inputTwo() {
		return "method_input2";
	}
	
	//���ް��� ��ȯ�޾� ������������� �����ϰ� ����������� ���� ViewName�� ��ȯ�ϴ� ��û ó�� �޼ҵ�
	// => POST ������θ� ��û�Ͽ� ���۵Ǵ� ��û ó�� �޼ҵ�
	//@RequestMapping ������̼��� ��û URL �ּҰ� ���� ��� Ŭ���̾�Ʈ�� ��û������� �����Ͽ�
	//��û ó�� �޼ҵ� ȣ�� ����
	@RequestMapping(value = "/method", method = RequestMethod.POST)
	public String outputTwo(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		return "method_output2";
	}	
}




