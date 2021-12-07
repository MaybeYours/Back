package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//�ټ��� ���� �����ϰų� �����ϰ��� �� ��� Batch  ������ SQL ����� �����Ͽ� �Ѳ����� SQL����� ���ʴ�� �����Ͽ� �����ϴ� ��� - SQL ����� �ϰ�ó��
public class AddBatchApp {
	public static void main(String[] args) throws SQLException{
		Connection con = ConnectionFactory2.getConnection();
		
		String sql ="insert into student values(? ,? ,? ,? ,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, 7000);
		pstmt.setString(2, "ȫ�淡");
		pstmt.setString(3, "010-2222-2222");
		pstmt.setString(4, "����� ������");
		pstmt.setString(5, "1996-05-07");
		
		//PreparedStatement.addBatch() : PreparedStatement �ν��Ͻ��� ����� SQL����� �ϰ�ó�� ������ �߰��ϴ� �޼ҵ� 
		pstmt.addBatch();
		
		
		pstmt.setInt(1, 8000);
		pstmt.setString(2, "�κ���");
		pstmt.setString(3, "010-3333-3333");
		pstmt.setString(4, "����� ���Ǳ�");
		pstmt.setString(5, "1999-12-07");
		pstmt.addBatch();
		
		//PreparedStatement.executeBatch : �ϰ�ó�� ������ �߰��� ��� SQL ����� ���ӵ� DBMS ������ �����Ͽ� �����ϴ� �޼ҵ� -int[] �� ��ȯ�Ѵ�.
		int[] rows = pstmt.executeBatch();
	
		System.out.println("[���]"+rows.length+"���� �л������� �����Ͽ����ϴ�.");
		
		ConnectionFactory2.close(con, pstmt);
	}
	
}
