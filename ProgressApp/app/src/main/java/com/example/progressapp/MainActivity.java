package com.example.progressapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button startPauseButton;
    private boolean isBound = false;
    private ProgressService progressService;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ProgressService.LocalBinder binder = (ProgressService.LocalBinder) service;
            progressService = binder.getService();
            isBound = true;

            if(progressService.getProgress() < 100 && !progressService.isPaused()) {
                startPauseButton.setText("Pause");
            } else {
                startPauseButton.setText("Start");
            }
            updateUI();
        }


        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };

    private void updateUI() {
        if(progressService != null){
            int progress = progressService.getProgress();
            progressBar.setProgress(progress);
            if (progress >= 100) {
                startPauseButton.setText("Stop");
                // Optionally unbind the service here
            } else if (progressService.isPaused())  {
                startPauseButton.setText("Start");

            } else {
                startPauseButton.setText("Pause");
            }
        }
    }


    private BroadcastReceiver progressUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ProgressService.BROADCAST_ACTION_PROGRESS_UPDATE)) {
                int progress = intent.getIntExtra(ProgressService.EXTRA_PROGRESS, 0);
                progressBar.setProgress(progress);
                if (progress >= 100) {
                    startPauseButton.setText("Stop");
                }
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ProgressService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(this).registerReceiver(progressUpdateReceiver,
                new IntentFilter(ProgressService.BROADCAST_ACTION_PROGRESS_UPDATE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(progressUpdateReceiver);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        startPauseButton = findViewById(R.id.startPauseButton);
        startPauseButton.setText("Start");


        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    if (progressService.getProgress() < 100 && !progressService.isPaused()) {
                        // 如果进度条正在运行，暂停进度条
                        progressService.pauseProgress();
                        startPauseButton.setText("Start");
                    } else {
                        // 如果进度条暂停或尚未开始，启动或恢复进度条
                        if(progressService.getProgress() < 100) {
                            progressService.resumeProgress();
                            startPauseButton.setText("Pause");
                        } else {
                            // 进度条已完成
                            startPauseButton.setText("Stop");
                        }
                    }
                }
                updateUI();
            }
        });
    }
}
