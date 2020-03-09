package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    Button setThresholds_bt,renamePlants_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setThresholds_bt = findViewById(R.id.setThresholds_bt);
        renamePlants_bt = findViewById(R.id.renamePlants_bt);

        setThresholds_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SetThresholds.class);
                startActivity(intent);
            }

        });
        renamePlants_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RenamePlants.class);
                startActivity(intent);
            }

        });

    }
}
