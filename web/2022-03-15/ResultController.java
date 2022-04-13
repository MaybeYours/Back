package xyz.itwill10.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
요청 처리 메소드의 처리결과를 뷰페이지(JSP)에게 제공하는 방법
1.ModelAndView 객체에 처리결과를 속성값으로 저장하여 제공
2.HttpServletRequest 객체에 처리결과를 속성값으로 저장하여 제공
3.Model 객체에 처리결과를 속성값으로 저장하여 제공
*/

@Controller
public class ResultController {
	/*
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult() {
		ModelAndView modelAndView=new ModelAndView("result_display");//ViewName 저장
		
		//ModelAndView.addObject(String attributeName, Object attributeValue) 
		// => 뷰페이지(JSP)에서 처리결과를 사용할 수 있도록 속성값으로 저장하는 메소드 - Request Scope 
		modelAndView.addObject("mavName", "홍길동");
		
		return modelAndView;
	}
	*/
	
	//요청 처리 메소드는 Front Controller에 의해 호출되는 메소드
	// => 요청 처리 메소드에 매개변수를 작성하면 Front Controller가 Spring Container로부터 
	//필요한 Spring Bean 객체를 제공받아 매개변수에 자동 저장하여 제공
	@RequestMapping("/resultMAV")
	public ModelAndView modelAndViewResult(ModelAndView modelAndView) {
		modelAndView.setViewName("result_display");
		modelAndView.addObject("mavName", "홍길동");
		return modelAndView;
	}
	
	@RequestMapping("/resultRequest") 
	public String requestResult(HttpServletRequest request) {
		//HttpServletRequest.setAttribute(String attributeName, Object attributeValue)
		// => 뷰페이지(JSP)에서 처리결과를 사용할 수 있도록 HttpServletRequest 객체에 속성값을 저장하는 메소드
		request.setAttribute("requestName", "임꺽정");
		return "result_display";
	}
	
	//Model : 처리결과를 속성값으러 저장하기 위한 객체
	@RequestMapping("/resultModel") 
	public String modelResult(Model model) {
		//Model.addAttribute(String attributeName, Object attributeValue)
		// => 뷰페이지(JSP)에서 처리결과를 사용할 수 있도록 Model 객체에 속성값을 저장하는 메소드
		model.addAttribute("modelName", "전우치");
		return "result_display";
	}
}






