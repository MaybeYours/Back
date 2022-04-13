package xyz.itwill.mapper;

import java.util.List;

import xyz.itwill.dto.MyMember;

//XML ¸ÊÆÛ¿Í ¹ÙÀÎµùµÈ Interface ¸ÊÆÛ
public interface MyMemberMapper {
	int insertMember(MyMember member);
	int updateMember(MyMember member);
	int deleteMember(String id);
	MyMember selectMember(String id);
	List<MyMember> selectMemberList();
}
