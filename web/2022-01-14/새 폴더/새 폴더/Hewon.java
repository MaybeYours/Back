package xyz.itwill.bean;

//JavaBean Ŭ���� : �����α׷�(JSP)���� ���ް��� ������ ������ Ŭ����

//JavaBean Ŭ���� �ۼ� ��Ģ
// => ���ް��� �̸�(�Է��±��� name �Ӽ���)�� ������ �̸����� �ʵ� ����
// => public ���������ڸ� �̿��� �⺻ ������(�Ű������� ���� ������) �ۼ�
// => �ʵ尪�� ��ȯ�ϴ� Getter �޼ҵ�� �ʵ尪�� �����ϴ� Setter �޼ҵ� �ۼ� - �ʵ�� �̿�

//ȸ�������� �����ϱ� ���� Ŭ���� - VO Ŭ����, DTO Ŭ����, JavaBean Ŭ����
public class Hewon {
	private String name;
	private String phone;
	private String address;
	
	public Hewon() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
