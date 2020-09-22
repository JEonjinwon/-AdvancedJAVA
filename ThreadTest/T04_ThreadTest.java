package kr.or.ddit.basic;

public class T04_ThreadTest {
	/**
	 * 1~20억 합계를 구하는데 걸리는 시간체크하기
	 * 전체 합계를 구하는 작업을 단독으로 처리했을때 (1개의 스레드를 사용했을때)와
	 * 여러 스레드로 분할해서 작업을 할때의 시간을 확인해보자.
	 * 
	 */

	public static void main(String[] args) {
		//단독으로 처리할 때
		sumThread sm = new sumThread(1L, 2000000000L);
		
		long startTime = System.currentTimeMillis();
		
		sm.start();
		try {
			sm.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리할 때 처리 시간 "+(endTime-startTime));
		System.out.println("\n\n");
		
		//여러 쓰래드가 협력해서 처리했을 때 ..
		sumThread[] sumThs = new sumThread[] {
				new sumThread(         1L,  500000000L),
				new sumThread( 500000001L, 1000000000L),
				new sumThread(1000000001L, 1500000000L),
				new sumThread(1500000001L, 2000000000L)
		};
		startTime = System.currentTimeMillis();
		for (sumThread th : sumThs) {
			th.start();
		}
		for (sumThread th : sumThs) {
			try {
				th.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("협력으로 처리할 때 처리 시간 "+(endTime-startTime));
		System.out.println("\n\n");
		
		
		
	}

}
class sumThread extends Thread{
	private long max, min;
	
	public sumThread(long min, long max) {
			this.max = max;
			this.min = min;
	}
	@Override
	public void run() {
		long sum=0L;
		for (int i = 0; i < max; i++) {
			sum+=i;
		}
		System.out.println(min+" ~ "+max +" 합 : "+sum );
	}
}