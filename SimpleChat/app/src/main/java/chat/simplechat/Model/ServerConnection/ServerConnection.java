package chat.simplechat.Model.ServerConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public abstract class ServerConnection  {
    private static final String ip = "192.168.0.229";
    private static final int port = 5050;

    static Socket socket;
    static BufferedWriter writer;
    static BufferedReader reader;

    ConnectionCallback connectionCallback;
    public interface ConnectionCallback{
        void timeout();
        void error();
    }
    public void setCallback(ConnectionCallback callback){
        this.connectionCallback = callback;
    }


    public void connectServer(){
        Thread connect = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socket = new Socket(ip, port);
                    socket.setSoTimeout(3000);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    connectionCallback.timeout();
                    e.printStackTrace();
                }

            }
        });

        try {
            connect.start();
            connect.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected abstract void send() throws IOException;

    protected abstract void receive() throws IOException;

    protected void closeSocket() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }

}
