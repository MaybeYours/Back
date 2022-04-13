package xyz.itwill10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.itwill10.dao.PointUserDAO;
import xyz.itwill10.dto.PointUser;

@Service
public class PointUserServiceImpl implements PointUserService {
	@Autowired
	private PointUserDAO pointUserDAO;

	//사용자 정보를 전달받아 POINTUSER 테이블에 삽입하여 저장하고 저장된 사용자 정보를
	//검색하여 반환하는 메소드
	@Override
	public PointUser addPointUser(PointUser user) {
		if(pointUserDAO.selectPointUser(user.getId())!=null) {//아이디 중복
			throw new RuntimeException("이미 사용중인 아이디입니다.");
		}
		pointUserDAO.insertPointUser(user);
		return pointUserDAO.selectPointUser(user.getId());
	}
	
	
}
