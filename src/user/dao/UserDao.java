package user.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.controller.MainController;
import user.domain.User;

public class UserDao {

	// 회원 가입
	public boolean userSignUp(User newUser) {
		
		boolean success = false;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxUserNumber = 0;
		
		try {
			
			String sql = "select * from shoppingMall_user where userId = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, newUser.getUserId());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				MainController.AlertView("이미 아이디가 존재합니다");
				
			} else {
				
				sql = "select max(userNumber)+1 as maxUserNumber from shoppingMall_user";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs = stmt.executeQuery(sql);
				
				if(rs.next()){
					maxUserNumber = rs.getInt(1);
					if(rs.wasNull()){
						maxUserNumber = 1;
					}
				}
				
				newUser.setUserNumber(maxUserNumber);
				
				sql = "insert into shoppingMall_user values(?,?,?,?)";
				pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
				pstmt.setInt(1, newUser.getUserNumber());
				pstmt.setString(2, newUser.getUserId());
				pstmt.setString(3, newUser.getUserPassword());
				pstmt.setString(4, newUser.getUserName());
				pstmt.executeUpdate();
				success = true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
		
	}

	
	// 회원 조회
	
	
}
