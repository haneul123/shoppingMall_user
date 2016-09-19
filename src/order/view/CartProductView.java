package order.view;

import java.util.Scanner;

import main.controller.MainController;
import order.domain.Order;

public class CartProductView {

	// variable
	private Scanner keyboard;
	
	
	// constructor
	public CartProductView() {
		
		this.keyboard = new Scanner(System.in);
		
	}
	
	
	// method
	public void cartProductView() {

		System.out.println("장바구니에 넣을 상품 번호를 입력하십시오");
		int selectedProductNumber = keyboard.nextInt();
		
		System.out.println("몇개 주문하시겠습니까?");
		int cartProductCount = keyboard.nextInt();
		
		Order newOrder = new Order(selectedProductNumber, cartProductCount);
		
		MainController.getOrderController().requestCartProduct(newOrder);
		
	}

}
