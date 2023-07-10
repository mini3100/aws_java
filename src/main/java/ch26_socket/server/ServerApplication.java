package ch26_socket.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServerApplication {
	
	public static ServerSocket serverSocket;	
	public static int port = 0;
	//static이기 때문에 클래스 안에 넣어줌.
	
	public static void main(String[] args) {
		Thread connectionThread = null;

		System.out.println("[ 서버프로그램 실행 ]");
		while (true) {
			Scanner scanner = new Scanner(System.in);
			int selectedMenu = 0;
			System.out.println("1. 서버 켜기");
			System.out.println("2. 서버 끄기");
			System.out.print("선택: ");
			try {
				selectedMenu = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				continue;
			}

			switch (selectedMenu) {
			case 1:
				if (serverSocket != null) {
					System.out.println("이미 서버가 실행중입니다.");
					break;
				}
				
				System.out.print("서버의 포트 번호를 입력하세요: ");
				try {
					port = scanner.nextInt();					
				} catch(InputMismatchException e){
					System.out.println("숫자만 입력 가능합니다.");
					continue;	//다음 while로
				}
				
				connectionThread = new Thread(() -> {	//반복이 될 때마다 new(새로 생성) 해주기 때문에 interrupt 안해줘도 됨
					//람다식 runnable 메소드
					//람다식 -> 새로운 클래스의 메소드. 람다식 밖의 변수 쓸 수 없음.
					//=> serverSocket, port static 변수로 만들어 줌
					try {
						serverSocket = new ServerSocket(port);

						while (!Thread.interrupted()) {	//Thread가 interrupt 됐다면 실행 x (Thread가 실행중일 때만 접속)
							Socket socket = serverSocket.accept();
							//Socket이 연결될 때까지 대기
							//대기하고 있는데 serverSocket 객체를 소멸시키면 SocketException 예외가 발생
							ConnectedSocket connectedSocket = new ConnectedSocket(socket);
							connectedSocket.start();	//thread 시작
							ConnectedClientController.getInstance().getConnectedSockets().add(connectedSocket);
							//접속이 이루어질 때마다 새로운 스레드를 생성(각각의 소켓마다 새로운 스레드)
							//ConnectedSocket(스레드)을 담는 List
							System.out.println("접속!!");
							System.out.println(socket.getInetAddress().getHostAddress());
						}
					} catch (BindException e) {		//포트번호 예외
						System.out.println("이미 사용중인 포트번호입니다.");
					} catch (SocketException e) {	//소켓을 강제 종료 했을 때의 예외
						System.out.println("서버의 연결이 종료되었습니다.");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}, "connectionThread");		//runnable 선언, 구현
				
				connectionThread.start(); 	//Thread 시작해줌.
				
				break;
			case 2:
				if(serverSocket == null) {	//이 조건을 걸어주지 않으면 stop에서 NullPointException 발생 가능
					System.out.println("서버가 실행중이지 않습니다.");
					break;
				}
				try {
					serverSocket.close();	//serverSocket 객체를 소멸시킴.
				} catch (IOException e) {
				}
				
				connectionThread.interrupt();	
				//Thread.interrupted() == true가 됨 
				
				try {
					connectionThread.join();	//connectionThread가 정상 종료될 때까지 대기(main이 먼저 종료되지 않도록)
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("프로그램 종료");
				return;
			default:
				System.out.println("다시 입력하세요.");
			}
			if(serverSocket == null) {	
				//serverSocket이 생성되지 않았다면 Thread가 0.5초 대기
				//main 메소드를 순서대로 실행하기 위함.
				//serverSocket 생성과 thread가 동시에 돌아가기 때문에 thread가 먼저 끝나서 다음 반복으로 가는 것을 방지함.
				try {
					connectionThread.join(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
