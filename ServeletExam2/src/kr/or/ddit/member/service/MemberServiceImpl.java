package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	private IMemberDao memDao;
	private static IMemberService service;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}

	public static IMemberService getInstance(){
	      if(service ==null) {
	         service = new MemberServiceImpl();
	      }
	      return service;
	   }
	
	
	
	@Override
	public int insertMember(MemberVO mv) {
		int cnt =0;
		try {
			return memDao.insertMember(mv);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public void reservation(MemberVO mv) {
		//좌석 선택 ...(좌석선택 DAO 호출)
		
		//계좌이체 기능
		
		//사용자한테 메일 발송...
	}
	
	@Override
	public int deleteMember(String memId) {
		int cnt =0;
		try {
			return memDao.deleteMember(memId);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt =0;
		try {
			return memDao.updateMember(mv);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	
	}

	@Override
	public List<MemberVO> displayMemberAll() {
		List<MemberVO> memList = new ArrayList<>();
		try {
			
			memList = memDao.displayMemberAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO memberVO = new MemberVO();
		try {
			memberVO = memDao.getMember(memId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		try {
			
			memList =  memDao.getSearchMember(mv);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return memList;
		
		
	}

	
}
