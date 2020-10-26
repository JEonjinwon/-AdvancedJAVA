package kr.or.ddit.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener , ServletContextAttributeListener{
   /**
    * 서블릿 컨텍스트 객체가 제거되었을때 호출됨. 
    */
   @Override
   public void contextDestroyed(ServletContextEvent sce) {
      System.out.println("MyServletContxtListener : contextDestroyed() 호출됨.");
   }
   /**
    * 서블릿 컨텍스트 객체가 초기화 되었을때 호출됨. 
    */
   @Override
   public void contextInitialized(ServletContextEvent sce) {
      System.out.println("MyServletContxtListener : contextInitialized() 호출됨.");      
   }
   /**
    * 서블릿 컨텍스트에 속성값이 추가되었을때...
    */
   @Override
   public void attributeAdded(ServletContextAttributeEvent sce) {
      System.out.println("MyServletContxtListener : attributeAdded() 호출됨."+sce.getName());
   }
   /**
    * 서블릿 컨텍스트에 속성값이 삭제되었을떄.
    */
   @Override
   public void attributeRemoved(ServletContextAttributeEvent sce) {
      
      System.out.println("MyServletContxtListener : attributeRemoved() 호출됨."+sce.getName());
   }
   /**
    * 서블릿 컨텍스트에 속성값이 변경되었을때...
    */
   @Override
   public void attributeReplaced(ServletContextAttributeEvent sce) {
      System.out.println("MyServletContxtListener : attributeReplaced() 호출됨."+sce.getName());
   }
   

}