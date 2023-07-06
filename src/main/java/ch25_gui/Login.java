package ch25_gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
	
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "1234";

	private JPanel mainCardPane;
	private CardLayout mainCardLayout;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		/* <<<mainCardPane>>> */
		mainCardPane = new JPanel();
		mainCardLayout = new CardLayout(0, 0);
		
		mainCardPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainCardPane.setLayout(mainCardLayout);

		setContentPane(mainCardPane);
		
		/* <<<loginPanel>>> */
		JPanel loginPanel = new JPanel();
		mainCardPane.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(142, 71, 219, 29);
		loginPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(142, 110, 219, 29);
		loginPanel.add(passwordTextField);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.setBounds(153, 183, 115, 29);
		loginPanel.add(loginBtn);
		
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameTextField.getText();
				String password = passwordTextField.getText(); // jdk 다음 버전엔 사라질 수 있다는 표시
				
				if (!username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) { // 로그인 정보가 틀렸을 때
					JOptionPane.showMessageDialog(loginPanel, "사용자 정보가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(loginPanel, "환영합니다.", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
				mainCardLayout.show(mainCardPane, "mainPanel");
			}
		});
		
		JLabel idLabell = new JLabel("아이디");
		idLabell.setBounds(12, 71, 95, 29);
		loginPanel.add(idLabell);
		
		JLabel passwordLabel = new JLabel("비밀번호");
		passwordLabel.setBounds(12, 110, 95, 29);
		loginPanel.add(passwordLabel);
		
		/* <<<mainPanel>>> */
		JPanel mainPanel = new JPanel();
		mainCardPane.add(mainPanel, "mainPanel");
		mainPanel.setLayout(null);
		
		JLabel mainLabel = new JLabel("Main");
		mainLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setBounds(139, 10, 140, 28);
		mainPanel.add(mainLabel);
	}
}
