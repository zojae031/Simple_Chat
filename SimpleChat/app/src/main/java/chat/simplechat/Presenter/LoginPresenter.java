package chat.simplechat.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import chat.simplechat.MVPInterface.MVP_login;
import chat.simplechat.Model.LoginModel;
import chat.simplechat.Model.ServerConnection.ServerClient;

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
                case ServerClient.LOGIN_OK:
                    Intent intent = new Intent();
                    Bundle bundle = msg.getData();
                    intent.putExtra("id",bundle.get("id").toString());
                    view.login(intent);
                    break;
                case ServerClient.LOGIN_FAIL:
                    ServerClient.getInstance().userLogout();
                    view.error();
                    break;
            }
        }
    }
    public LoginPresenter(MVP_login.View view){
        this.view = view;
        model = new LoginModel();
        handler = new LoginHandler();
        model.connectServer(); //서버 통신
    }



    @Override
    public void setLoginButton(final Button button,final EditText id,final EditText pw) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.clientLogin(id.getText().toString(),pw.getText().toString(),handler); //로그인 스레드 생성

            }
        });
    }
}
