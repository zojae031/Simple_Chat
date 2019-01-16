package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DataBase.IDBConnector;
import DataBase.SelectState;

public class ClientThread extends Thread{
	Socket client ;
	BufferedReader reader;
	PrintWriter writer;
	
	IDBConnector db;
	SelectState selectState;
	
	public ClientThread(Socket client) {
		this.client = client;
		
	}
	@Override
	public void run() {
		
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
			JsonParser parser =new JsonParser();
			JsonObject data = (JsonObject)parser.parse(reader.readLine());
			db = selectState.getInstance().getState(data); //Json 데이터 key 를 읽어서 해당하는 작업 반환 (State pattern)
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8")),true);
			System.out.println(data);
			writer.println(db.excute(data));//다형성 구현
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
