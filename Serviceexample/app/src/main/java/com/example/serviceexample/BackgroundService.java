package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {

    private static  String TAG = "Background service";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread mythread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Log.d(TAG,"Keep Alive Message");
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException  e){
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        mythread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}