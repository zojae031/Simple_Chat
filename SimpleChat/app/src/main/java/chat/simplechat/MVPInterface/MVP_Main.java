package chat.simplechat.MVPInterface;

import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import chat.simplechat.Model.ChatVO;

public interface MVP_Main {
    interface View{
        void drawUserText();
        void drawTargetText();
        void clearEditText();
    }
    interface Presenter{
        void setAdapter(ListView listView, ArrayList<ChatVO> list);
        void setSendButton(Button button, final EditText editText);
        void logout();
    }
    interface Model{
        void sendMessage(String msg,String id);
        void logoutUser();
    }
}
