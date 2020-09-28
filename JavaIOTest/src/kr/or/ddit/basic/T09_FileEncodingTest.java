package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/**
	 *	InputStreamReader 객체는 파일의 인코딩 방식을 지정할 수 있다.
	 *	형식) new InputStreamReader(바이트기반 스트림객체, 인코딩방식)
	 *
	 *		- 인코딩 방식
	 *			한글인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
	 *			원래 한글윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서 EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
	 *			CP949는 EUC-KR의 확장이며, 하위호환성이 있다.
	 *
	 *		- MS949 => 원도우의 기본한글 인코딩 방식(ANSI개열)
	 *		- UTF-8	=> 유니코드 UTF-8인코딩 방식(영문자 및 숫자 : 1byte, 한글 3byte) =>가번적
	 *		- US-ASCII => 영어전용 인코딩방식
	 *
	 *		ANSI는 영어를 표기하기 위해 만든 코드 규격으로 자체에 한글이 없었다가 나중에 여기에 EUC-KR(유닉스 개열), CP949(원도우)이라는 식으로 한글이 포함됨.
	 *
	 */

	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		FileInputStream fis =null;
		InputStreamReader isr = null;
		
		try {
			/**
			 * FileInputStream객체를 생성한 후 이객체를 매개변수로 받는 InputStreamReader객체를 생성한다.
			 * (바이트 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환시킨다.)
			 */
			fis = new FileInputStream("d://D_Other/test_utf8.txt");
			//fis = new FileInputStream("d://D_Other/test_ansi.txt");
			isr = new InputStreamReader(fis,"CP949");
			
			int c ;
			while ((c=isr.read())!=-1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력끝...");
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
