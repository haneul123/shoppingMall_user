package payment.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import login.repository.LoginRepository;
import main.controller.MainController;
import order.domain.Order;
import payment.domain.Payment;
import product.domain.Product;

public class PaymentDao {

	// 결제하기
	public void pay(int selectedMethodNumber, ArrayList<Order> orders, ArrayList<Product> products) {

		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int maxPaymentListNumber = 0;
		String sql = null;

		try {

			for(int i=0; i<products.size(); i++){

				sql = "select max(paymentListNumber) + 1 as paymentListNumber from paymentList";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs = stmt.executeQuery(sql);

				if(rs.next()){

					maxPaymentListNumber = rs.getInt(1);

					if(rs.wasNull()){
						maxPaymentListNumber = 1;
					}
				}

				sql = "insert into paymentlist values(?, ?, ?, ?, ?, ?)";	
				pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt.setInt(1, maxPaymentListNumber);
				pstmt.setInt(2, orders.get(i).getUserNumber());
				pstmt.setInt(3, products.get(i).getProductNumber());
				pstmt.setInt(4, orders.get(i).getOrderCount());
				pstmt.setInt(5, selectedMethodNumber);
				Date sqlDate = new Date(Calendar.getInstance().getTimeInMillis());
				pstmt.setDate(6, sqlDate);
				pstmt.executeUpdate();

			}

			sql = "delete cartlist where userNumber = ?";
			pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt2.setInt(1, orders.get(0).getUserNumber());	
			pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt2 != null){MainController.getDbController().close(pstmt2);}
			if(pstmt != null){MainController.getDbController().close(pstmt);}
			if(rs != null){MainController.getDbController().close(rs);}
			if(stmt != null){MainController.getDbController().close(stmt);}
		}

	}

	public ArrayList<Payment> paymentList() {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String sql = null;
		ArrayList<Payment> payments = new ArrayList<Payment>();
		int userNumber = 0;

		try {

			sql = "select userNumber from userlist where userId = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, LoginRepository.getLogin().getLoginUserId());
			rs = pstmt.executeQuery();

			if(rs.next()){
				userNumber = rs.getInt(1);
			}

			sql = "select pm.paymentListNumber, pm.userNumber, pm.productNumber, pm.paymentCount, pm.paymentMethod, pm.paymentDate, pt.productName, pt.productPrice from paymentList pm, productList pt where pm.userNumber = ? and pm.productNumber = pt.productNumber";
			pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt2.setInt(1, userNumber);
			rs2 = pstmt2.executeQuery();

			while(rs2.next()){
				
				Payment payment = new Payment();
				payment.setPaymentListNumber(rs2.getInt(1));
				payment.setUserNumber(rs2.getInt(2));
				payment.setProductNumber(rs2.getInt(3));
				payment.setPaymentCount(rs2.getInt(4));
				payment.setPaymentMethod(rs2.getInt(5));
				payment.setPaymentDate(rs2.getDate(6));
				payment.setProductName(rs2.getString(7));
				payment.setProductPrice(rs2.getInt(8));
				payments.add(payment);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs2 != null){MainController.getDbController().close(rs2);}
			if(pstmt2 != null){MainController.getDbController().close(pstmt2);}
			if(rs != null){MainController.getDbController().close(rs);}
			if(pstmt != null){MainController.getDbController().close(pstmt);}
		}

		return payments;

	}

}
