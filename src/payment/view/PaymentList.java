package payment.view;

import java.util.ArrayList;

import payment.domain.Payment;

public class PaymentList {

	public void paymentList(ArrayList<Payment> payments) {
		
		System.out.println("결제내역입니다");
		System.out.println("상품번호\t상품이름\t\t상품가격\t주문수량\t총결제액\t결제방법\t결제일");
		
		for(int i=0; i<payments.size(); i++){
			System.out.print(payments.get(i).getProductNumber() + "\t");
			System.out.print(payments.get(i).getProductName() + "\t");
			System.out.print(payments.get(i).getProductPrice() + "\t");
			System.out.print(payments.get(i).getPaymentCount() + "\t");
			System.out.print(payments.get(i).getProductPrice()*payments.get(i).getPaymentCount() + "\t");
			
			if(payments.get(i).getPaymentMethod() == 1){
				System.out.print("카드결제" + "\t");
			} else {
				System.out.print("현금결제" + "\t");
			}
		
			System.out.println(payments.get(i).getPaymentDate());
			
		}
		
		
	}

}
