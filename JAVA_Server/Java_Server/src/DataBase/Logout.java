package DataBase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class Logout extends DataBaseConnector {
	public static final int LOGOUT = 400;

	public static final String updateSQL = "update user set flag=0 where id=? ";
	PreparedStatement stat;

	@Override
	public Object excute(JsonObject data) throws SQLException {
		super.connect();
		String id = data.get("id").toString().replace("\"", "");
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("key", LOGOUT);
		jsonObject.addProperty("id", id);
		
		stat = conn.prepareStatement(updateSQL);
		

		stat.setString(1, id);
		stat.executeUpdate();

		super.closeConnection();
		return jsonObject;
	}

}
