package com.example.cameraactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button myButton;

    private ImageView myImageview;

    public static int code=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.button);
        myImageview = (ImageView) findViewById(R.id.imageView);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(myIntent, code); // 此函数逐渐被下面的函数取代
                activityResultLauncher.launch(myIntent);

            }
        });

    }

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if(o.getResultCode() == RESULT_OK){
                                Intent data = o.getData();
                                Bitmap image = (Bitmap) data.getExtras().get("data");
                                myImageview.setImageBitmap(image);
                            }
                        }
                    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==code & resultCode==RESULT_OK){
            Bitmap image = (Bitmap) data.getExtras().get("data");
            myImageview.setImageBitmap(image);
        }
    }
}