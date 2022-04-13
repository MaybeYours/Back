package xyz.itwill09.mvc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//Command Controller : Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� Ŭ���� - Model
// => Spring Framework���� �����ϴ� Controller �������̽��� ��ӹ޾� �ۼ�
public class ListContoller implements Controller {
	//handleRequest : Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� ����� �ۼ��ϴ� �޼ҵ�
	// => Front Controller(DispatcherServlet)�� ���� �ڵ� ȣ��Ǵ� �޼ҵ�
	// => HttpServletRequest ��ü�� HttpServletResponse ��ü�� �Ű������� �����޾� ��û ó��
	// => �� ���� ������ Spring Framework���� �����ϴ� ModelAndView Ŭ������ ��ü�� �����Ͽ� ��ȯ
	//ModelAndView : ���� ���� ������ �����ϱ� ���� Ŭ����
	// => ��û�� ���� ó�� ����� �������� ���� ������ �����Ͽ� Front Controller(DispatcherServlet)���� ����
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Ŭ���̾�Ʈ ��û�� ���� ó���� ���� Service Ŭ������ �޼ҵ带 ȣ���Ͽ� ó������� ��ȯ�޾� ����
		List<Member> memberList=new ArrayList<Member>();
		memberList.add(new Member("aaa", "ȫ�浿"));
		memberList.add(new Member("bbb", "�Ӳ���"));
		memberList.add(new Member("ccc", "����ġ"));
	
		//ModelAndView ��ü ���� - ��û ó�� �޼ҵ��� ��ȯ�� 
		ModelAndView modelAndView=new ModelAndView();
		
		//ModelAndView.addObject(String attributeName, Object attributeValue)
		// => ModelAndView ��ü�� ��û�� ���� ó�� ����� �����ϱ� ���� �޼ҵ�
		// => HttpServletRequest.setAttribute() �޼ҵ�� ������ ��� ���� - Request Scope
		// => ��������(JSP)���� EL�� JSTL�� ����Ͽ� ���� ó��
		modelAndView.addObject("memberList", memberList);
		
		//ModelAndView.setViewName(String viewName) : �������� ���� ����(ViewName)�� �����ϴ� �޼ҵ�
		modelAndView.setViewName("member_list");
		
		return modelAndView;
	}

}







