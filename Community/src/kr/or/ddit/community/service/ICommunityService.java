package kr.or.ddit.community.service;

import java.util.List;

import kr.or.ddit.community.vo.CommunityVO;

public interface ICommunityService {

	public List<CommunityVO> search(CommunityVO cv) ;
	
	public int delete(String board_title);
	
	public int update(CommunityVO cV);
	
	public int write(CommunityVO CV);
	
	public List<CommunityVO> displayAll();

	public boolean getCommunity(String board_title);
	

}
