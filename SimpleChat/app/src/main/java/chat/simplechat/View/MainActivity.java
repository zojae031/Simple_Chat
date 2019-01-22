package chat.simplechat.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import chat.simplechat.MVPInterface.MVP_Main;
import chat.simplechat.Model.ChatVO;
import chat.simplechat.Presenter.MainPresenter;
import chat.simplechat.R;

public class MainActivity extends AppCompatActivity implements MVP_Main.View{
    MVP_Main.Presenter presenter;

    Button button;

    ListView listView;
    EditText editText;

    ArrayList<ChatVO> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        presenter = new MainPresenter(this,id);

        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.send_btn);

        presenter.setAdapter(listView,list);
        presenter.setSendButton(button,editText);
    }


    @Override
    public void drawUserText() {

    }

    @Override
    public void drawTargetText() {

    }

    @Override
    public void clearEditText() {
        editText.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.logout();
    }
}
