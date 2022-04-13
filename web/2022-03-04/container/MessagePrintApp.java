package xyz.itwill03.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Spring Container로 객체를 생성하여 제공받아 사용
// => 개발자가 아닌 String Container가 객체 관리 - Spring IoC
// => Spring Bean Configuration File(XML)를 이용하여 객체의 생명주기 관리
public class MessagePrintApp {
	public static void main(String[] args) {
		/*
		HelloMessageObject object=new HelloMessageObject();
		MessagePrint print=new MessagePrint();
		print.setObject(object);//포함관계(의존관계) 성립
		print.messagePrint();
		*/
		
		//ApplicationContext 객체(Spring Container) 생성
		// => Spring Bean Configuration File(XML)을 제공받아 Spring Bean을 생성하여 관리
		ApplicationContext context=new ClassPathXmlApplicationContext("03_message.xml");
		
		//Spring Container에게 Spring Bean(객체)를 제공받아 저장
		// => Spring Bean를 구분하기 위한 식별자(beanName)를 전달하여 원하는 Spring Bean 반환
		MessagePrint print=(MessagePrint)context.getBean("messagePrint");
		print.messagePrint();
		
		//Spring Container 제거 : Spring Container가 관리하는 모든 Spring Bean 제거
		((ClassPathXmlApplicationContext)context).close();
	}
}










