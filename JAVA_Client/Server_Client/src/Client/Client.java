package Client;

import java.io.IOException;
import java.io.PrintWriter;

public class Client extends ServerConnection{
	String result;
	@Override
	void send() {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(writer,true);
		String data = "data";
		out.println(data);
		System.out.println("������ ���� �� ������ : "+data);
	}

	@Override
	void receive() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					result = reader.readLine();
					closeSocket();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				System.out.println("������ ���� ���� ��� : "+result);
				
			}
			
		}).start();
		
	}

}
