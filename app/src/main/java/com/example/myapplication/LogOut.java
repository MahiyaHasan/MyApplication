package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LogOut extends AppCompatActivity {
    Button yes_bt;
    Button no_bt;
    TextView r_tv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        yes_bt=findViewById(R.id.yes_bt);
        no_bt=findViewById(R.id.no_bt);
        r_tv=findViewById(R.id.r_tv);
        r_tv.setText("Click No to go back to Home");
        no_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }

        });
        yes_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                logout(view);
            }

        });


    }
    public void logout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
