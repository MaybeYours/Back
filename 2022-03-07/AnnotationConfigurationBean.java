package xyz.itwill04.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration : Spring Container�� �����ϱ� ���� Ŭ������ Spring Bean���� ����ϱ� �����
//�����ϴ� ȯ�漳�� Ŭ������ �����ϱ� ���� ������̼�
// => Bean Configuration File�� beans ������Ʈ�� ������ ����� �����ϴ� ������̼�
@Configuration
public class AnnotationConfigurationBean {
	//@Bean : Spring Container�� �����ϱ� ���� Ŭ������ Spring Bean���� ����ϴ� ������̼�
	// => Spring Bean���� ����ϱ� ���� Ŭ������ ��ü�� �����Ͽ� ��ȯ�ϴ� �޼ҵ带 ������̼� ����
	// => Bean Configuration File�� bean ������Ʈ�� ������ ����� �����ϴ� ������̼�
	// => �⺻������ �޼ҵ���� beanName���� ���
	// => @Bean ������̼��� name �Ӽ��� ����Ͽ� beanName ���� ����
	@Bean
	public AnnotationBean annotationBean() {
		return new AnnotationBean();
	}
}
