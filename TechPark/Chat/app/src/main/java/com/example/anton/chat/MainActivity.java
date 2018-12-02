package com.example.anton.chat;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public final String NAME_EXTRA="NAME";
    private Socket client;
    ChatClient chatClient;
    private PrintWriter printwriter;
    private EditText textField;
    private Button button;
    private String messsage;
    private TextView textView;
    private OnMessageReceived mMessageListener = null;
    private BufferedReader mBufferIn;
    private String mServerMessage;
    private String name;

    private MainActivity getInstance(){
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name=getIntent().getStringExtra(NAME_EXTRA);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_view);
        textField = (EditText) findViewById(R.id.edit_text); //reference to the text field
        button = (Button) findViewById(R.id.button1);   //reference to the send button
        new AsyncRequest().execute();



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
                    client = new Socket("192.168.1.40", 9000);  //connect to server
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (client.isConnected()){
                    service();
                }
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{Manifest.permission.INTERNET},);

               // while (!mBufferIn.ready()) { }





//                printwriter.flush();
//                try { ;
//                    mServerMessage = mBufferIn.readLine();
//                    Log.d("TAG", "doInBackground: "+mServerMessage);
//                }catch (Exception e){
//                    Log.d("TAG", "doInBackground: "+e);
//                }
//                printwriter.close();
//                Log.d("TAG", "doInBackground: "+"OK");
//                while (client.isConnected()){
//
//                }
//
//
//                client.close();   //closing the connection


            } catch (Exception e){
                Log.d("TAG", "doInBackground: "+e.toString());
            }
            return null;
        }
    }

    public interface OnMessageReceived {
        public void messageReceived(String message);
    }

    public void setText(String s){
        textView.setText(s);
    }

    private void service(){
        Log.d("TAG", "point 2");
        chatClient=new ChatClient(client,String.format("{%s}",name),getInstance());
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
}
