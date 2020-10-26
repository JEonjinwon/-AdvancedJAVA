package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * 에러(예외)처리를 위한 서블릿 예제
 * @author PC-17
 *
 */
public class T04_ErrorHandler extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//예외 객체 가져오기
		Throwable throwable = (Throwable)req.getAttribute("javax.servlet.error.exception");
		//에러 상태 코드 가져오기
		Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status.code");
		//에러발생한 서블릭 이름 가져오기
		String servletName=(String)req.getAttribute("javax.servlet.error.servlet_name");
		
		if(servletName==null) {
			servletName="알수없음";
		}
		
		//에러발생 URL가져오기
		String requestUri = (String)req.getAttribute("javax.servlet.error.request_uri");
		
		if(requestUri==null) {
			requestUri ="알수없는 URL";
		}
		
		//응답 헤더 설정하기
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter pw = resp.getWriter();
		String title = "예외(에러) 정보";
		
		pw.println("<!DOCTYPE html>\n"+
				"<html>\n"+
				"<head><title>"+title+"</title></head>\n"+
				"<body>\n"	);
		if(throwable ==null && statusCode==null) {
			pw.println("<h2>에러정보없음</h2>");
			pw.println("홈페이지로 돌아가기 : <a href=\"http://localhost:9090/ServletTest/index.html"+
					"\">홈페이지</a");
		}
		else if(statusCode !=null) {
			pw.println("Status code : "+statusCode);
		}
		else {
			pw.println("<h2>에러(예외) 정보 </h2>");
			pw.println("Servlert Name :" +servletName+"<br><br>");
			pw.println("Exception Type : "+throwable.getClass().getName()+"<br><br>");
			pw.println("요청 URL :"+requestUri +"<br><br>");
			pw.println("예외메시지:"+throwable.getMessage());
		}
		pw.println("</body>");
		pw.println("</html>");
	
		
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
