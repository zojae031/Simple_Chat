package chat.simplechat.Model.ServerConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public abstract class ServerConnection extends Thread {
    private static final String ip = "192.168.0.21";
    private static final int port = 5050;

    static Socket socket;
    static BufferedWriter writer;
    static BufferedReader reader;

    public ServerConnection() {
        Thread connect = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socket = new Socket(ip, port);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        try {
            connect.start();
            connect.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void run() {
        try {
            receive();
            closeSocket();
        } catch (IOException e) {
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
