package model;

import java.sql.Date;

public class Product {
	private String seller_no;		//Seller
	private String seller_name;		//Seller
	private int product_no;			//Product
	private String product_name;	//Product
	private int cost;				//Product
	private int price;				//Product
	private int stock;				//Product
	private Date product_modified_date;		//Product_modified	
	private String emp_no;					//Product_modified
	private int modified_stock;				//Product_modified
	private int variance	;					////Product_modified: (현재고 - 기존재고)하면서 추가된 컬럼
	private String modified_memo;			//Product_modified
	private String keyword;

	

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getProduct_modified_date() {
		return product_modified_date;
	}

	public void setProduct_modified_date(Date product_modified_date) {
		this.product_modified_date = product_modified_date;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public int getModified_stock() {
		return modified_stock;
	}

	public void setModified_stock(int modified_stock) {
		this.modified_stock = modified_stock;
	}

	public String getModified_memo() {
		return modified_memo;
	}

	public void setModified_memo(String modified_memo) {
		this.modified_memo = modified_memo;
	}

	public int getVariance() {
		return variance;
	}

	public void setVariance(int variance) {
		this.variance = variance;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
