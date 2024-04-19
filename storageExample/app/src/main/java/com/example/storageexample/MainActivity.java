package com.example.storageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton, loginButton;

    private EditText username, password;

    private SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences(registerActivity.namepres,MODE_PRIVATE);

        username = (EditText) findViewById(R.id.editTextText2);
        password = (EditText) findViewById(R.id.editTextTextPassword2);

        registerButton = (Button) findViewById(R.id.button2);
        loginButton = (Button) findViewById(R.id.button3);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button2){

            String username_ = prefs.getString("Username", "NoUSERNAME");
            String password_ = prefs.getString("Password", "Nopassword");

            String iusername = username.getText().toString();
            String ipassword = password.getText().toString();




            if (username_.equals(iusername) && password_.equals(ipassword)){

                Toast.makeText(getApplicationContext(),"login successful", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"login unsucessful", Toast.LENGTH_LONG).show();
            }



        }

        else if(v.getId() == R.id.button3){
            Intent mIntent = new Intent(MainActivity.this,registerActivity.class);
            startActivityForResult(mIntent,1);
        }
    }
}