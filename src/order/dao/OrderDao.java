package order.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.controller.MainController;
import order.domain.Order;
import product.domain.Product;

public class OrderDao {

	// 주문한 상품 장바구니에 넣기
	public int orderProduct(Order newOrder) {
		
		int orderProcessNumber = 0;
		Statement stmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int maxOrderNumber = 0;
		
		try {
			
			String sql = "select * from shoppingMall_Cart where productNumber = ?";
			pstmt1 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt1.setInt(1, newOrder.getProductNumber());
			rs = pstmt1.executeQuery();
			
			if(rs.next()){	
				
				// 동일 상품이 이미 장바구니에 있다면 수량만 조정
				sql = "update shoppingMall_Cart set orderCount = ? where productNumber = ?";
				pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt2.setInt(1, newOrder.getOrderCount());
				pstmt2.setInt(2, newOrder.getProductNumber());
				pstmt2.executeUpdate();
				orderProcessNumber = 1;
				
			} else {
				
				// 수량 추가시 주문 번호 증가
				sql = "select max(orderNumber) + 1 as maxOrderNumber from shoppingMall_Cart";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs2 = stmt.executeQuery(sql);
				
				if(rs2.next()){
					
					maxOrderNumber = rs2.getInt(1);
					
					if(rs2.wasNull()){
						maxOrderNumber = 1;
					}
				}
				
				newOrder.setOrderNumber(maxOrderNumber);
				
				// 동일 상품이 장바구니에 없으면 상품 추가
				sql = "insert into shoppingMall_Cart values(?, ?, ?)";
				pstmt3 = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt3.setInt(1, newOrder.getOrderNumber());
				pstmt3.setInt(2, newOrder.getProductNumber());
				pstmt3.setInt(3, newOrder.getOrderCount());
				pstmt3.executeUpdate();
				
				orderProcessNumber = 2;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			if(pstmt3 != null){MainController.getDbController().close(pstmt3);}
			if(rs2 != null){MainController.getDbController().close(rs2);}
			if(stmt != null){MainController.getDbController().close(stmt);}
			if(pstmt2 != null){MainController.getDbController().close(pstmt2);}
			if(rs != null){MainController.getDbController().close(rs);}
			if(pstmt1 != null){MainController.getDbController().close(pstmt1);}
			
		}
		
		return orderProcessNumber;
		
	}

	
	// 장바구니 상품리스트 가져오기
	public ArrayList<Order> orderList() {

		ArrayList<Order> orders = new ArrayList<Order>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from shoppingMall_Cart";
			stmt = MainController.getDbController().getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				Order orderList = new Order();
				orderList.setOrderNumber(rs.getInt(1)); 
				orderList.setProductNumber(rs.getInt(2));
				orderList.setOrderCount(rs.getInt(3));
				orders.add(orderList);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orders;
		
	}

	
	// 주문상품에 해당하는 상품정보 뽑기
	public ArrayList<Product> orderListProduct(ArrayList<Order> orders) {
	
		ArrayList<Product> products = new ArrayList<Product>();
				
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
		
			String sql = " select productNumber from shoppingMall_Cart";
			stmt = MainController.getDbController().getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				Product product = new Product();
				product.setProductNumber(rs.getInt(1));
				products.add(product);
				
			}
						
			for(int i=0; i<orders.size(); i++){
				
				sql = "select * from shoppingMall_Product where productNumber = ?";
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			if(rs != null){MainController.getDbController().close(rs);}
			if(pstmt != null){MainController.getDbController().close(pstmt);}
			
		}
		
		return products;
		
	}

}
