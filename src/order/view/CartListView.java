package order.view;

import java.util.ArrayList;
import java.util.Scanner;

import main.controller.MainController;
import order.domain.Order;

public class CartListView {

	// variable
	private Scanner keyboard;
	
	// constructor
	public CartListView() {
		
		this.keyboard = new Scanner(System.in);
		
	}
	
	public void cartListView(ArrayList<Order> orders) {
		
		int sumPrice = 0;
		
		System.out.println("주문번호\t상품이름\t\t상품가격\t주문수량\t전체가격");
		for(int i=0; i<orders.size(); i++){
			System.out.print(orders.get(i).getOrderNumber() + "\t");
			System.out.print(orders.get(i).getProductName() + "\t");
			System.out.print(orders.get(i).getProductPrice() + "\t");
			System.out.print(orders.get(i).getOrderCount() + "\t");
			System.out.println(orders.get(i).getProductPrice() * orders.get(i).getOrderCount());
			sumPrice = sumPrice + (orders.get(i).getProductPrice() * orders.get(i).getOrderCount());
		}
		
		System.out.println("총금액 : " + sumPrice);
		
		if(sumPrice == 0){
			System.out.println("장바구니가 비어있습니다");
			return;
		}
		
		System.out.println("주문하시겠습니끼?");
		System.out.println("주문하시려면 1번을 돌아가시려면 2번을 눌러주세요");
		
		int selectedMenu = keyboard.nextInt();
		
		if(selectedMenu == 1){
			MainController.getOrderController().requestOrderProduct();
		} else if(selectedMenu == 2) {
			return;
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
		
	}

}
