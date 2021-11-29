package model;

import java.sql.Date;

public class OutProduct {
	private Date sales_order_date; 			// Sales_order
	private String customer_no;				// Customer
	private String customer_name; 			// Customer
	private int product_no; 				// Product
	private String product_name; 			// Product
	private int price; 						// Product
	private int sales_detail_pcount; 		// Sales_order_detail

	
	public Date getSales_order_date() {
		return sales_order_date;
	}

	public void setSales_order_date(Date sales_order_date) {
		this.sales_order_date = sales_order_date;
	}

	public String getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSales_detail_pcount() {
		return sales_detail_pcount;
	}

	public void setSales_detail_pcount(int sales_detail_pcount) {
		this.sales_detail_pcount = sales_detail_pcount;
	}

}
