package DataBase;

import java.sql.SQLException;

import com.google.gson.JsonObject;

public interface IDBConnector {
	public Object excute(JsonObject data)throws SQLException;
	
}
