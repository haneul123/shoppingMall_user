package payment.controller;

import java.util.ArrayList;

import order.domain.Order;
import payment.dao.PaymentDao;
import payment.domain.Payment;
import payment.view.PaymentList;
import payment.view.PaymentView;

public class PaymentController {

	// variable
	private PaymentDao paymentDao;
	
	// constructor
	public PaymentController() {
		
		this.paymentDao = new PaymentDao();
		
	}
	
	// method
	// 결제창뷰 출력요청
	public void requestPaymentView(ArrayList<Order> orders) {
		
		PaymentView paymentView = new PaymentView();
		paymentView.paymentView(orders);
		
	}

	
	// 결정된 결제방법으로 결제요청
	public void requestPayment(int selectedMethodNumber, ArrayList<Order> orders) {
		
		paymentDao.pay(selectedMethodNumber, orders);
		
	}

	
	// 결제내역 출력요청
	public void requestPaymentList(){
		
		ArrayList<Payment> payments = paymentDao.paymentList();
		PaymentList paymentList = new PaymentList();
		paymentList.paymentList(payments);
		
	}
	
}