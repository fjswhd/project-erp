package ch14.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;
public class BoardDao {
	// singleton
	private static BoardDao instance = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
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
	
	public List<SellerBoard> sellerList() {
		List<SellerBoard> sellerList = new ArrayList<SellerBoard>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select * from seller order by seller_no)"
				+ "where rownum between 1 and 10"; 						
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SellerBoard board = new SellerBoard();
				board.setSeller_no(rs.getString("seller_no"));
				board.setSeller_reg_num(rs.getString("seller_reg_num"));
				board.setSeller_name(rs.getString("seller_name"));
				board.setSeller_tel(rs.getString("seller_tel"));
				board.setSeller_address(rs.getString("seller_address"));
				board.setEmp_no(rs.getString("emp_no"));
				board.setSeller_email(rs.getString("seller_email"));
				board.setSeller_memo(rs.getString("seller_memo"));
				board.setSeller_del(rs.getString("seller_del"));
				
				sellerList.add(board);
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
		return sellerList;
	}
	
	public void setSellerNo(SellerBoard board) {
		String sellerNO="";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select count(*) from seller";
		try {			
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery(sql);
			
			if(rs.next()) {
			sellerNO = "S" + (rs.getInt(1) + 1);
			board.setSeller_no(sellerNO);
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
	}
			
	public int insert(SellerBoard board) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="insert into seller values(?,?,?,?,?,?,?,?,'n')";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSeller_no());
			pstmt.setString(2, board.getSeller_name());
			pstmt.setString(3, board.getSeller_reg_num());
			pstmt.setString(4, board.getSeller_tel());
			pstmt.setString(5, board.getSeller_email());
			pstmt.setString(6, board.getSeller_address());
			pstmt.setString(7, board.getEmp_no());
			pstmt.setString(8, board.getSeller_memo());
			
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
	
	public SellerBoard select(String seller_no) {
		SellerBoard board = new SellerBoard();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from seller where seller_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seller_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setSeller_no(rs.getString("seller_no"));
				board.setSeller_name(rs.getString("seller_name"));
				board.setSeller_reg_num(rs.getString("seller_reg_num"));
				board.setSeller_tel(rs.getString("seller_tel"));
				board.setSeller_email(rs.getString("seller_email"));
				board.setSeller_address(rs.getString("seller_address"));
				board.setEmp_no(rs.getString("emp_no"));
				board.setSeller_memo(rs.getString("seller_memo"));
				board.setSeller_del(rs.getString("seller_del"));
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
	
	public int update(SellerBoard board) { // board 화면에서 입력한 게시글
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql="update seller set seller_no=?,seller_name=? ,seller_reg_num=? "
				+ ",seller_tel=? ,seller_email=? ,seller_address=? ,emp_no=? ,seller_memo=? "
				+ "where seller_no=?";
		SellerBoard bd = select(board.getSeller_no()); // bd은 num으로 읽은 게시글
		if (bd.getSeller_no().equals(board.getSeller_no())) { // 화면에서 읽은 암호와 읽은 게시글의 암호와 비교
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getSeller_no());
				pstmt.setString(2, board.getSeller_name());
				pstmt.setString(3, board.getSeller_reg_num());
				pstmt.setString(4, board.getSeller_tel());
				pstmt.setString(5, board.getSeller_email());
				pstmt.setString(6, board.getSeller_address());
				pstmt.setString(7, board.getEmp_no());
				pstmt.setString(8, board.getSeller_memo());
				pstmt.setString(9, board.getSeller_no());				
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
	
	public int delete(String seller_no) { // board 화면에서 입력한 게시글
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		String sql="update seller set seller_del='y' where seller_no=?";
		SellerBoard bd = select(seller_no); // bd은 num으로 읽은 게시글
		if (seller_no.equals(bd.getSeller_no())) { // 화면에서 읽은 암호와 읽은 게시글의 암호와 비교
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,seller_no);				
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
		String sql = "select count(*) from board";
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