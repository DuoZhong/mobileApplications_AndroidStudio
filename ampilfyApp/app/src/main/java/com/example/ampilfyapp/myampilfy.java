package com.example.ampilfyapp;

import android.app.Application;

import com.amplifyframework.core.Amplify;

public class myampilfy extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
