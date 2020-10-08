package kr.or.ddit.basic;
/**
 * Singleton 패턴 => 객체(인스턴스)를 한개만 만들어지게 하는 프로그램 방법
 * 
 *  - singleton class를 구성하는 방법 
 *  1. 자기 자신 class의 참조변수를 멤버변수로 선언한다.
 * 	 	(이 변수는 private static으로 지정한다.)
 *  
 *  2. 생성자를 private으로 한다.
 *  	(외부에서 생성자에 접근을 못하게 하기위해 즉, 외부에서 new 명령을 사용하지 못하게 하기 위해서...)
 *  
 *  3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
 *  	(이 메서드의 이름은 보통 gdtInstance()로 지정한다. 이 메서드는 static으로 지정한다.)
 *
 */
public class Mysingleton {
	//자기 자신의 class의 참조값을 저장하기 위한 멤버변수 선언
	private static Mysingleton single;
	
	//생성자를 private으로 지정한다.
	private Mysingleton() {
		System.out.println("생성자입니다.");
	}
	
	public static Mysingleton getInstance() {
		if(single ==null) {
			single = new Mysingleton();
		}
		return single;
	}
	
	//나머지 내용들은 이 클래스로 처리할 내용을 기술한다.
	public void displayText() {
		System.out.println("안녕하세요 싱글톤 객체 입니다.");
	}
	
}
