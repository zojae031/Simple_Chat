package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBase.DataBaseConnector;
import DataBase.InsertText;
import DataBase.Login;
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
			while (true) {
				
				JsonObject data = (JsonObject) parser.parse(reader.readLine());
				int key = data.get("key").getAsInt();
				
				if(key==Login.LOGIN || key==InsertText.SEND) {//데이터베이스에 접근하는 key값이라면
					db = SelectState.getInstance().getState(data); // JSon 데이터 key 를 읽어서 해당하는 작업 반환 (State pattern)
					writer.println(db.excute(data));// 다형성 구현
				}
				else {
					//db에 접근하지 않을 떄에 관한 작업
				}
				System.out.println(data);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkBoradCast() {

		return true;
	}
}
