package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.listener.MySessionBindingListener;


@WebServlet("/SessionListenerTest")
public class T10_SessionListenerTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("AAA", "AAA입니다");
		req.getSession().setAttribute("AAA", "AAA수정입니다");
		req.getSession().setAttribute("BBB", "BBB입니다");
		req.getSession().removeAttribute("AAA");
		
		MySessionBindingListener bindingListener = new MySessionBindingListener();
		req.getSession().setAttribute("obj1", bindingListener);
		req.getSession().removeAttribute("obj1");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
