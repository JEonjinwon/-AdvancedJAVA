package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/ViewMember")
public class ViewMemberServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		//1. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		
		//2. 회원정보 조회하기
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		
		MemberVO memVO = memService.getMember(memId);
		req.setAttribute("memVO", memVO);
		
		//3. 결과를 VIEW화면에 출력하기
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/view.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
