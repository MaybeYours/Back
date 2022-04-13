package xyz.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Advice 클래스 : 횡단관심모듈이 선언된 클래스
//횡단관심모듈(CrossCutting  Concern Module) : 횡단관심코드가 작성된 메소드
//횡단관심코드 : 핵심관심코드 실행 전 또는 후에 동작될 보조적인 명령
// => 로그 처리, 보안(권한) 처리, 트렌젝션 처리, 예외 처리 등의 명령
public class StudentAdvice {
	private final static Logger logger=LoggerFactory.getLogger(StudentAdvice.class);
	
	//타겟메소드(핵심관심모듈)의 명령 실행 전에 삽입되어 실행될 명령을 작성한 메소드(횡단관심모듈)
	// => JoinPoint : Before Advice
	//JoinPoint : 핵심관심모듈을 기준으로 횡단관심모듈이 삽입될 위치 표현
	public void beforeLog() {
		logger.info("[before]핵심관심코드 동작 전 삽입되어 실행될 횡단관심코드");
	}

	//타겟메소드의 명령 실행 후에 삽입되어 실행될 명령을 작성한 메소드
	// => 타겟메소드에서 발생된 예외와 상관없이 무조건 삽입되어 실행
	// => JoinPoint : After Advice
	public void afterLog() {
		logger.info("[after]핵심관심코드 동작 후 무조건 삽입되어 실행될 횡단관심코드");
	}
	
	//타겟메소드의 명령 실행 후에 삽입되어 실행될 명령을 작성한 메소드
	// => 타겟메소드에서 예외가 발생되지 않고 정상적으로 동작된 경우 삽입되어 실행
	// => JoinPoint : After Returning Advice
	public void afterReturningLog() {
		logger.info("[after-returning]핵심관심코드의 정상적인 동작 후 삽입되어 실행될 횡단관심코드");
	}
	
	//타겟메소드의 명령 실행 후에 삽입되어 실행될 명령을 작성한 메소드
	// => 타겟메소드에서 예외가 발생된 경우 삽입되어 실행
	// => JoinPoint : After Throwing Advice
	public void afterThrowingLog() {
		logger.info("[after-throwing]핵심관심코드에서 예외가 발생된 후 삽입되어 실행될 횡단관심코드");
	}
	
	//타겟메소드의 명령 실행 전후에 삽입되어 실행될 명령을 작성한 메소드
	// => JoinPoint : Around Advice
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[around]핵심관심코드 동작 전 삽입되어 실행될 횡단관심코드");
		Object object=joinPoint.proceed();//타겟메소드 호출 - 핵심관심코드 실행
		logger.info("[around]핵심관심코드 동작 후 삽입되어 실행될 횡단관심코드");
		return object;
	}
	
}









