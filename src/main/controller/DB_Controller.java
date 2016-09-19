package main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Controller {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public DB_Controller(){


		try{
			System.out.println("프로그램이 시작되었습니다.");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "system", "1234");
			System.out.println("데이터베이스가 연결되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩에 실패했습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		}

	}
	
	public Connection getConnection() {
		
		return conn;
		
	}

	public void requestExitProgram() {

		if(conn != null) {
			try { conn.close(); } catch(SQLException e) { e.printStackTrace();}
			try { rs.close(); } catch(SQLException e) { e.printStackTrace();}
			try { stmt.close(); } catch(SQLException e) { e.printStackTrace();}
		}

		System.out.println("프로그램이 종료되었습니다.");
		System.exit(0);

	}


	/*
	// 클래스 로딩시 단 한번 요청되는 영역
	static{

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	// Connection --- oracle driver
	public Connection getConnection(){

		Connection conn = null;

		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}


	// Connection close
	public void close(Connection conn){

		try{
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}


	// Statement close
	public void close(Statement stmt){

		try{
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}


	// PreparedStatement close
	public void close(PreparedStatement pstmt){

		try{
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}


	// ResultSet close
	public void close(ResultSet rs){

		try{
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}


	// Transaction 처리 (commit)
	public void commit(Connection conn){

		try{		
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}

	}


	// rollback
	public void rollback(Connection conn){

		try{
			conn.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}

	}*/

}
