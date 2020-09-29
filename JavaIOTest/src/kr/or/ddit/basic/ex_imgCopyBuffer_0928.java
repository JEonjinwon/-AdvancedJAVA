package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ex_imgCopyBuffer_0928 {

	public static void main(String[] args) {
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos =null;
		
		try {
			
			fis=new FileInputStream("d:/D_Other/Tulips.jpg");
			fos = new FileOutputStream("d://D_Other/복사본_Tulips.jpg");
			bos = new BufferedOutputStream(fos,5);
			
			int c;
			long start = System.currentTimeMillis();
			while ((c=fis.read())!=-1) {
				bos.write(c);
			}
			
			long end = System.currentTimeMillis();
			System.out.println("복사에 걸린 시간 : "+(end-start)); // 파일복사 걸린 시간
			
			fis.close();
			bos.close();
			fos.close();
			
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
