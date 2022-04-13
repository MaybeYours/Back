package xyz.itwill10.dto;

import org.springframework.web.multipart.MultipartFile;

//create table fileboard(num number primary key, writer varchar2(20), subject varchar2(100)
//    ,origin varchar2(100), upload varchar2(100));
//create sequence fileboard_seq;  
public class FileBoard {
	private int num;
	private String writer;
	private String subject;
	private String origin;//원본 파일명
	private String upload;//업로드 파일명
	private MultipartFile file;//전달파일을 저장하기 위한 필드
	
	public FileBoard() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
