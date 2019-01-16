package s.smartleader.multi_chat_client.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import s.smartleader.multi_chat_client.Model.UserVO;
import s.smartleader.multi_chat_client.R;
import s.smartleader.multi_chat_client.ServerConnect.Client;
import s.smartleader.multi_chat_client.ServerConnect.ServerConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    RecyclerView recyclerView;

    ActionBar actionBar ;

    RecyclerView.LayoutManager layoutManager;

    Client client;

    UserVO userVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingView();
        SettingServerConnect();



    }
    private void settingView(){
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyle);
        actionBar = getSupportActionBar();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
    private void SettingServerConnect(){

        /*
         * 로그인 세션 login Act로 바꿔서 서버에서 결과 받아와야함 (DB만든 후)
         */
        userVO =new UserVO();
        Intent intent =getIntent();

        userVO.setId(intent.getStringExtra("id"));
        userVO.setPw(intent.getStringExtra("pw"));

        client = new Client();
        client.setServerConnectCallback(new ServerConnection.IServerConnectCallback() {
            @Override
            public void timeout() {
                Looper.prepare();
                Toast.makeText(getApplicationContext(),"서버와 연결이 되지 않음",Toast.LENGTH_SHORT).show();
            }
        });
        client.setChatCallback(new Client.IChatCallback() {
            @Override
            public void update(final String data) {


            }
        });
        client.clientLogin(userVO);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            client.clientLogout(userVO);
            Toast.makeText(getApplicationContext(),"로그아웃 되었습니다.",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sendBtn:
                String data = editText.getText().toString();
                editText.setText("");
                client.send(data);
                /*
                 * 서버로 문자 전송 + 화면에 띄우기
                 */
                break;
        }


    }

}
