package xyz.itwill.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

//Provider Ŭ���� : SQL ����� ��ȯ�ϴ� �޼ҵ尡 ����� Ŭ����
public class MyHewonProvider {
	//Java ������� ���� SQL ����� �����Ͽ� SQL ��� ��ȯ
	public String selectDynaminHewonList(Map<String, Object> map) {
		//SQL : SQL ����� �����ϱ� ���� Ŭ����
		// => SQL ��� �ۼ��� �ʿ��� �޼ҵ带 ȣ���Ͽ� SQL ����� �ν��Ͻ��� ����
		// => �͸��� ���� Ŭ������ �ν��Ͻ��� �����Ͽ� �޼ҵ� ȣ��
		//SQL.toString() : SQL �ν��Ͻ��� ����� SQL ����� ���ڿ��� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
		return new SQL() {{
			SELECT("*");
			FROM("myhewon");
			if(map.get("name")!=null && !map.get("name").equals("")) {
				WHERE("hewon_name=#{name}");
			}
			ORDER_BY("hewon_id");
		}}.toString();
	}
}
