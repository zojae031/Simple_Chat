package DataBase;

import com.google.gson.JsonObject;

public class SelectState {
	static SelectState instance;
	private SelectState() {
		
	}
	
	public static SelectState getInstance() {
		if(instance==null) {
			instance = new SelectState();
		}
		return instance;
	}
	
	public DataBaseConnector getState(JsonObject  data) {
		int key = Integer.parseInt(data.get("key").toString());
		switch(key) {
		case Login.LOGIN : 
			return new Login();
		}
		return null;
	}
}
