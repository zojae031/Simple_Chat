package chat.simplechat.Presenter;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import chat.simplechat.MVP_login;
import chat.simplechat.Model.LoginModel;
import chat.simplechat.ServerConnection.Login;

public class LoginPresenter implements MVP_login.Presenter {
    MVP_login.View view;
    MVP_login.Model model;


    LoginHandler handler;

    private class LoginHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            switch (Integer.parseInt(result)) {
                case Login.LOGIN_OK:
                    view.login();
                    break;
                case Login.LOGIN_FAIL:
                    view.error();
                    break;
            }
        }
    }
    public LoginPresenter(MVP_login.View view){
        this.view = view;
        model = new LoginModel();
        handler = new LoginHandler();
    }



    @Override
    public void setLoginButton(final Button button,final EditText id,final EditText pw) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.newServerThread(id.getText().toString(),pw.getText().toString(),handler); //로그인 스레드 생성
                model.connectServer(); //서버 통신
            }
        });
    }
}
