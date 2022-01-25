package xyz.itwill.dto;

/*
이름       널?       유형            
-------- -------- ------------- 
NUM      NOT NULL NUMBER(4)     - 제품번호     
CATEGORY          VARCHAR2(20)  - 카테고리  
NAME              VARCHAR2(50)  - 제품명
IMAGE             VARCHAR2(30)  - 제품대표이미지 
DETAIL            VARCHAR2(300) - 제품상세정보 
QTY               NUMBER(8)     - 제품수량  
PRICE             NUMBER(8)     - 제품가격
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
