package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer2 {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버 준비완료 ....");
		
		Socket socket =server.accept();//클라이언트와 통신이 될때까지 대기
		
		System.out.println(socket.toString());
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
		
	}

}
