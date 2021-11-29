package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class OutProductDao {
	
	private static OutProductDao instance = new OutProductDao();

	private OutProductDao() {}

	public static OutProductDao getInstance() {
		return instance;
	}

	// DataBase Connection Pool
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("연결 에러 : " + e.getMessage());
		}
		return conn;
	}

	public List<OutProduct> outProductList(int startRow, int endRow) {
		List<OutProduct> outProductList = new ArrayList<OutProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from (select rowNum rn, a.* from" 
				+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
				+ " from sales_order so, customer c, product p, sales_order_detail sod"
				+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
				+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no)a )"
				+  "where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OutProduct outProduct = new OutProduct();
				
				outProduct.setSales_order_date(rs.getDate("sales_order_date"));
				outProduct.setCustomer_no(rs.getString("customer_no"));
				outProduct.setCustomer_name(rs.getString("customer_name"));
				outProduct.setProduct_no(rs.getInt("product_no"));
				outProduct.setProduct_name(rs.getString("product_name"));
				outProduct.setPrice(rs.getInt("price"));
				outProduct.setSales_detail_pcount(rs.getInt("sales_detail_pcount"));
				
				
				outProductList.add(outProduct);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return outProductList;
	}
	
	
	
	public int getTotal() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from"
				+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
				+ " from sales_order so, customer c, product p, sales_order_detail sod"
				+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
				+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no)";
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
	
		
	
	public List<OutProduct> getSearch(int startRow, int endRow, String s_date, String e_date, String searchField, String keyword) {
		List<OutProduct> getSearch = new ArrayList<OutProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = " ";

		if(searchField.equals("0") || keyword.equals("")){
			 sql = "select * from (select rowNum rn, a.* from" 
					+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
					+ " from sales_order so, customer c, product p, sales_order_detail sod"
					+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
					+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
					+ " and so.sales_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 ) a )"
					+ " where rn between ? and ?"; 
		
		}else if(s_date.equals("")|| e_date.equals("")) {
			sql = "select * from (select rowNum rn, a.* from " 
					+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
					+ " from sales_order so, customer c, product p, sales_order_detail sod"
					+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
					+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
					+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' )a )"
					+ " where rn between ? and ?"; 
			 
		}else {
					sql = "select * from (select rowNum rn, a.* from " 
							+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
							+ " from sales_order so, customer c, product p, sales_order_detail sod"
							+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
							+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
							+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' "
							+ " and so.sales_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 ) a )"
							+ " where rn between ? and ?"; 
			
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OutProduct outProduct = new OutProduct();
				
				outProduct.setSales_order_date(rs.getDate("sales_order_date"));
				outProduct.setCustomer_no(rs.getString("customer_no"));
				outProduct.setCustomer_name(rs.getString("customer_name"));
				outProduct.setProduct_no(rs.getInt("product_no"));
				outProduct.setProduct_name(rs.getString("product_name"));
				outProduct.setPrice(rs.getInt("price"));
				outProduct.setSales_detail_pcount(rs.getInt("sales_detail_pcount"));
				
				
				getSearch.add(outProduct);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return getSearch;
	}
	
	
	
	public int getTotal2(String s_date, String e_date, String searchField, String keyword) {
		int total2 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = " ";
		
		if(searchField.equals("0") || keyword.equals("")){
			sql =  "select count(*) from "
					+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
					+ " from sales_order so, customer c, product p, sales_order_detail sod"
					+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
					+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
					+ " and so.sales_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 )";
			
		}else if (s_date.equals("")|| e_date.equals("")){
			sql =  "select count(*) from "
					+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
					+ " from sales_order so, customer c, product p, sales_order_detail sod"
					+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
					+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
					+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' )";
					
		}else  {
			sql =  "select count(*) from "
					+ "(select so.sales_order_date, c.customer_no, c.customer_name, p.product_no, p.product_name, p.price, sod.sales_detail_pcount"
					+ " from sales_order so, customer c, product p, sales_order_detail sod"
					+ " where c.customer_no = so.customer_no and so.sales_order_no = sod.sales_oreder_no"
					+ " and c.emp_no = so.emp_no and sod.product_no = p.product_no"
					+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' "
					+ " and so.sales_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 )";
					
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total2 = rs.getInt(1);
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
		return total2;
	}

}
