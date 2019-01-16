package DataBase;

import com.google.gson.JsonObject;

public class SelectState {
	SelectState instance;
	private SelectState() {
		
	}
	
	public SelectState getInstance() {
		if(instance==null) {
			instance = new SelectState();
		}
		return instance;
	}
	
	public IDBConnector getState(JsonObject  data) {
		int key = Integer.parseInt(data.get("key").toString());
		switch(key) {
		case Login.LOGIN : 
			return new Login();
		}
		return null;
	}
}
