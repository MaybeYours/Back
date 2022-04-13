package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import xyz.itwill.dto.MyHewon;

public interface MyHewonInterfaceMapper {
	//@Results : �˻����� �÷����� Ŭ������ �ʵ忡 ���εǾ� ������ �� �ִ� ������ �����ϴ� ������̼�
	// => XML ������ resultMap ������Ʈ�� ������ ��� ����
	//value �Ӽ� : ���������� �����ϴ� Result ������̼��� �迭�� ǥ���Ͽ� �Ӽ������� ����
	//@Results ������̼��� �ٸ� �߻�޼ҵ��� SQL ��ɿ� ���� ���� ���� ���� �Ұ��� - �������� ���� �Ұ���
	@Results(value = {
		//@Result : �˻����� �÷����� �ʵ忡 �����ϱ� ���� ������ �����ϴ� ������Ʈ
		//column �Ӽ� : �˻����� �÷����� �Ӽ������� ����
		//property �Ӽ� : �÷����� ������ Ŭ������ �ʵ���� �Ӽ������� ����
		@Result(column = "hewon_id",property = "id")	
		,@Result(column = "hewon_name",property = "name")	
		,@Result(column = "hewon_phone",property = "phone")	
		,@Result(column = "hewon_email",property = "email")	
		,@Result(column = "hewon_state",property = "state")	
	})
	@Select("select * from myhewon order by hewon_id")
	List<MyHewon> selectHewonList();
	
	@Results(value = {
		@Result(column = "hewon_id",property = "id")	
		,@Result(column = "hewon_name",property = "name")	
		,@Result(column = "hewon_phone",property = "phone")	
		,@Result(column = "hewon_email",property = "email")	
		,@Result(column = "hewon_state",property = "state")	
	})
	@Select("select * from myhewon where hewon_name=#{name} order by hewon_id")
	List<MyHewon> selectNameHewonList(String name);
	
	@Results(value = {
		@Result(column = "hewon_id",property = "id")	
		,@Result(column = "hewon_name",property = "name")	
		,@Result(column = "hewon_phone",property = "phone")	
		,@Result(column = "hewon_email",property = "email")	
		,@Result(column = "hewon_state",property = "state")	
	})
	//@SelectProvider : SELECT ����� ��ȯ�ϴ� Ŭ������ �޼ҵ带 ȣ���Ͽ� SELECT �����
	//����ϴ� ������̼� - ���� SQL ����� ����ϱ� ���� ������̼�
	//type �Ӽ� : SQL ����� ��ȯ�ϴ� �޼ҵ尡 �ۼ��� Ŭ����(Clazz)�� �Ӽ������� ����
	//method �Ӽ� : SQL ����� ��ȯ�ϴ� �޼ҵ���� �Ӽ������� ����
	@SelectProvider(type = MyHewonProvider.class, method = "selectDynaminHewonList")
	List<MyHewon> selectDynamicHewonList(Map<String, Object> map);
}
