package user.view;

import java.util.Scanner;

import main.controller.MainController;

public class UserMainMenu {

	// variable
	private Scanner keyboard;

	// constructor
	public UserMainMenu() {

		this.keyboard = new Scanner(System.in);

	}

	public void userMainMenu() {

		while(true){
			
			System.out.println("원하시는 메뉴를 선택하여 주십시오");
			System.out.println("1. 전체상품보기 || 2. 상품주문하기 || 3. 장바구니 확인 || 4. 장바구니 수정 || 5. 결제내역 확인 || 6. 개인정보 변경 || 7. 로그아웃 하기");
			int selectedMenu = keyboard.nextInt();

			if(selectedMenu == 1){

				MainController.getProductController().requestProductlist();

			} else if(selectedMenu == 2){

				//MainController.getOrderController().requestOrderProductView();
				MainController.getOrderController().requestCartProductView();

			} else if(selectedMenu == 3){

				MainController.getOrderController().requestOrderListView();
				
			} else if(selectedMenu == 4){
				
				MainController.getOrderController().requestUpdateOrderListInfo();
				
			} else if(selectedMenu == 5){

				MainController.getPaymentController().requestPaymentList();

			} else if(selectedMenu == 6){

				MainController.getUserController().requestUserUpdateInfo();
				
			} else if(selectedMenu == 7){
				
				MainController.getLoginController().requestLogout();
				
			} else {

				System.out.println("잘못 선택하셨습니다");

			}
		}
	}

}
