package kr.or.ddid.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/**
 * *******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************
 * 
 *
 */
public class EX_0916_HOTEL {
	private static Map<Integer, Hotel> hotelMap = new HashMap<>();
	static Scanner sc = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		
		System.out.println("\r\n" + 
				"**************************\r\n" + 
				"호텔 문을 열었습니다.\r\n" + 
				"**************************");
		while(true) {
			System.out.println("*******************************************\r\n" + 
					"어떤 업무를 하시겠습니까?\r\n" + 
					"1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + 
					"*******************************************");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴 선택 => ");
			int input = sc.nextInt();
			
			switch (input) {
			case 1:
				//체크인
				checkIn();
				break;
			case 2:
				//체크아웃
				checkOut();
				break;
			case 3:
				//객실상태
				room();
				break;
			case 4:
				//업무종료
				System.exit(0);
				
			}
			
		}
		
	}
	
	
	//호텔 체크인 하는 메소드
		public static void checkIn() {
			System.out.println("어느방에 체크인 하시겠습니까?");
			int rNum = roomNum();
			
			//호텔 방 체크인 여부 검사
			if(hotelMap.get(rNum) !=null) {
				System.out.println(rNum+"호는 이미 체크인 된 방입니다.");
				return; 
			}
			
			System.out.println("누구를 체크인 하시겠습니까?");
			String rName = name();
			
			hotelMap.put(rNum, new Hotel(rNum, rName) );
			System.out.println(rName+"님이 "+rNum+"호에 체크인 되었습니다.\n\n");
		}
		
		//호텔 체크아웃하는 메소드
		public static void checkOut() {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			int rNum = roomNum();
			if(hotelMap.remove(rNum)==null) {
				System.out.println(rNum+"호는 체크아웃 할 수 없습니다.");
			}else {
				System.out.println(rNum+"호를 체크아웃 하였습니다.");
		
			}		
			System.out.println("");
			System.out.println("");

			
		}
		//객실 상태 확인하는 메소드
		public static void room() {
		
			Set<Integer> keySet = hotelMap.keySet();
			System.out.println("=====================================================");
			if(keySet.size()==0) {
				System.out.println("체크인 한 사람이 아무도 없습니다.");
			}else {
				Iterator<Integer> it = keySet.iterator();
				System.out.println("확인하고 싶은 객실 번호를 입력해주세요");
				while (it.hasNext()) {
					int rNum =it.next();
					Hotel h = hotelMap.get(rNum);
					System.out.println(h.getrName()+"님이  "+h.getrNum()+"호에 체크인 되었습니다.");
				}
			}
			System.out.println("=====================================================\n\n");
			
		}
		
		//방번호 입력 메소드
		public static int roomNum() {
			
			System.out.print("방번호 입력 => ");
			int rNum = sc.nextInt();
			return rNum;
		}
		
		//이름을 입력받는 메소드
		public static String name() {
			System.out.print("이름 입력 => ");
			String rName = sc.next();
			return rName;
		}
		
		
		
}



class Hotel{
	private int rNum;
	private String rName;
	public Hotel(int rNum,String rName) {
		super();
		this.rNum = rNum;
		this.rName = rName;
	}
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	@Override
	public String toString() {
		return "Hotel [rNum=" + rNum + ", rName=" + rName + "]";
	}
	
	
}
