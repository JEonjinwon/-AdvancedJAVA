package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03_ServletParameterTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/**
	 *  요청 객체로부터 파라미터 데이터 가져오는 방법
	 *  
	 *  - getParameter(파라미터이름) - 파라미터값이 한개인 경우에 가져올때 사용함
	 *  - getParameterValues() - 파라미터값이 여러개인 경우에 사용함 ex)checkbox
	 *  - getParameterNames() - request객체에 존재하는 모든 파라미터 정보(이름)을 가져올때 사용함.
	 */
		
		req.setCharacterEncoding("utf-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
		String[] food = req.getParameterValues("food");
		
		
		//resp.setCharacterEncoding("utf-8");
		//resp.setContentType("text/html");
		resp.setContentType("text/html;charset=utf-8");// 위 두줄과 같다.
		
		PrintWriter pw = resp.getWriter();
		pw.print("<html>");
		pw.print("<body>");
		pw.print("<p>username : "+username+"</p>");
		pw.print("<p>password : "+password+"</p>");
		pw.print("<p>gender : "+gender+"</p>");
		pw.print("<p>hobby : "+hobby+"</p>");
		pw.print("<p>rlgn : "+rlgn+"</p>");
		
		if(food!=null) {
			for (String s : food) {
				pw.print("<p> food: "+s+"</p");
			}
			
		}
		
		Enumeration<String> params = req.getAttributeNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			pw.print("<p> 파라미터이름 : "+param+"</p");
			
		}
		
		
		pw.print("</body>");
		pw.print("</html>");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	
	}
}
