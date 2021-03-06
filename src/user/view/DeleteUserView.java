package user.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteUserView {

	// variable
	private Scanner keyboard;
	
	// constructor
	public DeleteUserView() {
		
		this.keyboard = new Scanner(System.in);
		
	}
	
	// 회원탈퇴 확인요청
	public boolean deleteUserView() {
		
		boolean isAgree = false; 
		
		try{
			
			System.out.println("정말 회원 탈퇴를 하시겠습니까? y or n");
			char yesOrNo = keyboard.next().charAt(0);
			
			if(yesOrNo == 'y'){
				isAgree = true;
			}
					
		}catch(InputMismatchException e){
			
			System.err.println("잘못입력하셨습니다");
		}
		
		return isAgree;
		
	}
	
}
