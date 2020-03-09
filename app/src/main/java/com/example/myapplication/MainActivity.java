package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText username_et, password_et;
    Button login_bt,signUp_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findview
        username_et = findViewById(R.id.username_et);
        password_et = findViewById(R.id.password_et);
        login_bt = findViewById(R.id.login_bt);
        signUp_bt = findViewById(R.id.signup_bt);

        ////intent
        login_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main1Activity.class);
                startActivity(intent);
            }

        });


        signUp_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }

        });

    }
}
