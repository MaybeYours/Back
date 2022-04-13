package xyz.itwill.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

//Provider 클래스 : SQL 명령을 반환하는 메소드가 선언된 클래스
public class MyHewonProvider {
	//Java 명령으로 동적 SQL 기능을 구현하여 SQL 명령 반환
	public String selectDynaminHewonList(Map<String, Object> map) {
		//SQL : SQL 명령을 저장하기 위한 클래스
		// => SQL 명령 작성에 필요한 메소드를 호출하여 SQL 명령을 인스턴스에 저장
		// => 익명의 내부 클래스로 인스턴스를 생성하여 메소드 호출
		//SQL.toString() : SQL 인스턴스에 저장된 SQL 명령을 문자열로 변환하여 반환하는 메소드
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
