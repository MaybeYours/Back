package xyz.itwill.dto;

//MYCOMMENT 테이블과 MYUSER 테이블의 검색결과를 저장하기 위한 클래스
// => 1:1 관계의 조인 검색 결과를 저장하기 위한 클래스
//기존에 작성된 클래스를 재사용(POJO - Plan Old Java Object)하여 새로운 클래스를 쉽게 
//작성해 생산성 증가 및 유지보수 효율성 증가
public class MyCommentUser2 {
	//검색행의 게시글정보(MYCOMMENT 테이블)을 저장하기 위한 필드 - 포함 관계
	// => 포함 관계가 성립되기 위해서는 반드시 필드에 인스턴스 저장 - association 엘리먼트 이용
	private MyComment1 comment;
	
	//검색행의 회원정보(MYUSER 테이블)을 저장하기 위한 필드 - 포함 관계	
	private MyUser user;
	
	public MyCommentUser2() {
		// TODO Auto-generated constructor stub
	}

	public MyComment1 getComment() {
		return comment;
	}

	public void setComment(MyComment1 comment) {
		this.comment = comment;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}
}
