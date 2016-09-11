package order.domain;

public class Order {

	// variable
	private int orderNumber;
	private int productNumber;
	private int orderCount;
	
	
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

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}	
	
}
