package order.controller;

import java.util.ArrayList;

import main.controller.MainController;
import order.dao.OrderDao;
import order.domain.Order;
import order.view.OrderListView;
import order.view.OrderProductView;
import product.domain.Product;

public class OrderController {

	// variable
	private OrderDao orderDao;
	
	// constructor
	public OrderController() {
		
		this.orderDao = new OrderDao();
		
	}
	
	// method
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
	
	
	// 장바구니 수정 요청
	public void requestUpdateOrderList(){
		
		
		
	}
	
	
	// 장바구니 삭제 요청
	public void requestDeleteOrderList(){
		
		
		
	}
	
}
