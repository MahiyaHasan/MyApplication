package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckWeather extends AppCompatActivity {
Button setAlarm_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_weather);
        setAlarm_bt = findViewById(R.id.setAlarm_bt);
        setAlarm_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoveSystem.class);
                startActivity(intent);
            }

        });
    }
}
