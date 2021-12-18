package xyz.itwill.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//채팅 서버 프로그램 -다중 스레드
// => 클라이언트가 보내온 메세지를 전달받아 접속된 모든 클라이언트에게 전달하는 기능 구현
// => 클라이언트와 연결된 서버의 소켓은 새로운 스레드를 생성하여 독립적으로 동작되도록 구현
public class ChatServerApp {
	//접속된 클라이언트와 연결된 서버의 Socket 인스턴스와 입출력 스트림이 저장된  
	//SocketThread 인스턴스를 요소로 저장하기 위한 콜렉션 필드
	private List<SocketThread> socketList;
	
	public ChatServerApp() {
		ServerSocket chatServer=null;
		
		try {
			chatServer=new ServerSocket(6000);
			System.out.println("[메세지]채팅 서버 동작 중...");
			
			//콜렉션 필드에 ArrayList 인스턴스를 생성하여 저장
			socketList=new ArrayList<ChatServerApp.SocketThread>();
			while(true) {
				try {
					Socket socket=chatServer.accept();
					
					System.out.println("[접속로그]"+socket.getInetAddress().getHostAddress()
							+"의 컴퓨터에서 접속 하였습니다.");
					
					//클라이언트와 연결된 서버의 Socket 인스턴스를 저장한 SocketThread 인스턴스 생성
					// => Thread 인스턴스 생성
					SocketThread socketThread=new SocketThread(socket);
					
					//List 인스턴스에 요소(SocketThread 인스턴스) 추가
					// => List 인스턴스에는 클라이언트와 연결된 모든 접속정보(SocketThread 인스턴스)가 저장
					socketList.add(socketThread);

					//새로운 스레드를 생성하여 Socket 인스턴스를 이용한 입출력 스트림으로
					//독립적인 입출력 기능을 제공하는 run() 메소드의 명령 실행
					socketThread.start();
				} catch (IOException e) {
					System.out.println("[에러로그]클라이언트의 접속 관련 문제가 발생 되었습니다.");
				}
			}
		} catch (IOException e) {
			System.out.println("[에러로그]서버가 동작중지.");
		}
	}
	
	public static void main(String[] args) {
		new ChatServerApp();
	}
	
	//메세지를 전달받아 현재 접속중인 모든 클라이언트에게 전달하는 메소드
	public void sendMessage(String message) {
		//List 인스턴스의 요소(SocketThread 인스턴스)를 하나씩 제공받아 사용 - 일괄처리 
		for(SocketThread socketThread:socketList) {
			//클라이언트와 연결된 서버의 Socket 인스턴스에 출력스트림을 이용하여 메세지 전달
			socketThread.out.println(message);
		}
	}
	
	//클라이언트와 연결된 소켓과 입출력스트림을 저장하여 입출력 기능을 제공하기 위한 내부 클래스(Inner Class)
	// => 독립적인 입출력 기능을 제공하기 위해 새로운 스레드를 생성하여 동작되도록 구현
	public class SocketThread extends Thread {
		//클라이언트와 연결된 서버의 Socket 인스턴스를 저장하기 위한 필드
		private Socket socket;
		
		//클라이언트가 보내온 메세지를 전달받은 입력스트림을 저장하기 위한 필그 
		private BufferedReader in;
		
		//클라이언트에게 메세지를 전달하는 출력스트림을 저장하기 위한 필그
		private PrintWriter out;

		//SocketThread 클래스를 인스턴스로 생성하기 위한 생성자 작성
		// => Socket 인스턴스를 전달받아 필드 초기화
		public SocketThread(Socket socket) {
			super();
			this.socket = socket;
		}

		//새롭게 생성된 스레드가 실행하기 위한 명령 작성
		// => 클라이언트에서 보내온 메세지를 전달받아 모든 접속 클라이언트에게 전달하는 메소드 
		@Override
		public void run() {
			String aliasName="";//클라이언트의 대화명을 저장하기 위한 변수
			
			try {
				//Socket 인스턴스의 입력스트림을 반환받아 문자열을 전달받을 수 있는 
				//입력스트림으로 확장하여 필드에 저장
				in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//Socket 인스턴스의 출력스트림을 반환받아 문자열을 전달할 수 있는 
				//출력스트림으로 확장하여 필드에 저장
				
				//Writer 관련 출력스트림은 대용량의 값을 전달하는 출력스트림으로 일정 크기
				//미만인 경우 출력버퍼에 값을 저장하고 데이타 미전달 - flush() 메소드 사용
				//out=new PrintWriter(socket.getOutputStream());
				
				//PrintWriter(OutputStream out, boolean autoFlush) : 출력스트림을 확장하여
				//모든 자료형의 값을 문자열로 변환하여 전달하고 boolean 매개변수에 true를
				//전달하면 출력버퍼를 사용하지 않고 무조건 데이타를 전달하는 인스턴스를 
				//생성하는 생성자
				out=new PrintWriter(socket.getOutputStream(), true);
				
				//클라이언트에세 보내온 대화명을 반환받아 저장 - 입력스트림 사용
				aliasName=in.readLine();
				
				//현재 접속된 모든 클라이언트에게 입장 메세지를 전달 - 출력스트림 사용
				sendMessage("["+aliasName+"]님이 입장 하였습니다.");

				//클라이언트에서 보내온 메세지를 전달받아 현재 접속중인 모든 클라이언트에게 전달
				// => 클라이언트가 접속을 종료하기 전까지 반복 처리
				// => 클라이언트가 접속을 종료하면 입출력 스트림이 제거되므로 IOException 발생
				while(true) {
					sendMessage("["+aliasName+"]"+in.readLine());
				}
			} catch(IOException e) {
				//클라이언트가 접속을 종료하면 실행될 명령 작성
				// => List 인스턴스에 저장된 SocketThread 인스턴스 요소 제거
				// => 현재 접속중인 모든 클라이언트에게 퇴장 메세지를 전달
				socketList.remove(this);//this : 현재 사용중인 SocketThread 인스턴스
				sendMessage("["+aliasName+"]님이 퇴장 하였습니다.");
				
				System.out.println("[해제로그]"+socket.getInetAddress().getHostAddress()
						+"의 컴퓨터에서 접속을 종료 하였습니다.");
			}
		}
	}
}














