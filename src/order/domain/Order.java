package order.domain;

import java.sql.Date;

public class Order {

	// variable
	private int orderNumber;
	private int productNumber;
	private int userNumber;
	private int orderCount;
	private Date orderDate;
	private int orderSum;
	
	
	// constructor
	public Order() {

		
	}
	
	public Order(int productNumber, int orderCount){
		
		this.productNumber = productNumber;
		this.orderCount = orderCount;
		
	}
	
	
	// getter and setter
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(int orderSum) {
		this.orderSum = orderSum;
	}	
	
}
