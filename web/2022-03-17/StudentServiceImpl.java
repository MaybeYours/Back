package xyz.itwill10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.itwill10.dao.StudentDAO;
import xyz.itwill10.dto.Student;

//Service 클래스 : 프로그램 실행에 필요한 기능을 제공하는 클래스

//Service 클래스를 Spring Annotation으로 Spring Bean으로 등록 - Service 객체로 메소드 호출 가능
//@Service : Service 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 예외 처리 기능을 제공받아 사용
//Spring Container가 Spring Annotation를 처리하기 위해 Bean Configuration File(servlet-context.xml)에서
//component-scan 엘리먼트를 사용하여 Service 클래스가 작성된 패키지를 base-package 속성값으로 설정
@Service
public class StudentServiceImpl implements StudentService {
	//DAO 클래스로 등록된 Spring Bean를 제공받아 필드에 인젝션 처리
	// => Service 클래스의 메소드에서 DAO 클래스의 메소드를 호출하여 필요한 기능 구현
	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void addStudent(Student student) {
		studentDAO.insertStudent(student);
	}

	@Override
	public List<Student> getStudentList() {
		return studentDAO.selectStudentList();
	}
}










