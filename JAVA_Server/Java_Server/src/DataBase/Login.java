package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class Login extends DataBaseConnector  {
	public static final int LOGIN = 100;
	public static final int LOGIN_OK = 101;
	public static final int LOGIN_FAIL = 102;

	public static final String sql = "Select * from user where id=? and password =?";
	PreparedStatement stat;
	ResultSet res;
	UserHolder user;
	

	public Object excute(JsonObject data) throws SQLException {
		super.connect();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("key", LOGIN);
		
		stat = conn.prepareStatement(sql);
		String id = data.get("id").toString().replace("\"", "");
		String password = data.get("password").toString().replace("\"", "");
		
		stat.setString(1, id);
		stat.setString(2, password);
		stat.executeUpdate();
		res = stat.executeQuery();

		user = new UserHolder();
		
		while (res.next()) {
			user.id = res.getString("id");
			user.password = res.getString("password");
			user.flag = res.getInt("flag");
		}
		
		if (id.equals(user.id)&&user.flag == 0) {// 로그인 x
			jsonObject.addProperty("value", LOGIN_OK);
			
		} else {// 로그인 o
			jsonObject.addProperty("value", LOGIN_FAIL);
		}
		super.closeConnection();
		return jsonObject;
	}

	private class UserHolder {
		public String id;
		public String password;
		public int flag;
	}

}
