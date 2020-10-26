package kr.or.ddit.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MySessionAttributeListener
 *
 */
@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {


	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent hsbe)  { 
        System.out.println("MySessionAttributeListener : attributeAdded() 호출됨 >>" + hsbe.getName()+ " : "+hsbe.getValue());      

    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent hsbe)  { 
    	System.out.println("MySessionAttributeListener : attributeRemoved() 호출됨 >>" + hsbe.getName()+ " : "+hsbe.getValue());      
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent hsbe)  { 
    	System.out.println("MySessionAttributeListener : attributeReplaced() 호출됨 >>" + hsbe.getName()+ " : "+hsbe.getValue());      
    }
	
}
