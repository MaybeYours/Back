package xyz.itwill.custom;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

//태그 속성이 있으며 태그 내용이 없는 커스텀 태그 클래스
public class HelloMessageTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	//태그의 속성값을 저장하기 위한 필드 - 태그의 속성명과 동일한 이름으로 선언
	private String name;
	
	//생성자는 인스턴스 생성시 필드 초기화 작업 - 필드값 저장
	public HelloMessageTag() {
		//태그 속성에 대한 기본값 설정 - 커스텀 태그 사용시 속성 생략 가능
		// => 태그 속성을 사용하지 않을 경우 기본적으로 사용될 속성값
		// => 태그 속성이 필수인 경우에는 기본값 설정 생략
		name="홍길동";
	}

	public String getName() {
		return name;
	}

	//커스텀 태그에서 태그 속성을 사용하여 속성값을 변경할 경우 자동 호출
	// => 매개변수로 태그의 속성값을 전달받아 필드값 변경
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if(name.equals("홍길동")) {
				pageContext.getOut().println("<h3>관리자님, 안녕하세요.</h3>");
			} else {
				pageContext.getOut().println("<h3>"+name+"님, 안녕하세요.</h3>");
			}
		} catch (IOException e) {
			//브라우저에게 예외 메세지를 전달하여 출력하기 위해 인위적 예외 발생
			throw new JspException(e.getMessage());
		}
		return super.doStartTag();
	}

	//doAfterBody() 메소드 또는 doEndTag() 메소드를 오버라이드 선언하지 않은 경우 부모클래스
	//(TagSupport)의 메소드 자동 호출
	// => 커스텀 태그 사용시 실행될 명령이 없는 경우 메소드를 오버라이드 선언하지 않고 사용
}





