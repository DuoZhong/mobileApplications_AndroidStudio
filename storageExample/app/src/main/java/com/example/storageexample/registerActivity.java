package com.example.storageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;

public class registerActivity extends AppCompatActivity {

    private Button registerButton;

    private EditText username, password;

    private SharedPreferences prefs;

    private SharedPreferences.Editor editor;

    public static String namepres = "Authentication";


//    public static String fire;

//    private PrintStream pstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        prefs = getSharedPreferences(namepres, MODE_PRIVATE);
        editor = prefs.edit();


//        try {
//            fstrem =  onpenFileOutput(filename, MODE_APPEND);
//            PrintStream pstream = new PrintStream(fstrem);
//        } catch (FileOutputStream);



        username = (EditText) findViewById(R.id.editTextText);
        password = (EditText) findViewById(R.id.editTextTextPassword);

        registerButton = (Button) findViewById(R.id.button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username_ = username.getText().toString();
                String password_ = password.getText().toString();

//                pstream.println("Username: "+ username);
//                pstream.println("Password: "+password);
//                pstream.close();

                editor.putString("Username",username_);
                editor.putString("Password", password_);

                editor.commit();

                finish();

            }
        });

    }
}