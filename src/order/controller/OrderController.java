package order.controller;

import java.util.ArrayList;

import main.controller.MainController;
import order.dao.OrderDao;
import order.domain.Order;
import order.view.CartListView;
import order.view.CartProductView;
import order.view.OrderListView;
import order.view.CartUpdateView;
import order.view.UpdateCartList;

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
		cartListView.cartListView();
		
	}
	
	
	// 장바구니 내용을 수정하기
	public void requestUpdateCartListView(){
		
		UpdateCartList updateCartList = new UpdateCartList();
		updateCartList.updateCartList();
		
	}
	
	
	// 수정한 내용 dao에 적용
	public void requestUpdateCartList(Order order){
		
		boolean isFind = orderDao.updateCartList(order);
		
		if(isFind){
			MainController.AlertView("잘 수정되었습니다");
		} else {
			MainController.AlertView("수정에 실패하였습니다");
		}
		
	}


	// 장바구니 내용을 주문 리스트 데이터베이스에 저장을 요청
	public void requestOrderProduct(){

		boolean success = orderDao.orderProduct();
		
		if(success){
			MainController.AlertView("주문이 정상처리되었습니다");
		} else {
			MainController.AlertView("주문처리에 실패했습니다");
		}

	}


	// 주문 리스트 요청
	public void requestOrderListView(){

		ArrayList<Order> orders = orderDao.orderList();
		OrderListView orderListView = new OrderListView();
		orderListView.orderListView(orders);

	}


	// 주문 리스트 수정을 위한 정보 요청
	public void requestUpdateOrderListInfo(){

		CartUpdateView orderUpdateView = new CartUpdateView();
		orderUpdateView.orderUpdateView();

	}


	// 주문 리스트 수정 정보 데이터베이스 반영	
	public void requestUpdateOrderList(Order updatedOrder){

		boolean success = orderDao.updateOrderList(updatedOrder);

		if(success){
			MainController.AlertView("성공적으로 수정되었습니다");
		}else{
			MainController.AlertView("선택하신 상품은 존재하지 않는 상품입니다");
		}

	}


	// 주문 리스트 삭제 요청
	public void requestDeleteOrderList(int selectedProductNumber){

		boolean success = orderDao.deleteOrderList(selectedProductNumber);

		if(success){
			MainController.AlertView("성공적으로 삭제되었습니다");
		}else{
			MainController.AlertView("선택하신 상품은 존재하지 않는 상품입니다");
		}

	}

}
