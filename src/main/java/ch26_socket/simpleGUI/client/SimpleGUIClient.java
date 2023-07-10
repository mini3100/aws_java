package ch26_socket.simpleGUI.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SimpleGUIClient extends JFrame {

	private String username; // 채팅하는 사람 이름
	private Socket socket;

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleGUIClient frame = new SimpleGUIClient();
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
	public SimpleGUIClient() {
		username = JOptionPane.showInputDialog(contentPane, "아이디를 입력하세요.");

		if (Objects.isNull(username)) {
			System.exit(0);
		}
		if (username.isBlank()) {
			System.exit(0);
		}
		try {
			// 소켓 연결
			socket = new Socket("127.0.0.1", 8000);
//			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
//			printWriter.println(username + "님이 접속했습니다.");
			
			Thread inputThread = new Thread(() -> {
				try {
					while(true) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						String requestBody = bufferedReader.readLine();
						textArea.append(requestBody+"\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			inputThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(164, 186, 198));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 512, 255);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
						printWriter.println(username + ": " + textField.getText());
						// 엔터를 누를 시 텍스트필드의 내용을 소켓 outputStream(서버)에 보내줌
						textField.setText(null);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		textField.setBounds(12, 275, 512, 28);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
