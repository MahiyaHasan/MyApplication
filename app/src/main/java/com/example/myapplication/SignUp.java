package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {
    EditText fullName_et, username_et, password_et, retypePassword_et;
    Button signUp_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullName_et = findViewById(R.id.fullName_et);
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        retypePassword_et = findViewById(R.id.retypePassword_et);
        signUp_bt = findViewById(R.id.signUp_bt);
        findViewById(R.id.signUp_bt);
        signUp_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main1Activity.class);
                startActivity(intent);
            }

        });


    }
}
