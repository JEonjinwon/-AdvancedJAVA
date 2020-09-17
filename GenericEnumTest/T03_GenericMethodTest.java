package kr.or.ddit.basic;




/**
 * 재너릭 메서드 <T,R> R method(T t)
 * 
 * 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드 
 * 선언방법 : 리턴타입앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용함.
 * 
 * @param p1
 * @param p2
 * @return
 */
class Util {
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getValue().equals(p2.getValue());

		return keyCompare && valueCompare;
	}
}


/**
 * 
 * 멀티타입 <K,V> 가지는 제너릭 클래스 
 *
 * @param <K>
 * @param <V>
 */
class Pair<K, V> {
	private K Key;
	private V value;

	public Pair(K key, V value) {
		super();
		Key = key;
		this.value = value;
	}

	public K getKey() {
		return Key;
	}

	public void setKey(K key) {
		Key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	//키와 값을 출력하는 메서드
	public <K,V> void displayAll(K key, V value) {
		System.out.println(key.toString()+" : " +value);
	}
	
}

public class T03_GenericMethodTest {

	public static void main(String[] args) {
		
		Pair<Integer,String> p1 = new Pair<Integer,String>(1,"홍길동");
		Pair<Integer,String> p2 = new Pair<Integer,String>(1,"홍길동");
		
		//구체적 타입을 명시적을 지정 
		boolean result1 = Util.<Integer,String>compare(p1, p2);
		
		if(result1) {
			System.out.println("논리적(의미)으로 동일한 객체임");
		}else {
			System.out.println("논리적(의미)으로 동일한 객체아님");
		}
		
		Pair<String,String> p3 = new Pair<String,String>("001", "홍길동");
		Pair<String,String> p4 = new Pair<String,String>("002", "홍길동");
		
		
		boolean result2 = Util.<String,String>compare(p3, p4);
		
		if(result2) {
			System.out.println("논리적(의미)으로 동일한 객체임");
		}else {
			System.out.println("논리적(의미)으로 동일한 객체아님");
		}
		
		//제너릭 메서드 호출
		p1.<String,Integer> displayAll("키값", 1234);
		
		
		
		
		
	}

}
