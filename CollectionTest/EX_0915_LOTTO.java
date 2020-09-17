
package kr.or.ddid.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 * 로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
      출력한다.)

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.
			
   	 ==========================
       	  Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다
 * 
 *
 */
public class EX_0915_LOTTO {
	public static void main(String[] args) {
		System.out.println("==========================\r\n" + 
				"     Lotto 프로그램\r");
		LoTTo lt  = new LoTTo();
		lt.StartLoTTo();
	}
}
class LoTTo{
	Scanner sc = new Scanner(System.in);
	
	public void StartLoTTo() {
		while(true) {
			System.out.println("============================================");
			System.out.println("1.로또 구입하기, 2.종료하기 ");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				//로또 번호 뽑기
				buyLoTTo();
				StartLoTTo();
				break;
			case 2:
				//나가기
				System.out.println("감사합니다.");
				return;

			}
		}
	}

	private void buyLoTTo() {

		while(true) {
			
			System.out.println("구매할 금액을 입력하세요");
			int money = sc.nextInt();
			int lottoNum = money / 1000;
			int change = money % 1000;
			System.out.println(lottoNum + "장의 로또를 구매하셨습니다. ");
			System.out.println("거스름돈은 " + change + "원 입니다.");
			
			for (int i = 0; i < lottoNum; i++) {
				Set<Integer> lottoSet = new HashSet<>();
				while (lottoSet.size() < 6) {
					int rnd = (int) (Math.random() * 45 + 1);
					lottoSet.add(rnd);
				}
				System.out.println(" lotto " + (i + 1) + "번 :" + lottoSet);
			}
			
			return;
		}

	}
}