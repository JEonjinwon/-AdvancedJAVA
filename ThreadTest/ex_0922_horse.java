package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

오름차순 sort
경기가 끝나면 등수 순으로 출력한다.

스레드로 말 클래스 만들고 전역변수로 발이름, 등수
 */
public class ex_0922_horse {
	static int strRank=1;

	public static void main(String[] args) {
		List<Horse> hlist = new ArrayList<>();
		
		
		hlist.add(new Horse("달팽이","🐌"))	;
		hlist.add(new Horse("고릴라","🦍"))	;
		hlist.add(new Horse("무당벌래","🐞"))	;
		hlist.add(new Horse("거북이","🐢"))	;
		hlist.add(new Horse("사슴","🦌"))	;
		hlist.add(new Horse("뱀","🐍"))	;
		hlist.add(new Horse("낙타","🐫"))	;
		hlist.add(new Horse("독수리","🦅"))	;
		hlist.add(new Horse("병아리","🐤"))	;
		hlist.add(new Horse("고슴도치","🦔"));
		for (Horse horse : hlist) {
			horse.start();
		}
		
		
		for (Horse hs : hlist) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Collections.sort(hlist);
		System.out.println("경기끝 ....");
		System.out.println("======================================================");
		System.out.println();
		System.out.println(" 경기 결과 ");
		

	for (Horse horse : hlist) {
		
		System.out.println(horse.getName1()+" "+horse.getIcon()+" "+horse.getRank()+"등");
	
	}
		
		
	}

}

class Horse extends Thread implements Comparable<Horse>{
	private String name1;
	int rank;
	private String icon;
	
	public Horse(String name, String icon) {
		this.name1 = name;
		this.icon= icon;
	}
	
	public String getName1() {
		return name1;
	}

	public void setName1(String name) {
		this.name1 = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public void run() {
		int count = 0;
		do {
			
		
			for (int i = 0; i <50; i++) {
				System.out.println("\n"+name1 +" : ");
				for (int j = 0; j<i; j++) {
					System.out.print("_");
				}
				
				
				System.out.print(icon);
			
				
				for (int j = 50; j > i-1; j--) {
					System.out.print("_");
					
					
				}
				System.out.println();
				System.out.println();
				System.out.println();
				
				try {
					Thread.sleep((int)(Math.random()*1500+10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}System.out.println(name1+" 끝");
			
			setRank(ex_0922_horse.strRank);
			ex_0922_horse.strRank++;
			
		}while(count==50);
	
	}

	@Override
	public int compareTo(Horse h1) {
		if(rank >h1.getRank()) {
			return 1;
		}
		else {
			return -1;
		}
		
	}
	
	
	
}
