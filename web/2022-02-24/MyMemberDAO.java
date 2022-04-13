package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xyz.itwill.dto.MyMember;
import xyz.itwill.mapper.MyMemberMapper;

public class MyMemberDAO {
	private static MyMemberDAO _dao;
	
	private MyMemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MyMemberDAO();
	}
	
	public static MyMemberDAO getDAO() {
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
	
	//ȸ�������� ���޹޾� MYMEMBER ���̺� ���� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	public int insertMember(MyMember member) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			//Interface ������ �߻�޼ҵ带 ȣ���ϸ� XML ���ۿ� ��ϵ� SQL ����� �����޾� ���
			return sqlSession.getMapper(MyMemberMapper.class).insertMember(member);
		} finally {
			sqlSession.close();
		}
	}
	
	//ȸ�������� ���޹޾� MYMEMBER ���̺� ����� ȸ�������� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	public int updateMember(MyMember member) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberMapper.class).updateMember(member);	
		} finally {
			sqlSession.close();
		}
	}

	//���̵� ���޹޾� MYMEMBER ���̺� ����� ȸ�������� �����ϰ� �������� ������ ��ȯ�ϴ� �޼ҵ�
	public int deleteMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberMapper.class).deleteMember(id);	
		} finally {
			sqlSession.close();
		}
	}

	//���̵� ���޹޾� MYMEMBER ���̺� ����� ȸ�������� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	public MyMember selectMember(String id) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberMapper.class).selectMember(id);
		} finally {
			sqlSession.close();
		}
	}
	
	//MYMEMBER ���̺� ����� ��� ȸ�������� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	public List<MyMember> selectMemberList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyMemberMapper.class).selectMemberList();	
		} finally {
			sqlSession.close();
		}
	}
}
