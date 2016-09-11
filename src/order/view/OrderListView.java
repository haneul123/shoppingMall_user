package order.view;

import java.util.ArrayList;

import order.domain.Order;
import product.domain.Product;

public class OrderListView {

	public void orderListView(ArrayList<Order> orders, ArrayList<Product> products) {
		
		if(orders.size() == 0){
			
			System.out.println("장바구니에 상품이 존재하지 않습니다");
			
		} else {
		
			System.out.println("주문번호\t상품번호\t상품이름\t\t상품가격\t상품설명\t\t제조사\t주문수량");
			
			for(int i=0; i<products.size(); i++){
				
				System.out.print(orders.get(i).getOrderNumber() + "\t");
				System.out.print(orders.get(i).getProductNumber() + "\t");
				System.out.print(products.get(i).getProductName() + "\t");
				System.out.print(products.get(i).getProductPrice() + "\t");
				System.out.print(products.get(i).getProductComment() + "\t");
				System.out.print(products.get(i).getProductVendor() + "\t");
				System.out.println(orders.get(i).getOrderCount());
				
			}	
		}	
	}
}
