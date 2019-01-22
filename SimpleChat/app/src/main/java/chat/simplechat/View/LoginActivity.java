package chat.simplechat.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import chat.simplechat.MVPInterface.MVP_login;
import chat.simplechat.Presenter.LoginPresenter;
import chat.simplechat.R;

public class LoginActivity extends AppCompatActivity implements MVP_login.View  {
    EditText id,pw;
    Button login_btn;

    MVP_login.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        login_btn = findViewById(R.id.login_btn);

        presenter = new LoginPresenter(this);
        presenter.setLoginButton(login_btn,id,pw);

    }


    @Override
    public void error() {
        Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void login(Intent intent) {
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
}
}
