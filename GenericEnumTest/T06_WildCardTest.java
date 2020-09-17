package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06_WildCardTest {

	public static void main(String[] args) {
		
		//FruitBox2<? extends Fruit> fruitBox1 = new FruitBpx2<Fruit>);
		FruitBox2<?> fruitBox1 = new FruitBox2();
		FruitBox2<?> fruitBox2 = new FruitBox2();//위와 동일 
		
		//FruitBox2<?>는  FruitBox2<? extends Fruit>를 의미함 
	//	FruitBox2<?> fruitBox3 = new FruitBox2<Object>();
		
		// 두 타입 (Object, Fruit)이 일치하지 않음 
	//	FruitBox2<Object> fruitBox4 = new FruitBox2<Fruit>(); 
		
		
		FruitBox2<?> fruitBox5 = new FruitBox2<Fruit>(); 
		FruitBox2<? extends Fruit> fruitBox6 = new FruitBox2<Apple>(); 
		
		//new 연산자는 타입이 명확해야 객체생성을 할 수 있다.(와일드 카드 사용 불가)
	//	FruitBox2<? extends Object> fruitBox7 = new FruitBox2<? extends Object>(); 
		
	}
}

/**
 * 과일상자2
 *
 * @param <T>
 */
class FruitBox2<T extends Fruit>{
	List<T> itemList = new ArrayList<>();
	
	
	public void addItem(T item) {
		this.itemList.add(item);
	}


	public List<T> getItemList() {
		return itemList;
	}


	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}


}