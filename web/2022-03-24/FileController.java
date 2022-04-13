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

//���� ���ε� ó��
//1.commons-fileupload ���̺귯���� ������Ʈ�� ���� ó�� - pom.xml
//2.Bean Configuration File(servlet-context.xml)�� ���� ���ε� ����� �����ϴ� Ŭ������ Spring Bean���� ���

@Controller
public class FileController {
	//�ʵ忡 WebApplicationContext ��ü(Spring Container)�� �����ϵ��� ������ ó��
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private FileBoardService fileBoardService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "file/upload";
	}
	
	/*
	//MultipartHttpServletRequest : [multipart/form-data] ���·� ���޵Ǵ� ���� ������ ó���ϱ� ���� ��ü
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) throws IOException {
		String uploadName=request.getParameter("uploadName");
		
		//MultipartHttpServletRequest.getFile(String parameterName) : ���޵� ������ �����޾�
		//MultipartFile ��ü�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
		//MultipartFile : ���޵� ������ ������ ������ ��ü
		MultipartFile uploadFile=request.getFile("uploadFile");
		
		//�������Ͽ� ���� ��ȿ�� �˻�
		//MultipartFile.isEmpty() : ���������� ���� ��� true�� ��ȯ�ϴ� �޼ҵ�
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//MultipartFile.getContentType() : ���������� ����(MimeType)�� ��ȯ�ϴ� �޼ҵ�
		System.out.println("���� ���� = "+uploadFile.getContentType());
		//MultipartFile.getBytes() : ���������� byte �迭(���õ���Ÿ)�� ��ȯ�ϴ� �޼ҵ�
		System.out.println("���� ũ�� = "+uploadFile.getBytes().length);
		
		//���������� ������ �����ϱ� ���� ���ε� ���丮�� ���� �ý��� ��θ� ��ȯ�޾� ����
		String uploadDirectory=request.getServletContext().getRealPath("/resources/images");
		
		//���������� ������ �����ϱ� ���� File ��ü ����
		File file=new File(uploadDirectory, uploadFile.getOriginalFilename());
		
		//MultipartFile.transferTo(File file) : ���������� ������ �����ϴ� �޼ҵ�
		// => ���� ���丮�� ���ε� ���ϰ� ���� �̸��� ������ �ִ� ��� �����⸦ �Ͽ� ����
		uploadFile.transferTo(file);//���ε� ó��
		
		request.setAttribute("uploadName", uploadName);
		request.setAttribute("uploadFile", uploadFile.getOriginalFilename());
		
		return "file/upload_ok";
	}
	*/

	//���� ���丮�� �������ϰ� ���� �̸��� ������ ������ ��� �������ϸ� ���	���ο� 
	//�̸����� ���ϸ��� �����Ͽ� ���ε� ó��
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam String uploadName, @RequestParam MultipartFile uploadFile, Model model) throws IllegalStateException, IOException {
		if(uploadFile.isEmpty()) {
			return "file/upload_fail";
		}
		
		//WebApplicationContext ��ü�� �̿��Ͽ� ServletContext ��ü�� �����޾� ���� ���丮��
		//���� �ý��� ��θ� ��ȯ�޾� ����
		String uploadDirectory=context.getServletContext().getRealPath("/resources/images");
		
		//�������ϸ��� ��ȯ�޾� ����
		String originalFilename=uploadFile.getOriginalFilename();
		File file=new File(uploadDirectory, originalFilename);
		
		//���� ���� ���丮�� ����Ǵ� ���ϸ��� �����ϱ� ���� ���� ����
		// => �ʱⰪ���� ���������� �̸��� ����
		String uploadFilename=originalFilename;
		
		//���� ���丮�� �������ϰ� ���� �̸��� ������ ������ ��� ���� ���丮�� ����� ���ϸ� ����
		int i=0;
		while(file.exists()) {//���� ���丮�� ���� �̸��� ������ �ִ� ��� �ݺ� ó��
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
		
		//���� ���ϸ��� ��ȯ�޾� ����
		String origin=fileBoard.getFile().getOriginalFilename();
		
		//���ε� ���ϸ��� ������(���� �ý����� Ÿ�ӽ�����)���� ����
		// => ���ε� ó���� ���ϸ��� �ߺ����� �ʵ��� ����
		String upload=System.currentTimeMillis()+"";
		
		//DTO ��ü�� �ʵ尪 ����
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

		//���� ���丮�� ����� ���� ����
		new File(uploadDir, fileBoard.getUpload()).delete();
		
		//FILEBOARD ���̺� ����� �ڷ�� ���� ����
		fileBoardService.removeFileBoard(num);
		
		return "redirect:/file_list";
	}
}






