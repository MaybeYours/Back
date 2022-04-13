package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import xyz.itwill.dto.MyHewon;

public interface MyHewonInterfaceMapper {
	//@Results : 검색행의 컬럼값을 클래스의 필드에 매핑되어 저장할 수 있는 정보를 제공하는 어노테이션
	// => XML 맵퍼의 resultMap 엘리먼트와 유사한 기능 제공
	//value 속성 : 매핑정보를 제공하는 Result 어노테이션의 배열로 표현하여 속성값으로 설정
	//@Results 어노테이션은 다른 추상메소드의 SQL 명령에 대한 매핑 정보 제공 불가능 - 매핑정보 재사용 불가능
	@Results(value = {
		//@Result : 검색행의 컬럼값을 필드에 매핑하기 위한 정보를 제공하는 엘리먼트
		//column 속성 : 검색행의 컬럼명을 속성값으로 설정
		//property 속성 : 컬럼값을 저장할 클래스의 필드명을 속성값으로 설정
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
	//@SelectProvider : SELECT 명령을 반환하는 클래스의 메소드를 호출하여 SELECT 명령을
	//등록하는 어노테이션 - 동적 SQL 기능을 사용하기 위한 어노테이션
	//type 속성 : SQL 명령을 반환하는 메소드가 작성된 클래스(Clazz)를 속성값으로 설정
	//method 속성 : SQL 명령을 반환하는 메소드명을 속성값으로 설정
	@SelectProvider(type = MyHewonProvider.class, method = "selectDynaminHewonList")
	List<MyHewon> selectDynamicHewonList(Map<String, Object> map);
}
