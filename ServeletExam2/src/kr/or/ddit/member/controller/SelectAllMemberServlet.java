package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/selectAllMember")
public class SelectAllMemberServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pageNo = req.getParameter("pageNo")== null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		String msg = req.getParameter("msg")==null ? "":req.getParameter("msg");
		System.out.println("msg : "+msg);
		
		//1. 서비스객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		//2. 페이징 객체 생성
		
		//3. 회원 정보 조회
		List<MemberVO> memList = memberService.displayMemberAll();
		req.setAttribute("msg", msg);
		req.setAttribute("memList", memList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
		/*dispatcher.include(req, resp);*/	
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
