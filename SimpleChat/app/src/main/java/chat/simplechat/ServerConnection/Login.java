package chat.simplechat.ServerConnection;

import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;


public class Login extends ServerConnection {
    public static final int LOGIN = 100;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_FAIL = 102;

    JSONObject jsonObject;


    Handler handler;
    Message message;





    public Login(String id, String pw,Handler handler) {
        this.handler = handler;
        message= handler.obtainMessage();
        jsonObject = new JSONObject();
        try {
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
