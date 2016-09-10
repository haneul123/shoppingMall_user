package user.view;

import java.util.Scanner;

import main.controller.MainController;
import user.domain.User;

public class NewUserInfoView {

	// variable
	private Scanner keyboard;
	
	// constructor
	public NewUserInfoView() {
		
		this.keyboard = new Scanner(System.in);
		
	}
	
	public void newUserInfoView() {
		
		System.out.println("회원가입 모드 입니다");
		
		System.out.println("가입하실 아이디를 입력하세요 : ");
		String userId = keyboard.next();
		
		System.out.println("로그인할 패스워드를 입력하세요 : ");
		String userPassword = keyboard.next();
		
		System.out.println("이름을 입력하세요 : ");
		String userName = keyboard.next();
		
		User newUser = new User(userId, userPassword, userName);
		MainController.getUserController().requestSignUp(newUser);
		
	}

}
