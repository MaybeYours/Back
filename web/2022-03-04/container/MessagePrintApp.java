package xyz.itwill03.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Spring Container�� ��ü�� �����Ͽ� �����޾� ���
// => �����ڰ� �ƴ� String Container�� ��ü ���� - Spring IoC
// => Spring Bean Configuration File(XML)�� �̿��Ͽ� ��ü�� �����ֱ� ����
public class MessagePrintApp {
	public static void main(String[] args) {
		/*
		HelloMessageObject object=new HelloMessageObject();
		MessagePrint print=new MessagePrint();
		print.setObject(object);//���԰���(��������) ����
		print.messagePrint();
		*/
		
		//ApplicationContext ��ü(Spring Container) ����
		// => Spring Bean Configuration File(XML)�� �����޾� Spring Bean�� �����Ͽ� ����
		ApplicationContext context=new ClassPathXmlApplicationContext("03_message.xml");
		
		//Spring Container���� Spring Bean(��ü)�� �����޾� ����
		// => Spring Bean�� �����ϱ� ���� �ĺ���(beanName)�� �����Ͽ� ���ϴ� Spring Bean ��ȯ
		MessagePrint print=(MessagePrint)context.getBean("messagePrint");
		print.messagePrint();
		
		//Spring Container ���� : Spring Container�� �����ϴ� ��� Spring Bean ����
		((ClassPathXmlApplicationContext)context).close();
	}
}










