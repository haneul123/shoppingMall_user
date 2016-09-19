package order.repository;

import java.util.ArrayList;

import order.domain.Order;

public class OrderRepository {

	
	// variable
	static private ArrayList<Order> orders;
	static private int lastCartNumber;
	
	
	// constructor
	public OrderRepository() {
		
		orders = new ArrayList<Order>();
		lastCartNumber = 1;
		
	}

	
	// getter and setter
	public static ArrayList<Order> getOrders() {
		return orders;
	}


	public static void setOrders(ArrayList<Order> orders) {
		OrderRepository.orders = orders;
	}


	public static int getLastCartNumber() {
		return lastCartNumber;
	}


	public static void setLastCartNumber(int lastCartNumber) {
		OrderRepository.lastCartNumber = lastCartNumber;
	}

}
