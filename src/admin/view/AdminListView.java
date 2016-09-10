package admin.view;

import java.util.ArrayList;

import admin.domain.Admin;

public class AdminListView {

	// 관리자 계정 리스트 
	public void adminListView(ArrayList<Admin> admins) {

		System.out.println("관리자 계정 리스트 입니다");
		System.out.println("관리자 번호\t관리자 아이디\t관리자 패스워드\t관리자 이름\t관리자 권한");
		for(int i=0; i<admins.size(); i++){
			System.out.print(admins.get(i).getAdminNumber() + "\t");
			System.out.print(admins.get(i).getAdminId() + "\t");
			System.out.print(admins.get(i).getAdminPassword() + "\t");
			System.out.print(admins.get(i).getAdminName() + "\t");
			System.out.println(admins.get(i).getAuthority());
		}

	}
		
}
