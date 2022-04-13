package xyz.itwill07.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//횡단관심모듈이 선언된 클래스
public class JoinPointAdvice {
	//Around Advice 메소드를 제외한 나머지 Advice 메소드는 반환형을 void로 작성하며 
	//매개변수를 작성하지 않거나 JoinPoint 인터페이스를 자료형으로 하는 매개변수 선언 가능
	// => 횡단관심모듈의 매개변수를 비정상적으로 선언한 경우 IllegalArgumentException 발생  
	//JoinPoint : 타겟메소드(핵심관심모듈)의 정보를 저장하기 위한 객체
	// => Spring Container가 Advice 메소드를 호출할 때 타겟메소드의 정보(JoinPoint 객체)를 매개변수에 자동 저장
	// => Advice 메소드(횡단관심모듈)에서 타겟메소드의 정보가 필요한 경우 JoinPoint 매개변수 작성
	public void displayTarget(JoinPoint joinPoint) {//Before Advice
		//System.out.println("[before]핵심관심모듈 동작 전 삽입되어 실행될 횡단관심모듈");
		
		//JoinPoint.getTarget() : 타겟메소드를 호출하는 Spring Bean 객체를 반환하는 메소드
		// => 타겟메소드가 선언된 클래스의 객체를 Object 타입으로 반환
		//Object.getClass() : 객체에 대한 클래스 정보(Clazz)를 반환하는 메소드
		//Class.getName() : Class 객체(Clazz)의 클래스명(패키지 포함)을 반환하는 메소드
		//System.out.println(joinPoint.getTarget().getClass().getName());
		
		//Class.getSimpleName() : Class 객체(Clazz)의 클래스명(패키지 미포함)을 반환하는 메소드
		//System.out.println(joinPoint.getTarget().getClass().getSimpleName());
		
		
		//JoinPoint.getSignature() : Signature 객체(타겟메소드 정보)를 반환하는 메소드
		//Signature.getName() : 타겟메소드의 이름을 반환하는 메소드
		//System.out.println(joinPoint.getSignature().getName());
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		//System.out.println("[before]"+className+" 클래스의 "+methodName+"() 메소드 호출");
		
		//JoinPoint.getArgs() : 타겟메소드의 매개변수에 저장된 모든 값(객체)을 Object 배열로 반환하는 메소드 
		Object[] objects=joinPoint.getArgs();
		
		/*
		if(methodName.equals("remove")) {
			int num=(Integer)objects[0];
			System.out.println("[결과]"+methodName+"(int num) 메소드의 매개변수에 전달된 값 = "+num);
		}
		*/
		
		System.out.println("[before]"+className+" 클래스의 "+methodName
				+"() 메소드에 선언된 매개변수의 갯수 = "+objects.length);
	}
	
	//After Returning Advice 메소드에는 JoinPoint 매개변수외에 Object 매개변수 작성 가능
	// => Object 매개변수에는 타겟메소드의 반환값을 전달받아 저장
	// => 타겟메소드 반환값의 자료형이 고정되어 있는 경우 Object 타입 대신 반환값의 자료형으로 매개변수 작성
	//Bean Configuration File의 AOP 설정에서 after-returning 엘리먼트의 returning 속성에 매개변수명을
	//속성값으로 반드시 설정해야만 타겟메소드의 반환값을 매개변수로 전달받아 저장 가능 
	public void displayName(Object object) {//After Returning Advice
		//System.out.println("[after-returning]핵심관심모듈이 정상적으로 동작된 후 삽입되어 실행될 횡단관심모듈");
		
		if(object instanceof String) {//매개변수가 String 클래스로 형변환될 수 있는 경우
			String name=(String)object;
			System.out.println("[after-returning]"+name+"님, 환영합니다.");
		}
	}
	
	//After Throwing Advice 메소드에는 JoinPoint 매개변수외에 Exception 매개변수 작성 가능
	// => Exception 매개변수에는 타겟메소드에서 발생된 예외(Exception 객체)를 전달받아 저장
	//Bean Configuration File의 AOP 설정에서 after-throwing 엘리먼트의 throwing 속성에 매개변수명을
	//속성값으로 반드시 설정해야만 타겟메소드의 예외를 매개변수로 전달받아 저장 가능 
	public void displayException(Exception exception) {//After Throwing Advice
		//System.out.println("[after-throwing]핵심관심모듈 동작시 예외가 발생된 후 삽입되어 실행될 횡단관심모듈");
		
		System.out.println("[after-throwing]타겟메소드에서 발생된 예외 메세지 = "+exception.getMessage());
	}
	
	//Around Advice 메소드는 반환형을 void 또는 Object 타입으로 작성하고 ProceedingJoinPoint 
	//인터페이스를 자료형으로 작성된 매개변수를 반드시 선언
	// => Around Advice 메소드는 타겟메소드의 반환값을 제공받아 반환하기 위해 Object 타입으로 선언
	//ProceedingJoinPoint : 타겟메소드(핵심관심모듈)의 정보를 저장하기 위한 객체
	// => JoinPoint 객체와 다른점은 타겟메소드를 호출할 수 있는 기능 제공 
	public Object display(ProceedingJoinPoint joinPoint) throws Throwable {//Around Advice
		System.out.println("[around]핵심관심모듈 동작 전에 삽입되어 실행될 횡단관심모듈");
		//ProceedingJoinPoint.proceed() : 타겟메소드를 호출하는 메소드 - 핵심관심모듈 동작
		// => 타겟메소드 호출시 반환되는 결과값을 제공받아 저장 가능
		// => 프로그램 실행시 발생되는 모든 오류 정보를 저장한 객체(Throwable) 발생 가능 - 예외 처리
		Object object=joinPoint.proceed();
		
		System.out.println("[around]핵심관심모듈 동작 후에 삽입되어 실행될 횡단관심모듈");
		return object;
	}
}










