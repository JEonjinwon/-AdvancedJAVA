package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 성능향상을 위한 보조스트림 예제
 */
public class T11_BufferedIOTest {

	public static void main(String[] args) {
		//입출력의 성능 향상을 위해서 버퍼를 이용하는 보조스트림 
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼와 크기가 8192(8kb)로 설정된다
			
			//버퍼의 크기가 5인 스트림 객체생성
			bos = new BufferedOutputStream(fos,5);//5개가 차면 넘겨주기 떄문에 9byte는 2번 IO작업 하면된다.
			for (int i = '1'; i <= '9'; i++) {//숫자 자체를 문자로 지정하기 위해 ''사용
				bos.write(i);
			}
			
			//9번 반복하고 버퍼가 5이면 5개 채워서 한번 IO하고 두번째는 4개만 차니깐 IO를 안해서 flush()로 마지막에 IO
			bos.flush();// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시켜야한다.		
						// (close 시 자동 호출)
			
			bos.close();
			System.out.println("작업 끝 ....");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
