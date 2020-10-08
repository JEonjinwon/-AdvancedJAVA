package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.JDBCUtil;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class T02_MemberInfoTest_Log4j {
	
	//Log4j를 이용한 로그 남기기위한 로거 생성 
	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger resultLogger = Logger.getLogger(T02_MemberInfoTest_Log4j.class);
	
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	
	
	/**
	 * 회원 정보를 삭제하는 메서드
	 * (입력받은 회원 ID를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원의 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from mymember where mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(memId+"님의 정보 삭제 완료...");
			}else {
				System.out.println(memId+"님의 정보 삭제 실패...");	
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		
		
		
	}

	/**
	 * 회원 정보를 수정하는 메서드
	 */
	private void updateMember() {
		boolean chk = false;	//기존 회원 존재여부 체크
		String memId =  "";
		
		do {
			System.out.println();
			System.out.println("수정할 회원의 정보를 입력하세요");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk= getMember(memId);
			
			if(!chk) {
				System.out.println("회원 ID가 "+ memId+"인 회원은 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		
		
		}while(chk==false);
		
		System.out.println("회원 이름 >>");
		String memName = scan.next();
		System.out.println("회원 전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.println("회원주소 >>");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " update mymember set "
					+ " mem_name= ?,"
					+ " mem_tel= ?,"
					+ " mem_addr= ? "
					+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println(memId+"님의 정보를 수정하였습니다...");
			}else {
				System.out.println(memId+"님의 정보를 수정 못했습니다....실패!!");
			}
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("===================================");
		System.out.println("ID\t이름\t전화번호\t\t주소");
		System.out.println("===================================");
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = " select * from mymember ";
			
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sql);
			
			while (rs.next()) {
					String  memId= rs.getString("mem_id");
					String  memName= rs.getString("mem_name");
					String  memTel= rs.getString("mem_tel");
					String  memAddr= rs.getString("mem_addr");
					System.out.println(memId+"\t"+memName+"\t"+memTel+"\t\t"+memAddr);
			}
			
			System.out.println("=============================================");
			System.out.println("출력 작업 끝 ...");
			
		} catch (SQLException e) {
			System.out.println("가져오기 실패...");
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 회원을 추가하는 메서드
	 */
	private void insertMember() {
		boolean chk = false;	//기존 회원 존재여부 체크
		String memId =  "";
		
		do {
			System.out.println();
			System.out.println("추가할 회원의 정보를 입력하세요");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			
			chk= getMember(memId);
			
			if(chk) {
				System.out.println("회원 ID가 "+ memId+"인 회원은 이미 존재합니다.");
				System.out.println("다시 입력해주세요.");
			}
		
		
		}while(chk==true);
		
		System.out.println("회원 이름 >>");
		String memName = scan.next();
		System.out.println("회원 전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.println("회원주소 >>");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "insert into myMember " 
						+ " (mem_id,mem_name,mem_tel,mem_addr) " 
						+" values(?,?,?,?) ";
			
			sqlLogger.debug("쿼리 : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			
			paramLogger.debug("파라미터 ("
			+ memId+", "
			+ memName+", "
			+ memTel+", "
			+ memAddr+")");
			
			int cnt = pstmt.executeUpdate();
			
			resultLogger.info("결과값 : "+ cnt);
			
			if(cnt>0) {
				System.out.println(memId+" 회원 추가 성공...");
			}else {
				System.out.println(memId+" 회원 추가 실패...");
			}
			
		}catch (SQLException e) {
			System.out.println(memId+" 회원 추가 실패...");
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}	
	}

	/**
	 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드 
	 * @param memId
	 * @return
	 */
	private boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			conn= JDBCUtil.getConnection();
			String sql = "select count(*) cnt from mymember "
					+" where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
				
			int count =0;
			
			while(rs.next()) {
				count = rs.getInt("cnt");
			}
			
			if(count>0) {
				chk=true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			chk=false;
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		return chk;
	}

//	/**
//	 * 사용한 자원을 반납하는 메서드
//	 */
//	private void disConnenct() {
//		if(rs!=null)try {rs.close();}catch (SQLException ex) {ex.printStackTrace();}
//		if(stmt!=null)try {stmt.close();}catch (SQLException ex) {ex.printStackTrace();}
//		if(pstmt!=null)try {pstmt.close();}catch (SQLException ex) {ex.printStackTrace();}
//		if(conn!=null)try {conn.close();}catch (SQLException ex) {ex.printStackTrace();}
//		
//	}

	public static void main(String[] args) {
		T02_MemberInfoTest_Log4j memObj = new T02_MemberInfoTest_Log4j();
		memObj.start();
	}

}





