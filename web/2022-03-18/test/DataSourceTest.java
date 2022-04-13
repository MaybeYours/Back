package xyz.itwill.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Spring Framework를 이용하여 테스트 프로그램을 작성해 모듈(단위 프로그램) 테스트 하는 방법
//1.junit 라이브러리와 spring-test 라이브러리를 프로젝트에 빌드 처리 - pom.xml
// => junit 라이브러리와 spring-test 라이브러리의 scope 엘리먼트 주석 처리
//2.[src/test/resources] 폴더의 [log4j.xml] 파일의 내용 변경
//3.[src/test/java] 폴더에 테스트 클래스 작성 - 테스트 메소드를 이용하여 모듈 테스트

//@RunWith : 테스트 메소드를 호출하여 실행하기 위한 클래스(Clazz)를 설정하는 어노테이션
//value 속성 : 테스트 메소드를 호출하기 위한 클래스(Clazz)를 속성값으로 설정 - 다른 속성이 없는 경우 속성값만 설정 가능
//SpringJUnit4ClassRunner : Spring Container(ApplicationContext 객체)를 생성하여 Spring Bean을 제공하여 
//테스트 프로그램을 실행하는 클래스 
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration : 테스트 클래스에서 사용될 Spring Bean이 등록된 Bean Configuration File를 설정하는 어노테이션
//locations 속성 : Bean Configuration File을 배열의 요소로 지정하여 속성값으로 설정
// => Bean Configuration File 경로는 file 접두사를 사용하여 파일 시스템 경로 표현
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {
	public static final Logger logger=LoggerFactory.getLogger(DataSourceTest.class);
	
	//@Autowired 어노테이션을 사용하여 DataSource 관련 클래스의 Spring Bean를 필드에 인젝션 처리
	@Autowired
	private DataSource dataSource;
	
	//@Test : 테스트 메소드를 설정하는 어노테이션
	// => Runner 클래에 의해 호출되어 명령이 실행되는 메소드
	@Test
	public void testDataSource() throws SQLException {
		logger.info("DataSource = "+dataSource);
		logger.info("Connection = "+dataSource.getConnection());
	}
}









