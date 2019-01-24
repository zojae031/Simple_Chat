package chat.simplechat.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import chat.simplechat.MVPInterface.MVP_Main;
import chat.simplechat.Model.ChatVO;
import chat.simplechat.Model.MainModel;
import chat.simplechat.Model.ServerConnection.ServerClient;
import chat.simplechat.R;

public class MainPresenter implements MVP_Main.Presenter {
    MVP_Main.View view;
    MVP_Main.Model model;

    String userId="";
    MainHandler handler;
    ChatAdapter adapter;
    ArrayList<ChatVO> list= new ArrayList<>();

    public MainPresenter(MVP_Main.View view,String id) {
        this.view = view;
        this.userId =id;
        handler = new MainHandler();
        model = new MainModel();
        ServerClient.getInstance().setMainHandler(handler);


    }
    private class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String id = ((String) bundle.get("id")).replace("\"","");
            String text = ((String)bundle.get("text")).replace("\"","");;
            if(id.equals(userId)){
                Log.e("사용자 결과값",text);
                list.add(new ChatVO(id,text));
                adapter.notifyDataSetChanged();
//                view.drawUserText(bundle);
            }
            else{
                Log.e("상대방 결과값",text);
                list.add(new ChatVO(id,text));
                adapter.notifyDataSetChanged();
//                view.drawTargetText(bundle);
            }


            //TODO 아이디 식별확인 하여 view.drawUserText() or view.drawTargetText();
        }
    }
    @Override
    public void setAdapter(ListView listView) {
        adapter = new ChatAdapter((Context)view, R.layout.chat_message,list,userId);
        listView.setAdapter(adapter);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setSendButton(Button button, final EditText editText) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                model.sendMessage(editText.getText().toString(),userId);
                view.clearEditText();


            }
        });
    }



    @Override
    public void logout() {
        model.logoutUser(userId);
    }


}
