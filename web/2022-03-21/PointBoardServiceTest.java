package xyz.itwill.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import xyz.itwill10.dto.PointBoard;
import xyz.itwill10.dto.PointUser;
import xyz.itwill10.service.PointBoardService;


//Spring Framework의 TransactionManager를 이용하여 트렌젝션을 처리하는 방법
//1.spring-tx 라이브러리를 프로젝트에 빌드 처리 - pom.xml >> spring-jdbc 라이브러리에 의해 자동 빌드
//2.Bean Configuration File에서 TransactionManager 관련 클래스를 Spring bean을 등록 - root-context.xml
//3-1.Bean Configuration File에서 AOP 기능을 이용하여 TransactionManager 관련 클래스로 트렌젝션 처리되도록 설정 - servlet-context.xml
// => Bean Configuration File(servlet-context.xml)에 tx 네임스페이스(spring-tx.xsd)와 aop 네임스페이스(spring-aop.xsd) 추가
//3-2.TransactionManager 관련 어노테이션을 사용하여 트렌젝션 처리되도록 설정
// => Bean Configuration File(root-context.xml)에 tx 네임스페이스(spring-tx.xsd) 추가
// => Bean Configuration File 파일(root-context.xml)에서 TransactionManager 관련 어노테이션이 동작되도록 설정

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//@FixMethodOrder : 테스트 메소드의 호출 순서를 설정하는 어노테이션
//value 속성 : MethodSorters 자료형(Enum)의 상수 중 하나를 속성값으로 설정
// => MethodSorters.DEFAULT : 테스트 메소드의 해쉬코드(메모리주소)를 이용하여 호출순서 설정 - 호출순서 불확실
// => MethodSorters.JVM : 테스트 메소드를 JVM이 불러온 순서대로 호출순서 설정 - 호출순서 불확실
// => MethodSorters.NAME_ASCENDING : 테스트 메소드의 이름을 참조하여 호출순서 설정 - 호출순서 확실
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class PointBoardServiceTest {
	private static final Logger logger=LoggerFactory.getLogger(PointBoardServiceTest.class);

	@Autowired
	private PointBoardService pointBoardService;
	
	@Test
	public void testAddPointBoard() {
		PointBoard board=new PointBoard();
		//board.setWriter("abc");
		board.setWriter("xyz");
		board.setSubject("테스트");
		
		//게시글 작성자가 없는 예외 발생
		//문제점)예외 발생전에 실행된 게시글 저장 관련 SQL 명령은 이미 처리되어 테이블에 게시글 저장
		//해결법)예외가 발생되기 전에 실행된 모든 SQL 명령은 취소되도록 처리 - ROLLBACK 처리 
		// => Spring Framework에서 제공하는 TransactionManager 관련 클래스를 이용하여 트렌젝션 처리 - AOP 
		PointUser user=pointBoardService.addPointBoard(board);
		
		logger.info("아이디 = "+user.getId()+", 이름 = "+user.getName()+", 포인트 = "+user.getPoint());
	}
	
	@Test
	public void testGetPointBoardList() {
		List<PointBoard> boardList=pointBoardService.getPointBoardList();
		
		if(boardList.isEmpty()) {
			logger.info("저장된 게시글이 하나도 없습니다.");
			return;
		}
		
		for(PointBoard board:boardList) {
			logger.info("글번호 = "+board.getNum()+", 작성자 = "+board.getWriter()+", 제목 = "+board.getSubject());
		}
	}
	
	/*
	@Test
	public void testErasePointBoard() {
		PointUser user=pointBoardService.erasePointBoard(2);

		logger.info("아이디 = "+user.getId()+", 이름 = "+user.getName()+", 포인트 = "+user.getPoint());
	}
	*/
}
