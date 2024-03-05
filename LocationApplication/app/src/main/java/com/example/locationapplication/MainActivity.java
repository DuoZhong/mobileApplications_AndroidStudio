package com.example.locationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener{

    private EditText latitude_;
    private EditText longitude_;

    private Button button_;

    private LocationManager locationManager_;

    private Handler handler_;

    private Sensor sensor_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude_ = (EditText) findViewById(R.id.editTextText_latitude);
        longitude_ = (EditText) findViewById(R.id.editTextText_longitude);
        button_ = (Button) findViewById(R.id.button);

        locationManager_ = (LocationManager) getSystemService(LOCATION_SERVICE);
        handler_ = new Handler();

        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(locationManager_.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(context MainActivity.this, android.Manifest))
                    locationManager_.requestLocationUpdates();
            }
        }
    };


    protected void onPause(){
        super.onPause();
        locationManager_.removeUpdates(MainActivity.this);
    }


    private Location location;

    private class LocationTask implements Runnable {

        private Location location;

        public LocationTask(Location location_){
            this.location = location_;
        }

        public void run() {
            Double latitude = this.location.getLatitude();
            Double longitude = this.location.getLongitude();
            latitude_.setText(latitude.toString());
            longitude_.setText(longitude.toString());
        }

    }

    @Override
    public void onLocationChanged(@NonNull List<Location> location) {
//      Double latitude = location.getLatitude;
//      Double longitude = location.getLongitude;
//      latitude_.setText(latitude.toString());
//      longitude_.setText(longitude.toString());
        LocationTask mytask = new LocationTask(location);
        handler_.post(mytask);

    }
}