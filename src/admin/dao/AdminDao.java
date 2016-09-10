package admin.dao;

import java.util.ArrayList;

import admin.domain.Admin;

public class AdminDao {

	// 관리자 등록정보 데이터베이스에 저장
	public boolean adminSignUp(Admin newAdmin) {

		boolean success = false;



		return success;

	}


	// 관리자 리스트 정보 불러오기
	public ArrayList<Admin> adminList() {

		ArrayList<Admin> admins = null;




		return admins;

	}


	// 선택한 관리자 정보 불러오기
	public Admin adminInfo(int selectedAdminNumber) {

		Admin selectedAdmin = null;



		return selectedAdmin;

	}


	// 수정된 관리자 정보 데이터베이스에 저장
	public boolean adminUpdate(Admin updatedAdmin) {

		boolean success = false;


		return success;

	}


	public boolean adminDelete(int selectedAdminNumber) {

		boolean success = false;

		

		return success;

	}


}
