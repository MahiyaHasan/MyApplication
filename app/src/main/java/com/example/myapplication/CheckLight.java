package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yangp.ypwaveview.YPWaveView;

import static android.os.Build.VERSION_CODES.N;



public class CheckLight extends AppCompatActivity {
    YPWaveView ypWaveView;
    private NotificationManagerCompat notificationManager;
    DatabaseReference reff;
    DatabaseReference CurrentLightIntensity;
    SwitchCompat reading_switch, light_switch;
    private NotificationHelper notificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_light);
        ypWaveView=findViewById(R.id.YPwaveView);
        light_switch=findViewById(R.id.turnFlaslightsOn_bt);
        reading_switch = findViewById(R.id.reading_switch);
        ypWaveView.setMax(1024);
        //ypWaveView.setProgress(50);
        ypWaveView.startAnimation();
        CurrentLightIntensity = FirebaseDatabase.getInstance().getReference().child("Moisture");

        notificationManager = NotificationManagerCompat.from(this);
        notificationHelper=new NotificationHelper(this);
        SharedPreferences sharedPreferences=getSharedPreferences("save",MODE_PRIVATE);
        light_switch.setChecked(sharedPreferences.getBoolean("value",true));
        reff = FirebaseDatabase.getInstance().getReference();



        light_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(light_switch.isChecked())
                {

                    reff.child("TurnLightOn").setValue("1");


                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    light_switch.setChecked(true);
                    sendOnChannel1("Lighting System","FlashLight is On");


                }
                else
                {
                    SharedPreferences.Editor editor=getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    light_switch.setChecked(false);
                    Toast.makeText(CheckLight.this,"FlashLight is Off",Toast.LENGTH_SHORT).show();
                    reff.child("TurnLightOn").setValue("0");

                }
            }
        });


        // ypWaveView.setAnimationSpeed();


        reading_switch.setOnClickListener(new View.OnClickListener() {
            ///////////////////////////###################
            @Override
            public void onClick(View v) {
                Boolean switchState = reading_switch.isChecked();
                if(switchState == true)
                {
                    reff.child("SendLightIntensityValue").setValue("1");

                    CurrentLightIntensity.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String moistureValue = ds.getValue().toString().trim();
                                int value=Integer.parseInt(moistureValue);
                                ypWaveView.setProgress(value);


//  int moistureValueInt = Integer.valueOf(moistureValue);
//  moisture_tv.setText(moistureValueInt);


                                // else
                                //  {
                                // notificationManager.cancel(0);
                                //
                                //notificationFlag = 0;
                                //notificationFlag2 = 0;
                                //}


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                }//////////if ends

                else
                {
                    reff = FirebaseDatabase.getInstance().getReference();

                    reff.child("SendLightIntensityValue").setValue("0");
                }
            }//// on click ends
            //////////////////////###############
        });






    }
    public void sendOnChannel1(String title, String message)
    {
        NotificationCompat.Builder nb=notificationHelper.getChannel1Notification(title,message);
        notificationHelper.getManager().notify(1,nb.build());
    }
}


