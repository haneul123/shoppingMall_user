package login.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import login.domain.Login;
import main.controller.MainController;

public class LoginDao {

	// 로그인
	public int login(Login loginUser) {

		int userOrAdmin = 0;
		String sql = null;
		PreparedStatement pstmt = null; 
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		
		try {
			
			// 회원인지 확인
			sql = "select * from shoppingMall_user where userId = ? and userPassword = ?";	
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, loginUser.getLoginUserId());
			pstmt.setString(2, loginUser.getLoginUserPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				userOrAdmin = 2;
			}
			
			// 관리자인지 확인
			sql = "select * from shoppingMall_admin where adminId = ? and adminPassword = ?";
			pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt2.setString(1, loginUser.getLoginUserId());
			pstmt2.setString(2, loginUser.getLoginUserPassword());
			rs2 = pstmt2.executeQuery();
			
			if(rs2.next()){
				userOrAdmin = 1;
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(pstmt);
			MainController.getDbController().close(rs);
			MainController.getDbController().close(pstmt2);
			MainController.getDbController().close(rs2);
			
		}
			
		if(userOrAdmin == 0){
			return userOrAdmin;
		}
			
		// 확인 후 로그인
		try {
			sql = "insert into shoppingMall_login values(?,?)";
			pstmt3 = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt3.setString(1, loginUser.getLoginUserId());
			pstmt3.setString(2, loginUser.getLoginUserPassword());
			pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MainController.getDbController().close(pstmt3);	
		}
	
		return userOrAdmin;
		
	}

	
	// 로그아웃
	public boolean logout() {
		
		boolean success = false;
		Statement stmt = null;
		
		try {
			
			stmt = MainController.getDbController().getConnection().createStatement();
			String sql = "delete shoppingMall_login";
			stmt.executeQuery(sql);
			success = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(stmt);
			
		}
		
		return success;
		
	}


	// 로그인한 유저 정보 리턴
	public Login loginUser() {
		
		Login loginUser = new Login();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from shoppingMall_login";
			stmt = MainController.getDbController().getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				loginUser.setLoginUserId(rs.getString(1));
				loginUser.setLoginUserPassword(rs.getString(2));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(rs);
			MainController.getDbController().close(stmt);
			
		}
			
		return loginUser;
		
	}

}
