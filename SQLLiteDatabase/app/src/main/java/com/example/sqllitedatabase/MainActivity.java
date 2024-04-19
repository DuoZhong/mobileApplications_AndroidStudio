package com.example.sqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button add;
    private Button update;
    private Button fetch;
    private Button delete;

    private EditText username;
    private EditText password;

    private DatabaseManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbmanager = new DatabaseManager();


        username = (EditText) findViewById(R.id.editTextText);
        password =(EditText) findViewById(R.id.editTextText2);

        add = (Button) findViewById(R.id.buttonAdd);
        update = (Button) findViewById(R.id.buttonUpdate);
        fetch = (Button) findViewById(R.id.buttonRead);
        delete = (Button) findViewById(R.id.buttonDelete);

    }

    public void onClick(View v){
        if(v.getId()==R.id.buttonAdd){
            dbmanager.insert(username.getText().toString(), password);
        }
        else if(v.getId()==R.id.buttonRead){
            readDB();
        }
        else if(v.getId()==R.id.buttonDelete){

        }
        else  if(v.getId()==R.id.buttonUpdate){

        }



    }

    private List<User> readDB(){

        Cursor cursor = dbmanager.fetch();
        List<User> myuser = new LinkedList<User>();
        do{
           @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME).toString());
           @SuppressLint("Range") String pawwword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.PASSWORD).toString());

        }while
    }



}