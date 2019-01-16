package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public abstract class ServerConnection extends Thread{
	//������  ����
	private static final String SERVER_IP ="172.30.1.2";
	private static final int SERVER_PORT = 5050;
	
	//Socket ���� ��ü
	private Socket socket;
	protected BufferedReader reader;
	protected BufferedWriter writer;
	

	public ServerConnection() {
		
	}
	@Override
	public void run() {
		try {
			
			socket = new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT),2000);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			send();
			receive();
			
		}catch(SocketTimeoutException e) {
			System.out.println("�ð��ʰ� IP�� PORT�� ��Ȯ�� �Ͻÿ�.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	abstract void send();
	abstract void receive();
	void closeSocket() throws IOException{
		writer.close();
		reader.close();
		socket.close();
	}
}
