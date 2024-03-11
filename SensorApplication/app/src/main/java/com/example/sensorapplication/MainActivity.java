package com.example.sensorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText acclx_, accly_, acclz_;

    private Button button_;

    private Sensor sensor_;
    private Sensor gyroscope_;

    private Handler handler_;

    private SensorManager sensorManager_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acclx_ = (EditText) findViewById(R.id.editTextAccl_X);
        accly_ = (EditText) findViewById(R.id.editTextAccl_y);
        acclz_ = (EditText) findViewById(R.id.editTextAccl_z);

        button_ = (Button) findViewById(R.id.button);

        sensorManager_ = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_ = sensorManager_.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope_ = sensorManager_.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        handler_ = new Handler(Looper.getMainLooper());

        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager_.registerListener(
                        MainActivity.this,
//                        sensor_,
                        gyroscope_,
//                        SensorManager.SENSOR_DELAY_FASTEST,
                        SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            Float x_ = event.values[0];
//            Float y_ = event.values[1];
//            Float z_ = event.values[2];
//            acclx_.setText(x_.toString());
//            accly_.setText(y_.toString());
//            acclz_.setText(z_.toString());
//        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            AccWork temp_ = new AccWork(event);
            handler_.post(temp_);
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            GyrWork temp_ = new GyrWork(event);
            handler_.post(temp_);
        }

//        AccWork temp_ = new AccWork(event);
//        handler_.post(temp_);
    }


    private class AccWork implements Runnable{
        private SensorEvent event_;

        public AccWork(SensorEvent event){
            this.event_ = event;
        }
        @Override
        public void run() {
            if (this.event_.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Float x_ = event_.values[0];
            Float y_ = event_.values[1];
            Float z_ = event_.values[2];
            acclx_.setText(x_.toString());
            accly_.setText(y_.toString());
            acclz_.setText(z_.toString());
            }
        }
    };


    private class GyrWork implements Runnable{
        private SensorEvent event_;

        public GyrWork(SensorEvent event){
            this.event_ = event;
        }
        @Override
        public void run() {
            if (this.event_.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                Float x_ = event_.values[0];
                Float y_ = event_.values[1];
                Float z_ = event_.values[2];
                acclx_.setText(x_.toString());
                accly_.setText(y_.toString());
                acclz_.setText(z_.toString());
            }
        }
    };

    public void onAccuracyChanged (Sensor sensor,int accuracy){

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager_.unregisterListener(this);
    }
}