package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Handler;

import com.google.gson.JsonObject;

public class InsertText extends DataBaseConnector {
	public static final int SEND = 200;
	
	public static final String sql = "insert into chat values (?, ?)";
	Time time;
	PreparedStatement stat;
	ResultSet res;

	@Override
	public Object excute(JsonObject data) throws SQLException {
		super.connect();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("key", 123);
		String id = data.get("id").toString().replace("\"", "");
		String text = data.get("text").toString().replace("\"", "");
//		String t= String.valueOf(time.getHours())+String.valueOf(time.getMinutes())+String.valueOf(time.getSeconds());
		stat = conn.prepareStatement(sql);
		stat.setString(1, id);
		stat.setString(2, text);
//		stat.setTimestamp(3, t);
		stat.executeUpdate();
		res = stat.executeQuery();
		/*
		 * 여기는 insert만 하도록 수행하고 핸들러를 통해서 메인쪽으로 정보전달
		 * 새로운 Listening 통신을 만들어 그쪽에서 핸들러로 전송을 받은 후 arrayList에 담긴 유저들에게 핸들러로 받은 데이터를 뿌려줌
		 */
		
		
		jsonObject.addProperty("id", id);
		jsonObject.addProperty("text", text);

		super.closeConnection();
		return jsonObject;
	}
}
