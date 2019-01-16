package s.smartleader.multi_chat_client.ServerConnect;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import s.smartleader.multi_chat_client.Model.UserVO;


public class Client extends ServerConnection {
    public static final int LOGIN = 100;
    public static final int SEND = 200;
    public static final int LOGOUT = 300;
    private String result;

    private IChatCallback chatCallback;

    private boolean loginFlag = true;

    JSONObject jsonObject;

    public Client(){
        jsonObject = new JSONObject();
    }

    public interface IChatCallback {
        public void update(String data);
    }

    public void setChatCallback(IChatCallback chatCallback) {
        this.chatCallback = chatCallback;
    }

    public void clientLogin(final UserVO userVO){
        /*
         * 로그인 스레드 작성
         */

        //서버 연결
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();//서버연결이 마친 후 로그인 세션을 시작하기 위한 join
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //로그인 정보를 보냄

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        jsonObject.put("key", LOGIN);
                        jsonObject.put("id", userVO.getId());
                        jsonObject.put("pw", userVO.getPw());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    PrintWriter out = new PrintWriter(writer, true);
                    out.println(jsonObject);
                }
            }).start();

            //브로드 캐스팅 시작
            receive();
        }


    public void clientLogout(final UserVO userVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    jsonObject.put("key",LOGOUT);
                    jsonObject.put("id",userVO.getId());
                    jsonObject.put("pw",userVO.getPw());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PrintWriter out = new PrintWriter(writer,true);
                out.println(jsonObject);
                try {
                    closeSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.loginFlag=false;
    }
    @Override
    public void send(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintWriter out = new PrintWriter(writer, true);
                try {
                    jsonObject.put("key",SEND);
                    jsonObject.put("msg",msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                out.println(jsonObject);
            }
        }).start();

    }




    @Override
    public void receive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (loginFlag) {
                        result = reader.readLine();
                        if (!result.equals("")) {//데이터가 들어온 경우
                            chatCallback.update(result);
                        }

                    }

                    closeSocket();
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        closeSocket();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }


            }

        }).start();

    }

}
