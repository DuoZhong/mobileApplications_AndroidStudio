package com.example.progressapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class ProgressService extends Service {
    // IBinder instance
    private final IBinder binder = new LocalBinder();
    // Current progress
    private int progress = 0;
    // Whether progress is paused
    private boolean isPaused = true;
    // Handler and Runnable for updating progress
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable progressUpdater;

    public static final String BROADCAST_ACTION_PROGRESS_UPDATE = "com.example.serviceexample.progress_update";
    public static final String EXTRA_PROGRESS = "progress";


    // ServiceConnection will call this to get the binder
    @Override
    public IBinder onBind(Intent intent) {
        // Initialize the progress updater Runnable
        progressUpdater = new Runnable() {
            @Override
            public void run() {
                if (progress < 100 && !isPaused) {
                    progress++;
                    // Broadcast progress update
                    broadcastUpdate(progress);
                    handler.postDelayed(this, 100); // Update every 100 milliseconds
                } else if (progress == 100) {
                    // Stop the service or mark as completed
                    broadcastUpdate(progress);
                    // Broadcast stop signal
                }
            }
        };
        return binder;
    }

    // Method to start progress
    public void startProgress() {
        isPaused = false;
        handler.post(progressUpdater);
    }

    // Method to pause progress
    public void pauseProgress() {
        isPaused = true;
        handler.removeCallbacks(progressUpdater);
    }

    public void resumeProgress() {
        isPaused = false;
        handler.post(progressUpdater);
    }

    // Method to get current progress
    public int getProgress() {
        return progress;
    }

    public boolean isPaused() {
        return isPaused;
    }

    // LocalBinder class
    public class LocalBinder extends Binder {
        ProgressService getService() {
            return ProgressService.this;
        }
    }

    private void broadcastUpdate(int progress) {
        Intent intent = new Intent(BROADCAST_ACTION_PROGRESS_UPDATE);
        intent.putExtra(EXTRA_PROGRESS, progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
