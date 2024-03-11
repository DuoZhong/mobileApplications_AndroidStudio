package com.example.orientationtrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private TextView orientation_;

    private Button button_;

    private Sensor sensor_;
    private Sensor gyroscope_;


    private static final Float WEIGHT= 0.4f;

    private SensorManager sensorManager_;

    private Float previousOrient = 0f;

    private Float acclx_, accly_, gryz_;

    private long lastTimestamp = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientation_ = (TextView) findViewById(R.id.textView1);
        button_ = (Button) findViewById(R.id.button);

        sensorManager_ = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_ = sensorManager_.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope_ = sensorManager_.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager_.registerListener(
                        MainActivity.this,
                        sensor_,
//                        SensorManager.SENSOR_DELAY_FASTEST,
                        SensorManager.SENSOR_DELAY_NORMAL);

                sensorManager_.registerListener(
                        MainActivity.this,
                        gyroscope_,
                        SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

    };

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (lastTimestamp == -1) {
            lastTimestamp = event.timestamp;
            return;
        }

        Float timeStep = (event.timestamp - lastTimestamp) * 1.0f / 1000000000.0f;

        lastTimestamp = event.timestamp;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acclx_ = event.values[0];
            accly_ = event.values[1];
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gryz_ = event.values[2];
        }

        if (accly_ != null && acclx_ != null && gryz_!= null) {
            Float thetaGyro = previousOrient + gryz_ * timeStep;
            Double thetaAcc = Math.atan2(acclx_.doubleValue(), accly_.doubleValue());
            Float orientation = WEIGHT * thetaGyro + (1 - WEIGHT) * thetaAcc.floatValue();

            orientation_.setText(orientation.toString());
            previousOrient = orientation;
        }

    }


    public void onAccuracyChanged (Sensor sensor,int accuracy){

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager_.unregisterListener(this);
    }

}