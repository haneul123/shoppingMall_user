package order.view;

import java.util.Scanner;

import main.controller.MainController;
import order.domain.Order;

public class OrderProductView {

	// variable
	private Scanner keyboard;
	
	// constructor
	public OrderProductView() {
	
		this.keyboard = new Scanner(System.in);
		
	}
	
	// method
	// 상품 주문을 위한 번호 입력받기
	public void orderProductView() {
		
		System.out.println("주문을 원하시는 상품번호를 입력하여 주십시오");
		int selectedProductNumber = keyboard.nextInt();
		
		System.out.println("몇개 주문하시겠습니까?");
		int orderProductCount = keyboard.nextInt();
		
		Order newOrder = new Order(selectedProductNumber, orderProductCount);
		
		MainController.getOrderController().requestOrderProduct(newOrder);
		
	}

}
