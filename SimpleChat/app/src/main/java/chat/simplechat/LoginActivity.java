package chat.simplechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import chat.simplechat.ServerConnection.Login;
import chat.simplechat.ServerConnection.ServerConnection;

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
                login.start();
            }
        });
    }
}
