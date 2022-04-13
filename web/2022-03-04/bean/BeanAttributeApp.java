package xyz.itwill04.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAttributeApp {
	public static void main(String[] args) {
		System.out.println("========== Spring Container 초기화 전 ==========");
		ApplicationContext context=new ClassPathXmlApplicationContext("04-2_beanAttribute.xml");
		System.out.println("========== Spring Container 초기화 후 ==========");
		//ApplicationContext.getBean(String beanName) : Spring Bean를 구분하는 식별자(beanName)를
		//전달받아 Spring Container로부터 Spring Bean를 반환하는 메소드
		// => Object 타입으로 Spring Bean를 반환하므로 명시적 객체 형변환하여 사용
		//InitDestroyMethodBean bean=(InitDestroyMethodBean)context.getBean("initDestroyMethodBean");

		//ApplicationContext.getBean(String beanName,Class<T> clazz) : Spring Bean를 구분하는  
		//식별자(beanName)와 메모리의 저장된 클래스(Clazz)를 전달받아 Spring Container로부터
		//Spring Bean를 객체 형변화하여 반환하는 메소드
		InitDestroyMethodBean bean=context.getBean("initDestroyMethodBean",InitDestroyMethodBean.class);
		
		//bean.init();
		bean.display();
		//bean.destroy();
		System.out.println("===============================================");
		context.getBean("lazyInitBean", LazyInitBean.class);
		System.out.println("===============================================");
		ScopeBean bean1=context.getBean("singletonBean", ScopeBean.class);
		ScopeBean bean2=context.getBean("singletonBean", ScopeBean.class);
		ScopeBean bean3=context.getBean("singletonBean", ScopeBean.class);
		
		System.out.println("bean1 = "+bean1);
		System.out.println("bean2 = "+bean2);
		System.out.println("bean3 = "+bean3);
		System.out.println("===============================================");
		ScopeBean bean4=context.getBean("prototypeBean", ScopeBean.class);
		ScopeBean bean5=context.getBean("prototypeBean", ScopeBean.class);
		ScopeBean bean6=context.getBean("prototypeBean", ScopeBean.class);
		
		System.out.println("bean4 = "+bean4);
		System.out.println("bean5 = "+bean5);
		System.out.println("bean6 = "+bean6);
		System.out.println("===============================================");
		((ClassPathXmlApplicationContext)context).close();
		System.out.println("===============================================");

	}
}
