package chat.simplechat.Model;

import chat.simplechat.MVPInterface.MVP_Main;
import chat.simplechat.Model.ServerConnection.ServerClient;

public class MainModel implements MVP_Main.Model {



    @Override
    public void sendMessage(String msg,String id) {
        ServerClient.getInstance().sendMessage(id,msg);
    }

    @Override
    public void logoutUser() {
        ServerClient.getInstance().userLogout();
    }


}
