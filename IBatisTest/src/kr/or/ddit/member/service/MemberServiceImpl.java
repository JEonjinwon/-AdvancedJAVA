package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService{

	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}

	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	public void reservation(MemberVO mv) {
		//좌석 선택 ...(좌석선택 DAO 호출)
		
		//계좌이체 기능
		
		//사용자한테 메일 발송...
	}
	
	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public List<MemberVO> displayMemberAll() {
		return memDao.displayMemberAll();
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		return memDao.getSearchMember(mv);
	}

	
}
