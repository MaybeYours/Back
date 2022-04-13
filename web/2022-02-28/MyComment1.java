package xyz.itwill.dto;

import java.io.Serializable;

/*
MYCOMMENT 테이블 : 게시글을 저장하기 위한 테이블
 => 글번호(COMMENT_NO),작성자 - 아이디(COMMENT_ID),글내용(COMMENT_CONTENT),작성일(COMMENT_DATE)
create table mycomment(comment_no number primary key, comment_id varchar2(20)
    , comment_content varchar2(100), comment_date date);

MYCOMMENT 테이블의 글번호(COMMENT_NO) 컬럼에 저장될 자동 증가값을 제공하기 위한 시퀸스    
create sequence mycomment_seq; 

이름              널?       유형            
--------------- -------- ------------- 
COMMENT_NO      NOT NULL NUMBER        
COMMENT_ID               VARCHAR2(20)  
COMMENT_CONTENT          VARCHAR2(100) 
COMMENT_DATE             DATE          
*/

//객체직렬화 클래스 : Serializable 인터페이스를 상속받은 클래스
// => 객체 단위로 입력 또는 출력을 위한 클래스 선언하는 방법
public class MyComment1 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int commentNo;
	private String commentId;
	private String commentContent;
	private String commentDate;
	
	public MyComment1() {
		// TODO Auto-generated constructor stub
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
}
