package kr.or.ddit.basic;

public class T09_ThreadDaemonTest {

	public static void main(String[] args) {
		Thread autoSave = new AutoSaveThread();
		
		//데몬 스레드로 설정하기 => start()메서드 호출 전에 설정해야한다.
		autoSave.setDaemon(true);	//데몬쓰레드는 메인쓰레드 종료시 같이 종료 
		autoSave.start();
		
		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println("작업 "+i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료 ...");
	}

}

/**
 *	자동 저장하는 쓰레드 (3초에 한번씩 저장하기)
 */
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();//저장기능 호출
		}
	
	}
	
}