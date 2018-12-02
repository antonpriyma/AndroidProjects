package com.example.anton.myapplication;





import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
    private String mServerMessage;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false; // флаг, определяющий, запущен ли сервер
    private PrintWriter mBufferOut;
    private BufferedReader mBufferIn;
    private Socket socket;
    private String address;

    public SocketClient(String address, OnMessageReceived listener) {
        this.address = address;
        mMessageListener = listener;
    }

    public void sendMessage(String message) {
        if (mBufferOut != null && !mBufferOut.checkError()) {
            mBufferOut.println(message);
            mBufferOut.flush();
        }
    }

    public void stopClient() {
        sendMessage("close");

        mRun = false;

        if (mBufferOut != null) {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mMessageListener = null;
        mBufferIn = null;
        mBufferOut = null;
        mServerMessage = null;
    }

    public void run(String player) {
        try {
            InetAddress serverAddr = InetAddress.getByName(address);

            try {
                socket = new Socket(serverAddr, 22);
                mRun = true;
                mBufferOut =
                        new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),
                                true);

                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                sendMessage("anton"); // отправляем название команды
                mMessageListener.onConnected();

                // ждем ответа
                while (mRun) {
                    if (mBufferOut.checkError()) {
                        mRun = false;
                    }

                    mServerMessage = mBufferIn.readLine();

                    if (mServerMessage != null && mMessageListener != null) {
                        mMessageListener.messageReceived(mServerMessage);
                    }
                }
            } catch (Exception e) {
            } finally {
                if (socket != null && socket.isConnected()) {
                    socket.close();
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public boolean isRunning() {
        return mRun;
    }

    public interface OnMessageReceived {
        void messageReceived(String message);

        void onConnected();
    }
}