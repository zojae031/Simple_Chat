package chat.simplechat.ServerConnection;

import java.io.PrintWriter;

public class Login extends ServerConnection {
    public static final int LOGIN=100;
    public static final int LOGIN_OK=101;
    public static final int LOGIN_FAIL=102;

    UserVO userVO;
    /*
     *TODO gson추가, json에 담아서 보내기

     */
    private class UserVO{
        public String id;
        public String password;

    }
    public Login(String id,String pw){
        userVO = new UserVO();
        userVO.id = id;
        userVO.password = pw;

    }
    @Override
    public void run() {
        super.run();
    }

    @Override
    public void send() {
        PrintWriter out = new PrintWriter(writer,true);
        out.println();
    }


    @Override
    public void receive() {

    }
}
