package ch26_socket.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApplication extends JFrame {
	
	private Socket socket;

	private JPanel mainPanel;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField messageTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApplication frame = new ClientApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientApplication() {
		setTitle("chat program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(203, 215, 224));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		
		// <<< 채팅 내용 >>>
		JScrollPane chatTextAreaScrollPane = new JScrollPane();
		chatTextAreaScrollPane.setBounds(12, 10, 374, 406);
		mainPanel.add(chatTextAreaScrollPane);
		
		JTextArea chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);	//채팅한 내용은 수정할 수 없도록
		chatTextAreaScrollPane.setViewportView(chatTextArea);
		
		// <<< 채팅 연결 >>>
		JLabel ipLabel = new JLabel("ip");
		ipLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ipLabel.setBounds(398, 8, 176, 21);
		mainPanel.add(ipLabel);
		
		ipTextField = new JTextField();
		ipTextField.setBounds(398, 33, 176, 28);
		mainPanel.add(ipTextField);
		ipTextField.setColumns(10);
		
		JLabel portLabel = new JLabel("port");
		portLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		portLabel.setBounds(398, 65, 176, 21);
		mainPanel.add(portLabel);
		
		portTextField = new JTextField();
		portTextField.setBounds(398, 90, 176, 28);
		mainPanel.add(portTextField);
		portTextField.setColumns(10);
		
		JButton connectedButton = new JButton("접속");
		connectedButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		connectedButton.setBounds(398, 130, 176, 28);
		connectedButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String serverIp = ipTextField.getText();
				String serverPort = portTextField.getText();
				
				if(serverIp.isBlank() || serverPort.isBlank()) {	//ip나 port를 입력하지 않았을 때
					JOptionPane.showMessageDialog(
							mainPanel
							, "IP와 PORT번호를 입력해주세요."
							,"접속 오류"
							,JOptionPane.ERROR_MESSAGE);
					return;	//mouseClicked 이벤트 빠져나감.
				}
				
				try {
					socket = new Socket(serverIp, Integer.parseInt(serverPort));
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		mainPanel.add(connectedButton);
		
		// <<< 접속자 >>>
		JScrollPane connectedUserListScrollPane = new JScrollPane();
		connectedUserListScrollPane.setBounds(398, 171, 176, 246);
		mainPanel.add(connectedUserListScrollPane);
		
		JList connectedUserList = new JList();
		connectedUserListScrollPane.setViewportView(connectedUserList);
		
		// <<< 메세지 입력 및 전송 >>>
		messageTextField = new JTextField();
		messageTextField.setBounds(12, 426, 450, 28);
		messageTextField.setEditable(false);	//접속 전에 메시지 입력창 비활성화
		mainPanel.add(messageTextField);
		messageTextField.setColumns(10);
		
		JButton messageSendButton = new JButton("전송");
		messageSendButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		messageSendButton.setBounds(474, 427, 100, 27);
		messageSendButton.setEnabled(false); 	//접속 전에 전송 버튼을 비활성화
		mainPanel.add(messageSendButton);
	}
}
