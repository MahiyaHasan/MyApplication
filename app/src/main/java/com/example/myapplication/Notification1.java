package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Notification1 extends AppCompatActivity {
  TextView t1_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification1);
        t1_tv = findViewById(R.id.textView12);
        String g=getIntent().getStringExtra("message");
        t1_tv.setText(g);
    }
}