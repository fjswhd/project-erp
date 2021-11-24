package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Product;
public class ProductBoardDao {
	// singleton
	private static ProductBoardDao instance = new ProductBoardDao();
	private ProductBoardDao() {}
	public static ProductBoardDao getInstance() {
		return instance;
	}
	// DataBase Connection Pool
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("연결 에러 : "+e.getMessage());
		}
		return conn;
	} 
	
	public List<Product> productList(int startRow, int endRow) {
		List<Product> productList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn,a.* from "
				+ "(select * from product order by product_no) a)"				
				+ "where rn between ? and ?"; 						
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product board = new Product();
				board.setProduct_no(rs.getInt("product_no"));
				board.setProduct_name(rs.getString("product_name"));
				board.setPrice(rs.getInt("price"));
				board.setCost(rs.getInt("cost"));
				board.setStock(rs.getInt("stock"));
				board.setProduct_memo(rs.getString("product_memo"));
				
				productList.add(board);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return productList;
	}
			
	public int insert(Product board) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="insert into product values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getProduct_no());
			pstmt.setString(2, board.getProduct_name());
			pstmt.setInt(3, board.getPrice());
			pstmt.setInt(4, board.getCost());
			pstmt.setInt(5, board.getStock());
			pstmt.setString(6, board.getProduct_memo());
			
			result = pstmt.executeUpdate();			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	
	public Product select(int product_no) {
		Product board = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from product where product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setProduct_no(rs.getInt("product_no"));
				board.setProduct_name(rs.getString("product_name"));
				board.setPrice(rs.getInt("price"));
				board.setCost(rs.getInt("cost"));
				board.setStock(rs.getInt("stock"));
				board.setProduct_memo(rs.getString("product_memo"));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return board;
	}
	
	public int update(Product board) { // board 화면에서 입력한 게시글
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql="update product "
				+ "set product_no=?, "
				+ "product_name=?, "
				+ "price=?, "
				+ "cost=?, "
				+ "stock=?, "
				+ "product_memo=? "
				+ "where product_no=?";
		Product bd = select(board.getProduct_no()); // bd은 num으로 읽은 게시글
		if (bd.getProduct_no() == (board.getProduct_no())) { // 화면에서 읽은 암호와 읽은 게시글의 암호와 비교
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board.getProduct_no());
				pstmt.setString(2, board.getProduct_name());
				pstmt.setInt(3, board.getPrice());
				pstmt.setInt(4, board.getCost());
				pstmt.setInt(5, board.getStock());
				pstmt.setString(6, board.getProduct_memo());	
				pstmt.setInt(7, board.getProduct_no());
				result = pstmt.executeUpdate();			
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null)  conn.close();
				}catch (Exception e) {		}
			}
		} else result = -1;
		return result;
	}
	
	public int getTotal() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from product";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return total;
	}
}