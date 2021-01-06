package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckPH extends AppCompatActivity {
Button setScheduleForManuring_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_ph);

        setScheduleForManuring_bt = findViewById(R.id.setScheduleForManuring_bt);


        setScheduleForManuring_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManuringSchedule.class);
                startActivity(intent);
            }

        });
    }
}
