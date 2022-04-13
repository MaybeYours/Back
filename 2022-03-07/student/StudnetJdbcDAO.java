package xyz.itwill05.di;

import java.util.List;

//DAO 클래스 : 저장매체(File, DBMS 등)에 대한 행 삽입,변경,삭제,검색 기능을 제공하는 클래스
// => 저장매체의 종류 또는 방법에 따라 DAO 클래스 변경 가능
// => DAO 클래스가 변경돼도 Service 클래스의 영향을 최소화 하기 위한 인터페이스를 상속받아 저장 
public class StudnetJdbcDAO implements StudentDAO {
	public StudnetJdbcDAO() {
		System.out.println("### StudnetJdbcDAO 클래스의 기본 생성자 호출 ###");
	}
	
	@Override
	public int insertStudent(Student student) {
		System.out.println("*** StudnetJdbcDAO 클래스의 insertStudent(Student student) 메소드 호출 ***");
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		System.out.println("*** StudnetJdbcDAO 클래스의 updateStudent(Student student) 메소드 호출 ***");
		return 0;
	}

	@Override
	public int deleteStudent(int num) {
		System.out.println("*** StudnetJdbcDAO 클래스의 deleteStudent(int num) 메소드 호출 ***");
		return 0;
	}

	@Override
	public Student selectStudent(int num) {
		System.out.println("*** StudnetJdbcDAO 클래스의 selectStudent(int num) 메소드 호출 ***");
		return null;
	}

	@Override
	public List<Student> selectStudentList() {
		System.out.println("*** StudnetJdbcDAO 클래스의 selectStudentList() 메소드 호출 ***");
		return null;
	}

}
