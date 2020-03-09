package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckMoisture extends AppCompatActivity {
Button waterPlant_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_moisture);
        waterPlant_bt = findViewById(R.id.waterPlant_bt);

        waterPlant_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Watering.class);
                startActivity(intent);
            }

        });


    }
}
