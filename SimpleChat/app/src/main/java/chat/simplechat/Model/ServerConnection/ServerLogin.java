package chat.simplechat.Model.ServerConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;


public class ServerLogin extends ServerConnection {
    public static final int LOGIN = 100;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_FAIL = 102;

    JSONObject jsonObject;


    Handler handler;
    Message message;





    public ServerLogin(String id, String pw, Handler handler) {
        this.handler = handler;
        message= handler.obtainMessage();
        jsonObject = new JSONObject();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("id",id);
            message.setData(bundle);

            jsonObject.put("key", LOGIN);
            jsonObject.put("id", id);
            jsonObject.put("password", pw);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        super.run();

    }

    @Override
    public void send() throws IOException {
        PrintWriter out = new PrintWriter(writer, true);
        out.println(jsonObject);
    }


    @Override
    public void receive() throws IOException {
        String result;

        result = reader.readLine();
        message.obj =result;
        handler.sendMessage(message);

    }
}
