package xyz.itwill.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//필터 클래스 : 특정 웹프로그램 요청에 대해 웹프로그램 동작 전 실행될 명령과 웹프로그램 동작 후
//실행될 명령을 작성하기 위한 클래스
// => 웹프로그램 동작 전 실행될 명령 : 리퀘스트 메세지(HttpServletRequest 인스턴스)의 정보를 변경
// => 웹프로그램 동작 후 실행될 명령 : 리스폰즈 메세지(HttpServletResponse 인스턴스)의 정보를 변경

//필터 클래스는 반드시 Filter 인터페이스를 상속받아 작성
// => 필터 클래스는 @WebFilter 어노테이션 또는 web.xml 파일의 filter 엘리먼트를 사용하여 필터로 동작되도록 설정 

//모든 웹프로그램 요청에 대해 웹프로그램 동작 전 전달값에 대한 캐릭터셋을 변경하는 필터 클래스
public class EncodingFilter implements Filter {
	//변경할 캐릭터셋의 인코딩을 저장하기 위한 필드
	private String encoding;

	//필터 클래스가 인스턴스로 생성된 후 가장 먼저 한번만 호출되는 메소드 - 초기화 작업
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//encoding="utf-8";
		
		//web.xml 파일에서 제공되는 값을 반환받아 필드값 변경 
		encoding=filterConfig.getInitParameter("encoding");
	}
	
	//필터가 동작될 웹프로그램에 대한 요청마다 반복 호출되는 메소드 
	// => 클라언트에 요청에 의해 동작될 웹프로그램의 처리 전 또는 후에 실행될 명령을 작성
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//웹프로그램 동작 전에 실행될 명령 작성 - 리퀘스트 메세지 변경 
		if(request.getCharacterEncoding()==null || 
				!request.getCharacterEncoding().equalsIgnoreCase(encoding)) {
			request.setCharacterEncoding(encoding);//전달값에 대한 캐릭터셋 변경
		}
		
		//FilterChain.doFilter(ServletRequest request, ServletResponse response) : 클라이언트가
		//요청한 웹프로그램을 연결하여 실행되도록 설정하는 메소드
		chain.doFilter(request, response);//요청 웹프로그램 실행

		//웹프로그램 동작 후에 실행될 명령 작성 - 리스폰즈 메세지 변경  
	}
	
	
}
