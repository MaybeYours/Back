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

	//게시글 정보를 전달받아 POINTBOARD 테이블에 삽입하여 저장하고 게시글 작성자 정보를 검색하여 반환하는 메소드
	// => 게시글 작성자를 전달하여 POINTUSER 테이블에 저장된 사용자의 포인터가 증가되도록 작성
	@Override
	public PointUser addPointBoard(PointBoard board) {
		pointBoardDAO.insertPointBoard(board);
		
		//게시글 작성자가 POINTUSER 테이블에 저장된 사용자가 이닌 경우 인위적 예외 발생
		if(pointUserDAO.selectPointUser(board.getWriter())==null) {
			throw new RuntimeException("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updatePlusPointUser(board.getWriter());
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//게시글 번호를 전달받아 POINTBOARD 테이블에 저장된 게시글 삭제하고 삭제된 게시글의 작성자 정보를 검색하여 반환하는 메소드
	// => 삭제된 게시글의 작성자를 전달하여 POINTUSER 테이블에 저장된 사용자의 포인터가 감소되도록 작성
	@Override
	public PointUser erasePointBoard(int num) {
		PointBoard board=pointBoardDAO.selectPointBoard(num);
		//삭제 처리될 게시글이 POINTBOARD 테이블에 저장되어 있지 않은 경우 인위적 예외 발생
		if(board==null) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		
		pointBoardDAO.deletePointBoard(num);
		pointUserDAO.updateMinusPointUser(board.getWriter());
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//POINTBOARD 테이블에 저장된 모든 게시글 정보를 정보를 검색하여 반환하는 메소드
	@Override
	public List<PointBoard> getPointBoardList() {
		return pointBoardDAO.selectPointBoardList();
	}
}
