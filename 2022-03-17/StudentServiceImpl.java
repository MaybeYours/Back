package xyz.itwill10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.itwill10.dao.StudentDAO;
import xyz.itwill10.dto.Student;

//Service Ŭ���� : ���α׷� ���࿡ �ʿ��� ����� �����ϴ� Ŭ����

//Service Ŭ������ Spring Annotation���� Spring Bean���� ��� - Service ��ü�� �޼ҵ� ȣ�� ����
//@Service : Service Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
// => ���� ó�� ����� �����޾� ���
//Spring Container�� Spring Annotation�� ó���ϱ� ���� Bean Configuration File(servlet-context.xml)����
//component-scan ������Ʈ�� ����Ͽ� Service Ŭ������ �ۼ��� ��Ű���� base-package �Ӽ������� ����
@Service
public class StudentServiceImpl implements StudentService {
	//DAO Ŭ������ ��ϵ� Spring Bean�� �����޾� �ʵ忡 ������ ó��
	// => Service Ŭ������ �޼ҵ忡�� DAO Ŭ������ �޼ҵ带 ȣ���Ͽ� �ʿ��� ��� ����
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










