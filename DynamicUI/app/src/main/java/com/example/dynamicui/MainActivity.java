package com.example.dynamicui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private Listview listview_;

    private String[] fruitnames={"alpple","banana"};
    private int[] images ={R.drawable.apple, R.drawable.banana};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview_ = (Listview) findViewById(R.id.listview);

        CustomerAdapter adapter_ =new CustomerAdapter(getApplicationContext(), fruitnames, images);

        listview_.setAdapter(adapter_);

//        listview_.setOnItemClickListener(new View.OnClickListener(){
//            onItemClick(A)
//        })


    }
}