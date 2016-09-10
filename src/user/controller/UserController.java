package user.controller;

import main.controller.MainController;
import user.dao.UserDao;
import user.domain.User;
import user.view.NewUserInfoView;
import user.view.UserMainMenu;

public class UserController {

	// variable
	private UserDao userDao;

	
	// constructor
	public UserController() {

		this.userDao = new UserDao();

	}

	
	// 유저 메인메뉴 요청
	public void requestUserMainMenu() {

		UserMainMenu userMainMenu = new UserMainMenu();
		userMainMenu.userMainMenu();

	}
	
	
	// 회원 가입 정보 요청
	public void requestNewUserInfo() {

		NewUserInfoView newUserInfoView = new NewUserInfoView();
		newUserInfoView.newUserInfoView();

	}


	// 받은 회원 정보 데이터베이스 저장 요청
	public void requestSignUp(User newUser){

		boolean success = userDao.userSignUp(newUser);

		if(success){
			MainController.AlertView("가입에 성공하였습니다");
		} else {
			MainController.AlertView("가입에 실패하였습니다");
		}

	}



}
