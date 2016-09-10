package main.controller;

import login.controller.LoginController;
import order.controller.OrderController;
import payment.controller.PaymentController;
import product.controller.ProductController;
import user.controller.UserController;

public class MainController {

	
	// Connect Controllers
	private static DB_Controller dbController;
	private static UserController userController;
	private static LoginController loginController;
	private static ProductController productController;
	private static OrderController orderController;
	private static PaymentController paymentController;
	
	
	// constructor
	public MainController() {
	
		dbController = new DB_Controller();
		userController = new UserController();
		loginController = new LoginController();
		productController = new ProductController();
		orderController = new OrderController();
		paymentController = new PaymentController();
		
	}

	
	// getter and setter
	public static DB_Controller getDbController() {
		return dbController;
	}

	public static UserController getUserController() {
		return userController;
	}

	public static LoginController getLoginController() {
		return loginController;
	}

	public static ProductController getProductController() {
		return productController;
	}

	public static OrderController getOrderController() {
		return orderController;
	}

	public static PaymentController getPaymentController() {
		return paymentController;
	}
	
}
