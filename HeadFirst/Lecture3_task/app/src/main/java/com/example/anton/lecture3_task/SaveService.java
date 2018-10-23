package com.example.anton.lecture3_task;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class SaveService extends Service{

    public static final String ACTION_OPEN = "open";
    public static final String ACTION_SAVE ="save" ;
    public static final String EXTRA_VALUE = "value";

    private String value;

    @Override
    public int onStartCommand(final Intent intent, final int flags,final int startId){
        final String action = intent.getAction();

        if (action==null){
            throw new NullPointerException("Action is null");
        }
        switch (action){
            case ACTION_SAVE:
                value=intent.getStringExtra(SaveService.EXTRA_VALUE);
                break;
            case ACTION_OPEN:
                run();

        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void run(){
        Intent intent= new Intent(MainActivity.BROADCAST_ACTION);
        intent.putExtra(EXTRA_VALUE,value);
        sendBroadcast(intent);
    }
}
