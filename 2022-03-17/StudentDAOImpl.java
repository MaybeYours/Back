package xyz.itwill10.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.itwill10.dto.Student;
import xyz.itwill10.mapper.StudentMapper;

//SpringMVC에서 Mybatis Framework를 사용하여 DAO 클래스를 작성하는 방법
//1.DataSource 관련 라이브러리와 Mybatis 관련 라이브러리 빌드 처리 - pom.xml
// => ojdbc, spring-jdbc(spring-tx), mybatis, mybatis-spring
//2.Mybtis Framework의 환경설정파일 작성
//3.SqlSessionFactory 관련 클래스와 SqlSession 관련 클래스를 Spring Bean으로 등록
// => Bean Configuration File(root-context)에서 bean 엘리먼트 사용하여 Spring Bean으로 등록
//4.DTO 클래스 작성
//5.Mapper 작성 - XML 맵퍼와 Interface 맵퍼를 바인딩하여 작성하는 것을 권장
//6.DAO 클래스 작성 - XML 맵퍼에 등록된 SQL 명령을 전달하여 실행하고 결과를 제공받아 반환

//DAO 클래스 : 저장매체에게 행에 대한 삽입,변경,삭제,검색 기능일 제공하는 클래스
// => DBMS 서버에 SQL 명령을 전달하여 실행하고 실행결과를 Java 객체로 반환하는 기능의 메소드 작성

//DAO 클래스를 Spring Annotation으로 Spring Bean으로 등록 - DAO 객체로 메소드 호출 가능
//@Repository : DAO 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 트렌젝션 관리자 관련 기능을 제공받아 사용
//Spring Container가 Spring Annotation를 처리하기 위해 Bean Configuration File(servlet-context.xml)에서
//component-scan 엘리먼트를 사용하여 DAO 클래스가 작성된 패키지를 base-package 속성값으로 설정
@Repository  
public class StudentDAOImpl implements StudentDAO {
	//SqlSession 관련 클래스의 Spring Bean를 제공받아 필드에 인젝션 처리
	// => DAO 클래스의 메소드에서 SqlSession 객체의 메소드를 호출하여 XML 맵퍼 파일에 등록된
	//SQL 명령을 전달하여 실행하고 실행결과를 Java 객체로 반환받아 처리 가능
	//@Autowired 어노테이션과 @Qualifier 어노테이션을 사용하여 인젝션 처리
	// => 인젝션 처리할 필드마다 @Autowired 어노테이션 사용 
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertStudent(Student student) {
		return sqlSession.getMapper(StudentMapper.class).insertStudent(student);
	}

	@Override
	public List<Student> selectStudentList() {
		return sqlSession.getMapper(StudentMapper.class).selectStudentList();
	}

}
