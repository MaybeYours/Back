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

//Spring Framework�� �̿��Ͽ� �׽�Ʈ ���α׷��� �ۼ��� ���(���� ���α׷�) �׽�Ʈ �ϴ� ���
//1.junit ���̺귯���� spring-test ���̺귯���� ������Ʈ�� ���� ó�� - pom.xml
// => junit ���̺귯���� spring-test ���̺귯���� scope ������Ʈ �ּ� ó��
//2.[src/test/resources] ������ [log4j.xml] ������ ���� ����
//3.[src/test/java] ������ �׽�Ʈ Ŭ���� �ۼ� - �׽�Ʈ �޼ҵ带 �̿��Ͽ� ��� �׽�Ʈ

//@RunWith : �׽�Ʈ �޼ҵ带 ȣ���Ͽ� �����ϱ� ���� Ŭ����(Clazz)�� �����ϴ� ������̼�
//value �Ӽ� : �׽�Ʈ �޼ҵ带 ȣ���ϱ� ���� Ŭ����(Clazz)�� �Ӽ������� ���� - �ٸ� �Ӽ��� ���� ��� �Ӽ����� ���� ����
//SpringJUnit4ClassRunner : Spring Container(ApplicationContext ��ü)�� �����Ͽ� Spring Bean�� �����Ͽ� 
//�׽�Ʈ ���α׷��� �����ϴ� Ŭ���� 
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration : �׽�Ʈ Ŭ�������� ���� Spring Bean�� ��ϵ� Bean Configuration File�� �����ϴ� ������̼�
//locations �Ӽ� : Bean Configuration File�� �迭�� ��ҷ� �����Ͽ� �Ӽ������� ����
// => Bean Configuration File ��δ� file ���λ縦 ����Ͽ� ���� �ý��� ��� ǥ��
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DataSourceTest {
	public static final Logger logger=LoggerFactory.getLogger(DataSourceTest.class);
	
	//@Autowired ������̼��� ����Ͽ� DataSource ���� Ŭ������ Spring Bean�� �ʵ忡 ������ ó��
	@Autowired
	private DataSource dataSource;
	
	//@Test : �׽�Ʈ �޼ҵ带 �����ϴ� ������̼�
	// => Runner Ŭ���� ���� ȣ��Ǿ� ����� ����Ǵ� �޼ҵ�
	@Test
	public void testDataSource() throws SQLException {
		logger.info("DataSource = "+dataSource);
		logger.info("Connection = "+dataSource.getConnection());
	}
}









