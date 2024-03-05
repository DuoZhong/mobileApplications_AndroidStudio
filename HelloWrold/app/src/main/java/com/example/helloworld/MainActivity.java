package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText helloworld;
    private Button submitButton;
    private TextView helloworldtw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloworld = (EditText) findViewById(R.id.editTextHelloWorld);
        submitButton = (Button) findViewById(R.id.buttonSubmit);
        helloworldtw = (TextView) findViewById(R.id.textView);
        submitButton.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonSubmit){
                String text = helloworld.getText().toString();
                if (text != "") {
                    helloworldtw.setText(text);
                }
        }
    }
}