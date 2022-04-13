package xyz.itwill.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
//[*] 패턴문자를 사용하여 Bean Configuration File 설정 가능
// => [**] 형식으로 폴더를 표현하면 0개 이상의 하위 폴더를 표현
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class StudentControllerTest {
	public static final Logger logger=LoggerFactory.getLogger(StudentControllerTest.class);
	
	//WebApplicationContext : SpringMVC(웹프로그램)에서 사용하는 Spring Container 객체
	@Autowired
	private WebApplicationContext context;
	
	//MockMvc : 요청과 응답을 가상으로 제공하기 위한 클래스
	private MockMvc mvc;
	
	//@Before : 테스트 메소드 호출 전 실행될 명령을 작성한 메소드에 설정하는 어노테이션 - 초기화 작업
	@Before
	public void setup() {
		//MockMvcBuilders.webAppContextSetup(WebApplicationContext context) : MockMvcBuilder 객체를 반환하는 메소드
		//MockMvcBuilder.build() : MockMvc 객체를 반환하는 메소드
		mvc=MockMvcBuilders.webAppContextSetup(context).build();
		logger.info("MockMvc 객체 생성");
	}
	
	@Test
	public void testStudentDisplay() throws Exception {
		//MockMvc.perform(Builder requestBuilder) : 가상으로 웹프로그램을 요청하는 메소드
		// => ResultActions 객체(요청에 대한 처리 결과를 저장한 객체) 반환
		//MockMvcRequestBuilders.get(String url) : 요청 URL 주소를 이용하여 GET 방식으로 요청하는 메소드
		// => RequestBuilder 객체(웹프로그램의 요청정보를 저장한 객체) 반환 
		//ResultActions.andReturn() : 요청 처리 메소드의 결과를 MvcResult 객체로 반환하는 메소드
		MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/student/display")).andReturn();
		
		logger.info(result.getModelAndView().getViewName());
		logger.info(result.getModelAndView().getModel().toString());
	}
}









