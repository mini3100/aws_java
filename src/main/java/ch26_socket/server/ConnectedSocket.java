package ch26_socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	//socket 매개변수가 있는 생성자
public class ConnectedSocket extends Thread{

	private final Socket socket;	//final을 붙여서 RequiredArg가 됨
	
	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		try {
			while(true) {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//buffer : 데이터 넘어오자마자 바로 처리
				String requestBody = bufferedReader.readLine();
				System.out.println("입력데이터: "+requestBody);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
