package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
        int mode = 0;
        if(intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            try{
                mode = Settings.Global.getInt(context.getContentResolver(),
                        Settings.Global.AIRPLANE_MODE_ON);
            } catch (Settings.SettingNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(mode != 0){
                    Log.d("MyReceiver", "airplane mode turned on");
                }
                else{
                    Log.d("MyReceiver","airplane mode not turned on");
                }
            }
        }




    }
}