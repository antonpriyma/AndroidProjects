package com.example.anton.syshelper;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInfoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    DiskInfoFragment diskInfoFragment;
    ConsoleFragment consoleFragment;
    private Socket client;
    InfoClient chatClient;
    ServerInfo serverInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_info);
        initTabs();
        setServerInfo();
        chatClient=new InfoClient();
        new AsyncRequest().execute();
    }

    private void initTabs() {//Инициализация верхнего меню
        diskInfoFragment=new DiskInfoFragment();
        consoleFragment=new ConsoleFragment();
        viewPager = (ViewPager)findViewById(R.id.view_pager);//Разметка
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);//Разметка Бара выбора
        TabsAdapter adapter = new TabsAdapter(this,getSupportFragmentManager());//Обработчик выбора
        adapter.addFragment(diskInfoFragment,"TEST");
         adapter.addFragment(consoleFragment,"CONSOLE");
        //adapter.addFragment(toDoFragment,getString(R.string.todo_string));//Example fragment - просто xml разметка
//        adapter.addFragment(new AbstractFragment(),getString(R.string.todo_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.ideas_string));
//        adapter.addFragment(new AbstractFragment(),getString(R.string.birthday_string));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    protected void setDiskInfoText(DiskInfo diskInfo){
        diskInfoFragment.setDiskInfo(diskInfo);
    }

    protected void setConsoleLog(String log){
        consoleFragment.addLog(log);
    }

    private void setServerInfo(){
        Intent intent=getIntent();
        serverInfo=new ServerInfo(intent.getStringExtra("NAME"),
                intent.getStringExtra("HOST"),
                intent.getStringExtra("PORT"),
                intent.getStringExtra("PASSWORD"),
                "");

    }

    private void service(){
        Log.d("TAG", "point 2");

        chatClient.setClient(client, serverInfo, getInstance());


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

    private ServerInfoActivity getInstance(){
        return this;
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

//
            } catch (Exception e){
                Log.d("TAG", "doInBackground: "+e.toString());
            }
            return null;
        }
    }
    //TODO сделать нормально через константы
    protected String getDiskInfo(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                chatClient.watchForConsoleInput("/getDiskInfo");
            }
        });
        thread.start();

        return "";
    }

    protected String execCommand(final String command){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                chatClient.watchForConsoleInput("/exe "+command);
            }
        });
        thread.start();

        return "";
    }


}