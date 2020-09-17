package kr.or.ddid.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class T02_ListSortTest {

	/*
	 * 정렬과 관련된 interface는 Comparable과 Comparator가 존재한다.
	 * 	- 보통 객체 자체에서 정렬 기능을 넣기위해서는 Comparable을 구현하고,
	 * 	     정렬 기준을 나만의 방식으로 별도로 구현하고 싶을 때는 Comparator를 구현하여 사용한다.
	 * 
	 * 	- Comparable에서는 compareTo()메서드를 구현해야하고,
	 * 	  Comparator에서는 compare()메서드를 구현해야한다.
	 * 
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		System.out.println("정렬 전 : " +list);
		//정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		//기본적으로 '오름차순 정렬'을 수행한다.
		
		//정렬방식을 변경하려면 정렬방식을 결정하는 객체를 만들어서 
		// Collection.sort()메서드에 변수로 넘겨주면된다.
		Collections.sort(list);
		System.out.println("정렬 후 : "+list);
		
		Collections.shuffle(list);
		System.out.println("자료 섞기 후 : "+list);
		
		//정렬방식을 결정하는 객체(정렬자)를 이용하여 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("정렬 후 : "+list);
		
	}
}
/**
 * 정렬방식을 결정하는 class는 Comparator라는 인터페이스를 구현해야한다.
 * 이 Comparator인터페이스의 compare()라는 메서드를 재정의 하여 구현하면된다.
 *
 */
class Desc implements Comparator<String>{
/**
 * compare()메서드의 변환값을 결정하는 방법
 *  =>이 메서드가 암수를 반환하면 두 값의 순서가 바뀐다.(오름차순이 기본이다.)
 *  
 *  - 오름차순 정렬일 경우 ...
 *  	-> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 한다.
 *  - String객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데 이메서드의 반환 값은 오름차순에 맞게 반환되도록 구현되어 있다.
 *  	(Wrapper클래스의 Date, File 클래스에도 구현되어 있다.)
 *  
 */
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2)*1; //1 대신 -1이면 내림차순 
	}
	
	
	
}