package order.view;

import java.util.Scanner;

import main.controller.MainController;
import order.domain.Order;

public class UpdateCartList {

	// variable
	private Scanner keyboard;
	
	public void updateCartList() {
		
		Order order = null;
		
		System.out.println("수정하시려면 1번 || 삭제하시려면 2번을 눌러주세요");
		int selectedMenu = keyboard.nextInt();
		
		if(selectedMenu == 1){
			System.out.println("수정할 상품번호를 입력해 주세요");
			int selectedProductNumber = keyboard.nextInt();
			
			System.out.println("수정할 수량을 입력해 주세요");	
			int updateProductCount = keyboard.nextInt();
			
			order = new Order(selectedProductNumber, updateProductCount);
			
		}else if(selectedMenu == 2){
			
			MainController.getOrderController().requestUpdateCartList(order);
		}
		
		MainController.getOrderController().requestUpdateCartList(order);
		
	}

}
