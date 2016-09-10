package main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Controller {

	// 클래스 로딩시 단 한번 요청되는 영역
	static{

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Connection --- oracle driver
	public static Connection getConnection(){
		
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123456");
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// Connection close
	public static void close(Connection conn){
		
		try{
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Statement close
	public static void close(Statement stmt){
		
		try{
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// PreparedStatement close
	public static void close(PreparedStatement pstmt){
		
		try{
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// ResultSet close
	public static void close(ResultSet rs){
		
		try{
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// Transaction 처리 (commit)
	public static void commit(Connection conn){
		
		try{		
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	// rollback
	public static void rollback(Connection conn){
		
		try{
			conn.rollback();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
