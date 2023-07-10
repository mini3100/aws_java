package ch26_socket.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			Socket socket = new Socket("127.0.0.1", 8000);
			Thread inputThread = new Thread(() -> {
				try {
//					InputStream inputStream = socket.getInputStream();
//					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					while(true) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String requestBody = bufferedReader.readLine();
						System.out.println("내용: "+requestBody);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			inputThread.start();
			
			while(true) {	//계속해서 입력하기 위해 반복
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
				//PrintWriter : 서버와 클라이언트의 통로 역할
				System.out.print("입력: ");
				String input = scanner.nextLine();
				
				printWriter.println(input);	//입력을 하는 순간 output(서버로 보내줌)
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
