package xyz.itwill10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.itwill10.dao.PointBoardDAO;
import xyz.itwill10.dao.PointUserDAO;
import xyz.itwill10.dto.PointBoard;
import xyz.itwill10.dto.PointUser;

@Service
public class PointBoardServiceImpl implements PointBoardService {
	@Autowired
	private PointUserDAO pointUserDAO;
	
	@Autowired
	private PointBoardDAO pointBoardDAO;

	//�Խñ� ������ ���޹޾� POINTBOARD ���̺� �����Ͽ� �����ϰ� �Խñ� �ۼ��� ������ �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	// => �Խñ� �ۼ��ڸ� �����Ͽ� POINTUSER ���̺� ����� ������� �����Ͱ� �����ǵ��� �ۼ�
	@Override
	public PointUser addPointBoard(PointBoard board) {
		pointBoardDAO.insertPointBoard(board);
		
		//�Խñ� �ۼ��ڰ� POINTUSER ���̺� ����� ����ڰ� �̴� ��� ������ ���� �߻�
		if(pointUserDAO.selectPointUser(board.getWriter())==null) {
			throw new RuntimeException("�Խñ� �ۼ��ڰ� �������� �ʽ��ϴ�.");
		}
		
		pointUserDAO.updatePlusPointUser(board.getWriter());
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//�Խñ� ��ȣ�� ���޹޾� POINTBOARD ���̺� ����� �Խñ� �����ϰ� ������ �Խñ��� �ۼ��� ������ �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	// => ������ �Խñ��� �ۼ��ڸ� �����Ͽ� POINTUSER ���̺� ����� ������� �����Ͱ� ���ҵǵ��� �ۼ�
	@Override
	public PointUser erasePointBoard(int num) {
		PointBoard board=pointBoardDAO.selectPointBoard(num);
		//���� ó���� �Խñ��� POINTBOARD ���̺� ����Ǿ� ���� ���� ��� ������ ���� �߻�
		if(board==null) {
			throw new RuntimeException("�Խñ��� �������� �ʽ��ϴ�.");
		}
		
		pointBoardDAO.deletePointBoard(num);
		pointUserDAO.updateMinusPointUser(board.getWriter());
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//POINTBOARD ���̺� ����� ��� �Խñ� ������ ������ �˻��Ͽ� ��ȯ�ϴ� �޼ҵ�
	@Override
	public List<PointBoard> getPointBoardList() {
		return pointBoardDAO.selectPointBoardList();
	}
}
