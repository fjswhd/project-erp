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
import model.SearchOption;
public class ProductDao {
	// singleton
	private static ProductDao instance = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
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
				Product product = new Product();
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setCost(rs.getInt("cost"));
				product.setStock(rs.getInt("stock"));
				product.setProduct_memo(rs.getString("product_memo"));
				
				productList.add(product);
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
			
	public int insertProduct(Product product) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		//상품코드, 상품명, 판매가, 매입가, 현재 재고량, 상품메모
		String sql="insert into product values(?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, product.getProduct_no());
			pstmt.setString	(2, product.getProduct_name());
			pstmt.setInt	(3, product.getPrice());
			pstmt.setInt	(4, product.getCost());
			pstmt.setInt	(5, product.getStock());
			pstmt.setString	(6, product.getProduct_memo());
			
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
	
	public Product selectProduct(int product_no) {
		Product product = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from product where product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setCost(rs.getInt("cost"));
				product.setStock(rs.getInt("stock"));
				product.setProduct_memo(rs.getString("product_memo"));
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
		return product;
	}
	
	//
	public int updateProduct(Product product) { // product 화면에서 입력한 게시글
		int result = 0;
		
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		
		String sql="update product "
				+ "set "
				+ "product_name=?, "
				+ "price=?, "
				+ "cost=?, "
				+ "stock=?, "
				+ "product_memo=? "
				+ "where product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString	(1, product.getProduct_name());
			pstmt.setInt	(2, product.getPrice());
			pstmt.setInt	(3, product.getCost());
			pstmt.setInt	(4, product.getStock());
			pstmt.setString	(5, product.getProduct_memo());	
			pstmt.setInt	(6, product.getProduct_no());
			
			result = pstmt.executeUpdate();			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result;
	}
	
	public int getTotalProduct() {
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
	public List<Product> productList() {
		List<Product> productList = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = "select * from product order by product_no"; 						
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setCost(rs.getInt("cost"));
				product.setStock(rs.getInt("stock"));
				product.setProduct_memo(rs.getString("product_memo"));
				
				productList.add(product);
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
	public List<Product> searchProduct(SearchOption options) {
		List<Product> searchList = new ArrayList<Product>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = "select * from product "
				+ "where "+options.getSearchField()+" like '%'||'"+options.getKeyword()+"'||'%' "
				+ "order by product_no";					
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setCost(rs.getInt("cost"));
				product.setStock(rs.getInt("stock"));
				product.setProduct_memo(rs.getString("product_memo"));
				
				searchList.add(product);
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
		return searchList;
	}
}