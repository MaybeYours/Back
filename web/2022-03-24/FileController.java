package xyz.itwill10.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import xyz.itwill10.dto.FileBoard;
import xyz.itwill10.service.FileBoardService;

//파일 업로드 처리
//1.commons-fileupload 라이브러리를 프로젝트에 빌드 처리 - pom.xml
//2.Bean Configuration File(servlet-context.xml)에 파일 업로드 기능을 제공하는 클래스를 Spring Bean으로 등록

@Controller
public class FileController {
	//필드에 WebApplicationContext 객체(Spring Container)를 저장하도록 인젝션 처리
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FileBoardService fileBoardService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "file/upload";
	}
	
	/*
	//MultipartHttpServletRequest : [multipart/form-data] 형태로 전달되는 값과 파일을 처리하기 위한 객체
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) throws IOException {
		String uploadName=request.getParameter("uploadName");
		
		//MultipartHttpServletRequest.getFile(String parameterName) : 전달된 파일을 제공받아
		//MultipartFile 객체로 생성하여 반환하는 메소드
		//MultipartFile : 전달된 파일의 정보를 저장한 객체
		MultipartFile uploadFile=request.getFile("uploadFile");
		
		//전달파일에 대한 유효성 검사
		//MultipartFile.isEmpty() : 전달파일이 없는 경우 true를 반환하는 메소드
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//MultipartFile.getContentType() : 전달파일의 형식(MimeType)를 반환하는 메소드
		System.out.println("파일 형식 = "+uploadFile.getContentType());
		//MultipartFile.getBytes() : 전달파일을 byte 배열(원시데이타)로 반환하는 메소드
		System.out.println("파일 크기 = "+uploadFile.getBytes().length);
		
		//전달파일을 서버에 저장하기 위한 업로드 디렉토리의 파일 시스템 경로를 반환받아 저장
		String uploadDirectory=request.getServletContext().getRealPath("/resources/images");
		
		//전달파일을 서버에 저장하기 위한 File 객체 생성
		File file=new File(uploadDirectory, uploadFile.getOriginalFilename());
		
		//MultipartFile.transferTo(File file) : 전달파일을 서버에 저장하는 메소드
		// => 서버 디렉토리에 업로드 파일과 같은 이름의 파일이 있는 경우 덮어씌우기를 하여 저장
		uploadFile.transferTo(file);//업로드 처리
		
		request.setAttribute("uploadName", uploadName);
		request.setAttribute("uploadFile", uploadFile.getOriginalFilename());
		
		return "file/upload_ok";
	}
	*/

	//서버 디렉토리에 전달파일과 같은 이름의 파일이 존재할 경우 전달파일명 대신	새로운 
	//이름으로 파일명을 변경하여 업로드 처리
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam String uploadName, @RequestParam MultipartFile uploadFile, Model model) throws IllegalStateException, IOException {
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//WebApplicationContext 객체를 이용하여 ServletContext 객체를 제공받아 서버 디렉토리의
		//파일 시스템 경로를 반환받아 저장
		String uploadDirectory=context.getServletContext().getRealPath("/resources/images");
		
		//전달파일명을 반환받아 저장
		String originalFilename=uploadFile.getOriginalFilename();
		File file=new File(uploadDirectory, originalFilename);
		
		//실제 서버 디렉토리에 저장되는 파일명을 저장하기 위한 변수 선언
		// => 초기값으로 전달파일의 이름을 저장
		String uploadFilename=originalFilename;
		
		//서버 디렉토리에 전달파일과 같은 이름의 파일이 존재할 경우 서버 디렉토리에 저장될 파일명 변경
		int i=0;
		while(file.exists()) {//서버 디렉토리에 같은 이름의 파일이 있는 경우 반복 처리
			i++;
			int index=originalFilename.lastIndexOf(".");
			uploadFilename=originalFilename.substring(0, index)+"_"+i+originalFilename.substring(index);
			file=new File(uploadDirectory, uploadFilename);
		}
		
		uploadFile.transferTo(file);
		
		model.addAttribute("uploadName", uploadName);
		model.addAttribute("originalFilename", originalFilename);
		model.addAttribute("uploadFilename", uploadFilename);
		
		return "file/upload_ok";
	}
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.GET)
	public String fileUpload() {
		return "file/file_upload";
	}
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public String fileUpload(@ModelAttribute FileBoard fileBoard) throws IllegalStateException, IOException {
		if(fileBoard.getFile().isEmpty()) {
			return "file/file_upload";
		}
	
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");
		
		//전달 파일명을 반환받아 저장
		String origin=fileBoard.getFile().getOriginalFilename();
		
		//업로드 파일명을 고유값(현재 시스템의 타임스템프)으로 저장
		// => 업로드 처리된 파일명이 중복되지 않도록 설정
		String upload=System.currentTimeMillis()+"";
		
		//DTO 객체의 필드값 변경
		fileBoard.setOrigin(origin);
		fileBoard.setUpload(upload);
		
		fileBoard.getFile().transferTo(new File(uploadDir, upload));
		
		fileBoardService.addFileBoard(fileBoard);
		
		return "redirect:/file_list";
	}
	
	@RequestMapping("/file_list")
	public String fileList(Model model) {
		model.addAttribute("fileBoardList", fileBoardService.getFileBoardList());
		return "file/file_list";
	}
	
	@RequestMapping("/file_delete/{num}")
	public String fileDelete(@PathVariable int num) {
		FileBoard fileBoard=fileBoardService.getFileBoard(num);
		String uploadDir=context.getServletContext().getRealPath("/WEB-INF/upload");

		//서버 디렉토리에 저장된 파일 삭제
		new File(uploadDir, fileBoard.getUpload()).delete();
		
		//FILEBOARD 테이블에 저장된 자료실 정보 삭제
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list";
	}
}






