package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Seller;

public class PurchaseDao {
	//singleton
	private static PurchaseDao instance = new PurchaseDao();
	private PurchaseDao() { }
	public static PurchaseDao getInstance() { 
		return instance;
	}
	// DataBase Connection Pool
	private Connection getConnection() {	
		Connection conn=null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch (Exception e) {
			System.out.println("연결 에러 : "+e.getMessage());
		}
		return conn;
	}
	
	public int getTotalSeller() {
		int total = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = "select count(*) from seller where seller_del='n'";
		
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
	
	public List<Seller> sellerList(int startRow, int endRow) {
		List<Seller> sellerList = new ArrayList<Seller>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select * from (select rowNum rn,a.* from "
				+ "(select * from seller order by seller_no) a)"				
				+ "where rn between ? and ?"; 						
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Seller board = new Seller();
				board.setSeller_no			(rs.getString("seller_no"));
				board.setSeller_reg_num		(rs.getString("seller_reg_num"));
				board.setSeller_name		(rs.getString("seller_name"));
				board.setSeller_tel			(rs.getString("seller_tel"));
				board.setSeller_addr_no		(rs.getString("seller_addr_no"));
				board.setSeller_addr		(rs.getString("seller_addr"));
				board.setSeller_addr_detail	(rs.getString("seller_addr_detail"));
				board.setEmp_no				(rs.getString("emp_no"));
				board.setSeller_email		(rs.getString("seller_email"));
				board.setSeller_memo		(rs.getString("seller_memo"));
				board.setSeller_del			(rs.getString("seller_del"));
				
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
}
