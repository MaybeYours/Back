package xyz.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
//@Aspect : Spring Bean���� ��ϵ� Ŭ������ �޼ҵ忡 Aspect ����� �����ϱ� ���� ������̼�
// => Bean Configuration File�� aspect ������Ʈ�� ������ ����� ����
@Aspect
public class AopAnnotationAdvice {
	private static final Logger logger=LoggerFactory.getLogger(AopAnnotationAdvice.class);
	
	//@Pointcut : �ٽɰ��ɸ���� Ÿ�ٸ޼ҵ�� �����ϱ� ���� ������̼�
	// => Bean Configuration File�� pointcut ������Ʈ�� ������ ����� ����
	// => Ÿ�ٸ޼ҵ带 �����ϱ� ���� Pointcut�� ���� ������ �������� ����
	// => �޼ҵ��� �Ű������� Pointcut �ĺ��ڷ� ���Ǹ� �޼ҵ� ȣ�� �������� Pointcut ���
	//value �Ӽ� : Ÿ�ٸ޼ҵ带 �����ϱ� ���� ����(execution �Ǵ� within)�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	@Pointcut("within(xyz.itwill07.aop.AopAnnotationBean)")
	public void aopPointcut() {}
	
	//@Before : Before Advice �޼ҵ带 �����ϱ� ���� ������̼�
	// => Bean Configuration File�� before ������Ʈ�� ������ ����� ����
	//value �Ӽ� : Ÿ�ٸ޼ҵ带 �����ϱ� ���� ����(execution �Ǵ� within)�� �Ӽ������� ���� - Pointcut
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	//@Before("within(xyz.itwill07.aop.AopAnnotationBean)")
	//value �Ӽ������� Pointcut �޼ҵ带 ȣ���Ͽ� Ÿ�ٸ޼ҵ� ���� ����
	@Before("aopPointcut()")
	public void beforeLog() {
		logger.info("[before]�ٽɰ��ɸ�� ���� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
	}

	//@AfterReturning : After Returning Advice �޼ҵ带 �����ϱ� ���� ������̼�
	// => Bean Configuration File�� after-returning ������Ʈ�� ������ ����� ����
	//returning �Ӽ� : Ÿ�ٸ޼ҵ��� ��ȯ���� �����ϱ� ���� �Ű������� �̸��� �Ӽ������� ����
	@AfterReturning(value = "aopPointcut()", returning = "object")
	public void afterReturningLog(Object object) {
		logger.info("[after-returning]�ٽɰ��ɸ���� ���������� ���۵� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
	}
	
	//@AfterThrowing : After Throwing Advice �޼ҵ带 �����ϱ� ���� ������̼�
	// => Bean Configuration File�� after-throwing ������Ʈ�� ������ ����� ����
	//throwing �Ӽ� : Ÿ�ٸ޼ҵ忡�� �߻��� ������ �����ϱ� ���� �Ű������� �̸��� �Ӽ������� ����
	@AfterThrowing(value = "aopPointcut()", throwing = "exception")
	public void afterThrowingLog(Exception exception) {
		logger.info("[after-throwing]�ٽɰ��ɸ�⿡�� ���ܰ� �߻��� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
	}

	//@After : After Advice �޼ҵ带 �����ϱ� ���� ������̼�
	// => Bean Configuration File�� after ������Ʈ�� ������ ����� ����
	@After("aopPointcut()")
	public void afterLog() {
		logger.info("[after]�ٽɰ��ɸ�� ���� �� ������ ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
	}
	
	//@Around : Around Advice �޼ҵ带 �����ϱ� ���� ������̼�
	// => Bean Configuration File�� around ������Ʈ�� ������ ����� ����
	@Around("aopPointcut()")
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[around]�ٽɰ��ɸ�� ���� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		Object object=joinPoint.proceed();
		logger.info("[around]�ٽɰ��ɸ�� ���� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		return object;
	}
}














