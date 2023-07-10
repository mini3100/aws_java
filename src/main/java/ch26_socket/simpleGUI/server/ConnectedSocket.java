package ch26_socket.simpleGUI.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {
	@Getter		//ConnectedSocket 리스트의 socket 변수로 outputStream을 쓰기 위해 Getter 사용
	private final Socket socket; // RequiredArg

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				
				List<ConnectedSocket> connectedSocketList = SimpleGUIServer.connectedSocketList;
				connectedSocketList.forEach(s -> {	//s : ConnectedSocket
					PrintWriter printWriter;
					try {
						printWriter = new PrintWriter(s.getSocket().getOutputStream(), true);	//getter를 통해 ConnectedSocket의 socket을 받아옴
						printWriter.println(requestBody);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				});
			} catch (SocketException e) {
				System.out.println("소켓 종료");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
