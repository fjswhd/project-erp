package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.DataSource;

public class InProductDao {
	
	private static InProductDao instance = new InProductDao();

	private InProductDao() {}

	public static InProductDao getInstance() {
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

	public List<InProduct> inProductList(int startRow, int endRow) {
		List<InProduct> inProductList = new ArrayList<InProduct>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();

		String sql = "select * from (select rowNum rn, a.* from " 
				+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
				+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
				+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
				+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no )a )"
				+ " where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InProduct inProduct = new InProduct();
				
				inProduct.setPurchase_order_date(rs.getDate("purchase_order_date"));
				inProduct.setSeller_no(rs.getString("seller_no"));
				inProduct.setSeller_name(rs.getString("seller_name"));
				inProduct.setProduct_no(rs.getInt("product_no"));
				inProduct.setProduct_name(rs.getString("product_name"));
				inProduct.setCost(rs.getInt("cost"));
				inProduct.setPurchase_detail_pcount(rs.getInt("purchase_detail_pcount"));
				
				
				inProductList.add(inProduct);
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
		return inProductList;
	}
	
	
	
	public int getTotal() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from "
				+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
				+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
				+ " where s.seller_no = po.seller_no"
				+ " and s.emp_no = po.emp_no"
				+ " and po.purchase_order_no = pod.purchase_order_no"
				+ " and pod.product_no = p.product_no)";
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
		
		public List<InProduct> getSearch(int startRow, int endRow, String s_date, String e_date, String searchField, String keyword) {
			List<InProduct> getSearch = new ArrayList<InProduct>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = getConnection();
			
			String sql = " ";

				if(searchField.equals("0") || keyword.equals("")){
					 sql = "select * from (select rowNum rn, a.* from " 
							+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
							+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
							+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
							+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
							+ " and po.purchase_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 ) a )"
							+ " where rn between ? and ?"; 
				
				}else if(s_date.equals("")|| e_date.equals("")) {
					sql = "select * from (select rowNum rn, a.* from " 
							+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
							+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
							+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
							+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
							+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' )a )"
							+ " where rn between ? and ?"; 
					 
				}else {
						sql = "select * from (select rowNum rn, a.* from " 
						+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
						+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
						+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
						+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
						+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' "
						+ " and po.purchase_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 ) a )"
						+ " where rn between ? and ?"; 
					
				}
				
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					InProduct inProduct = new InProduct();
					
					inProduct.setPurchase_order_date(rs.getDate("purchase_order_date"));
					inProduct.setSeller_no(rs.getString("seller_no"));
					inProduct.setSeller_name(rs.getString("seller_name"));
					inProduct.setProduct_no(rs.getInt("product_no"));
					inProduct.setProduct_name(rs.getString("product_name"));
					inProduct.setCost(rs.getInt("cost"));
					inProduct.setPurchase_detail_pcount(rs.getInt("purchase_detail_pcount"));
					
					getSearch.add(inProduct);
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
		
		
		
		public int getTotal2( String s_date, String e_date, String searchField, String keyword) {
			int total2 = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Connection conn = getConnection();
			
			String sql = " ";
			
			if(searchField.equals("0") || keyword.equals("")){
				sql =  "select count(*) from "
						+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
						+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
						+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
						+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
						+ " and po.purchase_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 )";
			}else if (s_date.equals("")|| e_date.equals("")){
				sql =  "select count(*) from "
						+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
						+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
						+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
						+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
						+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' )";
						
			}else  {
				sql =  "select count(*) from "
						+ "(select po.purchase_order_date, s.seller_no, s.seller_name, p.product_no, p.product_name, p.cost, pod.purchase_detail_pcount"
						+ " from purchase_order po, seller s, product p, purchase_order_detail pod"
						+ " where s.seller_no = po.seller_no and s.emp_no = po.emp_no"
						+ " and po.purchase_order_no = pod.purchase_order_no and pod.product_no = p.product_no"
						+ " and "+searchField.trim()+" like '%"+keyword.trim()+"%' "
						+ " and po.purchase_order_date between to_date('"+s_date+"', 'yyyy-mm-dd') and to_date('"+e_date+"', 'yyyy-mm-dd')+1 )";
						
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
