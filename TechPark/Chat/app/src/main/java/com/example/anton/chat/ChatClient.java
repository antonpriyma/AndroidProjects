package com.example.anton.chat;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ChatClient {
    private final Pattern p = Pattern.compile("^\\/([^\\s]*)\\s?(?:\\[([^\\]]*)\\])?\\s*(.*)$");
    private Socket socket;
    private final String TAG="CLIENT";
    private String username="";
    private PrintWriter printWriter;
    private MainActivity mainActivity;
    private boolean isSet;
    private static  ChatClient instance=new ChatClient();
    private List<Message> messages;

    public ChatClient() {
        messages=new ArrayList<>();
        return;
    }

    public boolean isSetted(){
        return !messages.isEmpty();
    }

    public static ChatClient getInstance(){
        return instance;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setClient(Socket socket, String username, MainActivity mainActivity){
        this.socket=socket;

        this.mainActivity=mainActivity;
        this.username=username;
        isSet=false;
        try {
            printWriter=new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance=this;
    }

    public void setConnection(Socket socket,MainActivity mainActivity){
        this.socket=socket;
        this.mainActivity=mainActivity;
    }



    public void watchForConnectionInput() throws IOException {
        BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (true){
            String message = reader.readLine();
            Log.d(TAG, "watchForConnectionInput: "+message);
            if (!message.equals("")){
                Command command=parseCommand(message);
                switch (command.Command){
                    case "ready":
                        sendCommand("user", username);
                        break;

                    case "connect":
                        Log.d(TAG,  command.UserName+"has connected to lobby");
                        isSet=true;

                        //sendCommand("message","mem");

                        break;

                    case "disconnect":
                        //fmt.Printf( Command.Username)
                        break;

                    case "enter":
                        //fmt.Println( Command.Username,"enter",Command.Body)
                        break;

                    case "leave":
                        //fmt.Println( Command.Username, "leave",Command.Body)
                        break;

                    case "message":
                        Log.d(TAG,  command.UserName+command.Body);
                        Log.d(TAG, username);
                        if (command.UserName.equals(username)){
                            Log.d(TAG, command.UserName+command.Body);
                            final String name=command.UserName;
                            final String body=command.Body;
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainActivity.onMessage(body,"",true);
                                }
                            });
                        }else {
                            Log.d(TAG, command.UserName+command.Body);
                            final String name=command.UserName;
                            final String body=command.Body;
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainActivity.onMessage(body,name.replace("{","").replace("}",""),false);
                                }
                            });
                        }
                    break;
                }
            }
        }
    }

    public void watchForConsoleInput(String message){
        Log.d(TAG, "watchForConsoleInput: "+message);
        while (!isSet){ ;}
        if (!message.isEmpty()){
            Log.d(TAG, "watchForConsoleInput: "+username);
//            Command command=parseInput(message);
//            if (command.Command=="") {
                sendCommand("message", message);
//            }else {
//                switch (command.Command){
//                    case "enter":
//                        sendCommand("enter", command.Body, conn);
//                    case "leave":
//                        sendCommand("leave", "", conn)
//                }
//            }
        }
    }

    private class Command{
        private String Command;
        private String Body;
        private String UserName;

        Command(String command,String body,String userName){
            this.Command=command;
            this.Body=body;
            this.UserName=userName;
        }

    }

    private Command parseCommand(String message){
        String res[] =message.split(" ");
        if (res.length==2){
            return new Command("connect","","{Anton}");
        }else if (res.length==3) {
            return new Command("message",res[2],res[1].replace("[{","{").replace("}]","}"));
        }else{
            return new Command("ready","","");
        }
    }

//    private Command parseInput(String message){
//        String res[] = message.split(" ");
//        if (res.length==2){
//            return new Command()
//        }
//    }

    private void sendCommand(String command,String body){
        String message = String.format("/%s %s\n",command,body);
        Log.d(TAG, "SendCommand: "+message);
        printWriter.write(message);
        printWriter.flush();
    }







}
