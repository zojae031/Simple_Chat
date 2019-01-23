package Server;
import java.util.ArrayList;

import com.google.gson.JsonObject;

public class Clients {
	public static final int BROAD_CAST = 300;
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
	public void sendClients(JsonObject data) {
		data.addProperty("key", BROAD_CAST);
		for(ChatThread thread : broadCasting) {
			thread.writer.println(data);
		}
	}
}
/*
 * �̱��� �������� ������� Clients ���� BroadCasting�� ���ִ� �޼ҵ� ��������
 */
