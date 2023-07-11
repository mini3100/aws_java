package ch26_socket.simpleGUI.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch26_socket.simpleGUI.client.SimpleGUIClient;
import ch26_socket.simpleGUI.server.dto.RequestBodyDto;
import ch26_socket.simpleGUI.server.dto.SendMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {
	
	private final Socket socket; // RequiredArg
	private String username;

	@Override
	public void run() {
		while (true) {	//계속해서 채팅을 치기 위한 반복
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();

				requestController(requestBody);
//				SimpleGUIServer.connectedSocketList.forEach(connectedSocket -> {
//					PrintWriter printWriter;
//					try {
//						printWriter = new PrintWriter(connectedSocket.socket.getOutputStream(), true); 
//						// getter를 통해 ConnectedSocket의 socket을 받아옴
//						printWriter.println(requestBody);
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//
//				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void requestController(String requestBody) {	//클래스 안에서만 쓸 것이므로 private
		Gson gson = new Gson();
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();
		
		switch(resource) {
			case "join":
				username = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();	//private 전역 변수에 저장
				
				//접속한 유저 List에 추가하는 기능, 접속 메시지 띄우는 기능
				SimpleGUIServer.connectedSocketList.forEach(connectedSocket -> {
					List<String> usernameList = new ArrayList<>();
					
					SimpleGUIServer.connectedSocketList.forEach(con -> {	//client가 접속할 때마다 usernameList를 새로 만듦.
						usernameList.add(con.username);
					});
					
					RequestBodyDto<List<String>> updateUserListDto = new RequestBodyDto<List<String>>("updateUserList", usernameList);
					RequestBodyDto<String> joinMessageDto = new RequestBodyDto<String>("showMessage", username + "님이 접속했습니다.");
					
					ServerSender.getInstance().send(connectedSocket.socket, updateUserListDto);
					try {
						Thread.sleep(100);	//send가 동시에 동작하게 되면 밑에게 동작 안할 수도 있음
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ServerSender.getInstance().send(connectedSocket.socket, joinMessageDto);	
				});
				
				break;
			case "sendMessage" :
				TypeToken<RequestBodyDto<SendMessage>> typeToken = new TypeToken<>() {};
				RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, typeToken.getType());
				//RequestBodyDto의 제네릭 타입까지 SendMessage로 바꾸려면 typeToken을 쓰는 것이 필요
				SendMessage sendMessage = requestBodyDto.getBody();

				SimpleGUIServer.connectedSocketList.forEach(connectedSocket -> {
					RequestBodyDto<String> dto = new RequestBodyDto<String>("showMessage"
							, sendMessage.getFromUsername()+": "+sendMessage.getMessageBody());
					ServerSender.getInstance().send(connectedSocket.socket, dto);
				});
				
				break;
		
		}
	}

}
