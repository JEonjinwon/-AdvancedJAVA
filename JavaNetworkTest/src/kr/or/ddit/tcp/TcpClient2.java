package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",7777);
		System.out.println("서버에 연결되었습니다...");
		System.out.println(socket);
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}
