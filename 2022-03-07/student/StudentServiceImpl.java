package xyz.itwill05.di;

import java.util.List;

//Service Ŭ���� : ���α׷� ���࿡ �ʿ��� ����� �����ϱ� ���� Ŭ���� - ���۳�Ʈ(���)
// => Service Ŭ������ �޼ҵ�� DAO Ŭ������ �޼ҵ带 ȣ���Ͽ� ���α׷� ���࿡ �ʿ��� ��� ����
// => Service Ŭ������ ����ŵ� ���α׷� �ۼ��� ���⸦ �ּ�ȭ �ϱ� ���� �������̽��� ��ӹ޾� �ۼ�
public class StudentServiceImpl implements StudentService {
	//StudnetJdbcDAO ��ü�� �ʵ忡 �����ؾ� ���԰���(��������) ����
	// => DAO Ŭ������ ����� ��� Service Ŭ������ �ʵ� �� �޼ҵ� ���� ���� - ���������� ȿ���� ���� 
	//private StudnetJdbcDAO studnetJdbcDAO;
	
	//StudentDAO �������̽��� ��ӹ��� �ڽ�Ŭ������ ��ü�� �ʵ忡 �����ؾ� ���԰��� ����
	// => �θ� �������̽��� ��������(�ʵ�)�� �����ϸ� ��� �ڽ� Ŭ������ ��ü ���� ����
	// => �θ� �������̽��� ��������(�ʵ�)�� �޼ҵ带 ȣ���ϸ� ��������(�ʵ�)�� ����� �ڽ�Ŭ���� ��ü�� �޼ҵ� ȣ�� - �������̵忡 ���� ������
	//��ü���� ���յ��� ���߾� ���������� ȿ���� ���� 
	// => DAO Ŭ������ Ŭ������ ����ŵ� Service Ŭ���� Ŭ������ ��ġ�� ���� �ּ�ȭ
	private StudentDAO studentDAO;	
	
	public StudentServiceImpl() {
		System.out.println("### StudentServiceImpl Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	public StudentServiceImpl(StudentDAO studentDAO) {
		super();
		this.studentDAO = studentDAO;
		System.out.println("### StudentServiceImpl Ŭ������ �Ű������� ����� ������ ȣ�� ###");
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		System.out.println("*** StudentServiceImpl Ŭ������ setStudentDAO(StudentDAO studentDAO) �޼ҵ� ȣ�� ***");
	}

	@Override
	public void addStudent(Student student) {
		System.out.println("*** StudentServiceImpl Ŭ������ addStudent(Student student) �޼ҵ� ȣ�� ***");
		studentDAO.insertStudent(student);
	}

	@Override
	public void modifyStudent(Student student) {
		System.out.println("*** StudentServiceImpl Ŭ������ modifyStudent(Student student) �޼ҵ� ȣ�� ***");
		studentDAO.updateStudent(student);
	}

	@Override
	public void removeStudent(int num) {
		System.out.println("*** StudentServiceImpl Ŭ������ removeStudent(int num) �޼ҵ� ȣ�� ***");
		studentDAO.deleteStudent(num);
	}

	@Override
	public Student getStudent(int num) {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudent(int num) �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudent(num);
	}

	@Override
	public List<Student> getStudentList() {
		System.out.println("*** StudentServiceImpl Ŭ������ getStudentList() �޼ҵ� ȣ�� ***");
		return studentDAO.selectStudentList();
	}

}
