package kr.or.ddit.basic;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터 기능 제공 보조 스트림 예제
 */
public class T14_PrintStreamTest  {

	public static void main(String[] args) throws IOException {
	FileOutputStream fos  = new FileOutputStream("d:/D_Other/print.txt");
	FileOutputStream fos2  = new FileOutputStream("d:/D_Other/print2.txt");
	
	//PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브클래스이다.
	//PrintStream은 IOException을 발생시키지 않는다.
	//printIn and print등 메서드 호출 시 마다 autoflush 기능 제공
	
	//PrintStream out = new PrintStream(fos);		//파일로 출력
	PrintStream out = new PrintStream(System.out);	//콘솔에 출력
	out.print("안녕하세요 printStream1 입니다.\r\n");
	out.println("안녕하세요 printStream2 입니다.");
	out.println("안녕하세요 printStream3 입니다.");
	out.println(out);//객체 출력
	out.print(3.14);
	
	out.close();
	/**
	 * PrintStream은 데이터를 문자로 출력하는 기능을 수행함 .(System.out)
	 * 향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨.
	 * 
	 * PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다.
	 * 둘다 기본적으로 autoflush기능이 꺼져 있음	 */
	
	PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos2,"UTF-8"));
	writer.print("안녕하세요  PrintWriter1 입니다.\r\n");
	writer.println("안녕하세요  PrintWriter2 입니다.");
	writer.println("안녕하세요  PrintWriter3 입니다.");
	
	writer.close();
	}

}
