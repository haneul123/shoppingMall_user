package order.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import login.repository.LoginRepository;
import main.controller.MainController;
import order.domain.Order;
import order.repository.OrderRepository;
import product.domain.Product;

public class OrderDao {

	// constructor
	public OrderDao() {
		
		new OrderRepository();
		
	}
	
	
	// 주문한 상품 장바구니 넣기
	public int cartProduct(Order newOrder) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cartProcessNumber = 0;
		
		// 상품번호, 수량 받아옴		
		// newOrder 내용을 OrderRepository로 넣기
	
		for(int i=0; i<OrderRepository.getOrders().size(); i++){
			if(OrderRepository.getOrders().get(i).getProductNumber() == newOrder.getProductNumber()){
				// 이미 주문한 상품이 존재하므로 수량만 변경한다.
				OrderRepository.getOrders().get(i).setOrderCount(newOrder.getOrderCount());
				cartProcessNumber = 1;
				return cartProcessNumber;
			}
		}
		
		// 주문한 상품이 없어 새롭게 등록하는 경우
		OrderRepository.getOrders().get(OrderRepository.getLastCartNumber()).setOrderNumber(OrderRepository.getLastCartNumber());
		OrderRepository.getOrders().get(OrderRepository.getLastCartNumber()).setProductNumber(newOrder.getProductNumber());
		OrderRepository.getOrders().get(OrderRepository.getLastCartNumber()).setOrderCount(newOrder.getOrderCount());
		
		// 상품정보 DB에서 불러오기	
		try {
			
			String sql = "select productName, productPrice from productlist where productNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, newOrder.getProductNumber());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				OrderRepository.getOrders().get(OrderRepository.getLastCartNumber()).setProductName(rs.getString(1));
				OrderRepository.getOrders().get(OrderRepository.getLastCartNumber()).setProductPrice(rs.getInt(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt != null){try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
		}
		
		// 장바구니 마지막 번호 1 증가
		OrderRepository.setLastCartNumber(OrderRepository.getLastCartNumber() + 1);
				
		cartProcessNumber = 2;
		return cartProcessNumber;
		
	}
	
	
	// 주문한 상품 주문상태 장바구니로 넣기
	public int orderProduct(Order newOrder) {
		
		int orderProcessNumber = 0;
		Statement stmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		int maxOrderNumber = 0;
		int userNumber = 0;
		
		try {
			MainController.getDbController().getConnection().setAutoCommit(false);
			String sql = "select userNumber from userlist where userId = ?";
			pstmt1 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt1.setString(1, LoginRepository.getLogin().getLoginUserId());
			rs1 = pstmt1.executeQuery();

			if(rs1.next()){
				userNumber = rs1.getInt(1);
			}
			
			sql = "select * from CARTLIST where productNumber = ? and userNumber = ?";
			pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt2.setInt(1, newOrder.getProductNumber());
			pstmt2.setInt(2, userNumber);
			rs2 = pstmt2.executeQuery();
			
			if(rs2.next()){	
				
				// 동일 상품이 이미 장바구니에 있다면 수량만 조정
				sql = "update CARTLIST set orderCount = ? where productNumber = ? and userNumber = ?";
				pstmt3 = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt3.setInt(1, newOrder.getOrderCount());
				pstmt3.setInt(2, newOrder.getProductNumber());
				pstmt3.setInt(3, userNumber);
				pstmt3.executeUpdate();
				orderProcessNumber = 1;
				
			} else {
				
				// 수량 추가시 주문 번호 증가
				sql = "select max(orderNumber) + 1 as maxOrderNumber from CARTLIST";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs3 = stmt.executeQuery(sql);
				
				if(rs3.next()){
					
					maxOrderNumber = rs3.getInt(1);
					
					if(rs3.wasNull()){
						maxOrderNumber = 1;
					}
				}
				
				newOrder.setOrderNumber(maxOrderNumber);
				
				// 동일 상품이 장바구니에 없으면 상품 추가
				sql = "insert into CARTLIST(orderNumber, productNumber, userNumber, orderCount) values(?, ?, ?, ?)";
				pstmt4 = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt4.setInt(1, newOrder.getOrderNumber());
				pstmt4.setInt(2, newOrder.getProductNumber());
				pstmt4.setInt(3, LoginRepository.getLogin().getUserNumber());
				pstmt4.setInt(4, newOrder.getOrderCount());
				pstmt4.executeUpdate();
				
				orderProcessNumber = 2;
			}
			
			MainController.getDbController().getConnection().commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			try {
				MainController.getDbController().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			
			if(pstmt4 != null){try{pstmt4.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs3 != null){try{rs3.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt3 != null){try{pstmt3.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs2 != null){try{rs2.close();} catch (SQLException e) {e.printStackTrace();}}
			if(stmt != null){try{stmt.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt2 != null){try{pstmt2.close();} catch (SQLException e) {e.printStackTrace();}}
			if(rs1 != null){try{rs1.close();} catch (SQLException e) {e.printStackTrace();}}
			if(pstmt1 != null){try{pstmt1.close();} catch (SQLException e) {e.printStackTrace();}}
			
			try {
				MainController.getDbController().getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return orderProcessNumber;
		
	}

	
	// 장바구니 상품리스트 가져오기
	public ArrayList<Order> orderList() {

		ArrayList<Order> orders = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Order orderList = new Order();
			String sql = "select * from CARTLIST where userNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, LoginRepository.getLogin().getUserNumber());
			rs = pstmt.executeQuery();
			
			while(rs.next()){	
				
				orderList.setOrderNumber(rs.getInt(1)); 
				orderList.setProductNumber(rs.getInt(2));
				orderList.setUserNumber(rs.getInt(3));
				orderList.setOrderCount(rs.getInt(4));
				orderList.setOrderDate(rs.getDate(5));
				orders.add(orderList);
				
			}
			
			rs.close();
			pstmt.close();
			
			sql = "select sum(ct.orderCount * pr.productPrice) from CARTLIST ct, PRODUCTLIST pr where userNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, LoginRepository.getLogin().getUserNumber());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderList.setOrderSum(rs.getInt(1));
			}
					
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return orders;
		
	}

	
	// 주문상품에 해당하는 상품정보 뽑기
	public ArrayList<Product> orderListProduct(ArrayList<Order> orders) {
	
		ArrayList<Product> products = new ArrayList<Product>();			
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
		
			String sql = " select productNumber from CARTLIST where userNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, LoginRepository.getLogin().getUserNumber());
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				Product product = new Product();
				product.setProductNumber(rs.getInt(1));
				products.add(product);
				
			}
						
			for(int i=0; i<orders.size(); i++){
				
				sql = "select * from PRODUCTLIST where productNumber = ?";
				pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt.setInt(1, products.get(i).getProductNumber());
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					
					products.get(i).setProductName(rs.getString(2));
					products.get(i).setProductPrice(rs.getInt(3));
					products.get(i).setProductComment(rs.getString(4));
					products.get(i).setProductVendor(rs.getString(5));
					
				}

			}
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} 
		
		return products;
		
	}


	// 장바구니 수량 수정
	public boolean updateOrderList(Order updatedOrder) {
	
		boolean success = true;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int userNumber = 0;
		
		try {
			
			String sql = "select userNumber from userlist where userId = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, LoginRepository.getLogin().getLoginUserId());
			rs = pstmt.executeQuery();

			if(rs.next()){
				userNumber = rs.getInt(1);
			}
			
			rs.close();
			pstmt.close();
			
			sql = "update CARTLIST set orderCount = ? where productNumber = ? and userNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, updatedOrder.getOrderCount());
			pstmt.setInt(2, updatedOrder.getProductNumber());
			pstmt.setInt(3, userNumber);
			pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} 

		return success;
		
	}


	// 선택 상품 삭제
	public boolean deleteOrderList(int selectedProductNumber) {
		
		boolean success = false;
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "delete CARTLIST where productNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, selectedProductNumber);
			pstmt.executeUpdate();
			success = true;
			
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
		
	}

}
