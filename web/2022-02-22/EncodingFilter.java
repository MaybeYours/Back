package xyz.itwill.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//���� Ŭ���� : Ư�� �����α׷� ��û�� ���� �����α׷� ���� �� ����� ��ɰ� �����α׷� ���� ��
//����� ����� �ۼ��ϱ� ���� Ŭ����
// => �����α׷� ���� �� ����� ��� : ������Ʈ �޼���(HttpServletRequest �ν��Ͻ�)�� ������ ����
// => �����α׷� ���� �� ����� ��� : �������� �޼���(HttpServletResponse �ν��Ͻ�)�� ������ ����

//���� Ŭ������ �ݵ�� Filter �������̽��� ��ӹ޾� �ۼ�
// => ���� Ŭ������ @WebFilter ������̼� �Ǵ� web.xml ������ filter ������Ʈ�� ����Ͽ� ���ͷ� ���۵ǵ��� ���� 

//��� �����α׷� ��û�� ���� �����α׷� ���� �� ���ް��� ���� ĳ���ͼ��� �����ϴ� ���� Ŭ����
public class EncodingFilter implements Filter {
	//������ ĳ���ͼ��� ���ڵ��� �����ϱ� ���� �ʵ�
	private String encoding;

	//���� Ŭ������ �ν��Ͻ��� ������ �� ���� ���� �ѹ��� ȣ��Ǵ� �޼ҵ� - �ʱ�ȭ �۾�
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//encoding="utf-8";
		
		//web.xml ���Ͽ��� �����Ǵ� ���� ��ȯ�޾� �ʵ尪 ���� 
		encoding=filterConfig.getInitParameter("encoding");
	}
	
	//���Ͱ� ���۵� �����α׷��� ���� ��û���� �ݺ� ȣ��Ǵ� �޼ҵ� 
	// => Ŭ���Ʈ�� ��û�� ���� ���۵� �����α׷��� ó�� �� �Ǵ� �Ŀ� ����� ����� �ۼ�
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//�����α׷� ���� ���� ����� ��� �ۼ� - ������Ʈ �޼��� ���� 
		if(request.getCharacterEncoding()==null || 
				!request.getCharacterEncoding().equalsIgnoreCase(encoding)) {
			request.setCharacterEncoding(encoding);//���ް��� ���� ĳ���ͼ� ����
		}
		
		//FilterChain.doFilter(ServletRequest request, ServletResponse response) : Ŭ���̾�Ʈ��
		//��û�� �����α׷��� �����Ͽ� ����ǵ��� �����ϴ� �޼ҵ�
		chain.doFilter(request, response);//��û �����α׷� ����

		//�����α׷� ���� �Ŀ� ����� ��� �ۼ� - �������� �޼��� ����  
	}
	
	
}
