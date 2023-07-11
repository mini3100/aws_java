package ch26_socket.simpleGUI.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class SimpleGUIServer {
	
	public static List<ConnectedSocket> connectedSocketList = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("[ 서버 실행 ]");
			
			while(true) {	//클라이언트 여러 개를 접속하기 위한 반복
				Socket socket = serverSocket.accept();
				//클라이언트가 접속할 때마다 connectedSocket(Thread) 생성, 리스트에 추가
				ConnectedSocket connectedSocket = new ConnectedSocket(socket);
				connectedSocketList.add(connectedSocket);
				connectedSocket.start();	//Thread start
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
