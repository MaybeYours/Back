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

//REST ����� �����ϴ� �����α׷��� ��û ó�� �޼ҵ��� ��ȯ��(������)�� Ȯ���ϱ� ���� 
//Advanced REST Client ũ�Ҿ��� ��ġ�Ͽ� REST ���񽺸� �����ϴ� �����α׷� �׽�Ʈ ����

//AJAX ����� �̿��Ͽ� �����α׷� ��û�� ���� ��û ���
// => ��û ó�� �޼ҵ��� @RequestMapping ������̼��� method �Ӽ���
// => GET(�˻�), POST(����), PUT(��ü ����), PATCH(�κ� ����), DELETE(����) ��

//@RestController : Controller Ŭ������ Spring Bean���� ����ϴ� ������̼�  
// => ��� ��û ó�� �޼ҵ��� ��ȯ������ ����ǵ��� �����ϴ� ������̼����� ��û ó�� �޼ҵ忡�� 
//@ResponseBody ������̼��� ������� �ʾƵ� �޼ҵ� ��ȯ������ ���� ó��
// => Rest ���񽺸� �����ϴ� Controller Ŭ������ �ۼ��� ��� ���
//@RestController
@Controller
public class RestBoardController {
	@Autowired
	private RestBoardService restBoardService;
	
	@RequestMapping("/board")
	public String restBoard() {
		return "rest/board";
	}
	
	//REST_BOARD ���̺� ����� �Խñ� ����� �˻��Ͽ� JSON ������ �ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ�
	// => �Խñ� ����� ����¡ ó���ϱ� ���� ��û ������ ��ȣ�� �Խñ� ��ϰ� ����¡ ó�� ���� ������ 
	//Map ��ü�� ��Ʈ���� �����Ͽ� JSON ������ �ؽ�Ʈ�� ����ǵ��� ��ȯ
	@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> restBoardList(@RequestParam(defaultValue = "1") int pageNum) {
		//System.out.println("pageNum = "+pageNum);
		
		//REST_BOARD ���̺� ����� ��� �Խñ��� ������ �˻��Ͽ� ��ȯ�޾� ����
		int totalBoard=restBoardService.getRestBoardCount();
		int pageSize=5;//�ϳ��� �������� ��µ� �Խñ��� ���� ����
		int blockSize=5;//�ϳ��� ������ ���� ��µ� ������ ��ȣ�� ���� ����
		
		//����¡ ó�� ���� ���� �����ϴ� Pager Ŭ������ ��ü�� �����Ͽ� ����
		Pager pager=new Pager(pageNum, totalBoard, pageSize, blockSize);
		
		//RestBoardService Ŭ������ �޼ҵ� ȣ���� ���� �Ű������� �����ϱ� ���� Map ��ü ����
		// => ��û �������� ���� ���ȣ�� ���� ���ȣ�� Map ��ü�� ��Ʈ���� �����Ͽ� SQL ��ɿ� ����
		Map<String, Object> pagerMap=new HashMap<String, Object>();
		pagerMap.put("startRow", pager.getStartRow());
		pagerMap.put("endRow", pager.getEndRow());
		
		//��û ó�� �޼ҵ��� ��ȯ������ ���� Map ��ü ����
		// => ��û �������� ���� �Խñ� ��ϰ� ����¡ ó�� ���� ������ Map ��ü�� ��Ʈ���� �����Ͽ� ��ȯ
		Map<String, Object> returnMap=new HashMap<String, Object>();
		returnMap.put("restBoardList", restBoardService.getRestBoardList(pagerMap));
		returnMap.put("pager", pager);
		
		return returnMap;
	}
	
	//�Խñ��� ���޹޾� REST_BOARD ���̺� �����Ͽ� �����ϰ� ó�� ����� �Ϲ� �ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ�
	// => @RequestBody ������̼��� �̿��Ͽ� ��� ���ް��� [�̸�=��&�̸�=��&...] �������� ���޹޾� �Ű������� ����
	// => �Ű������� �ڷ����� JavaBean(DTO) Ŭ������ ��� ���ް��� ���� �̸��� �ʵ忡 ����
	@RequestMapping(value = "/board_add", method = RequestMethod.POST)
	@ResponseBody
	public String restBoardAdd(@RequestBody RestBoard board) {
		//HtmlUtils.htmlEscape(String str) : �Ű������� ���޹��� ���ڿ����� HTML �±� ���� ���ڸ�
		//Escape ���ڷ� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ� - XSS ���ݿ� ���� ���
		board.setContent(HtmlUtils.htmlEscape(board.getContent()));
		restBoardService.addRestBoard(board);
		return "success";
	}
	
	/*
	//�۹�ȣ�� ���޹޾� REST_BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ��� �˻��Ͽ� JSON ������
	//�ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ� - QueryString�� �̿��Ͽ� �۹�ȣ ����
	@RequestMapping(value = "/board_view", method = RequestMethod.GET)
	@ResponseBody
	public RestBoard restBoardView(@RequestParam int num) {
		return restBoardService.getRestBoard(num);
	}
	*/
	
	//�۹�ȣ�� ���޹޾� REST_BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ��� �˻��Ͽ� JSON ������
	//�ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ� - ��û URL �ּҸ� �̿��Ͽ� �۹�ȣ ����
	//@RequestMapping ������̼��� value �Ӽ������� ���ε� URL �ּҿ� {�̸�} �������� ���� ǥ���Ͽ� ����
	// => @PathVariable ������̼��� ����Ͽ� URL �ּҸ� ������ �����޾� �Ű������� ����
	// => �ĺ��ڸ� ���޹޾� �������� �˻��ϰų� ���� ó���� ���
	@RequestMapping(value = "/board_view/{num}", method = RequestMethod.GET)
	@ResponseBody
	//@PathVariable : ��û URL �ּҷ� ǥ���� ���� �Ű������� �����ϴ� ������̼�
	// => ��û URL �ּҷ� ǥ���� �̸��� �Ű������� �̸��� ���ƾ߸� ���� ���޹޾� ����
	// => ��û URL �ּҷ� ǥ���� �̸��� �Ű������� �̸��� �ٸ� ��� value �Ӽ������� URL �ּ��� 
	//�̸��� �����Ͽ� URL �ּҷ� ǥ���� ���� �Ű������� ���� ���� 
	public RestBoard restBoardView(@PathVariable int num) {
		return restBoardService.getRestBoard(num);
	}
	
	//�Խñ��� ���޹޾� REST_BOARD ���̺� ����� �Խñ��� �����ϰ� ó�� ����� �Ϲ� �ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ�
	// => ��û����� �������� ��� method �Ӽ����� �迭�� �ۼ��Ͽ� ��ҷ� ǥ��
	@RequestMapping(value = "/board_modify", method = {RequestMethod.PUT, RequestMethod.PATCH})
	@ResponseBody
	public String restBoardModify(@RequestBody RestBoard board) {
		restBoardService.modifyRestBoard(board);
		return "success";
	}
	
	//�۹�ȣ�� ���޹޾� REST_BOARD ���̺� ����� �ش� �۹�ȣ�� �Խñ��� �����ϰ� ó�� �����
	//�Ϲ� �ؽ�Ʈ�� �����ϴ� ��û ó�� �޼ҵ� - ��û URL �ּҸ� �̿��Ͽ� �۹�ȣ ����
	@RequestMapping(value = "/board_remove/{num}", method = RequestMethod.DELETE)
	@ResponseBody
	public String restBoardRemove(@PathVariable int num) {
		restBoardService.removeRestBoard(num);
		return "success";
	}
}