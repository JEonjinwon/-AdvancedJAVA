package kr.or.ddit.community.service;

import java.util.List;

import kr.or.ddit.community.dao.ICommunityDao;
import kr.or.ddit.community.dao.CommunityDaoImpl;
import kr.or.ddit.community.vo.CommunityVO;


public class CommunityServiceImpl implements ICommunityService {

	private ICommunityDao cmDao;
	
	
	
	
	public CommunityServiceImpl() {
	
		cmDao = new CommunityDaoImpl();
	}

	@Override
	public List<CommunityVO> search(CommunityVO cv) {
		return cmDao.search(cv);
	}

	@Override
	public int delete(String board_title) {
		return cmDao.delete(board_title);
	}

	@Override
	public int update(CommunityVO cV) {
		return cmDao.update(cV);
	}

	@Override
	public int write(CommunityVO CV) {
		return cmDao.write(CV);
	}

	@Override
	public List<CommunityVO> displayAll() {
		return cmDao.displayAll();
	}

	@Override
	public boolean getCommunity(String board_title) {
		
		return cmDao.getCommunity(board_title);
	}



}
