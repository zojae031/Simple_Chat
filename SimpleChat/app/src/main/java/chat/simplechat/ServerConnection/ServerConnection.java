package chat.simplechat.ServerConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public abstract class  ServerConnection extends Thread{
    private static final String ip = "172.30.1.17";
    private static final int port = 5050;

    Socket socket;
    BufferedWriter writer;
    BufferedReader reader;
    public ServerConnection(){


    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip,port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            send();
            receive();
            closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
    public abstract void send() throws IOException;
    public abstract void receive() throws IOException;
    public void closeSocket() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
