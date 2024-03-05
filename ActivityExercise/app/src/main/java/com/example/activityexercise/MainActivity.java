package com.example.activityexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements ViewStub.OnClickListener{

    private EditText text1;
    private Button mybutton;

    private TextView text2;

    public static int code=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById(R.id.editTextActivity1);
        mybutton = (Button) findViewById(R.id.buttonactivity1);
        text2 = (TextView) findViewById((R.id.textViewActivity1));

        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.buttonactivity1 ){
            Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
            String val= text1.getText().toString();
            myIntent.putExtra("key1",val);
            //startActivities(myIntent);
            startActivityForResult(myIntent, code);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code){
            if(resultCode==RESULT_OK){
                String text=data.getStringExtra("key2");
                text2.setText(text);

            }
        }

    }
}