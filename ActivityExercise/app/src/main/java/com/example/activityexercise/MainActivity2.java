package com.example.activityexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements ViewStub.OnClickListener{

    private EditText text1;
    private Button mybutton;

    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text1 = (EditText) findViewById(R.id.editTextActivity2);
        mybutton = (Button) findViewById(R.id.buttonactivity2);
        text2 = (TextView) findViewById(R.id.textViewActivity2);
        text2.setText(getIntent().getStringExtra("key1"));

        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonactivity2) {
            String text = text1.getText().toString();
            Intent myIntent = new Intent();
            myIntent.putExtra("key2",text);
            setResult(RESULT_OK, myIntent);
            finish();
        }
    }


}