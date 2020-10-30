package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하는 방법 방법1) Properties 객체 이용하기
 * 
 */
public class JDBCUtill2 {
	static Properties prop;// Properties객체 변수 선언

	static {
		prop = new Properties(); // 객체 생성
		File file = new File("res/db.properties");
		

		
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
			
		} catch (IOException e) {
			System.err.println("파일이 없거나 입출력 오류입니다.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("드라이빙 로드 실패 ");
			e.printStackTrace();
		}

	}
	

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
					
		} catch (SQLException e) {
			System.out.println("DB 연결 실패...");
			return null;
		}
		
	}
	/**
	 * 자원 반납하는 메서드
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if(rs!=null)try {rs.close();}catch (SQLException ex) {ex.printStackTrace();}
		if(stmt!=null)try {stmt.close();}catch (SQLException ex) {ex.printStackTrace();}
		if(pstmt!=null)try {pstmt.close();}catch (SQLException ex) {ex.printStackTrace();}
		if(conn!=null)try {conn.close();}catch (SQLException ex) {ex.printStackTrace();}
	}
	
	
	
}
