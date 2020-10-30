package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload모듈을 이용한 파일 업로드 예제
 */
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOADD_DIR ="upload_files";
	//메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD =1024*1024*3;
	//파일 1개당 최대크기
	private static final long MAX_FILE_SIZE = 1024*1024*40;
	//요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024*1024*40;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//인코딩 타입이 mulitipart/form-data인 경우...
		if(ServletFileUpload.isMultipartContent(req)) {
			Map<String, String> formMap = new HashMap<String, String>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			//웹 애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			String uploadPath  = getServletContext().getRealPath("")+File.separator+UPLOADD_DIR;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				List<FileItem>formItems = upload.parseRequest(req);
				if(formItems != null && formItems.size()>0) {
					for (FileItem item : formItems) {
						if(!item.isFormField()) {//파일인 경우
							//전체 경로를 제외한 파일명만 추출하기
							String fileName =new File(item.getName()).getName();
							String filePath = uploadPath +File.separator+fileName;
							File storeFile = new File(filePath);
							item.write(storeFile);	//업로드 파일 저장.
							req.setAttribute("message", "업로드 완료된 => "+fileName);
						}else {// 폼필드인 경우 ...
							formMap.put(item.getFieldName(), item.getString());
						}
					}
				}
			
			}catch (Exception e) {
				req.setAttribute("message", "예외발생 : "+e.getMessage());
			}
			for(Entry<String, String>entry : formMap.entrySet()) {
				System.out.println("파라미터 명 : "+entry.getKey());
				System.out.println("파라미터 값 : "+entry.getValue());
			}
			
			System.out.println("안꺼내짐"+req.getParameter("sender"));
			System.out.println("꺼내짐"+formMap.get("sender"));
			
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨");
		}
	
	}
}
