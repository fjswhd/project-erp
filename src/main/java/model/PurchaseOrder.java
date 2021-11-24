package model;

import java.sql.Date;

public class PurchaseOrder {
	//구매주문번호, 구매처 코드, 구매 주문일, 주문등록 사원
	private int 	purchase_order_no;
	private String 	customer_no;
	private Date	purchase_order_date;
	private String 	emp_no;
	public int getPurchase_order_no() {
		return purchase_order_no;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public Date getPurchase_order_date() {
		return purchase_order_date;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setPurchase_order_no(int purchase_order_no) {
		this.purchase_order_no = purchase_order_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	public void setPurchase_order_date(Date purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	
}
