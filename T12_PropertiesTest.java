package kr.or.ddid.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class T12_PropertiesTest {
	/**
	 * Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
	 * Map은 모든 형태의 객체 데이터를 key와 value값으로 사용할 수 있지만 Properties는 key와 value값으로 String만 사용할 수 있다.
	 * 
	 *  Map은 put(), get()메서드를 이용해서 데이터를 입출력 하지만 
	 *  Properties는  setProperty(), getProperty()메서드를 통해서 데이터를 입출력한다.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 *  
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-5014-0707");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름 :"+name);
		System.out.println("전번 :"+tel);
		System.out.println("주소 :"+prop.getProperty("addr"));
	
		//내용 파일로 저장하기
		prop.store(new FileOutputStream("src/kr/or/ddid/basic/test.properties"), "this is comments");
	
	}

}
