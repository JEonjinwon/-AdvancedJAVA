package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtill2;
import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.util.JDBCUtill;

public class MemberDaoImpl implements IMemberDao {

	
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	public MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	
	}
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	



	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			Object obj = smc.insert("member.insertMember",mv);

			if(obj==null) {
				cnt=1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
		cnt = smc.delete("member.deleteMember",memId);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
		
			cnt = smc.update("member.updateMember",mv);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<MemberVO> displayMemberAll() {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		try {
		
			memList = smc.queryForList("member.getMemberAll");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return memList;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;

		try {
			
			int count = 0;
			MemberVO mv = (MemberVO) smc.queryForObject("member.getMember", memId);
			
			if(mv!=null) {
				chk = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		}

		return chk;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		try {
		
			memList = smc.queryForList("member.getSearchMember",mv);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return memList;
	}

}
