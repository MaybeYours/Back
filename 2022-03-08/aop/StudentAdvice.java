package xyz.itwill07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Advice Ŭ���� : Ⱦ�ܰ��ɸ���� ����� Ŭ����
//Ⱦ�ܰ��ɸ��(CrossCutting  Concern Module) : Ⱦ�ܰ����ڵ尡 �ۼ��� �޼ҵ�
//Ⱦ�ܰ����ڵ� : �ٽɰ����ڵ� ���� �� �Ǵ� �Ŀ� ���۵� �������� ���
// => �α� ó��, ����(����) ó��, Ʈ������ ó��, ���� ó�� ���� ���
public class StudentAdvice {
	private final static Logger logger=LoggerFactory.getLogger(StudentAdvice.class);
	
	//Ÿ�ٸ޼ҵ�(�ٽɰ��ɸ��)�� ��� ���� ���� ���ԵǾ� ����� ����� �ۼ��� �޼ҵ�(Ⱦ�ܰ��ɸ��)
	// => JoinPoint : Before Advice
	//JoinPoint : �ٽɰ��ɸ���� �������� Ⱦ�ܰ��ɸ���� ���Ե� ��ġ ǥ��
	public void beforeLog() {
		logger.info("[before]�ٽɰ����ڵ� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}

	//Ÿ�ٸ޼ҵ��� ��� ���� �Ŀ� ���ԵǾ� ����� ����� �ۼ��� �޼ҵ�
	// => Ÿ�ٸ޼ҵ忡�� �߻��� ���ܿ� ������� ������ ���ԵǾ� ����
	// => JoinPoint : After Advice
	public void afterLog() {
		logger.info("[after]�ٽɰ����ڵ� ���� �� ������ ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//Ÿ�ٸ޼ҵ��� ��� ���� �Ŀ� ���ԵǾ� ����� ����� �ۼ��� �޼ҵ�
	// => Ÿ�ٸ޼ҵ忡�� ���ܰ� �߻����� �ʰ� ���������� ���۵� ��� ���ԵǾ� ����
	// => JoinPoint : After Returning Advice
	public void afterReturningLog() {
		logger.info("[after-returning]�ٽɰ����ڵ��� �������� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//Ÿ�ٸ޼ҵ��� ��� ���� �Ŀ� ���ԵǾ� ����� ����� �ۼ��� �޼ҵ�
	// => Ÿ�ٸ޼ҵ忡�� ���ܰ� �߻��� ��� ���ԵǾ� ����
	// => JoinPoint : After Throwing Advice
	public void afterThrowingLog() {
		logger.info("[after-throwing]�ٽɰ����ڵ忡�� ���ܰ� �߻��� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
	}
	
	//Ÿ�ٸ޼ҵ��� ��� ���� ���Ŀ� ���ԵǾ� ����� ����� �ۼ��� �޼ҵ�
	// => JoinPoint : Around Advice
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("[around]�ٽɰ����ڵ� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
		Object object=joinPoint.proceed();//Ÿ�ٸ޼ҵ� ȣ�� - �ٽɰ����ڵ� ����
		logger.info("[around]�ٽɰ����ڵ� ���� �� ���ԵǾ� ����� Ⱦ�ܰ����ڵ�");
		return object;
	}
	
}









