package ch26_socket.simpleGUI.client;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ch26_socket.simpleGUI.client.dto.RequestBodyDto;
import ch26_socket.simpleGUI.client.dto.SendMessage;
import lombok.Getter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter//모든 멤버 변수들에 Getter가 생성
public class SimpleGUIClient extends JFrame {

	// 싱글톤
	private static SimpleGUIClient instance;

	public static SimpleGUIClient getInstance() {
		if (instance == null) {
			instance = new SimpleGUIClient(); // Client 생성(최초의 한 번)
		}
		return instance;
	}

	// 변수
	private String username; // 채팅하는 사람 이름
	private Socket socket;

	// mainCard
	private CardLayout mainCardLayout;
	private JPanel mainCardPanel;

	// chattingRoomList
	private JPanel chattingRoomListPanel;
	private JScrollPane roomListScrollPanel;
	private DefaultListModel<String> roomListModel;
	private JList roomList;

	// chattingRoom
	private JPanel chattingRoomPanel;
	private JTextField messageTextField;
	private JTextArea chattingTextArea;
	private JScrollPane userListScrollPane;
	private DefaultListModel<String> userListModel;
	private JList userList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleGUIClient frame = SimpleGUIClient.getInstance(); // Client 생성
					frame.setVisible(true);

					ClientReceiver clientReceiver = new ClientReceiver();
					clientReceiver.start(); // Thread 작동

					// 접속 이벤트
					RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("connection", frame.username);
					ClientSender.getInstance().send(requestBodyDto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private SimpleGUIClient() {
		username = JOptionPane.showInputDialog(chattingRoomPanel, "아이디를 입력하세요.");

		if (Objects.isNull(username)) {
			System.exit(0);
		}
		if (username.isBlank()) {
			System.exit(0);
		}
		try {
			// 소켓 연결
			socket = new Socket("127.0.0.1", 8000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 350);

		mainCardLayout = new CardLayout();
		mainCardPanel = new JPanel();
		mainCardPanel.setLayout(mainCardLayout);
		setContentPane(mainCardPanel);

		// <<< chattingRoomList >>>
		chattingRoomListPanel = new JPanel();
		chattingRoomListPanel.setBackground(new Color(168, 186, 198));
		chattingRoomListPanel.setLayout(null);
		mainCardPanel.add(chattingRoomListPanel, "chattingRoomListPanel");

		JButton createRoomButton = new JButton("방 만들기");
		createRoomButton.setBounds(10, 10, 100, 30);
		createRoomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String roomName = JOptionPane.showInputDialog(chattingRoomListPanel,"방제목을 입력하세요.");
				if(Objects.isNull(roomName)) {	//취소 버튼을 눌렀을 때
					return;
				}
				if(roomName.isBlank()) {		//방제목을 입력하지 않았을 때
					JOptionPane.showMessageDialog(chattingRoomListPanel, "방제목을 입력하세요.", "방만들기 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				for(int i = 0; i < roomListModel.size(); i++) {
					if(roomListModel.get(i).equals(roomName)) {
						JOptionPane.showMessageDialog(chattingRoomListPanel, "이미 존재하는 방제목입니다.", "방만들기 실패", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("createRoom", roomName);
				ClientSender.getInstance().send(requestBodyDto);
				mainCardLayout.show(mainCardPanel, "chattingRoomPanel");	//패널 전환
				
				requestBodyDto = new RequestBodyDto<String>("join", roomName);
				ClientSender.getInstance().send(requestBodyDto);
			}
		});
		chattingRoomListPanel.add(createRoomButton);

		roomListScrollPanel = new JScrollPane();
		roomListScrollPanel.setBounds(12, 50, 512, 253);
		chattingRoomListPanel.add(roomListScrollPanel);
		
		roomListModel = new DefaultListModel<String>();
		roomList = new JList(roomListModel);
		roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //더블 클릭
					String roomName = roomListModel.get(roomList.getSelectedIndex());
					mainCardLayout.show(mainCardPanel, "chattingRoomPanel");	//패널 전환
					RequestBodyDto<String> requestBodyDto = new RequestBodyDto<String>("join", roomName);
					ClientSender.getInstance().send(requestBodyDto);
				}
			}
		});
		roomListScrollPanel.setViewportView(roomList);

		// <<< chattingRoom >>>
		chattingRoomPanel = new JPanel();
		chattingRoomPanel.setBackground(new Color(168, 186, 198));
		chattingRoomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chattingRoomPanel.setLayout(null);
		mainCardPanel.add(chattingRoomPanel, "chattingRoomPanel");

		JScrollPane chattingTextAreaScrollPanel = new JScrollPane();
		chattingTextAreaScrollPanel.setBounds(12, 10, 354, 255);
		chattingRoomPanel.add(chattingTextAreaScrollPanel);

		chattingTextArea = new JTextArea();
		chattingTextAreaScrollPanel.setViewportView(chattingTextArea);

		messageTextField = new JTextField();
		messageTextField.setBounds(12, 275, 512, 28);
		messageTextField.setColumns(10);
		messageTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					SendMessage sendMessage = SendMessage.builder().fromUsername(username)
							.messageBody(messageTextField.getText()).build();
					RequestBodyDto<SendMessage> requestBodyDto = new RequestBodyDto<>("sendMessage", sendMessage);
					
					ClientSender.getInstance().send(requestBodyDto);
					
					messageTextField.setText(""); // 전송후 텍스트필드 비우기
				}
			}
		});
		chattingRoomPanel.add(messageTextField);

		userListScrollPane = new JScrollPane();
		userListScrollPane.setBounds(378, 10, 146, 255);
		chattingRoomPanel.add(userListScrollPane);

		userListModel = new DefaultListModel<>();
		userList = new JList(userListModel);
		userListScrollPane.setViewportView(userList);

	}
}
