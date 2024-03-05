package com.example.serviceexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ForegroundService extends Service {

    private static  String TAG = "Foreground service";
    private static int ID=5;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Log.d(TAG,"Foreground Service Running");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        myThread.start();
        final String ChannelID = "Foreground Service Channel";
        NotificationChannel channel = new NotificationChannel(ChannelID,"Foreground Service",
                NotificationManager.IMPORTANCE_LOW);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder mynotification=new Notification.Builder(this,
                ChannelID).setContentTitle("Foreground Service").setContentText("Foreground Service Runing");
        startForeground(ID, mynotification.build());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}