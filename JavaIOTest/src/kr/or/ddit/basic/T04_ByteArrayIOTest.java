package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	//자료를 읽어올 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			//available()	=> 읽어 올 수 있는 byte수를 반환 
			int len;	//실제 읽어온 byte수를 반환한다.
			while((len=bais.read(temp))!=-1){
				//while(bais.available()>0){
			/*bais.read(temp);	//temp 배열 크기만큼 자료를 읽어와 temp 배열에 저장한다.
			baos.write(temp);  //temp 배열의 내용을 출력한다.
			 */			
				
			//실제로 읽어올 byte수 
			//int len = bais.read(temp);
			
			//temp배열의 내용 중에서 0번째 부터 len개수만큼 출력
			baos.write(temp, 0, len);
			//0이 시작점 len이 몇개 길이
				
			System.out.println("temp : "+Arrays.toString(temp) );
			}
			outSrc = baos.toByteArray();
			
			System.out.println("inSrc => "+Arrays.toString(inSrc));
			System.out.println("outSrc => "+Arrays.toString(outSrc));
			
			//스트림객체 닫아주기
			bais.close();
			baos.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
