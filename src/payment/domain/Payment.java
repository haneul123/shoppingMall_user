package payment.domain;

import java.sql.Date;

public class Payment {

	// variable
	private int paymentListNumber;
	private int userNumber;
	private int productNumber;
	private int PaymentCount;
	private int paymentMethod;
	private Date paymentDate;
	private String productName;
	private int productPrice;
	
	
	// constructor
	public Payment() {
	
	}

	
	// getter and setter
	public int getPaymentListNumber() {
		return paymentListNumber;
	}


	public void setPaymentListNumber(int paymentListNumber) {
		this.paymentListNumber = paymentListNumber;
	}


	public int getUserNumber() {
		return userNumber;
	}


	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}


	public int getProductNumber() {
		return productNumber;
	}


	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}


	public int getPaymentCount() {
		return PaymentCount;
	}


	public void setPaymentCount(int paymentCount) {
		PaymentCount = paymentCount;
	}


	public int getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
		
}
