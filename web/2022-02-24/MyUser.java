package xyz.itwill.dto;

/*
MYUSER ���̺� : ȸ�������� �����ϱ� ���� ���̺�
 => ���̵�(USER_ID), �̸�(USER_NAME)
 => SQL ����� ��ҹ��ڸ� �������� �ʾ� �ĺ��ڸ� ������ �� ������ũ ǥ����� ����Ͽ� ����
 => ������ũ ǥ���(Snake Case) : �ܾ�� �ܾ �����ϱ� ���� _ ��ȣ ����Ͽ� �ĺ��� ���� 
create table myuser(user_id varchar2(20) primary key, user_name varchar2(30));

�̸�        ��?       ����           
--------- -------- ------------ 
USER_ID   NOT NULL VARCHAR2(20) 
USER_NAME          VARCHAR2(30) 
*/

//Java �ڷ���(Class, Interface, Enum)�� �Ľ�Į ǥ����� ����Ͽ� ����
// => �Ľ�Į ǥ���(Pascal Case) : ��� �ܾ��� ù���ڸ� �빮�ڷ� ǥ���Ͽ� �ĺ��� ����
public class MyUser {
	//Java �ڷ����� ������ �ĺ��ڴ� ī�� ǥ����� ����Ͽ� ����
	// => ī�� ǥ���(Camel Case) : ù��° �ܾ��� ù���ڸ� ������ ������ �ܾ��� ù���ڸ� �빮�ڷ� ǥ���Ͽ� �ĺ��� ����
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
