package xyz.itwill.dto;

import java.util.List;

//MYCOMMENT 테이블과 MYREPLY 테이블, MYUSER 테이블의 검색결과를 저장하기 위한 클래스
public class MyCommentReplyUser {
	//MYCOMMENT 테이블의 검색결과를 저장하기 위한 클래스 - 검색행 : 1개
	//private MyComment1 comment;
	
	//id 엘리먼트 또는 result 엘리먼트로 매핑 설정되도록 값을 저장하는 필드 선언
	private int commentNo;
	private String commentId;
	private String commentContent;
	private String commentDate;

	//MYUSER 테이블의 검색결과를 저장하기 위한 클래스 - 검색행 : 1개
	private MyUser user;
	
	//MYREPLY 테이블의 검색결과를 저장하기 위한 클래스 - 검색행 : 0개 이상
	// => 댓글정보(MyReply)와 회원정보(MyUser)를 저장하는 MyReplyUser 클래스를 요소로 설정
	private List<MyReplyUser> replies;
	
	public MyCommentReplyUser() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	public MyComment1 getComment() {
		return comment;
	}

	public void setComment(MyComment1 comment) {
		this.comment = comment;
	}
	*/
	
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

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

	public List<MyReplyUser> getReplies() {
		return replies;
	}

	public void setReplies(List<MyReplyUser> replies) {
		this.replies = replies;
	}
}
