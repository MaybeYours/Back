package xyz.itwill10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill10.dto.Student;
import xyz.itwill10.service.StudentService;

//Controller Ŭ���� : Ŭ���̾�Ʈ ��û�� ó���ϱ� ���� ����� �����ϴ� Ŭ���� - �����α׷�

//Controller Ŭ������ Spring Annotation���� Spring Bean���� ��� 
// => Front Controller���� Controller ��ü�� ��û ó�� �޼ҵ带 ȣ���ϱ� ���� Spring Bean���� ���
//@Controller : Controller Ŭ������ Spring Bean���� ����ϱ� ���� ������̼�
// => Ŭ���̾�Ʈ�� ��û�� ���� ȣ��Ǵ� ��û ó�� �޼ҵ� �ۼ�
//Spring Container�� Spring Annotation�� ó���ϱ� ���� Bean Configuration File(servlet-context.xml)����
//component-scan ������Ʈ�� ����Ͽ� Controller Ŭ������ �ۼ��� ��Ű���� base-package �Ӽ������� ����
@Controller
//@RequestMapping ������̼��� Ŭ������ �����ϸ� Controller Ŭ������ ��� ��û ó�� �޼ҵ��� ��û
//URL �ּ� �պκп� ���������� ���ԵǴ� URL �ּ� ���� ����
@RequestMapping("/student")
public class StudentController {
	//Service Ŭ������ ��ϵ� Spring Bean�� �����޾� �ʵ忡 ������ ó��
	// => Controller Ŭ������ ��û ó�� �޼ҵ忡�� Service Ŭ������ �޼ҵ带 ȣ���Ͽ� ��û ó�� ����
	@Autowired
	private StudentService studentService;
	
	//@RequestMapping(value = "/student/add", method = RequestMethod.GET)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "student/student_add";
	}
	
	//@RequestMapping(value = "/student/add", method = RequestMethod.POST)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute Student student,Model model) {
		try {
			//PK �������ǿ� ���� �ߺ��� ��(�й�)�� ���޵Ǿ� �޼ҵ带 ȣ���� ��� ���� �߻�
			studentService.addStudent(student);
		} catch (Exception e) {
			model.addAttribute("message", "�̹� ������� �й��� �Է� �Ͽ����ϴ�.");
			return "student/student_add";
		}
		return "redirect:/student/display";
	}
	
	//@RequestMapping("/student/display")
	@RequestMapping("/display")
	public String display(Model model) {
		model.addAttribute("studentList", studentService.getStudentList());
		return "student/student_display";
	}
}
