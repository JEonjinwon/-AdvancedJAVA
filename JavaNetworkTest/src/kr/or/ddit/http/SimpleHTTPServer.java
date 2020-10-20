package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SimpleHTTPServer {
	

	private static final Logger LOGGER = Logger.getLogger("SimpleHTTPServer");
	
	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;
	
	public SimpleHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
		this(data.getBytes(encoding), encoding, mimeType, port);
	}

	public SimpleHTTPServer(byte[] data, String encoding, String mimeType, int port) {
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n"
			+ "Server: SimpleHTTPServer 1.0\r\n"
			+ "Content-length: " + this.content.length + "\r\n"
			+ "Content-type: " + mimeType + "; charset=" + encoding + "\r\n\r\n";
		this.header = header.getBytes(Charset.forName("US-ASCII"));	
	}
	
	public void start() {
		try (ServerSocket server = new ServerSocket(this.port)) {
			LOGGER.setLevel(Level.INFO);
			LOGGER.info("Accepting connections on port " + server.getLocalPort());
			LOGGER.info("Data to be sent:");
			LOGGER.info(new String(this.content, encoding));
			
			while(true) {
				try {
					Socket socket = server.accept();
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
					
				} catch(IOException ex) {
					LOGGER.log(Level.WARNING, "Exception accepting connection", ex);
				}catch(RuntimeException ex) {
					LOGGER.log(Level.SEVERE, "Unexpected error", ex);
				}
			}
		}catch(IOException ex) {
			LOGGER.log(Level.SEVERE, "Could not start server", ex);
		}
	}
	
	private class HttpHandler implements Runnable {
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				OutputStream out = new BufferedOutputStream(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				// 여기서는 필요한 첫번째 줄만 읽는다.
				StringBuilder request = new StringBuilder();
				while (true) {
					String str = br.readLine();
					
					//if (c == '\r' || c == '\n' || c == -1) break;
					if (str.equals("")) break; // 빈줄이 포함되었으면 중지
					request.append(str + "\n");
				}
				
				System.out.println("요청헤더:\n" + request.toString());
				
				// HTTP/1.0 이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				if (request.toString().indexOf("HTTP/") != -1) {
					out.write(header);
				}
				
				System.out.println("응답헤더:\n" + new String(header));
				
				out.write(content);
				out.flush();
				
			}catch (IOException ex) {
				LOGGER.log(Level.WARNING, "Error writing to client", ex);
			}finally {
				/*try {
					socket.close(); // 소켓 닫기(연결 끊기)
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			}
		}
	}
	
	public static void main(String[] args) {
		// 대기(listen)할 포트를 설정한다.
		int port;
		try {
			port = Integer.parseInt(args[1]);
			if(port < 1 || port > 65535) port = 80;
		}catch(RuntimeException ex) {
			port = 80;
		}
		
		String encoding = "UTF-8";
		if(args.length > 0) encoding = args[2];
		
		FileInputStream fis = null;
		try {
			//Path path = Paths.get(args[0]);
			//byte[] data = Files.readAllBytes(path);
			File file = new File(args[0]);
			byte[] data = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(data);
			
			// 해당 파일이름을 이용하여	Content-type 정보 추출하기
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
			System.out.println("contentType => " + contentType);
			SimpleHTTPServer server = new SimpleHTTPServer(data, encoding, contentType, port);
			server.start();
			
		}catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java SimpleHTTPServer filename port encoding");
		}catch(IOException ex){
			LOGGER.severe("IO Error: " + ex.getMessage());
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
