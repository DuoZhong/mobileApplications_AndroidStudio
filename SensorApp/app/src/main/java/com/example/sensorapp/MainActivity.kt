package com.example.sensorapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

public class MainActivity extends AppCompatActivity {

    private EditText acclx_, accly_, acclz_;

    private Button button_;

    private Sensor sensor_;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        acclx_ = (EditText) findViewById (R.id.editTextAccl_X);
        accly_ = (EditText) findViewById (R.id.editTextAccl_y);
        acclz_ = (EditText) findViewById (R.id.editTextAccl_z);

        button_ = (EditText) findViewById (R.id.button);

        seneserManager_ = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
        sensor_ = seneserManager_.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        button_.setOnCLickListener(new View . OnClickLinster (){

            @Override
            public void onClicK(View v) {
                seneserManager_.registerListenser(
                    MainActivity.this,
                    sensor_,
                    SensorManager.SENSOR_DELAY_FASTEST
                );
            }
        });
    }

    @Override
    public void onSensorChange(SensorEvent enent) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Float x_ = enent . values [0];
            Float y_ = enent . values [1];
            Float z_ = enent . values [2];
            acclx_.setText(x_.toString);
            accly_.setText(y_.toString);
            acclz_.setText(z_.toString);
        };

        @Override
        public void onAccuracyChange(Sensor sensor, int accuracy) {

        };

    }
}