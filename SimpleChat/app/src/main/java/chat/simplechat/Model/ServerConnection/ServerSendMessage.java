package chat.simplechat.Model.ServerConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class ServerSendMessage extends ServerConnection {
    public static final int SEND = 200;


    JSONObject jsonObject;

    public ServerSendMessage(String id,String msg){
        jsonObject =new JSONObject();
        try{
            jsonObject.put("key",SEND);
            jsonObject.put("id",id);
            jsonObject.put("text",msg);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public void send() throws IOException {
        PrintWriter out = new PrintWriter(writer,true);
        out.println(jsonObject);
    }

    @Override
    public void receive() throws IOException {

    }
}
