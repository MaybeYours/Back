package xyz.itwill.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import xyz.itwill.dto.Student;

public class StudentDAO {
	private static StudentDAO _dao;
	
	private StudentDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new StudentDAO();		
	}
	
	public static StudentDAO getDAO() {
		return _dao;
	}
	
	//SqlSessionFactory �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	// => SqlSessionFactory �ν��Ͻ��� �����ϱ� ���� mybatis ȯ�漳�������� �ݵ�� �ʿ�
	//SqlSessionFactory : SqlSession �ν��Ͻ��� �����Ͽ� �����ϴ� �ν��Ͻ�
	private SqlSessionFactory getSqlSessionFactory() {
		//String resource="xyz/itwill/dao/mybatis-config.xml";
		String resource="mybatis-config.xml";
		
		InputStream inputStream=null;
		try {
			//mybatis ȯ�漳�������� �б� ���� �Է½�Ʈ���� �����޾� ����
			//Resources.getResourceAsStream(String resource) : ���� ���ؽ�Ʈ�� �����ϴ�
			//���ҽ� ������ ��θ� �����޾� �Է½�Ʈ���� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
			inputStream=Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		
		//SqlSessionFactoryBuilder : SqlSessionFactory �ν��Ͻ��� �����ϴ� ����� �����ϴ� �ν��Ͻ�
		//SqlSessionFactoryBuilder.build(InputStream inputStream) : �Է½�Ʈ���� �̿��Ͽ�
		//mybatis ȯ�漳�������� ������ �����޾� SqlSessionFactory �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	//STUDENT ���̺� ����� ��� �л������� �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	public List<Student> selectStudentList() {
		//SqlSessionFactory.openSession() : SqlSession �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
		//SqlSession : DBMS ������ �����ؼ� ���ۿ� ��ϵ� SQL ����� �����޾� �����Ͽ� �����ϰ�
		//SQL ����� �������� Java �ڷ����� ��ü(��)�� �����ϴ� �ν��Ͻ�
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		try {
			//SqlSession.selectList(String elementId) : ���ۿ� ��ϵ� SELECT ����� �����޾�
			//�����Ͽ� SELECT ����� ó������� List �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
			//elementId : ������ mapper ������Ʈ�� namespace �Ӽ����� select ������Ʈ��
			//id �Ӽ����� �̿��Ͽ� ���ۿ� ��ϵ� SQL ����� �����ޱ� ���� �ĺ���
			return sqlSession.selectList("xyz.itwill.mapper.StudentMapper.selectStudentList");
		} finally {
			//SqlSession.close() : SqlSession �ν��Ͻ��� �����ϴ� �޼ҵ�
			sqlSession.close();
		}
	}
}












