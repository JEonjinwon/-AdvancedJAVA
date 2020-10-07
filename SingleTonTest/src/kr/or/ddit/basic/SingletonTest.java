package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		//MySingleton test1 = new Mysingelton();	//new 명령사용
		
		//getInstance()메서드를 이용하여 객체생성
		Mysingleton test2 = Mysingleton.getInstance();
		Mysingleton test3 = Mysingleton.getInstance();
		
		System.out.println("test2 =>" +test2);
		System.out.println("test3 =>" +test3);
		

	}

}
