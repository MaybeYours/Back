package xyz.itwill10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
��û ó�� �޼ҵ��� ó������� ��������(JSP)���� �����ϴ� ���
1.ModelAndView ��ü�� ó������� �Ӽ������� �����Ͽ� ����
2.HttpServletRequest ��ü�� ó������� �Ӽ������� �����Ͽ� ����
3.Model ��ü�� ó������� �Ӽ������� �����Ͽ� ����
*/

@Controller
public class ResultController {
	/*
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult() {
		ModelAndView modelAndView=new ModelAndView("result_display");//ViewName ����
		
		//ModelAndView.addObject(String attributeName, Object attributeValue) 
		// => ��������(JSP)���� ó������� ����� �� �ֵ��� �Ӽ������� �����ϴ� �޼ҵ� - Request Scope 
		modelAndView.addObject("mavName", "ȫ�浿");
		
		return modelAndView;
	}
	*/
	
	//��û ó�� �޼ҵ�� Front Controller�� ���� ȣ��Ǵ� �޼ҵ�
	// => ��û ó�� �޼ҵ忡 �Ű������� �ۼ��ϸ� Front Controller�� Spring Container�κ��� 
	//�ʿ��� Spring Bean ��ü�� �����޾� �Ű������� �ڵ� �����Ͽ� ����
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult(ModelAndView modelAndView) {
		modelAndView.setViewName("result_display");
		modelAndView.addObject("mavName", "ȫ�浿");
		return modelAndView;
	}
	
	@RequestMapping("/resultRequest") 
	public String requestResult(HttpServletRequest request) {
		//HttpServletRequest.setAttribute(String attributeName, Object attributeValue)
		// => ��������(JSP)���� ó������� ����� �� �ֵ��� HttpServletRequest ��ü�� �Ӽ����� �����ϴ� �޼ҵ�
		request.setAttribute("requestName", "�Ӳ���");
		return "result_display";
	}
	
	//Model : ó������� �Ӽ������� �����ϱ� ���� ��ü
	@RequestMapping("/resultModel") 
	public String modelResult(Model model) {
		//Model.addAttribute(String attributeName, Object attributeValue)
		// => ��������(JSP)���� ó������� ����� �� �ֵ��� Model ��ü�� �Ӽ����� �����ϴ� �޼ҵ�
		model.addAttribute("modelName", "����ġ");
		return "result_display";
	}
}






