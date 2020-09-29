package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



/**
 *문제) 

호텔 운영을 관리하는 프로그램 작성.(Map이용)
 - 키값은 방번호 

(단, 종료시 파일로 저장하고 프로그램 실행시 파일로부터 데이터를 불러올 수 있도록 처리한다.)
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
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

 */
public class ex_HotelIO_0928  {

	private static Map<Integer, Hotel> hotelMap = new HashMap<>();
	static Scanner sc = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		
		System.out.println("\r\n" + 
				"**************************\r\n" + 
				"호텔 문을 열었습니다.\r\n" + 
				"**************************");
		newFile();	// bin 파일이 해당 경로에 위치하고 있는지 확인 하는 메서드 -> 있으면 진행 없으면 생성
		receive();	// 해당 경로에 bin 파일에 저장된 정보를 갖고와 map에 담는 메서드 -> bin파일 쓴이유 : 객체에 정보를 저장하기 위해
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
				System.out.println("프로그램 저장 및 종료 ...");
				close();	//프로그램 수행시 처리한 정보를 종료시 bin 파일에 저장해주는 기능 
				System.exit(0);//프로그램 종료
				
			}
			
		}
		
	}
	
	
	//호텔 체크인 하는 메소드
		public static void checkIn() {
			System.out.println("어느방에 체크인 하시겠습니까?");
			int rNum = roomNum();	//방번호를 입력받는 메서드 호출하여 리턴값을 변수에 저장
			
			//호텔 방 체크인 여부 검사
			if(hotelMap.get(rNum) !=null) {	//입력받은 방번호가 키로 hotelMap에서 입력을 받은 적있나 확인 입력받은 적이 없으면 null이 나온다.
				System.out.println(rNum+"호는 이미 체크인 된 방입니다.");
				return; 
			}
			
			System.out.println("누구를 체크인 하시겠습니까?");
			String rName = name();//이름를 입력받는 메서드 호출하여 리턴값을 변수에 저장
			
			hotelMap.put(rNum, new Hotel(rNum, rName) );	//방번호를 키로 이름과 방번호를 hotelMap에 put로 저장
			System.out.println(rName+"님이 "+rNum+"호에 체크인 되었습니다.\n\n");
		}
		
		//호텔 체크아웃하는 메소드
		public static void checkOut() {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			int rNum = roomNum();  //방번호를 입력받는 메서드 호출하여 리턴값을 변수에 저장
			if(hotelMap.remove(rNum)==null) {	//입력받은 방번호가 키로 hotelMap에서 입력을 받은 적있나 확인 입력받은 적이 없으면 null이 나온다. null이 아니면 방번호가 키인 정보를 삭제한다.
				System.out.println(rNum+"호는 체크아웃 할 수 없습니다.");
			}else {
				System.out.println(rNum+"호를 체크아웃 하였습니다.");
		
			}		
			System.out.println("");
			System.out.println("");

			
		}
		//객실 상태 확인하는 메소드
		public static void room() {
		
			//hotelMap에 담긴 정보를 모두 출력 하여야 해서 keySet키의  iterator()메서드로 모든 키를 받아온다.
			Set<Integer> keySet = hotelMap.keySet();
			System.out.println("=====================================================");
			if(keySet.size()==0) {	//keySet의 size()메서드로 저장된 값이 있는지 확인  'keySet.size()==0'이면 저장된 정보가 없음.			
				System.out.println("체크인 한 사람이 아무도 없습니다.");
			}else {
				//Set의 데이터를 가져오기 위해 Iterator객체를 얻어와야한다.
				// => Set의 iterator() 메서드를 호출하면 된다.
				Iterator<Integer> it = keySet.iterator();
				System.out.println("▼ 현재 객실 상태 ▼");
				//데이터 개수만큼 반복하기
				while (it.hasNext()) {//hasNext()메서드 => 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환한다
					int rNum =it.next();	//next()메서드 => 포인터 다음 자료위치로 이동하고, 이동한 위치의 자료를 반환한다.
					Hotel h = hotelMap.get(rNum);	//it.next로 반환 받은 rNum(방번호)를 키로 hotelMap에서 get으로 갖고와 h변수에 저장
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
		
		
		/**
		 * 최초로 파일이 없을 경우 파일 생성, 파일이 있을경우 넘어가는 메서드
		 */
		private static void newFile() {
			File hotel = new File("d:/D_Other/hotel/roomState.bin");
			
			if(hotel.exists()) {	//roomState 있으면 그냥 리턴  
									//파일명.exists() 파일 존재 확인 하는 것 있으면 true
				return;
			}else {			//roomState 파일이 없으면 파일을 생성해줘야한다.
				
				try {
					ObjectOutputStream room = new ObjectOutputStream(
							new BufferedOutputStream(
							new FileOutputStream("d:/D_Other/hotel/roomState.bin")));
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * 호텔 문이 열릴시 기존 정보를 받아오는 메서드
		 */
		private static void receive()   {
			try {
				ObjectInputStream ois;
				ois = new ObjectInputStream(
						new BufferedInputStream(
						new FileInputStream("d:/D_Other/hotel/roomState.bin")));
			
				Object obj;
				
				while ((obj = ois.readObject())!=null) {
					hotelMap = (Map<Integer,Hotel>)obj;	//케스팅 후에 저장 
				}
				ois.close();
				
			}catch (IOException e) {
		
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 업무 종료시 파일에 정보를 저장 하는 메서드
		 */
		private static void close() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream("d:/D_Other/hotel/roomState.bin")));
				oos.writeObject(hotelMap);				
				oos.close();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
}



/**
 * 방번호 투숙객이름을 저장하는 클래스
 *
 */
class Hotel implements Serializable{
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
