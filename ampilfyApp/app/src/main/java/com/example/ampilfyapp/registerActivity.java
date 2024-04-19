package com.example.ampilfyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class registerActivity extends AppCompatActivity {
    private EditText usrename, password, confirmcode;
    private Button register,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usrename = (EditText) findViewById(R.id.editTextText);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        confirmcode = (EditText) findViewById(R.id.editTextNumber);
        register = (Button) findViewById(R.id.button);
        confirm = (Button) findViewById(R.id.button2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(),password.getText().toString()).build();
                Amplify.Auth.signUp(usrename.getText().toString(), password.getText().toString(),
                        options,result -> Log.i("AmplifyRegister","Result"+result.toString()),
                        error ->Log.e("AmplifyRegister","Result"+error.toString()));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}