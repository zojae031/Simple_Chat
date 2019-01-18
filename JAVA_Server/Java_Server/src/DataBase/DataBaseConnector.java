package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.JsonObject;
/*
 * excute를 구현할때 시작시 super.connect(), 종료시 super.closeConnection()호출 해줘야함
 */
public abstract class DataBaseConnector {
	private static final String address = "jdbc:mariadb://localhost:3306/chat";
	private static final String user = "root";
	private static final String password = "8845";
	
	protected static Connection conn;

	protected void connect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(address,user,new String(password));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void closeConnection() throws SQLException {
		conn.close();
	}
	
	public abstract Object excute(JsonObject data)throws SQLException;
	/*
	 * example) 
	 * Object excute(JsonObject data) throws SQLException{
	 * 		super.connect();
	 *		TDDO Somthing
	 *		super.closeConnection();
	 * }
	 */
}
