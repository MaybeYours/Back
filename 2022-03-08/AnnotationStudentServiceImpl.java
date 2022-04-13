package xyz.itwill05.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Component("studentService")

//@Service : Service Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
//=> Ŭ�������� beanName���� �ڵ� �����ϸ� value �Ӽ��� ����Ͽ� beanName ���� ����
@Service("studentService")
public class AnnotationStudentServiceImpl implements StudentService {
	//@Autowired : �ʵ忡 Spring Bean ��ü�� �����Ͽ� �������踦 �ڵ� �����ϴ� ������̼�
	// => bean ������Ʈ�� autowire �Ӽ��� [byType] �Ӽ����� ������ ������� �������� ���� - Setter Injection
	// => �ʵ��� Setter �޼ҵ带 �������� �ʾƵ� Setter Injection ��� ����
	//������)�ʵ��� �ڷ����� �������̽��� ��� �ڽ�Ŭ������ Spring Bean ��ü�� ������ ������
	//�������� ������ UnsatisfiedDependencyException �߻�
	//�ذ��-1)Spring Bean���� ��ϵ� �ڽ�Ŭ���� �� �ϳ��� beanName�� �ʵ��� ���� �̸����� ����
	// => �ڽ�Ŭ������ Spring Bean ��ü�� ������ �ִ� ��� autowire �Ӽ��� [byName]�� ���� ������� �������� ����
	//�ذ��-2)@Qualifier ������̼��� ����Ͽ� �������踦 ���� ����
	//@Qualifier : �������踦 �����ϱ� ���� Spring Bean ��ü�� �����ϱ� ���� ������̼�
	// => @Autowired ������̼ǿ� ���ӵ� ������̼�
	//value �Ӽ� : �������踦 �����ϱ� ���� Spring Bean ��ü�� beanName�� �Ӽ������� ����
	// => �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
	@Autowired
	@Qualifier("annotationStudentMybatisDAO")
	//@Resource : Spring Framework�� @Autowired ������̼��� �������� ������� ������̼�
	// => JDK ���̺귯���� ���� �����Ǵ� ������̼� - �ٸ� Framework������ ��� ����
	// => �⺻������ autowire �Ӽ��� [byName]�� ���� ������� �������� ����
	//@Inject : Spring Framework�� @Autowired ������̼��� �������� ������� ������̼�
	// => JDK ���̺귯���� ���� �����Ǵ� ������̼� - �ٸ� Framework������ ��� ����
	// => �⺻������ autowire �Ӽ��� [byType]�� ���� ������� �������� ����
	private StudentDAO studentDAO;
	
	public AnnotationStudentServiceImpl() {
		System.out.println("### AnnotationStudentServiceImpl Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	@Override
	public void addStudent(Student student) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ addStudent(Student student) �޼ҵ� ȣ�� ***");
		studentDAO.insertStudent(student);
	}

	@Override
	public void modifyStudent(Student student) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ modifyStudent(Student student) �޼ҵ� ȣ�� ***");
		studentDAO.updateStudent(student);
	}

	@Override
	public void removeStudent(int num) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ removeStudent(int num) �޼ҵ� ȣ�� ***");
		studentDAO.deleteStudent(num);
	}

	@Override
	public Student getStudent(int num) {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ getStudent(int num) �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudent(num);
	}

	@Override
	public List<Student> getStudentList() {
		System.out.println("*** AnnotationStudentServiceImpl Ŭ������ getStudentList() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudentList();
	}
}
