package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오. 컴퓨터의 가위바위보는 난수를 이용하여 구하고 사용자의 가위바위보는
 * showInputDialog()메서드를 이용하여 입력받는다.
 *
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다, 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시) ===결과=== 컴퓨터 : 가위 당신 : 바위 결과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	public static boolean inputCheck = false;

	public static void main(String[] args) {
		Thread th1 = new Input();
		Thread th2 = new Count1();

		th1.start();
		th2.start();
	}

}

class Input extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위, 바위, 보 ");
		int com = (int) (Math.random() * 3 + 1);
		T07_ThreadGame.inputCheck = true;

		if (str.equals("가위")) {
			switch (com) {
			case 1:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 가위 ");
				System.out.println("당신 : 가위 ");
				System.out.println("결과 : 무승부 ");
				break;
			case 2:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 바위 ");
				System.out.println("당신 : 가위 ");
				System.out.println("결과 : 패배 ");
				break;
			case 3:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 보 ");
				System.out.println("당신 : 가위 ");
				System.out.println("결과 : 승리 ");
				break;

			}
		} else if (str.equals("바위")) {
			switch (com) {
			case 1:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 가위 ");
				System.out.println("당신 : 바위 ");
				System.out.println("결과 : 승리");
				break;
			case 2:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 바위 ");
				System.out.println("당신 : 바위 ");
				System.out.println("결과 : 무승부 ");
				break;
			case 3:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 보 ");
				System.out.println("당신 : 바위 ");
				System.out.println("결과 : 패배 ");
				break;
			default:
				System.out.println("잘못 입렸했으니깐 니가 진거다 ㅉㅉ");
				break;

			}

		} else if (str.equals("보")) {
			switch (com) {
			case 1:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 가위 ");
				System.out.println("당신 : 보 ");
				System.out.println("결과 : 패배");
				break;
			case 2:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 바위 ");
				System.out.println("당신 : 보 ");
				System.out.println("결과 : 승리 ");
				break;
			case 3:
				System.out.println("===결과===");
				System.out.println("컴퓨터 : 보 ");
				System.out.println("당신 : 보 ");
				System.out.println("결과 : 무승부 ");
				break;

			}

		} else {
			System.out.println("잘못 입렸했으니깐 니가 진겨ㅉㅉ");
		}
	}
}

/**
 * 카운트다운 처리를 위한 쓰레드 클래스
 */
class Count1 extends Thread {
	@Override
	public void run() {

		for (int i = 5; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			// run()메서드를 종료시킨다. 즉 현재쓰레드를 종료시킨다.
			if (T07_ThreadGame.inputCheck == true) {
				return; // run() 메서드가 종료되면 쓰레드도 종료된다.
			}

			System.out.println(i);

			try {
				Thread.sleep(1000); // 1초동안 잠시멈춘다
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 10초가 경과되었는데 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다.\n 시간 경과 당신 패배 ㅉㅉ.");
		System.exit(0);// 프로그램을 종료시키는 명령
	}
}