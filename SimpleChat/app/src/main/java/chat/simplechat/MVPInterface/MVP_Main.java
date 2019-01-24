package chat.simplechat.MVPInterface;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public interface MVP_Main {
    interface View{
        void drawUserText(Bundle bundle);
        void drawTargetText(Bundle bundle);
        void clearEditText();
    }
    interface Presenter{
        void setAdapter(ListView listView);
        void notifyDataSetChanged();
        void setSendButton(Button button, final EditText editText);
        void logout();
    }
    interface Model{
        void sendMessage(String msg,String id);
        void logoutUser(String id);
    }
}
