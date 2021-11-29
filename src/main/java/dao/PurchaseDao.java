package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.Cash;
import model.Product;
import model.Purchase;
import model.PurchaseOrder;
import model.PurchaseOrderDetail;
import model.Seller;
public class PurchaseDao {
	// singleton
	private static PurchaseDao instance = new PurchaseDao();
	private PurchaseDao() {}
	public static PurchaseDao getInstance() {
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
	
	public List<Purchase> purchaseList(int startRow, int endRow) {
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		
		String sql = "select * from (select rownum rn, p.* from purchase p) where rn between ? and ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Purchase purchase = new Purchase();
				// 판매주문일, 주문번호, 판매처코드, 판매처명, 상품코드, 상품명, 판매가, 매입가, 현재 재고량, 주문수량, 주문 등록 사원번호
				purchase.setPurchase_order_date		(rs.getDate("PURCHASE_ORDER_DATE"));
				purchase.setPurchase_order_no		(rs.getInt("purchase_order_no"));
				purchase.setSeller_no				(rs.getString("seller_no"));
				purchase.setSeller_name				(rs.getString("seller_name"));
				purchase.setProduct_no				(rs.getInt("product_no"));
				purchase.setProduct_name			(rs.getString("product_name"));
				purchase.setPrice					(rs.getInt("price"));
				purchase.setCost					(rs.getInt("cost"));
				purchase.setStock					(rs.getInt("stock"));
				purchase.setPurchase_detail_pcount	(rs.getInt("purchase_detail_pcount"));
				purchase.setEmp_no					(rs.getString("emp_no"));
				purchase.setEmp_name				(rs.getString("emp_name"));
				
				purchaseList.add(purchase);
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
		return purchaseList;
	}
	
	public int selectPurchase_order_no() {
		int selectPurchase_order_no = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select count(*) from Purchase_order";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				selectPurchase_order_no = rs.getInt(1);
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
		return selectPurchase_order_no;
	}
	
	public int insertPurchase_order(int plusedsSelectPurchase_order_no, Seller seller) {
		int result1 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="insert into purchase_order values(?,?,sysdate,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, plusedsSelectPurchase_order_no);
			pstmt.setString(2, seller.getSeller_no());
			pstmt.setString(3, seller.getEmp_no());
			result1 = pstmt.executeUpdate();
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result1;
	}
	
	public int insertPurchase_order_detail(PurchaseOrderDetail purchaseOrderDetail) {
		int result2 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="insert into purchase_order_detail values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, purchaseOrderDetail.getPurchase_order_no());
			pstmt.setInt(2, purchaseOrderDetail.getProduct_no());
			pstmt.setInt(3, purchaseOrderDetail.getPurchase_detail_pcount());
			result2 = pstmt.executeUpdate();
			
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result2;
	}
	
	public int stock(PurchaseOrderDetail purchaseOrderDetail) {
		int stock = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select stock from product where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, purchaseOrderDetail.getProduct_no());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stock = rs.getInt(1);
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
		return stock;
	}
	
	public int updateProduct(Product product) {
		int result3 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="update product set stock = ? where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getStock());
			pstmt.setInt(2, product.getProduct_no());
			result3 = pstmt.executeUpdate();
			
			 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result3;
	}
	
	public int selectCashCode() {
		int cash_code = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select count(*) from cash";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cash_code = rs.getInt(1);
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
		return cash_code;
	}
	
	public int selectCost(Product product) {
		int cost = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select cost from product where product_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getProduct_no());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cost = rs.getInt(1);
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
		return cost;
	}
	 
	public int selectCash(Cash cash) {
		int updatedCash = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="select cash from cash where cash_code = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cash.getCash_code());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updatedCash = rs.getInt(1);;
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
		return updatedCash;
	}
	
	public int insertCash(Cash cash) {
		int result4 = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql="insert into cash values(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cash.getCash_code());
			pstmt.setInt(2, cash.getCash());
			pstmt.setInt(3, cash.getPurchase_order_no());
			pstmt.setInt(4, 0);
			result4 = pstmt.executeUpdate();
			
		
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		return result4;
	}
	
//	public int insert(PurchaseHistory board) {
//		int result = 0;
//		int purchase_order_no = 0;
//		int stock = 0;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Connection conn = getConnection();
//		String sql1="select count(*) from Purchase_order";
//		String sql3="insert into purchase_order values(?,?,sysdate,?)";
//		String sql4="insert into purchase_order_detail values(?,?,?)";
//		String sql5="select stock from product where product_no = ?";
//		String sql6="update product set stock = ? where product_no = ?";
//		try {
//			pstmt = conn.prepareStatement(sql1);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				purchase_order_no = rs.getInt(1) + 1;
//			} 
//			rs.close();
//			pstmt.close();
//			
//			
//			
//			pstmt = conn.prepareStatement(sql3);
//			pstmt.setInt(1, purchase_order_no);
//			pstmt.setString(2, board.getSeller_no());
//			pstmt.setString(3, board.getEmp_no());
//			result += pstmt.executeUpdate();	
//			rs.close();
//			pstmt.close();
//			
//			pstmt = conn.prepareStatement(sql4);
//			pstmt.setInt(1, purchase_order_no);
//			pstmt.setInt(2, board.getProduct_no());
//			pstmt.setInt(3, board.getPurchase_detail_pcount());
//			result += pstmt.executeUpdate();
//			rs.close();
//			pstmt.close();
//			
//			pstmt = conn.prepareStatement(sql5);
//			pstmt.setInt(1, board.getProduct_no());
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				stock = rs.getInt(1) + board.getPurchase_detail_pcount();
//			} 
//			rs.close();
//			pstmt.close();
//			
//			pstmt = conn.prepareStatement(sql6);
//			pstmt.setInt(1, stock);
//			pstmt.setInt(2, board.getProduct_no());
//			pstmt.executeUpdate();	
//			result += pstmt.executeUpdate();
//			rs.close();
//			pstmt.close();
//			conn.close();
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}finally {
//			try {
//				if (rs != null) rs.close();
//				if (pstmt != null) pstmt.close();
//				if (conn != null)  conn.close();
//			}catch (Exception e) {		}
//		}
//		return result;
//	}
			
	public int getTotalPurchase() {
		int total = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = getConnection();
		String sql = "select count(*) from (select a.purchase_order_date, b.seller_no, b.seller_name, d.product_no, "
				+ "d.product_name, d.cost, c.purchase_detail_pcount, a.emp_no "
				+ "from purchase_order a, seller b, purchase_order_detail c, product d " 
				+ "where a.seller_no = b.seller_no and a.purchase_order_no = c.purchase_order_no "
				+ "and c.product_no = d.product_no)";
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

	public int insertPurchase(PurchaseOrder purchaseOrder, PurchaseOrderDetail purchaseOrderDetail, Cash cash, Product product) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		Connection conn = getConnection();
		
		
		try {
			conn.setAutoCommit(false);
			
			//판매주문번호, 판매처 번호, 판매 주문일, 주문등록 사원
			String sql = "insert into purchase_order values (?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, purchaseOrder.getPurchase_order_no());
			pstmt.setString	(2, purchaseOrder.getSeller_no());
			pstmt.setString	(3, purchaseOrder.getEmp_no());
			
			result += pstmt.executeUpdate(); 
			pstmt.close();
			
			//판매 주문 상세 입력
			//판매주문번호, 상품번호, 상품주문수량
			sql = "insert into purchase_order_detail values (?, ?, ?)";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, purchaseOrderDetail.getPurchase_order_no());
			pstmt.setInt(2, purchaseOrderDetail.getProduct_no());
			pstmt.setInt(3, purchaseOrderDetail.getPurchase_detail_pcount());
			
			result += pstmt.executeUpdate(); 
			pstmt.close();
			
			//현금 변동 내역 입력
			//현금 코드, 현금, 구매주문 번호, 판매주문 번호
			sql = "insert into cash values (?, ?, ?, null)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cash.getCash_code());
			pstmt.setInt(2, cash.getCash());
			pstmt.setInt(3, cash.getPurchase_order_no());
			
			result += pstmt.executeUpdate(); 
			pstmt.close();
			
			//상품재고 업데이트
			sql = "update product set stock=? where product_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, product.getStock());
			pstmt.setInt	(2, product.getProduct_no());
			
			result += pstmt.executeUpdate();			
			pstmt.close();
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("판매주문 입력 오류 : " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null)  conn.close();
			}catch (Exception e) {		}
		}
		
		return result;
	}
}