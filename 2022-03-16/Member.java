package xyz.itwill10.dto;

//JavaBean Ŭ���� : �����α׷� ��û�� ���޵� ���� �����ϱ� ���� Ŭ����
// => DAO Ŭ�������� ����� ��� DTO Ŭ������ ��� ����
//���ް��� �̸��� ���� �̸��� �ʵ带 �����ϸ� Setter �޼ҵ带 ȣ���Ͽ� ���ް��� �ʵ忡
//�ڵ����� ����ǵ��� ���� 
public class Member {
	private String id;
	private String passwd;
	private String name;
	private String email;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
