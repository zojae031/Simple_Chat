package chat.simplechat;

import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

public interface MVP_login {
    interface View{
        void login();
        void error();
    }
    interface Presenter{
        void setLoginButton(final Button button, final EditText id,final EditText pw);
    }
    interface Model{
        void connectServer();
        void newServerThread(String id, String pw, Handler handler);
    }
}
