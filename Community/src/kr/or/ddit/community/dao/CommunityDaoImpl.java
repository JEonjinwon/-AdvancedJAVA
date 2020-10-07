package kr.or.ddit.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.community.vo.CommunityVO;
import kr.or.ddit.util.JDBCUtill;

public class CommunityDaoImpl implements ICommunityDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;



	@Override
	public int delete(String board_title) {
		int cnt = 0;
		try {
			conn = JDBCUtill.getConnection();

			String sql = "delete from JDBC_BOARD where board_title=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public int update(CommunityVO cV) {
		int cnt = 0;
		try {
			conn = JDBCUtill.getConnection();

		
			
			String sql = " update jdbc_board set " + " Board_no= board_seq.nextVal"  + " , Board_writer= ? "+" , Board_date=SYSDATE" +" , Board_content=? "
					+ " where Board_title = ? ";

			pstmt = conn.prepareStatement(sql);

			
			pstmt.setString(1, cV.getBoard_writer());
			pstmt.setString(2, cV.getBoard_content());
			pstmt.setString(3, cV.getBoard_title());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public int write(CommunityVO CV) {
		int cnt = 0;
		try {
			conn = JDBCUtill.getConnection();
			String sql = "insert into jdbc_board " 
						+ " (board_no, board_title, board_writer, board_date, board_content) " 
						+ " values(board_seq.nextVal,?,?,SYSDATE,?) ";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, CV.getBoard_title());
			pstmt.setString(2, CV.getBoard_writer());
		
			pstmt.setString(3, CV.getBoard_content());
		
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	
	@Override
	public List<CommunityVO> search(CommunityVO cv) {
		List<CommunityVO>cmList = new ArrayList<CommunityVO>();
		try {
			conn = JDBCUtill.getConnection();
		
			String sql = "select * from jdbc_board where 1=1"; //1=1 사용한 이유 모든 것에 and 붙여서 사용할려고
			
			//쿼리문을 동적으로 그떄그때 바꿔서 사용 => 다이나믹 쿼리
			if(cv.getBoard_title()!=null && !cv.getBoard_title().equals("")) {
				sql +=" and board_title= ? ";
			}
			if(cv.getBoard_writer()!=null && !cv.getBoard_writer().equals("")) {
				sql +=" and board_writer= ? ";
			}
			
			if(cv.getBoard_content()!=null && !cv.getBoard_content().equals("")) {
				sql +=" and board_content like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int index =1;
			if(cv.getBoard_title()!=null && !cv.getBoard_title().equals("")) {
				pstmt.setString(index++, cv.getBoard_title());
			}
			if(cv.getBoard_writer()!=null && !cv.getBoard_writer().equals("")) {
				pstmt.setString(index++, cv.getBoard_writer());
			}
			if(cv.getBoard_content()!=null && !cv.getBoard_content().equals("")) {
				pstmt.setString(index++,cv.getBoard_content());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommunityVO cv2 = new CommunityVO();
				cv2.setBoard_no(rs.getString("board_no"));
				cv2.setBoard_title(rs.getString("board_title"));
				cv2.setBoard_writer(rs.getNString("board_writer"));
				cv2.setBoard_date(rs.getString("board_date"));
				cv2.setBoard_content(rs.getNString("board_content"));
						
				cmList.add(cv2);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}
		
		
		return cmList;
	}
	
	@Override
	public List<CommunityVO> displayAll() {
		List<CommunityVO>cmList = new ArrayList<CommunityVO>();
		try {
			conn = JDBCUtill.getConnection();
			String sql = " select * from JDBC_BOARD";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				CommunityVO cv = new CommunityVO();
				cv.setBoard_no(rs.getString("board_no"));
				cv.setBoard_title(rs.getString("board_title"));
				cv.setBoard_writer(rs.getNString("board_writer"));
				cv.setBoard_date(rs.getString("board_date"));
				cv.setBoard_content(rs.getNString("board_content"));
				

				cmList.add(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}

		return cmList;
	}

	@Override
	public boolean getCommunity(String board_title) {
		boolean chk = false;

		try {
			conn = JDBCUtill.getConnection();
			String sql = "select count(*) cnt from JDBC_BOARD " + " where BOARD_TITLE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);

			rs = pstmt.executeQuery();

			int count = 0;

			while (rs.next()) {
				count = rs.getInt("cnt");
			}

			if (count > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		} finally {
			JDBCUtill.disConnect(conn, stmt, pstmt, rs);
		}

		return chk;
	}

}
