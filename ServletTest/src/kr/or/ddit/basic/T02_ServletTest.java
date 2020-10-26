package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T02_ServletTest extends HttpServlet{
	/**
	 * 서블릿 동작 방식에 대하여...
	 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를 Servlet Container로 요청 전송(요청)한다.
	 * 2. 컨테이너는 web.xml에 정의된 url 패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.(로딩이 안된 경우에는 로딩함. 로딩시, init() 호출됨)
	 * 3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.(이 때 HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다)
	 * 4. service() 메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.(doGet, doPost, doPut, doDelete 등...)
	 * 5. 요청처리가 완료되면, httpServletRequest 및 HttpServletResponse 객체는 소멸된다.
	 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
	 *
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Post방식으로 넘어오는 Body데이터를 인코딩 처리하기
		//get방식은 톰캣의 URIEncoding 설정을 이용함.
		//반드시 request객체에서 값을 가져오기 전에 먼저 설정해야 적용됨.
		req.setCharacterEncoding("utf-8");
		
		//요청정보 로부터 name값을 가져오기
		String name = req.getParameter("name");
		
		System.out.println("name => "+name);
		
		//응답 메시지 인코딩 설정(Content-Type의 charset-UTF-8과 동일)
		resp.setCharacterEncoding("UTF-8");
		//응답 메시지의 컨테츠 타입 설정 
		resp.setContentType("text/plain");
		
		//실제 수행할 로직(기능)이 시작되는 부분
		PrintWriter out = resp.getWriter(); //PrintWriter 아무거나 출력할때 편함  print기능이 최적화된 스트림
		
		out.println("name => "+name);
		out.println("서블릿 경로 => "+req.getServletPath());
		out.println("컨텍스트 경로 => "+req.getContextPath());
		
		//리다이렉트나 포워드 하기전에 response를하면 안된다. response하면 모든게 종료,  
		
		//리다이랙트
		//resp.sendRedirect("index.html");
		
		//포워드
		//RequestDispatcher dispatchar =  req.getRequestDispatcher("index.html");
		//dispatchar.forward(req, resp);
		
		throw new IOException(); //예외발생(테스트용)
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
	
}
