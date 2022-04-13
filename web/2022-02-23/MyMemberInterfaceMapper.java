package xyz.itwill.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import xyz.itwill.dto.MyMember;

//mybatis 프레임워크는 인터페이스를 이용하여 맵퍼 설정 가능
// => 추상메소드에 맵퍼 어노테이션(Mapper Annotation)을 사용하여 SQL 명령 등록
public interface MyMemberInterfaceMapper {
	//@Insert : INSERT 명령을 등록하기 위한 어노테이션
	//value 속성 : 추상메소드에 등록한 SQL 명령을 속성값으로 설정
	// => 어노테이션에 value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
	@Insert(value = "insert into mymember values(#{id},#{name},#{phone},#{email})")
	//추상메소드의 매개변수에는 SQL 작성에 필요한 값을 제공받기 위해 선언하며 반환형은 SQL 명령의
	//실행결과를 제공받기 위한 Java 자료형을 선언
	int insertMember(MyMember member);
	
	//@Update : UPDATE 명령을 등록하기 위한 어노테이션
	@Update("update mymember set name=#{name},phone=#{phone},email=#{email} where id=#{id}")
	int updateMember(MyMember member);
	
	//@Delete : DELETE 명령을 등록하기 위한 어노테이션
	@Delete("delete from mymember where id=#{id}")
	int deleteMember(String id);
	
	//@Select : SELECT 명령을 등록하기 위한 어노테이션
	@Select("select * from mymember where id=#{id}")
	MyMember selectMember(String id);
	
	@Select("select * from mymember order by id")
	List<MyMember> selectMemberList();
}







