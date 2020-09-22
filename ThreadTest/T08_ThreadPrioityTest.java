package kr.or.ddit.basic;

public class T08_ThreadPrioityTest {

	public static void main(String[] args) {
		Thread th1 = new Thread1();
		Thread th2 = new Thread2();
		
		//우선 순위는 start() 메서드를 호출하기 전에 설정해야한다.
		th1.setPriority(10); //setPriority() 우선순위 정해주는것 파라미터로 1~10 정수 가능 
		th2.setPriority(1);	//10이 최우선 
		
		System.out.println("th1의 우선순위 : "+th1.getPriority());
		System.out.println("th2의 우선순위 : "+th2.getPriority());
		
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("최대 우선순위 : "+Thread.MAX_PRIORITY );
		System.out.println("최소 우선순위 : "+Thread.MIN_PRIORITY );
		System.out.println("보통 우선순위 : "+Thread.NORM_PRIORITY );
		
		
	}

}

// 대문자를 출력하는 쓰레드
class Thread1 extends Thread {

	@Override
	public void run() {
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);

			// 아무것도 하지 않는 반복문 (시간 떄우기용)
			for (long i = 1L; i < 1000000000L; i++) {
			}
		}

	}
}

// 소문자를 출력하는 쓰레드
class Thread2 extends Thread {

	@Override
	public void run() {
		for (char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);

			// 아무것도 하지 않는 반복문 (시간 떄우기용)
			for (long i = 1L; i < 1000000000L; i++) {
			}
		}

	}
}