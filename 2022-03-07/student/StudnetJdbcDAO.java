package xyz.itwill05.di;

import java.util.List;

//DAO Ŭ���� : �����ü(File, DBMS ��)�� ���� �� ����,����,����,�˻� ����� �����ϴ� Ŭ����
// => �����ü�� ���� �Ǵ� ����� ���� DAO Ŭ���� ���� ����
// => DAO Ŭ������ ����ŵ� Service Ŭ������ ������ �ּ�ȭ �ϱ� ���� �������̽��� ��ӹ޾� ���� 
public class StudnetJdbcDAO implements StudentDAO {
	public StudnetJdbcDAO() {
		System.out.println("### StudnetJdbcDAO Ŭ������ �⺻ ������ ȣ�� ###");
	}
	
	@Override
	public int insertStudent(Student student) {
		System.out.println("*** StudnetJdbcDAO Ŭ������ insertStudent(Student student) �޼ҵ� ȣ�� ***");
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		System.out.println("*** StudnetJdbcDAO Ŭ������ updateStudent(Student student) �޼ҵ� ȣ�� ***");
		return 0;
	}

	@Override
	public int deleteStudent(int num) {
		System.out.println("*** StudnetJdbcDAO Ŭ������ deleteStudent(int num) �޼ҵ� ȣ�� ***");
		return 0;
	}

	@Override
	public Student selectStudent(int num) {
		System.out.println("*** StudnetJdbcDAO Ŭ������ selectStudent(int num) �޼ҵ� ȣ�� ***");
		return null;
	}

	@Override
	public List<Student> selectStudentList() {
		System.out.println("*** StudnetJdbcDAO Ŭ������ selectStudentList() �޼ҵ� ȣ�� ***");
		return null;
	}

}
