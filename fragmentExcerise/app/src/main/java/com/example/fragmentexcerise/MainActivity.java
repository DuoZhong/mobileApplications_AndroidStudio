package com.example.fragmentexcerise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_;
    private Fragment1 fragment1;

    private boolean isFragment1Displayed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_ = (Button) findViewById(R.id.button);
        Fragment1 fragment1= new Fragment1();
        Fragment2 fragment2 = new Fragment2();


        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragment(fragment1,fragment2);
            }
        });

    }

    private void swapFragment(Fragment fragment1, Fragment fragment2){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment2);
        fragmentTransaction.replace(R.id.frameLayout2,fragment1);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

    }


}