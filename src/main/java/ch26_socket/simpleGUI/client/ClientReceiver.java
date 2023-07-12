package ch26_socket.simpleGUI.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.google.gson.Gson;

import ch26_socket.simpleGUI.client.dto.RequestBodyDto;

//Server에서 보내준 데이터를 받아오는 역할
public class ClientReceiver extends Thread {

	@Override
	public void run() {
		SimpleGUIClient simpleGUIClient = SimpleGUIClient.getInstance();
		while (true) {
			try {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(simpleGUIClient.getSocket().getInputStream()));
				String requestBody = bufferedReader.readLine();
				
				requestController(requestBody);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	//Server에서 받아온 데이터를 클라이언트들에게 전송
	private void requestController(String requestBody) {
		Gson gson = new Gson();
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();
		
		switch(resource) {
			case "updateRoomList":
				List<String> roomList = (List<String>) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
				SimpleGUIClient.getInstance().getRoomListModel().clear();
				SimpleGUIClient.getInstance().getRoomListModel().addAll(roomList);
				break;
			case "showMessage":
				String messageContent = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();
				SimpleGUIClient.getInstance().getChattingTextArea().append(messageContent + "\n");
				break;
			case "updateUserList":
				List<String> usernameList = (List<String>) gson.fromJson(requestBody, RequestBodyDto.class).getBody();				
				SimpleGUIClient.getInstance().getUserListModel().clear();				//리스트 초기화 시키고
				SimpleGUIClient.getInstance().getUserListModel().addAll(usernameList);	//새로 받아온 리스트를 addAll
				break;
		}
	}
}
