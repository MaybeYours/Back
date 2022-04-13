package xyz.itwill07.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//Ⱦ�ܰ��ɸ���� ����� Ŭ����
public class JoinPointAdvice {
	//Around Advice �޼ҵ带 ������ ������ Advice �޼ҵ�� ��ȯ���� void�� �ۼ��ϸ� 
	//�Ű������� �ۼ����� �ʰų� JoinPoint �������̽��� �ڷ������� �ϴ� �Ű����� ���� ����
	// => Ⱦ�ܰ��ɸ���� �Ű������� ������������ ������ ��� IllegalArgumentException �߻�  
	//JoinPoint : Ÿ�ٸ޼ҵ�(�ٽɰ��ɸ��)�� ������ �����ϱ� ���� ��ü
	// => Spring Container�� Advice �޼ҵ带 ȣ���� �� Ÿ�ٸ޼ҵ��� ����(JoinPoint ��ü)�� �Ű������� �ڵ� ����
	// => Advice �޼ҵ�(Ⱦ�ܰ��ɸ��)���� Ÿ�ٸ޼ҵ��� ������ �ʿ��� ��� JoinPoint �Ű����� �ۼ�
	public void displayTarget(JoinPoint joinPoint) {//Before Advice
		//System.out.println("[before]�ٽɰ��ɸ�� ���� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		//JoinPoint.getTarget() : Ÿ�ٸ޼ҵ带 ȣ���ϴ� Spring Bean ��ü�� ��ȯ�ϴ� �޼ҵ�
		// => Ÿ�ٸ޼ҵ尡 ����� Ŭ������ ��ü�� Object Ÿ������ ��ȯ
		//Object.getClass() : ��ü�� ���� Ŭ���� ����(Clazz)�� ��ȯ�ϴ� �޼ҵ�
		//Class.getName() : Class ��ü(Clazz)�� Ŭ������(��Ű�� ����)�� ��ȯ�ϴ� �޼ҵ�
		//System.out.println(joinPoint.getTarget().getClass().getName());
		
		//Class.getSimpleName() : Class ��ü(Clazz)�� Ŭ������(��Ű�� ������)�� ��ȯ�ϴ� �޼ҵ�
		//System.out.println(joinPoint.getTarget().getClass().getSimpleName());
		
		
		//JoinPoint.getSignature() : Signature ��ü(Ÿ�ٸ޼ҵ� ����)�� ��ȯ�ϴ� �޼ҵ�
		//Signature.getName() : Ÿ�ٸ޼ҵ��� �̸��� ��ȯ�ϴ� �޼ҵ�
		//System.out.println(joinPoint.getSignature().getName());
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		//System.out.println("[before]"+className+" Ŭ������ "+methodName+"() �޼ҵ� ȣ��");
		
		//JoinPoint.getArgs() : Ÿ�ٸ޼ҵ��� �Ű������� ����� ��� ��(��ü)�� Object �迭�� ��ȯ�ϴ� �޼ҵ� 
		Object[] objects=joinPoint.getArgs();
		
		/*
		if(methodName.equals("remove")) {
			int num=(Integer)objects[0];
			System.out.println("[���]"+methodName+"(int num) �޼ҵ��� �Ű������� ���޵� �� = "+num);
		}
		*/
		
		System.out.println("[before]"+className+" Ŭ������ "+methodName
				+"() �޼ҵ忡 ����� �Ű������� ���� = "+objects.length);
	}
	
	//After Returning Advice �޼ҵ忡�� JoinPoint �Ű������ܿ� Object �Ű����� �ۼ� ����
	// => Object �Ű��������� Ÿ�ٸ޼ҵ��� ��ȯ���� ���޹޾� ����
	// => Ÿ�ٸ޼ҵ� ��ȯ���� �ڷ����� �����Ǿ� �ִ� ��� Object Ÿ�� ��� ��ȯ���� �ڷ������� �Ű����� �ۼ�
	//Bean Configuration File�� AOP �������� after-returning ������Ʈ�� returning �Ӽ��� �Ű���������
	//�Ӽ������� �ݵ�� �����ؾ߸� Ÿ�ٸ޼ҵ��� ��ȯ���� �Ű������� ���޹޾� ���� ���� 
	public void displayName(Object object) {//After Returning Advice
		//System.out.println("[after-returning]�ٽɰ��ɸ���� ���������� ���۵� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		if(object instanceof String) {//�Ű������� String Ŭ������ ����ȯ�� �� �ִ� ���
			String name=(String)object;
			System.out.println("[after-returning]"+name+"��, ȯ���մϴ�.");
		}
	}
	
	//After Throwing Advice �޼ҵ忡�� JoinPoint �Ű������ܿ� Exception �Ű����� �ۼ� ����
	// => Exception �Ű��������� Ÿ�ٸ޼ҵ忡�� �߻��� ����(Exception ��ü)�� ���޹޾� ����
	//Bean Configuration File�� AOP �������� after-throwing ������Ʈ�� throwing �Ӽ��� �Ű���������
	//�Ӽ������� �ݵ�� �����ؾ߸� Ÿ�ٸ޼ҵ��� ���ܸ� �Ű������� ���޹޾� ���� ���� 
	public void displayException(Exception exception) {//After Throwing Advice
		//System.out.println("[after-throwing]�ٽɰ��ɸ�� ���۽� ���ܰ� �߻��� �� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		
		System.out.println("[after-throwing]Ÿ�ٸ޼ҵ忡�� �߻��� ���� �޼��� = "+exception.getMessage());
	}
	
	//Around Advice �޼ҵ�� ��ȯ���� void �Ǵ� Object Ÿ������ �ۼ��ϰ� ProceedingJoinPoint 
	//�������̽��� �ڷ������� �ۼ��� �Ű������� �ݵ�� ����
	// => Around Advice �޼ҵ�� Ÿ�ٸ޼ҵ��� ��ȯ���� �����޾� ��ȯ�ϱ� ���� Object Ÿ������ ����
	//ProceedingJoinPoint : Ÿ�ٸ޼ҵ�(�ٽɰ��ɸ��)�� ������ �����ϱ� ���� ��ü
	// => JoinPoint ��ü�� �ٸ����� Ÿ�ٸ޼ҵ带 ȣ���� �� �ִ� ��� ���� 
	public Object display(ProceedingJoinPoint joinPoint) throws Throwable {//Around Advice
		System.out.println("[around]�ٽɰ��ɸ�� ���� ���� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		//ProceedingJoinPoint.proceed() : Ÿ�ٸ޼ҵ带 ȣ���ϴ� �޼ҵ� - �ٽɰ��ɸ�� ����
		// => Ÿ�ٸ޼ҵ� ȣ��� ��ȯ�Ǵ� ������� �����޾� ���� ����
		// => ���α׷� ����� �߻��Ǵ� ��� ���� ������ ������ ��ü(Throwable) �߻� ���� - ���� ó��
		Object object=joinPoint.proceed();
		
		System.out.println("[around]�ٽɰ��ɸ�� ���� �Ŀ� ���ԵǾ� ����� Ⱦ�ܰ��ɸ��");
		return object;
	}
}










