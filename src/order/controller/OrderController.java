package order.controller;

import java.util.ArrayList;

import main.controller.MainController;
import order.dao.OrderDao;
import order.domain.Order;
import order.view.CartProductView;
import order.view.OrderListView;
import order.view.OrderProductView;
import order.view.OrderUpdateView;
import product.domain.Product;

public class OrderController {

	// variable
	private OrderDao orderDao;

	
	// constructor
	public OrderController() {

		this.orderDao = new OrderDao();

	}

	
	// method
	// 상품 주문 장바구니로 이동
	public void requestCartProductView() {
		
		CartProductView cartProductView = new CartProductView();
		cartProductView.cartProductView();
		
	}
	
	
	// 선택한 상품 장바구니로 이동
	public void requestCartProduct(Order newOrder) {
		
		int cartProcessNumber = orderDao.cartProduct(newOrder);

		if(cartProcessNumber == 1){

			MainController.AlertView("이미 장바구니에 있는 상품입니다. 수량만 변경되었습니다");

		} else if(cartProcessNumber == 2){

			MainController.AlertView("정상적으로 장바구니에 담겼습니다");

		} else {

			System.out.println("선택하신 상품은 없는 상품입니다");

		}
		
	}
	
	// 장바구니 확인하기
	
	public void requestCartListView(){
		
		CartListView cartListView = new CartListView();
		
	}
	
	
	
	// 상품 주문을 위한 상품번호와 수량 입력받음
	public void requestOrderProductView(){

		OrderProductView orderProductView = new OrderProductView();
		orderProductView.orderProductView();

	}


	// 주문받은 상품번호와 수량을 데이터베이스에 저장을 요청
	public void requestOrderProduct(Order newOrder){

		int orderProcessNumber = orderDao.orderProduct(newOrder);

		if(orderProcessNumber == 1){

			MainController.AlertView("이미 주문된 상품입니다. 수량만 변경되었습니다");

		} else if(orderProcessNumber == 2){

			MainController.AlertView("정상적으로 주문되었습니다");

		} else {

			System.out.println("선택하신 상품은 없는 상품입니다");

		}

	}


	// 장바구니 리스트 요청
	public void requestOrderListView(){

		ArrayList<Order> orders = orderDao.orderList();
		ArrayList<Product> products = orderDao.orderListProduct(orders);
		OrderListView orderListView = new OrderListView();
		orderListView.orderListView(orders, products);

	}


	// 장바구니 수정을 위한 정보 요청
	public void requestUpdateOrderListInfo(){

		OrderUpdateView orderUpdateView = new OrderUpdateView();
		orderUpdateView.orderUpdateView();

	}


	// 장바구니 수정 정보 데이터베이스 반영	
	public void requestUpdateOrderList(Order updatedOrder){

		boolean success = orderDao.updateOrderList(updatedOrder);

		if(success){
			MainController.AlertView("성공적으로 수정되었습니다");
		}else{
			MainController.AlertView("선택하신 상품은 존재하지 않는 상품입니다");
		}

	}


	// 장바구니 삭제 요청
	public void requestDeleteOrderList(int selectedProductNumber){

		boolean success = orderDao.deleteOrderList(selectedProductNumber);

		if(success){
			MainController.AlertView("성공적으로 삭제되었습니다");
		}else{
			MainController.AlertView("선택하신 상품은 존재하지 않는 상품입니다");
		}

	}

}
