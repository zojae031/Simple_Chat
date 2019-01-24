package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBase.DataBaseConnector;
import DataBase.InsertText;
import DataBase.Login;
import DataBase.Logout;
import DataBase.SelectState;

public class ChatThread extends Thread {
	Socket client;
	BufferedReader reader;
	PrintWriter writer;

	DataBaseConnector db;

	public ChatThread(Socket client) {
		this.client = client;

	}

	@Override
	public void run() {

		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8")),
					true);
			JsonParser parser = new JsonParser();
			while (true) {// flag로 바꾸어 로그아웃되면 스레드 종료되도록 만들어야 함

				JsonObject data = (JsonObject) parser.parse(reader.readLine());
				int key = data.get("key").getAsInt();

				db = SelectState.getInstance().getState(data); // JSon 데이터 key 를 읽어서 해당하는 작업 반환 (State pattern)
				Object ResultData = db.excute(data);

				switch (key) {
				case Login.LOGIN:
					writer.println(ResultData);// 로그인 결과
					break;
				case InsertText.SEND:
					Clients.getInstance().sendClients(data); // 채팅 결과 브로드캐스팅
					break;
				case Logout.LOGOUT:
					System.out.println("사용자 로그아웃 : " + ((JsonObject)ResultData).get("id"));
					break;
				}

				System.out.println(data);

			}

		} catch (NullPointerException e) {
			;//사용자 로그아웃
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkBoradCast() {

		return true;
	}
}
