package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import xyz.itwill.dto.MyHewon;
import xyz.itwill.mapper.MyHewonMapper;

public class MyHewonDAO extends AbstractSession {
	private static MyHewonDAO _dao;
	
	private MyHewonDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new MyHewonDAO();
	}
	
	public static MyHewonDAO getDAO() {
		return _dao;
	}
	
	public int insertHewon(MyHewon hewon) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).insertHewon(hewon);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<MyHewon> selectHewonList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectHewonList();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<MyHewon> selectDiscriminatorHewonList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectDiscriminatorHewonList();
		} finally {
			sqlSession.close();
		}
	}
	
	public List<MyHewon> selectStateHewonList(int state) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectStateHewonList(state);
		} finally {
			sqlSession.close();
		}
	}
	
	public String selectBeanHewonId(MyHewon hewon) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectBeanHewonId(hewon);
		} finally {
			sqlSession.close();
		}
	}

	public String selectMapHewonId(Map<String, Object> map) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectMapHewonId(map);
		} finally {
			sqlSession.close();
		}
	}
	
	public int insertMapHewon(Map<String, Object> map) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).insertMapHewon(map);
		} finally {
			sqlSession.close();
		}
	}

	public List<Map<String, Object>> selectMapHewonList() {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectMapHewonList();
		} finally {
			sqlSession.close();
		}
	}
	
	public String selectParamHewonId(String name, String phone) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectParamHewonId(name, phone);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<MyHewon> selectSearchHewonList(Map<String, Object> map) {
		SqlSession sqlSession=getSqlSessionFactory().openSession(true);
		try {
			return sqlSession.getMapper(MyHewonMapper.class).selectSearchHewonList(map);
		} finally {
			sqlSession.close();
		}
	}
}