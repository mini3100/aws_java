package ch26_socket.server;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ConnectedClientController {
	//싱글톤 패턴
	private static ConnectedClientController instance;
	
	@Getter	//변수 위에 달면 해당 변수만 getter 생성
	private List<ConnectedSocket> connectedSockets;
	
	private ConnectedClientController() {
		connectedSockets = new ArrayList<>();	//싱글톤 : 최초의 한 번만 생성
	}
	
	public static ConnectedClientController getInstance() {
		if(instance == null) {
			instance = new ConnectedClientController();
		}
		return instance;
	}
}
