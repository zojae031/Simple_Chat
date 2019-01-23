package chat.simplechat.Model;

import android.os.Handler;

import chat.simplechat.MVPInterface.MVP_login;
import chat.simplechat.Model.ServerConnection.ServerClient;

public class LoginModel implements MVP_login.Model {


    @Override
    public void connectServer() {
        ServerClient.getInstance().start();
    }


    @Override
    public void clientLogin(String id, String pw, Handler handler) {
        ServerClient.getInstance().login(id, pw, handler);
    }
}
