import java.util.ArrayList;

import Server.ChatThread;

public class Clients {
	
	ArrayList<ChatThread> broadCasting = new ArrayList();
	
	private static Clients instance=null;
	public static Clients getInstance() {
		if(instance==null) {
			instance = new Clients();
		}
		return instance;
	}
	private Clients() {
		
	}
	public void addThread(ChatThread thread) {
		broadCasting.add(thread);
	}
}
/*
 * �̱��� �������� ������� Clients ���� BroadCasting�� ���ִ� �޼ҵ� ��������
 */
