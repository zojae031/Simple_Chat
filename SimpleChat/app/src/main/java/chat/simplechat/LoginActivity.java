package chat.simplechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import chat.simplechat.ServerConnection.Login;

public class LoginActivity extends AppCompatActivity {
    EditText id,pw;
    Button login_btn;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);


        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = new Login(id.getText().toString(),pw.getText().toString());
                login.setLoginCallback(new Login.LoginCallback() {
                    @Override
                    public void error() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void login() {
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });
                login.start();
            }
        });
    }
}
