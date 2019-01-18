package chat.simplechat.ServerConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class Login extends ServerConnection {
    public static final int LOGIN = 100;
    public static final int LOGIN_OK = 101;
    public static final int LOGIN_FAIL = 102;

    JSONObject jsonObject;

    LoginCallback loginCallback;


    public interface LoginCallback {
        public void error();

        public void login();
    }

    public void setLoginCallback(LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
    }


    public Login(String id, String pw) {
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
        switch (Integer.parseInt(result)) {
            case LOGIN_OK:
                loginCallback.login();
                break;
            case LOGIN_FAIL:
                loginCallback.error();
                break;
        }
        writer.close();
        reader.close();


    }
}
