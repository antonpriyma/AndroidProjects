package com.example.anton.chat;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    public final String NAME_EXTRA="NAME";
    private Socket client;
    ChatClient chatClient;
    private PrintWriter printwriter;
    private EditText textField;
    private ImageButton button;
    private String messsage;
    private TextView textView;
    private BufferedReader mBufferIn;
    private String mServerMessage;
    private String name;
    private MemberData data;
    private MessageAdapter messageAdapter;
    private ListView messagesView;

    private MainActivity getInstance(){
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
        }
        chatClient=ChatClient.getInstance();

        name=getIntent().getStringExtra(NAME_EXTRA);
        data=new MemberData(name,getRandomColor());
        setContentView(R.layout.activity_main);
        messageAdapter = new MessageAdapter(this);
        messagesView = (ListView)findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        textField = (EditText) findViewById(R.id.edit_text); //reference to the text field
        button = findViewById(R.id.button1);   //reference to the send button
        new AsyncRequest().execute();
//        if (chatClient.isSetted()) {
//            messageAdapter.setMessages(chatClient.getMessages());
//        }


        //Button press event listener
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                messsage = textField.getText().toString(); //get the text message on the text field
                //textField.setText("");      //Reset the text field to blank
                Log.d("TAG", "point 1");
                new AsyncSendMessage().execute();




            }
        });

    }

    public void onMessage(String s,String name,boolean toCurrentUser) {
        try {

            final Message message = new Message(s, new MemberData(name,"#4a148c"),toCurrentUser);
            textField.setText("");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.add(message);
                    chatClient.getMessages().add(message);
                    messagesView.setSelection(messagesView.getCount() - 1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class AsyncSendMessage extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            chatClient.watchForConsoleInput(textField.getText().toString());
            return null;
        }
    }

    class AsyncRequest extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {


            try {
                try {
                        client = new Socket("192.168.0.50", 9000);  //connect to server
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    if (client.isConnected()) {
                        service();
                    }

                    if (ChatClient.getInstance().isSetted()){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messageAdapter.setMessages(chatClient.getMessages());
                            }
                        });

                    }



            } catch (Exception e){
                Log.d("TAG", "doInBackground: "+e.toString());
            }
            return null;
        }
    }


    public void setText(String s){
        textView.setText(s);
    }

    private void service(){
        Log.d("TAG", "point 2");

            chatClient.setClient(client, String.format("{%s}", name), getInstance());


        Runnable watch = new Runnable() {
            @Override
            public void run() {
                try {
                    chatClient.watchForConnectionInput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(watch).start();

    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    class MemberData {
        private String name;
        private String color;

        public MemberData(String name, String color) {
            this.name = name;
            this.color = color;
        }


        public MemberData() {
        }

        public String getName() {
            return name;
        }

        public String getColor() {
            return color;
        }

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
