package model;

import java.sql.Date;

public class InProduct {
	private Date purchase_order_date;		//Purchase_order
	private String seller_no;				//Seller
	private String seller_name;				//Seller
	private int product_no;					//Product
	private String product_name;			//Product
	private int cost;						//Product
	private int purchase_detail_pcount;		//Purchase_order_detail

	
	public Date getPurchase_order_date() {
		return purchase_order_date;
	}

	public void setPurchase_order_date(Date purchase_order_date) {
		this.purchase_order_date = purchase_order_date;
	}

	public String getSeller_no() {
		return seller_no;
	}

	public void setSeller_no(String seller_no) {
		this.seller_no = seller_no;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public int getProduct_no() {
		return product_no;
	}

	public void setProduct_no(int product_no) {
		this.product_no = product_no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPurchase_detail_pcount() {
		return purchase_detail_pcount;
	}

	public void setPurchase_detail_pcount(int purchase_detail_pcount) {
		this.purchase_detail_pcount = purchase_detail_pcount;
	}

}
