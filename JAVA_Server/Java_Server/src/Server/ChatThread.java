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
				
				if(key==Login.LOGIN || key==InsertText.SEND) {//�����ͺ��̽��� �����ϴ� key���̶��
					db = SelectState.getInstance().getState(data); // JSon ������ key �� �о �ش��ϴ� �۾� ��ȯ (State pattern)
					writer.println(db.excute(data));// ������ ����
				}
				else {
					//db�� �������� ���� ���� ���� �۾�
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
