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
 * 싱글톤 패턴으로 만들어진 Clients 에서 BroadCasting을 해주는 메소드 만들어야해
 */
