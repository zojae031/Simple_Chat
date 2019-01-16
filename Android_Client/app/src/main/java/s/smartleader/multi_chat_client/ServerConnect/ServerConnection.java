package s.smartleader.multi_chat_client.ServerConnect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;


public abstract class ServerConnection extends Thread {
    //서버의  정보
    private static final String SERVER_IP = "192.168.0.16";
    private static final int SERVER_PORT = 5050;

    //Socket 관련 객체
    protected Socket socket;
    protected BufferedReader reader;
    protected BufferedWriter writer;

    //callback
    IServerConnectCallback callback;
    public interface IServerConnectCallback{
        public void timeout();
    }
    public void setServerConnectCallback(IServerConnectCallback callback){
        this.callback = callback;
    }
    @Override
    public void run() {
        try {

            socket = new Socket();
            socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT), 2000);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            callback.timeout();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public abstract void send(String msg);

    public abstract void receive();

    void closeSocket() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
