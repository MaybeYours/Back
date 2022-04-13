package xyz.itwill10.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.itwill10.dto.Student;
import xyz.itwill10.mapper.StudentMapper;

//SpringMVC���� Mybatis Framework�� ����Ͽ� DAO Ŭ������ �ۼ��ϴ� ���
//1.DataSource ���� ���̺귯���� Mybatis ���� ���̺귯�� ���� ó�� - pom.xml
// => ojdbc, spring-jdbc(spring-tx), mybatis, mybatis-spring
//2.Mybtis Framework�� ȯ�漳������ �ۼ�
//3.SqlSessionFactory ���� Ŭ������ SqlSession ���� Ŭ������ Spring Bean���� ���
// => Bean Configuration File(root-context)���� bean ������Ʈ ����Ͽ� Spring Bean���� ���
//4.DTO Ŭ���� �ۼ�
//5.Mapper �ۼ� - XML ���ۿ� Interface ���۸� ���ε��Ͽ� �ۼ��ϴ� ���� ����
//6.DAO Ŭ���� �ۼ� - XML ���ۿ� ��ϵ� SQL ����� �����Ͽ� �����ϰ� ����� �����޾� ��ȯ

//DAO Ŭ���� : �����ü���� �࿡ ���� ����,����,����,�˻� ����� �����ϴ� Ŭ����
// => DBMS ������ SQL ����� �����Ͽ� �����ϰ� �������� Java ��ü�� ��ȯ�ϴ� ����� �޼ҵ� �ۼ�

//DAO Ŭ������ Spring Annotation���� Spring Bean���� ��� - DAO ��ü�� �޼ҵ� ȣ�� ����
//@Repository : DAO Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
// => Ʈ������ ������ ���� ����� �����޾� ���
//Spring Container�� Spring Annotation�� ó���ϱ� ���� Bean Configuration File(servlet-context.xml)����
//component-scan ������Ʈ�� ����Ͽ� DAO Ŭ������ �ۼ��� ��Ű���� base-package �Ӽ������� ����
@Repository  
public class StudentDAOImpl implements StudentDAO {
	//SqlSession ���� Ŭ������ Spring Bean�� �����޾� �ʵ忡 ������ ó��
	// => DAO Ŭ������ �޼ҵ忡�� SqlSession ��ü�� �޼ҵ带 ȣ���Ͽ� XML ���� ���Ͽ� ��ϵ�
	//SQL ����� �����Ͽ� �����ϰ� �������� Java ��ü�� ��ȯ�޾� ó�� ����
	//@Autowired ������̼ǰ� @Qualifier ������̼��� ����Ͽ� ������ ó��
	// => ������ ó���� �ʵ帶�� @Autowired ������̼� ��� 
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
