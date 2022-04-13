package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xyz.itwill.dto.MyMember;

//XML 맵퍼에 등록된 SQL 명령을 제공받아 작성된 DAO 클래스
public class MyMemberXMLDAO {
	private static MyMemberXMLDAO _dao;
	
	private MyMemberXMLDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MyMemberXMLDAO();
	}
	
	public static MyMemberXMLDAO getDAO() {
		return _dao;
	}
	
	private SqlSessionFactory getSqlSessionFactory() {
		String resource="mybatis-config.xml";
		
		InputStream inputStream=null;
		try {
			inputStream=Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//회원정보를 전달받아 MYMEMBER 테이블에 삽입 저장하고 삽입행의 갯수를 반환하는 메소드
	public int insertMember(MyMember member) {
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		try {
			//SqlSession.insert(String elementId, Object parameterValue) : INSERT 명령이
			//등록된 엘리먼트의 SQL 명령을 제공받아 실행 후 삽입행의 갯수를 반환하는 메소드
			// => elementId : SQL 명령이 등록된 맵퍼의 엘리먼트 식별자를 전달받아 저장
			// => parameterValue : SQL 명령에 필요한 값이 저장된 인스턴스를 전달받아 저장
			// => 전달값이 없는 경우 parameterValue 매개변수 생략 가능
			int rows=sqlSession.insert("xyz.itwill.mapper.MyMemberXMLMapper.insertMember", member);
			
			//mybatis 프레임워크는 기본적으로 AutoCommit 기능을 비활성화 처리하고 SQL 명령을 전달하여 실행
			// => DML 명령을 전달하여 실행한 경우 반드시 트렌젝션 관련 메소드를 호출
			if(rows>0) {
				//SqlSession.commit() : 트렌젝션 적용 명령(COMMIT)을 전달하여 실행하는 메소드
				sqlSession.commit();
			} else {
				//SqlSession.rollback() : 트렌젝션 취소 명령(ROLLBACK)을 전달하여 실행하는 메소드
				sqlSession.rollback();
			}
			
			return rows;
		} finally {
			sqlSession.close();
		}
	}
	
	//회원정보를 전달받아 MYMEMBER 테이블에 저장된 회원정보를 변경하고 변경행의 갯수를 반환하는 메소드
	public int updateMember(MyMember member) {
		//SqlSessionFactory.openSession(boolean autoCommit) : SqlSession 인스턴스를 생성하여 반환하는 메소드
		// => false : AutoCommit 기능 비활성화(기본), true : AutoCommit 기능 활성화
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//SqlSession.update(String elementId, Object parameterValue) : UPDATE 명령이
			//등록된 엘리먼트의 SQL 명령을 제공받아 실행 후 변경행의 갯수를 반환하는 메소드
			return sqlSession.update("xyz.itwill.mapper.MyMemberXMLMapper.updateMember", member);	
		} finally {
			sqlSession.close();
		}
	}

	//아이디를 전달받아 MYMEMBER 테이블에 저장된 회원정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	public int deleteMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//SqlSession.delete(String elementId, Object parameterValue) : DELETE 명령이
			//등록된 엘리먼트의 SQL 명령을 제공받아 실행 후 삭제행의 갯수를 반환하는 메소드
			return sqlSession.delete("xyz.itwill.mapper.MyMemberXMLMapper.deleteMember", id);	
		} finally {
			sqlSession.close();
		}
	}

	//아이디를 전달받아 MYMEMBER 테이블에 저장된 회원정보를 검색하여 반환하는 메소드
	public MyMember selectMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//SqlSession.selectOne(String elementId, Object parameterValue) : SELECT 명령이
			//등록된 엘리먼트의 SQL 명령을 제공받아 실행 후 검색결과를 엘리먼트에서 설정한
			//Java 자료형의 인스턴스를 하나만 생성하여 반환하는 메소드
			return sqlSession.selectOne("xyz.itwill.mapper.MyMemberXMLMapper.selectMember", id);
		} finally {
			sqlSession.close();
		}
	}
	
	//MYMEMBER 테이블에 저장된 모든 회원정보를 검색하여 반환하는 메소드
	public List<MyMember> selectMemberList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//SqlSession.selectList(String elementId, Object parameterValue) : SELECT 명령이
			//등록된 엘리먼트의 SQL 명령을 제공받아 실행 후 검색결과를 엘리먼트에서 설정한
			//Java 자료형의 인스턴스를 여러개 생성하여 List 인스턴스의 요소로 저장하여 반환하는 메소드
			return sqlSession.selectList("xyz.itwill.mapper.MyMemberXMLMapper.selectMemberList");
		} finally {
			sqlSession.close();
		}
	}
}






