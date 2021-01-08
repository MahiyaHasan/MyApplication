package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.Random;


public class Main1Activity extends AppCompatActivity {

    ListView simpleList;
    Button settings_bt,editProfile_bt,profile_bt,notification_bt,logout_bt;

    int flags[]={R.drawable.plant1, R.drawable.plant1, R.drawable.plant1, R.drawable.plant1,R.drawable.plant1,R.drawable.plant1};
    int arrows[]={R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp, R.drawable.ic_arrow_forward_black_24dp,R.drawable.ic_arrow_forward_black_24dp,R.drawable.ic_arrow_forward_black_24dp};
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        simpleList = findViewById(R.id.simpleListView);
        settings_bt = findViewById(R.id.settings_bt);
        editProfile_bt = findViewById(R.id.editProfile_bt);
        profile_bt = findViewById(R.id.profile_bt);
        notification_bt = findViewById(R.id.notification_bt);
        logout_bt = findViewById(R.id.logout_bt);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), arrows, flags);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(Main1Activity.this, Main2Activity.class);
               // put image data in Intent
                startActivity(intent); // start Intent
            }
        });

        settings_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }

        });

        editProfile_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
            }

        });



        logout_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogOut.class);
                startActivity(intent);
            }

        });
    }

    }
