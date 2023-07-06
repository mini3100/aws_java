package ch25_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIMain1 extends JFrame {

	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "1234"; // 관리자 아이디, 비밀번호 -> 바뀔 일이 없기 때문에 상수로 선언

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIMain1 frame = new GUIMain1();
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
	public GUIMain1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(144, 92, 196, 33);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);

		JButton signinBtn = new JButton("Login");
		signinBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText(); // jdk 다음 버전엔 사라질 수 있다는 표시

				if (!username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) { // 로그인 정보가 틀렸을 때
					JOptionPane.showMessageDialog(contentPane, "사용자 정보가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "환영합니다.", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
			}
		});
		signinBtn.setBounds(109, 200, 166, 39);
		contentPane.add(signinBtn);

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(144, 135, 196, 33);
		contentPane.add(passwordTextField);
	}
}
