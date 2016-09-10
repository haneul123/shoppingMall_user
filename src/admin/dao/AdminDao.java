package admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import admin.domain.Admin;
import main.controller.MainController;

public class AdminDao {

	// 새로운 관리자 정보 데이터베이스에 저장
	public boolean adminSignUp(Admin newAdmin) {

		boolean success = false;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int nextAdminNumber = 0;
		
		try {
			// 중복체크
			String sql = "select * from shoppingMall_admin where adminId = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setString(1, newAdmin.getAdminId());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				MainController.AlertView("이미 아이디가 있습니다");
			} else {
				sql = "select max(adminNumber) + 1 as maxAdminNumber from shoppingMall_admin";
				stmt = MainController.getDbController().getConnection().createStatement();
				rs2 = stmt.executeQuery(sql);
				
				if(rs2.next()){
					nextAdminNumber = rs2.getInt("maxAdminNumber");
					if(rs2.wasNull()){
						nextAdminNumber = 1;
					}
				}
				
				newAdmin.setAdminNumber(nextAdminNumber);
				
				sql = "insert into shoppingMall_admin values(?,?,?,?,?)";
				pstmt2 = MainController.getDbController().getConnection().prepareStatement(sql);		
				pstmt2.setInt(1, newAdmin.getAdminNumber());
				pstmt2.setString(2, newAdmin.getAdminId());
				pstmt2.setString(3, newAdmin.getAdminPassword());
				pstmt2.setString(4, newAdmin.getAdminName());
				pstmt2.setInt(5, newAdmin.getAuthority());
				pstmt2.executeUpdate();
				success = true;
					
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(rs);
			MainController.getDbController().close(rs2);
			MainController.getDbController().close(stmt);
			MainController.getDbController().close(pstmt);
			MainController.getDbController().close(pstmt2);
		}

		return success;

	}


	// 관리자 리스트 정보 불러오기
	public ArrayList<Admin> adminList() {

		ArrayList<Admin> admins = new ArrayList<Admin>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from shoppingMall_admin";	
			stmt = MainController.getDbController().getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				Admin admin = new Admin();
				
				admin.setAdminNumber(rs.getInt(1));
				admin.setAdminId(rs.getString(2));
				admin.setAdminPassword(rs.getString(3));
				admin.setAdminName(rs.getString(4));
				admin.setAuthority(rs.getInt(5));
					
				admins.add(admin);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(rs);
			MainController.getDbController().close(stmt);
			
		}

		return admins;

	}


	// 선택한 관리자 정보 불러오기
	public Admin adminInfo(int selectedAdminNumber) {

		Admin selectedAdmin = new Admin();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			
			String sql = "select * from shoppingMall_admin where adminNumber = ?";	
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, selectedAdminNumber);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				selectedAdmin.setAdminNumber(rs.getInt(1));
				selectedAdmin.setAdminId(rs.getString(2));
				selectedAdmin.setAdminPassword(rs.getString(3));
				selectedAdmin.setAdminName(rs.getString(4));
				selectedAdmin.setAuthority(rs.getInt(5));
				
				if(rs.wasNull()){
					MainController.AlertView("등록된 회원이 없습니다");
				}
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(rs);
			MainController.getDbController().close(pstmt);
			
		}

		return selectedAdmin;

	}


	// 수정된 관리자 정보 데이터베이스에 저장
	public boolean adminUpdate(Admin updatedAdmin) {

		boolean success = false;
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "update shoppingMall_admin set adminPassword = ?, authority = ? where adminNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			
			if(updatedAdmin.getAdminPassword() != null){
				pstmt.setString(1, updatedAdmin.getAdminPassword());	
			}
			
			if(updatedAdmin.getAuthority() != 0){
				pstmt.setInt(2, updatedAdmin.getAuthority());	
			}
			
			pstmt.setInt(3, updatedAdmin.getAdminNumber());
			
			pstmt.executeUpdate();
			success = true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			MainController.getDbController().close(pstmt);
			
		}

		return success;

	}

	
	// 선택된 관리자 정보 데이터베이스 삭제
	public boolean adminDelete(int selectedAdminNumber) {

		boolean success = false;
		PreparedStatement pstmt = null;
		
		
		try {
			
			String sql = "delete shoppingMall_admin where adminNumber = ?";
			pstmt = MainController.getDbController().getConnection().prepareStatement(sql);
			pstmt.setInt(1, selectedAdminNumber);
			pstmt.executeUpdate();
			success = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			MainController.getDbController().close(pstmt);
			
		}
		
		return success;

	}


}
