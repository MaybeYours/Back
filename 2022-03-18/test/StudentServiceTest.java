package xyz.itwill.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import xyz.itwill10.dto.Student;
import xyz.itwill10.service.StudentService;

//테스트 프로그램은 DAO 클래스 또는 Service 클래스의 메소드를 검사할 목적으로 작성
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration : WebAppcationContext 객체(Spring Container)를 이용하여 Spring Bean를 
//관리하도록 설정하는 어노테이션
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class StudentServiceTest {
	public static final Logger logger=LoggerFactory.getLogger(StudentServiceTest.class);
	
	@Autowired
	private StudentService studentService;
	
	@Test
	public void testStudentList() {
		List<Student> studentList=studentService.getStudentList();
		
		for(Student student:studentList) {
			/*
			logger.info(student.getNo()+", "+student.getName()+", "+student.getPhone()
				+", "+student.getAddress()+", "+student.getBirthday().substring(0, 10));
			*/
			logger.info(student.toString());
		}
	}
}





