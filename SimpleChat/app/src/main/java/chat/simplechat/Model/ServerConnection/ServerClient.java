package chat.simplechat.Model.ServerConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerClient extends ServerConnection {
    public static final int LOGIN = 100;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_FAIL = 102;

    public static final int SEND = 200;

    public static final int BROAD_CAST = 300;
    JSONObject jsonObject;

    private static ServerClient instance = null;

    public static ServerClient getInstance() {
        if (instance == null) {
            instance = new ServerClient();
        }
        return instance;
    }

    private ServerClient() {

    }

    Handler loginHandler;
    Handler mainHandler;
    Message loginMessage;
    Message mainMessage;

    private boolean status = true;

    public void setMainHandler(Handler handler) {
        mainHandler = handler;

//        mainMessage = mainHandler.obtainMessage();
    }

    public void login(String id, String pw, Handler handler) {
        this.loginHandler = handler;
        loginMessage = handler.obtainMessage();
        jsonObject = new JSONObject();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            loginMessage.setData(bundle);
            jsonObject.put("key", LOGIN);
            jsonObject.put("id", id);
            jsonObject.put("password", pw);
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        super.run();
    }

    public void sendMessage(String id, String msg) {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("key", SEND);
            jsonObject.put("id", id);
            jsonObject.put("text", msg);
            send();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userLogout() {
        status = false;

    }

    @Override
    public void send() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintWriter out = new PrintWriter(writer, true);
                out.println(jsonObject);
            }
        }).start();

    }

    @Override
    public void receive() throws IOException {
        String msg;

        while (status) {
            try {
                msg = reader.readLine();
                if (!msg.equals("")) {//전달 데이터 분류하기
                    JsonParser parser = new JsonParser();
                    JsonObject data = (JsonObject) parser.parse(msg);
                    int key = Integer.parseInt(data.get("key").toString().replace("\"", ""));
                    switch (key) {
                        case LOGIN:
                            loginMessage.obj = data.get("value").toString();
                            loginHandler.sendMessage(loginMessage);
                            break;
                        case BROAD_CAST:
                            Bundle bundle = new Bundle();
                            bundle.putString("id", data.get("id").toString());
                            bundle.putString("text", data.get("text").toString());
                            mainMessage = mainHandler.obtainMessage();
                            mainMessage.setData(bundle);
                            mainHandler.sendMessage(mainMessage);

                            break;
                    }

                    Log.e("전달받은 데이터", msg);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                //서버가 제대로 열리지 않았을 떄 생기는 문제
            }
        }
        Log.e("앙 기모띠", "ServerClient 스레드 종료");
    }
}
