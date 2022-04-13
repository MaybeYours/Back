package xyz.itwill.service;

import java.sql.SQLException;
import java.util.List;

import xyz.itwill.dao.UserinfoModelTwoDAO;
import xyz.itwill.dto.UserinfoDTO;
import xyz.itwill.exception.ExistsUserinfoException;
import xyz.itwill.exception.PasswordMissMatchException;
import xyz.itwill.exception.UserinfoNotFoundException;

//Service 클래스 : 모델 클래스의 요청 처리 메소드에게 처리 기능을 제공하기 위한 클래스
// => 컴퍼넌트 클래스 - 단위 프로그램(모듈)
// => DAO 클래스의 메소드를 호출하여 웹프로그램 요청에 대한 처리 기능을 메소드로 제공
public class UserinfoService {
	private static UserinfoService _service;
	
	private UserinfoService() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_service=new UserinfoService();
	}
	
	public static UserinfoService getService() {
		return _service;
	}
	
	//회원정보를 전달받아 USERINFO 테이블에 저장하는 메소드
	// => 전달받은 회원정보의 아이디가 USERINFO 테이블에 저장된 회원정보의 아이디와 중복될 경우 인위적 예외 발생
	public void addUserinfo(UserinfoDTO userinfo) throws SQLException, ExistsUserinfoException {
		if(UserinfoModelTwoDAO.getDAO().selectUserinfo(userinfo.getUserid())!=null) {
			//인위적 예외 발생 - 예외가 발생된 경우 메소드 강제 종료
			throw new ExistsUserinfoException("이미 사용중인 아이디를 입력 하였습니다.");
		}
		UserinfoModelTwoDAO.getDAO().insertUserinfo(userinfo);
	}
	
	//회원정보를 전달받아 USERINFO 테이블에 저장된 회원정보를 변경하는 메소드
	// => 전달받은 회원정보가 USERINFO 테이블에 없는 경우 인위적 예외 발생
	public void modifyUserinfo(UserinfoDTO userinfo) throws SQLException, UserinfoNotFoundException {
		/*
		if(UserinfoModelTwoDAO.getDAO().selectUserinfo(userinfo.getUserid())==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		*/
		getUserinfo(userinfo.getUserid());
		
		UserinfoModelTwoDAO.getDAO().updateUserinfo(userinfo);
	}
	
	//아이디를 전달받아 USERINFO 테이블에 저장된 회원정보를 삭제하는 메소드
	// => 전달받은 아이디의 회원정보가 USERINFO 테이블에 없는 경우 인위적 예외 발생
	public void removeUserinfo(String userid) throws SQLException, UserinfoNotFoundException {
		/*
		if(UserinfoModelTwoDAO.getDAO().selectUserinfo(userid)==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		*/
		getUserinfo(userid);
		
		UserinfoModelTwoDAO.getDAO().deleteUserinfo(userid);
	}
	
	//아이디를 전달받아 USERINFO 테이블에 저장된 해당 아이디의 회원정보를 검색하여 반환하는 메소드
	// => 전달받은 아이디의 회원정보가 USERINFO 테이블에 없는 경우 인위적 예외 발생
	public UserinfoDTO getUserinfo(String userid) throws SQLException, UserinfoNotFoundException {
		UserinfoDTO userinfo=UserinfoModelTwoDAO.getDAO().selectUserinfo(userid);
		if(userinfo==null) {
			throw new UserinfoNotFoundException("아이디의 회원정보가 존재하지 않습니다.");
		}
		return userinfo;
	}
	
	//USERINFO 테이블에 저장된 모든 회원정보를 검색하여 반환하는 메소드
	public List<UserinfoDTO> getUserinfoList() throws SQLException {
		return UserinfoModelTwoDAO.getDAO().selectUserinfoList();
	}
	
	//아이디와 비밀번호를 전달받아 USERINFO 테이블에 저장된 회원정보와 비교하여 인증 처리하는 메소드
	// => 인증이 실패되면 인위적 예외 발생 - 예외가 발생되지 않은 경우 인증 성공
	public void auth(String userid, String password) throws SQLException, UserinfoNotFoundException, PasswordMissMatchException {
		//아이디에 대한 인증 처리
		// => 전달된 아이디에 대한 회원정보가 USERINFO 테이블에 없는 경우 인위적 예외 발생
		// => 아이디 인증 실패 : UserinfoNotFoundException 발생
		UserinfoDTO userinfo=getUserinfo(userid);
		
		//비밀번호에 대한 인증 처리
		// => 전달받은 비밀번호가 검색된 회원정보의 비밀번호와 같지 않은 경우 인위적 예외 발생
		// => 비밀번호 인증 실패 : PasswordMissMatchException 발생
		if(!password.equals(userinfo.getPassword())) {
			throw new PasswordMissMatchException("입력된 아이디가 잘못 되었거나 비밀번호가 맞지 않습니다.");
		}
	}
}











