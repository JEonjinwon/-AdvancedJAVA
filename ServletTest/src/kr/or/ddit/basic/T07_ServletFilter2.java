package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T07_ServletFilter2 implements Filter{

	@Override
	public void destroy() {
		System.out.println("T07_ServlertFilter2 : destroy()메서드 호출...");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T07_ServlertFilter2 : doFilter()메서드 호출...");
		
		//서블릿 수행시간 계산하기
		long startTime = System.nanoTime();
		
		//필터체인을 실행한다.(req, resp 객체전달)
		fc.doFilter(req, resp);
		
		System.out.println("수행 시간 (ns)"+(System.nanoTime()-startTime));
		System.out.println("T07_ServlertFilter2 : doFilter()메서드 완료...");
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("T07_ServlertFilter2 : init()메서드 호출...");
	}

}
