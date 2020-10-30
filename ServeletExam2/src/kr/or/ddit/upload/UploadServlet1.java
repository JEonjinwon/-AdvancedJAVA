package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Oreilly의 MultipartRequest를 이용한 파일 업로드 예제
 * (생성자 호출과 동시에 파일이 생성되기 때문에 선택적인 파일 생성 처리 불가)
 */
public class UploadServlet1 extends HttpServlet {
	//파일업로드는 두겟이 말이 안되서 두포스트만
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
	
		PrintWriter out = resp.getWriter();
		
		String encType ="UTF-8";
		int maxFileSize = 5* 1024* 1024;	//5MB
		
		//MultipartRequest(request, 저장경로[, 인코딩캐릭터셋, 동일한파일명보호여부])
		//DefaultFileRenamePolicy => name.zip, name1.zip, name2.zip, ....
		MultipartRequest mr = new MultipartRequest(req, "d:/D_Other/",maxFileSize, encType, new DefaultFileRenamePolicy());
		
		File file01 = mr.getFile("file01");
		
		System.out.println(file01);//첨부된 파일의 전체 경로 출력
		
		//파라미터값 읽어오기
		System.out.println(mr.getParameter("title"));
		
		out.println("업로드 완료됨. => " +file01.toString()+"\n title => "+mr.getParameter("title"));
		
	}
}
