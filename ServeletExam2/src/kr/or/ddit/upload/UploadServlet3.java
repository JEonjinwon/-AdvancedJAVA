package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * 서블릿3부터 지원하는 Part인터페이스를 이용한 파일 업로드 예제
 */
@WebServlet(name="UploadServlet3",urlPatterns= {"/UploadServlet3"})
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1, maxRequestSize=1024*1024*5*5)
public class UploadServlet3 extends  HttpServlet {
	
	private static final String UPLOAD_DIR = "upload_files";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploadPath = getServletContext().getRealPath("")+java.io.File.separator+UPLOAD_DIR;
		File uploadDir = new File(uploadPath);
				
		if(!uploadDir.exists()) {
			uploadDir .mkdir();
		}
		try {
			String fileName="";
			for (Part part : req.getParts()) {
				System.out.println(part.getHeader("content-disposition"));
				fileName = getFileName(part);
				if(fileName != null && !fileName.equals("")){
					part.write(uploadPath+File.separator +fileName);//파일 저장
					System.out.println("파일명 : "+ fileName + " 저장완료 !!!");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("파라미터 값 : " + req.getParameter("sender"));
		resp.setContentType("text/html");
		resp.getWriter().println("업로드 완료 ...!!!");
		
	
	}

	/**
	 * Part객체 파싱하여 파일 이름 추출하기
	 * @param part
	 * @return 파일명 : 파일명이 존재하지 않으면 null (폼필드)
	 */
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=")+2,content.length()-1);
			}
		}
		return null;	//filename이 없는경우 ...(폼필드)
	}
}
