package xyz.itwill.dto;

/*
MYUSER 테이블 : 회원정보를 저장하기 위한 테이블
 => 아이디(USER_ID), 이름(USER_NAME)
 => SQL 명령은 대소문자를 구분하지 않아 식별자를 선언할 때 스네이크 표기법을 사용하여 선언
 => 스네이크 표기법(Snake Case) : 단어와 단어를 구분하기 위해 _ 기호 사용하여 식별자 선언 
create table myuser(user_id varchar2(20) primary key, user_name varchar2(30));

이름        널?       유형           
--------- -------- ------------ 
USER_ID   NOT NULL VARCHAR2(20) 
USER_NAME          VARCHAR2(30) 
*/

//Java 자료형(Class, Interface, Enum)은 파스칼 표기법을 사용하여 선언
// => 파스칼 표기법(Pascal Case) : 모든 단어의 첫문자를 대문자로 표현하여 식별자 선언
public class MyUser {
	//Java 자료형을 제외한 식별자는 카멜 표기법을 사용하여 선언
	// => 카멜 표기법(Camel Case) : 첫번째 단어의 첫문자를 제외한 나머지 단어의 첫문자를 대문자로 표현하여 식별자 선언
	private String userId;
	private String userName;
	
	public MyUser() {
		// TODO Auto-generated constructor stub
	}
	
	public MyUser(String userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
