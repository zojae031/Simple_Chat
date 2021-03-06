package chat.simplechat.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import chat.simplechat.MVPInterface.MVP_Main;
import chat.simplechat.Presenter.MainPresenter;
import chat.simplechat.R;

public class MainActivity extends AppCompatActivity implements MVP_Main.View{
    MVP_Main.Presenter presenter;

    Button button;

    ListView listView;
    EditText editText;


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

        presenter.setAdapter(listView);
        presenter.setSendButton(button,editText);
    }


    @Override
    public void drawUserText(Bundle bundle) {
        presenter.notifyDataSetChanged();
    }

    @Override
    public void drawTargetText(Bundle bundle) {
        presenter.notifyDataSetChanged();
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
