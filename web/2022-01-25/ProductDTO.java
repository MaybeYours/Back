package xyz.itwill.dto;

/*
�̸�       ��?       ����            
-------- -------- ------------- 
NUM      NOT NULL NUMBER(4)     - ��ǰ��ȣ     
CATEGORY          VARCHAR2(20)  - ī�װ�  
NAME              VARCHAR2(50)  - ��ǰ��
IMAGE             VARCHAR2(30)  - ��ǰ��ǥ�̹��� 
DETAIL            VARCHAR2(300) - ��ǰ������ 
QTY               NUMBER(8)     - ��ǰ����  
PRICE             NUMBER(8)     - ��ǰ����
*/

public class ProductDTO {
	private int num;
	private String category;
	private String name;
	private String image;
	private String detail;
	private int qty;
	private int price;
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
