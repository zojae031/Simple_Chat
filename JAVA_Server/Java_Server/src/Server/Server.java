package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private static final String ip = "192.168.0.247";
	private static final int port = 5050;
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
			String address = InetAddress.getLocalHost().getHostAddress();
			System.out.println("IP : " + address);
			System.out.println("Server Open...");
			while (true) {
				Socket client = socket.accept();
				ChatThread thread = new ChatThread(client);
				Clients.getInstance().addThread(thread);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
