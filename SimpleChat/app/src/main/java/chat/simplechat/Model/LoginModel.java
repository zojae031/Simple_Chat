package chat.simplechat.Model;

import android.os.Handler;

import chat.simplechat.MVP_login;
import chat.simplechat.ServerConnection.Login;

public class LoginModel implements MVP_login.Model {
    Login login;

    @Override
    public void connectServer() {
        login.start();
    }


    @Override
    public void newServerThread(String id, String pw, Handler handler) {
        login = new Login(id,pw,handler);
    }
}
