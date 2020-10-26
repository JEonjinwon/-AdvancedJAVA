package kr.or.ddit.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements  HttpSessionBindingListener{

	/**
	 * HTTP 세션 영역 내에서 HttpSessionBindingListener을 구현한 객체가 바인딩 되면 호출됨.
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent hsbe) {
        System.out.println("MySessionBindingListener : valueBound() 호출됨 >>" + hsbe.getName()+ " : "+hsbe.getValue());      
	}

	/**
	 * HTTP 세션 영역 내에서 HttpSessionBindingListener을 구현한 객체가 언바인딩 되면 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent hsbe) {
		System.out.println("MySessionBindingListener : valueUnbound() 호출됨 >>" + hsbe.getName()+ " : "+hsbe.getValue());      
		
	}

}
