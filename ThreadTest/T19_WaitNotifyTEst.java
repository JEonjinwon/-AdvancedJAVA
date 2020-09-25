package kr.or.ddit.basic;

public class T19_WaitNotifyTEst {
	/**
	 *	wait()메서드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로 이동시킨다.
	 *
	 *	notify()또는 notifyAll()메서드 => Wait-Set영역에 있는 쓰레드를 꺠워서 실행할 수 있도록 한다.
	 *				(notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 꺠운다.)
	 *
	 *	=> wait()과 notify(),notifyAll() 메서드는 동기화 영역에서만 실행할 수 있고,
	 *		Object 클래스에서 제공하는 메서드 이다.
	 */

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA tha = new ThreadA(workObj);
		ThreadB thb = new ThreadB(workObj);
		
		tha.start();
		thb.start();
	}

}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()에서 작입중...");

		notify();	//대기실에 있는 놈 깨운다.

		try {
			wait();	//내가 대기실로 간다. 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB()에서 작입중...");

		notify();

		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


/**
 * WorkObject의 methodA()메서드만 호출하는 쓰레드 
 */
class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료 ...");
	}
	
}

/**
 * WorkObject의 methodB()메서드만 호출하는 쓰레드 
 */
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료 ...");
	}
	
}






























