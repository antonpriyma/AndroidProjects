package com.example.anton.syshelper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InfoClient {
    private final Pattern p = Pattern.compile("^\\/([^\\s]*)\\s?(?:\\[([^\\]]*)\\])?\\s*(.*)$");
    private Socket socket;
    private  boolean watch;
    private final String TAG="CLIENT";
    private String username="";
    private ServerInfo serverInfo;
    private PrintWriter printWriter;
    private ServerInfoActivity mainActivity;
    private boolean isSet;
    private static  InfoClient instance=new InfoClient();
    //private List<Message> messages;

    public InfoClient() {
       // messages=new ArrayList<>();
        return;
    }

//    public boolean isSetted(){
//        return !messages.isEmpty();
//    }

    public static InfoClient getInstance(){
        return instance;
    }

   // public List<Message> getMessages() {
//        return messages;
//    }

    public void setClient(Socket socket, ServerInfo serverInfo,ServerInfoActivity mainActivity){
        this.socket=socket;

        this.mainActivity=mainActivity;
        this.serverInfo=serverInfo;
        isSet=false;
        try {
            printWriter=new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance=this;
    }

    public void setConnection(Socket socket,ServerInfoActivity mainActivity){
        this.socket=socket;
        this.mainActivity=mainActivity;
    }



    public void watchForConnectionInput() throws IOException {
        watch=true;
        BufferedReader reader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while (watch){
            String message = reader.readLine();
            Log.d(TAG, "watchForConnectionInput: "+message);
            if (!message.equals("")){
                final Command command=parseCommand(message);
                switch (command.Command){
                    case "ready":
                        sendCommand("serverUserName", "USER");
                        sendCommand("serverServerName", serverInfo.getName());
                        sendCommand("serverHost", serverInfo.getHost());
                        sendCommand("serverPort", serverInfo.getPort());
                        sendCommand("serverPassword", serverInfo.getPassword());
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
                       // mainActivity.makeEnterToast(command.UserName,command.Body);
                        break;

                    case "leave":
                       // mainActivity.makeLeaveToast(command.UserName,command.Body);
                        break;

                    case "postDiskInfo":
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.setDiskInfoText(new DiskInfo(command.Body));
                            }
                        });
                        break;

                    case "postExe":
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.setConsoleLog(command.Body);
                            }
                        });
                        break;
                    case "postFilesExe":
                        mainActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.setFiles(command.Body);
                            }
                        });
                        break;
                    case "remove":
                        watch=false;
                        socket.close();
                        break;




                    case "message":
//                        Log.d(TAG,  command.UserName+command.Body);
//                        Log.d(TAG, username);
//                        if (command.UserName.equals(username)){
//                            Log.d(TAG, command.UserName+command.Body);
//                            final String name=command.UserName;
//                            final String body=command.Body;
//                            mainActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mainActivity.onMessage(body,"",true);
//                                }
//                            });
//                        }else {
//                            Log.d(TAG, command.UserName+command.Body);
//                            final String name=command.UserName;
//                            final String body=command.Body;
//                            mainActivity.runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    mainActivity.onMessage(body,name.replace("{","").replace("}",""),false);
//                                }
//                            });
//                        }
                        break;
                }
            }
        }
    }

    public void watchForConsoleInput(String message){
        Log.d(TAG, "watchForConsoleInput: "+message);
        //
        if (!message.isEmpty()){
            Log.d(TAG, "watchForConsoleInput: "+username);
            Command command=parseInput(message);
            if (command.Command=="") {
                sendCommand("message", message);
            }else {
                switch (command.Command){
                    case "enter":
                        sendCommand("enter", command.Body);
                        break;
                    case "leave":
                        sendCommand("leave", "");
                        break;
                    case "test":
                        sendCommand("test",command.Body);
                        break;
                    case "getDiskInfo":
                        sendCommand("getDiskInfo",command.Body);
                        break;
                    case "exe":
                        sendCommand("exe",command.Body);
                        break;
                    case "filesExe" :
                        sendCommand("filesExe",command.Body);
                        break;
                }
            }
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
        StringBuilder s=new StringBuilder();
        if (message.charAt(1)=='p'){
            switch (message.charAt(5)){
                case 'D':
                    return new Command("postDiskInfo",message.substring(message.indexOf("]")+2),"");
                case 'E':
                    return new Command("postExe",message.substring(message.indexOf("]")+2),"");
                case 'F':
                    return new Command("postFilesExe",message.substring(message.indexOf("]")+2),"");
            }

        }
        if (res.length==2){
            return new Command("connect","","{Anton}");
        }else if (res.length>=3) {
            if (res[0].charAt(1)=='e'){
                return new Command("enter",res[2],res[1].replace("[","").replace("]",""));
            }else if (res[0].charAt(1)=='l'){
                return new Command("leave","",username);
            }else if(res[0].charAt(1)=='r'){
                return new Command("ready","","");
            }
            for (int i=2;i<res.length;i++){
                s.append(" "+res[i]);
            }
            return new Command("message",s.toString(),res[1].replace("[{","{").replace("}]","}"));
        }else {
            return null;
        }
    }

    private Command parseInput(String message) {
        String res[]=message.split(" ");
        if (res[0].charAt(1) == 'e') {
                String args = message.substring(message.indexOf(" ")+1);
                return new Command("exe", args, username);
        }else if (res[0].charAt(1) == 'f') {
            String args = message.substring(message.indexOf(" ") + 1);
            return new Command("filesExe", args, username);
        }
            if (res[0].equals("/test")) {
                return new Command(res[0].replace("/", ""), res[1].replace("\n", ""), username);
            } else if (!message.contains("/")) {
                return new Command("", "", "");
            }
            res = message.split(" ");
            if (res.length == 2) {
                if (res[0].charAt(1) == 'e') {
                } else
                    return new Command("enter", res[1], username);
            } else {
                if (res[0].charAt(1) == 'g') {
                    return new Command("getDiskInfo", "", username);
                } else
                    return new Command("leave", "", username);
            }
            return null;
        }

    private void sendCommand(String command,String body){
        String message = String.format("/%s %s\n",command,body);
        Log.d(TAG, "SendCommand: "+message);
        while (printWriter==null){
            ;
        }
        printWriter.write(message);
        printWriter.flush();
    }

    public void closeConnection(){
        watch=false;
        sendCommand("close","");
    }







}
