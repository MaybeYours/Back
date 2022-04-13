package xyz.itwill04.bean;

import org.springframework.stereotype.Component;

//@Component : Ŭ������  Spring Bean���� ����ϱ� ���� ������̼�
// => �⺻������ Ŭ�������� beanName���� ���� - ù���ڴ� �ҹ��ڷ� ��ȯ
//@Component ������̼��� value �Ӽ��� ����Ͽ� beanName ���� ����
// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� 
@Component(value = "bean")
public class ComponentAnnotationBean {
	public ComponentAnnotationBean() {
		System.out.println("### ComponentAnnotationBean Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	public void display() {
		System.out.println("*** ComponentAnnotationBean Ŭ������ display() �޼ҵ� ȣ�� ***");
	}
}
