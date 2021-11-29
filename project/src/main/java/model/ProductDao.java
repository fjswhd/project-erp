package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class ProductDao { // singleton

	private static ProductDao instance = new ProductDao();

	private ProductDao() {
	}

	public static ProductDao getInstance() {
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

	public List<Product> productList(int startRow, int endRow) {
		List<Product> productList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from product_list where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setCost(rs.getInt("cost"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));

				productList.add(product);
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
		return productList;
		
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
		return total;
	}

	public Product select(int product_no) {

		Product product = new Product();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from modify_stock_form where product_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product.setSeller_no(rs.getString("seller_no"));
				product.setSeller_name(rs.getString("seller_name"));
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setCost(rs.getInt("cost"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));
				product.setEmp_no(rs.getString("emp_no"));
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
		return product;

	}

	public int insert(Product product) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "insert into product_modified values(sysdate,?,?,?,?)";
		String sql2 = "update product set stock=? where product_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProduct_no());
			pstmt.setString(2, product.getEmp_no());
			pstmt.setInt(3, product.getModified_stock());
			pstmt.setString(4, product.getModified_memo());
			result = pstmt.executeUpdate();

			pstmt.close();

			pstmt = conn.prepareStatement(sql2);

			pstmt.setInt(1, product.getStock());
			pstmt.setInt(2, product.getProduct_no());
			pstmt.executeUpdate();

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
		return result;
	}

	public List<Product> modifyStockList(int startRow, int endRow) {
		List<Product> modifyStockList = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from modify_stock_list where rn between ? and ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Product product = new Product();

				product.setProduct_modified_date(rs.getDate("product_modified_date"));
				product.setSeller_no(rs.getString("seller_no"));
				product.setSeller_name(rs.getString("seller_name"));
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setVariance(rs.getInt("variance"));
				product.setModified_memo(rs.getString("modified_memo"));
				product.setEmp_no(rs.getString("emp_no"));

				modifyStockList.add(product);
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
		return modifyStockList;
	}

	public int getTotal2() {
		int total2 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from modify_stock_list";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total2 = rs.getInt(1);
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
		return total2;
	}

	public List<Product> getSearch(int startRow, int endRow, String searchField, String keyword) {
		List<Product> getSearch = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from (select rowNum rn, a.* from (select * from product where " 
				+ searchField.trim()+ " like '%" + keyword.trim() + "%' order by product_no)a)" 
				+ " where rn between ? and ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setCost(rs.getInt("cost"));
				product.setPrice(rs.getInt("price"));
				product.setStock(rs.getInt("stock"));

				getSearch.add(product);
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

	public int getTotal3(String searchField, String keyword) {
		int total3 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from " 
					+ "(select * from product where "
					+ searchField.trim() + " like '%" + keyword.trim() + "%')";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total3 = rs.getInt(1);
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
		return total3;
	}

	public List<Product> getSearch2(int startRow, int endRow, String s_date, String e_date, String searchField, String keyword) {
		List<Product> getSearch2 = new ArrayList<Product>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = "select * from (select rowNum rn, a.* from (select * from modify_stock_search" 
				+ " where "+searchField.trim()+" like '%"+keyword.trim()+"%' "
				+ " and product_modified_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 ) a )"
				+ " where rn between ? and ?"; 

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				Product product = new Product();

				product.setProduct_modified_date(rs.getDate("product_modified_date"));
				product.setSeller_no(rs.getString("seller_no"));
				product.setSeller_name(rs.getString("seller_name"));
				product.setProduct_no(rs.getInt("product_no"));
				product.setProduct_name(rs.getString("product_name"));
				product.setVariance(rs.getInt("variance"));
				product.setModified_memo(rs.getString("modified_memo"));
				product.setEmp_no(rs.getString("emp_no"));

				getSearch2.add(product);
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
		return getSearch2;
	}

	
	public int getTotal4( String s_date, String e_date, String searchField, String keyword) {
		int total4 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql =  "select count(*) from (select * from modify_stock_search"
				+ " where "+searchField.trim()+" like '%"+keyword.trim()+"%' "
				+ " and product_modified_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 )";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total4 = rs.getInt(1);
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
		return total4;
	}

}
