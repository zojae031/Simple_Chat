package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;

public class Login extends DataBaseConnector implements IDBConnector{
	public static final int LOGIN = 100;
	public static final int LOGIN_OK = 101;
	public static final int LOGIN_FAIL = 102;
	
	public static final String sql = "Select * from user where id='?'and password ='?'";
	PreparedStatement stat;
	ResultSet res;
	UserHolder user;
	@Override
	public Object excute(JsonObject data) throws SQLException {
		int returnValue=0;
		super.connect();
		stat = conn.prepareStatement(sql);
		stat.setString(1, data.get("id").toString().replace("\"",""));
		stat.setString(2, data.get("password").toString().replace("\"",""));
		stat.executeUpdate();
		stat.executeQuery();
		res = stat.executeQuery();
		
		user = new UserHolder();
		while(res.next()) {
			int cnt = 0;
			user.id = res.getString(cnt++);
			user.password = res.getString(cnt++);
			user.flag = res.getInt(cnt);
		}
		
		if(user.flag==0) {//로그인 x
			returnValue = LOGIN_OK;
		}
		else {//로그인 o
			returnValue = LOGIN_FAIL;
		}
		super.closeConnection();
		return returnValue;
	}
	
	private class UserHolder{
		public String id;
		public String password;
		public int flag;
	}

	

}
