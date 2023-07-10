package ch26_socket.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer {
	public static void main(String[] args) {
		try {
			System.out.println("서버 실행");
			ServerSocket serverSocket = new ServerSocket(8000);
			List<Socket> socketList = new ArrayList<>();
			
			while (true) {
				Socket socket = serverSocket.accept(); // 클라이언트의 접속 대기
				socketList.add(socket);

				Thread thread = new Thread(() -> { // 스레드 정의, 구현
					Socket threadSocket = socket;
					while (true) { // 클라이언트의 입력을 계속해서 받기 위해 반복(입력 대기)
						System.out.println("데이터 입력 기다림.");
						BufferedReader bufferedReader;
						try {
							InputStreamReader inputStreamReader = new InputStreamReader(threadSocket.getInputStream());
							bufferedReader = new BufferedReader(inputStreamReader);
							String requestBody = bufferedReader.readLine();
							// 클라이언트에 input이 들어오는 순간 getInput(클라이언트에서 읽어옴)

							socketList.forEach(s -> {	//서버에 연결된 소켓들(클라이언트들)에게 모두 읽어온 메세지내용을 전송함
								try {
									PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
									printWriter.println("메세지내용(" + requestBody + ")");
								} catch (IOException e) {
									e.printStackTrace();
								}
							});

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				thread.start(); // 스레드 실행
			}
		} catch (IOException e) {
		}

	}
}
