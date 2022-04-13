package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.ProductDTO;

public class ProductDAO extends JdbcDAO {
	private static ProductDAO _dao;
	
	private ProductDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new ProductDAO();
	}
	
	public static ProductDAO getDAO() {
		return _dao;
	}
	
	//카테고리를 전달받아 PRODUCT 테이블에 저장된 해당 카테고리의 제품정보를 검색하여 반환하는 메소드
	public List<ProductDTO> selectCategoryProductList(String category) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ProductDTO> productList=new ArrayList<ProductDTO>();
		try {
			con=getConnection();
			
			if(category.equals("ALL")) {//모든 제품정보를 검색할 경우
				String sql="select * from product order by num";
				pstmt=con.prepareStatement(sql);
			} else {
				String sql="select * from product where category=? order by num";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, category);	
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO product=new ProductDTO();
				product.setNum(rs.getInt("num"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setDetail(rs.getString("detail"));
				product.setQty(rs.getInt("qty"));
				product.setPrice(rs.getInt("price"));
				productList.add(product);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectCategoryProductList 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return productList;
	}
	
	//제품정보를 전달받아 PRODUCT 테이블에 삽입하여 저장하고 삽입행의 갯수를 반환하는 메소드
	public int insertProduct(ProductDTO product) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into product values(product_seq.nextval,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product.getCategory());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getImage());
			pstmt.setString(4, product.getDetail());
			pstmt.setInt(5, product.getQty());
			pstmt.setInt(6, product.getPrice());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertProduct 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//제품번호를 전달받아 PRODUCT 테이블에 저장된 해당 제품번호의 제품정보를 검색하여 반환하는 메소드
	public ProductDTO selectNumProduct(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ProductDTO product=null;
		try {
			con=getConnection();
			
			String sql="select * from product where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				product=new ProductDTO();
				product.setNum(rs.getInt("num"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("name"));
				product.setImage(rs.getString("image"));
				product.setDetail(rs.getString("detail"));
				product.setQty(rs.getInt("qty"));
				product.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectNumProduct 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return product;
	}
	
	//제품정보를 전달받아 PRODUCT 테이블에 저장된 제품정보를 변경하고 변경행의 갯수를 반환하는 메소드
	public int updateProduct(ProductDTO product) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update product set category=?,name=?,image=?,detail=?,qty=?,price=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, product.getCategory());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getImage());
			pstmt.setString(4, product.getDetail());
			pstmt.setInt(5, product.getQty());
			pstmt.setInt(6, product.getPrice());
			pstmt.setInt(7, product.getNum());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateProduct 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//제품번호를 전달받아 PRODUCT 테이블에 저장된 해당 제품번호의 제품정보를 삭제하고 
	//삭제행의 갯수를 반환하는 메소드
	public int deleteProduct(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="delete from product where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteProduct 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
}








