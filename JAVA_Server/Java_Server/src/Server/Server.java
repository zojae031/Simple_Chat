package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final String ip = "";
	private static final int port=5050;
	ServerSocket socket;
	
	
	public Server() {
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void open() {
		try {
			System.out.println("Server Open...");
			while(true) {
				Socket client = socket.accept();
				ClientThread thread = new ClientThread(client);
				thread.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
