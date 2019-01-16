package s.smartleader.multi_chat_client.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import s.smartleader.multi_chat_client.R;

public class LoginActivity extends AppCompatActivity {
    EditText id,pw;

    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        button = findViewById(R.id.login_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("id",id.getText().toString());
                intent.putExtra("pw",pw.getText().toString());
                startActivity(intent);
            }
        });

    }
}
