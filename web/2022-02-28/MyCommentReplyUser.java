package xyz.itwill.dto;

import java.util.List;

//MYCOMMENT ���̺�� MYREPLY ���̺�, MYUSER ���̺��� �˻������ �����ϱ� ���� Ŭ����
public class MyCommentReplyUser {
	//MYCOMMENT ���̺��� �˻������ �����ϱ� ���� Ŭ���� - �˻��� : 1��
	//private MyComment1 comment;
	
	//id ������Ʈ �Ǵ� result ������Ʈ�� ���� �����ǵ��� ���� �����ϴ� �ʵ� ����
	private int commentNo;
	private String commentId;
	private String commentContent;
	private String commentDate;

	//MYUSER ���̺��� �˻������ �����ϱ� ���� Ŭ���� - �˻��� : 1��
	private MyUser user;
	
	//MYREPLY ���̺��� �˻������ �����ϱ� ���� Ŭ���� - �˻��� : 0�� �̻�
	// => �������(MyReply)�� ȸ������(MyUser)�� �����ϴ� MyReplyUser Ŭ������ ��ҷ� ����
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
