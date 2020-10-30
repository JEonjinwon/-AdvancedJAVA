package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.service.IMemberService;
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
	public int insertMember(MemberVO mv) throws Exception {
		int cnt = 0;
		
			Object obj = smc.insert("member.insertMember",mv);

			if(obj==null) {
				cnt=1;
			}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId)  throws Exception{
		int cnt = 0;
		
		cnt = smc.delete("member.deleteMember",memId);


		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv)  throws Exception{
		int cnt = 0;
		
		
			cnt = smc.update("member.updateMember",mv);
		

		return cnt;
	}

	@Override
	public List<MemberVO> displayMemberAll()throws Exception {

		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		
			memList = smc.queryForList("member.getMemberAll");
			
		

		return memList;
	}

	@Override
	public MemberVO getMember(String memId) throws Exception{

		
			
			MemberVO mv = (MemberVO) smc.queryForObject("member.getMember", memId);
			
			
		return mv;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) throws Exception{
		
		List<MemberVO> memList = new ArrayList<>();
		
		
			memList = smc.queryForList("member.getSearchMember",mv);
			
		
		
		
		return memList;
	}

}
