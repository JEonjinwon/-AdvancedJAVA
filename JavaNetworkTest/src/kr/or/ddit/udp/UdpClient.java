package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpClient {

	//시작메서드
	public void start() throws IOException, UnknownHostException {
		 DatagramSocket datagramSocket = new DatagramSocket(); // 소캣객체 생성
		 InetAddress serverAddress = InetAddress.getByName("192.168.45.2");
		 
		 // 데이터가 저장될 공간으로 byte배열을 생성한다.(패킷 수신용)
		 byte[] msg = new byte[100];
		 
		 DatagramPacket outPacket = new DatagramPacket(msg, 1/*1byte만 넣어줌 원래는 ~~.length*/, serverAddress, 8888);
		 DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		 
		 datagramSocket.send(outPacket); // 패킷 전송
		 datagramSocket.receive(inPacket); // 패킷 수신
		 
		 System.out.println("현재 서버 시간 => " + new String(inPacket.getData())); //패킷에서 데이터를 갖고올때 getData()메서드 사용
		 
		 datagramSocket.close(); // 소켓 종료
		 } 
	
	
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}

}
