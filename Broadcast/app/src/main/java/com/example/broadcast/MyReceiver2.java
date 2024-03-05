package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()=="ACTION_CUSTOM_BROADCAST"){
            Log.d("MyReceiver2", "Received ACTION_CUSTOM_BROADCAST");
        }
    }
}