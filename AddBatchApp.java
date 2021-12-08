package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//다수의 행을 삽입하거나 변경하고자 할 경우 Batch  영역에 SQL 명령을 저장하여 한꺼번에 SQL명령을 차례대로 전달하여 실행하는 방법 - SQL 명령의 일괄처리
public class AddBatchApp {
	public static void main(String[] args) throws SQLException{
		Connection con = ConnectionFactory2.getConnection();
		
		String sql ="insert into student values(? ,? ,? ,? ,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
	
		pstmt.setInt(1, 7000);
		pstmt.setString(2, "홍경래");
		pstmt.setString(3, "010-2222-2222");
		pstmt.setString(4, "서울시 도봉구");
		pstmt.setString(5, "1996-05-07");
		
		//PreparedStatement.addBatch() : PreparedStatement 인스턴스에 저장된 SQL명령을 일괄처리 영역에 추가하는 메소드 
		pstmt.addBatch();
		
		
		pstmt.setInt(1, 8000);
		pstmt.setString(2, "로빈훗");
		pstmt.setString(3, "010-3333-3333");
		pstmt.setString(4, "서울시 관악구");
		pstmt.setString(5, "1999-12-07");
		pstmt.addBatch();
		
		//PreparedStatement.executeBatch : 일괄처리 영역에 추가된 모든 SQL 명령을 접속된 DBMS 서버에 전달하여 실행하는 메소드 -int[] 을 반환한다.
		int[] rows = pstmt.executeBatch();
	
		System.out.println("[결과]"+rows.length+"명의 학생저보를 저장하였습니다.");
		
		ConnectionFactory2.close(con, pstmt);
	}
	
}
