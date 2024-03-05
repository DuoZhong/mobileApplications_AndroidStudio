package com.example.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

private final IBinder mBinder = new BoundService();
public class BoundService extends Service {
    private final IBinder mBinder = new BoundService();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }

    Binder
    BoundService getService(){

    }
}