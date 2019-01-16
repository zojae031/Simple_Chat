package s.smartleader.multi_chat_client.Model;

import java.io.Serializable;

public class UserVO implements Serializable {
    private String id;
    private String msg;
    private String pw;
    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
