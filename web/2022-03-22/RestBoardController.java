package xyz.itwill10.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import xyz.itwill10.dto.RestBoard;
import xyz.itwill10.service.RestBoardService;
import xyz.itwill10.util.Pager;

//REST 기능을 제공하는 웹프로그램은 요청 처리 메소드의 반환값(실행결과)을 확인하기 위해 
//Advanced REST Client 크롬앱을 설치하여 REST 서비스를 제공하는 웹프로그램 테스트 가능

//AJAX 기능을 이용하여 웹프로그램 요청에 대한 요청 방식
// => 요청 처리 메소드의 @RequestMapping 어노테이션의 method 속성값
// => GET(검색), POST(저장), PUT(전체 변경), PATCH(부분 변경), DELETE(삭제) 등

//@RestController : Controller 클래스를 Spring Bean으로 등록하는 어노테이션  
// => 모든 요청 처리 메소드의 반환값으로 응답되도록 설정하는 어노테이션으로 요청 처리 메소드에서 
//@ResponseBody 어노테이션을 사용하지 않아도 메소드 반환값으로 응답 처리
// => Rest 서비스를 제공하는 Controller 클래스를 작성할 경우 사용
//@RestController
@Controller
public class RestBoardController {
	@Autowired
	private RestBoardService restBoardService;
	
	@RequestMapping("/board")
	public String restBoard() {
		return "rest/board";
	}
	
	//REST_BOARD 테이블에 저장된 게시글 목록을 검색하여 JSON 형식의 텍스트로 응답하는 요청 처리 메소드
	// => 게시글 목록을 페이징 처리하기 위해 요청 페이지 번호의 게시글 목록과 페이징 처리 관련 정보를 
	//Map 객체의 엔트리에 저장하여 JSON 형식의 텍스트로 응답되도록 반환
	@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> restBoardList(@RequestParam(defaultValue = "1") int pageNum) {
		//System.out.println("pageNum = "+pageNum);
		
		//REST_BOARD 테이블에 저장된 모든 게시글의 갯수를 검색하여 반환받아 저장
		int totalBoard=restBoardService.getRestBoardCount();
		int pageSize=5;//하나의 페이지에 출력될 게시글의 갯수 저장
		int blockSize=5;//하나의 페이지 블럭에 출력될 페이지 번호의 갯수 저장
		
		//페이징 처리 관련 값을 제공하는 Pager 클래스로 객체를 생성하여 저장
		Pager pager=new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		//RestBoardService 클래스의 메소드 호출을 위해 매개변수에 전달하기 위한 Map 객체 생성
		// => 요청 페이지의 시작 행번호와 종료 행번호를 Map 객체의 엔트리로 저장하여 SQL 명령에 제공
		Map<String, Object> pagerMap=new HashMap<String, Object>();
		pagerMap.put("startRow", pager.getStartRow());
		pagerMap.put("endRow", pager.getEndRow());
		
		//요청 처리 메소드의 반환값으로 사용될 Map 객체 생성
		// => 요청 페이지에 대한 게시글 목록과 페이징 처리 관련 정보를 Map 객체의 엔트리로 저장하여 반환
		Map<String, Object> returnMap=new HashMap<String, Object>();
		returnMap.put("restBoardList", restBoardService.getRestBoardList(pagerMap));
		returnMap.put("pager", pager);
		
		return returnMap;
	}
	
	//게시글을 전달받아 REST_BOARD 테이블에 삽입하여 저장하고 처리 결과를 일반 텍스트로 응답하는 요청 처리 메소드
	// => @RequestBody 어노테이션을 이용하여 모든 전달값을 [이름=값&이름=값&...] 형식으로 전달받아 매개변수에 저장
	// => 매개변수의 자료형이 JavaBean(DTO) 클래스인 경우 전달값은 같은 이름의 필드에 저장
	@RequestMapping(value = "/board_add", method = RequestMethod.POST)
	@ResponseBody
	public String restBoardAdd(@RequestBody RestBoard board) {
		//HtmlUtils.htmlEscape(String str) : 매개변수로 전달받은 문자열에서 HTML 태그 관련 문자를
		//Escape 문자로 변환하여 반환하는 메소드 - XSS 공격에 대한 방어
		board.setContent(HtmlUtils.htmlEscape(board.getContent()));
		restBoardService.addRestBoard(board);
		return "success";
	}
	
	/*
	//글번호를 전달받아 REST_BOARD 테이블에 저장된 해당 글번호의 게시글을 검색하여 JSON 형식의
	//텍스트로 응답하는 요청 처리 메소드 - QueryString를 이용하여 글번호 전달
	@RequestMapping(value = "/board_view", method = RequestMethod.GET)
	@ResponseBody
	public RestBoard restBoardView(@RequestParam int num) {
		return restBoardService.getRestBoard(num);
	}
	*/
	
	//글번호를 전달받아 REST_BOARD 테이블에 저장된 해당 글번호의 게시글을 검색하여 JSON 형식의
	//텍스트로 응답하는 요청 처리 메소드 - 요청 URL 주소를 이용하여 글번호 전달
	//@RequestMapping 어노테이션의 value 속성값으로 매핑된 URL 주소에 {이름} 형식으로 값을 표현하여 전달
	// => @PathVariable 어노테이션을 사용하여 URL 주소를 값으로 제공받아 매개변수에 저장
	// => 식별자를 전달받아 단일행을 검색하거나 삭제 처리시 사용
	@RequestMapping(value = "/board_view/{num}", method = RequestMethod.GET)
	@ResponseBody
	//@PathVariable : 요청 URL 주소로 표현된 값을 매개변수에 저장하는 어노테이션
	// => 요청 URL 주소로 표현된 이름과 매개변수의 이름이 같아야만 값을 전달받아 저장
	// => 요청 URL 주소로 표현된 이름과 매개변수의 이름이 다른 경우 value 속성값으로 URL 주소의 
	//이름을 설정하여 URL 주소로 표현된 값을 매개변수에 저장 가능 
	public RestBoard restBoardView(@PathVariable int num) {
		return restBoardService.getRestBoard(num);
	}
	
	//게시글을 전달받아 REST_BOARD 테이블에 저장된 게시글을 변경하고 처리 결과를 일반 텍스트로 응답하는 요청 처리 메소드
	// => 요청방식이 여러개인 경우 method 속성값을 배열로 작성하여 요소로 표현
	@RequestMapping(value = "/board_modify", method = {RequestMethod.PUT, RequestMethod.PATCH})
	@ResponseBody
	public String restBoardModify(@RequestBody RestBoard board) {
		restBoardService.modifyRestBoard(board);
		return "success";
	}
	
	//글번호를 전달받아 REST_BOARD 테이블에 저장된 해당 글번호의 게시글을 삭제하거 처리 결과를
	//일반 텍스트로 응답하는 요청 처리 메소드 - 요청 URL 주소를 이용하여 글번호 전달
	@RequestMapping(value = "/board_remove/{num}", method = RequestMethod.DELETE)
	@ResponseBody
	public String restBoardRemove(@PathVariable int num) {
		restBoardService.removeRestBoard(num);
		return "success";
	}
}