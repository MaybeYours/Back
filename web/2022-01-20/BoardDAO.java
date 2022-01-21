package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.BoardDTO;

public class BoardDAO extends JdbcDAO {
	private static BoardDAO _dao;
	
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new BoardDAO();
	}
	
	public static BoardDAO getDAO() {
		return _dao;
	}
	
	//BOARD 테이블에 저장된 전체 게시글의 갯수를 검색하여 반환하는 메소드
	public int selectBoardCount() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=getConnection();
			
			String sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectBoardCount 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}
	
	//시작 행번호와 종료 행번호를 전달받아 BOARD 테이블에 저장된 해당 행범위의 게시글을 검색하여 반환하는 메소드
	public List<BoardDTO> selectBoardList(int startRow, int endRow) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		try {
			con=getConnection();
			
			String sql="select * from (select rownum rn,temp.* from "
				+ "(select * from board order by ref desc,re_step) temp) where rn between ? and ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectBoardList 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return boardList;
	}
}













