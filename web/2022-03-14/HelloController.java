package xyz.itwill10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller : Ŭ���̾�Ʈ�� ��û�� ó���ϴ� Controller Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
// => Ŭ�������� beanName���� ���(ù���ڴ� �ҹ���)�Ǹ� value �Ӽ��� �̿��Ͽ� beanName ���� ����
// => Controller �������̽��� ��ӹ��� �ʾƵ� Controller Ŭ������ �ۼ� ����
// => Ŭ���̾�Ʈ�� ��û�� ó���ϴ� �ټ��� ��û ó�� �޼ҵ� �ۼ� ���� - @RequestMapping ������̼� ���
@Controller
public class HelloController {
	public static final Logger logger=LoggerFactory.getLogger(HelloController.class);
	
	//��û ó�� �޼ҵ忡�� Front Controller(DispatcherServlet)���� ViewName�� �����ϴ� ���
	// => Front Controller�� �������� ViewName�� ViewResolver ��ü���� �����Ͽ� JSP ���ϰ�θ� 
	//��ȯ�޾� ������ �̵��Ͽ� ����ó��
	//1.��û ó�� �޼ҵ��� ��ȯ���� void �ڷ������� �ۼ��ϸ� �޼ҵ���� ViewName���� ����
	
	//Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� �޼ҵ�
	//@RequestMapping : Ŭ���̾�Ʈ�� ��û�� ���� ó�� �޼ҵ带 �����ϱ� ���� ������̼�
	// => @GetMapping, @PostMapping, @PutMapping, @DeleteMapping ���� ������̼� ��� ����
	//value �Ӽ� : Ŭ���̾�Ʈ�� ��û����(URL)�� �Ӽ������� ����
	// => Ŭ���̾�Ʈ ��û������ ���� Front Controller(DispatcherServlet)�� Controller Ŭ������ ��û ó�� �޼ҵ� ȣ��
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	// => �ٸ� ��û ó�� �޼ҵ忡 ���� �̸��� ��û������ ������ ��� WAS ����� ���� �߻�
	@RequestMapping(value = "/hello")
	public void hello() {
		//��û ó�� ��� �ۼ� - Service Ŭ������ �޼ҵ� ȣ��
		logger.info("/hello ��û >> HelloController Ŭ������ hello() �޼ҵ带 ȣ���Ͽ� ��û ó��");
	}
}





