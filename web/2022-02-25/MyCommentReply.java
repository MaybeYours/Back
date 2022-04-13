package xyz.itwill.dto;

import java.util.List;

//MYCOMMENT 테이블과 MYREPLY 테이블의 검색결과를 저장하기 위한 클래스
// => 1:N 관계의 조인 검색 결과를 저장하기 위한 클래스
public class MyCommentReply {
	//MYCOMMENT 테이블의 검색결과를 저장하기 위한 필드 - 검색행 : 1개
	private int commentNo;
	private String commentId;
	private String commentContent;
	private String commentDate;
	
	//MYREPLY 테이블의 검색결과를 저장하기 위한 필드 - 검색행 : 0개 이상
	// => 게시글에 대한 댓글목록을 저장하기 위해 List 자료형을 이용하여 필드 선언 - 포함관계
	private List<MyReply> replies;
	
	public MyCommentReply() {
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

	public List<MyReply> getReplies() {
		return replies;
	}

	public void setReplies(List<MyReply> replies) {
		this.replies = replies;
	}
}







