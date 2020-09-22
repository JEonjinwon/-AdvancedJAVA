package kr.or.ddit.basic;

/**
 *	쓰레드 수행 시간 체크
 */
public class T03_ThreadTest {

	public static void main(String[] args) {
		Thread th = new Thread(new MyRunner());
		
		//1970년 1월 1일 0시 0분 0초(표준시)로 부터 경과한 시간을 밀리세컨드(1/1000초)단위로 나타내라.
		long startTime = System.currentTimeMillis();
		th.start();//쓰레드 시작
		try {
			th.join();	//현재 실행중인 쓰레드에0서 작업중인 쓰레드(지금은 th쓰레드)가 종료 될 때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("경과시간 : "+(endTime-startTime)+"ms" );
		
		
	}

}
class MyRunner implements Runnable{
	
	//1~10억까지 합계 구하는 메서드
	@Override
	public void run() {
		long sum =0;
		for (long i = 0L; i <= 1000000000; i++) {
			sum+=i;
			
		}
		System.out.println("합계 : " +sum);
	}
	
}