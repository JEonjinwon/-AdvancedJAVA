package kr.or.ddit.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadServerlet")
public class DownloadServerlet extends HttpServlet {
	
	private static final String DOWNLOAD_DIR = "d:/D_Other/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = req.getParameter("fileName");
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기 
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		
		/**
		 * Content-Disposition 해더에 대하여
		 * 1. response 헤더에 사용되는 경우 ...ex)파일 다운로드
		 * 	Content-Disposition : inline(default)
		 * 	Content-Disposition : attachment
		 * 	Content-Disposition : attachment; filename="filename.jpg"
		 * 	
		 * 2. multipart body를 위한 헤더 정보로 사용되는 경우 ... ex)파일 업로드
		 * 	Content-Disposition : form-data
		 * 	Content-Disposition : form-data; name ="fieldName"
		 * 	Content-Disposition : form-data; name ="fieldName"; filename="filename.jpg"
		 */
		
		BufferedInputStream bis = new BufferedInputStream(
									new FileInputStream(DOWNLOAD_DIR + fileName));
		
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int readBytes = 0;
		while ((readBytes=bis.read())!=-1) {
			bos.write(readBytes);
		}
		bis.close();
		bos.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
