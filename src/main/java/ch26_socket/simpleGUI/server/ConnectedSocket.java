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
import ch26_socket.simpleGUI.server.entity.Room;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConnectedSocket extends Thread {

	private final Socket socket; // RequiredArg
	private Gson gson;

	private String username;

	@Override
	public void run() {
		gson = new Gson();
		while (true) { // 계속해서 채팅을 치기 위한 반복
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String requestBody = bufferedReader.readLine();
				requestController(requestBody);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void requestController(String requestBody) { // 클래스 안에서만 쓸 것이므로 private
		String resource = gson.fromJson(requestBody, RequestBodyDto.class).getResource();

		switch (resource) {
		case "connection":
			connection(requestBody);
			break;

		case "createRoom":
			createRoom(requestBody);
			break;

		case "join":
			join(requestBody);
			break;

		case "sendMessage":
			sendMessage(requestBody);
			break;
		}
	}

	private void connection(String requestBody) {
		// 접속 될 때 ConnectedSocket마다 username을 부여
		username = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody(); // private 전역 변수에 저장
		// 접속 했을 때 서버에 저장된 roomList가 뜨도록
		List<String> roomNameList = new ArrayList<>();
		Server.roomList.forEach(room -> {
			roomNameList.add(room.getRoomName());
		});

		RequestBodyDto<List<String>> updateRoomListRequestBodyDto = new RequestBodyDto<List<String>>("updateRoomList",
				roomNameList);

		ServerSender.getInstance().send(socket, updateRoomListRequestBodyDto); // 자기 자신만 업데이트(forEach 안 씀)
	}

	private void createRoom(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();

		Room newRoom = Room.builder().roomName(roomName).owner(username) // 방 만들기를 누른 사람이 owner이므로 해당 소켓의 username이
																			// 들어가면 됨.
				.userList(new ArrayList<ConnectedSocket>()).build();

		Server.roomList.add(newRoom);

		List<String> roomNameList = new ArrayList<>(); // 방 이름들을 담는 list
		Server.roomList.forEach(room -> {
			roomNameList.add(room.getRoomName());
		});

		RequestBodyDto<List<String>> updateRoomListRequestBodyDto = new RequestBodyDto<List<String>>("updateRoomList",
				roomNameList);

		Server.connectedSocketList.forEach(con -> {
			ServerSender.getInstance().send(con.socket, updateRoomListRequestBodyDto);
		});
	}

	private void join(String requestBody) {
		String roomName = (String) gson.fromJson(requestBody, RequestBodyDto.class).getBody();

		Server.roomList.forEach(room -> {
			if (room.getRoomName().equals(roomName)) { // 들어가고자 하는 방 이름과 같은가?
				room.getUserList().add(this); // 자기 자신(ConnectedSocket)을 userList에 추가

				List<String> usernameList = new ArrayList<>();

				room.getUserList().forEach(con -> { // 방 안의 유저 이름 리스트
					usernameList.add(con.username);
				});

				room.getUserList().forEach(connectedSocket -> { // 방 안의 사람들에게만 유저리스트 업데이트, 접속메시지 전송
					RequestBodyDto<List<String>> updateUserListDto = new RequestBodyDto<List<String>>("updateUserList",
							usernameList);
					RequestBodyDto<String> joinMessageDto = new RequestBodyDto<String>("showMessage",
							username + "님이 접속했습니다.");

					ServerSender.getInstance().send(connectedSocket.socket, updateUserListDto);
					try {
						Thread.sleep(100); // send가 동시에 동작하게 되면 밑에게 동작 안할 수도 있음
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ServerSender.getInstance().send(connectedSocket.socket, joinMessageDto);
				});

			}
		});
	}

	private void sendMessage(String requestBody) {
		TypeToken<RequestBodyDto<SendMessage>> typeToken = new TypeToken<>() {
		};
		RequestBodyDto<SendMessage> requestBodyDto = gson.fromJson(requestBody, typeToken.getType());
		// RequestBodyDto의 제네릭 타입까지 SendMessage로 바꾸려면 typeToken을 쓰는 것이 필요
		SendMessage sendMessage = requestBodyDto.getBody();

		Server.roomList.forEach(room -> {	//roomList의 room객체 - userList<ConnectedSocket>
			//userList 안에 해당 클라이언트의 ConnectedSocket이 들어 있는지 : 방 안에 유저가 있는지
			if (room.getUserList().contains(this)) {	
				room.getUserList().forEach(connectedSocket -> {
					RequestBodyDto<String> dto = new RequestBodyDto<String>("showMessage",
							sendMessage.getFromUsername() + ": " + sendMessage.getMessageBody());
					ServerSender.getInstance().send(connectedSocket.socket, dto);
				});
			}
		});
	}
}
