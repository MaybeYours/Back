package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementApp2 {

	public static void main(String[] args) throws IOException, SQLException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("<<�л����� �����>>");
		System.out.println("�л��̸� �Է�>> ");
		String name = in.readLine();
		
		Connection con = ConnectionFactory2.getConnection();
		
		
		String sql = "select * from student where name = ? order by no";
		PreparedStatement  pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("<<�˻���� ���>>");
		if(rs.next()) {
			do {
				System.out.println("�й� = "+rs.getInt("no")+"�̸� = "+rs.getString("name"));
			} while (rs.next());
			
			
		}else {
			System.out.println("ã�� ����� ����?");
		}
		ConnectionFactory2.close(con, pstmt, rs);
	}
}