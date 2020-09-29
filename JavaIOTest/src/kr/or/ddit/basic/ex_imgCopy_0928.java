package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ex_imgCopy_0928 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos =null;
		
		try {
			
			fis=new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d://D_Other/복사본_Tulips.jpg");
			
			int c;
			long start = System.currentTimeMillis();
			while ((c=fis.read())!=-1) {
			fos.write(c);
			}
			long end = System.currentTimeMillis();
			System.out.println(end-start); // 파일복사 걸린 시간
			
			fis.close();

			fos.close();
			
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
