package xyz.itwill.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import xyz.itwill.dto.MyReply;
import xyz.itwill.dto.MyReplyUser;
import xyz.itwill.mapper.MyReplyMapper;

public class MyReplyDAO extends AbstractSession {
	private static MyReplyDAO _dao;
	
	private MyReplyDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MyReplyDAO();
	}
	
	public static MyReplyDAO getDAO() {
		return _dao;
	}
	
	public int insertReply(MyReply reply) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyReplyMapper.class).insertReply(reply);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<MyReply> selectReplyList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyReplyMapper.class).selectReplyList();
		} finally {
			sqlSession.close();
		}	
	}
	
	public List<MyReply> selectReplyCountList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyReplyMapper.class).selectReplyCountList();
		} finally {
			sqlSession.close();
		}	
	}
	
	public List<MyReplyUser> selectReplyUserList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyReplyMapper.class).selectReplyUserList();
		} finally {
			sqlSession.close();
		}	
	}
}
