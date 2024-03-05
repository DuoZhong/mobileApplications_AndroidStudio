package com.example.sensortempeture;

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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private EditText temperature_;

    private Button button_;

    private Sensor sensor_;

    private Handler handler_;

    private SensorManager seneserManager_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature_ = (EditText) findViewById(R.id.editText_temperature);

        button_ = (Button) findViewById(R.id.button);

        seneserManager_ = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_ = seneserManager_.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

//        sensor_ = seneserManager_.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seneserManager_.registerListener(
                        MainActivity.this,
                        sensor_,
                        SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        AccWork temp_ = new AccWork(event);
        handler_.post(temp_);
    }


    private class AccWork implements Runnable{
        private SensorEvent event_;

        public AccWork(SensorEvent event){
            this.event_ = event;
        }
        @Override
        public void run() {
            if (this.event_.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                Float temperature = event_.values[0];
                temperature_.setText(temperature.toString());
            }
        }
    };

    public void onAccuracyChanged (Sensor sensor,int accuracy){

    }


    @Override
    protected void onPause() {
        super.onPause();
        seneserManager_.unregisterListener(this);
    }
}